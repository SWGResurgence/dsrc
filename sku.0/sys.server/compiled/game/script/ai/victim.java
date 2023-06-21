package script.ai;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.attrib;
import script.obj_id;

public class victim extends script.base_script
{
    public victim()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setAttributeAttained(self, attrib.VICTIM);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setAttributeAttained(self, attrib.VICTIM);
        return SCRIPT_CONTINUE;
    }
}
