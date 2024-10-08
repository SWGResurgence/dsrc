package script.item.loot;

import script.library.buff;
import script.library.utils;
import script.obj_id;
import script.menu_info;
import script.menu_info_types;
import script.menu_info_data;
import script.string_id;

public class toy extends script.base_script
{
    public toy()
    {
    }
    public static int COOLDOWN_TIME = 14400;
    public static int currentGameTime = getCalendarTime();
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        int lastUsed = getIntObjVar(self, "used.timestamp");
        names[idx] = "last_used";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp"));
        idx++;
        names[idx] = "next_use";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp") + 14400);
        idx++;
        String NO = "\\#DD1234" + "No" + "\\#FFFFFF";
        String YES = "\\#32CD32" + "Yes" + "\\#FFFFFF";
        if(currentGameTime < (lastUsed + COOLDOWN_TIME))
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
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Katiara's Toy (Enhancement)");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("spam", "use"));
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
                buff.applyBuff(player, "nova_orion_rank6_lucky_salvage");
                setObjVar(self, "used.timestamp", currentGameTime);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
