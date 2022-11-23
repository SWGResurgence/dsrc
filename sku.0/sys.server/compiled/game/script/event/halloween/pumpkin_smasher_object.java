package script.event.halloween;

import script.library.buff;
import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class pumpkin_smasher_object extends script.base_script
{
    public pumpkin_smasher_object()
    {
    }
    private static final String HALLOWEEN = "event/halloween";
    public static final string_id SID_USE = new string_id(HALLOWEEN, "smash_pumpkin");
    public static final String PULPED_ITER_OBJVAR = "halloween.pulped";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            int baseUse = 0;
            if (!hasObjVar(player, PULPED_ITER_OBJVAR))
            {
                setObjVar(player, PULPED_ITER_OBJVAR, baseUse);
            }
            if (!buff.hasBuff(player, "event_halloween_pumpkin_pulper"))
            {
                buff.applyBuff(player, "event_halloween_pumpkin_pulper");
            }
            int randChance = rand(1,100);
            if (randChance <= 90 ) {
                broadcast(player, "You find a strange object inside the pumpkin!");
                static_item.createNewItemFunction("event_halloween_token", utils.getInventoryContainer(player), 2);
            }
            int currentSmashed = getIntObjVar(player, "halloween.pulped");
            setObjVar(player, PULPED_ITER_OBJVAR, currentSmashed++);
            playClientEffectObj(player, "clienteffect/egg_hatch_01.cef", player, "root");
            broadcast(player, "You've smashed a pumpkin.");
            modifyCollectionSlotValue(player, "halloween_pumpkin_pulper", 1);
			if (hasCompletedCollectionSlot(player, "halloween_pumpkin_pulper")) {
                modifyCollectionSlotValue(player, "halloween_pumpkin_pulper_finish", 1);
            }
            hideFromClient(self, true);
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
