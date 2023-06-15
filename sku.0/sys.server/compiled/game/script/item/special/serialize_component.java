package script.item.special;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class serialize_component extends script.base_script
{
    public serialize_component()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        boolean success = true;
        success &= setCraftedId(self, self);
        success &= setCrafter(self, self);
        if (success)
        {
            detachScript(self, "item.special.serialize_component");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        boolean success = true;
        success &= setCraftedId(self, self);
        success &= setCrafter(self, self);
        if (success)
        {
            detachScript(self, "item.special.serialize_component");
        }
        return SCRIPT_CONTINUE;
    }
}
