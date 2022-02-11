package script.conversation;

import java.util.ArrayList;
import java.util.List;

import script.library.chat;
import script.library.groundquests;
import script.library.instance;

import script.obj_id;
import script.string_id;

public class heroic_prequest_bypass extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "conversation/heroic_prequest_bypass";
    private static final String[] QUESTS = {
        "axkva_min_intro",
        "exar_kun_intro_01",
        "exar_kun_intro_02",
        "feeder_ig88_01",
        "feeder_ig88_02",
        "feeder_ig88_03",
        "feeder_tusken_01",
        "star_destroyer_intro_imperial",
        "star_destroyer_intro_neutral",
        "star_destroyer_intro_rebel"
    };
    private static final String[] INSTANCES = {
        "heroic_axkva_min",
        null,
        "heroic_exar_kun",
        null,
        null,
        "heroic_ig88",
        "heroic_tusken_army",
        "heroic_star_destroyer",
        "heroic_star_destroyer",
        "heroic_star_destroyer"
    };
    
    public static final OnStartNpcConversation(obj_id self, obj_id player) {
        String[] completedPrequests = getCompletedPrequests(player);
        player.setScriptVar("prequests", completedPrequests);
        if (getLevel(player) >= 85 && completedPrequests.length > 0) {
            OnStartNpcConversation(SCRIPT, "s_1", new String[]{"s_2"}, player, self);
        } else {
            chat.chat(self, player, new string_id(SCRIPT, "s_10"));
        }
        return null;
    }
    
    public static final OnNpcConversationResponse(obj_id self, String conversationName, obj_id player, string_id response) throws InterruptedException {
        switch (response.getAsciiId()) {
            case "s_2":
                setupResponses(SCRIPT, "s_3", "s_4", player);
                break;
            case "s_4":
                setupResponses(SCRIPT, "s_5", new String[]{"s_6", "s_8"}, player);
                break;
            case "s_6":
                String[] prequests = player.getScriptVars().getStringArray("prequests");
                player.getScriptVars().remove("prequests");
                for (int i = 0; i < prequests.length; i++) {
                    if (groundquests.isQuestActive(player, prequests[i])) {
                        groundquests.completeQuest(player, prequests[i]);
                        if (INSTANCES[i] != null && !instance.isFlaggedForInstance(player, INSTANCES[i])) {
                            instance.flagPlayerForInstance(player, INSTANCES[i]);
                        }
                        npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_7"));
                    } else {
                        groundquests.grantQuest(player, prequests[i]);
                        npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_11"));
                    }
                }
                break;
            case "s_8":
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_9"));
                break;
        }
        return null;
    }
    
    private static obj_id getAltInGroup(obj_id player) throws InterruptedException {
        obj_id groupObject = getGroupObject(player);
        if (groupObject != null) {
            obj_id[] groupMembers = getGroupMemberIds(groupObject);
            for (obj_id groupMember : groupMembers) {
                if (groupMember == player) {
                    continue;
                }
                if (charactersAreSamePlayer(player, groupMember)) {
                    return groupMember;
                }
            }
        }
        return null;
    }
    
    private static String[] getCompletedPrequests(obj_id player) throws InterruptedException {
        obj_id altInGroup = getAltInGroup(player);
        List<String> questsToComplete = new ArrayList<>();
        if (altInGroup != null) {
            for (String quest : QUESTS) {
                if (groundquests.hasCompletedQuest(altInGroup, quest) && !groundquests.hasCompletedQuest(player, quest)) {
                    questsToComplete.add(quest);
                }
            }
        }
        return questsToComplete.toArray(new String[questsToComplete.size()]);
    }
}
