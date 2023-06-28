package script.item;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.buff;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class buffer_ent extends script.base_script
{
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("sui", "apply_ent_buff"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {

        if (item == menu_info_types.ITEM_USE)
        {
            buff.applyBuff(player, "general_inspiration", 3600);
        }
        return SCRIPT_CONTINUE;
    }
}
