package script.systems.crafting.repair;

import script.obj_id;

public class clothing_repair extends script.base_script
{

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setObjVar(self, "crafting.type", GOT_clothing);
        return SCRIPT_CONTINUE;
    }
}
