package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose:
*/

import script.*;
import script.library.utils;

public class loot_roll_item extends base_script
{
    public loot_roll_item()
    {
    }
    public static String LR_VAR = "loot_roll";
    public static String LR_INCREASE_VAR = "loot_roll.charges";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!hasObjVar(self, LR_INCREASE_VAR))
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Test Your Luck"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!hasObjVar(self, LR_INCREASE_VAR))
            {
                int charges = rand(1, 5);
                setObjVar(self, LR_INCREASE_VAR, charges);
                broadcast(player, "You have been granted " + charges + " additional loot rolls to use on a heroic enemy.");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, LR_INCREASE_VAR))
        {
            names[idx] = utils.packStringId(new string_id("Loot roll bonus"));
            attribs[idx] = "" + getIntObjVar(self, LR_INCREASE_VAR);
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

}
