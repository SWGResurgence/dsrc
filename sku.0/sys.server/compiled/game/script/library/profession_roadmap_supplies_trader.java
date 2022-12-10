package script.library;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class profession_roadmap_supplies_trader extends script.base_script
{
    public static final String STF_FILE = "npe";

    public static obj_id[] grantTraderRoadmapSupplies(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_trader_backpack_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_roadmap_jumpsuit_02_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_roadmap_boots_02_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_roadmap_gloves_02_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_ring_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_pendant_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_roadmap_belt_trader_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_knife_trader_roadmap_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_pistol_trader_roadmap_01_02", pInv));
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_contents"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantTraderRoadmapSupplies(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
