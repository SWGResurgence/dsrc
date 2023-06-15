package script.developer.soe.beta;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class featuretest extends script.base_script
{
    public static final int SWG_BASE_GAME = 0x00000001;
    public static final int SWG_COLLECTORS_GAME = 0x00000002;
    public featuretest()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        debugSpeakMsg(self, "feature test script attached");
        return SCRIPT_CONTINUE;
    }

    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (text.equals("haha no more"))
        {
            broadcast(self, "DEPRECATED");
        }
        return SCRIPT_CONTINUE;
    }
}
