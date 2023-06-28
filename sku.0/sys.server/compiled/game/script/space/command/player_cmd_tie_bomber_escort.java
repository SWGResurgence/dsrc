package script.space.command;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ship_ai;
import script.library.space_combat;
import script.library.space_utils;
import script.obj_id;

public class player_cmd_tie_bomber_escort extends script.base_script
{
    public player_cmd_tie_bomber_escort()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "evacuate"))
        {
            debugServerConsoleMsg(null, "ONdESTROY - evacuating tie bomber ESCORT unit just destructed. obj_id was: " + self);
            return SCRIPT_CONTINUE;
        }
        debugServerConsoleMsg(null, "ONdESTROY - tie bomber escort");
        dictionary outparams = new dictionary();
        outparams.put("deadFighterId", self);
        if (hasObjVar(self, "commanderPlayer"))
        {
            space_utils.notifyObject(getObjIdObjVar(self, "commanderPlayer"), "bomberStrikeEscortUnitDestroyed", outparams);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnSpaceUnitMoveToComplete(obj_id self) throws InterruptedException
    {
        debugServerConsoleMsg(null, "ONsPACEuNITmOVEtOcOMPLETE - tie bomber escort script");
        int squadId = ship_ai.unitGetSquadId(self);
        obj_id[] squaddyList = null;
        if (ship_ai.isSquadIdValid(squadId))
        {
            squaddyList = ship_ai.squadGetUnitList(squadId);
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
        for (obj_id obj_id : squaddyList)
        {
            space_combat.destroyObjectHyperspace(obj_id);
        }
        return SCRIPT_CONTINUE;
    }
}
