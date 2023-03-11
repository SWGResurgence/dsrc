package script.df;/*
@Filename: script.df.handler
@Author: BubbaJoeX
@Purpose:
*/

import script.obj_id;

import java.util.StringTokenizer;

public class handler extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int cmdDungeonFinder(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        if (!isIdValid(target) || !isPlayer(target) || params == null || params.equalsIgnoreCase(""))
        {
            sendSystemMessageTestingOnly(self, "[syntax] /dungeonFinder status");
        }
        else
        {
            StringTokenizer st = new StringTokenizer(params);
            String command = st.nextToken();
            if (command.equals("status"))
            {
                listAllDungeonStatuses(self);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int listAllDungeonStatuses(obj_id self)
    {
        broadcast(self, "insert sui message box here with ok & cancel.");
        return SCRIPT_CONTINUE;
    }
}
