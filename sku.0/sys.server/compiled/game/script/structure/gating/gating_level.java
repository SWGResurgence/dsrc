package script.structure.gating;/*
@Filename: script.structure.gating.
@Author: BubbaJoeX
@Purpose: Restricts entry if player is not of the correct level.
*/

import script.obj_id;

public class gating_level extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item)
    {
        if (isPlayer(item))
        {
            int gatingLevel = getIntObjVar(self, "gating.level");
            if (gatingLevel > 0)
            {
                if (getLevel(item) <= gatingLevel)
                {
                    broadcast(transferer, "You must be at least level " + gatingLevel + " to enter this structure.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
