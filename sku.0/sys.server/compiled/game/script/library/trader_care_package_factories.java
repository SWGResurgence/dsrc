package script.library;

import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.*;

import java.util.HashSet;

public class trader_care_package_factories extends script.base_script
{

    public static final String STF_FILE = "npe";

    public static obj_id[] grantTraderCarePackageFactories(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("item_clothing_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_clothing_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_food_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_food_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_item_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_item_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_structure_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_structure_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_structure_factory_deed_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_structure_factory_deed_01_01", pInv));

        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "redeem_care_package"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "redeemed_care_package"));
            obj_id[] allTheArmor = grantTraderCarePackageFactories(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}