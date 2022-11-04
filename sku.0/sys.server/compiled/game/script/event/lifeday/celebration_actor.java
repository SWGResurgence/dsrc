/*

@Purpose Lifeday Actor Script

@Author BubbaJoe
 */

package script.event.lifeday;


import script.location;
import script.obj_id;
import script.library.chat;
import script.library.ai_lib;


public class celebration_actor extends script.base_script
{
    public celebration_actor()
    {
    }
    public static String[] actorChatter =
            {
                    "@@CHANGE_ME@@",
                    "@@CHANGE_ME@@",
                    "@@CHANGE_ME@@",
            };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_STOP);
        setAnimationMood(self, "conversation");
        chirp(self);
        continueChirping(self);
        return SCRIPT_CONTINUE;
    }
    public void chirp(obj_id self) throws InterruptedException
    {
        chat.chat(self, getRandomEntry(actorChatter));
    }
    public void continueChirping(obj_id self) throws InterruptedException
    {
        messageTo(self, "chirp", null, 360.0f, false);
    }
    public String getRandomEntry(String[] array) throws InterruptedException
    {
        int randomIndex = rand(0, array.length - 1);
        String randomEntry = array[randomIndex];
        return randomEntry;
    }
}
