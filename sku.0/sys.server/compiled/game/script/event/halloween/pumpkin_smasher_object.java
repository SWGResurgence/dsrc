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
    public static final string_id SID_USE = new string_id(HALLOWEEN, "learn_song");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (!buff.hasBuff(player, "event_halloween_pumpkin_pulper"))
            {
                buff.applyBuff(player, "event_halloween_pumpkin_pulper");
            }
            int randChance = rand(1,100);
            if (randChance <= 90 ) {
                broadcast(player, "You find a strange object inside the pumpkin!");
                static_item.createNewItemFunction("event_halloween_token", utils.getInventoryContainer(player), 2);
            }
            playClientEffectObj(player, "clienteffect/egg_break.cef", player, "root");
            broadcast(player, "You've smashed a pumpkin.");
            modifyCollectionSlotValue(player, "halloween_pumpkin_pulper", 1);
        }
        return SCRIPT_CONTINUE;
    }
}
