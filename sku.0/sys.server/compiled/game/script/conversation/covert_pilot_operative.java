package script.conversation;

import static script.base_class.hasSkill;
import static script.base_class.revokeSkill;
import script.library.badge;
import script.library.chat;
import script.library.factions;
import script.obj_id;
import script.string_id;
import script.library.skill;
import script.library.space_skill;

public class covert_pilot_operative extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "conversation/covert_pilot_operative";
    public int onAttach(obj_id self) {
        setInvulnerable(self, true);
        setCondition(self, CONDITION_CONVERSABLE);
        String npcFaction = getStringObjVar(self, "socialGroup");
        switch (npcFaction) {
            case "Imperial":
                setName(self, "A Covert Imperial Operative");
                break;
            case "Rebel":
                setName(self, "A Covert Alliance Operative");
                break;
            default:
                setName(self, "A Covert Naboo Operative");
                break;
        }
        return SCRIPT_CONTINUE;
    }
    
    public void onStartNpcConversation(obj_id self, obj_id player) {
        obj_id tatooine = getPlanetByName("tatooine");
        
        if (hasObjVar(tatooine, "covert_pilot_operative" + getPlayerStationId(player)) && !isGod(player)) {
            chat.chat(self, player, new string_id(SCRIPT, "n5"));
            return;
        }
        
        String npcFaction = getStringObjVar(self, "socialGroup");
        String playerFaction = factions.getFaction(player);
        
        if (playerFaction == null) {
            playerFaction = "neutral";
        }
        
        if (npcFaction.equals(playerFaction.toLowerCase()) || (playerFaction.equals("neutral") && npcFaction.equals("townsperson"))) {
            OnStartNpcConversation(SCRIPT, "n2", new String[]{"p1"}, player, self);
        } else {
            chat.chat(self, player, new string_id(SCRIPT, "n6"));
        }
    }
    
    public void OnNpcConversationResponse(obj_id self, String conversationName, obj_id player, string_id response) {
        switch (response.getAsciiId()) {
            case "p1":
                String playerFaction = factions.getFaction(player);

                if (playerFaction == null) {
                    playerFaction = "";
                }

                switch (playerFaction) {
                    case factions.FACTION_IMPERIAL:
                        setupResponses(SCRIPT, "in4", new String[]{"be", "ss", "ii"}, player);
                        break;
                    case factions.FACTION_REBEL:
                        setupResponses(SCRIPT, "rn4", new String[]{"hs", "cps", "vs"}, player);
                        break;
                    default:
                        setupResponses(SCRIPT, "nn4", new String[]{"cs", "sa", "rsf"}, player);
                        break;
                }
                break;
            case "be":
            case "ss":
            case "ii":
            case "hs":
            case "cps":
            case "vs":
            case "cs":
            case "sa":
            case "rsf":
                grantPilotSkills(player, response.getAsciiId());
                chat.chat(self, player, new string_id(SCRIPT, "n2"));
                npcEndConversation(player);
                break;
        }
    }
    
    private static void revokeExistingPilotSkills(obj_id player) {
        String pilotFaction = null;
        if (space_skill.hasSpaceSkills(player)) {
            if (space_skill.hasRebelSkill(player)) {
                pilotFaction = "rebel";
            } else if (space_skill.hasImperialSkill(player)) {
                pilotFaction = "imperial";
            } else if (space_skill.hasPilotSkill(player)) {
                pilotFaction = "neutral";
            }
            if (pilotFaction != null) {
                for (String SKILL_NAME : space_skill.SKILL_NAMES) {
                    String skill = pilotFaction + SKILL_NAME;
                    if (hasSkill(player, skill))
                    {
                        revokeSkill(player, skill);
                    }
                }
                space_skill.revokeExperienceForRetire(player, pilotFaction);
            }
        }
    }
    
    private static void grantPilotSkills(obj_id player, String squadron) {
        obj_id tatooine = getPlanetByName("tatooine");
        setObjVar(tatooine, "covert_pilot_operative" + getPlayerStationId(player), true);

        revokeExistingPilotSkills(player);

        switch (squadron) {
            case "be":
                badge.grantBadge(player, "pilot_imperial_navy_corellia");
                break;
            case "ss":
                badge.grantBadge(player, "pilot_imperial_navy_tatooine");
                break;
            case "ii":
                badge.grantBadge(player, "pilot_imperial_navy_naboo");
                break;
            case "hs":
                badge.grantBadge(player, "pilot_rebel_navy_corellia");
                break;
            case "cps":
                badge.grantBadge(player, "pilot_rebel_navy_tatooine");
                break;
            case "vs":
                badge.grantBadge(player, "pilot_rebel_navy_naboo");
                break;
            case "cs":
                badge.grantBadge(player, "pilot_neutral_corellia");
                break;
            case "sa":
                badge.grantBadge(player, "pilot_neutral_tatooine");
                break;
            case "rsf":
                badge.grantBadge(player, "pilot_neutral_naboo");
                break;
        }
        
        String faction = factions.getFaction(player);
        
        if (faction == null) {
            faction = "neutral";
        }

        String begining = "pilot_" + faction.toLowerCase();

        if (!faction.equals("neutral")) {
            begining += "_navy";
        }

        for (String spaceSkill : space_skill.SKILL_NAMES) {
            skill.grantSkill(player, begining + spaceSkill);
        }
    }
}
