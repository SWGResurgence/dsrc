//please note this whole library was generated with copliot. don't expect everything to work right out of the box.

package script.library;
import script.*;
import script.library.*;
import script.library.sui;

import static script.library.holiday.closeOldWindow;
import static script.library.utils.sendMail;
import static script.script_entry.runScript;

public class resurgence  extends script.base_script {
    public resurgence() {
    }

    public static final string_id SID_PROMPT = new string_id("resurgence", "ui_list_objects_prompt");
    public static final string_id SID_TITLE = new string_id("resurgence", "ui_list_objects_title");

    public static int spawnWithScript(obj_id self, String template, String script) throws InterruptedException {
        location loc = getLocation(self);
        obj_id spawned = create.object(template, loc);
        attachScript(spawned, script);
        return SCRIPT_CONTINUE;
    }

    public static void sendToOrigin(obj_id self) throws InterruptedException {
        location loc = getLocation(self);
        obj_id planet = getPlanetByName(loc.area);
        warpPlayer(self, loc.area, loc.x, loc.y, loc.z, null, 0, 0, 0);
    }

    public static void listAllPumpkins(obj_id self) throws InterruptedException {
        obj_id[] allPumpkins = getAllObjectsWithTemplate(getLocation(self), 1000, "object/tangible/holiday/halloween/pumpkin_object.iff");
        if (allPumpkins == null || allPumpkins.length == 0) {
            sendSystemMessage(self, "No pumpkins found.", null);
            return;
        }
        for (int i = 0; i < allPumpkins.length; i++) {
            sendSystemMessage(self, "Pumpkin: " + allPumpkins[i], null);
        }
    }

    public static void cmdReadyCheck(obj_id self, obj_id target, String params, float defaultTime, dictionary dict) throws InterruptedException {
        obj_id[] players = getGroupMemberIds(self);
        if (players == null || players.length == 0) {
            sendSystemMessage(self, "You are not in a group.", null);
            return;
        }
        if (getGroupLeaderId(self) == self) {
            dict.put("readyCheckLeaderId", self);
            return;
        }
        for (int i = 0; i < players.length; i++) {
            play2dNonLoopingSound(players[i], "sound/utinni.snd");
            sui.msgbox(self, players[i], "The group leader has initialized a ready check.", 1, "READY CHECK", "handleReadyCheck");
        }
    }

    public static void handleReadyCheck(obj_id self, dictionary params) throws InterruptedException {
        int bp = sui.getIntButtonPressed(params);
        obj_id target = params.getObjId("readyCheckLeaderId");
        if (bp == sui.BP_OK) {
            sendSystemMessage(target, "Player is ready.", null);
            chat.chat(self, "Ready.");
        }
        if (bp == sui.BP_CANCEL) {
            sendSystemMessage(target, "Player is not ready.", null);
            chat.chat(self, "Not Ready.");
        }
    }

    public static int presentSui(obj_id self, dictionary params) throws InterruptedException {
        String title = params.getString("title");
        String prompt = params.getString("prompt");
        String[] buttons = params.getStringArray("buttons");
        String handler = params.getString("handler");
        int pid = sui.createSUIPage(sui.SUI_MSGBOX, self, self, handler);
        sui.setSUIProperty(pid, sui.MSGBOX_TITLE, sui.PROP_TEXT, title);
        sui.setSUIProperty(pid, sui.MSGBOX_PROMPT, sui.PROP_TEXT, prompt);
        sui.msgboxButtonSetup(pid, sui.OK_CANCEL);
        sui.setSUIProperty(pid, sui.MSGBOX_BTN_OK, sui.PROP_TEXT, buttons[0]);
        sui.setSUIProperty(pid, sui.MSGBOX_BTN_CANCEL, sui.PROP_TEXT, buttons[1]);
        sui.showSUIPage(pid);
        return SCRIPT_CONTINUE;
    }

    public static int destroySui(obj_id self, dictionary params) throws InterruptedException {
        int pid = params.getInt("pid");
        sui.closeSUI(self, pid);
        return SCRIPT_CONTINUE;
    }

