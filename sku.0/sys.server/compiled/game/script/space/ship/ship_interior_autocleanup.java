package script.space.ship;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class ship_interior_autocleanup extends script.base_script
{
    public ship_interior_autocleanup()
    {
    }

    public int OnRemovingFromWorld(obj_id self) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
