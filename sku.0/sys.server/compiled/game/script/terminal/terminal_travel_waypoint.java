package script.terminal;

import script.*;

public class terminal_travel_waypoint extends script.base_script
{
    public terminal_travel_waypoint()
    {
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        if (getTemplateName(item).equals("object/waypoint/waypoint.iff"))
        {
            location where = getWaypointLocation(item);
            setObjVar(self, "wptr.goto", where);
            setName(self, getWaypointName(item));
            setObjVar(self, "wptr.setup", true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (hasObjVar(self, "wptr.setup"))
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("travel", "travel_waypoint"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (hasObjVar(self, "wptr.goto"))
            {
                location where = getLocationObjVar(self, "wptr.goto");
                if (where != null)
                {
                    warpPlayer(player, where.area, where.x, 0, where.z, null, 0, 0, 0);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
