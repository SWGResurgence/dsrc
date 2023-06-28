package script.developer.soe.test;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class cannot_die_healing_test extends script.base_script
{
    public cannot_die_healing_test()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!isGod(self) || getGodLevel(self) < 10 || !isPlayer(self))
        {
            detachScript(self, "test.buy_box");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage) throws InterruptedException
    {
        setAttrib(self, HEALTH, 100);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectDamaged(obj_id self, obj_id attacker, obj_id weapon, int damage) throws InterruptedException
    {
        setHitpoints(self, 100);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        return SCRIPT_OVERRIDE;
    }
}
