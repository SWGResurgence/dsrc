package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.magic_satchel
@Author: BubbaJoeX
@Purpose: Clones an object 1:1 wet (with scripts and objvars) and places it in a satchel inside your inventory.
*/

import script.library.create;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class magic_satchel extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Magic Satchel");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isGod(player))
        {
            int mainMenu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Clone Contents"));
            mi.addSubMenu(mainMenu, menu_info_types.SERVER_MENU1, new string_id("Clear Contents"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id dupeContainer = utils.getInventoryContainer(self);
            obj_id pInv = utils.getInventoryContainer(player);
            if (getVolumeFree(pInv) <= 0)
            {
                broadcast(player, "Your inventory is full.");
            }
            obj_id[] dupeContents = getContents(dupeContainer);
            for (obj_id converted : dupeContents)
            {
                obj_id final_product = utils.stringToObjId(getTemplateName(converted));
                String[] scriptList = converted.getScripts();
                if (scriptList != null)
                {
                    for (String script : scriptList)
                    {
                        attachScript(final_product, script);
                    }
                }
                String packedVars = getPackedObjvars(converted);
                setPackedObjvars(final_product, packedVars);
                obj_id goodie_bag = create.createObject("object/tangible/container/general/satchel.iff", pInv, "");
                moveContents(dupeContainer, goodie_bag);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
