package script.theme_park.gating;/*
@Filename: script.theme_park.gating.lifeday_resettlement
@Author: BubbaJoeX
@Purpose:
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.obj_id;

public class lifeday_resettlement extends script.base_script
{
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        String cellName = "entry";
        obj_id cell = getCellId(self, cellName);
        if (cell == destinationCell)
        {
            if (!groundquests.hasCompletedQuest(item, "lifeday22_resettlement_prologue"))
            {
                broadcast(item, "You must assist Krronch before you can enter his home.");
                return SCRIPT_OVERRIDE;
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
