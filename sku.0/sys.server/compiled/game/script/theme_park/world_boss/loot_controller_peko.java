package script.theme_park.world_boss;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

public class loot_controller_peko extends script.base_script
{
    public static final String VOLUME_NAME = "aggressive_area";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress, her nesting site has been reported to have last been  on Naboo. Czerka Corporation is paying for it's remains.");
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (pet_lib.isPet(killer))
        {
            sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
        }
        sendSystemMessageGalaxyTestingOnly("ATTENTION GALACTIC BOUNTY HUNTERS: The Abomination, The Mutated Peko-Peko Empress has been reported to have been destroyed and the Czerka Corporation has paid out the bounty to " + getName(killer));
        return SCRIPT_CONTINUE;
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        createMyLoot(self);
        return SCRIPT_CONTINUE;
    }

    public void createMyLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null)
        {
            return;
        }
        int x = rand(1, 100);
        if (x < 91)
        { // 90% Drop Rate: TCG - Ball of Peace
            static_item.createNewItemFunction("item_tcg_loot_reward_series4_ball_of_peace_02_01", corpseInventory);
        }
        if (x < 26)
        { // 25% Drop Rate: TCG - Toxic Peko-Peko Mount
            static_item.createNewItemFunction("item_tcg_loot_reward_series4_peko_peko_mount_02_01", corpseInventory);
        }
        /*String myLoot1 = "";
        createObject(myLoot1, corpseInventory, "");
        String myLoot2 = "";
        createObject(myLoot2, corpseInventory, "");*/
        return;
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
                obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
                staggerPlayers(self, players);
                utils.setScriptVar(self, "hasKnockedBack", 1);
                return SCRIPT_CONTINUE;
            }
        }
        if (percentHealth <= 5)
        {
            if (!utils.hasScriptVar(self, "hasLastStand"))
            {
                obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
                resurgence.createCircleSpawn(self, self, "peko_peko", 6, 24);
                createObject("peko_peko_albatross_high", getLocation(self));
                staggerPlayers(self, players);
                for (obj_id who : players)
                {
                    broadcast(who, "The Mutated Peko-Peko Empress has called upon her whelps to aid her in her final stand!");
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void staggerPlayers(obj_id self, obj_id[] targets) throws InterruptedException
    {
        location stagger = null;
        location slapLoc = getLocation(self);
        stagger.x = slapLoc.x + rand(-64, 64);
        stagger.z = slapLoc.z + rand(-64, 64);
        stagger.y = getHeightAtLocation(slapLoc.x, slapLoc.z);
        stagger.area = getCurrentSceneName();
        for (obj_id iTarget : targets)
        {
            warpPlayer(iTarget, slapLoc.area, slapLoc.x, slapLoc.y, slapLoc.z, null, 0, 0, 0);
            broadcast(iTarget, "The wind from the Peko-Peko's wings knocks you back!");
            setYaw(self, rand(0.0f,180.0f));
        }

    }
}
