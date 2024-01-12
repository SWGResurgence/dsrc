package script.theme_park.warren;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.sui;
import script.menu_info;
import script.obj_id;
import script.string_id;

public class inquisitor_letter extends script.base_script
{
    public static final String SYSTEM_MESSAGES = "theme_park/warren/warren_system_messages";

    public inquisitor_letter()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        sui.msgbox(player, new string_id(SYSTEM_MESSAGES, "inq_letter_text"));
        return SCRIPT_OVERRIDE;
    }
}
