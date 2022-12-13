package script.systems.loot;

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class unique_loot extends script.base_script
{
    public unique_loot()
    {
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null || !isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "uniqueLootChance") || !hasObjVar(self, "uniqueLootItemName"))
        {
            return SCRIPT_CONTINUE;
        }
        createUniqueLoot(self);
        return SCRIPT_CONTINUE;
    }

    public void createUniqueLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        int uniqueLootChance = getIntObjVar(self, "uniqueLootChance");
        String uniqueLootItemName = getStringObjVar(self, "uniqueLootItemName");
        if (corpseInventory == null)
        {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null)
        {
            return;
        }
        int x = rand(1, 100);
        if (x <= uniqueLootChance)
        {
            static_item.createNewItemFunction(uniqueLootItemName, corpseInventory);
        }
        return;
    }
}
