package script.theme_park.dungeon.myyydril;

import script.dictionary;

import script.library.*;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;

import script.obj_id;

public class grievous_death extends script.base_script {
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        obj_id[] players = trial.getPlayersInDungeon(self);
        if (corpseInventory == null) {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self)) {
            return SCRIPT_CONTINUE;
        }
        createMyLoot(self);
        
        // HEROIC SYSTEM BEGIN \\
        
        dictionary dict = new dictionary();
        dict.put("tokenIndex", 38);
        dict.put("tokenCount", 5);
        utils.messageTo(players, "handleAwardtoken", dict, 0, false);
        obj_id group = getGroupObject(players[0]);
        int calendarTime = getCalendarTime();
        String realTime = getCalendarTimeStringLocal(calendarTime);
        CustomerServiceLog("instance-necrosis", "N-K Necrosis Defeated in instance (" + self + ") by group_id (" + group + ") at " + realTime);
        CustomerServiceLog("instance-necrosis", "Group (" + group + ") consists of: ");
        for (int i = 0; i < players.length; ++i) {
            String strProfession = skill.getProfessionName(getSkillTemplate(players[i]));
            CustomerServiceLog("instance-necrosis", "Group (" + group + ") member " + i + " " + getFirstName(players[i]) + "'s(" + players[i] + ") profession is " + strProfession + ".");
        }
        
        // HEROIC SYSTEM END \\
        
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
        if(x < 31){  // 30% Chance to Drop General Grevious Painting
           static_item.createNewItemFunction("item_general_grevious_painting_01_01", corpseInventory);
      if(x < 15){  // 14% chance at dropping bonus loot (at least a Bane's Heart crystal) 
          static_item.createNewItemFunction("item_color_crystal_02_16", corpseInventory);
			if(x < 3){ // 2% chance to drop Grievous Gutsack
          static_item.createNewItemFunction("item_tcg_loot_reward_series3_general_grievous_gutsack", corpseInventory);
			}
        }
        String myLoot1 = "object/tangible/ship/crafted/chassis/grievous_starfighter_reward_deed.iff";
        String myLoot2 = "object/tangible/wearables/cybernetic/s02/cybernetic_s02_arm_r.iff";
        createObject(myLoot1, corpseInventory, "");
        createObject(myLoot2, corpseInventory, "");
        return;
    }
}
