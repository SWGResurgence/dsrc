package script.systems.combat;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;

public class invulnerable extends script.base_script
{
    public invulnerable()
    {
    }

    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (!isGod(self))
        {
            return SCRIPT_CONTINUE;
        }
        java.util.StringTokenizer st = new java.util.StringTokenizer(text);
        String arg = st.nextToken();
        obj_id target = getLookAtTarget(self);
        if (!isIdValid(target))
        {
            target = self;
        }
        if (st.hasMoreTokens())
        {
            String soid = st.nextToken();
            obj_id tmp = utils.stringToObjId(soid);
            if (isIdValid(tmp) && tmp.isLoaded())
            {
                target = tmp;
            }
            else
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (arg.equals("invulnerable"))
        {
            if (setInvulnerable(target, true))
            {
                broadcast(self, "Target(" + target + ") is INVULNERABLE.");
                return SCRIPT_OVERRIDE;
            }
        }
        else if (arg.equals("vulnerable"))
        {
            if (setInvulnerable(target, false))
            {
                broadcast(self, "Target(" + target + ") is vulnerable.");
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
