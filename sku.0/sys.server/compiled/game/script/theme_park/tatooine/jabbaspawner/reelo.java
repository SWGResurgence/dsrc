package script.theme_park.tatooine.jabbaspawner;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class reelo extends script.base_script
{
    public reelo()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "reeloDied", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
