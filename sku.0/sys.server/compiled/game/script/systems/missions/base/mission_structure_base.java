package script.systems.missions.base;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class mission_structure_base extends script.systems.missions.base.mission_dynamic_base
{
    public mission_structure_base()
    {
    }

    public void addListener(obj_id objListener, obj_id objTarget) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        dctParams.put("objListener", objListener);
        messageTo(objTarget, "addListener", dctParams, 0, true);
    }

    public void removeListener(obj_id objListener, obj_id objTarget) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        dctParams.put("objListener", objListener);
        messageTo(objTarget, "removeListener", dctParams, 0, true);
    }

    public void playerDestructionIncomplete(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS INCOMPLETE!");
        messageTo(objMission, "destructionIncomplete", dctParams, 0, true);
    }

    public void playerDestructionFail(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS FAILED!");
        messageTo(objMission, "destructionFail", dctParams, 0, true);
    }

    public void playerDestructionSuccess(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS SUCCESFUL!");
        messageTo(objMission, "destructionSuccess", dctParams, 0, true);
    }

    public void returnAllCredits(obj_id objMission) throws InterruptedException
    {
    }

    public void addBountyHunterTrackingInformation(obj_id objPlayer, obj_id objBountyHunter, obj_id objMission) throws InterruptedException
    {
        debugServerConsoleMsg(objPlayer, "objPlayer is " + objPlayer.toString());
        debugServerConsoleMsg(objPlayer, "inAddBountyHunterTrackingInfo");
        if (hasObjVar(objPlayer, "bountyInfo." + objBountyHunter.toString()))
        {
            debugServerConsoleMsg(objPlayer, "Has BountyInfo ObjVar");
            int intMissionCount = getIntObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".intMissionCount");
            intMissionCount = intMissionCount + 1;
            debugServerConsoleMsg(objPlayer, "intMissionCount is " + intMissionCount);
            setObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".intMissionCount", intMissionCount);
            setObjVar(objPlayer, "bountyInfo." + objBountyHunter + "." + intMissionCount, objMission);
            debugServerConsoleMsg(objPlayer, "setObjVar's");
        }
        else
        {
            debugServerConsoleMsg(objPlayer, "No info ObjVar");
            int intBountyHunters = getIntObjVar(objPlayer, "bountyInfo.intBountyHunters");
            intBountyHunters = intBountyHunters + 1;
            setObjVar(objPlayer, "bountyInfo.intBountyHunters", intBountyHunters);
            debugServerConsoleMsg(objPlayer, "setting " + "bountyInfo." + objBountyHunter + ".1." + objMission.toString());
            setObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".1", objMission);
            setObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".intMissionCount", 1);
        }
    }

    public void removeBountyHunterTrackingInformation(obj_id objPlayer, obj_id objBountyHunter, obj_id objMission) throws InterruptedException
    {
        if (hasObjVar(objPlayer, "bountyInfo." + objBountyHunter.toString()))
        {
            debugServerConsoleMsg(objPlayer, "hasObjVar");
            int intMissionCount = getIntObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".intMissionCount");
            debugServerConsoleMsg(objPlayer, "intMissionCount = " + intMissionCount);
            int intI = 1;
            int intJ = 0;
            int intMissionIndex;
            if (intMissionCount > 1)
            {
                debugServerConsoleMsg(objPlayer, "intMissionCount is greater than 1");
                obj_id[] objBackup = new obj_id[intMissionCount];
                while (intI <= intMissionCount)
                {
                    debugServerConsoleMsg(objPlayer, "intI is " + intI);
                    if (objMission == getObjIdObjVar(objPlayer, "bountyInfo." + objBountyHunter + "." + intI))
                    {
                        debugServerConsoleMsg(objPlayer, "found mission obj_id at position " + intI + " in removebountyhunter");
                    }
                    else
                    {
                        debugServerConsoleMsg(objPlayer, "intI is " + intI);
                        debugServerConsoleMsg(objPlayer, "intJ is " + intJ);
                        debugServerConsoleMsg(objPlayer, "objBackup[] length is " + objBackup.length);
                        debugServerConsoleMsg(objPlayer, "bountyInfo." + objBountyHunter + "." + intI);
                        objBackup[intJ] = getObjIdObjVar(objPlayer, "bountyInfo." + objBountyHunter + "." + intI);
                        intJ = intJ + 1;
                    }
                    intI = intI + 1;
                }
                intI = 0;
                intJ = 1;
                debugServerConsoleMsg(objPlayer, "Now we need to recopy the array");
                while (intI < objBackup.length - 1)
                {
                    debugServerConsoleMsg(objPlayer, "intI is " + intI + " intJ is " + intJ);
                    setObjVar(objPlayer, "bountyInfo." + objBountyHunter + "." + intJ, objBackup[intI]);
                    intI = intI + 1;
                    intJ = intJ + 1;
                }
                removeObjVar(objPlayer, "bountyInfo." + objBountyHunter + "." + intMissionCount);
                intMissionCount = intMissionCount - 1;
                setObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".intMissionCount", intMissionCount);
            }
            else if (objMission == getObjIdObjVar(objPlayer, "bountyInfo." + objBountyHunter + ".1"))
            {
                debugServerConsoleMsg(objPlayer, "removing ");
                removeObjVar(objPlayer, "bountyInfo." + objBountyHunter);
                int intBountyHunters = getIntObjVar(objPlayer, "bountyInfo.intBountyHunters");
                intBountyHunters = intBountyHunters - 1;
                if (intBountyHunters == 0)
                {
                    removeObjVar(objPlayer, "bountyInfo");
                }
                else
                {
                    setObjVar(objPlayer, "bountyInfo.intBountyHunters", intBountyHunters);
                }
            }
        }
    }

    public void notifyBountyHunterFailure(obj_id objPlayer, obj_id objTarget) throws InterruptedException
    {
        int intI;
        intI = 1;
        int intMissionCount = getIntObjVar(objPlayer, "bountyInfo." + objTarget.toString() + ".intMissionCount");
        dictionary dctParams = new dictionary();
        debugServerConsoleMsg(objPlayer, "intMissionCount of bountyHunterNotification is " + intMissionCount);
        while (intI <= intMissionCount)
        {
            obj_id objMission = getObjIdObjVar(objPlayer, "bountyInfo." + objTarget + "." + intI);
            messageTo(objMission, "bountyHunterFail", dctParams, 0, true);
            intI = intI + 1;
        }
    }

    public void notifyBountyHunterSuccess(obj_id objPlayer, obj_id objTarget) throws InterruptedException
    {
        int intI;
        intI = 1;
        int intMissionCount = getIntObjVar(objPlayer, "bountyInfo." + objTarget.toString() + ".intMissionCount");
        dictionary dctParams = new dictionary();
        debugServerConsoleMsg(objPlayer, "intMissionCount of bountyHunterNotification is " + intMissionCount);
        while (intI <= intMissionCount)
        {
            obj_id objMission = getObjIdObjVar(objPlayer, "bountyInfo." + objTarget + "." + intI);
            debugServerConsoleMsg(objPlayer, "bountyInfo." + objTarget + "." + intI + " is " + objMission.toString());
            messageTo(objMission, "bountyHunterSuccess", dctParams, 0, true);
            intI = intI + 1;
        }
    }
}
