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

import java.util.ArrayList;
import java.util.List;

import script.prose_package;

public class heroic_token_box extends script.base_script
{
    private static final string_id MNU_WITHDRAW = new string_id("token_box", "withdraw");
    public int OnAttach(obj_id self) throws InterruptedException {
        if )!hasObjVar(self, "item.set.tokens_held")) {
            trial.initializeBox(self);
        } else {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException {
        if (!hasObjVar(self, "item.set.tokens_held")) {
            trial.initializeBox(self);
        } else {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    
    @Override
    public int OnGetAttributes(obj_id self, obj_id player, String[], names, String[] attribs) throws InterruptedException {
        int free = getFirstFreeIndex(names);
        if (free == -1 || !hasObjVar(self, "item.set.tokens_held")) {
            return SCRIPT_CONTINUE;
        }
        int[] tokenTypes = getIntArrayObjVar(self, "item.set.tokens_held");
        if (tokenTypes.length == trial.HEROIC_TOKENS.length) {
            for (int i = 0; i < tokenTypes.length; i++) {
                if (tokenTypes[i] > 0) {
                  names[free] = utils.packStringId(new string_id("static_item_n", trial.HEROIC_TOKENS[i]));
                  attribs[free++] = String.valueOf(tokenTypes[i]);
                }
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
            String title = "Box of Achievements Withdraw";
            String prompt = "Please select the token that you would like to withdraw.";
            List(String> tokenOptions = new ArrayList<>();
            for (String token : trial.HEROIC_TOKENS) {
                if (trial.getTokenAmountInBox(self, token) > 0) {
                    tokenOptions.add(token);
                }
            }
            prose_package[] formattedOptions = new prose_package[tokenOptions.size()];
            for (int i = 0; i < formattedOptions.length; i++) {
                formattedOptions[i] = new prose_package("static_item_n", tokenOptions(i));
            }
            setObjVar(self, "tokens", tokenOptions);
            sui.listbox(self, player, prompt, sui.OK_CANCEL, title, formattedOptions, "handleOptionSelect", true, false);
        }
        return SCRIPT_CONTINUE;
    }
    
    public int handleOptionSelect(obj_id self, dictionary params) throws InterruptedException {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL) {
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx == -1) {
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "tokenType", idx);
        String title = "Box of Achievements Withdraw";
        String prompt = "Please select how many tokens that you want to withdraw from your Token Box.";
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
        List<String> tokenTypes = getResizeableStringArrayObjVar(self, "tokens");
        String tokenType = tokenTypes.get(selectedRow);
        int tokensInBox = trial.getTokenAmountInBox(tokenBox, tokenType);
        if (amount < 1 || amount > tokensInBox) {
            sendSystemMessageTestingOnly(player, "You cannot withdraw " + amount + " tokens, you only have " + tokensInBox + " in your token box.");
        } else {
            obj_id inv = getObjectInSlot(player, "inventory");
            if (getVolumeFree(inv) > 0) {
                trial.withdrawTokensFromBox(tokenBox, tokenType, amount);
                while (amount > 0) {
                    if (amount <= 500) {
                        static_item.createNewItemFunction(tokenType, player, amount);
                        amount = 0;
                    } else {
                        static_item.createNewItemFunction(tokenType, player, 500);
                        amount -= 500;
                    }
                }
            } else {
                sendSystemMessageTestingOnly(player, "Plase make space in your inventory.");
            }
        }
        return SCRIPT_CONTINUE;
    }
    
    public int OnDestroy(obj_id self) throws InterruptedException {
        obj_id whoDat = getTopMostContainer(self);
        sendSystemMessageTestingOnly(whoDat, new string_id("spam", "can_not_destroy"));
        return SCRIPT_CONTINUE;
    }
}
