package script.player.skill;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class player_healing extends script.base_script
{
    public player_healing()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, "player.skill.player_healing");
        return SCRIPT_CONTINUE;
    }
}
