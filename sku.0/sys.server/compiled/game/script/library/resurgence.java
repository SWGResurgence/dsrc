/*
@Purpose Resurgence Script library

@Author BubbaJoe ...

@Note: please note this whole library was generated with copliot. hard to read.
 */

package script.library;

import script.dictionary;
import script.location;
import script.obj_id;
import script.string_id;

public class resurgence extends script.base_script
{
    public static final string_id SID_PROMPT = new string_id("resurgence", "ui_list_objects_prompt");
    public static final string_id SID_TITLE = new string_id("resurgence", "ui_list_objects_title");

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

    public static void sendToOrigin(obj_id self) throws InterruptedException
    {
        location loc = getLocation(self);
        obj_id planet = getPlanetByName(loc.area);
        warpPlayer(self, loc.area, loc.x, loc.y, loc.z, null, 0, 0, 0);
    }

    public static void listAllPumpkins(obj_id self) throws InterruptedException
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

    public static int moveAllPlayers(obj_id self, dictionary params) throws InterruptedException
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

    public static void pushPlayer(obj_id self, obj_id target, float distance, float angle) throws InterruptedException
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

    public static void renameItems(obj_id self, obj_id target, String name) throws InterruptedException
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

    public static void applyGMNameTag(obj_id self, obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "applyGMNameTag() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "applyGMNameTag() - target is not a player.");
        }
        String name = getName(target);
        String gmName = name + " *GM*";
        setName(target, gmName);
        debugServerConsoleMsg(self, "applyGMNameTag() - GM filter tag applied.");
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

    public static void setRainbowName(obj_id self, obj_id target) throws InterruptedException
    {
        String name = getName(target);
        debugServerConsoleMsg(self, "setRainbowName(" + name + ") - rainbow name applied.");
    }

    public static void relax(obj_id self, obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "relax() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "relax() - target is not a player.");
        }
        setPosture(target, POSTURE_SITTING);
        debugServerConsoleMsg(self, "relax() - player relaxed.");
    }

    public static void suspicious(obj_id self, obj_id target) throws InterruptedException
    {
        if (!isIdValid(target) || !exists(target))
        {
            debugServerConsoleMsg(self, "suspicious() - target is not a valid object.");
        }
        if (!isPlayer(target))
        {
            debugServerConsoleMsg(self, "suspicious() - target is not a player.");
        }
        setPosture(target, POSTURE_CROUCHED);
        debugServerConsoleMsg(self, "suspicious() - player is now suspicious.");
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
            withdrawCashFromBank(target, cash, "bankruptPlayer", null, null);
        }
        if (bank > 0)
        {
            withdrawCashFromBank(target, bank, "bankruptPlayer", null, null);
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

}
