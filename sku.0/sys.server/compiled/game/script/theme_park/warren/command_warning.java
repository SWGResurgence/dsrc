package script.theme_park.warren;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;
import script.string_id;

public class command_warning extends script.base_script
{
    public static final String SYSTEM_MESSAGES = "theme_park/warren/warren_system_messages";

    public command_warning()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!reactorCoreShutdown(self))
        {
            sendSystemMessage(item, new string_id(SYSTEM_MESSAGES, "core_warning"));
            permissionsMakePrivate(getCellId(getTopMostContainer(self), "plusroom84"));
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public boolean reactorCoreShutdown(obj_id self) throws InterruptedException
    {
        obj_id bldg = getTopMostContainer(self);
        if (utils.hasScriptVar(bldg, "warren.reactorOverriden"))
        {
            permissionsMakePublic(getCellId(getTopMostContainer(self), "plusroom84"));
            return true;
        }
        return false;
    }
}
