package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.tmp_barker
@Author: BubbaJoeX
@Purpose: Expells players from a trigger volume named "no_enter"
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class expel extends script.base_script
{
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("no_enter", 64.0f, true);
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (volumeName.equals("no_enter"))
        {
            if (!isPlayer(breacher))
            {
                return SCRIPT_CONTINUE;
            }
            if (!isGod(breacher))
            {
                broadcast(breacher, "This area has been restricted to authorized personnel only. You are being expelled.");
                expelFromTriggerVolume(self, "no_enter", breacher);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self)
    {
        removeTriggerVolume("no_enter");
        return SCRIPT_CONTINUE;
    }

    public int OnDetach(obj_id self)
    {
        removeTriggerVolume("no_enter");
        return SCRIPT_CONTINUE;
    }
}
