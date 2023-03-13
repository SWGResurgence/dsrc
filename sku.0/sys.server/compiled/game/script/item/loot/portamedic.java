package script.item.loot;

import script.library.buff;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class portamedic extends script.base_script
{
    public static int COOLDOWN_TIME = 3600 * 2; // 2 hours
    public static float BUFF_MODIFIER = 75.0f;
    public static int currentGameTime = getCalendarTime();

    public portamedic()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Bubba's Nervous Fluid");
        if (hasScript(self, "item.full_heal_item"))
        {
            detachScript(self, "item.full_heal_item");
        }
        if (hasScript(self, "item.static_item_base"))
        {
            detachScript(self, "item.static_item_base");
        }
        if (hasScript(self, "object.autostack"))
        {
            detachScript(self, "object.autostack");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        int lastUsed = getIntObjVar(self, "used.timestamp");
        names[idx] = "last_used";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp"));
        idx++;
        names[idx] = "next_use";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp") + COOLDOWN_TIME);
        idx++;
        String NO = "\\#DD1234" + "No" + "\\#FFFFFF";
        String YES = "\\#32CD32" + "Yes" + "\\#FFFFFF";
        if (currentGameTime < (lastUsed + COOLDOWN_TIME))
        {
            names[idx] = "ready";
            attribs[idx] = NO;
            idx++;
        }
        else
        {
            names[idx] = "ready";
            attribs[idx] = YES;
            idx++;
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Sample the Fluids"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int mi) throws InterruptedException
    {
        if (mi == menu_info_types.ITEM_USE)
        {
            if (getContainedBy(self) != utils.getInventoryContainer(player))
            {
                sendSystemMessage(player, new string_id("spam", "must_be_in_inventory"));
                return SCRIPT_CONTINUE;
            }
            int lastUsed = getIntObjVar(self, "used.timestamp");
            if (currentGameTime < (lastUsed + COOLDOWN_TIME))
            {
                broadcast(player, "You cannot use this yet.");
                return SCRIPT_CONTINUE;
            }
            else
            {
                buff.applyBuff((player), "me_buff_health_2", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_action_3", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_strength_3", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_agility_3", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_precision_3", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_melee_gb_1", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                buff.applyBuff((player), "me_buff_ranged_gb_1", (float) COOLDOWN_TIME, BUFF_MODIFIER);
                setObjVar(self, "used.timestamp", currentGameTime);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
