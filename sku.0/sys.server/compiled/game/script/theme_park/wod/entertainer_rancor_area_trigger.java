package script.theme_park.wod;

import script.library.ai_lib;
import script.library.attrib;
import script.library.utils;
import script.obj_id;

public class entertainer_rancor_area_trigger extends script.base_script
{

    public static final boolean debug = false;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("wod_rancor_area", 15, true);
        setAttributeInterested(self, attrib.BEAST);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        createTriggerVolume("wod_rancor_area", 15, true);
        setAttributeInterested(self, attrib.BEAST);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id who) throws InterruptedException
    {
        if (debug)
        {
            debugSpeakMsg(self, "ermahgerd " + who + " entered");
        }
        String placeWeAre = "nsRancorFinished";
        placeWeAre = getStringObjVar(self, "wod.areaName");
        if (debug)
        {
            sendSystemMessageTestingOnly(who, "you entered " + volumeName);
        }
        if (volumeName.equals("wod_rancor_area"))
        {
            if (utils.hasScriptVar(who, "wod_rancor_followingPlayer"))
            {
                obj_id rancorIsFollowing = utils.getObjIdScriptVar(who, "wod_rancor_followingPlayer");
                if (debug)
                {
                    sendSystemMessageTestingOnly(rancorIsFollowing, "your rancor following you entered " + volumeName);
                }
                messageTo(rancorIsFollowing, placeWeAre, null, 1.0f, false);
                ai_lib.aiStopFollowing(who);
                messageTo(who, "corpseCleanup", null, 5.0f, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
