package script.event.halloween;

import script.dictionary;
import script.library.*;
import script.menu_info;
import script.obj_id;

public class treat_thief extends script.base_script {
    public treat_thief() {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnCombatEntered(obj_id self, obj_id attacker, obj_id[] attackers, dictionary params) throws InterruptedException
    {
        sendSystemMessageGalaxyTestingOnly("[World Event] The Treat Thief has been spotted on " + toUpper(getCurrentSceneName(), 1));
        obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
        if (players == null || players.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        for (obj_id player : players) {
            addHate(self, player, 1);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage) throws InterruptedException
    {
        if (beast_lib.isBeast(attacker) || pet_lib.isPet(attacker))
        {
            attacker = getMaster(attacker);
        }
        if (!isPlayer(attacker))
        {
            return SCRIPT_CONTINUE;
        }
        float max = getMaxHealth(self);
        float current = getHealth(self);
        float ratio = current / max;
        if (ratio <= 0.9f)
        {
            if (ratio <= 0.75f)
            {
                if (ratio <= 0.6f)
                {
                    if (ratio <= 0.45f)
                    {
                        if (ratio <= 0.35f)
                        {
                            if (ratio <= 0.3f)
                            {
                                if (!buff.hasBuff(self, "event_halloween_treat_thief"))
                                {
                                    buff.applyBuff(self, "event_halloween_treat_thief", -1.0f);
                                }
                            }
                            spawnSidekick(self, "event_halloween_treat_thief_summon","frogBuff", 1.0f);
                            return SCRIPT_CONTINUE;
                        }
                        chat.chat(self, "You're not getting away with this" + getName(attacker));
                        spawnSidekick(self, "event_halloween_treat_thief_summon", "frogBuff", 1.0f);
                        return SCRIPT_CONTINUE;
                    }
                    chat.chat(self, "Finally... I've been waiting for you " + getName(attacker));
                    resurgence.createCircleSpawn(self, attacker,  "nuna", 20, 10.0f );
                    return SCRIPT_CONTINUE;
                }
                chat.chat(self, "Haha! I have your credits, " + getName(attacker) + "!");
                broadcast(attacker, "You have been robbed!");
                if (money.hasFunds(attacker, money.MT_TOTAL, 1000)) {
                    money.withdraw(attacker, 1000);
                }
                resurgence.pushPlayer(self, attacker,  -10.0f, 45.0f);
                return SCRIPT_CONTINUE;
            }
            chat.chat(self, "Help me minions. My precious treats are being stolen!");
            resurgence.createCircleSpawn(self, self,  "event_halloween_treat_thief_summon", 6, 5.0f );
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnSidekick(obj_id self, String creature, String buffName, float scale) throws InterruptedException {
        obj_id sidekick = create.object(creature, getLocation(self));
        setScale(sidekick, scale);
        buff.applyBuff(sidekick, buffName, -1.0f);
        attachScript(sidekick, "event_halloween_treat_thief");
        chat.chat(sidekick, "Utinni!");
    }
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        int lootChanc = rand(1, 100);
        if (lootChanc <= 50)
        {
            obj_id loot = createObject("object/tangible/loot/creature_loot/collections/shared_treasure_map_01.iff", utils.getInventoryContainer(self), "");
            setName(loot, "Treasure Map");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
