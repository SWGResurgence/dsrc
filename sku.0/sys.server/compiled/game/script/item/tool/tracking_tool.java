package script.item.tool;

import script.*;

public class tracking_tool extends script.base_script {
    public tracking_tool() {
    }
    public static int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        if (isGod(player)) {
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("sui", "track_use"));
        }
        return SCRIPT_CONTINUE;
    }
    public static int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
                obj_id[] players = getCreaturesInRange(player, 64.0f);
                if (players == null || players.length == 0) {
                    sendSystemMessage(player, "No creatures in range.", null);
                    return SCRIPT_CONTINUE;
                }
                for (obj_id player1 : players) {
                    if (player1 != player) {
                        sendSystemMessage(player, "Creature: " + getName(player1) + " is at: " + getLocation(player1), null);
                    }
                }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        if ((names == null) || (attribs == null) || (names.length != attribs.length))
        {
            return SCRIPT_CONTINUE;
        }
        if (player != null)
        {
            final int firstFreeIndex = getFirstFreeIndex(names);
            if ((firstFreeIndex >= 0) && (firstFreeIndex < names.length))
            {
                names[firstFreeIndex] = "category";
                attribs[firstFreeIndex] = "\\#ed8d16" + "Old Republic Technology";
            }
        }
        return SCRIPT_CONTINUE;
    }
}
