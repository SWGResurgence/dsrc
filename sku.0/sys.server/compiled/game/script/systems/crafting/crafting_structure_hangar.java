package script.systems.crafting;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import script.base_script;

import script.library.craftinglib;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

public class crafting_structure_hangar extends script.base_script
{
    public static final String OBJVAR_CRAFTING_TYPE = "crafting.type";
    public static final String INVISIBLE_CRAFTING_STATION_TEMPLATE = "object/tangible/crafting/station/inivisible_crafting_station.iff";
    public static final String PLAYER_HOUSE_HANGAR = "player_house_hangar";
    public int OnInitialize(obj_id self) {
        String buildingTemplate = getTemplateName(self);
        if (buildingTemplate == null || buildingTemplate.equals("")) {
            return SCRIPT_CONTINUE;
        }
        if (buildingTemplate.indexOf(PLAYER_HOUSE_HANGAR) > 0) {
            spawnInvCraftingStation(self, craftinglib.STATION_TYPE_HANGAR);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean spawnInvCraftingStation(obj_id building, int craftingBuff)
    {
        return spawnInvCraftingStation(building, craftingBuff, null);
    }
    public boolean spawnInvCraftingStation(obj_id building, int craftingBuff, String specificCell)
    {
        if (!isValidId(building) || !exists(building))
        {
            return false;
        }
        if (craftingBuff < 0)
        {
            return false;
        }
        String[] allCells = getCellNames(building);
        if (allCells == null)
        {
            return false;
        }
        for (int i = 0; i < allCells.length; i++)
        {
            if (specificCell != null && !specificCell.equals(allCells[i]))
            {
                continue;
            }
            if (specificCell != null && specificCell.equals(allCells[i]))
            {
                obj_id singleCellStation = createObjectInCell(INVISIBLE_CRAFTING_STATION_TEMPLATE, building, allCells[i]);
                if (!isValidId(singleCellStation) || !exists(singleCellStation))
                {
                    return false;
                }
                setObjVar(singleCellStation, OBJVAR_CRAFTING_TYPE, craftingBuff);
                break;
            }
            obj_id allCellsStation = createObjectInCell(INVISIBLE_CRAFTING_STATION_TEMPLATE, building, allCells[i]);
            if (!isValidId(allCellsStation) || !exists(allCellsStation))
            {
                return false;
            }
            setObjVar(allCellsStation, OBJVAR_CRAFTING_TYPE, craftingBuff);
        }
        return true;
    }
}