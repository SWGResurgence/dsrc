package script.quest.crowd_pleaser;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.utils;
import script.obj_id;

public class player_performance extends script.base_script
{
    public static final String PERFORMANCE_OBJVAR = "quest.crowd_pleaser.performance";
    public static final String CONTROL_OBJVAR = PERFORMANCE_OBJVAR + ".control";
    public player_performance()
    {
    }

    public int OnImmediateLogout(obj_id self) throws InterruptedException
    {
        obj_id control = utils.getObjIdScriptVar(self, CONTROL_OBJVAR);
        messageTo(control, "handleReceivePlayerFail", null, 0.0f, false);
        return SCRIPT_CONTINUE;
    }

    public int OnFlourish(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id control = utils.getObjIdScriptVar(self, CONTROL_OBJVAR);
        messageTo(control, "handleObservedFlourish", params, 0.0f, false);
        return SCRIPT_CONTINUE;
    }
}
