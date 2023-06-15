package script.developer.soe.test;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class report_posture extends script.base_script
{
    public report_posture()
    {
    }

    public int OnHearSpeech(obj_id self, obj_id speaker, String text) throws InterruptedException
    {
        if (text.equals("report"))
        {
            debugSpeakMsg(self, "Posture is " + getPosture(self) + "Locomotion is " + getLocomotion(self));
        }
        return SCRIPT_CONTINUE;
    }
}
