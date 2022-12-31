package script.theme_park.kashyyyk.rryatt_trail;

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class loot_controller_katarn extends script.base_script {

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null) {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self)) {
            return SCRIPT_CONTINUE;
        }
        createMyLoot(self);
        return SCRIPT_CONTINUE;
    }
    public void createMyLoot(obj_id self) throws InterruptedException {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null) {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null) {
            return;
        }
        int x = rand(1, 100);
        if (x < 26) { // 25% Drop Chance for Kashyyyk Embroidered Sash {
            static_item.createNewItemFunction("item_tcg_loot_reward_series7_embroidered_sash", corpseInventory);
            if (x < 11) { // 10% Drop Chance for Kashyyyk Beast Muzzle {
                static_item.createNewItemFunction("item_tcg_loot_reward_series6_beast_muzzle", corpseInventory);
            }
        }  
        String myLoot1 = "object/tangible/loot/misc/kashyyyk_token.iff";
        createObject(myLoot1, corpseInventory, "");
        return;
    }
}
