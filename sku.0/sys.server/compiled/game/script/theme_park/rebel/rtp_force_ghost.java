package script.theme_park.rebel;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class rtp_force_ghost extends script.base_script
{
    public rtp_force_ghost()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setState(self, STATE_GLOWING_JEDI, true);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setState(self, STATE_GLOWING_JEDI, true);
        return SCRIPT_CONTINUE;
    }
}
