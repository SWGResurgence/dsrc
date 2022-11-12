package script.developer;

import script.library.chat;
import script.library.utils;
import script.obj_id;

public class wear extends script.base_script
{
    public wear()
    {
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        if (isGod(giver))
        {
            if (isIdValid(item))
            {
                if (isPlayer(self))
                {
                    sendSystemMessageTestingOnly(giver, "You gave " + getName(self) + " " + getName(item) + ".");
                    if (equipOverride(item, self))
                    {
                        sendSystemMessageTestingOnly(giver, "You equipped " + getName(item) + " on " + getName(self) + ".");
                    }
                    else
                    {
                        sendSystemMessageTestingOnly(giver, "You failed to equip " + getName(item) + " on " + getName(self) + ".");
                    }
                }
                else
                {
                    sendSystemMessageTestingOnly(giver, "You cannot give an item to a non-creature object.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
