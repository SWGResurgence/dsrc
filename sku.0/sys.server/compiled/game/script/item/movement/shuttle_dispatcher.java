package script.item.movement;/*
@Filename: script.item.movement.shuttle_dispatcher
@Author: BubbaJoeX
@Purpose: This item will allow players to place a droid that will be able to call a shuttle to the location saved from using the item.
*/

import script.*;
import script.library.sui;

public class shuttle_dispatcher extends script.base_script
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
        if (!hasObjVar(self, "shuttleLocation"))
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Mark Location"));
        }
        else
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Call Shuttle"));
        }
        if (!hasObjVar(self, "template"))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, unlocalized("Set Template"));
        }
        else
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, unlocalized("Clear Template"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!hasObjVar(self, "shuttleLocation"))
            {
                markWaypoint(self, player);
                return SCRIPT_CONTINUE;
            }
            else
            {
                callShuttle(self, player);
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (!hasObjVar(self, "template"))
            {
                String prompt = "Current template is: " + getStringObjVar(self, "template") + "\nPlease enter the template you wish to use.";
                int pid = sui.inputbox(player, prompt, "handleTemplateInput");
            }
            else
            {
                removeObjVar(self, "template");
                broadcast(self, "Template cleared.");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void callShuttle(obj_id self, obj_id player)
    {
        location here = getLocationObjVar(self, "shuttleLocation");
        broadcast(self, "You have called a shuttle to this location: " + here.toReadableFormat(true) + ". Please stand-by for pick-up.");
        dictionary params = new dictionary();
        params.put("player", player);
        messageTo(self, "doWarp", params, 10, true);
    }

    public void markWaypoint(obj_id self, obj_id player)
    {
        location here = getLocation(player);
        setObjVar(self, "shuttleLocation", here);
        broadcast(self, "You have marked this location: " + here.toReadableFormat(true) + " for future pick-ups.");
    }

    public int handleTemplateInput(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        String template = sui.getInputBoxText(params);
        setObjVar(self, "template", template);
        broadcast(self, "Template set to: " + template);
        return SCRIPT_CONTINUE;
    }

    public int doWarp(obj_id self, dictionary params)
    {
        obj_id player = params.getObjId("player");
        location here = getLocationObjVar(self, "shuttleLocation");
        warpPlayer(player, here.area, here.x, here.y, here.z, null, 0, 0, 0, "", !isGod(player));
        return SCRIPT_CONTINUE;
    }
}
