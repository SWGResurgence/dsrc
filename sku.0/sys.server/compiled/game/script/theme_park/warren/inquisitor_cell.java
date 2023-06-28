package script.theme_park.warren;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;

public class inquisitor_cell extends script.base_script
{
    public inquisitor_cell()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (isGod(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!cellUnlocked(self))
        {
            permissionsMakePrivate(getCellId(getTopMostContainer(self), "smallroom47"));
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }

    public boolean cellUnlocked(obj_id self) throws InterruptedException
    {
        obj_id bldg = getTopMostContainer(self);
        if (!isIdValid(bldg))
        {
            return false;
        }
        return utils.hasScriptVar(bldg, "warren.cellOpened");
    }
}
