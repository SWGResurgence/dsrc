package script.systems.respec;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.respec;
import script.obj_id;

public class grant_single_respec_on_login extends script.base_script
{
    public grant_single_respec_on_login()
    {
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        detachScript(self, respec.SCRIPT_GRANT_SINGLE_ON_LOGIN);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, respec.SCRIPT_GRANT_SINGLE_ON_LOGIN);
        return SCRIPT_CONTINUE;
    }
}
