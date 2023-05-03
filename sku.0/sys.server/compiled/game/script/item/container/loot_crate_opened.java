package script.item.container;

import script.library.chat;
import script.library.pet_lib;
import script.obj_id;

public class loot_crate_opened extends script.base_script
{

    public loot_crate_opened()
    {
    }
    public String TRIGGER_NEARBY = "lootCrateTriggerNearby";
    public String TRIGGER_NEARBY_VISUAL = "clienteffect/level_granted.cef";
    public String TRIGGER_NEARBY_SOUND= "sound/item_ding.snd";
    public float TRIGGER_RADIUS = 5.2f;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume(TRIGGER_NEARBY, TRIGGER_RADIUS, true);
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
            broadcast(transferer, "Note: This item will have it's No-Trade tag reapplied upon galaxy initialization.");
            removeObjVar(item, "noTrade");
        }
        play2dNonLoopingSound(transferer, TRIGGER_NEARBY_SOUND);
        return SCRIPT_CONTINUE;
    }
    public int OnLostItem(obj_id self, obj_id objDestinationContainer, obj_id objTransferer, obj_id objItem) throws InterruptedException
    {
        if (getVolumeFree(self) == getTotalVolume(self))
        {
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String strVolumeName, obj_id objPlayer) throws InterruptedException
    {
        if (strVolumeName.equals(TRIGGER_NEARBY))
        {
            if (isPlayer(objPlayer))
            {
                playClientEffectLoc(objPlayer, TRIGGER_NEARBY_VISUAL, getLocation(self), 0.1f);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
