package script.theme_park.alderaan.act2;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ai_lib;
import script.library.locations;
import script.library.utils;
import script.location;
import script.obj_id;
import script.region;

public class imperial_commander extends script.base_script
{
    public imperial_commander()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int messageStartMission(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        obj_id npc = params.getObjId("npc");
        int missionNum = params.getInt("value");
        location deliveryLoc;
        String npcName;
        if (missionNum == 5)
        {
            deliveryLoc = getDeliveryLoc(player, 1500, 2500);
            npcName = "object/building/theme_park/alderaan/act2/rebel_drall_camp.iff";
        }
        else
        {
            LOG("CoA2_Imperial", "WARNING!!!  Imperial_Commander - Recieved messageStartMission for unknown mission number. (" + missionNum + ")");
            return SCRIPT_OVERRIDE;
        }
        location returnLoc = new location();
        obj_id building = getTopMostContainer(npc);
        if (building == npc)
        {
            returnLoc = getLocation(npc);
        }
        else
        {
            returnLoc = getLocation(building);
        }
        setObjVar(player, "coa2.imperial.missionNum", missionNum);
        setObjVar(player, "coa2.imperial.missionNpc", npcName);
        setObjVar(player, "coa2.imperial.missionLoc", deliveryLoc);
        setObjVar(player, "coa2.imperial.returnLoc", returnLoc);
        if (hasScript(player, "theme_park.alderaan.act2.imperial_mission"))
        {
            messageTo(player, "handleRestartMission", params, 0, false);
        }
        else
        {
            attachScript(player, "theme_park.alderaan.act2.imperial_mission");
        }
        return SCRIPT_CONTINUE;
    }

    public int messageAbortMission(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        obj_id npc = params.getObjId("npc");
        int missionNum = params.getInt("value");
        if (missionNum == 5)
        {
            messageTo(player, "handleAbortMission", params, 0, false);
        }
        else
        {
            LOG("CoA2_Imperial", "WARNING!!!  Imperial_Commander - Recieved messageAbortMission for unknown mission number. (" + missionNum + ")");
        }
        return SCRIPT_CONTINUE;
    }

    public location getDeliveryLoc(obj_id player, int minDistance, int maxDistance) throws InterruptedException
    {
        region city = null;
        location cityCenter = null;
        location deliveryLoc = null;
        city = locations.getCityRegion(getLocation(player));
        if (city != null)
        {
            cityCenter = locations.getRegionCenter(city);
        }
        if (cityCenter == null)
        {
            cityCenter = getLocation(player);
        }
        int x = 0;
        while (x < 10)
        {
            LOG("CoA2_Imperial", "Imperial Delivery Location:  Attepmt #" + (x + 1));
            location loc = utils.getRandomLocationInRing(cityCenter, minDistance, maxDistance);
            deliveryLoc = locations.getGoodLocationAroundLocation(loc, 30.0f, 30.0f, 200.0f, 200.0f);
            if (deliveryLoc != null)
            {
                deliveryLoc.y = getElevation(deliveryLoc);
                break;
            }
            x += 1;
        }
        return deliveryLoc;
    }
}
