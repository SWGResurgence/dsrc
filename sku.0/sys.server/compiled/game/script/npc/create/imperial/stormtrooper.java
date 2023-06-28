package script.npc.create.imperial;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.weapons;
import script.obj_id;

public class stormtrooper extends script.base_script
{
    public static final String BASE_PATH = "npc.create";
    public static final String SCRIPT_ME = BASE_PATH + ".imperial.stormtrooper";
    public stormtrooper()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        weapons.createWeapon("object/weapon/ranged/carbine/carbine_e11.iff", self, 1.0f);
        setName(self, "TK-" + rand(1, 1000));
        detachScript(self, SCRIPT_ME);
        return SCRIPT_CONTINUE;
    }
}
