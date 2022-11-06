package script.systems.city;

import script.dictionary;
import script.library.factions;
import script.library.utils;
import script.obj_id;

public class city_decor_converter extends script.base_script
{
    public city_decor_converter()
    {
    }
    public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isCrafted(item))
        {
            if (getTemplateName(item).contains("object/tangible/furniture/") || getTemplateName(item).contains("object/tangible/tarkin_custom/"))
            {
               attachScript(item, "systems.city.city_decor");
               setName(item, " ");
               //bad way to do this. @todo: add in jni call to hide name without hidding object.
               return SCRIPT_CONTINUE;
            }
        }
        else
        {
            broadcast(transferer, "You can only convert crafted items.");
        }
        return SCRIPT_CONTINUE;
    }
}
