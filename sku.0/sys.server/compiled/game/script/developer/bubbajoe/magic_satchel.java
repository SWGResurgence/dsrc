package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.magic_satchel
@Author: BubbaJoeX
@Purpose: Clones an object 1:1 wet (with scripts/scriptvars/objvars) and places it in a satchel inside your inventory.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.create;
import script.library.utils;
import script.*;

import java.util.Objects;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class magic_satchel extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Magic Satchel");
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

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, NullPointerException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id pInv = utils.getInventoryContainer(player);
            if (getVolumeFree(pInv) <= 0)
            {
                broadcast(player, "Your inventory is full.");
                return SCRIPT_CONTINUE;
            }
            obj_id[] dupeContents = utils.getContents(self);
            for (obj_id converted : dupeContents)
            {
                String[] SCRIPTS = getScriptList(converted);
                obj_id dupe = create.createObject(getTemplateName(converted), pInv, "");
                for (String SCRIPT : SCRIPTS)
                {
                    attachScript(dupe, SCRIPT);
                }
                obj_var_list dupeVars = getObjVarList(converted, "");
                IntStream.range(0, dupeVars.getNumItems()).mapToObj(dupeVars::getObjVar).filter(Objects::nonNull).forEach(dupeVar -> setObjVar(dupe, dupeVar.getName(), dupeVar.getData()));
                broadcast(player, converted + " has been cloned to " + dupe + "! ");
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            obj_id[] dupeContents = getContents(self);
            for (obj_id converted : dupeContents)
            {
                destroyObject(converted);
            }
            broadcast(player, "Contents cleared.");
        }
        return SCRIPT_CONTINUE;
    }
}