    public static int moveAllPlayers(obj_id self, dictionary params) throws InterruptedException {
        String planet = params.getString("planet");
        float x = params.getFloat("x");
        float y = params.getFloat("y");
        float z = params.getFloat("z");
        obj_id[] players = getAllPlayers(getLocation(self), 1000);
        if (players == null || players.length == 0) {
            sendSystemMessage(self, "No players found.", null);
            return SCRIPT_CONTINUE;
        }
        for (obj_id player : players) {
            warpPlayer(player, planet, x, y, z, null, 0, 0, 0);
        }
        return SCRIPT_CONTINUE;
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

    public static int explode(obj_id self, dictionary params) throws InterruptedException {
        location loc = getLocation(self);
        playClientEffectLoc(self, "clienteffect/combat_explosion_lair_large.cef", loc, 0);
        return SCRIPT_CONTINUE;
    }

    /*public void listAllObjectsByTemplate(obj_id self, obj_id player, String template) throws InterruptedException {
        obj_id[] allObjects = getAllObjectsWithTemplate(getLocation(self), 1000, template);
        if (allObjects == null || allObjects.length == 0) {
            sendSystemMessage(self, "No objects found.", null);
            return;
        }
        for (int i = 0; i < allObjects.length; i++) {
            sendSystemMessage(self, "Object: " + allObjects[i], null);
        }
        String prompt = utils.packStringId(SID_PROMPT);
        String title = utils.packStringId(SID_TITLE);
        int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, allObjects, "handleObjectList");
    }
    public void handleObjectList(obj_id self, dictionary params) throws InterruptedException {

            if (params == null || params.isEmpty()) {
                return;
            }
            obj_id player = sui.getPlayerId(params);
            int btn = sui.getIntButtonPressed(params);
            int idx = sui.getListboxSelectedRow(params);
            if (btn == sui.BP_CANCEL) {
              return;
            }
            if (idx == -1 || idx > SETS.length) {
               return;
            }
            //no objvar to retrieve
            closeOldWindow(player);
    }*/
    public static void renameItems(obj_id self, obj_id target, String name) throws InterruptedException {
        obj_id[] items = getInventoryAndEquipment(target);
        if (items == null || items.length == 0) {
            sendSystemMessage(self, "No items found.", null);
            return;
        }
        for (int i = 0; i < items.length; i++) {
            setName(items[i], name);
        }
    }

    public static void applyGMNameTag(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "applyGMNameTag() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "applyGMNameTag() - target is not a player.");
        }
        String name = getName(target);
        String gmName = name + " *GM*";
        setName(target, gmName);
        debugServerConsoleMsg(self, "applyGMNameTag() - GM filter tag applied.");
    }

