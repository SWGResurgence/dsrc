package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Wim Magwit's Luminous Lamp
*/

import script.*;
import script.library.sui;

public class magic_light extends script.base_script
{
    public static final String SCRIPT_MAGIC_LIGHT = "item.magic_light";
    public static final String DATATABLE_MAGIC_LIGHT_PREFIX = "datatables/furniture/";
    public static final String DATATABLE_MAGIC_LIGHT_SUFFIX = ".iff";
    public static final String DATATABLE_MAIN_COLOR_COL = "root_color";
    public static final String DATATABLE_SUB_COLOR_COL = "color";
    public static final String DATATABLE_SUB_COLOR_DETAIL = "description";
    public static final String OBJVAR_CLAIMED_BY = "claimedBy";
    public static String[] RANGES_MAGIC_LIGHT = {
            "2m",
            "4m",
            "8m",
            "16m",
    };
    public static String[] MCOLOR_MAGIC_LIGHT = {
            "blue",
            "cyan",
            "gray",
            "green",
            "orange",
            "pink",
            "purple",
            "red",
            "white",
            "yellow",
    };

    public int OnAttach(obj_id self)
    {
        setName(self, "Wim Magwit's Luminous Lamp");
        setDescriptionStringId(self, unlocalized("This lamp can be configured to emit a variety of colors at 4 static ranges."));
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, OBJVAR_CLAIMED_BY))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU10, unlocalized("Claim"));
        }
        else
        {
            if (player == getObjIdObjVar(self, OBJVAR_CLAIMED_BY))
            {
                mi.addRootMenu(menu_info_types.SERVER_MENU11, unlocalized("Modify Color"));
                mi.addRootMenu(menu_info_types.SERVER_MENU12, unlocalized("Unclaim"));
            }
            else
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            if (!hasObjVar(self, OBJVAR_CLAIMED_BY))
            {
                setObjVar(self, OBJVAR_CLAIMED_BY, player);
                broadcast(player, "You have claimed this lightsource, only you can modify it now.");
            }
        }
        if (item == menu_info_types.SERVER_MENU11)
        {
            if (player == getObjIdObjVar(self, OBJVAR_CLAIMED_BY))
            {
                sui.listbox(self, player, "Select the base color for this lightsource.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", MCOLOR_MAGIC_LIGHT, "handleMainColor", true, false);
            }
        }
        if (item == menu_info_types.SERVER_MENU12)
        {
            if (player == getObjIdObjVar(self, OBJVAR_CLAIMED_BY))
            {
                removeObjVar(self, OBJVAR_CLAIMED_BY);
                broadcast(player, "You have unclaimed this lightsource.");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void handleMainColor(obj_id self, dictionary params) throws InterruptedException
    {
        int idx = sui.getListboxSelectedRow(params);
        if (sui.getIntButtonPressed(params) == sui.BP_CANCEL)
        {
            return;
        }
        String mainColor = MCOLOR_MAGIC_LIGHT[idx];
        setObjVar(self, DATATABLE_MAIN_COLOR_COL, mainColor);
        String subcolorTable = DATATABLE_MAGIC_LIGHT_PREFIX + MCOLOR_MAGIC_LIGHT[idx] + DATATABLE_MAGIC_LIGHT_SUFFIX;
        String[] subcolorList = dataTableGetStringColumn(subcolorTable, "description");
        sui.listbox(self, sui.getPlayerId(params), "Select the sub color for this lightsource.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", subcolorList, "handleSubColor", true, false);
    }

    public void handleSubColor(obj_id self, dictionary params) throws InterruptedException
    {
        if (sui.getIntButtonPressed(params) == sui.BP_CANCEL)
        {
            return;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx < 0)
        {
            idx = 0;
        }
        String subColor = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT_PREFIX + getStringObjVar(self, DATATABLE_MAIN_COLOR_COL) + DATATABLE_MAGIC_LIGHT_SUFFIX, DATATABLE_SUB_COLOR_COL)[idx];
        String subColorString = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT_PREFIX + getStringObjVar(self, DATATABLE_MAIN_COLOR_COL) + DATATABLE_MAGIC_LIGHT_SUFFIX, "description")[idx];
        setObjVar(self, DATATABLE_SUB_COLOR_DETAIL, subColorString);
        setObjVar(self, DATATABLE_SUB_COLOR_COL, subColor);
        sui.listbox(self, sui.getPlayerId(params), "Select the range for this light.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", RANGES_MAGIC_LIGHT, "handleColorRange", true, false);
    }

    public void handleColorRange(obj_id self, dictionary params) throws InterruptedException
    {
        if (sui.getIntButtonPressed(params) == sui.BP_CANCEL)
        {
            return;
        }
        int idx = sui.getListboxSelectedRow(params);
        String rangeSelection = RANGES_MAGIC_LIGHT[idx];
        switchTemplate(self, getStringObjVar(self, DATATABLE_MAIN_COLOR_COL), getStringObjVar(self, DATATABLE_SUB_COLOR_COL), rangeSelection);
    }

    public void switchTemplate(obj_id self, String color, String subcolor, String rangeSelection)
    {
        location loc = getLocation(self);
        float yaw = getYaw(self);
        float[] rotation = getQuaternion(self);
        String template  = "object/tangible/tarkin_custom/decorative/lights/" + color + "/" + subcolor + "_" + rangeSelection + ".iff";
        obj_id newLight = createObject(template, loc);
        attachScript(newLight, SCRIPT_MAGIC_LIGHT);
        setYaw(newLight, yaw);
        setQuaternion(newLight, rotation[0], rotation[1], rotation[2], rotation[3]);
        destroyObject(self);
    }
}
