package script.theme_park.dungeon.avatar_platform;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class cell_door_setup_01 extends script.base_script
{
    public cell_door_setup_01()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id structure = getTopMostContainer(self);
        setObjVar(structure, "avatar_platform.cell_door_01", self);
        return SCRIPT_CONTINUE;
    }
}
