package script.structure.gating;/*
@Filename: script.structure.gating.
@Author: BubbaJoeX
@Purpose: Restricts entry if the player's ai faction is less than an amount.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.factions;
import script.obj_id;

public class gating_faction_ai extends script.base_script
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
            Float gatingFactionStanding = getFloatObjVar(self, "gating.faction_standing");
            if (factions.getFactionStanding(transferer, gatingFaction) < gatingFactionStanding)
            {
                broadcast(transferer, "You do not have the required faction standing to enter this structure.");
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
