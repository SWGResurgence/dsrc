package script.event;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.menu_info;
import script.obj_id;

public class no_rent extends script.base_script
{
    public no_rent()
    {
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        checkTimeLimit(self, player);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        checkTimeLimit(self, player);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        checkTimeLimit(self, player);
        return SCRIPT_CONTINUE;
    }

    public void checkTimeLimit(obj_id self, obj_id player) throws InterruptedException
    {
        float myCreationTime = getFloatObjVar(self, "event.creationTime");
        float rightNow = getGameTime();
        float delta = rightNow - myCreationTime;
        String myName = getName(self);
        if (delta > 60 * 60 * 4)
        {
            destroyObject(self);
            sendSystemMessage(player, "THE FOLLOWING ITEM HAS EXPIRED: " + myName, null);
        }
    }
}
