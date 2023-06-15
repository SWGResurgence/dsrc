package script.systems.combat;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class combat_actions_creature extends script.base_script
{
    public combat_actions_creature()
    {
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        if (!hasScript(self, "systems.combat.combat_actions"))
        {
            attachScript(self, "systems.combat.combat_actions");
        }
        detachScript(self, "systems.combat.combat_actions_creature");
        return SCRIPT_CONTINUE;
    }
}
