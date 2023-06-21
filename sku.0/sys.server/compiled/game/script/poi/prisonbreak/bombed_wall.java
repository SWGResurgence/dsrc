package script.poi.prisonbreak;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class bombed_wall extends script.base_script
{
    public static final String LOG_NAME = "poiPrisonBreak Bombed Wall";

    public bombed_wall()
    {
    }

    public int enableBombedWall(obj_id self, dictionary params) throws InterruptedException
    {
        setObjVar(self, "enabled", true);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectDamaged(obj_id self, obj_id attacker, obj_id weapon, int damage) throws InterruptedException
    {
        if (!hasObjVar(self, "enabled"))
        {
            return SCRIPT_CONTINUE;
        }
        boolean wallDamaged = getBooleanObjVar(self, "wallDamaged");
        if (!wallDamaged)
        {
            setObjVar(self, "wallDamaged", true);
            obj_id poiMaster = getObjIdObjVar(self, "poi.baseObject");
            if ((poiMaster == null) || (poiMaster == obj_id.NULL_ID))
            {
                return SCRIPT_CONTINUE;
            }
            dictionary params = new dictionary();
            params.put("attacker", attacker);
            messageTo(poiMaster, "wallDamaged", params, 0, false);
        }
        return SCRIPT_CONTINUE;
    }
}
