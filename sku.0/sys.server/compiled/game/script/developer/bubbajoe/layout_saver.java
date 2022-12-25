package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.layout_saver
@Author: BubbaJoeX
@Purpose: Saves the layout of a building to the player's client for archival purposes.
*/

import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class layout_saver extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isGod(player))
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Export House Layout"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!isIdValid(player))
            {
                return SCRIPT_CONTINUE;
            }
            if (isInWorldCell(player))
            {
                broadcast(player, "You can't export a layout while outside.");
                return SCRIPT_CONTINUE;
            }
            obj_id building = getTopMostContainer(self);
            obj_id[] buildingContents = utils.getContents((player), true);
            obj_id[] cellContents = utils.getContents(getContainedBy(player), true);
            if (buildingContents == null || buildingContents.length == 0)
            {
                sendSystemMessage(player, new string_id("spam", "no_building_contents"));
                return SCRIPT_CONTINUE;
            }
            String fileHeader = "House Layout | " + player + " | " + getCalendarTime() + "\n";
            String fileName = "layout_saver_" + getFirstName(player) + ".txt";
            String fileContents = "";
            for (int i = 0; i < buildingContents.length; i++)
            {
                String objectName = getTemplateName(buildingContents[i]);
                String objectLocation = getLocation(buildingContents[i]).toString();
                fileContents += getName(buildingContents[i]) + " | Location: " + objectLocation + " | Template: " + objectName + "\n";
            }

            fileContents += "Room saved: " + getCellName(getContainedBy(player)) + "\n";
            for (int i = 0; i < cellContents.length; i++)
            {
                String objectName = getTemplateName(cellContents[i]);
                String objectLocation = getLocation(cellContents[i]).toString();
                fileContents += getName(cellContents[i]) + " | Location: " + objectLocation + " | Template: " + objectName + "\n";
            }
            broadcast(player, "Attempting to save layout to " + fileName);
            saveTextOnClient(player, fileName, fileHeader + fileContents);
        }
        return SCRIPT_CONTINUE;
    }
}
