package script.conversation;

import script.library.badge;
import script.library.chat;
import script.library.factions;
import script.library.jedi;
import script.library.static_item;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class elder_robe_npc extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "conversation/elder_robe_npc";
    public void OnStartNpcConversation(obj_id self, obj_id player) {
        if (!utils.isProfession(player, utils.FORCE_SENSITIVE) || !badge.hasBadge(player, "bdg_col_jedi_robe") || getLevel(player) < 90) {
            chat.chat(self, player, new string_id(SCRIPT, "s_2"));
            return;
        }
        if (jedi.getNumberOfPOIsLeft(player) == 0) {
            if (getObjIdObjVar(player, jedi.ELDER_POI_OBJVAR) != null) {
                chat.chat(self, player, new string_id(SCRIPT, "s_20"));
            } else {
                OnStartNpcConversation(SCRIPT, "s_12", new String[]{"s_13"}, player, self);
            }
        } else if (hasObjVar(player, jedi.ELDER_POI_OBJVAR)) {
            chat.chat(self, player, new string_id(SCRIPT, "s_11"));
            return;
        }
        OnStartNpcConversation(SCRIPT, "s_1", new String[]{"s_3"}, player, self);
    }
    
    public void OnNpcConversationResponse(obj_id self, String conversationName, obj_id player, string_id response) {
        switch (response.getAsciiId()) {
            case "s_3":
                setupResponses(SCRIPT, "s_4", new String[]{"s_5", "s_6"}, player);
                break;
            case "s_5":
                setupResponses(SCRIPT, "s_7", new String[]{"s_8", "s_9"}, player);
                break;
            case "s_6":
                npcEndConversation(player);
                break;
            case "s_8":
                playMusic(player, "sound/music_become_dark_jedi.snd");
                jedi.setAssignedPOIs(player);
                npcEndConversation(player);
                break;
            case "s_9":
                playMusic(player, "sound/music_become_dark_jedi.snd");
                jedi.setAssignedPOIs(player);
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_10"));
                break;
            case "s_13":
                setupResponses(SCRIPT, "s_14", new String[]{"s_15"}, player);
                break;
            case "s_15":
                setupResponses(SCRIPT, factions.getFactionFlag(player) == factions.FACTION_FLAG_IMPERIAL ? "s_16" : "s_17", new String[]{"s_18"}, player);
                break;
            case "s_18":
                playMusic(player, "sound/music_become_light_jedi.snd");
                obj_id robe = static_item.createNewItemFunction(factions.getFactionFlag(player) == factions.FACTION_FLAG_IMPERIAL ? "item_jedi_robe_dark_04_07" : "item_jedi_robe_light_04_07", player);
                showLootBox(player, new obj_id[]{ robe });
                setObjVar(player, jedi.ELDER_POI_OBJVAR, robe);
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_19"));
                break;
        }
    }
}
