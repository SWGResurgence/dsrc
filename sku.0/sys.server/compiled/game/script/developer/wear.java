package script.developer;

import script.obj_id;

public class wear extends script.base_script
{
    public wear()
    {
    }

    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        equipOverride(item, self);
        return SCRIPT_CONTINUE;
    }
}
