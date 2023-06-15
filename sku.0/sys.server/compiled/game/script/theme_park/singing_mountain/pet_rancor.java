package script.theme_park.singing_mountain;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class pet_rancor extends script.base_script
{
    public pet_rancor()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setScale(self, 0.35f);
        return SCRIPT_CONTINUE;
    }
}
