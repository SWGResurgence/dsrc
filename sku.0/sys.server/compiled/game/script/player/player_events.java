package script.player;/*
@Filename: script.player.player_roleplay
@Author: BubbaJoeX
@Purpose: Makes a trigger volume that keeps players away from the god player
*/

import script.obj_id;

public class player_events extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        createTriggerVolume("player_events", 5f, true);
        return SCRIPT_CONTINUE;
    }
}
