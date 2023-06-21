package script.theme_park.gating.imperial;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.string_id;

public class vader_block extends script.base_script
{
    public vader_block()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if ((getIntObjVar(item, "theme_park_imperial") > 32) || (getIntObjVar(item, "space_access_imperial") > 32))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            string_id warning = new string_id("theme_park_imperial/warning", "emperor");
            sendSystemMessage(item, warning);
            return SCRIPT_OVERRIDE;
        }
    }
}
