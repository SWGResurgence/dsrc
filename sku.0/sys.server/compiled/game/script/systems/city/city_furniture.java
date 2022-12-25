package script.systems.city;

import script.*;
import script.library.*;

public class city_furniture extends script.base_script
{
    public static final string_id SID_PLACE = new string_id("city/city", "place");
    public static final string_id SID_MT_REMOVE = new string_id("city/city", "mt_remove");
    public static final string_id SID_MT_REMOVED = new string_id("city/city", "mt_removed");
    public static final string_id SID_DECO_TOO_CLOSE = new string_id("city/city", "deco_too_close");
    public static final string_id SID_NO_MORE_DECOS = new string_id("city/city", "no_more_decos");
    public static final string_id SID_ALIGN = new string_id("city/city", "align");
    public static final string_id SID_NORTH = new string_id("city/city", "north");
    public static final string_id SID_SOUTH = new string_id("city/city", "south");
    public static final string_id SID_EAST = new string_id("city/city", "east");
    public static final string_id SID_WEST = new string_id("city/city", "west");
    public static final string_id SID_MOVE = new string_id("Movement *");
    public static final string_id SID_MOVE_FORWARD = new string_id("Move Forward");
    public static final string_id SID_MOVE_BACKWARD = new string_id("Move Backward");
    public static final string_id SID_MOVE_LEFT = new string_id("Move Left");
    public static final string_id SID_MOVE_RIGHT = new string_id("Move Right");
    public static final string_id SID_MOVE_UP = new string_id("Move Up");
    public static final string_id SID_MOVE_DOWN = new string_id("Move Down");
    public static final string_id SID_MOVE_TO_ME = new string_id("Move Decoration to Self");
    public static final string_id SID_MOVE_TO_Y = new string_id("Move Decoration to Ground");
    public static final string_id SID_NAME = new string_id("Set Decoration Name");
    public static final string_id NO_SKILL_DECO = new string_id("You do not have the skill to place this decoration.");
    public static final String CITY_DECORATIONS = "datatables/city/decorations.iff";
    public static final string_id SID_CIVIC_ONLY = new string_id("city/city", "civic_only");


