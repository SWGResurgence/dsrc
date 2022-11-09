package script.item.loot;

import script.library.buff;
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
    public int OnObjectMenuResponse(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (mi.getMenuItemByType(menu_info_types.ITEM_USE) != null)
        {
            int currentGameTime = getCalendarTime();
            if(hasObjVar(self, "used.timestamp"))
            {
                int lastUsed = getIntObjVar(self, "used.timestamp");
                if(currentGameTime < (lastUsed + 14400))
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
        }
        return SCRIPT_CONTINUE;
    }
}
