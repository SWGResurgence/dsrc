package script.developer.talisa;

import script.library.loot;
import script.obj_id;

public class rare_loot_chests extends script.base_script
{
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (text.equalsIgnoreCase("rls"))
        {
            loot.createRareLootChest(self, 1);
            loot.createRareLootChest(self, 2);
            loot.createRareLootChest(self, 3);
        }
        return SCRIPT_CONTINUE;
    }
}