package script.item;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class trader_care_package extends script.base_script
{

    public static final String STF_FILE = "npe";

    public static obj_id[] grantTraderCarePackage(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("item_trader_care_package_generators_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_care_package_harvesters_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_care_package_Factories_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_care_package_survey_devices_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_care_package_tools_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_trader_care_package_stations_01_01", pInv));

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
            obj_id[] allTheArmor = grantTraderCarePackage(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}