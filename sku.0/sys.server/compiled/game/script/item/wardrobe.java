package script.item;

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
            return SCRIPT_CONTINUE;
        }
        setObjVar(item, "wardrobe", transferer);
        return SCRIPT_CONTINUE;
    }
}
