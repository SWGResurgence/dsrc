/*

@Purpose Lifeday Actor Script

@Author BubbaJoe
 */

package script.event.lifeday;


/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.library.chat;
import script.obj_id;


public class celebration_actor extends script.base_script
{
    public static String[] actorChatter =
            {
                    "Man, I am tired. I hope this is the last one.",
                    "I can't wait to get home and sleep.",
                    "I hope I get a good spot in the parade.",
                    "I hope I get to see the parade.",
                    "I hope I start to feel better soon.",
                    "I hope I don't get sick.",
                    "I wonder where the parade is going to be this year.",
                    "I wonder what the parade is going to be like this year.",
            };

    public celebration_actor()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_STOP);
        setAnimationMood(self, "conversation");
        chirp(self);
        return SCRIPT_CONTINUE;
    }

    public void chirp(obj_id self) throws InterruptedException
    {
        chat.chat(self, getRandomEntry(actorChatter));
        messageTo(self, "continueChirping", null, 10f, false);
    }

    public void continueChirping(obj_id self) throws InterruptedException
    {
        messageTo(self, "chirp", null, 1f, false);
    }

    public String getRandomEntry(String[] array) throws InterruptedException
    {
        int randomIndex = rand(0, array.length - 1);
        String randomEntry = array[randomIndex];
        return randomEntry;
    }
}
