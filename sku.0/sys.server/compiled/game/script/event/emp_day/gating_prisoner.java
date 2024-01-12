package script.event.emp_day;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.string_id;

public class gating_prisoner extends script.base_script
{
    public gating_prisoner()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(item, "event.emp_day.prisoner"))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            sendSystemMessage(item, new string_id("event/empire_day", "no_entry"));
            return SCRIPT_OVERRIDE;
        }
    }
}
