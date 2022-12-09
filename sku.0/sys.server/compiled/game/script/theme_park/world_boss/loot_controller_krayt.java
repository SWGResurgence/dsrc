package script.theme_park.world_boss;

import script.dictionary;
import script.library.*;
import script.obj_id;
import script.string_id;

public class loot_controller_krayt extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have last been seen on Tatooine. Czerka Corporation is paying for it's remains.");
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        showFlyText(self, new string_id("+ REGURGITATION + "), 10.5f, colors.DEEPPINK);
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }

    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id wpn, int[] damage) throws InterruptedException
    {
        int health = getHealth(self);
        int maxHealth = getMaxHealth(self);
        int percentHealth = (health * 100) / maxHealth;
        if (attacker == self) //this is a self damage check
        {
            return SCRIPT_CONTINUE;
        }
        int retaliateChance = rand(1, 100);
        if ((retaliateChance % 2) == 0)
        {
            if (percentHealth < 10)
            {
                obj_id[] aoe_targets = getCreaturesInRange(self, 64.0f);
                for (obj_id testSubjects : aoe_targets)
                {
                    buff.applyBuff(testSubjects, "acid", 60.0f, 5.0f);
                    showFlyText(testSubjects, new string_id("- STOMACH ACID -"), 10.5f, colors.YELLOW);
                    broadcast(testSubjects, "You have been hit with stomach acid!");
                }
            }
        }
        else
        {
            if (percentHealth < 10)
            {
                obj_id[] aoe_targets = getCreaturesInRange(self, 64.0f);
                for (obj_id testSubjects : aoe_targets)
                {
                    buff.applyBuff(testSubjects, "poison", 60.0f, 5.0f);
                    showFlyText(testSubjects, new string_id("- POISON -"), 10.5f, colors.GREEN);
                    broadcast(testSubjects, "You have been hit with poison!");
                }
            }
            //this is the odd chance. Do thing or something here.
        }
        return SCRIPT_CONTINUE;
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        createMyLoot(self);
        createStomachContents(self, corpseInventory);
        return SCRIPT_CONTINUE;
    }

    public void createMyLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
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
        if (x < 61)
        { // 60% Drop Rate: TCG - Tatooine Travel Advertisement
            static_item.createNewItemFunction("item_tcg_loot_reward_series4_tatooine_travel_advertisement_02_01", corpseInventory);
        }
        if (x < 26)
        { // 25% Drop Rate: TCG - Toydarian Greeter
            static_item.createNewItemFunction("item_tcg_loot_reward_series3_greeter_toydarian", corpseInventory);
        }
        return;
    }

    public void createStomachContents(obj_id self, obj_id container) throws InterruptedException
    {
        int JUNK_COUNT = getAllPlayers(getLocation(self), 120.0f).length * 3;
        if (container == null)
        {
            return;
        }
        if (!isIdValid(self))
        {
            return;
        }
        if (!isIdValid(container))
        {
            return;
        }
        String JUNK_TABLE = "datatables/crafting/reverse_engineering_junk.iff";
        String column = "note";
        for (int i = 0; i < JUNK_COUNT; i++)
        {
            //Subtract 1 in the junk table index to make sure we don't hit the "none" row.
            String junk = dataTableGetString(JUNK_TABLE, rand(1, dataTableGetNumRows(JUNK_TABLE) - 1), column);
            obj_id junkItem = static_item.createNewItemFunction(junk, container);
            if (isIdValid(junkItem))
            {
                setCount(junkItem, rand(1, 5));
            }
        }
    }
}
