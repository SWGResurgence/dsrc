package script.theme_park.world_boss;

import script.library.pet_lib;
import script.obj_id;

public class master_controller_paxvizla extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been last seen on Dxun at the Abandoned Mandalorian Outpost.");
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }
}
