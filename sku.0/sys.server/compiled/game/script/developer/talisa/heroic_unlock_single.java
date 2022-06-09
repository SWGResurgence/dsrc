package developer.talisa;

import script.*;
import script.library.instance;
import script.library.resource;
import script.library.sui;

public class heroic_unlock extends script.base_script {
    public heroic_unlock() {
    }
    
    public static final string_id SID = new string_id("sui", "use");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        if (!utils.isNestedWithin(self, player)) {
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null) {
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            use(self, player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    
    public void use(obj_id self, obj_idplayer) throws InterruptedException {
        //unlock heroics here...
    }
}
