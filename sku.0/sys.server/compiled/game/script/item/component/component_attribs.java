package script.item.component;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.craftinglib;
import script.library.utils;
import script.obj_id;
import script.obj_var;
import script.obj_var_list;

public class component_attribs extends script.base_script
{
    public component_attribs()
    {
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        if (names == null || attribs == null || names.length != attribs.length)
        {
            return SCRIPT_CONTINUE;
        }
        int i = getFirstFreeIndex(names);
        if (i != -1 && i < names.length)
        {
            float complexity = getComplexity(self);
            if (complexity >= 0)
            {
                names[i] = "@crafting:complexity";
                attribs[i] = Float.toString(complexity);
                ++i;
            }
            obj_var_list componentData = getObjVarList(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME);
            if (componentData != null)
            {
                int count = componentData.getNumItems();
                for (int j = 0; j < count; ++j)
                {
                    obj_var component = componentData.getObjVar(j);
                    if (component != null && component.getData() != null)
                    {
                        names[i] = "@crafting:" + component.getName();
                        attribs[i] = (component.getData()).toString();
                        ++i;
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String template = getTemplateName(self);
        String[] needToBeFixed = new String[]
                {
                        "object/tangible/component/armor/armor_layer_nightsister.iff",
                        "object/tangible/component/armor/feather_peko_albatross.iff",
                        "object/tangible/component/armor/armor_layer_ris.iff",
                        "object/tangible/component/armor/armor_appearance_enhancement_feather.iff",
                        "object/tangible/component/armor/scale_giant_dune_kimogila.iff",
                        "object/tangible/component/armor/hide_gurk_king.iff",
                        "object/tangible/component/armor/bone_fragment_woolamander_harrower.iff"

                };
        if (utils.getElementPositionInArray(needToBeFixed, template) > -1)
        {
            float val = getFloatObjVar(self, "attribute.bonus.0");
            if (val != 0)
            {
                int intVal = (int) val;
                removeObjVar(self, "attribute.bonus.0");
                setObjVar(self, "attribute.bonus.0", intVal);
            }
        }
        if (utils.getElementPositionInArray(needToBeFixed, template) > -1)
        {
            float val = getFloatObjVar(self, "attribute.bonus.2");
            if (val != 0)
            {
                int intVal = (int) val;
                removeObjVar(self, "attribute.bonus.2");
                setObjVar(self, "attribute.bonus.2", intVal);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
