package script.systems.raf;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.static_item;
import script.library.sui;
import script.obj_id;

public class refer_a_friend extends script.base_script
{
    public int setReferrer(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        if (!isPlayer(target))
        {
            return SCRIPT_CONTINUE;
        }
        int stationIdSelf = getPlayerStationId(self);
        int stationIdTarget = getPlayerStationId(target);
        if (stationIdSelf == stationIdTarget)
        {
            broadcast(self, "You cannot refer yourself.");
            return SCRIPT_CONTINUE;
        }
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "raf.referred_" + stationIdSelf))
        {
            broadcast(self, "You have already set another player as your referrer.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(tatooine, "raf.referred_" + stationIdTarget) && getIntObjVar(tatooine, "raf.referred_" + stationIdTarget) == stationIdSelf)
        {
            broadcast(self, "You cannot refer this friend because he is referred to you.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(tatooine, "raf.last_referred_" + stationIdTarget) && getIntObjVar(tatooine, "raf.last_referred_" + stationIdTarget) >= getCurrentBirthDate())
        {
            // TODO: Check the account instead of the character
            broadcast(self, "You cannot refer this friend because he has referred another friend within the last 24 hours.");
            return SCRIPT_CONTINUE;
        }
        obj_id[] friend_items = new obj_id[2];
        friend_items[0] = static_item.createNewItemFunction("item_reward_buddy_xp_chip_06_01", self);
        friend_items[1] = static_item.createNewItemFunction("item_auto_level_50_buddy_conversion", self);
        showLootBox(self, friend_items);
        setObjVar(tatooine, "raf.referred_" + stationIdSelf, target);
        sui.msgbox(self, self, "Congratulations! You have recieved a Cybernetic Experience Chip and a Holocron of Knowledge as rewards for the Refer a Friend program! Player " + getPlayerName(target) + " is now your referrer.", "Refer a Friend Rewards");

        obj_id[] referrer_items = new obj_id[1];
        referrer_items[0] = static_item.createNewItemFunction("col_buddy_" + (rand(0, 1) == 0 ? "02_" : "") + "token", target);
        showLootBox(target, referrer_items);
        setObjVar(tatooine, "raf.last_referred_" + stationIdTarget, getCurrentBirthDate());
        sui.msgbox(target, target, "Congratulations! You have recieved a Buddy Token for referring player " + getPlayerName(self) + " to the game!", "Refer a Friend Rewards");
        return SCRIPT_CONTINUE;
    }
}
