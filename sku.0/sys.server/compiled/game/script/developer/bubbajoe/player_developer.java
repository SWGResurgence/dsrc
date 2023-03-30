/*
@Purpose: /developer command handler for player admins.

@Author: BubbaJoe

 */
package script.developer.bubbajoe;

import script.*;
import script.library.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;

public class player_developer extends base_script
{
    //public String APIKEY = "https://discord.com/api/webhooks/1054125244060799076/YUI-Gwy8iJTHzBJkPkFBp7kjH27uGNFlO6z6-LFx39kAel5PlQ_xk_sFqxzdf5igiapD";
    public String APIKEY = "https://discord.com/api/webhooks/1056764306320003132/LRryi0SZDM920lm7Z6wRcs4eCKJAEkHcRCwLuyiKMYgzK5MGHvRj9kUx0gd3wFl_4wjE";
    public player_developer()
    {
    }

    public int cmdDeveloper(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException, InvocationTargetException, IOException
    {
        obj_id iTarget = target;
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
                String taskSub = tok.nextToken();
                String taskName = tok.nextToken();
                if (taskSub.equalsIgnoreCase("complete"))
                {
                    groundquests.completeTask(target, questString, taskName);
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("reloadAllScripts"))
        {
            reloadAllScripts(self);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("listWattos"))
        {
            obj_id[] wattos = getAllObjectsWithObjVar(getLocation(self), 16000f, "watto_tag");
            for (obj_id watto : wattos)
            {
                sendSystemMessageTestingOnly(self, "Watto: " + watto);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equals("gotoWatto"))
        {
            obj_id[] wattos = getAllObjectsWithObjVar(getLocation(self), 16000f, "watto_tag");
            if (wattos.length > 0)
            {
                location wattoLoc = getLocation(wattos[0]);
                warpPlayer(self, wattoLoc.area, wattoLoc.x, wattoLoc.y, wattoLoc.z, null, 0, 0, 0);
            }
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
        if (cmd.equalsIgnoreCase("resourceDatapad"))
        {
            obj_id myTarget = getIntendedTarget(self);
            obj_id pInv = utils.getInventoryContainer(myTarget);
            obj_id datapad = createObject("object/tangible/loot/npc_loot/datapad_flashy_generic.iff", pInv, "");
            setName(datapad, "Resource Analyzer");
            setBioLink(datapad, myTarget);
            setObjVar(datapad, "resource_amount", 10000);
            setObjVar(datapad, "noTrade", 1);
            attachScript(datapad, "developer.bubbajoe.dev_res");
            attachScript(datapad, "item.special.nomove");
            static_item.setStaticItemName(datapad, "Resource Analyzer");
            static_item.setDescriptionStringId(datapad, new string_id("This item is used to generate resources that have erroneously been removed from the game.\n\n" + "It is a developer tool and should not be used by players."));
            broadcast(self,"Resource Analyzer has been added to your inventory. All actions regarding this tool are logged. [Player: " +  myTarget + "]");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("describe"))
        {
            dictionary paramsDict = new dictionary();
            obj_id myTarget = getIntendedTarget(self);
            paramsDict.put("target", myTarget);
            sui.inputbox(self, self, "Enter a description for the target.", sui.OK_ONLY, "Describe", sui.INPUT_NORMAL, null, "handleDescribe", paramsDict);
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
            String speech = tok.nextToken();
            String wiki_link = "https://swg.fandom.com/wiki/" + speech;
            String pathed;
            pathed = wiki_link.replace(" ", "_");
            launchClientWebBrowser(self, pathed);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("shuttleRebelDrop"))
        {
            String message = "Mayday! Mayday! Mayday! I have to drop my payload, " + getFirstName(target) + "!";
            prose_package commP = new prose_package();
            commP.stringId = new string_id(message);
            commPlayer(self, target, commP, "object/mobile/dressed_rebel_intel_officer_human_female_01.iff");
            obj_id[] players = getAllPlayers(getLocation(target), 10.0f);
            playClientEffectLoc(players, "appearance/rebel_transport_touch_and_go.prt", getLocation(target), 2.0f);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("shuttleImperialDrop"))
        {
            String message = "Prepare for your cargo delivery, " + getFirstName(target) + "! I don't want any hiccups.";
            prose_package commP = new prose_package();
            commP.stringId = new string_id(message);
            commPlayer(self, target, commP, "object/mobile/dressed_imperial_officer_m_2.iff");
            obj_id[] players = getAllPlayers(getLocation(target), 10.0f);
            playClientEffectLoc(players, "appearance/imperial_transport_touch_and_go.prt", getLocation(target), 2.0f);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("seedAllSchematics"))
        {
            obj_id inventory = utils.getInventoryContainer(self);
            if (!isIdValid(inventory))
            {
                return SCRIPT_CONTINUE;
            }
            String schematicTable = "datatables/crafting/schematic_group.iff";
            String column = "SchematicName";
            String[] schematics = dataTableGetStringColumnNoDefaults(schematicTable, column);
            obj_id myBag = createObjectInInventoryAllowOverload("object/tangible/test/qabag.iff", self);
            String[] items = dataTableGetStringColumnNoDefaults(schematicTable, column);
            int bagLimit = 0;
            for (String item : items)
            {
                String description = "This item was created by " + colors_hex.HEADER + colors_hex.PEACHPUFF + getRandomHumanName(self) + colors_hex.FOOTER + " on " + colors_hex.HEADER + colors_hex.SANDYBROWN + getCalendarTimeStringGMT_YYYYMMDDHHMMSS(getCalendarTime()) + "\\#.";
                if (bagLimit > 500 && !utils.hasScriptVar(self, "bagLimit"))
                {
                    broadcast(self, "Breached 500 items.");
                    utils.setScriptVar(self, "bagLimit", 1);
                }
                obj_id madeItem = makeCraftedItem(item, 100f, myBag);
                setDescriptionStringId(madeItem, new string_id(description));
                bagLimit++;
            }
            broadcast(self, "Seeding " + items.length + " items with 100% quality.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("seedAllSchematicsByType"))
        {
            String type = "object/draft_schematic/" + tok.nextToken();
            obj_id inventory = utils.getInventoryContainer(self);
            if (!isIdValid(inventory))
            {
                return SCRIPT_CONTINUE;
            }
            String schematicTable = "datatables/crafting/schematic_group.iff";
            String column = "SchematicName";
            String[] schematics = dataTableGetStringColumnNoDefaults(schematicTable, column);
            obj_id myBag = createObjectInInventoryAllowOverload("object/tangible/test/qabag.iff", self);
            String[] items = dataTableGetStringColumnNoDefaults(schematicTable, column);
            int bagLimit = 0;
            for (String item : items)
            {
                if (item.startsWith(type)) //object/draft_schematic/TYPE/subtype/etc/etc
                {
                    String description = "This item was created by " + colors_hex.HEADER + colors_hex.PEACHPUFF + getRandomHumanName(self) + colors_hex.FOOTER + " on " + colors_hex.HEADER + colors_hex.SANDYBROWN + getCalendarTimeStringGMT_YYYYMMDDHHMMSS(getCalendarTime() - (rand(0, 106560))) + "\\#.";
                    if (bagLimit > 500 && !utils.hasScriptVar(self, "bagLimit"))
                    {
                        broadcast(self, "Breached 500 items.");
                        utils.setScriptVar(self, "bagLimit", 1);
                    }
                    obj_id madeItem = makeCraftedItem(item, 1000.0f, myBag);
                    setDescriptionStringId(madeItem, new string_id(description));
                    setObjVar(madeItem, "null_desc", description);
                    attachScript(madeItem, "developer.bubbajoe.sync");
                    bagLimit++;
                }
                else
                {
                    sendConsoleMessage(self,"Skipping " + item);
                }
            }
            broadcast(self, "Seeding " + bagLimit + " items with 100% quality.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("ballgame"))
        {
            obj_id myTarget = getIntendedTarget(self);
            obj_id pInv = utils.getInventoryContainer(myTarget);
            obj_id hotPotato = createObject("object/tangible/loot/dungeon/geonosian_mad_bunker/relic_gbb_small_ball.iff", pInv, "");
            setName(hotPotato, "a throwable ball");
            attachScript(hotPotato, "developer.bubbajoe.pass_the_ball");
            detachScript(hotPotato, "object.autostack");
            String descUnloc = "This is a ball that can be thrown at other players. Use \"Throw Ball\" to throw it at a player.";
            setDescriptionStringId(hotPotato, new string_id(descUnloc));
            broadcast(myTarget, "You have been passed a ball! Use \"Throw Ball\" to throw it at a player.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("shell"))
        {
            String where = tok.nextToken();
            String command = tok.nextToken();
            String args = "";
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
            broadcast(self, "Ran command.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("markObjects"))
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
        if (cmd.equalsIgnoreCase("makeAugs"))
        {
            String template = "object/tangible/component/weapon/new_weapon/enhancement_ranged_slot_one_s23.iff";
            obj_id augment = createObject(template, getLocation(self));
            attachScript(augment, "systems.crafting.weapon.component.crafting_weapon_component_attribute");
            setObjVar(augment, "attribute.bonus.0", 300);
            setObjVar(augment, "attribute.bonus.2", 300);
            setName(augment, "Melee Weapon Augmentation");
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
        if (cmd.equalsIgnoreCase("path"))
        {
            //createClientPath(self, getLocation(self), getLocation(target));
            createClientPathAdvanced(self, getLocation(self), getLocation(target), "default");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("messageto"))
        {
            dictionary param = new dictionary();
            messageTo(target, tok.nextToken(), param, utils.stringToFloat(tok.nextToken()), true);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("convertstringtocrc"))
        {
            String hash = tok.nextToken();
            int hashValue = getStringCrc(hash);
            broadcast(self, "Hash Value: " + hashValue);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("convertcrctostring"))
        {
            String hash = tok.nextToken();

            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("travel"))
        {
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
            String VAR_CITY_OLD = VAR_CITY + ".old";
            if (hasObjVar(city_hall, VAR_CITY))
            {
                setObjVar(city_hall, VAR_CITY_OLD, getIntObjVar(city_hall, VAR_CITY));
                removeObjVar(city_hall, VAR_CITY);
                sendSystemMessageTestingOnly(self, "Removed specstamp from City Hall with preservation of old specstamp.");
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
        if (cmd.equalsIgnoreCase("animate"))
        {
            String animationFile = tok.nextToken();
            doAnimationAction(target, animationFile);
            broadcast(self, "Animation '" + animationFile + "' performed on " + getName(target));
        }
        if (cmd.equalsIgnoreCase("renameContainerContents"))
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
        if (cmd.equalsIgnoreCase("tagContainerContents"))
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
        if (cmd.equalsIgnoreCase("unlockContainer"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                removeObjVar(content, "noTrade");
                detachScript(content, "item.special.nomove");
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("lockContainer"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                setObjVar(content, "noTrade", 1);
                attachScript(content, "item.special.nomove");
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("touchContainer"))
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
        if (cmd.equalsIgnoreCase("revertContainerContents"))
        {
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                setName(content, getTemplateName(content));
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("grantAllSchematics"))
        {
            String SCHEMATIC_TABLE = "datatables/crafting/schematic_group.iff";
            String SCHEMATIC_TABLE_COLUMN = "GroupId";
            String[] schematicGroups = dataTableGetStringColumnNoDefaults(SCHEMATIC_TABLE, SCHEMATIC_TABLE_COLUMN);
            for (String schematicGroup : schematicGroups)
            {
                grantSchematicGroup(target, schematicGroup);
                broadcast(self, "Granted schematic group " + schematicGroup + " to " + getName(target));
            }
            broadcast(self, "Granted all schematic groups to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("grantAllItems"))
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
        if (cmd.equalsIgnoreCase("magicSatchel"))
        {
            obj_id satchel = create.createObject("object/tangible/container/general/satchel.iff", utils.getInventoryContainer(self), "");
            attachScript(satchel, "developer.bubbajoe.magic_satchel");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("buffAllByName"))
        {
            String query = tok.nextToken();
            String BUFF_TABLE = "datatables/buff/buff.iff";
            String BUFF_TABLE_COLUMN = "NAME";
            String[] buffs = dataTableGetStringColumnNoDefaults(BUFF_TABLE, BUFF_TABLE_COLUMN);
            for (String buffName : buffs)
            {
                if (buffName.contains(query))
                {
                    if (buff.isDebuff(buffName))
                    {
                        return SCRIPT_CONTINUE;
                    }
                    else
                    {
                        buff.applyBuff(target, buffName);
                    }
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
        if (cmd.equalsIgnoreCase("grantAllSkills"))
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
            badSkills.add("swg_dev");
            badSkills.add("swg_cs");
            String query = tok.nextToken();
            String SKILL_TABLE = "datatables/skill/skills.iff";
            String SKILL_TABLE_COLUMN = "NAME";
            String[] skills = dataTableGetStringColumnNoDefaults(SKILL_TABLE, SKILL_TABLE_COLUMN);
            for (String skill : skills)
            {
                if (skill.contains(query))
                {
                    if (badSkills.contains(skill))
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
        if (cmd.equalsIgnoreCase("grantAllItemsBySearch"))
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
                    debugConsoleMsg(self, "Skipping item " + item);
                }
            }
            setName(myBag, "Travel Pack of " + query);
            broadcast(self, "Granted all items with search parameter " + query + " to " + getName(target));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("listStaticContents"))
        {
            obj_id[] contents = getContents(target);
            String prompt = "Contents of " + getName(target) + " are: \n";
            for (obj_id content : contents)
            {
                String itemName = getStaticItemName(content);
                prompt += itemName + "\n";
            }
            int page = createSUIPage("/Script.messageBox", self, self);
            setSUIProperty(page, "Prompt.lblPrompt", "LocalText", prompt);
            setSUIProperty(page, "Prompt.lblPrompt", "Font", "starwarslogo_optimized_56");
            setSUIProperty(page, "bg.caption.lblTitle", "Text", "Shell Commander");
            setSUIProperty(page, "Prompt.lblPrompt", "Editable", "true");
            setSUIProperty(page, "Prompt.lblPrompt", "GetsInput", "true");
            setSUIProperty(page, "btnCancel", "Visible", "false");
            setSUIProperty(page, "btnRevert", "Visible", "false");
            showSUIPage(page);
            flushSUIPage(page);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("toggle"))
        {
            String toggle = tok.nextToken();
            switch (toggle)
            {
                case "on":
                    sendConsoleCommand("/object setCoverVisibility " + self + " 1", self);
                    sendConsoleCommand("/object hide " + self + " 0", self);
                    sendConsoleCommand("/echo You are visible.", self);
                    break;
                case "off":
                    sendConsoleCommand("/object setCoverVisibility " + self + " 0", self);
                    sendConsoleCommand("/object hide " + self + " 0", self);
                    sendConsoleCommand("/echo You are visible.", self);
                    break;
                default:
                    sendConsoleCommand("Usage: /developer toggle [on|off]", self);
                    break;
            }
        }
        if (cmd.equalsIgnoreCase("editWeapon"))
        {
            obj_id weapon = utils.getHeldWeapon(target);
            if (weapon == null)
            {
                return SCRIPT_CONTINUE;
            }
            String mod = tok.nextToken();
            if (mod == null)
            {
                broadcast(self, "No mod specified. See console for valid mods.");
                debugConsoleMsg(self, "/developer editWeapon <mod> <value>");
                debugConsoleMsg(self, "List of valid mods:\nminDamage\nmaxDamage\nattackSpeed\nwoundChance\nattackCost\naccuracy\nelementalType\nelementalValue\nrangeInfo\nresetAllStats\ndamageRadius");
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
                sendSystemMessageTestingOnly(self, "Syntax: /developer openlink url");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String url = tok.nextToken();
                obj_id iTar = getIntendedTarget(self);
                launchClientWebBrowser(iTar, url);
            }
        }
        if (cmd.equalsIgnoreCase("pathToTargetPlanet"))
        {
            obj_id targetId = getIntendedTarget(self);
            obj_id[] players = getPlayerCreaturesInRange(getLocation(targetId), 16000f);
            for (obj_id player : players)
            {
                createClientPathAdvanced(player, getLocation(player), getLocation(targetId), "default");
            }
        }
        if (cmd.equalsIgnoreCase("awardBirthday"))
        {
            obj_id slice = create.createObject("object/tangible/food/crafted/dessert_air_cake.iff", utils.getInventoryContainer(target), "");
            setName(slice, "Slice of Birthday Cake");
            setDescriptionStringId(slice, new string_id("Cut from the most beautiful cake Master Abbub has ever made, this tasty slice will make you feel all cozy inside."));
            attachScript(slice, "developer.bubbajoe.bday_gift");
            broadcast(target, "Happy Birthday from the SWG: Resurgence Team!");

        }
        if (cmd.equals("tableView"))
        {
            String DATATABLE_MASTER_ITEM = "datatables/item/master_item/master_item.iff";
            final String[] columnHeader = {
                    "name",
                    "string_name",
                    "string_detail"
            };
            final String[] columnHeaderType = {
                    "text",
                    "text",
                    "text"
            };
            int numRows = dataTableGetNumRows(DATATABLE_MASTER_ITEM);
            int numColumns = dataTableGetNumColumns(DATATABLE_MASTER_ITEM);
            final String[][] columnData = new String[numRows][numColumns];
            for (int i = 0; i < numRows; i++)
            {
                broadcast(self, String.valueOf(numRows));
                for (int j = 0; j < numColumns; j++)
                {
                    broadcast(self, String.valueOf(numColumns));
                    String columnName = (columnHeader[j]);
                    columnData[i][j] = dataTableGetString(DATATABLE_MASTER_ITEM, i, columnName);
                    broadcast(self, dataTableGetString(DATATABLE_MASTER_ITEM, i, columnName));
                }
            }
            sui.tableColumnMajor(self, self, sui.OK_CANCEL, "DATATABLE VIEWER", "noHandler", "Master Item Table Loaded", columnHeader, columnHeaderType, columnData, true);
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
                sendSystemMessageTestingOnly(self, "Syntax: /admin playeffectatloc <effect name> <x> <y> <z>");
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
                sendSystemMessageTestingOnly(self, "Syntax: /admin playsoundeveryone <sound name>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                obj_id[] players = getAllPlayers(getLocation(self), 8000.0f);
                for (obj_id player : players)
                {
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
                for (obj_id player : players)
                {
                    String sound = tok.nextToken();
                    playClientEffectObj(player, sound, player, "head");
                }
            }
        }
        if (cmd.equalsIgnoreCase("rewardarea"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /developer rewardarea <item> <count>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                String item = tok.nextToken();
                int count = Integer.parseInt(tok.nextToken());
                obj_id[] players = getAllPlayers(getLocation(self), 250.0f);
                for (obj_id player : players)
                {
                    obj_id pInv = utils.getInventoryContainer(player);
                    obj_id pItem = static_item.createNewItemFunction(item, pInv, count);
                    if (isIdValid(pItem))
                    {
                        sendSystemMessageTestingOnly(player, colors_hex.HEADER + colors_hex.ORANGE + "You have been awarded " + count + " " + utils.getStringName(pItem) + " by the Event Team!");
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
            for (obj_id creature : creatures)
            {
                if (isMob(creature))
                {
                    if (hasObjVar(self, "loot.numItems"))
                    {
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
                sendSystemMessageTestingOnly(self, "Syntax: /admin setnumitems <loot count>");
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
            obj_id[] contents = getContents(target);
            for (obj_id content : contents)
            {
                if (getCount(content) > 1)
                {
                    int howMany = utils.stringToInt(tok.nextToken());
                    setCount(content, howMany);
                }
            }
        }
        if (cmd.equalsIgnoreCase("locomotion"))
        {
            int locomotionState = Integer.parseInt(tok.nextToken());
            setLocomotion(self, locomotionState);
        }
        if (cmd.equalsIgnoreCase("state"))
        {
            String toggle = tok.nextToken();
            int state = Integer.parseInt(tok.nextToken());
            if (toggle.equalsIgnoreCase("on"))
            {
                setState(self, state, true);
            }
            else if (toggle.equalsIgnoreCase("off"))
            {
                setState(self, state, false);
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("posture"))
        {
            int posture = Integer.parseInt(tok.nextToken());
            setPosture(self, posture);
        }
        if (cmd.equalsIgnoreCase("randomizecontainer"))
        {
            obj_id[] contents = utils.getContents(target, true);
            for (obj_id content : contents)
            {
                if (getCount(content) > 1)
                {
                    setCount(content, rand(1, 9999));
                }
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
            //liteLog(words);
            System.out.println(words);
        }
        if (cmd.equalsIgnoreCase("sws"))
        {
            String template = tok.nextToken();
            String script = tok.nextToken();
            obj_id item = createObject(template, getLocation(self));
            if (item == null)
            {
                broadcast(self, "Invalid template name.");
                return SCRIPT_CONTINUE;
            }
            if (script == null)
            {
                broadcast(self, "Invalid script name.");
                return SCRIPT_CONTINUE;
            }
            if (getGameObjectType(item) == GOT_building)
            {
                broadcast(self, "Cannot spawn this game object type.");
            }
            attachScript(item, script);
            setYaw(item, getYaw(self));
        }
        if (cmd.equalsIgnoreCase("sendPreviewMessage"))
        {
            String message = tok.nextToken();
            if (tok.hasMoreTokens())
            {
                while (tok.hasMoreTokens())
                {
                    message += " " + tok.nextToken();
                }
            }
            //sendWebhook(APIKEY, message);
        }
        if (cmd.equalsIgnoreCase("ringspawn"))
        {
            String creatureToSpawn = tok.nextToken();
            int num = Integer.parseInt(tok.nextToken());
            float radius = Float.parseFloat(tok.nextToken());
            location where = getLocation(self);
            resurgence.createCircleSpawn(self, self, creatureToSpawn, num, radius);

        }
        if (cmd.equalsIgnoreCase("ringspawninside"))
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
        if (cmd.equalsIgnoreCase("areacommand"))
        {
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /developer areacommand <command> <radius>");
                return SCRIPT_CONTINUE;
            }
            else
            {
                float radius = Float.parseFloat(tok.nextToken());
                String command = tok.nextToken();
                while (tok.hasMoreTokens())
                {
                    command += " " + tok.nextToken();
                }
                obj_id[] players = getPlayerCreaturesInRange(getLocation(self), radius);
                for (obj_id player : players)
                {
                    if (isGod(player))
                    {
                        continue;
                    }
                    else
                    {
                        sendConsoleCommand("/" + command, player);
                    }
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (cmd.equalsIgnoreCase("createLootableCorpse"))
        {
            String[] NAMEs = {
                    "an explorer",
                    "a scavenger",
                    "a slicer",
                    "a soldier",
                    "a technician",
                    "a thief",
                    "a trader",
                    "a smuggler",
                    "a bounty hunter",
                    "a mercenary",
                    "a spy",
                    "a senator"
            };
            if (!tok.hasMoreTokens())
            {
                sendSystemMessageTestingOnly(self, "Syntax: /developer createLootableCorpse <table> <amount>");
            }
            else
            {
                String table = tok.nextToken();
                int amt = Integer.parseInt(tok.nextToken());
                String corpseTemplate = "object/tangible/container/drum/warren_drum_skeleton.iff";
                location treasureLoc = getLocation(self);
                obj_id treasureChest = createObject(corpseTemplate, treasureLoc);
                attachScript (treasureChest, "item.container.loot_crate_opened");
                setName(treasureChest, "a corpse of " + NAMEs[rand(0, NAMEs.length - 1)]);
                loot.makeLootInContainer(treasureChest, table, amt, 300);
                sendSystemMessageTestingOnly(self, "A loot chest was made with " + amt + " items from the loot table: " + table);
                obj_id[] contents = getContents(treasureChest);
                {
                    for (obj_id content : contents)
                    {
                        if (hasScript(content, "item.special.nomove"))
                        {
                            detachScript(content, "item.special.nomove");
                            broadcast(self, "Removing nomove script from " + content);
                        }
                        if (hasObjVar(content, "noTrade"))
                        {
                            removeObjVar(self, "noTrade");
                            broadcast(self, "Removing No-Trade from " + content);
                        }
                    }
                }
            }
            return SCRIPT_CONTINUE;
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
        if (cmd.equals("botHumanoid"))
        {
            String[] RACES = {
                    "bothan",
                    "human",
                    "ithorian",
                    "moncal",
                    "rodian",
                    "sullustan",
                    "twilek",
                    "wookiee",
                    "zabrak"
            };
            int randomIndex = rand(0, RACES.length - 1);
            String randomString = RACES[randomIndex];
            int genderChance = rand(1,100);
            if (genderChance < 49)
            {
                obj_id bot = create.object("object/creature/player/" + randomString + "_male.iff", getLocation(self));
                attachScript(bot, "bot.clone");
            }
            else
            {
                obj_id bot = create.object("object/creature/player/" + randomString + "_female.iff", getLocation(self));
                attachScript(bot, "bot.clone");
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
        if (cmd.equalsIgnoreCase("gotoName"))
        {
            String parse = tok.nextToken();
            obj_id[] targetPool = getCreaturesInRange(getLocation(self), 1000.0f);
            for (obj_id aTargetPool : targetPool)
            {
                if (getFirstName(aTargetPool).equalsIgnoreCase(parse))
                {
                    setLocation(self, getLocation(aTargetPool));
                    sendSystemMessageTestingOnly(self, "Teleported to " + getEncodedName(aTargetPool));
                    return SCRIPT_CONTINUE;
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("removeinvuln"))
        {
            setInvulnerable(iTarget, false);
        }
        if (cmd.equalsIgnoreCase("makeEnt"))
        {
            String[] TEMPLATES = {
                    "object/mobile/dressed_commoner_naboo_human_female_01.iff",
                    "object/mobile/dressed_commoner_naboo_human_female_02.iff",
                    "object/mobile/dressed_commoner_naboo_human_female_03.iff",
                    "object/mobile/dressed_commoner_naboo_human_female_04.iff",
                    "object/mobile/dressed_commoner_naboo_human_female_05.iff",
                    "object/mobile/dressed_commoner_naboo_human_male_01.iff",
                    "object/mobile/dressed_commoner_naboo_human_male_02.iff",
                    "object/mobile/dressed_commoner_naboo_human_male_03.iff",
                    "object/mobile/dressed_commoner_naboo_human_male_04.iff",
                    "object/mobile/dressed_commoner_naboo_human_male_05.iff"
            };
            obj_id entertainer = createObject(TEMPLATES[rand(0, TEMPLATES.length - 1)], getLocation(self));
            setName(entertainer, "a Master Entertainer");
            setInvulnerable(entertainer, true);
            attachScript(entertainer, "ai.ai");
            ai_lib.setMood(entertainer, "themepark_oola");
            attachScript(entertainer, "bot.entertainer");
        }
        if (cmd.equalsIgnoreCase("setinvuln"))
        {
            setInvulnerable(iTarget, true);
        }
        if (cmd.equalsIgnoreCase("commPlanet"))
        {
            String message = tok.nextToken();
            while (tok.hasMoreTokens())
            {
                message += " " + tok.nextToken();
            }
            obj_id[] recipients = getPlayerCreaturesInRange(getLocation(self), 16000.0f);
            for (obj_id recipient : recipients)
            {
                prose_package pp = new prose_package();
                prose.setStringId(pp, new string_id(message));
                commPlayer(self, recipient, pp);
            }
        }
        if (cmd.equalsIgnoreCase("gonkie"))
        {
            obj_id gonkieControlDevice = create.object("object/tangible/loot/generic_usable/frequency_jammer_wire_generic.iff", getLocation(self));
            if (hasScript(gonkieControlDevice, "item.buff_click_item"))
            {
                detachScript(gonkieControlDevice, "item.buff_click_item");
            }
            setName(gonkieControlDevice, "Experimental EG-7 Grenadier Control Device");
            attachScript(gonkieControlDevice, "developer.bubbajoe.xp_gonk");
            setDescriptionStringId(gonkieControlDevice, new string_id("This control device allows users to request an experimental EG-6 unit. The unit will be delivered to the user's current location."));
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("meddroid"))
        {
            obj_id healer = create.object("object/mobile/fx_7_droid.iff", getLocation(self));
            attachScript(healer, "developer.bubbajoe.doctor_droid");
            return SCRIPT_CONTINUE;
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
                for (String s : getScriptList(iTarget))
                {
                    attachScript(iTarget, s);
                    setName(cloned_item, getName(iTarget));
                    utils.copyObjectData(iTarget, cloned_item);
                }
                broadcast(self, "Cloned " + getName(iTarget) + " to " + getName(self) + "'s inventory with " + copies + " copies.");

            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("modvehicle"))
        {
            if (tok.countTokens() < 1)
            {
                sendSystemMessageTestingOnly(self, "Syntax: /admin modvehicle (target) <mod index> <mod value>");
                return SCRIPT_CONTINUE;
            }
            if (vehicle.isRidingVehicle(target))
            {
                obj_id vehid = getMountId(target);
                String vehicleModifier = tok.nextToken();
                float vehicleModifierValue = Float.parseFloat(tok.nextToken());
                vehicle.setValue(vehid, vehicleModifierValue, Integer.parseInt(vehicleModifier));
                return SCRIPT_CONTINUE;
            }
            else
            {
                sendSystemMessageTestingOnly(self, "You are not riding a vehicle.");
            }
        }
        if (cmd.equalsIgnoreCase("copyOnMe"))
        {
            String template = getTemplateName(iTarget);
            sendConsoleCommand("/spawn " + template + " 1 0 0", self);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("copyInMe"))
        {
            String template = getTemplateName(iTarget);
            obj_id pInv = utils.getInventoryContainer(self);
            sendConsoleCommand("/object createIn " + template + " " + pInv, self);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("prepareStaticStrings"))
        {
            String table = "datatables/item/master_item/master_item.iff";
            String clobberedText = "/home/swg/swg-main/exe/linux/static_item_n.txt";
            String clobberedDesc = "/home/swg/swg-main/exe/linux/static_item_d.txt";
            String[] columns = {
                    "name",
                    "string_name",
                    "string_detail"
            };
            int rows = dataTableGetNumRows(table);
            for (int i = 0; i < rows; i++)
            {
                String itemCode =dataTableGetString(table, i, columns[0]);
                String itemName =dataTableGetString(table, i, columns[1]);
                String itemDesc =dataTableGetString(table, i, columns[2]);
                String finalizedFormatName = itemCode + "\t" + itemName + "\n";
                String finalizedFormatDesc = itemCode + "\t" + itemDesc + "\n";
                String strName = finalizedFormatName;
                String strDesc = finalizedFormatDesc;
                BufferedWriter nameWriter = new BufferedWriter(new FileWriter(clobberedText, true));
                nameWriter.append(' ');
                nameWriter.append(strName);
                nameWriter.close();
                BufferedWriter descWriter = new BufferedWriter(new FileWriter(clobberedDesc, true));
                descWriter.append(' ');
                descWriter.append(strDesc);
                descWriter.close();
            }
            broadcast(self, "Attempting to export " + columns.length + " columns worth of data split between " + clobberedText + " and " + clobberedDesc);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("-help"))
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
        else if (cmd.equals(""))
        {
            broadcast(self, "Command not found.  Use -help for a list of commands.");
        }
        return SCRIPT_CONTINUE;
    }

    public String getRandomHumanName(obj_id self)
    {
        return npc.generateRandomName("object/creature/player/human_male.iff");
    }

    private boolean echo(obj_id self, String s)
    {
        return sendConsoleCommand("/echo " + s, self);
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

    public void sendWebhook(String url, String message)
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
        if(hasObjVar(self, "live_qa"))
        {
            sendConsoleCommand( "/object setCoverVisibility " + self + " " + 0, self);
            sendConsoleCommand( "/object hide " + self + " " + 0, self);
            sendConsoleCommand( "/echo You are visible and interactable due to having the 'live_qa' objvar.", self);
            sendConsoleCommand( "/setGodMode 0", self);
        }
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
    private location getO2P(obj_id self, obj_id target)
    {
        location here = getLocation(self);
        location there = getLocation(target);
        float x = here.x - there.x;
        float y = here.y - there.y;
        float z = here.z - there.z;
        return new location(x, y, z);
    }
    public int handleDescribe(obj_id self, dictionary paramsDict) throws InterruptedException
    {
        obj_id myTarget = getIntendedTarget(self);
        String descInput = sui.getInputBoxText(paramsDict);
        if (descInput == null || descInput.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        string_id desc = new string_id(descInput);
        setDescriptionStringId(myTarget, desc);
        setObjVar(myTarget, "null_desc", descInput);
        attachScript(myTarget, "developer.bubbajoe.sync");
        return SCRIPT_CONTINUE;
    }
}


