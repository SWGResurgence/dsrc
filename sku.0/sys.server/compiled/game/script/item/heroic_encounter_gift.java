package script.item;

import script.library.*;
import script.*;

import java.util.HashSet;

public class heroic_encounter_gift extends script.base_script {
    public static final String STF_FILE = "heroic_encounter_gift";

    public static obj_id[] grantHeroicEncounterGift(obj_id player) throws InterruptedException {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("heroic_axkva_min", pInv));
        theSet.add(static_item.createNewItemFunction("heroic_ig88", pInv));
        theSet.add(static_item.createNewItemFunction("heroic_tusken_army", pInv));
        theSet.add(static_item.createNewItemFunction("heroic_star_destroyer", pInv));
        theSet.add(static_item.createNewItemFunction("heroic_exar_kun", pInv));

        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_heroic_encounter_gift"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_box"));
            obj_id[] allTheArmor = grantHeroicEncounterGift(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
