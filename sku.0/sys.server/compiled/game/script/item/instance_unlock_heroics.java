package script.item;

import script.dictionary;
import script.library.instance;
import script.library.resource;
import script.library.sui;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class instance_unlock_heroics extends script.base_script
{
    public static final String HANDLER_SET_TOOL_CLASS = "handleSetToolClass";
    public static final string_id SID_TOOL_OPTIONS = new string_id("sui", "Unlock_Heroics");
    public static final string_id SID_TOOL_CLASS = new string_id("sui", "Set_Heroic");

    public instance_unlock_heroics()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Heroic Unlock Terminal");
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_SURVEY_TOOL_CLASS)
        {
            String[] instanceFlags = dataTableGetStringColumn(instance.HEROIC_INSTANCE_DATATABLE, "key_required");
            if (instanceFlags != null && instanceFlags.length > 0)
            {
                for (String flag : instanceFlags)
                {
                    if (flag != null && flag.length() > 0)
                    {
                        instance.flagPlayerForInstance(player, flag);
                    }
                }
                sendSystemMessageTestingOnly(player, "All Heroic Instances Flagged");
            }
            else
            {
                sendSystemMessageTestingOnly(player, "No Instance Flags Found.");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int handleSetToolClass(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        int selRow = sui.getListboxSelectedRow(params);
        switch (selRow)
        {
            case -1:
                return SCRIPT_CONTINUE;
            default:
                if ((player != null) && (player != obj_id.NULL_ID))
                {
                    if (!resource.setToolClass(self, selRow))
                    {
                        sendSystemMessageTestingOnly(player, "Heroics Failed?");
                    }
                    else
                    {
                        sendSystemMessageTestingOnly(player, "Heroics!");
                    }
                }
                break;
        }
        return SCRIPT_CONTINUE;
    }
}
