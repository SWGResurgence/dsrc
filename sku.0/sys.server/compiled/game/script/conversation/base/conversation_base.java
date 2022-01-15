package script.conversation.base;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;

public class conversation_base extends script.base_script
{
	public String c_stringFile = "";
	public String scriptName = "";
	public String conversation = "";
	public conversation_base()
	{
	}
	public int OnInitialize(obj_id self) throws InterruptedException
	{
		if ((!isTangible(self)) || (isPlayer(self)))
		{
			detachScript(self, conversation);
		}
		setCondition(self, CONDITION_CONVERSABLE);
		return SCRIPT_CONTINUE;
	}
	public int OnAttach(obj_id self) throws InterruptedException
	{
		setCondition(self, CONDITION_CONVERSABLE);
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
	{
		int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
		menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
		menuInfoData.setServerNotify(false);
		setCondition(self, CONDITION_CONVERSABLE);
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
  
	public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
	{
		clearCondition(self, CONDITION_CONVERSABLE);
		detachScript(self, conversation);
		return SCRIPT_CONTINUE;
	}
	public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
	{
		Object[] objects = new Object[responses.length];
		System.arraycopy(responses, 0, objects, 0, responses.length);
		return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
	}
	public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
	{
		if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
		{
			return SCRIPT_OVERRIDE;
		}
		chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
		return SCRIPT_CONTINUE;
	}
	public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
	{
		chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
		utils.removeScriptVar(player, conversation + ".branchId");
		return SCRIPT_CONTINUE;
	}
	protected int craft_response(String[] responseStrings, int branchId, obj_id player) throws InterruptedException{
		string_id message = new string_id(c_stringFile, responseStrings[0]);
		string_id responses[] = new string_id[responseStrings.length];
		for(int i = 1; i < responseStrings.length; i++){
			responses[i] = new string_id(c_stringFile, responseStrings[i]);
		}
		if(responses.length > 1){
			utils.setScriptVar(player, conversation + ".branchId", branchId);
			npcSpeak(player, message);
			npcSetConversationResponses(player, responses);
		}
		else
		{
			utils.removeScriptVar(player, conversation + ".branchId");
			npcEndConversationWithMessage(player, message);
		}
		return SCRIPT_CONTINUE;
	}
	protected int craft_repeater(String[] responseStrings, int branchId, obj_id player, obj_id self) throws InterruptedException{
		string_id message = new string_id(c_stringFile, responseStrings[0]);
		string_id responses[] = new string_id[responseStrings.length];

		for(int i = 1; i < responseStrings.length; i++){
			responses[i] = new string_id(c_stringFile, responseStrings[i]);
		}

		if(responseStrings.length > 1) {
			utils.setScriptVar(player, conversation + ".branchId", branchId);
			npcStartConversation(player, self, scriptName, message, responses);
		}
		else{
			chat.chat(self, player, message);
		}

		return SCRIPT_CONTINUE;

	}
	protected int craft_response_prose(String[] responseStrings, int branchId, obj_id player, obj_id self, String name) throws InterruptedException{
		string_id message = new string_id(c_stringFile, responseStrings[0]);
		string_id responses[] = new string_id[responseStrings.length];
		for(int i = 1; i < responseStrings.length; i++){
			responses[i] = new string_id(c_stringFile, responseStrings[i]);
		}
		if(responses.length > 1){
			utils.setScriptVar(player, conversation + ".branchId", branchId);
			prose_package pp = new prose_package();
			pp.stringId = message;
			pp.actor.set(player);
			pp.target.set(self);
			pp.other.set(name);
			npcStartConversation(player, self, scriptName, null, pp, responses);
		}
		else
		{
			prose_package pp = new prose_package();
			pp.stringId = message;
			pp.actor.set(player);
			pp.target.set(self);
			pp.other.set(name);
			chat.chat(self, player, null, null, pp);
		}
		return SCRIPT_CONTINUE;
	}
}
