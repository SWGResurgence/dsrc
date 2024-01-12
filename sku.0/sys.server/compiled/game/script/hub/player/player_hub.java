package script.hub.player;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.sui;
import script.library.utils;
import script.location;
import script.obj_id;

import java.lang.reflect.Array;

public class player_hub extends script.base_script
{

    public static String TRACK_TITLE = "Area Track";
    public static String TRACK_PROMPT = "Current Trackables:";
    public static float BASE_TRACK_RANGE = 128.0f;

    public player_hub()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "hub.introMsg"))
        {
            //ui window similar to chronicler but a new ui window with overview
            sui.msgbox(self, self, "test123@");
            setObjVar(self, "hub.introMsg", 1);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnDuelRequest(obj_id actor, obj_id target)
    {
        if (getCurrentSceneName().equals("dungeon2"))
        {
            if (!getConfigSetting("Hub", "dueling").equals("true"))
            {
                broadcast(actor, "You currently may not duel.");
                return SCRIPT_CONTINUE;
            }
            else
            {
                broadcast(actor, "An unknown error has occured.");
            }
        }

        return SCRIPT_CONTINUE;
    }

    public int putOnStation(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public void groupTEF(obj_id self, obj_id group)
    {
        obj_id groupObject = group;
        obj_id[] objPlayers = getGroupMemberIds(groupObject);
        if (objPlayers != null)
        {
            for (obj_id objPlayer : objPlayers)
            {
                if (objPlayer != self)
                {
                    pvpSetPersonalEnemyFlag(self, objPlayer);
                    LOG("PVP", "[TEF System] " + getFirstName(self) + "has applied a TEF to the group " + groupObject);
                }
            }
        }
    }

    public int showAreaTrack(obj_id self) throws InterruptedException
    {
        String[] conc_list = null;
        float range = BASE_TRACK_RANGE;
        obj_id[] trackables = getCreaturesInRange(self, range);
        for (obj_id trackable : trackables)
        {
            int index = Array.getLength(trackables);
            Array.set(conc_list, index--, getFirstName(trackable));
        }
        sui.listbox(self, TRACK_PROMPT, TRACK_TITLE, conc_list);
        return SCRIPT_CONTINUE;
    }

    public int bustIn(obj_id building, obj_id self) throws InterruptedException
    {
        float duration = getSkillModBonus(self, "bh_break_in") * 120;
        moveHouseItemToPlayer(getTopMostContainer(self), getClosestPlayer(getLocation(self)), self);
        messageTo(self, "bustOut", null, duration, true);
        return SCRIPT_CONTINUE;
    }

    public int bustOut(obj_id self) throws InterruptedException
    {
        obj_id building = getTopMostContainer(self);
        location getYoAssOuttaHere = getBuildingEjectLocation(building);
        warpPlayer(self, getYoAssOuttaHere.area, getYoAssOuttaHere.x, getYoAssOuttaHere.y, getYoAssOuttaHere.z,
                getYoAssOuttaHere.cell, 0, 0, 0);
        broadcast(self, "You best leave before the authorities arrive!");
        return SCRIPT_CONTINUE;
    }

    public int cmdListObjects(obj_id self, obj_id target, String params, float defaultTime, dictionary param)
            throws InterruptedException
    {
        String[] object_list = null;
        float range = utils.stringToFloat(params);
        obj_id[] searchables = getCreaturesInRange(self, range);
        for (obj_id searchable : searchables)
        {
            int index = Array.getLength(searchables);
            Array.set(object_list, index--, getFirstName(searchable));
        }
        sui.listbox(self, TRACK_PROMPT, TRACK_TITLE, object_list, "handleList");
        return SCRIPT_CONTINUE;
    }

    public int handleList(obj_id self, dictionary param) throws InterruptedException
    {
        if ((param == null) || (param.isEmpty()))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(param);
        int btn = sui.getIntButtonPressed(param);
        int idx = sui.getListboxSelectedRow(param);
        if (btn == sui.BP_REVERT)
        {
            broadcast(self, "Exiting..");
            return SCRIPT_CONTINUE;
        }
        if (btn == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(player))
        {
            broadcast(player, "What a piece of junk!");
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }

}
