package script.space.qa;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.space_battlefield;
import script.library.space_crafting;
import script.library.space_create;
import script.obj_id;
import script.transform;
import script.vector;

public class tester extends script.base_script
{
    public tester()
    {
    }

    public int OnSpeaking(obj_id self, String strText) throws InterruptedException
    {
        String[] strCommands = split(strText, ' ');
        if (strCommands[0].equals("flipPhase"))
        {
            obj_id objManager = space_battlefield.getManagerObject();
            int intPhase = getIntObjVar(objManager, "intPhase");
            if (intPhase == 0)
            {
                space_battlefield.battlefieldCompleted(objManager, 1);
                broadcast(self, "setting to 1");
            }
            else if (intPhase == 1)
            {
                space_battlefield.battlefieldCompleted(objManager, 0);
                broadcast(self, "setting to 0");
            }
        }
        if (strCommands[0].equals("createAt"))
        {
            if (strCommands.length > 0)
            {
                obj_id objTest = getPilotedShip(self);
                obj_id objShip = null;
                if (isIdValid(objTest))
                {
                    objShip = space_create.createShip(strCommands[1], getTransform_o2p(getPilotedShip(self)));
                }
                else
                {
                    objShip = space_create.createShip(strCommands[1], getTransform_o2p(self));
                }
                if (objShip == null)
                {
                    broadcast(self, "You passed in a bad shipType. Type is " + strCommands[1]);
                    return SCRIPT_CONTINUE;
                }
                else
                {
                    broadcast(self, "Made ship of type " + strCommands[1] + " object id is: " + objShip);
                    debugConsoleMsg(self, "Made ship of type " + strCommands[1] + " object id is: " + objShip);
                }
            }
            else
            {
                broadcast(self, "You need to pass in a ship type for me to spawn.");
                return SCRIPT_CONTINUE;
            }
        }
        if (strCommands[0].equals("createNear"))
        {
            if (strCommands.length > 0)
            {
                transform whereAt = getTransform_o2p(getPilotedShip(self));
                transform nearby = whereAt.move_l((vector.unitY.multiply(100.0f)).add(vector.unitX.multiply(100.0f)));
                obj_id objShip = space_create.createShip(strCommands[1], nearby);
                if (objShip == null)
                {
                    broadcast(self, "You passed in a bad shipType. Type is " + strCommands[1]);
                    return SCRIPT_CONTINUE;
                }
                else
                {
                    broadcast(self, "Made ship of type " + strCommands[1] + " object id is: " + objShip);
                    debugConsoleMsg(self, "Made ship of type " + strCommands[1] + " object id is: " + objShip);
                }
            }
            else
            {
                broadcast(self, "You need to pass in a ship type for me to spawn.");
                return SCRIPT_CONTINUE;
            }
        }
        if (strCommands[0].equals("fixMe"))
        {
            space_crafting.repairDamage(self, getPilotedShip(self), 100.0f);
            broadcast(self, "Fixed ship");
        }
        if (strCommands[0].equals("checkPhase"))
        {
            obj_id objManager = space_battlefield.getManagerObject();
            broadcast(self, "Manager is " + objManager + " phase is " + getIntObjVar(objManager, "intPhase"));
        }
        if (strCommands[0].equals("resetBattlefield"))
        {
            obj_id objManager = space_battlefield.getManagerObject();
            broadcast(self, "Manager is " + objManager + " RESETTING");
            space_battlefield.resetSpaceBattlefield(objManager);
        }
        if (strCommands[0].equals("nextPhase"))
        {
            obj_id objManager = space_battlefield.getManagerObject();
            int intPhase = getIntObjVar(objManager, "intPhase");
            broadcast(self, "Manager is " + objManager);
            int intDuration = space_battlefield.PHASE_DURATIONS[intPhase];
            setObjVar(objManager, "intPhaseStart", 100);
            space_battlefield.sendNextPhaseNotification(objManager);
        }
        return SCRIPT_CONTINUE;
    }
}
