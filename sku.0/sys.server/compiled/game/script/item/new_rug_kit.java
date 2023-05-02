package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Way to obtain new rugs.
*/

import script.*;
import script.library.create;
import script.library.static_item;
import script.library.utils;

import java.util.HashSet;

public class new_rug_kit extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        reInitializeKit(self);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        reInitializeKit(self);
        return SCRIPT_CONTINUE;
    }

    public void reInitializeKit(obj_id self)
    {
        setName(self, "Abbub's Rug Kit");
        setDescriptionString(self, "This kit can be used to create three random rugs from 171 variations.  Use the kit to select the rug you want to create.");
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (isIncapacitated(self) || isDead(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (utils.isNestedWithinAPlayer(self))
        {
           mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Fabricate Rug"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (isIncapacitated(self) || isDead(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            String rugPrefix = "object/tangible/tarkin_custom/decorative/rug/tarkin_rug_";
            String rugSuffix = ".iff";
            obj_id pInv = utils.getInventoryContainer(player);
            HashSet theSet = new HashSet();
            theSet.add(create.createObject(rugPrefix + (rand(1, 171)) + rugSuffix, pInv, ""));
            theSet.add(create.createObject(rugPrefix + (rand(1, 171)) + rugSuffix, pInv, ""));
            theSet.add(create.createObject(rugPrefix + (rand(1, 171)) + rugSuffix, pInv, ""));
            obj_id[] items = new obj_id[theSet.size()];
            for (int i = 0; i < theSet.size(); i++)
            {
                items[i] = (obj_id)theSet.toArray()[i];
                setName(items[i], "an exotic rug");
                setDescriptionStringId(items[i], unlocalized("This exotic rug was created by Abbub's Rug Kit."));
            }
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
