package script.theme_park.corellia.content;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.obj_id;
import script.string_id;

public class kor_vella_mayors_office_block extends script.base_script
{
    public kor_vella_mayors_office_block()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (groundquests.isQuestActiveOrComplete(item, "corellia_38_corsec_files_05"))
        {
            string_id warning = new string_id("theme_park/corellia/quest", "kor_vella_mayors_office");
            sendSystemMessage(item, warning);
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
}
