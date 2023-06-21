package script.systems.buff_builder;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class buff_builder_cancel extends script.base_script
{
    public static final String SCRIPT_BUFF_BUILDER_CANCEL = "systems.buff_builder.buff_builder_cancel";

    public buff_builder_cancel()
    {
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        if (isIdValid(self))
        {
            detachScript(self, SCRIPT_BUFF_BUILDER_CANCEL);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnBuffBuilderCanceled(obj_id self) throws InterruptedException
    {
        if (isIdValid(self))
        {
            detachScript(self, SCRIPT_BUFF_BUILDER_CANCEL);
        }
        return SCRIPT_CONTINUE;
    }
}
