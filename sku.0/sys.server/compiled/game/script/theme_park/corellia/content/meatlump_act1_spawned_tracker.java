package script.theme_park.corellia.content;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class meatlump_act1_spawned_tracker extends script.base_script
{
    public meatlump_act1_spawned_tracker()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id objParent = getObjIdObjVar(self, "objParent");
        dictionary webster = new dictionary();
        webster.put("deadNpc", self);
        webster.put("deadType", getCreatureName(self));
        setObjVar(self, "alreadyHandledIncap", true);
        messageTo(objParent, "meatlumpDead", webster, 1, false);
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "alreadyHandledIncap"))
        {
            obj_id objParent = getObjIdObjVar(self, "objParent");
            dictionary webster = new dictionary();
            webster.put("deadNpc", self);
            webster.put("deadType", getCreatureName(self));
            messageTo(objParent, "meatlumpDead", webster, 1, false);
        }
        return SCRIPT_CONTINUE;
    }
}
