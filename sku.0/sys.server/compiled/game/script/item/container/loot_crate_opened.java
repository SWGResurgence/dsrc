package script.item.container;

import script.obj_id;

public class loot_crate_opened extends script.base_script
{
    public loot_crate_opened()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        debugServerConsoleMsg(self, "[SKYNET] Player " + transferer + " is attempting to move " + getName(item) + "(" + item +  ") to " + destContainer);
        return SCRIPT_CONTINUE;
    }
}
