/*
@Purpose: Wardrobe script for the Lifeday 2022 Reward

@Author: BubbaJoe
 */
package script.item;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.static_item;
import script.obj_id;

public class wardrobe extends script.base_script
{
    public wardrobe()
    {
    }

    public int OnAboutToBeTransferred(obj_id self, obj_id destContainer, obj_id transferer) throws InterruptedException
    {
        if (isPlayer(transferer) && (getVolumeFree(self) == getTotalVolume(self)))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            broadcast(transferer, "This container is too big to be carried full. Try removing some items, then try again.");
        }
        return SCRIPT_OVERRIDE;
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
        if (!hasScript(item, "item.special.recolor"))
        {
            attachScript(item, "item.special.recolor");
        }
        return SCRIPT_CONTINUE;
    }
}
