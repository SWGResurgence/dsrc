package script.event.halloween;

import script.dictionary;
import script.library.*;
import script.location;
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
        setInvulnerable(self, false);
        setHealth(self, 2500000);
        setHitpoints(self, 2500000);
        setName(self, "Treat Thief");
        setScale(self, 2.0f);
        startFog(self);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnCombatEntered(obj_id self, obj_id attacker, obj_id[] attackers, dictionary params) throws InterruptedException
    {
        //sendSystemMessageGalaxyTestingOnly("[World Event] The Treat Thief has been spotted on " + toUpper(getCurrentSceneName(), 1));
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
        if (ratio <= 0.9f && !hasObjVar(self, "event.halloween_first_ratio"))
        {
            if (ratio <= 0.75f && !hasObjVar(self, "event.halloween_second_ratio"))
            {
                if (ratio <= 0.6f && !hasObjVar(self, "event.halloween_third_ratio"))
                {
                    if (ratio <= 0.45f && !hasObjVar(self, "event.halloween_fourth_ratio"))
                    {
                        if (ratio <= 0.35f && !hasObjVar(self, "event.halloween_fifth_ratio"))
                        {
                            if (ratio <= 0.3f && !hasObjVar(self, "event.halloween_final_ratio"))
                            {
                                if (!buff.hasBuff(self, "event_rebel_candy"))
                                {
                                    buff.applyBuff(self, "event_rebel_candy", -1.0f, 40.0f);
                                    setObjVar(self, "event.halloween_final_ratio", true);
                                }
                            }
                            spawnSidekick(self, "tusken_warlord","frogBuff", 1.0f);
                            setObjVar(self, "event.halloween_fifth_ratio", true);
                            return SCRIPT_CONTINUE;
                        }
                        chat.chat(self, "You're not getting away with this" + getName(attacker));
                        spawnSidekick(self, "tusken_warlord", "frogBuff", 1.0f);
                        setObjVar(self, "event.halloween_fourth_ratio", true);
                        return SCRIPT_CONTINUE;
                    }
                    chat.chat(self, "Finally... I've been waiting for you " + getName(attacker));
                    createCircleSpawn(self, attacker,  "nuna", 20, 10.0f );
                    setObjVar(self, "event.halloween_third_ratio", true);
                    return SCRIPT_CONTINUE;
                }
                chat.chat(self, "Haha! I have your credits, " + getName(attacker) + "!");
                broadcast(attacker, "You have been robbed!");
                if (money.hasFunds(attacker, money.MT_TOTAL, 1000)) {
                    money.withdraw(attacker, 1000);
                }
                pushPlayer(self, attacker,  -10.0f, 45.0f);
                setObjVar(self, "event.halloween_second_ratio", true);
                return SCRIPT_CONTINUE;
            }
            chat.chat(self, "Help me minions. My precious treats are being stolen!");
            createCircleSpawn(self, self,  "tusken_warlord", 6, 5.0f );
            setObjVar(self, "event.halloween_first_ratio", 1);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnSidekick(obj_id self, String creature, String buffName, float scale) throws InterruptedException {
        obj_id sidekick = create.object(creature, getLocation(self));
        setScale(sidekick, scale);
        buff.applyBuff(sidekick, buffName, -1.0f);
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
    public static void createCircleSpawn(obj_id self, obj_id target, String creature, int amount, float distance) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "createCircleSpawn() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "createCircleSpawn() - target is not a player.");
        }
        if (amount < 1) {
            debugServerConsoleMsg(self, "createCircleSpawn() - amount is less than 1.");
        }
        if (distance < 0) {
            debugServerConsoleMsg(self, "createCircleSpawn() - distance is less than 0.");
        }
        location loc = getLocation(target);
        float x = loc.x;
        float z = loc.z;
        for (int i = 0; i < amount; i++) {
            float angle = (float) (i * (360 / amount));
            x = loc.x + (float) Math.cos(angle) * distance;
            z = loc.z + (float) Math.sin(angle) * distance;
            obj_id creatureObj = create.object(creature, new location(x, loc.y, z, loc.area));
            faceTo(self, creatureObj);
        }
        debugServerConsoleMsg(self, "createCircleSpawn() - circle spawn created.");
    }
    public static void pushPlayer(obj_id self, obj_id target, float distance, float angle) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "pushPlayer() - target is not a valid object.");
        }
        if (distance < 0) {
            debugServerConsoleMsg(self, "pushPlayer() - distance is less than 0.");
        }
        if (angle < 0 || angle > 360) {
            debugServerConsoleMsg(self, "pushPlayer() - angle is not between 0 and 360.");
        }
        location loc = getLocation(target);
        float x = loc.x + (float) Math.cos(angle) * distance;
        float z = loc.z + (float) Math.sin(angle) * distance;
        warpPlayer(target, loc.area, x, loc.y, z, null, 0, 0, 0);
        debugServerConsoleMsg(self, "pushPlayer() - player pushed.");
    }
    private void startFog(obj_id device) throws InterruptedException
    {
        stopClientEffectObjByLabel(device, "halloweenFog");
        playClientEffectObj(device, "clienteffect/halloween_fog_machine.cef", device, "", null, "halloweenFog");
        messageTo(device, "continueFog", null, 18.0f, false);
    }
    private void stopFog(obj_id device) throws InterruptedException
    {
        stopClientEffectObjByLabel(device, "halloweenFog");
    }
    public int continueFog(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "fogOn"))
        {
            playClientEffectObj(self, "clienteffect/halloween_fog_machine.cef", self, "", null, "halloweenFog");
            messageTo(self, "continueFog", null, 18.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
}