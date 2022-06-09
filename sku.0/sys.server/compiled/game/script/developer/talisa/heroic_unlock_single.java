package script.developer.talisa;

import script.*;
import script.library.instance;
import script.library.resource;
import script.library.sui;
import script.library.utils;

public class heroic_unlock_single extends script.base_script {
    public heroic_unlock_single() {
    }
    
    public static final string_id SID = new string_id("sui", "use");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        if (!utils.isNestedWithin(self, player)) {
            sendSystemMessageTestingOnly(player, "The Development Testing for Menu Request is currently disabled.");
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null) {
            mid.setServerNotify(true);
            sendSystemMessageTestingOnly(player, "The Development Testing for Item Use is currently disabled.");
        }
        return SCRIPT_CONTINUE;
    }
    
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            use(self, player);
            sendSystemMessageTestingOnly(player, "The Development Testing for Menu Select is currently disabled.");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    
    public void use(obj_id self, obj_id player) throws InterruptedException {
        //unlock heroics here...
    }
}
