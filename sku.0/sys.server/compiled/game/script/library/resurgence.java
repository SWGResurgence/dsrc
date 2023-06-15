/*
@Purpose Resurgence Script library

@Author BubbaJoe ...

@Note: please note this whole library was generated with copliot. hard to read.
 */

package script.library;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.location;
import script.obj_id;
import script.string_id;

import java.util.Arrays;
import java.util.Random;

public class resurgence extends script.base_script
{
    public static final string_id SID_PROMPT = new string_id("resurgence", "ui_list_objects_prompt");
    public static final string_id SID_TITLE = new string_id("resurgence", "ui_list_objects_title");
    //note: add to this list, do not reindex these values!
    public static int WORLD_BOSS_PEKO = 0;
    public static int WORLD_BOSS_KRAYT = 1;
    public static int WORLD_BOSS_PAX = 2;
    public static int WORLD_BOSS_GIZMO = 3;
    public static int WORLD_BOSS_DONKDONK = 4;
    public static int WORLD_BOSS_AURRA = 5;
    public static int WORLD_BOSS_EMPERORS_HAND = 6;

    public resurgence()
    {
    }

    public static int spawnWithScript(obj_id self, String template, String script) throws InterruptedException
    {
        location loc = getLocation(self);
        obj_id spawned = create.object(template, loc);
        attachScript(spawned, script);
        return SCRIPT_CONTINUE;
    }

    public static int setupLootAmount(obj_id what, int amount)
    {
        setObjVar(what, "loot.numItems", amount);
        return SCRIPT_CONTINUE;
    }

    public static void sendToOrigin(obj_id self)
    {
        location loc = getLocation(self);
        obj_id planet = getPlanetByName(loc.area);
        warpPlayer(self, loc.area, loc.x, loc.y, loc.z, null, 0, 0, 0);
    }

    public static void listAllPumpkins(obj_id self)
    {
        obj_id[] allPumpkins = getAllObjectsWithTemplate(getLocation(self), 1000, "object/tangible/holiday/halloween/pumpkin_object.iff");
        if (allPumpkins == null || allPumpkins.length == 0)
        {
            sendSystemMessage(self, "No pumpkins found.", null);
            return;
        }
        for (obj_id allPumpkin : allPumpkins)
        {
            sendSystemMessage(self, "Pumpkin: " + allPumpkin, null);
        }
    }

    public static int moveAllPlayers(obj_id self, dictionary params)
    {
        String planet = params.getString("planet");
        float x = params.getFloat("x");
        float y = params.getFloat("y");
        float z = params.getFloat("z");
        obj_id[] players = getAllPlayers(getLocation(self), 1000);
        if (players == null || players.length == 0)
        {
            sendSystemMessage(self, "No players found.", null);
            return SCRIPT_CONTINUE;
        }
        for (obj_id player : players)
        {
            warpPlayer(player, planet, x, y, z, null, 0, 0, 0);
        }
        return SCRIPT_CONTINUE;
    }

    public static void pushPlayer(obj_id self, obj_id target, float distance, float angle)
    {

        location loc = getLocation(target);
        float x = loc.x + (float) Math.cos(angle) * distance;
        float z = loc.z + (float) Math.sin(angle) * distance;
        warpPlayer(target, loc.area, x, loc.y, z, null, 0, 0, 0);
        debugServerConsoleMsg(self, "pushPlayer() - player pushed.");
    }

    public static int explode(obj_id self, dictionary params) throws InterruptedException
    {
        location loc = getLocation(self);
        playClientEffectLoc(self, "clienteffect/combat_explosion_lair_large.cef", loc, 0);
        return SCRIPT_CONTINUE;
    }

    public static void renameItems(obj_id self, obj_id target, String name)
    {
        obj_id[] items = getInventoryAndEquipment(target);
        if (items == null || items.length == 0)
        {
            sendSystemMessage(self, "No items found.", null);
            return;
        }
        for (obj_id item : items)
        {
            setName(item, name);
        }
    }

