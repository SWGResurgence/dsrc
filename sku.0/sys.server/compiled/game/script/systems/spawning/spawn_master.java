package script.systems.spawning;

import script.library.*;
import script.obj_id;

public class spawn_master extends script.systems.spawning.spawn_base
{

    /**
     Bootstrap function for trigger volume creation. This will be moved to OnInitialization once it comes online.
     */

    public int OnUniverseComplete(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "intMaxPlanetSpawnCount"))
        {
            setObjVar(self, "intMaxPlanetSpawnCount", 10000);
        }
        else 
        {
            int intSpawnLimit = getIntObjVar(self, "intMaxPlanetSpawnCount");
            if (intSpawnLimit > 10000)
            {
                setObjVar(self, "intMaxPlanetSpawnCount", 10000);
            }
        }
        preLoadSpawnDataTables(self);
        setObjVar(self, "intCurrentPlanetSpawnCount", 0);
        setObjVar(self, "intMinSpawnDelay", SPAWN_PLAYER_DELAY_MIN);
        setObjVar(self, "intMaxSpawnDelay", SPAWN_PLAYER_DELAY_MAX);
        if (spawning.SPAWNER_INITIAL_STATE_OFF) {
            setObjVar(self, "boolSpawnerIsOn", false);
        } else {
            setObjVar(self, "boolSpawnerIsOn", true);
        }
        return SCRIPT_CONTINUE;
    }

    /**
     * This is the initialization routine for the entire spawn system. This creates the master trigger volumes,
     * and initializes all necessary spawn values. The values are derived from spawn_base
     * These will be moved once we get a more concrete idea of spawn listings.
     */

    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "intMaxPlanetSpawnCount"))
        {
            setObjVar(self, "intMaxPlanetSpawnCount", 10000);
        }
        else 
        {
            int intSpawnLimit = getIntObjVar(self, "intMaxPlanetSpawnCount");
            if (intSpawnLimit > 10000)
            {
                setObjVar(self, "intMaxPlanetSpawnCount", 10000);
            }
        }
        preLoadSpawnDataTables(self);
        setObjVar(self, "intCurrentPlanetSpawnCount", 0);
        setObjVar(self, "intMinSpawnDelay", SPAWN_PLAYER_DELAY_MIN);
        setObjVar(self, "intMaxSpawnDelay", SPAWN_PLAYER_DELAY_MAX);
        if (spawning.SPAWNER_INITIAL_STATE_OFF) {
            setObjVar(self, "boolSpawnerIsOn", false);
        } else {
            setObjVar(self, "boolSpawnerIsOn", true);
        }
        return SCRIPT_CONTINUE;
    }
}
