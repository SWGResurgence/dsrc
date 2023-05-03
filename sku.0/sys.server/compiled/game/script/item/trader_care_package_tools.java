package script.item;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class trader_care_package_tools extends script.base_script
{

    public static final String STF_FILE = "npe";

    public static void grantTraderCarePackageTools(obj_id player) throws InterruptedException
    {
        String TOOLS[] = {
                "object/draft_schematic/item/item_clothing_tool.iff",
                "object/draft_schematic/item/item_food_tool.iff",
                "object/draft_schematic/item/item_structure_tool.iff",
                "object/draft_schematic/item/item_weapon_tool.iff",
                "object/draft_schematic/item/item_space_tool.iff"
        };
        obj_id pInv = utils.getInventoryContainer(player);
        for (String s : TOOLS)
        {
            obj_id tool = makeCraftedItem(s, 31.2f, pInv);
            if (isIdValid(tool))
            {
                setCrafter(tool, player);
            }
        }
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "redeem_care_package"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            grantTraderCarePackageTools(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
