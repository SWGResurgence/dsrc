package script.theme_park.world_boss;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.*;
import script.obj_id;
import script.string_id;

public class master_controller_krayt extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.krayt"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.krayt");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.krayt", "Active");
        resurgence.doWorldBossAnnounce(self, resurgence.WORLD_BOSS_KRAYT);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (isGod(killer))
        {
            return SCRIPT_CONTINUE;
        }
        showFlyText(self, new string_id("+ REGURGITATION + "), 10.5f, colors.DEEPPINK);
        resurgence.doWorldBossDeathMsg(self);
        obj_id[] attackerList = utils.getObjIdBatchScriptVar(self, "creditForKills.attackerList.attackers");
        for (obj_id anAttacker : attackerList)
        {
            createStomachContents(self, utils.getInventoryContainer(anAttacker));
        }
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.krayt"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.krayt");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.krayt", "Inactive");
        return SCRIPT_CONTINUE;
    }

    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id wpn, int[] damage) throws InterruptedException
    {
        int health = getHealth(self);
        int maxHealth = getMaxHealth(self);
        int percentHealth = (health * 100) / maxHealth;
        if (attacker == self)
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
        String JUNK_TABLE = "datatables/crafting/reverse_engineering_junk.iff";
        String column = "note";
        int JUNK_COUNT = 5;
        for (int i = 0; i < JUNK_COUNT; i++)
        {
            String junk = dataTableGetString(JUNK_TABLE, rand(1, dataTableGetNumRows(JUNK_TABLE)), column);
            obj_id junkItem = static_item.createNewItemFunction(junk, container);
            if (isIdValid(junkItem))
            {
                setCount(junkItem, rand(1, 3));
            }
        }

    }
}
