package script.theme_park.tatooine.jabbaspawner;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class random3 extends script.base_script
{
    public random3()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "random3Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }

    public int doFacing(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id faceTarget = getObjIdObjVar(self, "facer");
        faceTo(self, faceTarget);
        return SCRIPT_CONTINUE;
    }
}
