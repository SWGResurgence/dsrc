package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.teleporter_building
@Author: BubbaJoeX
@Purpose: This script is used to give the user a list of cells with thier cell name and warp them to it if they select it.
*/

import script.*;
import script.library.sui;

public class teleporter_building extends script.base_script
{
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
        int daddy = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Travel"));
        if (isGod(player))
        {
            if (!isInWorldCell(player))
            {
                mi.addSubMenu(daddy, menu_info_types.SERVER_MENU1, new string_id("Mark Cell"));
            }
            mi.addSubMenu(daddy, menu_info_types.SERVER_MENU2, new string_id("Toggle Visibility"));
            mi.addSubMenu(daddy, menu_info_types.SERVER_MENU3, new string_id("Set Name"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            String prompt = "\\#c49c48Are you sure you want to travel?";
            String title = "\\#c49c48Confirm Travel";
            sui.msgbox(self, player, prompt, sui.YES_NO, title, "handleConfirm");
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            location here = getLocation(player);
            debugConsoleMsg(player, "Mark Location: " + here);
            broadcast(player, "Location saved.");
            setObjVar(self, "marked_location", here);
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            if (hasObjVar(self, "teleporter.visible"))
            {
                removeObjVar(self, "teleporter.visible");
                broadcast(player, "Teleporter is now invisible.");
                hideFromClient(self, false);
            }
            else
            {
                setObjVar(self, "teleporter.visible", true);
                broadcast(player, "Teleporter is now visible.");
                hideFromClient(self, true);
            }
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            String prompt = "Enter the name you want to give this teleporter.";
            String title = "Set Name";
            sui.inputbox(self, player, prompt,"handleSetName");
        }
        return SCRIPT_CONTINUE;
    }
    public int handleConfirm(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            broadcast(player, "You have canceled your travel.");
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_REVERT)
        {
            broadcast(player, "You have canceled your travel.");
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_OK)
        {
            location whereTo = getLocationObjVar(self, "marked_location");
            warpPlayer(player, whereTo, null, false);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int handleSetName(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            broadcast(player, "You have canceled your name change.");
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_REVERT)
        {
            broadcast(player, "You have canceled your name change.");
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_OK)
        {
            String name = sui.getInputBoxText(params);
            setName(self, name);
            broadcast(player, "You have changed the name of this teleporter to " + name);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
