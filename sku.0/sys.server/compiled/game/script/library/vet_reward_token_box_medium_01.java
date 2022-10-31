package script.library;

import script.*;

import java.util.HashSet;

public class vet_reward_token_box_medium_01 extends script.base_script
{
    public static final String STF_FILE = "veteran_new";

    public static obj_id[] grantVetTokens(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 75));
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_vet_reward_tokens"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_box"));
            obj_id[] allTheArmor = grantVetTokens(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
