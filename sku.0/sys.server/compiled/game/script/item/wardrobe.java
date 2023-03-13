/*
@Purpose: Wardrobe script for the Lifeday 2022 Reward

@Author: BubbaJoe
 */
package script.item;

import script.library.static_item;
import script.obj_id;

public class wardrobe extends script.base_script
{
    public wardrobe()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!getTemplateName(item).contains("object/tangible/wearables/"))
        {
            broadcast(transferer, "You can only put wearables in this container.");
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(item, "wardrobe.ownedBy"))
        {
            setObjVar(item, "wardrobe.ownedBy", transferer);
        }
        setName(item, getNewName(item));
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!getTemplateName(item).contains("object/tangible/wearables/"))
        {
            broadcast(transferer, "You can only retrieve wearables from this container.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(item, "wardrobe.ownedBy"))
        {
            if (getObjIdObjVar(item, "wardrobe.ownedBy") != transferer)
            {
                broadcast(transferer, "You can only retrieve your own wearables from this container.");
                return SCRIPT_CONTINUE;
            }
        }
        if (!hasScript(item, "systems.armor_rehue.composite_rehue"))
        {
            attachScript(item, "systems.armor_rehue.composite_rehue");
        }
        return SCRIPT_CONTINUE;
    }

    public String getNewName(obj_id item) throws InterruptedException
    {
        if (static_item.isStaticItem(item))
        {
            return static_item.getStaticItemName(item) + " (modified)";
        }
        else
        {
            return getEncodedName(item) + " (modified)";
        }
    }
}
