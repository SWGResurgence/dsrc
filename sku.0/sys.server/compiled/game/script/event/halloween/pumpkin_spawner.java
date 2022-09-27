package script.event.halloween;

import script.*;
import script.library.create;

public class pumpkin_spawner extends script.base_script {
    public void pumpkin_spawnert()
    {
    }
    public String[] NAME_VARIATIONS = {
            "a plump pumpkin",
            "a regular pumpkin",
            "a scrawny pumpkin",
    };
    public int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException {
        handleWorldSpawn(self, getCurrentSceneName());
        return SCRIPT_CONTINUE;
    }
    public void handleWorldSpawn(obj_id self, String planet) throws InterruptedException {
        String pumpkinTemplate = "";
        location here = getLocation(self);
        if (!hasObjVar(self,"halloween.pulp_master")) {
            setObjVar(self, "halloween.pulp_master", planet);
        }
        int runTimes = 0;
        while (runTimes <= 48) {
            location spot = here;
            if (spot == null)
            {
                location locLowerLeft = spot;
                locLowerLeft.x -= 6500.0f;
                locLowerLeft.z -= 6500.0f;
                location locUpperRight = spot;
                locUpperRight.x += 6500.0f;
                locUpperRight.z += 6500.0f;
                spot = getGoodLocation(2.0f, 2.0f, locLowerLeft, locUpperRight, true, false);
                if (spot == null)
                {
                    LOG("halloween", "Invalid spot to place pumpkin. Revise!");
                }
                spot.y = getHeightAtLocation(spot.x, spot.z);
                obj_id pumpkin = create.object("object/tangible/holiday/halloween/pumpkin_object.iff", spot);
                setName(pumpkin, NAME_VARIATIONS[rand(0,2)]);
                obj_id player = getClosestPlayer(getLocation(self));
                broadcast(player, "spawned vegetation at " + spot.x + spot.y + spot.z + spot.area);
            }
            runTimes++;
        }
    }
}

