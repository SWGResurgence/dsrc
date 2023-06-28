package script.developer.soe.beta;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class food extends script.base_script
{
    public static final String SCRIPT_BETA_FOOD = "beta.food";

    public food()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
