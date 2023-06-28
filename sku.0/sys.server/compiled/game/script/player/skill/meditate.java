package script.player.skill;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class meditate extends script.base_script
{
    public meditate()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        detachScript(self, "player.skill.meditate");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, "player.skill.meditate");
        return SCRIPT_CONTINUE;
    }
}
