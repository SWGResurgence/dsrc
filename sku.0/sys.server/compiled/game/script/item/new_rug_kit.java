package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Way to obtain new rugs.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
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
        setDescriptionString(self, "This kit can be used to create three random rugs from 171 variations. Select 'Fabricate Rugs' to create your rugs.");
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
            mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Fabricate Rugs"));
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
            if (getVolumeFree(utils.getInventoryContainer(player)) < 3)
            {
                broadcast(player, "You do not have enough inventory space to fabricate three rugs.");
                return SCRIPT_CONTINUE;
            }
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
                items[i] = (obj_id) theSet.toArray()[i];
                setName(items[i], "an exotic rug");
                setObjVar(items[i], "null_desc", "This exotic rug was fabricated from Abbub's Rug Kit. What a spectacular rug!");
                attachScript(items[i], "developer.bubbajoe.sync");
            }
            playClientEventObj(player, "sound/item_cloth_open.snd", player, "");
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
