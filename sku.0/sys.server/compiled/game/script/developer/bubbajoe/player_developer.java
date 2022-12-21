/*
@Purpose: /developer command handler for player admins.

@Author: BubbaJoe

 */
package script.developer.bubbajoe;

import script.*;
import script.library.*;

import java.io.File;
import java.util.ArrayList;

public class player_developer extends base_script
{
    public player_developer()
    {

    }

    public int cmdDeveloper(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        obj_id iTarget = getIntendedTarget(self);
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
            StringBuilder combinedMessage = new StringBuilder();
            while (tok.hasMoreTokens())
            {
                combinedMessage.append(tok.nextToken()).append(" ");
            }
            chat.chat(target, combinedMessage.toString());
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("comm"))
        {
            String speech = tok.nextToken();
            StringBuilder combinedMessage = new StringBuilder();
            while (tok.hasMoreTokens())
            {
                combinedMessage.append(speech).append(" ");
            }
            if (combinedMessage != null && !combinedMessage.toString().equals(""))
            {
                prose_package pp = prose.getPackage(new string_id(combinedMessage.toString()));
                commPlayer(self, target, pp);
            }
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
            if (flag.equals(""))
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

        if (cmd.equals("shell"))
        {
            String where = tok.nextToken();
            String command = tok.nextToken();
            String args = new String();
            while (tok.hasMoreTokens())
            {
                args += tok.nextToken() + " ";
            }
            String fullCommand = command + " " + args;
            broadcast(self, "Running command: [/developer shell] " + where + " " + fullCommand);
            String outputString = system_process.runAndGetOutput(fullCommand, new File(where));
            int page = createSUIPage("/Script.messageBox", self, self);
            setSUIProperty(page, "Prompt.lblPrompt", "LocalText", outputString);
            setSUIProperty(page, "Prompt.lblPrompt", "Font", "starwarslogo_optimized_56");
            setSUIProperty(page, "bg.caption.lblTitle", "Text", "Shell Commander");
            setSUIProperty(page, "Prompt.lblPrompt", "Editable", "false");
            setSUIProperty(page, "Prompt.lblPrompt", "GetsInput", "true");
            subscribeToSUIEvent(page, sui_event_type.SET_onButton, "%btnOk%", "handleShellOutput");
            subscribeToSUIEvent(page, sui_event_type.SET_onButton, "%btnOK%", "handleShellOutput");
            setSUIProperty(page, "btnCancel", "Visible", "false");
            setSUIProperty(page, "btnRevert", "Visible", "false");
            showSUIPage(page);
            flushSUIPage(page);
            broadcast(self, "Ran command..");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("markObjects"))
        {
            obj_id[] nearbyObjects = getObjectsInRange(getLocation(self), 256.0f);
            for (obj_id nearbyObject : nearbyObjects)
            {
                if (!hasObjVar(nearbyObject, "buildout_utility.write"))
                {
                    setObjVar(nearbyObject, "buildout_utility.write", 1);
                }
            }
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
        if (cmd.equalsIgnoreCase("pathtome"))
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
        if (cmd.equalsIgnoreCase("convertstringtocrc"))
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
                broadcast(self, "Invalid Syntax: /developer travel add [COST] [Epic Name With Spaces]");
                return SCRIPT_CONTINUE;
            }
        }
        if (cmd.equalsIgnoreCase("removespecstamp"))
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
            StringBuilder commandParams = null;
            if (tok.hasMoreTokens())
            {
                for (int i = 0; i < tok.countTokens(); i++)
                {
                    commandParams.append(tok.nextToken()).append(" ");
                }
            }
            else
            {
                commandParams = new StringBuilder();
            }
            queueCommand(target, commandName, target, commandParams.toString(), COMMAND_PRIORITY_IMMEDIATE);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("animate"))
        {
            String animationFile = tok.nextToken();
            doAnimationAction(target, animationFile);
            broadcast(self, "Animation '" + animationFile + "' performed on " + getName(target));
        }
        if (cmd.equals("renameContainerContents"))
        {
            String name = tok.nextToken();
            while (tok.hasMoreTokens())
            {
                name += " " + tok.nextToken();
            }
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                setName(content, name);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("tagContainerContents"))
        {
            String tag = tok.nextToken();
            while (tok.hasMoreTokens())
            {
                tag += " " + tok.nextToken();
            }
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                String craftedName = getName(content);
                String originalName = utils.getStringName(content);
                if (!isCrafted(content))
                {
                    setName(content, originalName + " (" + tag + ")");
                }
                else
                {
                    setName(content, craftedName + " (" + tag + ")");
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("unlockContainer"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                removeObjVar(content, "noTrade");
                detachScript(content, "item.special.nomove");
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("lockContainer"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                setObjVar(content, "noTrade", 1);
                attachScript(content, "item.special.nomove");
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("touchContainer"))
        {
            String tag = colors_hex.HEADER + colors_hex.AQUAMARINE + " (Developer Item)" + colors_hex.FOOTER;
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                String oldName = utils.getStringName(content);
                setName(content, oldName + tag);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("revertContainerContents"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                setName(content, getTemplateName(content));
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("grantAllSchematics"))
        {
            String SCHEMATIC_TABLE = "datatables/crafting/schematic_group.iff";
            String SCHEMATIC_TABLE_COLUMN = "GroupId";
            //for every string in the colulmn GroupId, grantSchematicGroup to my target.
            String[] schematicGroups = dataTableGetStringColumnNoDefaults(SCHEMATIC_TABLE, SCHEMATIC_TABLE_COLUMN);
            for (String schematicGroup : schematicGroups)
            {
                grantSchematicGroup(target, schematicGroup);
                broadcast(self, "Granted schematic group " + schematicGroup + " to " + getName(target));
            }
            broadcast(self, "Granted all schematic groups to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("grantAllItems"))
        {
            String ITEM_TABLE = "datatables/item/master_item/master_item.iff";
            String ITEM_TABLE_COLUMN = "name";
            obj_id myBag = createObjectInInventoryAllowOverload("object/tangible/test/qabag.iff", self);
            String[] items = dataTableGetStringColumnNoDefaults(ITEM_TABLE, ITEM_TABLE_COLUMN);
            int bagLimit = 0;
            for (String item : items)
            {
                if (bagLimit > 250)
                {
                    broadcast(self, "Bag limit reached.  Stopping!");
                    bagLimit = 0;
                    break;
                }
                static_item.createNewItemFunction(item, myBag);
                broadcast(self, "Granted item " + item + " to " + getName(target));
                bagLimit++;
            }
            broadcast(self, "Granted all items to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("magicSatchel"))
        {
            obj_id satchel = create.createObject("object/tangible/container/general/satchel.iff", utils.getInventoryContainer(self), "");
            attachScript(satchel, "developer.bubbajoe.magic_satchel");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("buffAllByName"))
        {
            String query = tok.nextToken();
            String BUFF_TABLE = "datatables/buff/buff.iff";
            String BUFF_TABLE_COLUMN = "NAME";
            String[] buffs = dataTableGetStringColumnNoDefaults(BUFF_TABLE, BUFF_TABLE_COLUMN);
            for (String buffName : buffs)
            {
                if (buffName.contains(query))
                {
                    buff.applyBuff(target, buffName);
                    broadcast(self, "Applied " + buffName + " to " + getName(target));
                }
                else
                {
                    continue;
                }
            }
            broadcast(self, "Granted all items to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("grantAllSkills"))
        {
            ArrayList<String> badSkills = new ArrayList<String>();
            badSkills.add("combat_melee_basic");
            badSkills.add("combat_ranged_weapons_basic");
            badSkills.add("demo_combat");
            badSkills.add("common_knowledge");
            badSkills.add("utility");
            badSkills.add("utility_beta");
            badSkills.add("utility_beta_demo_combat");
            badSkills.add("utility_beta_demo_combat");
            badSkills.add("utility_player");
            String query = tok.nextToken();
            String SKILL_TABLE = "datatables/skill/skills.iff";
            String SKILL_TABLE_COLUMN = "NAME";
            String[] skills = dataTableGetStringColumnNoDefaults(SKILL_TABLE, SKILL_TABLE_COLUMN);
            for (String skill : skills)
            {
                if (skill.contains(query))
                {
                    if(badSkills.contains(skill))
                    {
                        broadcast(self, "Skipping " + skill + ", bad skill.");
                        continue;
                    }
                    grantSkill(target, skill);
                }
                else
                {
                    continue;
                }
            }
            broadcast(self, "Granted all skills from search to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("grantAllItemsBySearch"))
        {
            String query = tok.nextToken();
            String ITEM_TABLE = "datatables/item/master_item/master_item.iff";
            String ITEM_TABLE_COLUMN = "name";
            obj_id myBag = createObjectInInventoryAllowOverload("object/tangible/test/qabag.iff", self);
            String[] items = dataTableGetStringColumnNoDefaults(ITEM_TABLE, ITEM_TABLE_COLUMN);
            for (String item : items)
            {
                if (item.contains(query))
                {
                    static_item.createNewItemFunction(item, myBag);
                }
                else
                {
                    //skip it
                    debugConsoleMsg(self, "Skipping item " + item);
                }
            }
            setName(myBag, "Travel Pack of " + query);
            broadcast(self, "Granted all items with search parameter "+ query + " to " +  getName(target));
            return SCRIPT_CONTINUE;
        }

        if (cmd.equals("editWeapon"))
        {
            obj_id weapon = utils.getHeldWeapon(self);
            if (weapon == null)
            {
                return SCRIPT_CONTINUE;
            }
            String mod = tok.nextToken();
            if (mod == null)
            {
                broadcast(self, "No mod specified. See console for valid mods.");
                debugConsoleMsg(self, "/developer editWeapon <mod> <value>");
                debugConsoleMsg(self, "List of valid mods: minDamage\nmaxDamage\nattackSpeed\nwoundChance\nattackCost\naccuracy\nelementalType\nelementalValue\nrangeInfo\ndamageType\ndamageRadius");
                return SCRIPT_CONTINUE;
            }
            switch (mod)
            {
                case "minDamage":
                    setWeaponMinDamage(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "maxDamage":
                    setWeaponMaxDamage(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "attackSpeed":
                    setWeaponAttackSpeed(weapon, utils.stringToFloat(tok.nextToken()));
                    break;
                case "woundChance":
                    setWeaponWoundChance(weapon, utils.stringToFloat(tok.nextToken()));
                    break;
                case "attackCost":
                    setWeaponAttackCost(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "accuracy":
                    setWeaponAccuracy(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "elementalType":
                    setWeaponElementalType(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "elementalValue":
                    setWeaponElementalValue(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "rangeInfo":
                    setWeaponRangeInfo(weapon, utils.stringToFloat(tok.nextToken()), utils.stringToFloat(tok.nextToken()));
                    break;
                case "damageType":
                    setWeaponDamageType(weapon, utils.stringToInt(tok.nextToken()));
                    break;
                case "damageRadius":
                    setWeaponDamageRadius(weapon, utils.stringToFloat(tok.nextToken()));
                    break;


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
        if (cmd.equalsIgnoreCase("url"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin openlink url");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String url = tok.nextToken();
                obj_id iTar = getIntendedTarget(self);
                launchClientWebBrowser(iTar, url);
            }
        }
        if (cmd.equalsIgnoreCase("playeffect"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffect <effect name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String effect = tok.nextToken();
                playClientEffectObj(self, effect, self, "");
            }
        }
        if (cmd.equalsIgnoreCase("playeffecttarget"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffecttarget <effect name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String effect = tok.nextToken();
                playClientEffectObj(target, effect, target, "");
            }
        }
        if (cmd.equalsIgnoreCase("playeffectloc"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffectloc <effect name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String effect = tok.nextToken();
                playClientEffectLoc(self, effect, getLocation(self), 0.0f);
            }
        }
        if (cmd.equalsIgnoreCase("playeffectloctarget"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffectloctarget <effect name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String effect = tok.nextToken();
                playClientEffectLoc(target, effect, getLocation(target), 0.0f);
            }
        }
        if (cmd.equalsIgnoreCase("playeffectatloc"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffectlocatloc <effect name> <x> <y> <z>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String effect = tok.nextToken();
                float x = Float.parseFloat(tok.nextToken());
                float y = Float.parseFloat(tok.nextToken());
                float z = Float.parseFloat(tok.nextToken());
                location loc = new location(x, y, z);
                playClientEffectLoc(self, effect, loc, 0.0f);
            }
        }
        if (cmd.equalsIgnoreCase("playsound"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsound <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String sound = tok.nextToken();
                playClientEffectObj(self, sound, self, "");
            }
        }
        if (cmd.equalsIgnoreCase("playsoundtarget"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundtarget <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String sound = tok.nextToken();
                playClientEffectObj(iTarget, sound, iTarget, "");
            }
        }
        if (cmd.equalsIgnoreCase("playsoundloc"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundloc <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String sound = tok.nextToken();
                playClientEffectLoc(self, sound, getLocation(self), 0.0f);
            }
        }
        if (cmd.equalsIgnoreCase("playsoundeveryone"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundloc <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                obj_id[] players = getAllPlayers(getLocation(self), 8000.0f);
                for (obj_id player : players) {
                    String sound = tok.nextToken();
                    playClientEffectObj(player, sound, player, "");
                }
            }
        }
        if (cmd.equalsIgnoreCase("playcefeveryone"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playcefeveryone <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                obj_id[] players = getAllPlayers(getLocation(self), 8000.0f);
                for (obj_id player : players) {
                    String sound = tok.nextToken();
                    playClientEffectObj(player, sound, player, "head");
                }
            }
        }
        if (cmd.equalsIgnoreCase("rewardarea"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin grantItemArea <item> <count>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String item = tok.nextToken();
                int count = Integer.parseInt(tok.nextToken());
                obj_id[] players = getAllPlayers(getLocation(self), 250.0f);
                for (obj_id player : players) {
                    obj_id pInv = utils.getInventoryContainer(player);
                    obj_id pItem = static_item.createNewItemFunction(item, pInv, count);
                    if (isIdValid(pItem)) {
                        sendSystemMessageTestingOnly(player, "\\#DD1234You have been awarded items!\\#FFFFFF");
                    }
                }
            }
        }
        if (cmd.equalsIgnoreCase("editlootarea"))
        {
            float radius = 250.0f;
            if (tok.hasMoreTokens())
            {
                radius = Float.parseFloat(tok.nextToken());
            }
            obj_id[] creatures = getCreaturesInRange(getLocation(self), radius);
            for (obj_id creature : creatures) {
                if (isMob(creature)) {
                    if (hasObjVar(self, "loot.numItems")) {
                        setObjVar(creature, "loot.numItems", tok.nextToken());
                    }
                }
            }


        }
        if (cmd.equalsIgnoreCase("say"))
        {
            obj_id iTar = getIntendedTarget(self);
            String words = tok.nextToken();
            if (tok.hasMoreTokens())
            {
                while (tok.hasMoreTokens())
                {
                    words += " " + tok.nextToken();
                }
            }
            if (tok.countTokens() == 1)
            {
                chat.chat(iTar, words);
            }
            {

                broadcast(self, "Syntax: /developer say <message>");
            }
            chat.chat(iTar, words);
        }
        if (cmd.equalsIgnoreCase("setloottable"))
        {
            String table = tok.nextToken();
            if (table == null || table.equals(""))
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin setloottable <loot table name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                setObjVar(self, "loot.lootTable", table);
                sendSystemMessageTestingOnly(self, "Loot table set to " + table);
            }
        }
        if (cmd.equalsIgnoreCase("setnumitems"))
        {
            obj_id iTar = getIntendedTarget(self);
            int level = Integer.parseInt(tok.nextToken());
            if (level == 0)
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin setnumitems <loot level>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                setObjVar(self, "loot.numItems", level);
                sendSystemMessageTestingOnly(self, "Number of loot items set to " + level);
            }
        }
        if (cmd.equalsIgnoreCase("setcount"))
        {
            obj_id iTar = getIntendedTarget(self);
            setCount(iTar, Integer.parseInt(tok.nextToken()));
        }
        if (cmd.equalsIgnoreCase("setcountcontainer"))
        {
            obj_id[] itemsInside = utils.getContents(getIntendedTarget(self), true);
            for (obj_id itemInside : itemsInside) {
                setCount(itemInside, Integer.parseInt(tok.nextToken()));
            }
        }
        if (cmd.equalsIgnoreCase("sendwarning"))
        {
            String words = tok.nextToken();
            if (tok.hasMoreTokens())
            {
                while (tok.hasMoreTokens())
                {
                    words += " " + tok.nextToken();
                }
            }

        }
        if (cmd.equalsIgnoreCase("sws"))
        {
            String template = tok.nextToken();
            String script = tok.nextToken();
            obj_id item = createObject(template, getLocation(self));
            attachScript(item, script);
            setYaw(item, getYaw(self));
        }
        if (cmd.equalsIgnoreCase("ringspawn"))
        {
            String creatureToSpawn = tok.nextToken();
            int num = Integer.parseInt(tok.nextToken());
            float radius = Float.parseFloat(tok.nextToken());
            location where = getLocation(self);
            resurgence.createCircleSpawn(self, self, creatureToSpawn, num, radius);

        }
        if (cmd.equalsIgnoreCase("spawnringinterior"))
        {
            String creatureToSpawn = tok.nextToken();
            int num = Integer.parseInt(tok.nextToken());
            float radius = Float.parseFloat(tok.nextToken());
            location where = getLocation(self);
            spawnRingInterior(self, num, radius, where, creatureToSpawn);

        }
        if (cmd.equalsIgnoreCase("playsoundloctarget"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundloctarget <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String sound = tok.nextToken();
                playClientEffectLoc(iTarget, sound, getLocation(iTarget), 0.0f);
                return SCRIPT_CONTINUE;
            }
        }
        if (cmd.equalsIgnoreCase("playsoundatloc"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundatloc <sound name> <x> <y> <z>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String sound = tok.nextToken();
                float x = Float.parseFloat(tok.nextToken());
                float y = Float.parseFloat(tok.nextToken());
                float z = Float.parseFloat(tok.nextToken());
                location loc = new location(x, y, z);
                playClientEffectLoc(self, sound, loc, 0.0f);
                return SCRIPT_CONTINUE;
            }
        }
        if (cmd.equalsIgnoreCase("playmusic"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playmusic <music name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String music = tok.nextToken();
                playMusic(self, music);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("playmusictarget"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin playmusictarget <music name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String music = tok.nextToken();
                playMusic(iTarget, music);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("slap"))
        {
            chat.chat(self, "removed this.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("removeinvuln"))
        {
            setInvulnerable(iTarget, false);
        }
        if (cmd.equalsIgnoreCase("setinvuln"))
        {
            setInvulnerable(iTarget, true);
        }
        if (cmd.equalsIgnoreCase("boxspawn"))
        {
            resurgence.createCreatureGrid(self, iTarget, tok.nextToken(), Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()), Float.parseFloat(tok.nextToken()));
            echo(self, "OK!");
        }
        if (cmd.equalsIgnoreCase("clone"))
        {
            obj_id pInv = utils.getInventoryContainer(self);
            String copies = tok.nextToken();
            for (int i = 0; i < Integer.parseInt(copies); i++)
            {
                obj_id cloned_item = utils.cloneObject(iTarget, pInv);
                for (String s : getScriptList(iTarget)) {
                    attachScript(iTarget, s);
                    setName(cloned_item, getName(iTarget));
                    utils.copyObjectData(iTarget, cloned_item);
                }
                broadcast(self,"Cloned " + getName(iTarget) + " to " + getName(self) + "'s inventory with " + copies + " copies.");

            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("modvehicle"))
        {
            if (tok.countTokens() < 1)
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin modvehicle <mod index> <mod value>");
                return SCRIPT_CONTINUE;
            }
            if (vehicle.isRidingVehicle(self))
            {
                obj_id vehid = getMountId(self);
                String vehicleModifier = tok.nextToken();
                float vehicleModifierValue = Float.parseFloat(tok.nextToken());
                vehicle.setValue(vehid, vehicleModifierValue, Integer.parseInt(vehicleModifier));
                return SCRIPT_CONTINUE;
            }
            else
            {
                sendSystemMessageTestingOnly(self,"You are not riding a vehicle.");
            }
        }

        if (cmd.equals("-help"))
        {
            debugConsoleMsg(self, "Developer Commands:  ");
            debugConsoleMsg(self, "  QUEST:");
            debugConsoleMsg(self, "  /developer quest grant <questname> - Grants a quest to the target.");
            debugConsoleMsg(self, "  /developer quest complete <questname> - Completes a quest for the target.");
            debugConsoleMsg(self, "  /developer quest clear <questname> - Clears a quest for the target.");
            debugConsoleMsg(self, "  /developer quest task complete <questname> <taskname> - Completes a task for the target.");
            debugConsoleMsg(self, "  MISC:");
            debugConsoleMsg(self, "  /developer say <message> - Makes the target speak a message.");
            debugConsoleMsg(self, "  /developer comm <message> - Makes the target speak a message in a comm. window.");
            debugConsoleMsg(self, "  /developer scale <float> - Resizes the target.");
            debugConsoleMsg(self, "  /developer messageto <message> <float> - Sends a message to the target.");
            debugConsoleMsg(self, "  /developer wiki <search> - Opens a wiki page in your browser.");
            debugConsoleMsg(self, "  /developer pathToMe - Creates a path to you.");
            debugConsoleMsg(self, "  /developer convertStringToCrc <string> - Converts a string to a CRC.");
            debugConsoleMsg(self, "  /developer possess <command> <params> - Possesses the target and makes them do a command.");
            debugConsoleMsg(self, "  INTERNAL:");
            debugConsoleMsg(self, "  /developer shell <directory> <command and params> - Runs a command on the server box and returns the output to a messagebox.");
            debugConsoleMsg(self, "  EVENT:");
            debugConsoleMsg(self, "  /developer pumpkin ring <num to spawn> <radius> - Spawns a ring of pumpkins around the pumpkin master.");
            debugConsoleMsg(self, "  /developer pumpkin single - Makes a single pumpkin");
            debugConsoleMsg(self, "  /developer ballgame - Creates a ball in your inventory.");
            debugConsoleMsg(self, "  /developer tagContainerContents <tag> - Tags all items in a container. Formats all item names in container with a parenthesis and the tag.");
            debugConsoleMsg(self, "  /developer revertContainerContents - Resets the items in a container to their template paths.");
            debugConsoleMsg(self, "  /developer renameContainerContents <name> - Renames all items in a container to the parameter.");
            debugConsoleMsg(self, "  /developer lockContainer - Applies noTrade objvar and attaches item.special.nomove");
            debugConsoleMsg(self, "  /developer unlockContainer - Removes noTrade objvar and detaches item.special.nomove");
            debugConsoleMsg(self, "  DEBUG:");
            debugConsoleMsg(self, "  / ADD MORE HERE");
            return SCRIPT_CONTINUE;
        }
        else
        {
            broadcast(self, "Command not found.  Use -help for a list of commands.");
        }
        return SCRIPT_CONTINUE;
    }

    private boolean echo(obj_id self, String s)
    {
        return sendConsoleCommand("echo " + s, self);
    }

    private void reloadAllScripts(obj_id self)
    {
        debugServerConsoleMsg(self, "Reloading all scripts");
        String[] scripts = getScriptList(self);
        for (String script : scripts)
        {
            reloadScript(script);
            broadcast(self, "Attempting to reload " + script);
        }
    }

    public void sendWebhook(obj_id self, String url, String message)
    {
        if (url == null || url.equals(""))
        {
            return;
        }
        if (message == null || message.equals(""))
        {
            return;
        }
        DiscordWebhook webhook = new DiscordWebhook(url);

        webhook.setContent(message);
        webhook.setAvatarUrl("https://i.imgur.com/WO53VrH.png");
        webhook.setTts(true);
        webhook.setUsername("Newsnet Reporter");
    }

    public int handleShellOutput(obj_id self, dictionary params)
    {
        broadcast(self, "handleShellOutput -- we are here");
        return SCRIPT_CONTINUE;
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
    public void pathToWho(obj_id self, obj_id target)
    {
        location here = getLocation(self);
        location there = getLocation(target);
        createClientPathAdvanced(self, here, there, "default");
    }
    public int OnLogin(obj_id self) throws InterruptedException
    {
        sendConsoleCommand( "/setGodMode 0", self);
        sendConsoleCommand( "/object setCoverVisibility " + self + " " + 0, self);
        sendConsoleCommand( "/object hide " + self + " " + 0, self);
        sendConsoleCommand( "/echo YOU ARE VISIBLE WITH THIS SCRIPT ON LOGIN!", self);
        return SCRIPT_CONTINUE;
    }
    private void spawnRingInterior(obj_id self, int num, float radius, location where, String creatureToSpawn) throws InterruptedException
    {
        float x = where.x;
        float y = where.y;
        float z = where.z;
        float angle = 0;
        float angleInc = 360.0f / num;
        for (int i = 0; i < num; i++)
        {
             angle = angle + angleInc;
            float newX = x + (float)Math.cos(angle) * radius;
            float newY = y + (float)Math.sin(angle) * radius;
            location newLoc = new location(newX, newY, z, where.area, where.cell);
            obj_id creature = create.object(creatureToSpawn, newLoc);
            if (isIdValid(creature))
            {
                setScale(creature, 0.5f);
            }
        }
    }
}


