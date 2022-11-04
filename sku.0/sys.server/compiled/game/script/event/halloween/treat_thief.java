/*
@Purpose: World Boss for Galactic Moon Festival

@Author: BubbaJoe

 */
package script.event.halloween;

import script.dictionary;
import script.library.*;
import script.location;
import script.menu_info;
import script.obj_id;

public class treat_thief extends script.base_script
{
    public static final String RING_EVENT_ONE = "event_halloween_minion_01";
    public static final String RING_EVENT_TWO = "event_halloween_minion_02";
    public static final int RING_EVENT_NUM_MOBS = 16;
    public static final float RING_EVENT_MOB_RANGE = 0.0f;
    public static final String SIDEKICK_TEMPLATE_ONE = "event_halloween_sidekick_01";
    public static final String SIDEKICK_TEMPLATE_TWO = "event_halloween_sidekick_02";
    public static final String SIDEKICK_BUFF = "event_halloween_sidekick_buff";
    public static final String FINAL_BUFF = "event_halloween_boss_last_chance";
    public static final int FINAL_BUFF_DURATION = 5;
    public static final int FINAL_BUFF_POWER = 100;
    public static final float RUBBERBAND_DISTANCE = 16.0f;
    public static final String LOOT_DROP_TEMPLATE = "object/tangible/loot/quest/halloween_treat.iff"; //@TODO: change me
    public treat_thief()
    {
    }

    public static int createCircleSpawn(obj_id self, obj_id target, String creature, int amount, float distance) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            return SCRIPT_CONTINUE;
        }
        location loc = getLocation(target);
        float x = loc.x;
        float z = loc.z;
        for (int i = 0; i < amount; i++)
        {
            float angle = (float) (i * (360 / amount));
            x = loc.x + (float) Math.cos(angle) * distance;
            z = loc.z + (float) Math.sin(angle) * distance;
            obj_id creatureObj = create.object(creature, new location(x, loc.y, z, loc.area));
            faceTo(self, creatureObj);
        }
        //debugServerConsoleMsg(self, "createCircleSpawn() - circle spawn created.");
        return SCRIPT_CONTINUE;
    }

    public static void pushPlayer(obj_id self, obj_id player, float distance) throws InterruptedException
    {
        if (!isIdValid(player) || !exists(player))
        {
            return;
        }
        location loc = getLocation(player);
        float x = loc.x;
        float z = loc.z;
        float angle = rand(0.0f, 360.0f);
        x = loc.x + (float) Math.cos(angle) * distance;
        z = loc.z + (float) Math.sin(angle) * distance;
        setLocation(player, new location(x, loc.y, z, loc.area));
        //debugServerConsoleMsg(self, "pushPlayer() - player pushed.");
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, false);
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
        if (hasObjVar(self, "event.halloween_spam"))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            setObjVar(self, "event.halloween_spam", 1);
            sendSystemMessageGalaxyTestingOnly("[World Event] The Treat Thief has been engaged on " + toUpper(getCurrentSceneName(), 0));

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
        if (group.isGrouped(attacker))
        {
            obj_id[] groupMembers = getGroupMemberIds(attacker);
            if (groupMembers == null || groupMembers.length == 0)
            {
                return SCRIPT_CONTINUE;
            }
            for (obj_id groupMember : groupMembers)
            {
                addToHealth(self, groupMembers.length * 1800);
            }
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
                                if (!buff.hasBuff(self, FINAL_BUFF))
                                {
                                    buff.applyBuff(self, FINAL_BUFF, FINAL_BUFF_DURATION, FINAL_BUFF_POWER);
                                    setObjVar(self, "event.halloween_final_ratio", 1);
                                }
                            }
                            spawnSidekick(self, SIDEKICK_TEMPLATE_TWO, SIDEKICK_BUFF, 1.0f, 40.0f);
                            setObjVar(self, "event.halloween_fifth_ratio", 1);
                            return SCRIPT_CONTINUE;
                        }
                        chat.chat(self, "Stay away! I have a family to steal from!");
                        spawnSidekick(self, SIDEKICK_TEMPLATE_ONE, SIDEKICK_BUFF, 1.0f, 2.5f);
                        setObjVar(self, "event.halloween_fourth_ratio", 1);
                        return SCRIPT_CONTINUE;
                    }
                    chat.chat(self, "Those first minions were weak! Maybe these will be more of a challenge.");
                    createCircleSpawn(self, attacker, RING_EVENT_TWO, RING_EVENT_NUM_MOBS, RING_EVENT_MOB_RANGE);
                    setObjVar(self, "event.halloween_third_ratio", 1);
                    return SCRIPT_CONTINUE;
                }
                chat.chat(self, "My ledger seems a little light.. I'll be taking those credits, " + getName(attacker) + "!");
                broadcast(attacker, "The Treat Thief has stolen your credits!");
                if (money.hasFunds(attacker, money.MT_TOTAL, 1000))
                {
                    money.withdraw(attacker, 1000);
                }
                pushPlayer(self, attacker, RUBBERBAND_DISTANCE);
                setObjVar(self, "event.halloween_second_ratio", 1);
                return SCRIPT_CONTINUE;
            }
            chat.chat(self, "You cannot have my goodies! Minions!!!");
            setObjVar(self, "event.halloween_first_ratio", 1);
            createCircleSpawn(self, self, RING_EVENT_ONE, RING_EVENT_NUM_MOBS, RING_EVENT_MOB_RANGE);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public void spawnSidekick(obj_id self, String creature, String buffName, float dur, float scale) throws InterruptedException
    {
        obj_id sidekick = create.object(creature, getLocation(self));
        setScale(sidekick, scale);
        buff.applyBuff(sidekick, buffName, dur);
        chat.chat(sidekick, "Utinni!");
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        int lootChanc = rand(1, 100);
        if (lootChanc <= 50)
        {
            obj_id loot = createObject(LOOT_DROP_TEMPLATE, utils.getInventoryContainer(self), "");
            setName(loot, "Halloween Treat");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
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