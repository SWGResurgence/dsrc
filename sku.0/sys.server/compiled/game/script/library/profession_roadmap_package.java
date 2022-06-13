package script.library;

import script.*;
import script.library.*;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;
import java.lang.String;
import java.util.HashSet;

public class profession_roadmap_package extends base_script {
    
    // Localized String for "claim_redelivery_package" taken from "sui.stf"
    
    public static final string_id REDEEM= new string_id("sui", "claim_redelivery_package");
    public static final String[] ROADMAP_REDILIVERY_PROFESSIONS = {
        "Jedi",
        "Bounty Hunter",
        "Smuggler",
        "Officer",
        "Commando",
        "Spy",
        "Medic",
        "Entertainer",
        "Trader"
    };
    
    // Defining OnInitialize
    
    public int OnInitialize(obj_id self) throws InterruptedException {
        setObjVar(self, "noTrade", true);
        return SCRIPT_CONTINUE;
    }    
    
    // Server Call to Request Menu
    
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("", ""));
        return SCRIPT_CONTINUE;
    }

    // Server Call to Respond When Menu Row is Selected
    
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            use(self, player);
            startRoadmapRedeliverySelection(player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    
    // Defining startRoadmapRedeliverySelection
    
    public void startRoadmapRedeliverySelection(obj_id player) throws InterruptedException {
        obj_id self = getSelf();
        String prompt = "Choose a Profession Roadmap Set to Redeem:";
        String totle = "Supplies Redemption";
        closeOldWindow(player);
        int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, ROADMAP_REDILIVERY_PROFESSIONS, "handleOptionSelect", true, false);
        setWindowPid(player, pid);
    }
    
    // Server Call to Respond When Menu Option is Selected
    
    public int handleOptionSelect(obj_id self, dictionary params) throws InterruptedException {
        if (params == null || params.isEmpty()) {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int btn = sui.getIntButtonPressed(params);
        int idx = sui.getListboxSelectedRow(params);
        if (btn == sui.BP_CANCEL) {
            cleanScriptVars(player);
            return SCRIPT_CONTINUE;
        }
        if (idx == -1 || idx > ROADMAP_REDILIVERY_PROFESSIONS.length) {
            cleanScriptVars(player);
            return SCRIPT_CONTINUE;
        }
    }
    
    // Defining refreshMenu
    
    public void refreshMenu(obj_id player, String prompt, String title, String[] options, String myHandler, boolean draw) throws InterruptedException {
        obj_id self = getSelf();
        closeOldWindow(player);
        if (outOfRange(self, player, true)) {
            cleanScriptVars(player);
            return;
        }
        if (draw == false) {
            int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL_REFRESH, title, options, myHandler, false, false);
            sui.listboxUseOtherButton(pid, "Back");
            sui.showSUIPage(pid);
            setWindowPid(player, pid);
        }else{
            int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, options, myHandler, true, false);
            sui.showSUIPage(pid);
            setWindowPid(player, pid);
        }
    }
    
    // Defining outOfRange
    
    public boolean outOfRange(obj_id self, obj_id player, boolean message) throws InterruptedException {
        return false;
    }
    
    // Defining handleRoadmapRedliveryProfessionsOption
    
    public void handleRoadmapRedliveryProfessionsOption(obj_id player) throws InterruptedException {
        refreshMenu(player, "Choose a Profession Roadmap Set to Redeem", "", ROADMAP_REDILIVERY_PROFESSIONS, "handleRoadmapRedliveryProfessions", false);
    }
    
    // Defining handleRoadmapRedliveryProfessions
    
    public int handleRoadmapRedliveryProfessions(obj_id self, dictionary params) throws InterruptedException {
        if (params == null || params.isEmpty()) {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int btn = sui.getIntButtonPressed(params);
        int idx = sui.getListboxSelectedRow(params);
        if (btx == sui.BP_REVERT) {
            refreshMenu(player, "Choose a Profession Roadmap Set to Redeem", "", ROADMAP_REDILIVERY_PROFESSIONS, "handleRoadmapRedliveryProfessions", true);
            return SCRIPT_CONTINUE;
        }
        if (btn == sui.BP_CANCEL) {
            cleanScriptVars(player);
            closeOldWindow(player);
            return SCRIPT_CONTINUE;
        }
        if (idx == -1 || idx > WEAPON_OPTIONS.length) {
            cleanScriptVars(player);
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(player)) {
            sendSystemMessageTestingOnly(player, "The system is unable to complete the requested transaction.");
            return SCRIPT_CONTINUE;
        }
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        switch (idx) {
            case 0:  // Jedi Roadmap Items
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_jedi_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_force_sensitive_clicky_01_02", pInv));
                break;
            case 1:  // Bounty Hunter Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_bounty_hunter_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_bounty_hunter_clicky_01_02", pInv));
                break;
            case 2:  // Smuggler Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_smuggler_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_smuggler_clicky_01_02", pInv));
                break;
            case 3:  // Officer Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_officer_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_officer_clicky_01_02", pInv));
                break;
            case 4:  // Commando Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_commando_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_commando_clicky_01_02", pInv));
                break;
            case 5:  // Spy Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_spy_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_spy_clicky_01_02", pInv));
                break;
            case 6:  // Medic Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_medic_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_medic_clicky_01_02", pInv));
                break;
            case 7:  // Entertainer Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_entertainer_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_entertainer_clicky_01_02", pInv));
                break;
            case 8:  // Trader Roadmap Box
                theSet.add(static_item.createNewItemFunction("item_npe_uniform_crate_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_trader_equipment_set_01_01", pInv));
                theSet.add(static_item.createNewItemFunction("item_trader_clicky_01_02", pInv));
                break;
                default:
                cleanScriptVars(player);
                break;
        }
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        for (obj_id a : items) {
            setObjVar(a, "noTrade", true);
        }
        showLootBox(player, items);
        return items;
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    
    // Defining cleanScriptVars
    
    public void cleanScriptVars(obj_id player) throws InterruptedException {
        utils.removeScriptVarTree(player, "profession_roadmap_package");
    }
    
    public void closeOldWindow(obj_id player) throws InterruptedException {
        String playerPath = "character_builder.";
        if (utils.hasScriptVar(player, "character_builder.pid"); {
          forceCloseSUIPage(oldpid);
          utils.removeScriptVar(player, "character_builder.pid");
        }
    }
    
    public void setWindowPid(obj_id player, int pid) throws InterruptedException {
        if (pid > -1) {
            utils.setScriptVar(player, "character_builder.pid", pid);
        }
    }
}
