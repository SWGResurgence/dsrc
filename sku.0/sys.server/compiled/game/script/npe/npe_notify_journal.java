package script.npe;

import script.obj_id;

public class npe_notify_journal extends script.base_script
{

    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item))
        {
            attachScript(item, "npe.trigger_journal");
        }
        return SCRIPT_CONTINUE;
    }
}
