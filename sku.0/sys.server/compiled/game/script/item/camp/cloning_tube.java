package script.item.camp;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.structure;
import script.obj_id;

public class cloning_tube extends script.base_script
{
    public cloning_tube()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "handleInitializeCloningTube", null, 3.0f, false);
        return SCRIPT_CONTINUE;
    }

    public int handleInitializeCloningTube(obj_id self, dictionary params) throws InterruptedException
    {
        attachScript(self, "structure.municipal.cloning_facility");
        String myTemplate = getTemplateName(self);
        dictionary facilityData = dataTableGetRow(structure.DATATABLE_CLONING_FACILITY_RESPAWN, myTemplate);
        dictionary dict = new dictionary();
        dict.put("facilityData", facilityData);
        messageTo(self, "registerCloningFacility", dict, 10.0f, false);
        return SCRIPT_CONTINUE;
    }
}
