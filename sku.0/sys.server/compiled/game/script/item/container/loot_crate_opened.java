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
        //@NOTE: This is a hack to transfer the item to the players inventory without breaking the no trade tag. If the item is STATIC, the tag will get reapplied on next load.
        return SCRIPT_CONTINUE;
    }
}
