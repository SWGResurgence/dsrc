package script.theme_park.world_boss;

import script.dictionary;
import script.library.*;
import script.obj_id;
import script.prose_package;
import script.string_id;

public class master_controller_emperors_hand extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    /**
     * public String[] = {
     * };
     **/
    public static final string_id FOUND_JEDI = new string_id("restuss_event/object", "jedi_located");

    public master_controller_emperors_hand()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand");
        setObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand", "Active");
        createTriggerVolume(VOLUME_NAME, 15.0f, true);
        messageTo(self, "setLoiter", null, 10.0f, false);
        resurgence.doWorldBossAnnounce(self, resurgence.WORLD_BOSS_EMPERORS_HAND);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        createTriggerVolume(VOLUME_NAME, 15.0f, true);
        messageTo(self, "setLoiter", null, 10.0f, false);
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
            sendSystemMessageGalaxyTestingOnly("ATTENTION IMPERIAL CITIZENS: The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
        }
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand");
        setObjVar(tatooine, "dungeon_finder.world_boss.emperors_hand", "Inactive");
        sendSystemMessageGalaxyTestingOnly("ATTENTION IMPERIAL CITIZENS: The Hand of his Royal Majesty, The Emperor has been slain, a bounty is now being offered for the capture or murder of " + getName(killer));
        resurgence.doWorldBossDeathMsg(self);
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        prose_package pp = prose.getPackage(FOUND_JEDI);
        pp.target.set(breacher);
        if (utils.isProfession(breacher, utils.FORCE_SENSITIVE))
        {
            if (!ai_lib.isInCombat(self))
            {
                chat.chat(self, breacher, pp);
                addHate(self, breacher, 1000.0f);
                startCombat(self, breacher);
                return SCRIPT_CONTINUE;
            }
            else
            {
                addHate(self, breacher, 500.0f);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int setLoiter(obj_id self, dictionary params) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_LOITER);
        ai_lib.setLoiterRanges(self, 0.0f, 80.0f);
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
        if (percentHealth <= 95)
        {
            chat.chat(self, "I am Aralina Silk, The Hand of His Royal Majesty, The Emperor!");
        }
        if (percentHealth <= 75)
        {
            chat.chat(self, "You Don't Know The Extent Of My Power!");
            for (obj_id who : players)
            {
                broadcast(who, "The Hand has summoned a contingeant of Storm Troopers from the 501st!");
            }
            buff.removeAllBuffs(self);
            resurgence.createCircleSpawn(self, self, "emperors_hand_stormtroopers", 1, 4);
            utils.setScriptVar(self, "hasSpawned", 1);
            return SCRIPT_CONTINUE;
        }
        if (percentHealth <= 50)
        {
            chat.chat(self, "You Will Suffer For Your Cowardace!");
        }
        if (percentHealth <= 20)
        {
            if (!utils.hasScriptVar(self, "hasDisarmed"))
            {
                chat.chat(self, "The Sith Will Not Be Denied!");
                for (obj_id who : players)
                {
                    broadcast(who, "The most recent attack from " + getFirstName(attacker) + " has enraged , causing him to increase her focus. She has summoned additional reinforcements from the Imperial Security Bureau.");
                }
                resurgence.createCircleSpawn(self, self, "emperors_hand_isb_guards", 4, 4);
                utils.setScriptVar(self, "hasDisarmed", 1);
            }
        }
        if (percentHealth <= 10)
        {
            if (!utils.hasScriptVar(self, "feelsDarkSideForce"))
            {
                buff.removeAllBuffs(self);
                for (obj_id who : players)
                {
                    broadcast(who, "Aralina Silk has summoned the Full Power of the Dark Side of the Force. Her attacks are now amplified, and as she summoned the assistance of the Imperial Inquisitors!");
                    chat.chat(self, "The Emperor Has Decreed It... It Shall Be Done!");
                    buff.applyBuff(self, "crystal_buff", 60, 20);
                    resurgence.createCircleSpawn(self, self, "emperors_hand_inquisitors", 1, 4);
                }
            }
            if (percentHealth <= 1)
            {
                if (!utils.hasScriptVar(self, "lastHandMsg"))
                {
                    chat.chat(self, "Long Live The Empire!");
                }
            }
            else
            {
                return SCRIPT_CONTINUE;
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public void stunPlayers(obj_id self, obj_id[] targets) throws InterruptedException
    {
        playClientEffectObj(targets, "clienteffect/cr_bodyfall_huge.cef", self, "");
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            broadcast(iTarget, "The Emperor's Hand attempts to bring you to your knees!");
            chat.chat(self, "Now, Witness The Power of the Dark Side!");
            faceTo(iTarget, self);
        }
    }

    public void bombard(obj_id self, obj_id[] targets) throws InterruptedException
    {
        chat.chat(self, "If you think that your going to defeat me, you won't live to boast about it.");
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            playClientEffectObj(iTarget, "clienteffect/avatar_explosion_02.cef", iTarget, "");
            reduceHealth(iTarget, rand(2500, 5000));
            reduceAction(iTarget, rand(5000, 7500));
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
    /**public int cleanupCorps(obj_id self, dictionary params) throws InterruptedException
     * {
     *     trial.cleanupNpc(self);
     *     return SCRIPT_CONTINUE;
     * }
     **/
}