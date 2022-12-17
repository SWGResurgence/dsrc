/*
@Purpose: /developer command handler for player

@Author: BubbaJoe

 */
package script.developer.bubbajoe;

import script.*;
import script.library.*;

public class player_developer extends base_script
{
    public player_developer()
    {

    }

    public int cmdDeveloper(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        java.util.StringTokenizer tok = new java.util.StringTokenizer(params);
        String cmd = tok.nextToken();
        if (cmd.equalsIgnoreCase("quest"))
        {
            String subcommand = tok.nextToken();
            String questString = tok.nextToken();
            if (subcommand.equalsIgnoreCase("grant"))
            {
                groundquests.grantQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("complete"))
            {
                groundquests.completeQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("clear"))
            {
                groundquests.clearQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("task"))
            {
                String task = tok.nextToken();
                if (task.equalsIgnoreCase("complete"))
                {
                    groundquests.completeTask(target, questString, tok.nextToken());
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("reloadAllScripts"))
        {
            reloadAllScripts(self);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("say"))
        {
            String speech = tok.nextToken();
            String combinedMessage = "";
            while (tok.hasMoreTokens())
            {
                combinedMessage += tok.nextToken() + " ";
            }
            chat.chat(target, combinedMessage);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("comm"))
        {
            String speech = tok.nextToken();
            String combinedMessage = "";
            while (tok.hasMoreTokens())
            {
                combinedMessage += tok.nextToken() + " ";
            }
            if (combinedMessage == null || combinedMessage.equals(""))
            {
                return SCRIPT_CONTINUE;
            }
            prose_package pp = prose.getPackage(new string_id(combinedMessage));
            commPlayer(self, target, pp);
            return SCRIPT_CONTINUE;
        }

        if (cmd.equalsIgnoreCase("pumpkin"))
        {
            obj_id pumpkinMaster = target;
            String flag = tok.nextToken();
            String[] pumpkinNames = {
                    "a plump pumpkin",
                    "a regular pumpkin",
                    "a scrawny pumpkin",
                    "a nasty pumpkin",
                    "a scary pumpkin",
                    "a jagged pumpkin",
            };
            if (flag.equals("") || flag == null)
            {
                broadcast(self, "/developer pumpkin [single | ring]");

                return SCRIPT_CONTINUE;
            }
            if (flag.equalsIgnoreCase("single"))
            {
                obj_id pumpkin = createObject("object/tangible/holiday/halloween/pumpkin_object.iff", getLocation(pumpkinMaster));
                attachScript(pumpkin, "event.halloween.pumpkin_smasher_object");
                setName(pumpkin, pumpkinNames[rand(0, pumpkinNames.length - 1)]);
            }
            if (flag.equalsIgnoreCase("ring"))
            {
                location loc = getLocation(pumpkinMaster);
                int howMany = utils.stringToInt(tok.nextToken());
                int radius = utils.stringToInt(tok.nextToken());
                if (howMany == 0 || radius == 0)
                {
                    broadcast(self, "/developer pumpkin ring [num to spawn] [radius]");
                    return SCRIPT_CONTINUE;
                }
                if (!isIdValid(self) || !exists(self))
                {
                    return SCRIPT_CONTINUE;
                }
                float x = loc.x;
                float z = loc.z;
                for (int i = 0; i < howMany; i++)
                {
                    float angle = (float) (i * (360 / howMany));
                    x = loc.x + (float) Math.cos(angle) * radius;
                    z = loc.z + (float) Math.sin(angle) * radius;
                    obj_id pumpkin = create.object("object/tangible/holiday/halloween/pumpkin_object.iff", new location(x, getHeightAtLocation(x, z), z, loc.area));
                    attachScript(pumpkin, "event.halloween.pumpkin_smasher_object");
                    faceTo(pumpkin, pumpkinMaster);
                    setName(pumpkin, pumpkinNames[rand(0, pumpkinNames.length - 1)]);
                }
                prose_package pp = prose.getPackage(new string_id("H'chu apenkee, Moulee-rah!"));
                commPlayer(self, pumpkinMaster, pp, "object/mobile/jabba_the_hutt.iff");
            }
        }

        if (cmd.equalsIgnoreCase("wiki"))
        {
            //example: /developer wiki Combat Macro
            // /developer wiki (params[param, param2, param3])
            String speech = tok.nextToken();
            String wiki_link = "https://swg.fandom.com/wiki/" + speech;
            String pathed;
            pathed = wiki_link.replace(" ", "_");
            //chat.chat(target, splitMsg);
            launchClientWebBrowser(self, pathed);
            return SCRIPT_CONTINUE;
        }

        if (cmd.equals("shuttleRebelDrop"))
        {
            String message = "Mayday! Mayday! Mayday! I have to drop my payload, " + getFirstName(target) + "!";
            prose_package commP = new prose_package();
            commP.stringId = new string_id(message);
            commPlayer(self, target, commP, "object/mobile/dressed_rebel_intel_officer_human_female_01.iff");
            obj_id[] players = getAllPlayers(getLocation(target), 10.0f);
            playClientEffectLoc(players, "appearance/rebel_transport_touch_and_go.prt", getLocation(target), 2.0f);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("shuttleImperialDrop"))
        {
            String message = "Prepare for your cargo delivery, " + getFirstName(target) + "! I don't want any hiccups.";
            prose_package commP = new prose_package();
            commP.stringId = new string_id(message);
            commPlayer(self, target, commP, "object/mobile/dressed_imperial_officer_m_2.iff");
            obj_id[] players = getAllPlayers(getLocation(target), 10.0f);
            playClientEffectLoc(players, "appearance/imperial_transport_touch_and_go.prt", getLocation(target), 2.0f);
            return SCRIPT_CONTINUE;
        }

        if (cmd.equals("ballgame"))
        {
            obj_id pInv = utils.getInventoryContainer(self);
            obj_id hotPotato = createObject("object/tangible/loot/dungeon/geonosian_mad_bunker/relic_gbb_small_ball.iff", pInv, "");
            setName(hotPotato, "a throwable ball");
            attachScript(hotPotato, "developer.bubbajoe.pass_the_ball");
            detachScript(hotPotato, "object.autostack");
            broadcast(self, "a throwable ball has been created in your inventory.");
            return SCRIPT_CONTINUE;
        }

        if (cmd.equalsIgnoreCase("scale"))
        {
            float original = getScale(target);
            broadcast(self, "Original Scale: " + original);
            setScale(target, utils.stringToFloat(tok.nextToken()));
            broadcast(target, "You have been resized.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("pathToMe"))
        {
            createClientPathAdvanced(target, getLocation(target), getLocation(self), "default");
        }
        if (cmd.equalsIgnoreCase("messageto"))
        {
            dictionary param = new dictionary();
            messageTo(target, tok.nextToken(), param, utils.stringToFloat(tok.nextToken()), true);
            return SCRIPT_CONTINUE;
            //messageTo(target,token[0], params, token[1], true);
        }
        if (cmd.equalsIgnoreCase("convertStringToCrc"))
        {
            String hash = tok.nextToken();
            int hashValue = getStringCrc(hash);
            broadcast(self, "Hash Value: " + hashValue);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("travel"))
        {//@example /developer travel add COST Epic Name With Spaces
            String which = tok.nextToken();
            if (which.equals("add"))
            {
                int cost = utils.stringToInt((tok.nextToken()));
                String pointName = tok.nextToken();
                if (tok.hasMoreTokens())
                {
                    pointName += " " + tok.nextToken();
                }
                location loc = getLocation(target);

                if (cost == 0)
                {
                    cost = 125;
                }
                if (pointName == null || pointName.equals(""))
                {
                    broadcast(self, "You must specify a point name.");
                    return SCRIPT_CONTINUE;
                }
                if (loc == null)
                {
                    broadcast(self, "You must specify a valid target.");
                    return SCRIPT_CONTINUE;
                }
                setObjVar(self, "temp_shuttle", pointName);
                debugConsoleMsg(self, "Shuttle point " + pointName + " added at " + loc.x + ", " + loc.y + ", " + loc.z + " for " + cost + " credits.");
                addPlanetTravelPoint(getCurrentSceneName(), pointName, getLocation(self), cost, true, TPT_NPC_Starport);
            }
            if (which.equals("remove"))
            {
                String pointName = tok.nextToken();
                if (tok.hasMoreTokens())
                {
                    pointName += " " + tok.nextToken();
                }
                if (pointName == null || pointName.equals(""))
                {
                    broadcast(self, "You must specify a point name.");
                    return SCRIPT_CONTINUE;
                }
                removePlanetTravelPoint(getCurrentSceneName(), pointName);
            }
            else
            {
                broadcast(self, "Invalid Syntax: /developer travel add COST Epic Name With Spaces");
                return SCRIPT_CONTINUE;
            }
        }
        if (cmd.equalsIgnoreCase("removeSpecStamp"))
        {
            obj_id city_hall = getIntendedTarget(self);
            String VAR_CITY = "spec_stamp";
            String VAR_CITY_OLD = VAR_CITY + ".old"; // for backwards compatibility
            if (hasObjVar(city_hall, VAR_CITY))
            {
                setObjVar(city_hall, VAR_CITY_OLD, getIntObjVar(city_hall, VAR_CITY));
                removeObjVar(city_hall, VAR_CITY);
                sendSystemMessageTestingOnly(self, "Removed Spec Stamp from City Hall with preservation of old specstamp.");
            }
            else
            {
                sendSystemMessageTestingOnly(self, "This target does not have the \"spec_stamp\" objvar.");
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("possess"))
        {
            int commandName = getStringCrc(tok.nextToken());
            String commandParams = null;
            if (tok.hasMoreTokens())
            {
                for (int i = 0; i < tok.countTokens(); i++)
                {
                    commandParams += tok.nextToken() + " ";
                }
            }
            else
            {
                commandParams = "";
            }
            queueCommand(target, commandName, target, commandParams, COMMAND_PRIORITY_IMMEDIATE);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("animate"))
        {
            String animationFile = tok.nextToken();
            doAnimationAction(target, animationFile);
            broadcast(self, "Animation '" + animationFile + "' performed on " + getName(target));
        }
        if (cmd.equals("setRange"))
        {
            float minRange = utils.stringToFloat(tok.nextToken());
            float maxRange = utils.stringToFloat(tok.nextToken());
            setWeaponRangeInfo(getHeldWeapon(self), minRange, maxRange);
        }

        if (cmd.equals("modweapon"))
        {
            obj_id weapon = utils.getHeldWeapon(self);
            if (weapon == null)
            {
                return SCRIPT_CONTINUE;
            }
            String mod = tok.nextToken();
            String value = tok.nextToken();
            if (mod.equals("minDamage"))
            {
                setWeaponMinDamage(weapon, Integer.parseInt(value));
            }
            if (mod.equals("maxDamage"))
            {
                setWeaponMaxDamage(weapon, Integer.parseInt(value));
            }
            if (mod.equals("attackSpeed"))
            {
                setWeaponAttackSpeed(weapon, utils.stringToFloat(value));//float
            }
            if (mod.equals("woundChance"))
            {
                setWeaponWoundChance(weapon, utils.stringToFloat(value));//float
            }
            if (mod.equals("attackCost"))
            {
                setWeaponAttackCost(weapon, Integer.parseInt(value));
            }
            if (mod.equals("elementalValue"))
            {
                setWeaponElementalValue(weapon, Integer.parseInt(value));
            }
            if (mod.equals("elementalType"))
            {
                setWeaponElementalType(weapon, Integer.parseInt(value));
            }
            if (mod.equals("accuracy"))
            {
                setWeaponAccuracy(weapon, Integer.parseInt(value));
            }
            if (mod.equals("damageRadius"))
            {
                setWeaponDamageRadius(weapon, utils.stringToFloat(value));//float
            }
            if (mod.equals("setKinetic"))
            {
                setWeaponDamageType(weapon, DAMAGE_KINETIC);
            }
            if (mod.equals("setEnergy"))
            {
                setWeaponDamageType(weapon, DAMAGE_ENERGY);
            }
            if (mod.equals("setElectricity"))
            {
                setWeaponDamageType(weapon, DAMAGE_ELEMENTAL_ELECTRICAL);
            }
            if (mod.equals("setStun"))
            {
                setWeaponDamageType(weapon, DAMAGE_STUN);
            }
            if (mod.equals("setBlast"))
            {
                setWeaponDamageType(weapon, DAMAGE_BLAST);
            }
            if (mod.equals("setHeat"))
            {
                setWeaponDamageType(weapon, DAMAGE_ELEMENTAL_HEAT);
            }
            if (mod.equals("setCold"))
            {
                setWeaponDamageType(weapon, DAMAGE_ELEMENTAL_COLD);
            }
            if (mod.equals("setAcid"))
            {
                setWeaponDamageType(weapon, DAMAGE_ELEMENTAL_ACID);
            }
            if (mod.equals("setRestraint"))
            {
                setWeaponDamageType(weapon, DAMAGE_RESTRAINT);
            }

            if (mod.equals("resetAllStats"))
            {
                setWeaponMinDamage(weapon, 24);
                setWeaponMaxDamage(weapon, 64);
                setWeaponAttackSpeed(weapon, 1.05f);
                setWeaponWoundChance(weapon, 0f);
                setWeaponAttackCost(weapon, 2);
                setWeaponElementalValue(weapon, 0);
                setWeaponElementalType(weapon, 0);
                setWeaponAccuracy(weapon, 0);
                setWeaponDamageRadius(weapon, 64.0f);
                setWeaponDamageType(weapon, DAMAGE_KINETIC);
                setWeaponRangeInfo(weapon, 0f, 1200f);
                setWeaponDamageRadius(weapon, 0f);
            }
        }

        if (cmd.equals("-help"))
        {
            debugConsoleMsg(self, "Developer Commands:  ");
            debugConsoleMsg(self, "  /developer quest grant <questname> - Grants a quest to the target.");
            debugConsoleMsg(self, "  /developer quest complete <questname> - Completes a quest for the target.");
            debugConsoleMsg(self, "  /developer quest clear <questname> - Clears a quest for the target.");
            debugConsoleMsg(self, "  /developer quest task complete <questname> <taskname> - Completes a task for the target.");
            debugConsoleMsg(self, "  /developer say <message> - Makes the target speak a message.");
            debugConsoleMsg(self, "  /developer comm <message> - Makes the target speak a message in a comm. window.");
            debugConsoleMsg(self, "  /developer scale <float> - Resizes the target.");
            debugConsoleMsg(self, "  /developer messageto <message> <float> - Sends a message to the target.");
            debugConsoleMsg(self, "  /developer wiki <search> - Opens a wiki page in your browser.");
            debugConsoleMsg(self, "  /developer ballgame - Creates a ball in your inventory.");
            debugConsoleMsg(self, "  /developer pathToMe - Creates a path to you.");
            debugConsoleMsg(self, "  /developer convertStringToCrc <string> - Converts a string to a CRC.");
            debugConsoleMsg(self, "  /developer possess <command> <params> - Possesses the target and makes them do a command.");
            debugConsoleMsg(self, "  /developer pumpkin ring <num to spawn> <radius> - Spawns a ring of pumpkins around the pumpkin master.");
            debugConsoleMsg(self, "  /developer pumpkin single - Makes a single pumpkin");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    private void reloadAllScripts(obj_id self) throws InterruptedException
    {
        debugServerConsoleMsg(self, "Reloading all scripts");
        String[] scripts = getScriptList(self);
        for (String script : scripts)
        {
            reloadScript(script);
            broadcast(self, "Reloaded script: " + script);
        }
    }

    public int getPlayerLevel(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        int level = getLevel(target);
        debugSpeakMsg(self, "Your level is " + level);
        return SCRIPT_CONTINUE;
    }

    public location getOffsetFromRoot(obj_id self, obj_id structure_attachment)
    {
        location loc = getLocation(structure_attachment);
        location root = getLocation(self);
        return new location(loc.x - root.x, loc.y - root.y, loc.z - root.z, loc.area);
    }


    public void putPlayersInRing(obj_id[] targets, float ring_radius)
    {
        for (obj_id who : targets)
        {
            if (isIdValid(who))
            {
                location where = getLocation(who);
                float angle = rand(0, 360);
                float x = ring_radius * (float) Math.cos(angle);
                float y = ring_radius * (float) Math.sin(angle);
                where.x = where.x + x;
                where.y = where.y + y;
                setLocation(who, where);
            }
        }
    }

    public void pathToMe(obj_id self, obj_id target) throws InterruptedException
    {
        location here = getLocation(self);
        location there = getLocation(target);
        createClientPathAdvanced(target, there, here, "default");
    }
    public void pathToWho(obj_id self, obj_id target) throws InterruptedException
    {
        location here = getLocation(self);
        location there = getLocation(target);
        createClientPathAdvanced(self, here, there, "default");
    }
}


