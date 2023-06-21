package script.name;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.player_structure;
import script.library.static_item;
import script.library.sui;
import script.library.utils;

public class name extends script.base_script
{
    public static final int MAX_NAME_LENGTH = 32;
    public static final string_id SID_SET_NAME_TITLE = new string_id("sui", "set_name_title");
    public static final string_id SID_SET_NAME_PROMPT = new string_id("sui", "set_name_prompt");
    public static final String HANDLER_NAME_OBJECT = "handleNameObject";
    public name()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (static_item.isStaticItem(self))
        {
            LOG("static_item", "name.OnObjectMenuRequest() - " + player + " - static item, Adding menu because why not, Old name: " + getName(self));
        }
        if (utils.isNestedWithin(self, player) || isInPlayersHouse(self, player))
        {
            mi.addRootMenu(menu_info_types.SET_NAME, new string_id("Rename"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SET_NAME)
        {
            if (utils.isNestedWithin(self, player) || isInPlayersHouse(self, player) && player_structure.isAdmin(getTopMostContainer(self), player))
            {
                showNameInputUI(player, self);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void showNameInputUI(obj_id player, obj_id self) throws InterruptedException
    {
        String title = utils.packStringId(SID_SET_NAME_TITLE);
        String prompt = utils.packStringId(SID_SET_NAME_PROMPT);
        sui.inputbox(self, player, prompt, title, HANDLER_NAME_OBJECT, MAX_NAME_LENGTH, true, getEncodedName(self));
    }

    public int handleNameObject(obj_id self, dictionary params) throws InterruptedException
    {
        if ((params == null) || (params.isEmpty()))
        {
            return SCRIPT_CONTINUE;
        }
        int btn = sui.getIntButtonPressed(params);
        if (btn == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        if (!isIdValid(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (utils.isNestedWithin(self, player) || isInPlayersHouse(self, player))
        {
            String text = sui.getInputBoxText(params);
            int i = 0;
            int textlen = text.length();
            while (textlen > i && '@' == text.charAt(i))
            {
                ++i;
            }
            text = text.substring(i, textlen);
            if (!text.equals(""))
            {
                setName(self, text);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public boolean isInPlayersHouse(obj_id self, obj_id player) throws InterruptedException
    {
        if (utils.isInHouseCellSpace(self))
        {
            obj_id structure = getTopMostContainer(self);
            return player_structure.isAdmin(structure, player);
        }
        return false;
    }
}
