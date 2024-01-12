package script.player;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.static_item;
import script.obj_id;
import script.string_id;

public class jedi_conversion_robe extends script.base_script
{
    public static final String STF_FILE = "jedi_spam";

    public jedi_conversion_robe()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "giveRobe", null, 1, false);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, "giveRobe", null, 1, false);
        return SCRIPT_CONTINUE;
    }

    public int giveRobe(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id gaveRobe = static_item.createNewItemFunction("item_jedi_robe_padawan_04_01", self);
        if (gaveRobe == null)
        {
            messageTo(self, "giveRobe", null, 60, false);
            sendSystemMessage(self, new string_id(STF_FILE, "inventory_full_jedi_robe"));
        }
        else
        {
            detachScript(self, "player.jedi_conversion_robe");
        }
        return SCRIPT_CONTINUE;
    }
}
