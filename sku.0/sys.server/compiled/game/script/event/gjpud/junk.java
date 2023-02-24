package script.event.gjpud;/*
@Filename: script.event.gjpud.junk
@Author: BubbaJoeX
@Purpose: Junk object for Galactic Junk Pickup Day 2023
@ increases
*/

import script.*;

import java.lang.reflect.InvocationTargetException;

public class junk extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest( obj_id self, obj_id player, menu_info mi )
    {
        if (isGod(player))
        {
            int main = mi.addRootMenu( menu_info_types.ITEM_USE, new string_id( "event/gjpud", "gjpud_menu" ));
            mi.addSubMenu(main, menu_info_types.SERVER_MENU1, new string_id( "event/gjpud", "gjpud_spawn" ));
            mi.addSubMenu(main, menu_info_types.SERVER_MENU2, new string_id( "event/gjpud", "gjpud_destroy" ));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, InvocationTargetException
    {

        return SCRIPT_CONTINUE;
    }
}
