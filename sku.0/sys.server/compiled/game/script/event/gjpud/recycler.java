package script.event.gjpud;

import script.*;
import script.base_class.*;
import script.base_script;

import script.library.*;

public class recycler extends script.base_script
{
    public recycler()
    {
    }
    public static String REWARD_SETUP_TITLE = "GJPUD REWARD MENU";
    public static String REWARD_SETUP_PROMPT = "Paste a static item string here, from master_item table, for the player to be rewarded. You are modifying Tier: ";
    public static final int CASH_AMOUNT = rand(15000, 50000);
    public static final String STF = "city/city";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Recycle Item"));
        int menu2 = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Check Score"));
        if(isGod(player))
        {
            int menu3 = mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("[GodMode] Recycler Menu"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU3, new string_id("Clear Rewards"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU4, new string_id("Clear Points"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU5, new string_id("Set Item to Recycle"));
            int tiersettings = mi.addRootMenu(menu_info_types.SERVER_MENU6, new string_id("[GodMode] Modify Rewards"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU7, new string_id("Set Tier 1 Reward"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU8, new string_id("Set Tier 2 Reward"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU9, new string_id("Set Tier 3 Reward"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU10, new string_id("Set Tier 4 Reward"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU11, new string_id("Set Tier 5 Reward"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Galactic Junk Pickup Day Terminal");
        //persistObject(self);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Galactic Junk Pickup Day Terminal");
        //persistObject(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            String ITEM = getStringObjVar(self, "item_to_use");
            if (utils.playerHasItemByTemplateInInventoryOrEquipped(player, ITEM))
            {
                int stamps = getIntObjVar(player, "item_turn_in.stamps");
                String strTemplate = ITEM;
                obj_id objPlayer = player;
                obj_id item_to_delete = utils.getItemPlayerHasByTemplate(objPlayer, strTemplate); // This will be the first item it can find. It's not picky.
                if (getCount(item_to_delete) > 1)
                {
                    setCount(item_to_delete, getCount(item_to_delete) - 1);
                    if (getCount(item_to_delete) == 1)
                    {
                        destroyObject(item_to_delete);
                    }
                }
                else
                {
                    destroyObject(item_to_delete);
                }
                if (stamps > 50)
                {
                    sendSystemMessageTestingOnly(player, "Thank you!");
                }
                else if (stamps < 50)
                {
                    sendSystemMessageTestingOnly(player, "You have successfully recycled an item and have earned one (1) point toward a reward. You now have a total of " + stamps + " points.");
                }
                if (!hasObjVar(player, "item_turn_in.stamps"))
                {
                    setObjVar(player, "item_turn_in.stamps", 1);
                }
                else if (hasObjVar(player, "item_turn_in.stamps"))
                {
                    int currentStamps = getIntObjVar(player, "item_turn_in.stamps");
                    setObjVar(player, "item_turn_in.stamps", currentStamps + 1);
                    checkForRewards(self, player);
                }
                if (!hasObjVar(self, "event.total_recycled"))
                {
                    setObjVar(self, "event.total_recycled", 1);
                }
                else
                {
                    int current = getIntObjVar(self, "event.total_recycled");
                    setObjVar(self, "event.total_recycled", current++);
                }
                setObjVar(self, "last_used", getPlayerFullName(player));
            }
            else if (!utils.playerHasItemByTemplate(player, getStringObjVar(self, "item_to_use")))
            {
                sendSystemMessageTestingOnly(player, "You do not have anything to recycle at this time.");
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            String title = "GALACTIC JUNK PICKUP DAY";
            String buffer = "You current have " + getIntObjVar(player, "item_turn_in.stamps") + " recycle points!";
            sui.msgbox(self, player, buffer, title);
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            sendSystemMessageTestingOnly(player, "This menu will reset rewards, or clear points. choose wisely my dudes");
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            removeObjVar(player, "stamps.t1_reward_given");
            removeObjVar(player, "stamps.t2_reward_given");
            removeObjVar(player, "stamps.t3_reward_given");
            removeObjVar(player, "stamps.t4_reward_given");
            removeObjVar(player, "stamps.t5_reward_given");
            sendSystemMessageTestingOnly(player, "Rewards reset!");
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            setObjVar(player, "item_turn_in.stamps", 0);
            sendSystemMessageTestingOnly(player, "Recycle Points reset to zero!");
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            String objectToCheck = "";
            String buffer = "Paste a non-shared object template here to check. This item should ideally be a custom object related to GJPUD.";
            String title = "Item Check";
            sui.inputbox(self, player, buffer, title, "handleCheckRequest", objectToCheck);
        }
        if (item == menu_info_types.SERVER_MENU7)
        {
            String t1ToCheck = "";
            String buffer = REWARD_SETUP_PROMPT + " 1";
            String title = REWARD_SETUP_TITLE;
            sui.inputbox(self, player, buffer, title, "handleT1Request", t1ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU8)
        {
            String t2ToCheck = "";
            String buffer = REWARD_SETUP_PROMPT + " 2";
            String title = REWARD_SETUP_TITLE;
            sui.inputbox(self, player, buffer, title, "handleT2Request", t2ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            String t3ToCheck = "";
            String buffer = REWARD_SETUP_PROMPT + " 3";
            String title = REWARD_SETUP_TITLE;
            sui.inputbox(self, player, buffer, title, "handleT3Request", t3ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            String t4ToCheck = "";
            String buffer = REWARD_SETUP_PROMPT + " 4";
            String title = REWARD_SETUP_TITLE;
            sui.inputbox(self, player, buffer, title, "handleT4Request", t4ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU11)
        {
            String t5ToCheck = "";
            String buffer = REWARD_SETUP_PROMPT + " 5";
            String title = REWARD_SETUP_TITLE;
            sui.inputbox(self, player, buffer, title, "handleT5Request", t5ToCheck);
        }
        return SCRIPT_CONTINUE;
    }
    public int checkForRewards(obj_id self, obj_id player) throws InterruptedException
    {
        int stamps = getIntObjVar(player, "item_turn_in.stamps");
        if (stamps > 50)
        {
            payCredits(player);
        }
        if (stamps == 10 && !hasObjVar(player, "stamps.t1_reward_given"))
        {
            String title = "Congratulations!";
            String buffer = "You have earned enough points for the GJPUD Tier I reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t1_reward_given", true);
            grantT1Reward(player);
        }
        if (stamps == 20 && !hasObjVar(player, "stamps.t2_reward_given"))
        {
            String title = "Congratulations!";
            String buffer = "You have earned enough points for the GJPUD Tier II reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t2_reward_given", true);
            grantT2Reward(player);
        }
        if (stamps == 35 && !hasObjVar(player, "stamps.t3_reward_given"))
        {
            String title = "Congratulations!";
            String buffer = "You have earned enough points for the GJPUD Tier III reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t3_reward_given", true);
            grantT3Reward(player);
        }
        if (stamps == 45 && !hasObjVar(player, "stamps.t4_reward_given"))
        {
            String title = "Congratulations!";
            String buffer = "You have earned enough points for the GJPUD Tier IV reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t4_reward_given", true);
            grantT4Reward(player);
        }
        if (stamps == 50 && !hasObjVar(player, "stamps.t5_reward_given"))
        {
            String title = "Congratulations!";
            String buffer = "You have earned enough points for the GJPUD Tier V reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t5_reward_given", true);
            grantT5Reward(player);
        }
        return SCRIPT_CONTINUE;
    }
    public int grantT1Reward(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t1_reward = getStringObjVar(player, "event.earth_day_t1_reward");
        static_item.createNewItemFunction(t1_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the GJPUD Tier I reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT2Reward(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t2_reward = getStringObjVar(player, "event.earth_day_t2_reward");
        static_item.createNewItemFunction(t2_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the GJPUD Tier II reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT3Reward(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t3_reward = getStringObjVar(player, "event.earth_day_t3_reward");
        static_item.createNewItemFunction(t3_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the GJPUD Tier III reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT4Reward(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t4_reward = getStringObjVar(player, "event.earth_day_t4_reward");
        static_item.createNewItemFunction(t4_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the GJPUD Tier IV reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT5Reward(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t5_reward = getStringObjVar(player, "event.earth_day_t5_reward");
        static_item.createNewItemFunction(t5_reward, pInv);
        //badge
        //title (skill rather)
        sendSystemMessageTestingOnly(player, "You have recieved the GJPUD Tier V reward!");// and the Galactic Recycling Association badge and title!
        return SCRIPT_CONTINUE;
    }
    public void payCredits(obj_id player) throws InterruptedException
    {
        setObjVar(player, "event.starting_earth_day_credits", getCashBalance(player));
        int moneyz = getIntObjVar(player, "event.starting_earth_day_credits");
        if (moneyz <= 250000)
        {
            dictionary d = new dictionary();
            d.put("payoutTarget", player);
            money.systemPayout(money.ACCT_NPC_LOOT, player, CASH_AMOUNT, "handlePayoutToPlayer", d);
        }
        else
        {
            sendSystemMessageTestingOnly(player, "The Galactic Junk Pickup Day coordinators thank you for all your work, but you currently exceed the maximum amount we can give you. Have you considered donating to a charity?");
        }
    }
    public int handlePayoutToPlayer(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("payoutTarget");
        if (!isIdValid(player))
        {
            return SCRIPT_CONTINUE;
        }
        int retCode = params.getInt(money.DICT_CODE);
        if (retCode == money.RET_SUCCESS)
        {
            String terminal = "Galactic Junk Pickup Day Terminal";
            sendSystemMessageTestingOnly(player, "You receive " + CASH_AMOUNT + " credits from the " + terminal + " for your hard work keeping the galaxy clean.");
        }
        else if (retCode == money.RET_FAIL)
        {
            sendSystemMessageTestingOnly(player, "The system is unable to complete the transaction.");
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCheckRequest(obj_id self, dictionary params) throws InterruptedException
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
        String objectToCheck = sui.getInputBoxText(params);
        if (objectToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (!objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "You cannot use static items for this. It must be single tangible items to avoid duplication and exploits");
        }
        setObjVar(self, "item_to_use", objectToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT1Request(obj_id self, dictionary params) throws InterruptedException
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
        String t1ToCheck = sui.getInputBoxText(params);
        if (t1ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (t1ToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }
        setObjVar(self, "event.earth_day_t1_reward", t1ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT2Request(obj_id self, dictionary params) throws InterruptedException
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
        String t2ToCheck = sui.getInputBoxText(params);
        if (t2ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (t2ToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }
        setObjVar(self, "event.earth_day_t2_reward", t2ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT3Request(obj_id self, dictionary params) throws InterruptedException
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
        String t3ToCheck = sui.getInputBoxText(params);
        if (t3ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (t3ToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }
        setObjVar(self, "event.earth_day_t3_reward", t3ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT4Request(obj_id self, dictionary params) throws InterruptedException
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
        String t4ToCheck = sui.getInputBoxText(params);
        if (t4ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (t4ToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }
        setObjVar(self, "event.earth_day_t4_reward", t4ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT5Request(obj_id self, dictionary params) throws InterruptedException
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
        String t5ToCheck = sui.getInputBoxText(params);
        if (t5ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (t5ToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }
        setObjVar(self, "event.earth_day_t5_reward", t5ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        int lastUsed = getIntObjVar(self, "used.timestamp");
        names[idx] = "current_collected";
        attribs[idx] = getIntObjVar(self, "event.total_recycled") + " pieces of junk.";
        idx++;
        if (isGod(player))
        {
            names[idx] = "template";
            attribs[idx] = getStringObjVar(self, "item_to_use");
            idx++;
            names[idx] = "last_recycler";
            attribs[idx] = getStringObjVar(self, "last_used");
            idx++;
        }
        return SCRIPT_CONTINUE;
    }
}
