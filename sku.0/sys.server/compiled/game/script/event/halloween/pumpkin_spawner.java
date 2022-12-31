
/*
@Purpose: Pumpkin Spawner for Galactic Moon Festival

@Author: BubbaJoe

Does the following:
    - Spawns a pumpkin
    - Sets the pumpkin's name
    - Sets the pumpkin's loc
    - Sets the pumpkin's script
    - Sets the pumpkin's heading

Does not do the following:

    - Look for a good loc to spawn the pumpkin (can spawn inside buildings, lairs, static flora, etc.)
 */
package script.event.halloween;

import script.library.create;
import script.*;

import java.lang.reflect.InvocationTargetException;

public class pumpkin_spawner extends script.base_script
{
    public static int PUMPKIN_COUNT = 50;
    private static final String HALLOWEEN = "event/halloween";
    public static final string_id SID_USE = new string_id(HALLOWEEN, "spawn_pumpkins");
    public String[] NAME_VARIATIONS = {
            "a plump pumpkin",
            "a regular pumpkin",
            "a scrawny pumpkin",
            "a nasty pumpkin",
            "a scary pumpkin",
            "a jagged pumpkin",
    };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "[Event] Pumpkin Spawner");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, InvocationTargetException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            handleWorldSpawn(self);
            broadcast(player, "Spawning 50 pumpkins.");
            debugConsoleMsg(player, "Do not click this terminal more than 4 times per character per planet.");
        }
        return SCRIPT_CONTINUE;
    }

    public void handleWorldSpawn(obj_id self) throws InterruptedException
    {
        for (int i = 0; i <= 100; i++)
        {
            location pumpkin_loc = getLocation(self);
            pumpkin_loc.x = pumpkin_loc.x + (rand(-7250.0f, 7250.0f));
            pumpkin_loc.z = pumpkin_loc.z + (rand(-7250.0f, 7250.0f));
            pumpkin_loc.y = getHeightAtLocation(pumpkin_loc.x, pumpkin_loc.z);
            obj_id pumpkin = create.object("object/tangible/holiday/halloween/pumpkin_object.iff", pumpkin_loc);
            attachScript(pumpkin, "event.halloween.pumpkin_smasher_object");
            setYaw(pumpkin, rand(0.0f, 360.0f));
            String randomName = getRandFromArray(NAME_VARIATIONS);
            if (randomName == null)
            {
                randomName = "a pumpkin";
            }
            setName(pumpkin, randomName);
        }
    }
    public String getRandFromArray(String[] array) throws InterruptedException
    {
        int randIndex = rand(0, array.length - 1);
        return array[randIndex];
    }
}

