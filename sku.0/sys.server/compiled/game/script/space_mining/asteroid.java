package script.space_mining;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.space_content;
import script.obj_id;

public class asteroid extends script.base_script
{
    public asteroid()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "objParent"))
        {
            space_content.notifySpawner(self);
        }
        return SCRIPT_CONTINUE;
    }
}
