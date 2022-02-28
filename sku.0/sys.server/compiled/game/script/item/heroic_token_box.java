package script.item;

import script.library.trial;
import script.library.utils;
import script.obj_id;
import script.string_id;

import script.dictionary;
import script.menu_info;
import script.menu_info_types;
import script.library.static_item;
import script.library.sui;

public class heroic_token_box extends script.base_script
{
    private static final string_id MNU_WITHDRAW = new string_id("token_box", "withdraw");
    private static final String[] TOKEN_OPTIONS = {
        "Axkva Min Token of Heroism",               //#00
        "Mos Espa Token of Heroism",                //#01
        "Droid Factory Token of Heroism",           //#02
        "Black Sun Token of Heroism",               //#03
        "Exar Kun Token of Heroism",                //#04
        "Echo Base Token of Heroism",               //#05
        "Marauder Token of Heroism",                //#06
        "Mustafar Token of Heroism",                //#07
        "Veteran Reward Token",                     //#08
        "A Duty Mission Token",                     //#09
        "Rebel Battlefield Token",                  //#10
        "Imperial Battlefield Token",               //#11
        "Imperial Station Battle Token",            //#12
        "Rebel Station Battle Token",               //#13
        "Rebel Alliance Galactic Civil War Token",  //#14
        "Imperial Galactic Civil War Token",        //#15
        "Imperial Restuss Commendation",            //#16
        "Rebel Restuss Commendation",               //#17
        "Empire Day Token",                         //#18
        "Remebrance Day Token",                     //#19
        "Rebel Alliance Propaganda Token",          //#20
        "Imperial Propaganda Token",                //#21
        "Galactic Moon Coin",                       //#22
        "Ewok Festival of Love Chak Heart",         //#23
        "Copper Chronicles Token",                  //#24
        "Silver Chronicles Token",                  //#25
        "Gold Chronicles Token",                    //#26
        "Meatlump Lump",                            //#27
        "Midlithe Crystal",                         //#28
        "Dathomir Amber",                           //#29
        "Spider Silk",                              //#30
        "Rancor Teeth",                             //#31  
        "Whuffa Leather",                           //#32
        "Rare Dried Herb",                          //#33
        "Spell Weaver Crystal",                     //#34
        "Resurgence Token of Achievement",          //#35
        "Entertainer Token of Achievement",         //#36
        "Teasure Token of Achievement",             //#37
        "Kashyyyk Token of Freedom"                 //#38
    };
    private static final int[] TOKENS = {
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30,
        31,
        32,
        33,
        34,
        35,
        36,
        37,
        38
    };    
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            trial.initializeBox(self);
        }
        else 
        {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            trial.initializeBox(self);
        }
        else 
        {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int free = getFirstFreeIndex(names);
        if (free == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            return SCRIPT_CONTINUE;
        }
        int[] tokenTypes = getIntArrayObjVar(self, "item.set.tokens_held");
        if (tokenTypes.length == trial.HEROIC_TOKENS.length)
        {
            for (int i = 0; i < tokenTypes.length; i++)
            {
                names[free] = utils.packStringId(new string_id("static_item_n", trial.HEROIC_TOKENS[i]));
                attribs[free++] = Integer.toString(tokenTypes[i]);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        if (utils.isNestedWithin(self, player)) {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, MNU_WITHDRAW);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (utils.isNestedWithin(self, player) && item == menu_info_types.SERVER_MENU1) {
            String title = "Heroic Token Box";
            String prompt = "Please select the token that you would like to withdraw.";
            int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, TOKEN_OPTIONS, "handleOptionSelect", true, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleOptionSelect(obj_id self, dictionary params) throws InterruptedException {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL) {
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx == -1 || idx > TOKEN_OPTIONS.length)
            return SCRIPT_CONTINUE;
        setObjVar(self, "tokenType", idx);
        String title = "Heroic Token Box";
        String prompt = "Please select how many tokens you want to withdraw from your Token Box.";
        sui.filteredInputbox(self, sui.getPlayerId(params), prompt, title, "handleQuantitySelect", "");
        return SCRIPT_CONTINUE;
    }
    public int handleQuantitySelect(obj_id self, dictionary params) throws InterruptedException {
        obj_id player = utils.getContainingPlayer(self);
        obj_id tokenBox = utils.getObjectInInventory(player, trial.TOKEN_BOX);
        
        String input = sui.getInputBoxText(params);
        if (input == null || input.isEmpty()) {
            return SCRIPT_CONTINUE;
        }
        int amount = Integer.parseInt(input);
        int selectedRow = getIntObjVar(self, "tokenType");
        String tokenType = trial.HEROIC_TOKENS[TOKENS[selectedRow]];
        int tokensInBox = trial.getTokenAmountInBox(tokenBox, tokenType);
        if (amount < 1 || amount > tokensInBox) {
            sendSystemMessageTestingOnly(player, "You cannot withdraw " + amount + " tokens, you only have " + tokensInBox + " in your token box.");
        } else {
            obj_id inv = getObjectInSlot(player, "inventory");
            if (getVolumeFree(inv) > 0) {
                trial.withdrawTokensFromBox(tokenBox, tokenType, amount);
                static_item.createNewItemFunction(tokenType, player, amount);
            } else {
                sendSystemMessageTestingOnly(player, "Please make space in your inventory.");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self) throws InterruptedException
     {
         obj_id whoDat = getTopMostContainer(self);
         sendSystemMessage(whoDat, new string_id("spam", "can_not_destroy"));
         return SCRIPT_OVERRIDE;
     }
}