    public static void warpToNearestBuilding(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "warpToNearestBuilding() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "warpToNearestBuilding() - target is not a player.");
        }
        location loc = getLocation(target);
        obj_id building = getTopMostContainer(target);
        if (!isIdValid(building) || !exists(building)) {
            debugServerConsoleMsg(self, "warpToNearestBuilding() - target is not in a building.");
        }
        location buildingLoc = getLocation(building);
        warpPlayer(target, buildingLoc.area, buildingLoc.x, buildingLoc.y, buildingLoc.z, null, 0, 0, 0);
        debugServerConsoleMsg(self, "warpToNearestBuilding() - player warped to nearest building.");
    }

    public static void createCreatureGrid(obj_id self, obj_id target, String creature, int rows, int columns, float distance) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "createCreatureGrid() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "createCreatureGrid() - target is not a player.");
        }
        if (rows < 1 || columns < 1) {
            debugServerConsoleMsg(self, "createCreatureGrid() - rows or columns is less than 1.");
        }
        if (distance < 0) {
            debugServerConsoleMsg(self, "createCreatureGrid() - distance is less than 0.");
        }
        location loc = getLocation(target);
        float x = loc.x;
        float z = loc.z;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                obj_id creatureObj = create.object(creature, new location(x, loc.y, z, loc.area));
                x += distance;
            }
            x = loc.x;
            z += distance;
        }
        debugServerConsoleMsg(self, "createCreatureGrid() - creature grid created.");
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

    public static void setRainbowName(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "setRainbowName() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "setRainbowName() - target is not a player.");
        }
        String name = getName(target);
        //String rainbowName = name + "  ^#FF0000^R^#FF7F00^a^#FFFF00^i^#00FF00^n^#0000FF^b^#4B0082^o^#9400D3^w";
        //setName(target, rainbowName);
        debugServerConsoleMsg(self, "setRainbowName() - rainbow name applied.");
    }

    public static void relax(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "relax() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "relax() - target is not a player.");
        }
        setPosture(target, POSTURE_SITTING);
        debugServerConsoleMsg(self, "relax() - player relaxed.");
    }

    public static void suspicious(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "suspicious() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "suspicious() - target is not a player.");
        }
        setPosture(target, POSTURE_CROUCHED);
        debugServerConsoleMsg(self, "suspicious() - player is now suspicious.");
    }

    public static void bankruptPlayer(obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "bankruptPlayer() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "bankruptPlayer() - target is not a player.");
        }
        int cash = getCashBalance(target);
        int bank = getBankBalance(target);
        if (cash > 0) {
            withdrawCashFromBank(target, cash, "bankruptPlayer", null, null);
        }
        if (bank > 0) {
            withdrawCashFromBank(target, bank, "bankruptPlayer", null, null);
        }
        debugServerConsoleMsg(self, "bankruptPlayer() - player is now bankrupt.");
    }
    public static boolean isNear    (obj_id self, obj_id target, float distance) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "isNear() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "isNear() - target is not a player.");
        }
        if (distance < 0) {
            debugServerConsoleMsg(self, "isNear() - distance is less than 0.");
        }
        location loc = getLocation(self);
        location targetLoc = getLocation(target);
        float dist = getDistance(loc, targetLoc);
        return dist <= distance;
    }
    public static void sendPlayerHomeBind(  obj_id self, obj_id target) throws InterruptedException {
        if (!isIdValid(target) || !exists(target)) {
            debugServerConsoleMsg(self, "sendPlayerHomeBind() - target is not a valid object.");
        }
        if (!isPlayer(target)) {
            debugServerConsoleMsg(self, "sendPlayerHomeBind() - target is not a player.");
        }
        location homeBind = getHomeLocation(target);
        warpPlayer(target, homeBind.area, homeBind.x, homeBind.y, homeBind.z, null, 0, 0, 0);
        debugServerConsoleMsg(self, "sendPlayerHomeBind() - player sent to home bind.");
    }

    public static int getNumberOfPlayersInRange(obj_id self, float distance) throws InterruptedException {
        if (distance < 0) {
            debugServerConsoleMsg(self, "getNumberOfPlayersInRange() - distance is less than 0.");
        }
        obj_id[] players = getPlayerCreaturesInRange(getLocation(self), distance);
        return players.length;
    }

    public static void launchWookiepediaPage(obj_id self, String page) throws InterruptedException {
        if (page == null || page.equals("")) {
            debugServerConsoleMsg(self, "launchWookiepediaPage() - page is null or empty.");
        }
        String replacedText = page.replaceAll(" ", "_");
        String url = "http://starwars.wikia.com/wiki/" + page;
        sui.msgbox(self, self, "Opening " + url + " in your browser.", sui.OK_ONLY, "Wookiepedia", "noHandler");
        launchClientWebBrowser(self, url);
        debugServerConsoleMsg(self, "launchWookiepediaPage() - wookiepedia page launched.");
    }

    public static void spawnAllItemsFromTable(obj_id self, String datatable) throws InterruptedException {
        if (datatable == null || datatable.equals("")) {
            debugServerConsoleMsg(self, "spawnAllItemsFromTable() - datatable is null or empty.");
        }
        String[] items = dataTableGetStringColumnNoDefaults(datatable, "item");
        for (int i = 0; i < items.length; i++) {
            obj_id item = createObject(items[i], getLocation(self));
            if (isIdValid(item)) {
                debugServerConsoleMsg(self, "spawnAllItemsFromTable() - item " + items[i] + " spawned.");
            }
        }
    }
    public static void removeAllItemsOfTemplate(obj_id self, String template) throws InterruptedException {
        if (template == null || template.equals("")) {
            debugServerConsoleMsg(self, "removeAllItemsOfTemplate() - template is null or empty.");
        }
        var items = getContents(self);
        for (int i = 0; i < items.length; i++) {
            if (getTemplateName(items[i]).equals(template)) {
                destroyObject(items[i]);
                debugServerConsoleMsg(self, "removeAllItemsOfTemplate() - item " + template + " removed.");
            }
        }
    }
    public  static String getMemoryUsage() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        return Long.toString(memory);
    }
}
