package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class nexu_death extends script.base_script
{
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        createMyLoot(self);
        return SCRIPT_CONTINUE;
    }
    public void createMyLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null)
        {
            return;
        }
        int x = rand(1, 100);
        if (x < 101) { // 100% Drop Rate: Geonosian Solar Sail ITV
            static_item.createNewItemFunction("item_tcg_loot_reward_series4_home_itv_02_01", corpseInventory);
            if (x < 26) {  // 25% Drop Chance: Auto Feeder
            static_item.createNewItemFunction("item_tcg_loot_reward_series6_auto_feeder", corpseInventory);
            }
        }
        /*String myLoot1 = "object/tangible/tcg/series4/instant_travel_terminal_home.iff";
        String myLoot2 = "object/tangible/wearables/cybernetic/s02/cybernetic_s02_arm_r.iff";
        createObject(myLoot1, corpseInventory, "");
        createObject(myLoot2, corpseInventory, "");*/
        return;
    }
}
