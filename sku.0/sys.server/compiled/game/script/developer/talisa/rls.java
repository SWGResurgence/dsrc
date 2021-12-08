package script.developer.talisa;

import script.*;

import script.library.collection;
import script.library.loot;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.ArrayList;
import java.util.List;

public class rls extends script.base_script {
    public int OnSpeaking(obj_id self, String text) throws InterruptedException {
        if(text.equalsIgnoreCase("rls")) {
            loot.createRareLootChest(self, 1);
            loot.createRareLootChest(self, 2);
            loot.createRareLootChest(self, 3);
        }
        return SCRIPT_CONTINUE;
    }
}