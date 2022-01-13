package script.library;

import script.*;
import java.util.HashSet;

public class trader_care_package extends script.base_script {
	
	public static final String STF_FILE = "npe";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "redeem_care_package"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "redeemed_care_package"));
            obj_id[] allTheArmor = grantTraderCarePackage(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantTraderCarePackage(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();

    theSet.add(static_item.createNewItemFunction("item_power_generator_wind_deed_01_01", pInv, 5));
    theSet.add(static_item.createNewItemFunction("item_power_generator_solar_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_power_generator_photo_bio_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_power_generator_geothermal_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_power_generator_fusion_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_mineral_harvester_personal_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_mineral_harvester_personal_deed_01_02", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_mineral_harvester_heavy_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_moisture_harvester_personal_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_moisture_harvester_medium_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_moisture_harvester_heavy_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_liquid_harvester_personal_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_liquid_harvester_medium_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_liquid_harvester_heavy_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_gas_harvester_personal_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_gas_harvester_medium_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_gas_harvester_heavy_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_flora_harvester_personal_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_flora_harvester_medium_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_flora_harvester_heavy_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_creature_harvester_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_clothing_factory_deed_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_food_factory_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_item_factory_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_structure_factory_deed_01_01", pInv, 4));
    theSet.add(static_item.createNewItemFunction("item_organic_survey_tool_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_inorganic_survey_tool_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_solar_survey_tool_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_wind_survey_tool_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_clothing_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_food_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_space_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_structures_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_weapon_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_incubator_station_01_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_clothing_tool_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_food_tool_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_space_tool_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_structure_tool_01_01", pInv, 4));
    theSet.add(static_item.createNewItemFunction("item_weapon_tool_01_01", pInv, 2));

		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}