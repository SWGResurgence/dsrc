package script.theme_park.world_boss;/*
@Filename: script.library.theme_park.world_boss
@Author: BubbaJoeX
@Purpose: Donk-Donk Binks World Boss
*/

import script.library.*;
import script.obj_id;

public class master_controller_donkdonk_binks extends script.base_script
{
    public String[] GUNGAN_MSGS = {
            "Yous no escape me!",
            "Yous mula be moole!",
            "Yousa think yousa so bombad!",
            "Yous weapons will adden nicely to meesa collection!",
            "Me warned yousa!",
            "Ya-hoo yousa, stopen dat!",
            "Dat's it!",
            "No maken mesa usen a booma!",
            "Stopen da doo-doo, yousa cannot defeat mesa in combat!",
    };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks", "Active");
        resurgence.doWorldBossAnnounce(self, resurgence.WORLD_BOSS_DONKDONK);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.donkdonk_binks", "Inactive");
        resurgence.doWorldBossDeathMsg(self);
        return SCRIPT_CONTINUE;
    }

    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id wpn, int[] damage) throws InterruptedException
    {
        obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
        int health = getHealth(self);
        int maxHealth = getMaxHealth(self);
        int percentHealth = (health * 100) / maxHealth;
        if (attacker == self)
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "speaken"))
        {
            chat.chat(self, GUNGAN_MSGS[rand(0, GUNGAN_MSGS.length - 1)]);
            utils.setScriptVar(self, "speaken", 1);
        }
        if (percentHealth <= 75)
        {
            if (!utils.hasScriptVar(self, "hasSpawned"))
            {
                chat.chat(self, "Okie dey, you asken for it!");
                for (obj_id who : players)
                {
                    broadcast(who, "Donk-Donk Binks has called for reinforcements!");
                }
                create.object("donkdonk_binks_add", getLocation(self));
                utils.setScriptVar(self, "hasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 50)
        {
            if (!utils.hasScriptVar(self, "hasBeenBoomad"))
            {
                chat.chat(self, GUNGAN_MSGS[rand(0, GUNGAN_MSGS.length - 1)]);
                chat.chat(self, "Whoopsies! Meesa slipped!");
                booma(self, players);
                utils.setScriptVar(self, "hasBeenBoomad", 1);
            }
        }
        if (percentHealth <= 20)
        {
            if (!utils.hasScriptVar(self, "hasDruggedPlayers"))
            {
                chat.chat(self, GUNGAN_MSGS[rand(0, GUNGAN_MSGS.length - 1)]);
                chat.chat(self, "Meesa gonna make yousa feel verrry sleepy!");
                drugPlayers(self, players);
                utils.setScriptVar(self, "hasDruggedPlayers", 1);
            }
        }
        if (percentHealth <= 8)
        {
            if (!utils.hasScriptVar(self, "hasLastBoomad"))
            {
                chat.chat(self, "Meesa gonna make yousa go BOOM!");
                booma(self, players);
                utils.setScriptVar(self, "hasLastBoomad", 1);
            }
        }
        if (percentHealth <= 1)
        {
            if (!utils.hasScriptVar(self, "lastMsg"))
            {
                chat.chat(self, "If only me didn't liven a life of bombad crime...");
                utils.setScriptVar(self, "lastMsg", 1);
            }
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public void drugPlayers(obj_id self, obj_id[] targets) throws InterruptedException
    {
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            sendConsoleCommand("prone", iTarget);
            broadcast(iTarget, "You have been poisoned by Donk-Donk Binks!");
            buff.applyBuff(iTarget, "me_stasis_1", 15, 5);
            faceTo(iTarget, self);
        }
        chat.chat(self, "Yousa gonna sleep now!");
    }

    public void booma(obj_id self, obj_id[] targets) throws InterruptedException
    {
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            playClientEffectObj(iTarget, "clienteffect/combat_grenade_cryoban.cef", iTarget, "head");
            playClientEffectObj(iTarget, "clienteffect/combat_grenade_cryoban.cef", iTarget, "head");
            playClientEffectObj(iTarget, "clienteffect/combat_grenade_cryoban.cef", iTarget, "head");
            reduceHealth(iTarget, rand(900, 5000));
            reduceAction(iTarget, rand(900, 5000));
        }
    }

    public boolean reduceHealth(obj_id player, int amt)
    {
        return setHealth(player, (getHealth(player) - amt));
    }

    public boolean reduceAction(obj_id player, int amt)
    {
        return setAction(player, (getAction(player) - amt));
    }
}
