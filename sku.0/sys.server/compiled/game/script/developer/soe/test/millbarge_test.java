package script.developer.soe.test;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.buff;
import script.library.city;
import script.library.house_pet;
import script.library.utils;
import script.obj_id;

import java.util.Vector;

public class millbarge_test extends script.base_script
{
    public millbarge_test()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!isGod(self) || getGodLevel(self) < 50 || !isPlayer(self))
        {
            detachScript(self, "test.millbarge_test");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (isGod(self))
        {
            java.util.StringTokenizer tok = new java.util.StringTokenizer(text);
            String command = tok.nextToken();
            obj_id myTarget = getIntendedTarget(self);
            if (myTarget == null || myTarget.equals(""))
            {
                myTarget = self;
            }
            if (command.equalsIgnoreCase("log"))
            {
                LOG("sissynoid", "Test Log");
            }
            if (command.equalsIgnoreCase("applyRoot"))
            {
                buff.applyBuff(myTarget, "sp_cc_dot");
            }
            if (command.equalsIgnoreCase("rootImmune"))
            {
                buff.applyBuff(myTarget, "battlefield_vehicle_1");
            }
            if (command.equalsIgnoreCase("removeBuffs"))
            {
                if (buff.hasBuff(myTarget, "of_vortex_root"))
                {
                    buff.removeBuff(myTarget, "of_vortex_root");
                }
                if (buff.hasBuff(myTarget, "battlefield_vehicle_1"))
                {
                    buff.removeBuff(myTarget, "battlefield_vehicle_1");
                }
            }
            if (command.equalsIgnoreCase("clearDot"))
            {
                if (buff.hasBuff(myTarget, "sp_cc_dot"))
                {
                    buff.removeBuff(myTarget, "sp_cc_dot");
                }
            }
            if (command.equalsIgnoreCase("playSmoke"))
            {
                playClientEffectObj(myTarget, "appearance/pt_hoth_destroyed_turret_smoke.prt", self, "");
            }
            if (command.equalsIgnoreCase("getCityA"))
            {
                int city_id = getCityAtLocation(getLocation(self), 0);
                obj_id vote = cityGetCitizenAllegiance(city_id, self);
                broadcast(self, "City ID: " + city_id + " : My Allegience is to: (" + vote + ") " + cityGetCitizenName(city_id, vote));
            }
            if (command.equalsIgnoreCase("mayor"))
            {
                int city_id = getCityAtLocation(getLocation(self), 0);
                obj_id mayor = cityGetLeader(city_id);
                String name = cityGetCitizenName(city_id, mayor);
                broadcast(self, "mayor's name is: " + name + " Obj_Id: " + mayor);
            }
            if (command.equalsIgnoreCase("setEntSpec"))
            {
                int city_id = city.checkCity(self, false);
                city.setSpecialization(city_id, "city_spec_entertainer");
                obj_id city_hall = cityGetCityHall(city_id);
                removeObjVar(city_hall, "spec_stamp");
            }
            if (command.equalsIgnoreCase("removeEntSpec"))
            {
                int city_id = city.checkCity(self, false);
                city.setSpecialization(city_id, "city_spec_sample_rich");
                obj_id city_hall = cityGetCityHall(city_id);
                removeObjVar(city_hall, "spec_stamp");
            }
            if (command.equalsIgnoreCase("test_safe_house_system_overload"))
            {
                int city_id = getCityAtLocation(getLocation(self), 0);
                obj_id city_hall = cityGetCityHall(city_id);
                messageTo(city_hall, "QaTestSafeHouseOverload", null, 0.0f, true);
            }
            if (command.equalsIgnoreCase("whatCity"))
            {
                int city = getCitizenOfCityId(self);
                broadcast(self, "My getCitizenOfCityId Returned: " + city);
            }
            if (command.equalsIgnoreCase("breakspawner"))
            {
                Vector spawnedList = utils.getResizeableObjIdArrayScriptVar(myTarget, "myCreations");
                obj_id brokenId = obj_id.NULL_ID;
                for (int i = 0; i < spawnedList.size(); i++)
                {
                    broadcast(self, "Breaking Spawner - previous ID: " + spawnedList.get(i));
                    spawnedList.set(i, brokenId);
                }
                utils.setScriptVar(myTarget, "myCreations", spawnedList);
            }
            if (command.equalsIgnoreCase("owner"))
            {
                obj_id owner = getOwner(myTarget);
                broadcast(self, "Owner is: " + owner);
            }
            if (command.equalsIgnoreCase("resetScurrierSnackTime"))
            {
                if (hasObjVar(myTarget, house_pet.SCURRIER_SNACK_LAST_FED))
                {
                    removeObjVar(myTarget, house_pet.SCURRIER_SNACK_LAST_FED);
                    broadcast(self, "Scurrier Snack Time has been reset");
                }
                else
                {
                    broadcast(self, "Scurrier Snack Time objvar could not be found.  You must target the Controller (Feeding Bowl) for this command to work.");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
