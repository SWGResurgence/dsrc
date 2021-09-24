package script.systems.raf;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.static_item;
import script.library.sui;

public class ReferAFriend extends script.base_script {
    public int setReferrer(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException {
        if (hasObjVar(self, "raf.referred")) {
            sendSystemMessageTestingOnly(self, "You have already set another player as your referrer.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(target, "raf.referred") && getObjIdObjVar(target, "raf.referred") == self) {
            sendSystemMessageTestingOnly(self, "You cannot refer this friend because he is referred to you.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(target, "raf.last_referred") && getIntObjVar(target, "raf.last_referred") >= getCurrentBirthDate()) {
            sendSystemMessageTestingOnly(self, "You cannot refer this friend because he has referred another friend within the last 24 hours.");
            return SCRIPT_CONTINUE;
        }
        if (getPlayerStationId(self) == getPlayerStationId(target)) {
            sendSystemMessageTestingOnly(self, "You cannot refer yourself.");
            return SCRIPT_CONTINUE;
        }
        obj_id[] items = new obj_id[2];
        items[0] = static_item.createNewItemFunction("item_reward_buddy_xp_chip_06_01", self);
        items[1] = static_item.createNewItemFunction("item_auto_level_50_buddy_conversion", self);
        showLootBox(self, items);
        setObjVar(self, "raf.referral", target);
        sui.msgbox(self, self, "Congratulations! You have recieved a Cybernetic Experience Chip and a Holocron of Knowledge as rewards for the Refer a Friend program! Player " + getPlayerName(target) + " is now your referrer.", "Refer a Friend Rewards");

        obj_id[] items = new obj_id[1];
        items[0] = static_item.createNewItemFunction("col_buddy_" + (rand(0, 1) == 0 ? "02_" : "") + "token", target);
        showLootBox(target, items);
        setObjVar(self, "raf.last_referred", getCurrentBirthDate());
        sui.msgbox(target, target, "Congratulations! You have recieved a Buddy Token for referring player " + getPlayerName(self) + " to the game!", "Refer a Friend Rewards");
        return SCRIPT_CONTINUE;
    }
}
