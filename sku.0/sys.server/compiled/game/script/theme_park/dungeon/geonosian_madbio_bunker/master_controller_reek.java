package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.dictionary;
import script.library.*;
import script.obj_id;

public class master_controller_reek extends script.base_script {
    public static final String VOLUME_NAME = "aggressive_area";
    public int OnAttach(obj_id self) throws InterruptedException {
        sendSystemMessageGalaxyTestingOnly("ATTENTION ADVENTURERS: The Mutant Reek Abomination has been sighted within the Geonosian Caves.");
        return SCRIPT_CONTINUE;
    }
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)  {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))  {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException {
        if (pet_lib.isPet(killer)) {
            sendSystemMessageGalaxyTestingOnly("ATTENTION ADVENTURERS: The Mutant Reek Abomination has been destroyed. " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION ADVENTURERS: The Mutant Reek Abomination has been destroyed. " + getName(killer));
        return SCRIPT_CONTINUE;
    }
}
