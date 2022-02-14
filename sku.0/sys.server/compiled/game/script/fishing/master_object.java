package script.fishing;

import script.dictionary;
import script.library.fishing;
import script.obj_id;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class master_object extends script.base_script {

    public static final String OBJVAR_LEADERBOARD_UPDATE_TIME = "leaderboards_last_updated";
    public static final String OBJVAR_CONTROLLER_SETUP_TIME = "master_fishing_object_created";

    public int OnInitialize(obj_id self) throws InterruptedException {
        if(!hasObjVar(self, OBJVAR_CONTROLLER_SETUP_TIME)) {
            messageTo(self, "handleSetupMasterFishingObject", null, 3, true);
        }
        setName(self, "Master Fishing Object [do not move]");

        // Reset the leaderboard period weekly and force update if 7-days have passed since last update (in case of offline fails to trigger update)
        createWeeklyAlarmClock(self, "handleUpdateLeaderboards", null, DAY_OF_WEEK_THU, 19, 0,0);
        if (!hasObjVar(self, OBJVAR_LEADERBOARD_UPDATE_TIME)) {
            messageTo(self, "handleUpdateLeaderboards", null, 120, false);
        }
        // Update the current period board every 60 minutes
        createHourlyAlarmClock(self, "handleUpdateCurrentPeriod", null, 0, 0);
        if(getCalendarTime() > getIntObjVar(self, OBJVAR_LEADERBOARD_UPDATE_TIME) + 604800) {
            messageTo(self, "handleUpdateLeaderboards", null, 120, false);
        }
        return SCRIPT_CONTINUE;
    }

    public int handleSetupMasterFishingObject(obj_id self, dictionary params) throws InterruptedException {
        if(!hasObjVar(self, OBJVAR_CONTROLLER_SETUP_TIME)) {
            if (isIdValid(self)) {
                setObjVar(getPlanetByName("tatooine"), "master_fishing_object", self);
                setObjVar(self, OBJVAR_CONTROLLER_SETUP_TIME, getCalendarTime());
                fishing.handleConstructElusiveFishTable(self);
                LOG("fishing", "Created Master Fishing Object ("+ self +") and attached to Tatooine Planet Object.");
            } else {
                LOG("fishing", "[ERROR FEATURE-FATAL]: Attempted to create Master Fishing Object but it failed.");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int handleUpdateLeaderboards(obj_id self, dictionary params) throws InterruptedException {
        // placeholder for weekly updated leaderboards
        setObjVar(self, OBJVAR_LEADERBOARD_UPDATE_TIME, getCalendarTime());
        return SCRIPT_CONTINUE;
    }
    /*
    * When fishing period starts:
    * We're actively keeping track of who is in what position
    *
     */


    /**
     * getCurrentLeadingFishingCities
     * The total length of the best 25 non-collection fish caught during the Fishing Leaderboard period by the members/citizens
     * of the guild/city are used to determine the guild/city Fishing Leaderboard score.
     * @return dictionary containing cityId as key and a city's respective aggregate record of fish for the current period as the value
     */
    public static dictionary getCurrentLeadingFishingCities() throws InterruptedException {
        dictionary cityTotals = new dictionary();
        float sizeAggregateCity = 0;
        float size = 0;
        String fishObjVar = fishing.OBJVAR_LARGEST_FISH_CAUGHT_THIS_PERIOD;
        for (Integer city : getAllCityIds()) {
            sizeAggregateCity = 0;
            for (obj_id citizen : cityGetCitizenIds(city)) {
                size = getFloatObjVar(citizen, fishObjVar);
                if (size > 0) {
                    sizeAggregateCity += size;
                }
            }
            if (sizeAggregateCity > 0) {
                cityTotals.put(city, sizeAggregateCity);
            }
        }
        return cityTotals;
    }
}
