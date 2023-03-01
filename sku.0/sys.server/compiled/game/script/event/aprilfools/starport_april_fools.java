package script.event.aprilfools;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

import java.util.Calendar;
import java.util.Vector;

public class starport_april_fools extends script.base_script
{
    public starport_april_fools()
    {
    }
    public static final String CITY_OBJVAR = "aprilFools.city";
    public static final String LAST_SPAWN = "aprilFools.lastSpawn";
    public static final String CREATURE_LIST = "aprilFools.creatures";
    public static final String SPAWN_DATATABLE = "datatables/event/aprilfools/aprilfools09.iff";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if(!events.isEventActive(events.FOOLS_DAY)) {
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, LAST_SPAWN, 0);
        messageTo(self, "heartbeat", null, 60.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int heartbeat(obj_id self, dictionary params) throws InterruptedException
    {
        int currentTime = getCalendarTime();
        if(!events.isEventRunning(events.FOOLS_DAY)) {
            messageTo(self, "cleanupCreatures", null, 0.0f, false);
            messageTo(self, "heartbeat", null, 60.0f, false);
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, CITY_OBJVAR))
        {
            LOG("AprilFools", "No City ObjVar, bailing.");
            messageTo(self, "heartbeat", null, 60.0f, false);
            return SCRIPT_CONTINUE;
        }
        String cityName = getStringObjVar(self, CITY_OBJVAR);
        String spawnDatatable = SPAWN_DATATABLE;
        if (!dataTableOpen(spawnDatatable))
        {
            LOG("AprilFools", "Failed to open table " + spawnDatatable);
            return SCRIPT_CONTINUE;
        }
        int spawnTime = 3600;
        int configTime = utils.getIntConfigSetting("GameServer", "foolsDayTimer");
        if (configTime >= 1)
        {
            spawnTime = configTime * 60;
        }
        if (currentTime - getIntObjVar(self, LAST_SPAWN) < spawnTime)
        {
            messageTo(self, "heartbeat", null, 60.0f, false);
            return SCRIPT_CONTINUE;
        }
        Vector creatureList = new Vector();
        creatureList.setSize(0);
        int numRows = dataTableGetNumRows(spawnDatatable);
        obj_id[] currentCreatures;
        location currentLoc;
        obj_id newCreature;
        String templateName;

        for (int i = 0; i < numRows; ++i)
        {
            if (!getCurrentSceneName().equals(dataTableGetString(spawnDatatable, i, 0)) || !cityName.equals(dataTableGetString(spawnDatatable, i, 1)))
            {
                continue;
            }
            int maxCreatures = dataTableGetInt(spawnDatatable, i, 6);
            int totalCreatures = 0;
            if (hasObjVar(self, CREATURE_LIST))
            {
                currentCreatures = getObjIdArrayObjVar(self, CREATURE_LIST);
                if (currentCreatures != null && currentCreatures.length > 0)
                {
                    for (obj_id currentCreature : currentCreatures) {
                        if (isIdValid(currentCreature) && exists(currentCreature) && !isDead(currentCreature)) {
                            ++totalCreatures;
                            utils.addElement(creatureList, currentCreature);
                        }
                    }
                }
            }
            if (totalCreatures == maxCreatures)
            {
                setObjVar(self, CREATURE_LIST, creatureList);
                messageTo(self, "heartbeat", null, 60.0f, false);
                return SCRIPT_CONTINUE;
            }
            float cx = dataTableGetFloat(spawnDatatable, i, 3);
            float cy = dataTableGetFloat(spawnDatatable, i, 4);
            float cz = dataTableGetFloat(spawnDatatable, i, 5);
            templateName = dataTableGetString(spawnDatatable, i, 2);
            currentLoc = getLocation(self);
            currentLoc.x = cx;
            currentLoc.y = cy;
            currentLoc.z = cz;
            for (int j = totalCreatures; j < maxCreatures; ++j)
            {
                currentLoc.x += (10 * j);
                newCreature = create.object(templateName, currentLoc);
                utils.addElement(creatureList, newCreature);
            }
            setObjVar(self, CREATURE_LIST, creatureList);
            setObjVar(self, LAST_SPAWN, getCalendarTime());
        }
        messageTo(self, "heartbeat", null, 60.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int cleanupCreatures(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, CREATURE_LIST))
        {
            obj_id[] currentCreatures = getObjIdArrayObjVar(self, CREATURE_LIST);
            if (currentCreatures != null && currentCreatures.length > 0)
            {
                for (obj_id currentCreature : currentCreatures) {
                    if (isIdValid(currentCreature) && exists(currentCreature) && !isDead(currentCreature)) {
                        if (ai_lib.isInCombat(currentCreature)) {
                            if (!utils.hasScriptVar(self, "destroyDelay")) {
                                utils.setScriptVar(self, "destroyDelay", 1);
                                return SCRIPT_CONTINUE;
                            }
                            destroyObject(currentCreature);
                        }
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
