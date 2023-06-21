package script.theme_park.dungeon.mustafar_trials.obiwan_finale;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class obiwan_player_reattempt_delay extends script.base_script
{
    public static final int REATTEMPT_DELAY_MINUTES = 120;

    public obiwan_player_reattempt_delay()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        initializeTimeDelay(self);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        mustafarReattemptDelayCheck(self);
        return SCRIPT_CONTINUE;
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        mustafarReattemptDelayCheck(self);
        return SCRIPT_CONTINUE;
    }

    public void initializeTimeDelay(obj_id player) throws InterruptedException
    {
        int currentTime = getGameTime();
        int delayTime = currentTime + (REATTEMPT_DELAY_MINUTES * 60);
        setObjVar(player, "mustafar.reattemptDelay", delayTime);
    }

    public void mustafarReattemptDelayCheck(obj_id player) throws InterruptedException
    {
        if (!hasObjVar(player, "mustafar.reattemptDelay"))
        {
            detachScript(player, "player_reattempt_delay");
            return;
        }
        int timeOutEnd = getIntObjVar(player, "mustafar.reattemptDelay");
        int currentTime = getGameTime();
        if (currentTime < timeOutEnd)
        {
            int delayTimeRemaining = timeOutEnd - currentTime;
            if (delayTimeRemaining < 60)
            {
                return;
            }
            messageTo(player, "mustafarReattemptTimeoutExpire", null, delayTimeRemaining, false);
            return;
        }
        removeObjVar(player, "mustafar.reattemptDelay");
        detachScript(player, "them_park.dungeon.mustafar_trials.obiwan_finale.player_reattempt_delay");
    }

    public int mustafarReattemptTimeoutExpire(obj_id self, dictionary params) throws InterruptedException
    {
        mustafarReattemptDelayCheck(self);
        return SCRIPT_CONTINUE;
    }
}
