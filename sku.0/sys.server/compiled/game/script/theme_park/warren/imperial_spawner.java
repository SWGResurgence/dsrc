package script.theme_park.warren;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.create;
import script.obj_id;

public class imperial_spawner extends script.base_script
{
    public imperial_spawner()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        obj_id imperialOfficer = create.createNpc("imperial_army_captain", "object/mobile/dressed_imperial_captain_m.iff", getLocation(self));
        setInvulnerable(imperialOfficer, true);
        attachScript(imperialOfficer, "theme_park.warren.imperial_captain");
        setObjVar(imperialOfficer, "spawnEgg", self);
        return SCRIPT_CONTINUE;
    }
}
