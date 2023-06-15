package script.theme_park.dungeon.avatar_platform;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class trando02_setup extends script.base_script
{
    public trando02_setup()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id structure = getTopMostContainer(self);
        setObjVar(structure, "avatar_platform.trando2", self);
        return SCRIPT_CONTINUE;
    }
}
