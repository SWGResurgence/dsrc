package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.dictionary;
import script.library.*;
import script.obj_id;

public class master_controller_mutant_acklay extends script.base_script {
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
}