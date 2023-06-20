package script.theme_park.dungeon.geonosian_madbio_bunker;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.pet_lib;
import script.library.resurgence;
import script.library.utils;
import script.obj_id;

public class master_controller_mutant_acklay extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    public static final float SIGHTING_RADIUS = 12.0f;
    public static final String SIGHTING_NAME = "acklay_spotted";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.dungeon.geo_madbio.acklay");
        setObjVar(tatooine, "dungeon_finder.dungeon.geo_madbio.acklay", "Active");
        resurgence.setupLootAmount(self, rand(1, 2));
        createTriggerVolume(SIGHTING_NAME, SIGHTING_RADIUS, false);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.dungeon.geo_madbio.acklay");
        setObjVar(tatooine, "dungeon_finder.dungeon.geo_madbio.acklay", "Inactive");
        return SCRIPT_CONTINUE;
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id who) throws InterruptedException
    {
        if (isPlayer(who))
        {
            if (volumeName.equals(SIGHTING_NAME))
            {
                if (!utils.hasScriptVar(self, "spotted"))
                {
                    broadcast(who, "You have spotted The Mutant Acklay Abomination!");
                    utils.setScriptVar(self, "spotted", true);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}