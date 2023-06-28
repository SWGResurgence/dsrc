package script.poi.rabidbeast;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.poi;
import script.library.scenario;
import script.obj_id;

public class antagonist extends script.poi.base.scenario_actor
{
    public static final String LOG_NAME = "poiRabidBeast Antagonist";

    public antagonist()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id poiMaster = poi.getBaseObject(self);
        if ((poiMaster == null) || (poiMaster == obj_id.NULL_ID))
        {
            return SCRIPT_CONTINUE;
        }
        messageTo(poiMaster, scenario.HANDLER_ACTOR_DEATH, null, 0, false);
        return SCRIPT_CONTINUE;
    }
}
