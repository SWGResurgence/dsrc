package script.theme_park;

import script.dictionary;

import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;

import script.obj_id;

public class loot_controller_template extends script.base_script {
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
        if(x < 100){  // xx% Drop Rate: [LOOT ITEM OR OBJECT NAME]
            //static_item.createNewItemFunction("[MASTER_ITEM OBJECT STRING]", corpseInventory);
        }
        /*String myLoot1 = "object/tangible/ship/crafted/chassis/grievous_starfighter_reward_deed.iff";
        String myLoot2 = "object/tangible/wearables/cybernetic/s02/cybernetic_s02_arm_r.iff";
        createObject(myLoot1, corpseInventory, "");
        createObject(myLoot2, corpseInventory, "");*/
        return;
    }
}
