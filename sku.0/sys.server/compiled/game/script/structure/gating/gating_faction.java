package script.structure.gating;/*
@Filename: script.structure.gating.
@Author: BubbaJoeX
@Purpose: Restricts entry if player is not of the correct faction. [Rebel, Imperial, Neutral]
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

import static script.library.factions.isImperial;
import static script.library.factions.isRebel;

public class gating_faction extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item))
        {
            String gatingFaction = getStringObjVar(self, "gating.faction");
            if (gatingFaction != null && gatingFaction.length() > 0)
            {
                switch (gatingFaction)
                {
                    case "rebel":
                    {
                        if (!isRebel(item))
                        {
                            broadcast(transferer, "You must be a Rebel to enter this structure.");
                            return SCRIPT_OVERRIDE;
                        }
                    }
                    case "imperial":
                    {
                        if (!isImperial(item))
                        {
                            broadcast(transferer, "You must be an Imperial to enter this structure.");
                            return SCRIPT_OVERRIDE;
                        }
                    }
                    case "neutral":
                    {
                        if (isRebel(item) || isImperial(item))
                        {
                            broadcast(transferer, "You must be Neutral to enter this structure.");
                            return SCRIPT_OVERRIDE;
                        }
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
