package script.systems.crafting;

import script.library.craftinglib;
import script.obj_id;

public class crafting_shipwright extends script.base_script {
    public static final String OBJVAR_CRAFTING_TYPE = "crafting.type";
    public static final String INVISIBLE_CRAFTING_STATION_TEMPLATE = "object/tangible/crafting/station/inivisible_crafting_station.iff";
    public static final String HANGER_PATH = "player_house_hanger";
    public int OnInitialize(obj_id self) throws InterruptedException {
        String buildingTemplate = getTemplateName(self);
        if (buildingTemplate == null || buildingTemplate.equals("")) {
            return SCRIPT_CONTINUE;
        }
        if (buildingTemplate.indexOf(HANGER_PATH) > 0) {
            spawnInvCraftingStation(self, craftinglib.STATION_TYPE_SPACE);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean spawnInvCraftingStation(obj_id building, int craftingBuff) throws InterruptedException {
        return spawnInvCraftingStation(building, craftingBuff, null);
    }
    public boolean spawnInvCraftingStation(obj_id building, int craftingBuff, String specificCell) throws InterruptedException {
        if (!isValidId(building) || !exists(building)) {
            return false;
        }
        if (craftingBuff < 0) {
            return false;
        }
        String[] allCells = getCellNames(building);
        if (allCells == null) {
            return false;
        }
        for (String allCell : allCells) {
            if (specificCell != null && !specificCell.equals(allCell)) {
                continue;
            }
            if (specificCell != null && specificCell.equals(allCell)) {
                obj_id singleCellStation = createObjectInCell(INVISIBLE_CRAFTING_STATION_TEMPLATE, building, allCell);
                if (!isValidId(singleCellStation) || !exists(singleCellStation)) {
                    return false;
                }
                setObjVar(singleCellStation, OBJVAR_CRAFTING_TYPE, craftingBuff);
                break;
            }
            obj_id allCellsStation = createObjectInCell(INVISIBLE_CRAFTING_STATION_TEMPLATE, building, allCell);
            if (!isValidId(allCellsStation) || !exists(allCellsStation)) {
                return false;
            }
            setObjVar(allCellsStation, OBJVAR_CRAFTING_TYPE, craftingBuff);
        }
        return true;
    }
}
