package script.item.special;

import script.dictionary;
import script.library.utils;
import script.obj_id;

public class officer_drop_item extends script.base_script
{
    public static final float LIFESPAN = 18000.0f;

    public int OnAttach(obj_id self) throws InterruptedException
    {
        float rightNow = getGameTime();
        setObjVar(self, "item.temporary.time_stamp", rightNow);
        float dieTime = getDieTime(self);
        if (dieTime < 1)
        {
            dieTime = 1.0f;
        }
        messageTo(self, "cleanUp", null, dieTime, false);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        float dieTime = getDieTime(self);
        if (dieTime < 1)
        {
            dieTime = 1.0f;
        }
        messageTo(self, "cleanUp", null, dieTime, false);
        return SCRIPT_CONTINUE;
    }

    public float getDieTime(obj_id tempObject) throws InterruptedException
    {
        float timeStamp = getFloatObjVar(tempObject, "item.temporary.time_stamp");
        float deathStamp = timeStamp + LIFESPAN;
        float rightNow = getGameTime();
        float dieTime = deathStamp - rightNow;
        return dieTime;
    }

    public int cleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        if (self.isBeingDestroyed())
        {
            return SCRIPT_CONTINUE;
        }
        float dieTime = getDieTime(self);
        if (dieTime < 1)
        {
            destroyObject(self);
        }
        else
        {
            messageTo(self, "cleanUp", null, dieTime, false);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        int timeLeft = (int) getDieTime(self);
        names[idx] = "storyteller_time_remaining";
        attribs[idx++] = utils.formatTimeVerbose(timeLeft);
        return SCRIPT_CONTINUE;
    }
}