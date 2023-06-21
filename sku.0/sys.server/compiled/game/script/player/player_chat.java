package script.player;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class player_chat extends script.base_script
{
    public player_chat()
    {
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        LOG("ChatFilter", "OnChatLogin");
        return SCRIPT_CONTINUE;
    }
}
