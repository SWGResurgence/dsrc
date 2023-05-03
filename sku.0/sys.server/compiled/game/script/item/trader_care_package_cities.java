package script.item;

import script.*;
import script.library.static_item;
import script.library.sui;
import script.library.utils;

import java.util.HashSet;

public class trader_care_package_cities extends script.base_script
{
    public static String[] PLANETS = {
            "Corellia",
            "Naboo",
            "Tatooine"
    };
    public static void grantTraderCarePackageCities(obj_id player, String planet) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet<>();
        createObject("object/tangible/deed/city_deed/cityhall_" + planet + "_deed.iff", pInv, "");
        createObject("object/tangible/deed/city_deed/bank_" + planet + "_deed.iff", pInv, "");
        createObject("object/tangible/deed/player_house_deed/" + planet + "_house_small_deed.iff", pInv, "");
        createObject("object/tangible/deed/player_house_deed/" + planet + "_house_small_deed.iff", pInv, "");
        createObject("object/tangible/deed/player_house_deed/" + planet + "_house_small_deed.iff", pInv, "");
        createObject("object/tangible/deed/player_house_deed/" + planet + "_house_small_deed.iff", pInv, "");
        createObject("object/tangible/deed/player_house_deed/" + planet + "_house_small_deed.iff", pInv, "");
        obj_id[] items= new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Use Care Package"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sui.listbox(self, player, "Select a which planet you'd like for a City Hall Deed, and four Small House Deeds", sui.OK_CANCEL, "Trader Care Package: Cities", PLANETS, "handleCarePackageCities", true, false);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCarePackageCities(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        int idx = sui.getListboxSelectedRow(params);
        int btnPressed = sui.getIntButtonPressed(params);
        if (idx < 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (btnPressed == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (getVolumeFree(player) < 7)
        {
            broadcast(player, "You do not have enough inventory space to claim this care package.");
            return SCRIPT_CONTINUE;
        }
        String planet = PLANETS[idx];
        grantTraderCarePackageCities(player, planet.toLowerCase());
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
