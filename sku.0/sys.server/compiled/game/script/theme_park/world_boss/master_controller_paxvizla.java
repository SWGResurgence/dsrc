package script.theme_park.world_boss;

import script.library.*;
import script.obj_id;

public class master_controller_paxvizla extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public String[] MAND_MSGS = {
            "Cowards!",
            "You will not escape me!",
            "I will not be defeated!",
            "I will not be stopped!",
            "I will not be denied!",
            "I will not be ignored!",
            "I will not be forgotten!",
            "I will not be defeated!",
            "I am Mand'alor!",
    };
    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been last seen on Dxun at the Abandoned Mandalorian Outpost.");
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
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id wpn, int[] damage) throws InterruptedException
    {
        obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
        int health = getHealth(self);
        int maxHealth = getMaxHealth(self);
        int percentHealth = (health * 100) / maxHealth;
        if (attacker == self) //this is a self damage check
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "chirp"))
        {
            chat.chat(self, MAND_MSGS[rand(0, MAND_MSGS.length - 1)]);
            utils.setScriptVar(self, "chirp", 1);
        }
        if (percentHealth <= 75)
        {
            if (!utils.hasScriptVar(self, "hasSpawned"))
            {
                chat.chat(self, "I will not be challenged in such uncivil ways!");
                for (obj_id who : players)
                {
                    broadcast(who, "Pax has lost his enhancements. Strike now!");
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
                chat.chat(self, MAND_MSGS[rand(0, MAND_MSGS.length - 1)]);
                chat.chat(self, "Eat Durasteel!");
                bombard(self, players);
                utils.setScriptVar(self, "hasBeenBombed", 1);
            }
        }
        if (percentHealth <= 20)
        {
            if (!utils.hasScriptVar(self, "hasDisarmed"))
            {
                chat.chat(self, MAND_MSGS[rand(0, MAND_MSGS.length - 1)]);
                for (obj_id who : players)
                {
                    broadcast(who, "The most recent attack from " + getFirstName(attacker) +  " has enraged Pax Vizla, causing him to increase his focus.");
                }
                utils.setScriptVar(self, "hasDisarmed", 1);
            }
        }
        if (percentHealth <= 8)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                buff.removeAllBuffs(self);
                for (obj_id who : players)
                {
                    broadcast(who, "Pax Vizla has entered his last stand!");
                }
                chat.chat(self, "This Is The Way.");
                buff.applyBuff(self, "crystal_buff", 30, 10);
                utils.setScriptVar(self, "hasLastStand", 1);
            }
        }
        if (percentHealth <= 1)
        {
            if (!utils.hasScriptVar(self, "lastMandMsg"))
            {
                chat.chat(self, MAND_MSGS[(MAND_MSGS.length - 1)]);
                utils.setScriptVar(self, "lastMandMsg", 1);
            }
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
        return  SCRIPT_CONTINUE;
    }
    public void stunPlayers(obj_id self, obj_id[] targets) throws InterruptedException
    {
        playClientEffectObj(targets, "clienteffect/cr_bodyfall_huge.cef", self, "");
        if (targets == null || targets.length == 0)
        {
            return;
        }
        for (obj_id iTarget : targets)
        {
            sendConsoleCommand("/kneel", iTarget);
            broadcast(iTarget, "Pax Vizla brings you to your knees!");
            chat.chat(self, "Now, witness the power of a TRUE Mandalorian!");
            faceTo(iTarget, self);
        }
    }
    public void bombard (obj_id self, obj_id[] targets) throws InterruptedException
    {
        if (targets == null || targets.length == 0)
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
