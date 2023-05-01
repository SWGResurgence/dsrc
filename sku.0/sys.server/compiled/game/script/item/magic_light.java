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
    public static final String DATATABLE_MAGIC_LIGHT = "datatables/furniture/lights.iff";
    public static final String DATATABLE_MAIN_COLOR_COL = "root_color";
    public static final String DATATABLE_SUB_COLOR_COL = "sub_color";
    public static final String OBJVAR_CLAIMED_BY = "claimedBy";
    public static String[] RANGES_MAGIC_LIGHT = {
            "2m",
            "4m",
            "8m",
            "16m",
    };
    public int OnAttach(obj_id self)
    {
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
                String[] mainColors = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT, DATATABLE_MAIN_COLOR_COL);
                String[] subColors = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT, DATATABLE_SUB_COLOR_COL);
                sui.listbox(self, player, "Select the base color for this lightsource.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", mainColors, "handleMainColor", true, false);
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

    public int handleMainColor(obj_id self, dictionary params) throws InterruptedException
    {
        int idx = sui.getListboxSelectedRow(params);
        String[] mainColors = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT, DATATABLE_MAIN_COLOR_COL);
        String mainColor = mainColors[idx];
        setObjVar(self, "mainColor", mainColor);
        String[] subColors = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT, DATATABLE_SUB_COLOR_COL);
        sui.listbox(self, sui.getPlayerId(params), "Select the sub color you wish to transform for this lightsource.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", subColors, "handleSubAndFinalizeColor", true, false);
        return SCRIPT_CONTINUE;
    }

    public int handleSubColor(obj_id self, dictionary params) throws InterruptedException
    {
        int idx = sui.getListboxSelectedRow(params);
        String[] subColors = dataTableGetStringColumn(DATATABLE_MAGIC_LIGHT, DATATABLE_SUB_COLOR_COL);
        String subColor = subColors[idx];
        setObjVar(self, "subColor", subColor);
        sui.listbox(self, sui.getPlayerId(params), "Select the range you'd like for this lightsource.", sui.OK_CANCEL, "Wim Magwit's Luminous Lamp", RANGES_MAGIC_LIGHT, "handleRangeAndFinalize", true, false);
        return SCRIPT_CONTINUE;
    }

    public int handleRangeAndFinalize(obj_id self, dictionary params) throws InterruptedException
    {
        int idx = sui.getListboxSelectedRow(params);
        String range = RANGES_MAGIC_LIGHT[idx];
        String mainColor = getStringObjVar(self, "mainColor");
        String subColor = getStringObjVar(self, "subColor");
        setObjVar(self, "range", range);
        switchTemplate(self, mainColor, subColor, range);
        return SCRIPT_CONTINUE;
    }

    public void switchTemplate(obj_id self, String color, String subcolor, String rangeSelection)
    {
        location loc = getLocation(self);
        float yaw = getYaw(self);
        float[] rotation = getQuaternion(self);
        String template  = "object/tangible/tarkin_custom/decorative/lights/" + color + "_" + subcolor + "_" + rangeSelection + ".iff";
        obj_id newLight = createObject(template, loc);
        attachScript(newLight, SCRIPT_MAGIC_LIGHT);
        setYaw(newLight, yaw);
        setQuaternion(newLight, rotation[0], rotation[1], rotation[2], rotation[3]);
        destroyObject(self);
    }
}
