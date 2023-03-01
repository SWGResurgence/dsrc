package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Commando grenade droid. summons a grenade droid that will follow you and throw grenades at your enemies.
@   This script has 2 states, One when inside your inventory, one when outisde and active.
*/

import script.*;
import script.library.utils;
import script.obj_id;
import script.library.sui;

import static script.library.combat.isInCombat;

public class bf2_ammo extends script.base_script
{
    public String BOMB_DROID_PROMPT = "placeholder";
    public String BOMB_DROID_HANDLER = "setupGonk";
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (hasObjVar(self, "loaded"))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Use"));
        }
        else
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("Setup"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int mi) throws InterruptedException
    {
        if (mi == menu_info_types.SERVER_MENU1)
        {
            sui.msgbox(self, player, BOMB_DROID_PROMPT, BOMB_DROID_HANDLER);
            setObjVar(self, "owner", player);
            return SCRIPT_CONTINUE;
        }
        if (mi == menu_info_types.SERVER_MENU2)
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int setupGonk(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_REVERT)
        {
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_OK)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id gonkie = createObject("object/mobile/eg6_power_droid.iff", getLocation(self));
        attachScript(gonkie, "script.developer.bubbajoe.bf2_ammo");
        setRandomColor(gonkie, params);
        obj_id[] players = getPlayerCreaturesInRange(getLocation(self), 100.0f);
        for (obj_id single : players)
        {
            broadcast(single, "An unstable EG-6 Power Droid has been activated.");
        }
        return SCRIPT_CONTINUE;
    }
    public int setRandomColor(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_REVERT)
        {
            return SCRIPT_CONTINUE;
        }
        if (bp == sui.BP_OK)
        {
            return SCRIPT_CONTINUE;
        }
        int gonkR = rand(0, 255);
        int gonkG = rand(0, 255);
        int gonkB = rand(0, 255);
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", gonkR, gonkG, gonkB, 1);
        setPalcolorCustomVarClosestColor(self, "/private/index_color_2", gonkR, gonkG, gonkB, 1);
        return SCRIPT_CONTINUE;
    }
    public int goBoom(obj_id self) throws InterruptedException
    {
        if (!isInCombat(getObjIdObjVar(self, "owner")))
        {
            destroyObject(self);
            return SCRIPT_OVERRIDE;
        }
        obj_id wod =  createObject("object/weapon/ranged/grenade/grenade_cryoban.iff", getLocation(self));
        int cooldown = rand(30, 45);
        obj_id[] creatures = getCreaturesInRange(getLocation(self), 64.0f);
        obj_id target = creatures[rand(0, creatures.length - 1)];
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
        if (getHeldWeapon(target) == null)
        {
            equip(wod, utils.getInventoryContainer(self));
        }
        doDamage(self, target, wod, cbtHitData);
        messageTo(self, "goBoom", null, cooldown, true);
        broadcast(getObjIdObjVar(self, "owner"), "The EG-6 Power Droid has thrown a grenade at " + getName(target) + "!");
        return SCRIPT_CONTINUE;
    }
}
