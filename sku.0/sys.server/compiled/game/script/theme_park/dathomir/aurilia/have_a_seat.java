package script.theme_park.dathomir.aurilia;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class have_a_seat extends script.base_script
{
    public have_a_seat()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setAnimationMood(self, "npc_sitting_chair");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setAnimationMood(self, "npc_sitting_chair");
        return SCRIPT_CONTINUE;
    }
}
