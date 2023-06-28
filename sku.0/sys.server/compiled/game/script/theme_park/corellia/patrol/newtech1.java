package script.theme_park.corellia.patrol;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class newtech1 extends script.base_script
{
    public newtech1()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id hideout = getObjIdObjVar(self, "hideout");
        messageTo(hideout, "newtech1Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
