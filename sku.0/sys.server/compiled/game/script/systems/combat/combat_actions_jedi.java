package script.systems.combat;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class combat_actions_jedi extends script.base_script
{
    public combat_actions_jedi()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, "systems.combat.combat_actions_jedi");
        return SCRIPT_CONTINUE;
    }
}
