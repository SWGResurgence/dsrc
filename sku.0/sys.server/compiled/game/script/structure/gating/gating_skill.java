package script.structure.gating;/*
@Filename: script.structure.gating.
@Author: BubbaJoeX
@Purpose: Restricts entry if player does not have skill specified via an objvar.
*/

import script.obj_id;

public class gating_skill extends script.base_script
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
            String gatingSkill = getStringObjVar(self, "gating.skill");
            if (gatingSkill != null && gatingSkill.length() > 0)
            {
                if (!hasSkill(item, gatingSkill))
                {
                    broadcast(transferer, "You do not have the required skill to enter this structure.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
