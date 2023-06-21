package script.planet_map;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.planetary_map;
import script.obj_id;

public class map_loc_creature extends script.planet_map.map_loc_base
{
    public map_loc_creature()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        planetary_map.addCreatureLocation(self);
        return SCRIPT_CONTINUE;
    }
}
