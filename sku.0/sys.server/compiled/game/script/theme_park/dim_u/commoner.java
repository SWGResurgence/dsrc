package script.theme_park.dim_u;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.ai_lib;

/**
 * @author BubbaJoe
 */
public class commoner extends script.base_script
{
    public static String[] NAMES = {
            "a Dim-U follower",
            "a Dim-U caretaker",
            "a commoner"
    };

    public commoner()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0, 2)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0, 2)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
