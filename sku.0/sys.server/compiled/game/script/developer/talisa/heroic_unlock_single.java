package script.developer.talisa;

import script.*;
import script.library.instance;
import script.library.sui;
import script.library.utils;

import java.util.ArrayList;

public class heroic_unlock_single extends script.base_script
{
    public static final string_id SID = new string_id("sui", "use");

    // Localized String for "use" taken from "sui.stf"

    public heroic_unlock_single()
    {
    }

    // Server Call to obtain menu

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID);
        broadcast(player, "Select which Heroic Instance that you wish to permenantly unlock. You can only choose one.");
        return SCRIPT_CONTINUE;
    }

    // Server Call When Menu Item is Selected

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            use(self, player);
            broadcast(player, "Are you sure that you wish to unlock this one?");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    // Server Call for Selected Menu Operation

    public void use(obj_id self, obj_id player) throws InterruptedException
    {
        String[] instanceFlags = dataTableGetStringColumn(instance.INSTANCE_DATATABLE, "key_required");
        ArrayList<String> unflaggedInstances = new ArrayList<String>();
        ArrayList<prose_package> instanceNames = new ArrayList<prose_package>();
        cleanScriptVars(player);

        for (String instanceKey : instanceFlags)
        {
            if (!instanceKey.equals("none") && !instance.isFlaggedForInstance(player, instanceKey))
            {
                unflaggedInstances.add(instanceKey);
                prose_package pp = new prose_package();
                pp.stringId = new string_id("instance", instanceKey);
                pp.actor.set(player);
                instanceNames.add(pp);
            }
        }

        if (unflaggedInstances.size() > 0)
        {
            utils.setScriptVar(player, "heroic_unlock_single.unflaggedInstances", unflaggedInstances.toArray(new String[unflaggedInstances.size()]));
            prose_package[] prose = instanceNames.toArray(new prose_package[instanceNames.size()]);
            sui.listbox(self, player, "Select Heroic Instance", sui.OK_CANCEL,
                    "Available Instances",
                    prose,
                    "handleHeroicSelect", true, false);

        }
        else
        {
            broadcast(player, "No Instancing To Flag");
        }
    }

    public int handleHeroicSelect(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null || params.isEmpty())
        {
            return SCRIPT_CONTINUE;
        }

        obj_id player = sui.getPlayerId(params);
        int btn = sui.getIntButtonPressed(params);
        int idx = sui.getListboxSelectedRow(params);

        if (btn == sui.BP_CANCEL)
        {
            cleanScriptVars(player);
            return SCRIPT_CONTINUE;
        }

        if (utils.hasScriptVar(player, "heroic_unlock_single.unflaggedInstances"))
        {
            String[] unflaggedInstances = utils.getStringArrayScriptVar(player, "heroic_unlock_single.unflaggedInstances");
            if (idx == -1 || idx > unflaggedInstances.length)
            {
                cleanScriptVars(player);
                return SCRIPT_CONTINUE;
            }
            String instanceToFlag = unflaggedInstances[idx];
            instance.flagPlayerForInstance(player, instanceToFlag);
            cleanScriptVars(player);
            broadcast(player, "The Heroic Instance that you have choosen, has been unlocked.");
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }

    public void cleanScriptVars(obj_id player) throws InterruptedException
    {
        utils.removeScriptVarTree(player, "heroic_unlock_single");
    }
}
