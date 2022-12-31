package script.developer.soe.e3demo;

import script.obj_id;

public class e3_faceto extends script.base_script
{

    public int OnStartNpcConversation(obj_id self, obj_id conversant) throws InterruptedException
    {
        faceTo(self, conversant);
        return SCRIPT_CONTINUE;
    }
}
