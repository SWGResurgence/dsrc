package script.theme_park.stp.items;

import script.obj_id;

/**
 * @author BubbaJoe
 */
public class the_child extends script.base_script
{
    public the_child()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "a mysterious child");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "a mysterious child");
        return SCRIPT_CONTINUE;
    }
}
