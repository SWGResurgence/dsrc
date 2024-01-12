package script.theme_park.dim_u;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;

/**
 * @author BubbaJoe
 */
public class temple_access extends script.base_script
{
    public temple_access()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        int gating = getIntObjVar(item, "dim_u_access");
        if (gating != 1)
        {
            broadcast(item, "You are not a follower of the Dim-U");
            return SCRIPT_OVERRIDE;
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
    }
}
