package script.developer.soe.e3demo;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class e3_faceto extends script.base_script
{
    public e3_faceto()
    {
    }

    public int OnStartNpcConversation(obj_id self, obj_id conversant) throws InterruptedException
    {
        faceTo(self, conversant);
        return SCRIPT_CONTINUE;
    }
}
