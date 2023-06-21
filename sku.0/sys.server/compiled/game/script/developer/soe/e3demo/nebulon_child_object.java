package script.developer.soe.e3demo;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.space_utils;
import script.obj_id;

public class nebulon_child_object extends script.base_script
{
    public nebulon_child_object()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "intReset"))
        {
            obj_id objNebulon = getObjIdObjVar(self, "objNebulon");
            dictionary dctParams = new dictionary();
            dctParams.put("strType", getStringObjVar(self, "strType"));
            LOG("space", "Destroying " + getStringObjVar(self, "strType") + "and notifying " + objNebulon);
            dctParams.put("intAttacker", getIntObjVar(self, "intAttacker"));
            dctParams.put("trSpawnLocation", getTransformObjVar(self, "trSpawnLocation"));
            space_utils.notifyObject(objNebulon, "shipDestroyed", dctParams);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
