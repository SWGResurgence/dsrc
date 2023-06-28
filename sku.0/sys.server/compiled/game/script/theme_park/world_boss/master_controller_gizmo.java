package script.theme_park.world_boss;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.*;
import script.obj_id;

public class master_controller_gizmo extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";
    public String[] EWOK_MSGS = {
            "Wermoh!",
            "Meechoo oody danvay yor rota!",
            "Meechoo Oody dent bee defedad!",
            "Meechoo Oody dent bee staapt!",
            "Meechoo Oody dent bee dinaid",
            "Meechoo Oody dent bee ignod!",
            "Meechoo Oody dent bee fegatn!",
            "Meechoo Oody dent bee defedad!",
            "Meechoo aem ta troo Pubbza doh ta Tyrzok!",
    };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.gizmo"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.gizmo");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.gizmo", "Active");
        resurgence.doWorldBossAnnounce(self, resurgence.WORLD_BOSS_GIZMO);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (isGod(killer))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "dungeon_finder.world_boss.gizmo"))
        {
            removeObjVar(tatooine, "dungeon_finder.world_boss.gizmo");
        }
        setObjVar(tatooine, "dungeon_finder.world_boss.gizmo", "Inactive");
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
        if (!utils.hasScriptVar(self, "chirp"))
        {
            chat.chat(self, EWOK_MSGS[rand(0, EWOK_MSGS.length - 1)]);
            utils.setScriptVar(self, "chirp", 1);
        }
        if (percentHealth <= 75)
        {
            if (!utils.hasScriptVar(self, "hasSpawned"))
            {
                chat.chat(self, "Sta gata ta Rata!");
                for (obj_id who : players)
                {
                    broadcast(who, "Darth Gizmo has lost his enhancements. The Living Force says the time to strike is now!");
                }
                buff.removeAllBuffs(self);
                utils.setScriptVar(self, "hasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 50)
        {
            if (!utils.hasScriptVar(self, "hasBeenBombed"))
            {
                chat.chat(self, EWOK_MSGS[rand(0, EWOK_MSGS.length - 1)]);
                bombard(self, players);
                utils.setScriptVar(self, "hasBeenBombed", 1);
            }
        }
        if (percentHealth <= 25)
        {
            if (!utils.hasScriptVar(self, "hasDisarmed"))
            {
                chat.chat(self, EWOK_MSGS[rand(0, EWOK_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The most recent attack by " + getFirstName(attacker) + " has enraged Darth Gizmo.");
                }
                utils.setScriptVar(self, "hasDisarmed", 1);
            }
        }
        if (percentHealth <= 10)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                buff.removeAllBuffs(self);
                for (obj_id who : players)
                {
                    broadcast(who, "Darth Gizmo has entered his last stand, calling upon the full power of the Darkside of the Force!");
                }
                buff.applyBuff(self, "jediForce_11", 60, 20);
                utils.setScriptVar(self, "hasLastStand", 1);
            }
        }
        if (percentHealth <= 1)
        {
            if (!utils.hasScriptVar(self, "hasLastStandMsg"))
            {
                chat.chat(self, EWOK_MSGS[rand(0, EWOK_MSGS.length - 1)]);
                utils.setScriptVar(self, "hasLastStandMsg", 1);
            }
        }
        else
        {
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
            sendConsoleCommand("/kneel", iTarget);
            broadcast(iTarget, "The Darkside of the Force Brings You to Your Knees!");
            chat.chat(self, "Sta, witness ta true paamuk dob ta daarkswayed!");
            faceTo(iTarget, self);
        }
    }

    public void bombard(obj_id self, obj_id[] targets) throws InterruptedException
    {
        if (targets == null)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            playClientEffectObj(iTarget, "clienteffect/avatar_explosion_02.cef", self, "");
            reduceHealth(iTarget, rand(1200, 3000));
            reduceAction(iTarget, rand(1200, 3000));
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