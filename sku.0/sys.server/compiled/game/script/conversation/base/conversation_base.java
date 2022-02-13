package script.conversation.base;

import java.util.List;
import script.*;
import script.library.ai_lib;

public class conversation_base extends script.base_script {

    public int OnAttach(obj_id self) {
        setInvulnerable(self, true);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        return SCRIPT_CONTINUE;
    }

    public int OnStartNpcConversation(String c_stringFile, String index, List<String> responses, obj_id player, obj_id npc) {
        return OnStartNpcConversation(c_stringFile, index, responses.toArray(new String[responses.size()]), player, npc);
    }

    public int OnStartNpcConversation(String c_stringFile, String index, String[] responses, obj_id player, obj_id npc) {
        if (!ai_lib.isInCombat(player)) {
            prose_package pp = new prose_package(c_stringFile, index);
            pp.setTT(player);
            pp.setTO(player);
            pp.setTU(player);
            faceTo(npc, player);
            npcStartConversation(player, npc, c_stringFile, null, pp, convertToStringIds(c_stringFile, responses));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnStartNpcConversation(String c_stringFile, String index, String response, obj_id player, obj_id npc) {
        return OnStartNpcConversation(c_stringFile, index, new String[]{response}, player, npc);
    }

    public void setupResponses(String c_stringFile, String index, String[] responses, obj_id player) {
        npcSpeak(player, new string_id(c_stringFile, index));
        npcSetConversationResponses(player, convertToStringIds(c_stringFile, responses));
    }

    public void setupResponses(String c_stringFile, String index, String response, obj_id player) {
        setupResponses(c_stringFile, index, new String[]{response}, player);
    }

    public void setupResponses(String c_stringFile, String index, List<String> responses, obj_id player) {
        setupResponses(c_stringFile, index, responses.toArray(new String[responses.size()]), player);
    }

    public static string_id[] convertToStringIds(String c_stringFile, List<String> strings) {
        return convertToStringIds(c_stringFile, strings.toArray(new String[strings.size()]));
    }

    public static string_id[] convertToStringIds(String c_stringFile, String[] strings) {
        string_id[] arr = new string_id[strings.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new string_id(c_stringFile, strings[i]);
        }
        return arr;
    }

    public void showTokenVendorUI(obj_id player, obj_id npc) {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }
}
