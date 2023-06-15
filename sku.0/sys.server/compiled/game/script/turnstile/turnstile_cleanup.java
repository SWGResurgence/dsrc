package script.turnstile;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.turnstile;
import script.obj_id;

public class turnstile_cleanup extends script.base_script
{
    public static final String HANDLER_EXPIRED_CLEANUP = "handleExpiredCleanup";

    public turnstile_cleanup()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, HANDLER_EXPIRED_CLEANUP, null, turnstile.TURNSTILE_CLEANUP_HEARTBEAT, false);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, HANDLER_EXPIRED_CLEANUP, null, turnstile.TURNSTILE_CLEANUP_HEARTBEAT, false);
        return SCRIPT_CONTINUE;
    }

    public int OnDetach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int handleExpiredCleanup(obj_id self, dictionary params) throws InterruptedException
    {
        turnstile.cleanupExpiredPatrons(self);
        messageTo(self, HANDLER_EXPIRED_CLEANUP, null, turnstile.TURNSTILE_CLEANUP_HEARTBEAT, false);
        return SCRIPT_CONTINUE;
    }
}
