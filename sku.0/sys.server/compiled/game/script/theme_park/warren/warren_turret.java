package script.theme_park.warren;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;

public class warren_turret extends script.base_script
{
    public warren_turret()
    {
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id who) throws InterruptedException
    {
        if (!isIdValid(who))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id bldg = getTopMostContainer(self);
        if (utils.hasScriptVar(bldg, "warren.deactivated"))
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeExited(obj_id self, String volumeName, obj_id who) throws InterruptedException
    {
        obj_id bldg = getTopMostContainer(self);
        if (utils.hasScriptVar(bldg, "warren.deactivated"))
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
