package script.structure;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class make_private extends script.base_script
{
    public make_private()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        permissionsMakePrivate(self);
        return SCRIPT_CONTINUE;
    }
}
