package script.structure.gating;/*
@Filename: script.structure.gating.
@Author: BubbaJoeX
@Purpose: Restricts entry if player does not have the required command. [/meditate for example]
*/

import script.obj_id;

public class gating_command extends script.base_script
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
            String gatingCommand = getStringObjVar(self, "gating.command");
            if (gatingCommand != null && gatingCommand.length() > 0)
            {
                if (!hasCommand(item, gatingCommand))
                {
                    broadcast(transferer, "You must have the " + gatingCommand + " command to enter this structure.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
