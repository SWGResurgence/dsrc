package script.systems.crafting.bio_engineer;

import script.obj_id;

public class dna_component_attrib extends script.base_script
{

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "");
        return SCRIPT_CONTINUE;
    }
}
