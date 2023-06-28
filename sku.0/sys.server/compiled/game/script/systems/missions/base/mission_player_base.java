package script.systems.missions.base;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class mission_player_base extends script.systems.missions.base.mission_dynamic_base
{
    public mission_player_base()
    {
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
        messageTo(objMission, "destructionSuccess", dctParams, 0, true);
    }

    public void playerDeliverSuccess(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS SUCCESFUL!");
        messageTo(objMission, "deliverSuccess", dctParams, 0, true);
    }

    public void playerDeliverFail(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS FAILED!!");
        messageTo(objMission, "deliverFail", dctParams, 0, true);
    }

    public void playerDeliverIncomplete(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS Incomplete!");
        messageTo(objMission, "deliverIncomplete", dctParams, 0, true);
    }

    public void playerFetchSuccess(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS SUCCESFUL!");
        messageTo(objMission, "fetchSuccess", dctParams, 0, true);
    }

    public void playerFetchFail(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS FAILED!!");
        messageTo(objMission, "fetchFail", dctParams, 0, true);
    }

    public void playerFetchIncomplete(obj_id objMission) throws InterruptedException
    {
        dictionary dctParams = new dictionary();
        obj_id objPlayer = getMissionHolder(objMission);
        debugSpeakMsg(objPlayer, "MISSION IS Incomplete!");
        messageTo(objMission, "fetchIncomplete", dctParams, 0, true);
    }

    public void returnAllCredits(obj_id objMission) throws InterruptedException
    {
    }

    public void setupDeliverObjects(obj_id objMission) throws InterruptedException
    {
    }

    public void cleanupDeliverObjects(obj_id objMission) throws InterruptedException
    {
    }
}