    public static void createCreatureGrid(obj_id self, obj_id target, String creature, int rows, int columns, float distance) throws InterruptedException
    {
        location loc = getLocation(target);
        float x = loc.x;
        float z = loc.z;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                obj_id creatureObj = create.object(creature, new location(x, loc.y, z, loc.area));
                x += distance;
            }
            x = loc.x;
            z += distance;
        }
        debugServerConsoleMsg(self, "createCreatureGrid() - creature grid created.");
    }

    public static void createCircleSpawn(obj_id self, obj_id target, String creature, int amount, float distance) throws InterruptedException
    {
        location loc = getLocation(target);
        float x = loc.x;
        float z = loc.z;
        for (int i = 0; i < amount; i++)
        {
            float angle = (float) (i * (360 / amount));
            x = loc.x + (float) Math.cos(angle) * distance;
            z = loc.z + (float) Math.sin(angle) * distance;
            obj_id creatureObj = create.object(creature, new location(x, loc.y, z, loc.area));
            faceTo(creatureObj, self);
        }
    }

    public static String setRainbowName(obj_id target) throws InterruptedException
    {
        String name = getName(target);
        String rainbowName = "";
        for (int i = 0; i < name.length(); i++)
        {
            Random obj = new Random();
            int rand_num = obj.nextInt(0xffffff + 1);
            rainbowName += "\\#" + rand_num + name.charAt(i);
        }
        return rainbowName;
    }

    public static void relax(obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(target, "relax() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(target, "relax() - target is not a player.");
        }
        setPosture(target, POSTURE_SITTING);
        debugServerConsoleMsg(target, "relax() - player relaxed.");
    }

    public static void suspicious(obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(target, "suspicious() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(target, "suspicious() - target is not a player.");
        }
        setPosture(target, POSTURE_CROUCHED);
        debugServerConsoleMsg(target, "suspicious() - player is now suspicious.");
    }

    public static void sneak(obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(target, "sneak() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(target, "sneak() - target is not a player.");
        }
        setPosture(target, POSTURE_SNEAKING);
        debugServerConsoleMsg(target, "sneak() - player is now suspicious.");
    }

    public static void bankruptPlayer(obj_id self, obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "bankruptPlayer() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "bankruptPlayer() - target is not a player.");
        }
        int cash = getCashBalance(target);
        int bank = getBankBalance(target);
        if (cash > 0)
        {
            withdrawCashFromBank(target, cash, "noHandler", null, null);
        }
        if (bank > 0)
        {
            withdrawCashFromBank(target, bank, "noHandler", null, null);
        }
        debugServerConsoleMsg(self, "bankruptPlayer() - player is now bankrupt.");
    }

    public static boolean isNear(obj_id self, obj_id target, float distance) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "isNear() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "isNear() - target is not a player.");
        }
        if (distance < 0)
        {
            debugServerConsoleMsg(self, "isNear() - distance is less than 0.");
        }
        location loc = getLocation(self);
        location targetLoc = getLocation(target);
        float dist = getDistance(loc, targetLoc);
        return dist <= distance;
    }

    public static void sendPlayerHomeBind(obj_id self, obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "sendPlayerHomeBind() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "sendPlayerHomeBind() - target is not a player.");
        }
        location homeBind = getHomeLocation(target);
        warpPlayer(target, homeBind.area, homeBind.x, homeBind.y, homeBind.z, null, 0, 0, 0);
        debugServerConsoleMsg(self, "sendPlayerHomeBind() - player sent to home bind.");
    }

    public static int getNumberOfPlayersInRange(obj_id self, float distance) throws InterruptedException
    {
        if (distance < 0)
        {
            debugServerConsoleMsg(self, "getNumberOfPlayersInRange() - distance is less than 0.");
        }
        obj_id[] players = getPlayerCreaturesInRange(getLocation(self), distance);
        return players.length;
    }

    public static void launchWookiepediaPage(obj_id self, String page) throws InterruptedException
    {
        if (page == null || page.equals(""))
        {
            debugServerConsoleMsg(self, "launchWookiepediaPage() - page is null or empty.");
        }
        String replacedText = page.replaceAll(" ", "_");
        String url = "http://starwars.wikia.com/wiki/" + page;
        sui.msgbox(self, self, "Opening " + url + " in your browser.", sui.OK_ONLY, "Wookiepedia", "noHandler");
        launchClientWebBrowser(self, url);
        debugServerConsoleMsg(self, "launchWookiepediaPage() - wookiepedia page launched.");
    }

    public static void spawnAllItemsFromTable(obj_id self, String datatable) throws InterruptedException
    {
        if (datatable == null || datatable.equals(""))
        {
            debugServerConsoleMsg(self, "spawnAllItemsFromTable() - datatable is null or empty.");
        }
        String[] items = dataTableGetStringColumnNoDefaults(datatable, "item");
        for (String s : items)
        {
            obj_id item = createObject(s, getLocation(self));
            if (isIdValid(item))
            {
                debugServerConsoleMsg(self, "spawnAllItemsFromTable() - item " + s + " spawned.");
            }
        }
    }

    public static void removeAllItemsOfTemplate(obj_id self, String template) throws InterruptedException
    {
        if (template == null || template.equals(""))
        {
            debugServerConsoleMsg(self, "removeAllItemsOfTemplate() - template is null or empty.");
        }
        var items = getContents(self);
        for (obj_id item : items)
        {
            if (getTemplateName(item).equals(template))
            {
                destroyObject(item);
                debugServerConsoleMsg(self, "removeAllItemsOfTemplate() - item " + template + " removed.");
            }
        }
    }

    public static String getMemoryUsage() throws InterruptedException
    {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        return Long.toString(memory);
    }

    public static void requestMemoryUsage(obj_id self) throws InterruptedException
    {
        String memory = getMemoryUsage();
        sui.msgbox(self, self, "Memory usage: " + memory + " bytes.", sui.OK_ONLY, "Memory Usage", "noHandler");
        debugServerConsoleMsg(self, "requestMemoryUsage() - memory usage requested.");
    }

    public static void warpGroup(obj_id groupId, location loc) throws InterruptedException
    {
        obj_id[] members = getGroupMemberIds(groupId);
        for (obj_id member : members)
        {
            if (isIdValid(member))
            {
                warpPlayer(member, loc.area, loc.x, loc.y, loc.z, null, 0, 0, 0);
            }
        }
    }

    public static void warpGroupCell(obj_id groupId, location loc) throws InterruptedException
    {
        obj_id[] members = getGroupMemberIds(groupId);
        for (obj_id member : members)
        {
            if (isIdValid(member))
            {
                warpPlayer(member, loc.area, loc.x, loc.y, loc.z, loc.cell, 0, 0, 0);
            }
        }
    }

    public static void warpGroupToPlayer(obj_id groupId, obj_id player) throws InterruptedException
    {
        location loc = getLocation(player);
        warpGroup(groupId, loc);
    }

    public static void warpGroupToObjectByName(obj_id groupId, String objectName, float tolerance) throws InterruptedException
    {
        obj_id[] objects = getAllObjectsWithTemplate(getLocation(groupId), tolerance, objectName);
        if (objects == null || objects.length == 0)
        {
            return;
        }
        location loc = getLocation(objects[0]);
        warpGroup(groupId, loc);
    }

    public static void rewardGroup(obj_id group_id, String item, boolean noTrade) throws InterruptedException
    {
        obj_id[] members = getGroupMemberIds(group_id);
        for (obj_id member : members)
        {
            obj_id inventory = utils.getInventoryContainer(member);
            if (isIdValid(member))
            {
                obj_id item_id = createObject(item, inventory, "");
                if (isIdValid(item_id))
                {
                    if (noTrade)
                    {
                        setObjVar(item_id, "noTrade", true);
                    }
                }
            }
        }
    }

    public static String[] getAttackerList(obj_id target) throws InterruptedException
    {
        String[] attackerList = new String[0];
        if (isIdValid(target))
        {
            obj_id[] attackers = getHateList(target);
            if (attackers != null)
            {
                attackerList = new String[attackers.length];
                for (int i = 0; i < attackers.length; i++)
                {
                    if (isPlayer(attackers[i]))
                    {
                        attackerList[i] = getName(attackers[i]);
                    }
                }
            }
        }
        return attackerList;
    }

    public static void doWorldBossAnnounce(obj_id target, int worldboss) throws InterruptedException
    {
        location here = getLocation(target);
        switch (worldboss)
        {
            case 0:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The Mutated Peko-Peko Empress has been reported to have last been on Naboo. The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 1:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The Elder Ancient Krayt Dragon has been reported to have last been seen on Tatooine. The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 2:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\nThe Renegade Pax Vizla has been reported to have been last seen on Dxun near the Abandoned Mandalorian Outpost.The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 3:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The wretched and accursed, Darth Gizmo, has been reported to have been seen last on Endor at one of the Lake Villages. The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 4:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The wanted criminal, Donk-Donk Binks, has been reported to have been seen near the Rorgungan Lake Village on Rori. The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 5:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The assassin, Aurra Sing, has been reported to have been seen on an island on Naboo. The Czerka Corporation is paying a high price for it's remains.");
                break;
            case 6:
                notifyGalacticFeed("ATTENTION GALACTIC BOUNTY HUNTERS:\n The Hand of his Royal Majesty, The Emperor himself, has been located near the Emperor's Retreat on Naboo. The Czerka Corporation is paying a high price for her dissected remains, and any Jedi that she has captured.");
        }
    }

    public static void doWorldBossDeathMsg(obj_id target) throws InterruptedException
    {
        String[] attackerList = getAttackerList(target);
        if (attackerList.length > 0)
        {
            String msg = "The world boss " + getEncodedName(target) + " has been defeated by the following adventurers: " + toUpper(attackerList[0], 0);
            for (int i = 1; i < attackerList.length; i++)
            {
                msg += ", " + toUpper(attackerList[i], 0);
            }
            msg += ". Congratulations to all!";
            notifyGalacticFeed(msg);
        }
    }

    public int broadcastGroup(obj_id player, String message)
    {
        obj_id group = getGroupObject(player);
        if (isIdValid(group))
        {
            obj_id[] members = getGroupMemberIds(group);
            for (obj_id member : members)
            {
                if (isIdValid(member))
                {
                    broadcast(member, message);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int placePlayersAroundPoint(location point)
    {
        obj_id[] targets = getAllPlayers(point, 100.0f);
        for (obj_id player : targets)
        {
            // Make sure the player is valid
            if (isIdValid(player))
            {
                if (isPlayer(player))
                {
                    //place them in a ring facing the point.
                    float angle = rand(0, 360);
                    float distance = rand(1, 10);
                    float x = point.x + (float) Math.cos(angle) * distance;
                    float z = point.z + (float) Math.sin(angle) * distance;
                    warpPlayer(player, point.area, x, point.y, z, null, 0, 0, 0);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int placePlayersInGridFormation(location x1)
    {
        obj_id[] targets = getAllPlayers(x1, 100.0f);
        for (obj_id player : targets)
        {
            // Make sure the player is valid
            if (isIdValid(player))
            {
                if (isPlayer(player))
                {
                    //make a 10 x 10 grid and plot each player in a random spot in the grid.
                    float x = rand(x1.x - 5, x1.x + 5);
                    float z = rand(x1.z - 5, x1.z + 5);
                    warpPlayer(player, x1.area, x, x1.y, z, null, 0, 0, 0);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int listCreaturesAlphabetically(obj_id who, float radius, location where) throws InterruptedException
    {
        obj_id[] targets = getCreaturesInRange(where, radius);
        String[] names = new String[targets.length];
        for (int i = 0; i < targets.length; i++)
        {
            names[i] = getName(targets[i]);
        }
        Arrays.sort(names);
        sui.listbox(who, who, "Creatures in range:", sui.OK_ONLY, "Area Tracking", names, "handleTrackingSelection");
        return SCRIPT_CONTINUE;
    }

    public int handleTrackingSelection(obj_id self, dictionary params) throws InterruptedException
    {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        // Get the list of names but we must use the index because the list isn't baked.
        int index = sui.getListboxSelectedRow(params);
        String name = sui.getListboxSelectedRowText(params);
        obj_id[] targets = getCreaturesInRange(getLocation(self), 100.0f);
        for (obj_id target : targets)
        {
            if (getName(target).equals(name))
            {
                debugSpeakMsg(self, "I found " + name + " at " + getLocation(target));
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void echoToGroup(obj_id group, String message) throws InterruptedException
    {
        obj_id[] members = getGroupMemberIds(group);
        for (obj_id member : members)
        {
            if (isIdValid(member))
            {
                sendConsoleMessage(member, message);
            }
        }
    }

    public void disarmGroup(obj_id group) throws InterruptedException
    {
        obj_id[] members = getGroupMemberIds(group);
        for (obj_id member : members)
        {
            obj_id heldWeapon = getCurrentWeapon(member);
            if (isIdValid(heldWeapon))
            {
                putIn(heldWeapon, utils.getInventoryContainer(member));
            }
        }
    }

    public void disarmPlayer(obj_id player) throws InterruptedException
    {
        obj_id heldWeapon = getCurrentWeapon(player);
        if (isIdValid(heldWeapon))
        {
            putIn(heldWeapon, utils.getInventoryContainer(player));
        }
    }

    public void ringBomb(obj_id player, location where, float radius)
    {
        String clientEffect = "clienteffect/bacta_bomb.cef";
        float angle = rand(0, 360);
        float distance = rand(1, 10);
        float x = where.x + (float) Math.cos(angle) * distance;
        float z = where.z + (float) Math.sin(angle) * distance;
        float y = getHeightAtLocation(x, z);
        location targetPoint = new location(x, y, z);
        playClientEffectLoc(player, clientEffect, targetPoint, 0);
    }

    public void stripPlayer(obj_id player) throws InterruptedException
    {
        obj_id[] possessions = utils.getAllItemsInBankAndInventory(player);
        for (obj_id possession : possessions)
        {
            if (isIdValid(possession))
            {
                destroyObject(possession);
            }
            else
            {
                LOG("bubbajoe", "stripPlayer() - possession is invalid. ID: " + possession);
            }
        }
    }

    public int downloadCharacterData(obj_id avatar)
    {
        return SCRIPT_CONTINUE;
    }

    public void doClientEffect(obj_id[] players, String clientEffect) throws InterruptedException
    {
        for (obj_id player : players)
        {
            playClientEffectObj(player, clientEffect, player, "");
        }
    }

    public void doParticleEffect(obj_id[] players, String particleEffect) throws InterruptedException
    {
        for (obj_id player : players)
        {
            playClientEffectObj(player, particleEffect, player, "");
        }
    }
}
