package script.terminal;

import script.*;

public class terminal_newsnet extends script.base_script {
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info item) throws InterruptedException {
        item.addRootMenu(menu_info_types.ITEM_USE, new string_id("gcw", "vote"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            launchClientWebBrowser(player, "https://swgresurgence.com/forums/showthread.php?119-Game-Update-2-0-0");
        }
        return SCRIPT_CONTINUE;
    }
}
