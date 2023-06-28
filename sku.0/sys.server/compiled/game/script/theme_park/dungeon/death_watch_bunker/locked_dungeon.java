package script.theme_park.dungeon.death_watch_bunker;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class locked_dungeon extends script.base_script
{
    public static final String MSGS = "dungeon/geonosian_madbio";

    public locked_dungeon()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
