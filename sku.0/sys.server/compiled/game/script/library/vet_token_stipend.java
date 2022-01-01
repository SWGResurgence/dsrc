package script.library;

import script.*;

import java.util.HashSet;

public class vet_token_stipend extends script.base_script {

    private final String STF_FILE = "npe";

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "crate_use"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        obj_id tatooine = getPlanetByName("tatooine");
        String objVar = "vetToken_" + getPlayerStationId(player);
        if (!hasObjVar(tatooine, objVar)) {
            if (!hasObjVar(tatooine, objVar) || (isGod(player))) {
                if (item == menu_info_types.ITEM_USE) {
                    sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
                    grantVetTokenStipend(self, player);
                    return SCRIPT_CONTINUE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void grantVetTokenStipend(obj_id self, obj_id player) throws InterruptedException {
        obj_id pInv = utils.getInventoryContainer(player);
        obj_id[] items = new obj_id[1];
        items[1] = static_item.createNewItemFunction("item_vet_reward_token_01_01", pInv, 500);
        showLootBox(player, items);
        setObjVar(getPlanetByName("tatooine"), "vetToken_" + getPlayerStationId(player), "true");
        destroyObject(self);
    }
}