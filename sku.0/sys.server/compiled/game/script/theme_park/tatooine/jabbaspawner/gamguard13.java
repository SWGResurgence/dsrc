package script.theme_park.tatooine.jabbaspawner;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class gamguard13 extends script.base_script
{
    public gamguard13()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "gamGuard13Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
