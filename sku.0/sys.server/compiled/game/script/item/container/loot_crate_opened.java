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
        if (hasScript(item, "item.special.nomove"))
        {
            detachScript(item, "item.special.nomove");
        }
        if (hasObjVar(item, "noTrade"))
        {
            removeObjVar(item, "noTrade");
        }
        debugServerConsoleMsg(self, "\n[SKYNET] Player " + transferer + " is attempting to move " + getName(item) + "(" + item +  ") to " + destContainer + "\n");
        broadcast(transferer, "This item will have it's No Trade tag reapplied 24 hours after it is moved.");
        return SCRIPT_CONTINUE;
    }
}
