package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Stops spam from the vet instance reset script until it gets cycled out of the database.
*/

import script.obj_id;

public class vet_instance_reset extends script.base_script
{
    public int DUMMY_SCRIPT = 1;
    public int OnAttach(obj_id self)
    {
        return DUMMY_SCRIPT;
    }

    public int OnInitialize(obj_id self)
    {
        return DUMMY_SCRIPT;
    }
}
