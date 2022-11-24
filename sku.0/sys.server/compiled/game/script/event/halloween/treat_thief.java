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
        broadcast(self, "I AM HEREEEEE!");
        obj_id[] players = getPlayerCreaturesInRange(self, 64.0f);
        if (players == null || players.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        for (obj_id player : players) {
            addHate(self, player, 1);
        }
        messageTo(self, "doTaunt", params, 120.0f, true);
        messageTo(self, "doTauntSecondary", params, 360.0f, true);
        return SCRIPT_CONTINUE;
    }
    public void doTaunt(obj_id self, obj_id[] target, int damageModifier)
    {
        //do some cool attacks here
    }
    public void doTauntSecondary(obj_id self, obj_id[] targets)
    {
        //(possibly) do  some cool attacks here
    }
    public void handleLoot(obj_id self, obj_id player) throws InterruptedException {
        broadcast(player, "Oh no! You've been robbed!");
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage) throws InterruptedException
    {
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
                            spawnSidekick(self, "tusken_raider", "acid", 1.0f);
                            return SCRIPT_CONTINUE;
                        }
                        spawnSidekick(self, "tusken_raider", "acid", 1.0f);
                        return SCRIPT_CONTINUE;
                    }
                    chat.chat(self, "All right, thats it! I'm calling in the big guns!");
                    spawnSidekick(self, "", "acid", 1.0f);
                    return SCRIPT_CONTINUE;
                }
                chat.chat(self, "Haha! I have your money!");
                broadcast(attacker, "You have been robbed!");
                if (money.hasFunds(attacker, money.MT_TOTAL, 1000)) {
                    money.withdraw(attacker, 1000);
                }
                resurgence.pushPlayer(self, attacker,  -10.0f, 0.0f);
                return SCRIPT_CONTINUE;
            }
            chat.chat(self, "MY GOODIES!!");
            resurgence.createCircleSpawn(self, self,  "tusken_raider", 16, 5.0f );
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnSidekick(obj_id self, String creature, String buffName, float scale) throws InterruptedException {
        obj_id sidekick = create.object(creature, getLocation(self));
        setScale(sidekick, scale);
        buff.applyBuff(sidekick, buffName, -1.0f);
        attachScript(sidekick, "event_halloween_treat_thief");
    }
}
