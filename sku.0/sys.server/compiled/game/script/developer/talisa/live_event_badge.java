package script.developer.talisa;

import script.dictionary;
import script.library.badge;
import script.obj_id;

public class live_event_badge extends script.base_script {
    public int OnInitialize(obj_id self) throws InterruptedException {
        createTriggerVolume("badge124", 10.0f, true);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volName, obj_id breecher) throws InterruptedException {
        if (!isPlayer(breecher)) {
            return SCRIPT_CONTINUE;
        } else {
            if (!hasObjVar(self, "badge") && !hasObjVar(self, "icon")) {
                return SCRIPT_CONTINUE;
            }
            if (hasObjVar(self, "badge")) {
                int badgeNum = getIntObjVar(self, "badge");
                String badgeName = getCollectionSlotName(badgeNum);
                if ((badgeName != null) && (badgeName.length() > 0) && (!badge.hasBadge(breecher, badgeName))) {
                    dictionary explorerBadges = new dictionary();
                    explorerBadges.put("badgeNumber", badgeNum);
                    messageTo(breecher, "explorerBadge", explorerBadges, 0, false);
                    return SCRIPT_CONTINUE;
                }
            } else {
                int iconNum = getIntObjVar(self, "icon");
                String slotName = getCollectionSlotName(iconNum);
                if ((slotName != null) && (slotName.length() > 0) && (!hasCompletedCollectionSlot(breecher, slotName))) {
                    modifyCollectionSlotValue(breecher, slotName, 1);
                    return SCRIPT_CONTINUE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
