package script.systems.city;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

import static script.library.storyteller.STORYTELLER_DATATABLE;
import static script.library.storyteller.isAnyStorytellerItem;

public class city_decor_converter extends script.base_script
{
    public city_decor_converter()
    {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isIdValid(item))
        {
            if (getTemplateName(item).equals("object/tangible/storyteller/story_token_prop.iff"))
            {
                createPropObject(self, item);
                return SCRIPT_CONTINUE;
            }
            else if (getTemplateName(item).contains("object/tangible/"))
            {
               detachAllScripts(item);
               attachScript(item, "systems.city.city_furniture");
               setName(item, "");
               //bad way to do this. @todo: add in jni call/client change to hide name without hidding object. & crafter check
               return SCRIPT_CONTINUE;
            }
        }

        else
        {
            broadcast(transferer, "You can only convert physical items.");
        }
        return SCRIPT_CONTINUE;
    }
    public static void createPropObject(obj_id self, obj_id token) throws InterruptedException
    {
        obj_id prop = null;
        String itemName = getStaticItemName(token);
        int row = dataTableSearchColumnForString(itemName, "name", STORYTELLER_DATATABLE);
        dictionary dict = dataTableGetRow(STORYTELLER_DATATABLE, itemName);
        String template = dict.getString("template_name");
        prop = create.createObject(template, self, "");
        if (isIdValid(prop))
        {
            setName(prop, " ");
            obj_id mainInv = utils.getTopMostContainer(self);
            putIn(prop, mainInv);
            destroyObject(token);
        }
    }

}
