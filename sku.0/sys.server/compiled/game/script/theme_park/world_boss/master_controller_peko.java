package script.theme_park.world_boss;

import script.library.*;
import script.location;
import script.obj_id;

public class master_controller_peko extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";


    public String[] SQUAWK_MSGS = {
            "<LOUDS AVIAN NOISES>",
            "<ANGRY AVIAN NOISES>",
            "<UPSET AVIAN NOISES>",
    };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress, her nesting site has been reported to have last been  on Naboo. Czerka Corporation is paying for it's remains.");
        return SCRIPT_CONTINUE;
    }

    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId) throws InterruptedException
    {
        obj_id[] allPlayersNearby = getAllPlayers(getLocation(self), 128.0f);
        if (allPlayersNearby != null && allPlayersNearby.length > 0)
        {
            for (obj_id allPlayersNearby1 : allPlayersNearby)
            {
                groundquests.sendSignal(allPlayersNearby1, "collectedPekoPekoAlbatross");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        if (beast_lib.isBeast(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerName(beast_lib.getMaster(killer)));
        }
        if (!isPlayer(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerName(getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }

    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id wpn, int[] damage) throws InterruptedException
    {
        int health = getHealth(self);
        int maxHealth = getMaxHealth(self);
        int percentHealth = (health * 100) / maxHealth;
        if (attacker == self) //this is a self damage check
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "chirp"))
        {
            chat.chat(self, SQUAWK_MSGS[rand(0, SQUAWK_MSGS.length - 1)]);
            utils.setScriptVar(self, "chirp", 1);
        }
        if (percentHealth <= 50)
        {
            if (!utils.hasScriptVar(self, "hasSpawned"))
            {
                resurgence.createCircleSpawn(self, self, "peko_peko", 12, 24);
                utils.setScriptVar(self, "hasSpawned", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 20)
        {
            if (!utils.hasScriptVar(self, "hasKnockedBack"))
            {
                chat.chat(self, "<ANNOYED AVIAN NOISES>");
                obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
                staggerPlayers(self, players);
                utils.setScriptVar(self, "hasKnockedBack", 1);
            }
        }
        if (percentHealth <= 5)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
                resurgence.createCircleSpawn(self, self, "peko_peko_albatross_high", 2, 24);
                obj_id[] creatures = getCreaturesInRange(self, 64.0f);
                for (obj_id creature : creatures) {
                    combat.startCombat(creature, players[rand(0, players.length - 1)]);
                    chat.chat(creature, "<Indicates that " + getName(self) + " is the true Empress of the Peko-Peko>");
                }
                staggerPlayers(self, players);
                for (obj_id who : players)
                {
                    broadcast(who, "The Mutated Peko-Peko Empress has called upon her whelps to aid her in her final stand!");
                }
                utils.setScriptVar(self, "hasLastStand", 1);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void staggerPlayers(obj_id self, obj_id[] targets) throws InterruptedException
    {
        location stagger = getLocation(self);
        stagger.x = stagger.x + rand(-64.0f, 64.0f);
        stagger.z = stagger.z + rand(-64.0f, 64.0f);
        stagger.y = getHeightAtLocation(stagger.x, stagger.z);
        stagger.area = getCurrentSceneName();
        for (obj_id iTarget : targets)
        {
            warpPlayer(iTarget, stagger.area, stagger.x, stagger.y, stagger.z, null, 0, 0, 0);
            broadcast(iTarget, "The wind from the Mutated Peko-Peko's wings knocked you back!");
            setYaw(self, rand(0.0f, 180.0f));
        }

    }
}
