package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.dictionary;
import script.library.pet_lib;
import script.library.utils;
import script.obj_id;

public class master_controller_nexu extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    public static final float  SIGHTING_RADIUS = 12.0f;
    public static final String  SIGHTING_NAME = "nexu_spotted";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume(SIGHTING_NAME, SIGHTING_RADIUS, false);
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

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
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
                    broadcast(who, "You have spotted The Mutant Nexu Abomination!");
                    utils.setScriptVar(self, "spotted", true);
                }
            }
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
                    sendSystemMessageGalaxyTestingOnly("ATTENTION ADVENTURERS: The Mutant Nexu Abomination has been sighted within the Geonosian Caves.");
                    utils.setScriptVar(self, "spotted", true);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