    public int OnInitialize(obj_id self) throws InterruptedException
    {
        LOG("sissynoid", "Initializing City Decoration ");
        int city_id = getCityAtLocation(getLocation(self), 0);
        if (city_id <= 0)
        {
            LOG("sissynoid", "Initializing City Decoration " + self + " isInWorldCell(self) = " + isInWorldCell(self) + " getContainer(self) = " + getContainedBy(self) + " hasObjVar(self, 'city_id') = " + hasObjVar(self, "city_id"));
            if (isInWorldCell(self))
            {
                LOG("sissynoid", "City Decorations: OnInitialize: City ID is invalid for Decoration - Requesting Destruction of City Decoration(" + self + " : " + getTemplateName(self) + ")");
                CustomerServiceLog("player_city_transfer", "City Decorations: OnInitialize: City ID is invalid for Decoration - Requesting Destruction of City Decoration(" + self + " : " + getTemplateName(self) + ")");
                return SCRIPT_CONTINUE;
            }
            else
            {
                return SCRIPT_CONTINUE;
            }
        }
        obj_id currentMayor = cityGetLeader(city_id);
        obj_id decorationOwner = getOwner(self);
        if (!isIdValid(currentMayor))
        {
            LOG("sissynoid", "City Decorations: OnInitialize: Invalid Mayor ID(" + currentMayor + ") for City(" + city_id + ") when Initializing City Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing out until next initialization.");
            CustomerServiceLog("player_city_transfer", "City Decorations: OnInitialize: Invalid Mayor ID(" + currentMayor + ") for City(" + city_id + ") when Initializing City Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing out until next initialization.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, "returnObjectToOwner"))
        {
            LOG("sissynoid", "Initializing City Decoration: Found Decoration to Send to Owner");
            if (currentMayor != decorationOwner)
            {
                LOG("sissynoid", "Initializing City Decoration: Owner != Mayor - Moving Item.");
                if (moveCityDecorationToOwnerInventory(decorationOwner, self))
                {
                    LOG("sissynoid", "Successfully Moved Decoration - Removing from City Decor List");
                    city.removeDecoration(self);
                }
            }
        }
        else
        {
            LOG("sissynoid", "Initializing City Decoration: Not a Move-To-Inventory Decoration");
            setOwner(self, currentMayor);
            city.validateSpecialStructure(self);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        city.removeDecoration(self);
        return SCRIPT_CONTINUE;
    }

    public int requestDestroy(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "returnObjectToOwner"))
        {
            obj_id decorationOwner = getOwner(self);
            if (!isIdValid(decorationOwner))
            {
                LOG("sissynoid", "City Decorations: requestDestroy: Invalid Decoration Owner(" + decorationOwner + ") for City Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing out until next initialization.");
                CustomerServiceLog("player_city_transfer", "City Decorations: requestDestroy: Invalid Decoration Owner(" + decorationOwner + ") for City Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing out until next initialization.");
                return SCRIPT_CONTINUE;
            }
            if (moveCityDecorationToOwnerInventory(decorationOwner, self))
            {
                LOG("sissynoid", "Successfully sent Object (" + self + ") to Player(" + decorationOwner + ")");
                city.removeDecoration(self);
            }
        }
        else
        {
            LOG("sissynoid", "Destroying object - not a 'return-to-owner' object/decoration");
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (canPlaceItem(self, player))
        {
            if (isInWorldCell(self) && (!hasObjVar(self, "city_id")))
            {
                mi.addRootMenu(menu_info_types.SERVER_MENU1, SID_PLACE);
            }
        }
        if (canManipulate(self, player))
        {
            if (isInWorldCell(self))
            {
                int movement = mi.addRootMenu(menu_info_types.SERVER_MENU10, SID_MOVE);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU11, SID_MOVE_FORWARD);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU12, SID_MOVE_BACKWARD);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU13, SID_MOVE_LEFT);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU14, SID_MOVE_RIGHT);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU15, SID_MOVE_UP);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU16, SID_MOVE_DOWN);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU17, SID_MOVE_TO_ME);
                mi.addSubMenu(movement, menu_info_types.SERVER_MENU19, SID_MOVE_TO_Y);
                mi.addRootMenu(menu_info_types.SERVER_MENU18, SID_NAME);
                int menu = mi.addRootMenu(menu_info_types.SERVER_MENU3, SID_ALIGN);
                mi.addSubMenu(menu, menu_info_types.SERVER_MENU4, SID_NORTH);
                mi.addSubMenu(menu, menu_info_types.SERVER_MENU5, SID_SOUTH);
                mi.addSubMenu(menu, menu_info_types.SERVER_MENU6, SID_EAST);
                mi.addSubMenu(menu, menu_info_types.SERVER_MENU7, SID_WEST);
            }
        }
        region[] rgnTest = getRegionsWithBuildableAtPoint(getLocation(player), regions.BUILD_FALSE);
        if (rgnTest != null)
        {
            if (getIntObjVar(self, "city_id") != 0)
            {
                obj_id pinv = utils.getInventoryContainer(player);
                if (putIn(self, pinv))
                {
                    removalCleanup(self, player, true);
                }
            }
            return SCRIPT_CONTINUE;
        }
        else
        {
            if (isInWorldCell(player))
            {
                int city_id = getCityAtLocation(getLocation(player), 0);
                boolean isMayor = city.isTheCityMayor(player, city_id);
                if (getOwner(self) == player)
                {
                    mi.addRootMenu(menu_info_types.ITEM_PICKUP, SID_MT_REMOVE);
                }
                else if (isMayor)
                {
                    mi.addRootMenu(menu_info_types.SERVER_MENU2, SID_MT_REMOVE);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    private boolean canManipulate(obj_id self, obj_id player) throws InterruptedException
    {
        if (isGod(player))
        {
            return true;
        }
        if (getOwner(self) == player)
        {
            return true;
        }
        int city_id = getCityAtLocation(getLocation(player), 0);
        if (city_id == 0)
        {
            return false;
        }
        return city.isTheCityMayor(player, city_id);
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        location loc = getLocation(self);
        sendDirtyObjectMenuNotification(self);
        if (!hasObjVar(player, "city.movementRate"))
        {
            setObjVar(player, "city.movementRate", 1);
        }
        int movementRate = getIntObjVar(player, "city.movementRate");
        int city_id = city.getCityAtLocation(loc, 0);
        if (city_id == 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            placeDecoration(city_id, player, self);
        }
        else if (item == menu_info_types.SERVER_MENU2 || item == menu_info_types.ITEM_PICKUP)
        {
            obj_id pinv = utils.getInventoryContainer(player);
            if (putIn(self, pinv))
            {
                removalCleanup(self, player, true);
            }
        }
        else if (item == menu_info_types.SERVER_MENU4)
        {
            setYaw(self, 0);
        }
        else if (item == menu_info_types.SERVER_MENU5)
        {
            setYaw(self, 180);
        }
        else if (item == menu_info_types.SERVER_MENU6)
        {
            setYaw(self, 90);
        }
        else if (item == menu_info_types.SERVER_MENU7)
        {
            setYaw(self, -90);
        }
        else if (item == menu_info_types.SERVER_MENU10)
        {
            int pid = sui.inputbox(self, player, "Please enter a three digit number to use when moving decorations.. \n\n\n This value cannot be greater than 250.,", "Decoration Movement", "handleMovementIncrement", 3, false, "1");
            sui.setSUIProperty(pid, sui.INPUTBOX_PROMPT, "Font", "starwarslogo_optimized_56");
        }
        else if (item == menu_info_types.SERVER_MENU11)
        {
            loc.z = loc.z + movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU12)
        {
            loc.z = loc.z - movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU13)
        {
            loc.x = loc.x - movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU14)
        {
            loc.x = loc.x + movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU15)
        {
            if (movementRate > 250)
            {
                broadcast(player, "You cannot move a decoration UP more than 250 meters at a time.");
            }
            loc.y = loc.y + movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU16)
        {
            if (movementRate > 250)
            {
                broadcast(player, "You cannot move a decoration DOWN more than 250 meters at a time.");
            }
            loc.y = loc.y -= movementRate;
            setLocation(self, loc);
        }
        else if (item == menu_info_types.SERVER_MENU17)
        {
            location pLoc = getLocation(player);
            setLocation(self, pLoc);
        }
        else if (item == menu_info_types.SERVER_MENU18)
        {
            int pid =sui.inputbox(self, player, "Please enter a name for this decoration. \n\n\n Enter one space into the field for this object to have no name,", "Decoration Name", "handleDecorationName", 126, false, getName(self));
            sui.setSUIProperty(pid, sui.INPUTBOX_PROMPT, "Font", "starwarslogo_optimized_56");
        }
        else if (item == menu_info_types.SERVER_MENU18)
        {
            snapToGround(self);
        }
        return SCRIPT_CONTINUE;
    }

    public int handleMovementIncrement(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null || params.isEmpty())
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        int amount = utils.stringToInt(sui.getInputBoxText(params));
        if (amount == 0)
        {
            broadcast(player, "Amount must be greater than or equal to 1.");
            return SCRIPT_CONTINUE;
        }
        if (amount > 250)
        {
            broadcast(player, "Amount must be less than or equal to 250.");
            return SCRIPT_CONTINUE;
        }
        setObjVar(player, "city.movementRate", amount);
        sendSystemMessageTestingOnly(player, "Movement rate set: " + amount);
        return SCRIPT_CONTINUE;
    }

    public void snapToGround(obj_id self)
    {
        location loc = getLocation(self);
        location groundLoc = new location( loc.x, getHeightAtLocation(loc.x, loc.z), loc.z, getCurrentSceneName(), loc.cell);
        setLocation(self, groundLoc);
    }

    public void handleDecorationName(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        String name = sui.getInputBoxText(params);
        if (name == null || name.equals(""))
        {
            setName(self, "");
        }
        setObjVar(self, "city.decorationName", name);
        setName(self, name);
        broadcast(player, "Decoration has been renamed to: " + name);
    }

    public void placeDecoration(int city_id, obj_id player, obj_id self) throws InterruptedException
    {
        if (!hasCommand(player, "grantZoningRights")) //@note this is basically a hack check to make sure players have politician to place anything.
        {
            sendSystemMessage(player, NO_SKILL_DECO);
            return;
        }
        location loc = getLocation(player);
        int maxd = city.getMaxDecorationCount(city_id);
        int curd = city.getDecorationCount(city_id);
        if (curd + 1 > maxd)
        {
            sendSystemMessage(player, SID_NO_MORE_DECOS);
            return;
        }
        obj_id structure = getTopMostContainer(player);
        if ((!isIdValid(structure)) && (structure != player))
        {
            if (!player_structure.isCivic(structure))
            {
                sendSystemMessage(player, SID_CIVIC_ONLY);
                return;
            }
        }
        String name = getTemplateName(self);
        setLocation(self, loc);
        setYaw(self, getYaw(player));
        if (isGod(player))
        {
            chat.chat(self, "[GodMode] I am placing " + name + " in " + cityGetName(city_id));
        }
        else
        {
            chat.chat(self, "Oh yes, that looks nice!");
        }
        city.addDecoration(city_id, player, self);
    }

    public int OnTransferred(obj_id self, obj_id sourceContainer, obj_id destContainer, obj_id transferer) throws InterruptedException
    {
        obj_id player = transferer;
        if (isIdValid(player))
        {
            removalCleanup(self, player, false);
        }
        return SCRIPT_CONTINUE;
    }

    public void removalCleanup(obj_id object, obj_id player, boolean spam) throws InterruptedException
    {
        removeObjVar(object, "city_id");
        city.removeDecoration(object);
        if (spam)
        {
            sendSystemMessage(player, SID_MT_REMOVED);
        }
    }

    public int setNewMayor(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("sissynoid", "Entered: Setting New Mayor");
        if (params == null || params.isEmpty())
        {
            LOG("sissynoid", "City Decorations: setNewMayor: Failed to transfer Decoration(" + self + ") to New Mayor due to Empty Params.");
            CustomerServiceLog("player_city_transfer", "City Decorations: setNewMayor: Failed to transfer Decoration(" + self + ") to New Mayor due to Empty Params.");
            return SCRIPT_CONTINUE;
        }
        obj_id new_mayor = params.getObjId("mayor");
        if (!isIdValid(new_mayor) || !isIdValid(self))
        {
            LOG("sissynoid", "City Decorations: setNewMayor: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid Mayor/Self ID.");
            CustomerServiceLog("player_city_transfer", "City Decorations: setNewMayor: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid Mayor/Self ID.");
            return SCRIPT_CONTINUE;
        }
        updateOwnerAndAdmin(self, new_mayor);
        return SCRIPT_CONTINUE;
    }

    public void updateOwnerAndAdmin(obj_id self, obj_id new_mayor) throws InterruptedException
    {
        if (!isIdValid(new_mayor) || !isIdValid(self))
        {
            LOG("sissynoid", "City Decorations: updateOwnerAndAdmin: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid Mayor/Self ID.");
            CustomerServiceLog("player_city_transfer", "City Decorations: updateOwnerAndAdmin: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid Mayor/Self ID.");
            return;
        }
        int city_id = getCityAtLocation(getLocation(self), 0);
        if (city_id <= 0)
        {
            LOG("sissynoid", "City Decorations: updateOwnerAndAdmin: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid City ID (" + city_id + "). Used getCityAtLocation - using Self");
            CustomerServiceLog("player_city_transfer", "City Decorations: updateOwnerAndAdmin: Failed to transfer Structure(" + self + ") to New Mayor (" + new_mayor + ") due to Invalid City ID (" + city_id + "). Used getCityAtLocation - using Self");
            return;
        }
        if (!city.isDecoration(city_id, self))
        {
            LOG("sissynoid", "City Decorations: updateOwnerAndAdmin: Did not transfer Decoration(" + self + ") - Is not a Decoration");
            CustomerServiceLog("player_city_transfer", "City Decorations: updateOwnerAndAdmin: Did not transfer Decoration(" + self + ") - Is not a Decoration");
            return;
        }
        if (hasObjVar(self, "returnObjectToOwner"))
        {
            LOG("sissynoid", "Object Needing transferred to Owner's Inventory (" + self + ")");
            obj_id decorOwner = getOwner(self);
            if (!isIdValid(decorOwner))
            {
                LOG("sissynoid", "City Decorations: updateOwnerAndAdmin: (ObjVar)returnObjectToOwner: Invalid Decoration Owner for Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing until next attempt.");
                CustomerServiceLog("player_city_transfer", "City Decorations: updateOwnerAndAdmin: (ObjVar)returnObjectToOwner: Invalid Decoration Owner for Decoration(" + self + " : " + getTemplateName(self) + ") - Bailing until next attempt.");
                return;
            }
            if (decorOwner != new_mayor)
            {
                LOG("sissynoid", "New Mayor (" + new_mayor + ") does not equal Decor Owner (" + decorOwner + ") Decoration(" + self + ")");
                if (moveCityDecorationToOwnerInventory(decorOwner, self))
                {
                    LOG("sissynoid", "Transfer to Inventory (" + decorOwner + ") Complete: (" + self + ")");
                    city.removeDecoration(self);
                }
            }
        }
        else
        {
            obj_id decorOwner = getOwner(self);
            LOG("sissynoid", "Setting Owner of | Decoration (" + self + ") | from(" + decorOwner + ") | to(" + new_mayor + ")");
            setOwner(self, new_mayor);
        }
        return;
    }

    public boolean moveCityDecorationToOwnerInventory(obj_id player, obj_id item) throws InterruptedException
    {
        if (!isIdValid(item) || !isIdValid(player))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: Invalid ID: Player(" + player + ") or Item(" + item + ") - Can Not move to player's inventory at this time.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: Invalid ID: Player(" + player + ") or Item(" + item + ") - Can Not move to player's inventory at this time.");
            return false;
        }
        boolean isValidObject = true;
        obj_id objectOwner = getOwner(item);
        if (player != objectOwner)
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: Player(" + player + ") is not the objectOwner(" + objectOwner + ") of Object: City Decoration(" + item + " : " + getTemplateName(item) + ")");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: Player(" + player + ") is not the objectOwner(" + objectOwner + ") of Object: City Decoration(" + item + " : " + getTemplateName(item) + ")");
            isValidObject = false;
        }
        if (!hasObjVar(item, "returnObjectToOwner"))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") does not have the 'returnObjectToOwner' objvar - Not Transfering to player.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") does not have the 'returnObjectToOwner' objvar - Not Transfering to player.");
            isValidObject = false;
        }
        if (!isObjectPersisted(item))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is not a persisted object - not transfering object to player");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is not a persisted object - not transfering object to player");
            isValidObject = false;
        }
        if (isPlayer(item))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a player! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a player! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (isMob(item))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Mob! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Mob! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (hasCondition(item, CONDITION_VENDOR))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Vendor! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Vendor! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (getContainerType(item) != 0)
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Container! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is a Container! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (utils.isNestedWithinAPlayer(item, true))
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is already contained by a player! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is already contained by a player! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (!isInWorld(item) || !isInWorldCell(item) || getTopMostContainer(item) != item)
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is already contained by a ! Not transfering the object to player's inventory.");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is already contained by a player! Not transfering the object to player's inventory.");
            isValidObject = false;
        }
        if (isValidObject)
        {
            LOG("sissynoid", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is being transferred to Owner's Inventory. Owner ID(" + player + ")");
            CustomerServiceLog("player_city_transfer", "City Decorations: moveCityDecorationToOwnerInventory: City Decoration(" + item + " : " + getTemplateName(item) + ") is being transferred to Owner's Inventory. Owner ID(" + player + ")");
            removeObjVar(item, "city_id");
            moveToOfflinePlayerInventoryAndUnload(item, player);
        }
        return isValidObject;
    }

    public boolean canPlaceItem(obj_id self, obj_id player) throws InterruptedException
    {
        //@note: keep these in order of importance, with the most important last
        int city_id = getCityAtLocation(getLocation(player), 0);
        boolean isMayor = city.isTheCityMayor(player, city_id);
        if (hasObjVar(self, "city_id"))
        {
            return false;
        }
        if (hasObjVar(player, "city_decorator"))
        {
            return true;
        }
        if (city.isMilitiaOfCity(player, city_id))
        {
            return true;
        }
        if (isMayor)
        {
            return true;
        }
        return isGod(player);
    }
}
