package script.terminal;

import script.ai.ai;
import script.library.callable;
import script.library.chat;
import script.library.utils;
import script.*;

public class terminal_travel_waypoint extends script.base_script
{
    public static String TICKET = "object/tangible/loot/tool/datapad_broken.iff";

    public terminal_travel_waypoint()
    {
    }

    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        if (isMob(self))
        {
            if (getTemplateName(item).equals(TICKET))
            {
                setInvulnerable(self, true);
                String creatureName = "Shuttle Dispatcher: \\#21C1C2" + getName(item) + "\\#FFFFFF";
                chat.chat(self, "Coordinates for " + getName(item) + " received. Dispatching you a shuttle, " + getPlayerFullName(player) + ".");
                location whereAmIGoing = getLocationObjVar(item, "wptr.goto");
                setObjVar(self, "travel.waypoint", whereAmIGoing);
                setName(self, creatureName);
                destroyObject(item);
                if (hasObjVar(self, "wptr_mobile"))
                {
                    ai.follow(self, player, 5.0f, 6.0f);
                }
                destroyAllInContainerByObjVar(self, player, "wptr_tmp");
            }
            else
            {
                chat.chat(self, "I'm sorry, " + getPlayerFullName(player) + ", but I can't accept that.");
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isMob(self))
        {
            callable.storeCallables(player);
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("travel", "travel"));
            if (isGod(player))
            {
                mi.addRootMenu(menu_info_types.ITEM_USE_OTHER, new string_id("travel", "travel_god"));
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (hasObjVar(self, "travel.waypoint"))
            {
                location whereAmIGoing = getLocationObjVar(self, "travel.waypoint");
                warpPlayer(player, whereAmIGoing.area, whereAmIGoing.x, whereAmIGoing.y, whereAmIGoing.z, whereAmIGoing.cell, 0, 0, 0);
                return SCRIPT_CONTINUE;
            }
        }
        if (item == menu_info_types.ITEM_USE_OTHER)
        {
            makeTickets(self, player);
        }
        return SCRIPT_CONTINUE;
    }

    public int makeTickets(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id[] waypoints = getWaypointsInDatapad(player);
        if (waypoints == null || waypoints.length == 0)
        {
            sendSystemMessage(player, new string_id("travel", "no_waypoints"));
            return SCRIPT_CONTINUE;
        }
        for (obj_id waypoint : waypoints)
        {
            String name = getWaypointName(waypoint);
            location travelDest = getWaypointLocation(waypoint);
            obj_id ticket = createObject(TICKET, utils.getInventoryContainer(player), "");
            setObjVar(ticket, "wptr.goto", travelDest);
            setObjVar(ticket, "wptr_tmp", true);
            setName(ticket, name);
        }
        return SCRIPT_CONTINUE;
    }

    public int destroyAllInContainerByObjVar(obj_id self, obj_id player, String objvarName) throws InterruptedException
    {
        obj_id[] contents = getContents(utils.getInventoryContainer(player));
        if (contents == null || contents.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        for (obj_id content : contents)
        {
            if (hasObjVar(content, objvarName))
            {
                destroyObject(content);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
