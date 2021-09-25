package script.cureward;

import script.base_script;
import script.library.static_item;
import script.library.sui;
import script.obj_id;

public class cureward extends script.base_script {
    private static final byte[] DAYS = { 3, 7, 14, 21, 28, 35 };
    private static final int DAYS_ON_LAUNCH = 6086;
    private static final String[] OBJVARS = {
        "loyalty_granted",
        "gold_granted",
        "respec_device_granted",
        "bacta_tank_granted",
        "publish28_granted",
        "chapter1_granted"
    };
    private static final String[][] REWARDS = {
        new String[]{ "recapture_gift_chapter_11_hoth_hologram_02_01", "object/tangible/furniture/decorative/hologram_nebulon_frigate.iff" },
        new String[]{ "object/tangible/event_perk/frn_loyalty_award_plaque_silver.iff", "object/tangible/event_perk/frn_loyalty_award_plaque_gold.iff" },
        new String[]{ "item_respec_token_01_01", "veteranPlayerBuff" },
        new String[]{ "item_publish_gift_27_04_01", "weapon_publish_gift_27_04_01" },
        new String[]{ "item_tcg_loot_reward_series4_ball_of_peace_02_01" },
        new String[]{ "item_publish_gift_29_mustafar_04_01", "item_publish_gift_29_ryatt_04_01", "item_publish_gift_29_corellia_04_01" }
    };

    public int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException {
        int birth = getPlayerBirthDate(self) - DAYS_ON_LAUNCH;

        if (birth <= DAYS[DAYS.length - 1]) {
            for (int i = 0; i < DAYS.length; i++) {
                CheckAndGrant(self, birth, i);
            }
        }
        return SCRIPT_CONTINUE;
    }

    private void CheckAndGrant(obj_id self, int birth, int tier) throws InterruptedException {
        if (birth <= DAYS[tier]) {
            if (!hasObjVar(self, "cureward." + OBJVARS[tier])) {
                setObjVar(self, "cureward." + OBJVARS[tier], 1);
                obj_id[] items = new obj_id[REWARDS[tier].length];
                for (int i = 0; i < REWARDS[tier].length; i++) {
                    if (REWARDS[tier][i].startsWith("object/")) {
                        items[i] = createObjectInInventoryAllowOverload(REWARDS[tier][i], self);
                    } else if (REWARDS[tier][i].equals(REWARDS[2][1])) {
                        if (!hasCommand(self, REWARDS[tier][i])) {
                            grantCommand(self, REWARDS[tier][i]);
                        }
                    } else {
                        items[i] = static_item.createNewItemFunction(REWARDS[tier][i], self);
                    }
                }
                showLootBox(self, items);
            }
        }
    }
}
