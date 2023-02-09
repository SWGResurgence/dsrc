package script.theme_park.world_boss;

import script.library.pet_lib;
import script.obj_id;

public class master_controller_gizmo extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: Gizmo, the Wretched and Accursed Ewok, Corrupted by the Darkside of the Force, has been reported to have been last seen on Endor at one of the Lake Villages.");
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: Gizmo, the Wretched and Accursed Ewok has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: Gizmo, the Wretched and Accursed Ewok has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }
}
