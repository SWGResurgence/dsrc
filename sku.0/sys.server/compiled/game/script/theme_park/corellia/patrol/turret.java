package script.theme_park.corellia.patrol;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class turret extends script.base_script
{
    public turret()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id hideout = getObjIdObjVar(self, "hideout");
        messageTo(hideout, "turretDied", null, 30, false);
        return SCRIPT_CONTINUE;
    }
}
