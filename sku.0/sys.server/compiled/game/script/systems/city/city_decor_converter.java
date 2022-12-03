package script.systems.city;

import script.dictionary;
import script.library.create;
import script.library.utils;
import script.obj_id;

import static script.library.storyteller.STORYTELLER_DATATABLE;

public class city_decor_converter extends script.base_script
{
    public city_decor_converter()
    {
    }

    //@Converts a story teller object to a city decor object
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

    public int OnAboutToReceiveItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isIdValid(item))
        {
            if (hasObjVar(item, "noTrade") || (hasScript(item, "item.special.nomove")))
            {
                broadcast(transferer, "You cannot decorate your city with this item.");
                return SCRIPT_OVERRIDE;
            }
            if (getTemplateName(item).equals("object/tangible/storyteller/story_token_prop.iff"))
            {
                createPropObject(self, item);
                return SCRIPT_CONTINUE;
            }
            if (getTemplateName(item).equals("object/tangible/storyteller/story_token_flavor_npc.iff"))
            {
                //createActorProp(self, item, transferer);
                broadcast(transferer, "This feature is not yet implemented.");
                return SCRIPT_OVERRIDE;
            }
            if (getTemplateName(item).equals("object/tangible/storyteller/story_token_combat_npc.iff"))
            {
                //createActorProp(self, item, transferer);
                broadcast(transferer, "This feature is not yet implemented.");
                return SCRIPT_OVERRIDE;
            }
            else if (getTemplateName(item).contains("object/tangible/"))
            {
                attachScript(item, "systems.city.city_furniture");
                String oldName = getStaticItemName(item);
                setName(item, oldName + " (City Decoration)");
                return SCRIPT_CONTINUE;
            }
        }

        else
        {
            broadcast(transferer, "You can only convert physical items.");
        }
        return SCRIPT_CONTINUE;
    }
    //@Converts a story teller object to a city actor object
    public void createActorProp(obj_id self, obj_id item, obj_id transferer) throws InterruptedException
    {
        String itemName = getStaticItemName(item);
        int row = dataTableSearchColumnForString(itemName, "name", STORYTELLER_DATATABLE);
        dictionary dict = dataTableGetRow(STORYTELLER_DATATABLE, itemName);
        String template = dict.getString("template_name");
        obj_id prop = create.createObject(template, utils.getInventoryContainer(transferer), "");
        if (isIdValid(prop))
        {
            detachAllScripts(prop);
            attachScript(prop, "systems.city.city_actor");
            destroyObject(item);
        }
    }

}
