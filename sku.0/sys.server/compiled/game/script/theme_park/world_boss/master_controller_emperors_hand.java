package script.theme_park.world_boss;

import script.library.*;

import script.location;
import script.obj_id;

public class master_controller_emperors_hand extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand", "Active");
        resurgence.doWorldBossAnnounce(self, resurgence.WORLD_BOSS_EMPERORS_HAND);
        return SCRIPT_CONTINUE;
    }
    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId) throws InterruptedException
    {
        obj_id[] allPlayersNearby = getAllPlayers(getLocation(self), 128.0f);
        if (allPlayersNearby != null)
        {
            for (obj_id nearby : allPlayersNearby)
            {
                groundquests.sendSignal(nearby, "completedWorldBossNabooEmperorsHand");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (isGod(killer))
        {
            return SCRIPT_CONTINUE;
        }
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: Aralina Silk, The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
        }
        if (beast_lib.isBeast(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: Aralina Silk, The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
        }
        if (!isPlayer(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: Aralina Silk, The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
        }
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand", "Inactive");
        resurgence.doWorldBossDeathMsg(self);
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: Aralina Silk, The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
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
        if (percentHealth <= 100)
        {
            if (!utils.hasScriptVar(self, "handHasSpawned"))
            {
                chat.chat(self, "I am Aralina Silk, The Hand of his Royal Majesty, The Emperor!");
            }
        }
        if (percentHealth <= 90)
        {
            if (!utils.hasScriptVar(self, "handMedBuffs"))
            {
                buff.removeAllBuffs(self);
                for (obj_id who : players)
                {
                    broadcast(who, "Aralina Silk has summoned the use of Medical Buffs through the Darkside of the Force!");
                }
                chat.chat(self, "You will fail!");
                buff.applyBuff((self), "me_buff_health_2", 300);
                buff.applyBuff((self), "me_buff_action_3", 300);
                buff.applyBuff((self), "me_buff_strength_3", 300);
                buff.applyBuff((self), "me_buff_agility_3", 300);
                buff.applyBuff((self), "me_buff_precision_3", 300);
                buff.applyBuff((self), "me_buff_melee_gb_1", 300);
                buff.applyBuff((self), "me_buff_ranged_gb_1", 300);
                utils.setScriptVar(self, "handMedBuffs", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 75)
        {
            if (!utils.hasScriptVar(self, "stormtroopersHasSpawned"))
            {
                chat.chat(self, "You will suffer for your lack of vision!");
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the 501st Stormtrooper Detachment!");
                }
                resurgence.createCircleSpawn(self, self, "emperors_hand_stormtroopers", 8, 5);
                utils.setScriptVar(self, "stormtroopersHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 50)
        {
            if (!utils.hasScriptVar(self, "agentsHasSpawned"))
            {
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the Imperial Security Bureau!");
                }
                resurgence.createCircleSpawn(self, self, "emperors_hand_isb_guards", 4, 5);
                utils.setScriptVar(self, "agentsHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 25)
        {
            if (!utils.hasScriptVar(self, "inquisitorsHasSpawned"))
            {
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the Imperial Inquisitors!");
                }
                resurgence.createCircleSpawn(self, self,"emperors_hand_inquisitors", 2, 5);
                utils.setScriptVar(self, "inquisitorsHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 15)
        {
            if (!utils.hasScriptVar(self, "ig88Buff"))
            {
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned additional defenses through the Darkside of the Force!");
                }
                buff.applyBuff(self, "ig88_shield", 30, 100);
                utils.setScriptVar(self, "ig88Buff", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 8)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperor's Hand goes into her Last Stand, triggering her last defenses!");
                }
                chat.chat(self, "This Is The Way.");
                buff.applyBuff(self, "crystal_buff", 30, 20);
                utils.setScriptVar(self, "hasLastStand", 1);
            }
        }
        if (percentHealth <= 2)
        {
            if (!utils.hasScriptVar(self, "hasBeenBombed"))
            {
                for (obj_id who : players)
                {
                    broadcast(who, "The most recent attack from " + getFirstName(attacker) + " has enraged The Emperor's Hand, causing her to increase and call down an Imperial Bombing Run on her own position!");
                }
                bombard(self, players);
                utils.setScriptVar(self, "hasBeenBombed", 1);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void bombard(obj_id self, obj_id[] targets) throws InterruptedException
    {
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            playClientEffectObj(iTarget, "clienteffect/avatar_explosion_02.cef", iTarget, "");
            reduceHealth(iTarget, rand(5000, 7500));
            reduceAction(iTarget, rand(2500, 5000));
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