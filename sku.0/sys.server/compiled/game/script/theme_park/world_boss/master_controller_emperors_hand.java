package script.theme_park.world_boss;

import script.library.*;

import script.location;
import script.obj_id;

public class master_controller_emperors_hand extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    public String[] EMPERORS_HAND_MSGS = {
            "It is necessary to lie to achieve anything of value. And a skilled liar is nearly impossible to detect!",
            "You are a wretched thing, of weakness and fear!",
            "I am not a woman of words. But I respect the power of words, for that is what transformed me. The words of the Sith Code...",
            "Evil is a word used by the ignorant and the weak. The Dark Side is about survival. It's about unleashing your inner power. It glorifies the strength of the individual!",
            "We take what we desire because we can. We can because we have power. We have power because we are Sith!",
            "True power can come only to those who embrace the transformation!",
            "Be careful you don't choke on your aspirations!",
            "Darkness is a friend, an ally.",
            "Fear attracts the fearful... the strong... the weak... the innocent... the corrupt. Fear. Fear is the ally I need to defeat you!",
            "The strong rule; the weak are meant only to serve us. This is the way it must be!",
            "You will suffer for your lack of vision!",
    };
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
        if (!utils.hasScriptVar(self, "randomMsg"))
        {
            chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
            utils.setScriptVar(self,"randomMsg", 1);
        }
        if (percentHealth <= 100)
        {
            if (!utils.hasScriptVar(self, "handHasSpawned"))
            {
                chat.chat(self, "I am Aralina Silk, The Hand of his Royal Majesty, The Emperor! And... it is time for you to meet your end!");
                utils.setScriptVar(self,"handHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 90)
        {
            if (!utils.hasScriptVar(self, "handMedBuffs"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                buff.removeAllBuffs(self);
                for (obj_id who : players)
                {
                    broadcast(who, "Aralina Silk has summoned the use of Medical Buffs through the Darkside of the Force!");
                }
                buff.applyBuff((self), "me_buff_health_2", 120);
                buff.applyBuff((self), "me_buff_action_3", 120);
                buff.applyBuff((self), "me_buff_strength_3", 120);
                buff.applyBuff((self), "me_buff_agility_3", 120);
                buff.applyBuff((self), "me_buff_precision_3", 120);
                buff.applyBuff((self), "me_buff_melee_gb_1", 120);
                buff.applyBuff((self), "me_buff_ranged_gb_1", 120);
                utils.setScriptVar(self, "handMedBuffs", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 75)
        {
            if (!utils.hasScriptVar(self, "stormtroopersHasSpawned"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the 501st Stormtrooper Detachment!");
                }
                resurgence.createCircleSpawn(self, self, "emperors_hand_stormtroopers", 12, 2);
                utils.setScriptVar(self, "stormtroopersHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 50)
        {
            if (!utils.hasScriptVar(self, "agentsHasSpawned"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the Imperial Security Bureau!");
                }
                resurgence.createCircleSpawn(self, self, "emperors_hand_isb_guards", 6, 2);
                utils.setScriptVar(self, "agentsHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 25)
        {
            if (!utils.hasScriptVar(self, "inquisitorsHasSpawned"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned reinforcements from the Imperial Inquisitors!");
                }
                resurgence.createCircleSpawn(self, self,"emperors_hand_inquisitors", 3, 2);
                utils.setScriptVar(self, "inquisitorsHasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 20)
        {
            if (!utils.hasScriptVar(self, "officerBuffs"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned Officer Buffs from the Darkside of the Force!");
                }
                buff.applyBuff(self, "of_buff_def_9", 120, 100);
                buff.applyBuff(self, "of_focus_fire_6", 120, 100);
                buff.applyBuff(self, "of_drillmaster_1", 120, 100);
                utils.setScriptVar(self, "officerBuffs", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 15)
        {
            if (!utils.hasScriptVar(self, "hasShields"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperors Hand has summoned additional defenses through the Darkside of the Force!");
                }
                buff.applyBuff(self, "ig88_shield", 120, 100);
                utils.setScriptVar(self, "hasShields", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 10)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The Emperor's Hand goes into her Last Stand, triggering her last defenses!");
                }
                buff.applyBuff(self, "crystal_buff", 75, 40);
                utils.setScriptVar(self, "hasLastStand", 1);
            }
        }
        if (percentHealth <= 6)
        {
            if (!utils.hasScriptVar(self, "anotherMsg"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                utils.setScriptVar(self, "anotherMsg", 1);
            }
        }
        if (percentHealth <= 2)
        {
            if (!utils.hasScriptVar(self, "hasBeenBombed"))
            {
                chat.chat(self, EMPERORS_HAND_MSGS[rand(0, EMPERORS_HAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The most recent attack from " + getFirstName(attacker) + " has enraged The Emperor's Hand, causing her to increase and call down an Imperial Bombing Run on her own position!");
                }
                bombard(self, players);
                utils.setScriptVar(self, "hasBeenBombed", 1);
                return SCRIPT_CONTINUE;
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
