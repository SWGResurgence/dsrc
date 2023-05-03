package script.item;

import script.*;
import script.library.static_item;
import script.library.utils;

import java.util.HashSet;

public class resurgence_first_anniversary extends base_script
{

    public static final String STF_FILE = "gift_box";

    public static obj_id[] grantFirstAnniversaryGift(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_token_duty_space_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_token_duty_space_01_01", pInv, 500));
        theSet.add(static_item.createNewItemFunction("item_world_boss_token_01_01", pInv, 50));
        theSet.add(static_item.createNewItemFunction("item_resurgence_bunker_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_tcg_loot_reward_series6_cloud_car_itv", pInv));

        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_gift_box"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_gift_box"));
            obj_id[] allTheArmor = grantFirstAnniversaryGift(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}