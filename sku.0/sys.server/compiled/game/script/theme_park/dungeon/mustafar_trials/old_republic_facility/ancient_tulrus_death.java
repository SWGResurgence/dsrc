package script.theme_park.dungeon.mustafar_trials.old_republic_facility;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

public class ancient_tulrus_death extends script.base_script {
	public ancient_tulrus_death() {
	}
	public int ancientTulrusKilled(obj_id self, dictionary params) throws InterruptedException {
		trial.setDoomBringerKilled(self, true);
		dictionary dict = new dictionary();
        dict.put("tokenIndex", 7);
		dict.put("tokenCount", 2);
		utils.messageTo(players, "handleAwardtoken", dict, 0, false);
        obj_id group = getGroupObject(players[0]);
        int calendarTime = getCalendarTime();
        String realTime = getCalendarTimeStringLocal(calendarTime);
        CustomerServiceLog("instance-mustafar_trials_old_republic_facility", "Doom Bringer Defeated in instance (" + self + ") by group_id (" + group + ") at " + realTime);
        CustomerServiceLog("instance-mustafar_trials_old_republic_facility", "Group (" + group + ") consists of: ");
        for (int i = 0; i < players.length; ++i) {
            String strProfession = skill.getProfessionName(getSkillTemplate(players[i]));
            CustomerServiceLog("instance-mustafar_trials_old_republic_facility", "Group (" + group + ") member " + i + " " + getFirstName(players[i]) + "'s(" + players[i] + ") profession is " + strProfession + ".");
        }
		return SCRIPT_CONTINUE;
	}
	public void doLogging(String section, String message) throws InterruptedException {
		if (LOGGING) {
			LOG("doLogging/old_republic_facility/" + section, message);
		}
	}
}