package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Hot potato game.
*/

import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class pass_the_ball extends script.base_script
{
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Pass the Ball"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!isIdValid(player))
            {
                return SCRIPT_CONTINUE;
            }
            if (getState(player, STATE_RIDING_MOUNT) == 1)
            {
                broadcast(player, "You can't pass the ball while mounted.");
                return SCRIPT_CONTINUE;
            }
            obj_id myTarget = getIntendedTarget(player);
            if (myTarget == null || !isIdValid(myTarget))
            {
                sendSystemMessage(player, new string_id("spam", "need_intended_target"));
                return SCRIPT_CONTINUE;
            }
            doAnimationAction(player, "throw_object_left");
            broadcast(player, "You have passed the ball to " + toUpper(getName(myTarget), 0));
            putIn(self, utils.getInventoryContainer(myTarget));
            broadcast(myTarget, "You have been passed the ball from " + toUpper(getName(player) + "!", 0));
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
