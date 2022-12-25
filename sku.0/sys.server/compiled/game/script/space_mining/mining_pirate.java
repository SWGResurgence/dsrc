package script.space_mining;

import script.obj_id;

public class mining_pirate extends script.base_script
{

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id asteroid = getObjIdObjVar(self, "space_mining.parentRoid");
        messageTo(asteroid, "handlePirateKilled", null, 0, false);
        return SCRIPT_CONTINUE;
    }
}
