package script.event.halloween;

import script.library.create;
import script.*;

public class pumpkin_spawner extends script.base_script {
    private static final String HALLOWEEN = "event/halloween";
    public static final string_id SID_USE = new string_id(HALLOWEEN, "spawn_pumpkins");
    public void pumpkin_spawner()
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
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            handleWorldSpawn(self);
            broadcast(player, "Spawning 48 pumpkins...");
        }
        return SCRIPT_CONTINUE;
    }
    public int handleWorldSpawn(obj_id self) throws InterruptedException {
        location here = getLocation(self);
        if (!hasObjVar(self,"halloween.pulp_master")) {
            setObjVar(self, "halloween.pulp_master", 1);
        }
        int runTimes = 0;
        while (runTimes <= 48) {
            location spot = here;
            location locLowerLeft = spot;
            locLowerLeft.x -= 6500.0f;
            locLowerLeft.z -= 6500.0f;
            location locUpperRight = spot;
            locUpperRight.x += 6500.0f;
            locUpperRight.z += 6500.0f;
            spot = getGoodLocation(2.0f, 2.0f, locLowerLeft, locUpperRight, true, false);
            //spot.y = getHeightAtLocation(spot.x, spot.z);
            obj_id pumpkin = create.object("object/tangible/holiday/halloween/pumpkin_object.iff", spot);
            attachScript(pumpkin, "event.halloween.pumpkin_smasher_object");
            setName(pumpkin, NAME_VARIATIONS[rand(0,2)]);
            obj_id player = getClosestPlayer(getLocation(self));
            float elevation = getElevation(spot);
            broadcast(player, "spawned vegetation at " + spot.x + elevation + spot.z + spot.area);
            runTimes++;
        }
        return SCRIPT_CONTINUE;
    }
}

