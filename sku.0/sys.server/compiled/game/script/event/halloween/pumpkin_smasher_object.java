/*
@Purpose: Pumpkin Object for Galactic Moon Festival

@Author: BubbaJoe

@Overview: This is the smash script. This script handles spawning and awarding of the pumpkin smashers.
    I never finished the collection. If you want to add in the collection stuff, go for it. Set collectionEnabled to true and add the collection stuff in.

    This uses what i'd call "testing" funcs. If you want to use this for a 100% vanilla live server, you'll need to refactor this scripts.

    TL;DR: WYSIWYG.
 */
package script.event.halloween;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.*;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class pumpkin_smasher_object extends script.base_script
{
    public static final string_id SID_USE = new string_id("Smash!");
    public static final String COLLECTION_NAME = "col_halloween_pumpkin_smasher";
    public static final String PULPED_ITER_OBJVAR = "halloween.pulped";
    private static final String HALLOWEEN = "event/halloween";
    public static String[] LOOT_TABLES = {
            "creature/elite_insect:elite_insect_81_90",
            "npc/boss_npc:boss_npc_81_90",
            "treasure/treasure_81_90",
            "creature/creature_81_90",
            "creature/elite_creature:elite_creature_81_90"
    };
    public boolean collectionEnabled = false;
    public pumpkin_smasher_object()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            doAnimationAction(player, "stamp_feet");
            int baseUse = 0;
            if (!hasObjVar(player, PULPED_ITER_OBJVAR))
            {
                setObjVar(player, PULPED_ITER_OBJVAR, baseUse);
            }
            int coinChance = rand(1, 100);
            int mobChance = rand(1, 100);
            int devastationChance = rand(1, 100);
            int buffChance = rand(1, 3);
            int wormyDropAmt = rand(1, 7);
            if (coinChance <= 50)
            {
                broadcast(player, "You find some strange items inside this pumpkin.");
                static_item.createNewItemFunction("item_event_halloween_coin", utils.getInventoryContainer(player), 15);
            }
            if (mobChance <= 25)
            {
                if (vehicle.isRidingVehicle(player))
                {
                    dismountCreature(player);
                }
                broadcast(player, "An angry worm seems to have been living inside this pumpkin!");
                obj_id wormy = create.createCreature("angry_forage_worm", getLocation(player), true);
                setName(wormy, "an uprooted worm");
                setObjVar(wormy, "loot.lootTable", getRandomLootTable(self));
                setObjVar(wormy, "loot.numItems", wormyDropAmt);
                setScale(wormy, 0.5f);
                showFlyText(wormy, new string_id("*- SLIME -*"), 12.0f, colors.GREEN);
            }
            if (devastationChance <= 5)
            {
                broadcast(player, "You feel sick.");
                buff.applyBuff(player, "acid", 6, 9999);
            }
            if (buffChance == 1)
            {
                if (!buff.hasBuff(player, "burstRun"))
                {
                    broadcast(player, "You feel better after smashing the pumpkin!");
                    buff.applyBuff(player, "burstRun", 10.0f, 20.0f);
                }
            }
            playClientEffectLoc(player, "clienteffect/egg_hatch_01.cef", getLocation(player), 5.5f);
            int currentSmashed = getIntObjVar(player, PULPED_ITER_OBJVAR);
            setObjVar(player, PULPED_ITER_OBJVAR, currentSmashed + 1);
            if (currentSmashed >= 100)
            {
                broadcast(player, "You have smashed \\#00FF00" + currentSmashed + "\\#FFFFFF pumpkins.");
                if (collectionEnabled)
                {
                    if (!hasCompletedCollection(player, COLLECTION_NAME))
                    {
                        modifyCollectionSlotValue(player, COLLECTION_NAME, 1);
                    }
                }
            }
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }

    private String getRandomLootTable(obj_id self)
    {
        int tableIndex = rand(0, LOOT_TABLES.length - 1);
        return LOOT_TABLES[tableIndex];
    }
}
