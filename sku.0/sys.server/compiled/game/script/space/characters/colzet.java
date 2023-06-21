package script.space.characters;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class colzet extends script.base_script
{
    public colzet()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, "Lt. Colzet");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, "Lt. Colzet");
        return SCRIPT_CONTINUE;
    }
}
