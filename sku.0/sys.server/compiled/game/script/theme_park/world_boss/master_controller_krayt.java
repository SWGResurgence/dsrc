package script.theme_park.world_boss;

import script.dictionary;
import script.library.*;
import script.obj_id;
import script.string_id;

public class master_controller_krayt extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have last been seen on Tatooine. Czerka Corporation is paying for it's remains.");
        return SCRIPT_CONTINUE;
    }

    public int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.world_boss.krayt");
        setObjVar(tatooine, "dungeon_finder.world_boss.krayt", "Active");
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.world_boss.krayt");
        setObjVar(tatooine, "dungeon_finder.world_boss.krayt", "Inactive");
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
        createStomachContents(self, corpseInventory);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (isGod(killer))
        {
            return SCRIPT_CONTINUE;
        }
        showFlyText(self, new string_id("+ REGURGITATION + "), 10.5f, colors.DEEPPINK);
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerFullName(pet_lib.getMaster(killer)));
        }
        else
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Elder Ancient Krayt Dragon has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerFullName(killer));
        }
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
                obj_id[] aoe_targets = getPlayerCreaturesInRange(getLocation(self), 64f);
                for (obj_id testSubjects : aoe_targets)
                {
                    buff.applyBuff(testSubjects, "acid", 60f, 5f);

                }
            }
        }
        else
        {
            if (percentHealth < 10)
            {
                if (!utils.hasScriptVar(self, "krayt_poison"))
                {
                    obj_id[] aoe_targets = getCreaturesInRange(self, 64.0f);
                    for (obj_id testSubjects : aoe_targets)
                    {
                        buff.applyBuff(testSubjects, "poison", 60.0f, 5.0f);
                        showFlyText(testSubjects, new string_id("- POISON -"), 10.5f, colors.GREEN);
                        broadcast(testSubjects, "You have been hit with poison!");
                    }
                    utils.setScriptVar(self, "krayt_poison", 1);
                }
            }
            //this is the odd chance. Do thing or something here.
        }
        return SCRIPT_CONTINUE;
    }

    public void createStomachContents(obj_id self, obj_id container) throws InterruptedException
    {
        obj_id[] attackerList = utils.getObjIdBatchScriptVar(self, "creditForKills.attackerList.attackers");
        int JUNK_COUNT = getAllPlayers(getLocation(self), 64.0f).length * 4;
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
            obj_id player = attackerList[rand(0, attackerList.length - 1)];
            //Add 1 in the junk table index to make sure we don't hit the "none" row.
            String junk = dataTableGetString(JUNK_TABLE, rand(1, dataTableGetNumRows(JUNK_TABLE)), column);
            /*if (junk.contains("heroic_") || junk.contains("_heroic_") || junk.contains("meatlump"))
            {
                --JUNK_COUNT;
                return;
            }*/
            obj_id junkItem = static_item.createNewItemFunction(junk, utils.getInventoryContainer(player));
            if (isIdValid(junkItem))
            {
                setCount(junkItem, rand(1, 3));
            }
        }
    }
}
