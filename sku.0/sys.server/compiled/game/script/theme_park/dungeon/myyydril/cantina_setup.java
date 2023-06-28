package script.theme_park.dungeon.myyydril;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class cantina_setup extends script.base_script
{
    public cantina_setup()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "healing.canhealshock", 1);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setObjVar(self, "healing.canhealshock", 1);
        return SCRIPT_CONTINUE;
    }
}
