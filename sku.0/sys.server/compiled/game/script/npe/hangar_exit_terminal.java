package script.npe;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.npe;
import script.library.utils;
import script.*;

public class hangar_exit_terminal extends script.base_script
{
    public static final string_id OPEN_HANGAR = new string_id("npe_hangar_1", "open_hangar");

    public hangar_exit_terminal()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        else
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("ui_radial", "item_use"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            removeObjVar(player, "canpickupmedikit");
            removeObjVar(player, "finishedtutorial");
            utils.removeScriptVar(player, "npe.hangarEventStarted");
            if (checkGod(player))
            {
                return SCRIPT_CONTINUE;
            }
            detachScript(player, "npe.han_solo_experience_player");
            attachScript(player, "npe.npe_falcon_player");
            npe.movePlayerFromHangarToFalcon(player);
        }
        return SCRIPT_CONTINUE;
    }

    public boolean checkGod(obj_id self) throws InterruptedException
    {
        if (isGod(self))
        {
            broadcast(self, "Please turn off god mode when moving between npe locations. God mode and instances do not get along");
            return true;
        }
        return false;
    }
}
