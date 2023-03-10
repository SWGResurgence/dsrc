package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Commando grenade droid. Battlefield 2.0 reward.
*/

import script.ai.ai;
import script.*;
import script.library.*;

import java.util.ArrayList;

public class bf2_ammo extends script.base_script
{
    public String BOMB_DROID_PROMPT = "Processing request...";
    public String BOMB_DROID_HANDLER = "setupGonk";

    public int OnAttach(obj_id self)
    {
        setDescriptionStringId(self, new string_id("This EG-6 grenade droid is a prototype model. It is designed to follow its owner and lob grenades at it's master's enemies."));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Arm Bomb Droid"));
        mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("Lock Target"));
        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Dismiss"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int mi) throws InterruptedException
    {
        if (mi == menu_info_types.ITEM_USE)
        {
            if (!hasObjVar(self, "loaded"))
            {
                setLevel(self, 85);
                setOwner(self, player);
                ai.follow(self, player, 0, 2);
                setMovementRun(self);
                setMovementPercent(self, 20);
                setRandomColor(self);
                setHealth(self, 25000);
                goBoom(self);
            }
            else
            {
                sendSystemMessage(player, "This bomb droid is already active.", null);
            }
        }
        if (mi == menu_info_types.SERVER_MENU1)
        {
            destroyObject(self);
            broadcast(player, "You have dismissed an Experimental EG-6 Grenadier Droid.");
        }
        if (mi == menu_info_types.SERVER_MENU2)
        {
            ArrayList<obj_id> targets = new ArrayList<obj_id>();
            obj_id[] players = getCreaturesInRange(getLocation(self), 64.0f);
            for (obj_id single : players)
            {
                if (single != player)
                {
                    targets.add(single);
                }
            }
            if (targets.size() > 0)
            {
                String[] targetNames = new String[targets.size()];
                for (int i = 0; i < targets.size(); i++)
                {
                    targetNames[i] = getName(targets.get(i));
                }
                sui.listbox(self, player, "Select a target to lock onto.", sui.OK_CANCEL, "Lock Target", targetNames, "handleTargetLock", true, false);
            }
            else
            {
                sendSystemMessage(player, "There are no targets to lock onto.", null);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int setRandomColor(obj_id self) throws InterruptedException
    {
        int gonkR = rand(0, 255);
        int gonkG = rand(0, 255);
        int gonkB = rand(0, 255);
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", gonkR, gonkG, gonkB, 0);
        setPalcolorCustomVarClosestColor(self, "/private/index_color_2", gonkR, gonkG, gonkB, 0);
        return SCRIPT_CONTINUE;
    }

    public int goBoom(obj_id self) throws InterruptedException
    {
        obj_id wod = createObject("object/weapon/ranged/grenade/grenade_cryoban.iff", getLocation(self));
        int cooldown = rand(15, 25);
        obj_id[] creatures = getCreaturesInRange(getLocation(self), 64.0f);
        if (creatures == null || creatures.length == 0)
        {
            showFlyText(self, new string_id("- NO POINTER ON TARGETING MATRIX -"), 2.5f, colors.RED);
            return SCRIPT_CONTINUE;
        }
        obj_id target = creatures[rand(0, creatures.length - 1)];
        if (target == self)
        {
            showFlyText(self, new string_id("- NO POINTER ON TARGETING MATRIX -"), 2.5f, colors.RED);
        }
        combat.startCombat(self, target);
        int damage = getMaxHealth(target) + getHealth(target) - (rand(0, 1000));
        playClientEffectLoc(target, "clienteffect/avatar_explosion_01.cef", getLocation(target), 5.0f);
        playClientEffectLoc(target, "clienteffect/avatar_explosion_02.cef", getLocation(target), 2.0f);
        combat_engine.hit_result cbtHitData = new combat_engine.hit_result();
        cbtHitData.success = true;
        cbtHitData.baseRoll = 50;
        cbtHitData.finalRoll = 100;
        cbtHitData.hitLocation = rand(0, 5);
        cbtHitData.canSee = true;
        cbtHitData.damage = damage;
        cbtHitData.bleedingChance = 0;
        if (target == null)
        {
            showFlyText(self, new string_id("- NO TARGET -"), 1.5f, colors.RED);
            return SCRIPT_CONTINUE;
        }
        if (getHeldWeapon(self) == null)
        {
            showFlyText(self, new string_id("-  CYCLING GRENADES -"), 1.5f, colors.ORANGE);
            equip(wod, utils.getInventoryContainer(self));
        }
        doDamage(self, target, wod, cbtHitData);
        showFlyText(self, new string_id("- LAUNCHING GRENADE -"), 1.5f, colors.YELLOW);
        broadcast(getObjIdObjVar(self, "owner"), "The EG-6 Power Droid has thrown a grenade at " + getName(target) + "!");
        messageTo(self, "goBoom", null, cooldown, true);
        return SCRIPT_CONTINUE;
    }
}
