package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose:
*/

import script.library.space_dungeon;
import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

public class avatar_mover extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "[Debug] Avatar Mover");
        createTriggerVolume("avatar_mover", 64, false);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String name, obj_id who) throws InterruptedException
    {
        if (!isPlayer(who))
        {
            return SCRIPT_CONTINUE;
        }
        location here = getLocation(self);
        location avatarLoc = new location(here.x, 41.f, here.y, getCurrentSceneName());
        if (isInWorldCell(who) && here.y < 41)
        {
            space_dungeon.sendGroupToDungeonWithoutTicketCollector(who, "avatar_platform","quest_type");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeExited(obj_id self, String name, obj_id who) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

}
