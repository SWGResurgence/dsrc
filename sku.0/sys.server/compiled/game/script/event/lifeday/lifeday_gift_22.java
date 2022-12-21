/*
@Purpose: Gift for Lifeday 2022 script.

@Author: BubbaJoe
@Purpose: Creates gifts for Lifeday 2022.
 */

package script.event.lifeday;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class lifeday_gift_22 extends script.base_script
{
    public static final String[] GOODIES =
            {
                    "item_vet_reward_resource_fabricator",
                    "item_vet_reward_token_stipend_scroll_01_01",
                    "item_tcg_loot_reward_series8_yodas_soup",
                    "item_event_dance_party_device_03_01",
                    "item_event_air_cake_01_02",
                    "item_event_energy_drink_01_02",
                    "weapon_publish_gift_27_04_01"
            };

    public lifeday_gift_22()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Life Day Gift");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mainMenu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Unwrap Gift"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id pInv = utils.getInventoryContainer(player);
            int maxItems = GOODIES.length;
            if (getVolumeFree(pInv) < maxItems)
            {
                sendSystemMessage(player, new string_id("Please make space in your inventory and try again."));
                return SCRIPT_CONTINUE;
            }
            createGoodies(pInv);
            grantRandomItemsByFlag(player, pInv, "lifeday");
            playClientEffectObj(player, "clienteffect/lifeday_gift.cef", player, "");
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }

    private void createGoodies(obj_id pInv) throws InterruptedException
    {
        for (int i = 0; i < GOODIES.length; i++)
        {
            obj_id individualGoodie = static_item.createNewItemFunction(GOODIES[i], pInv);
            setDescriptionStringId(individualGoodie, new string_id("This item was a personal gift from Saun Dann"));
            setName(individualGoodie, getStaticItemName(individualGoodie) + " (Lifeday 2022)");
            attachScript(individualGoodie, "item.special.nomove");
            setObjVar(individualGoodie, "noTrade", true);
        }
    }

    private void grantRandomItemsByFlag(obj_id player, obj_id container, String flag) throws InterruptedException
    {
        String ITEM_TABLE = "datatables/item/master_item/master_item.iff";
        String ITEM_TABLE_COLUMN = "name";
        String[] items = dataTableGetStringColumnNoDefaults(ITEM_TABLE, ITEM_TABLE_COLUMN);
        int bagLimit = 0;
        for (String item : items)
        {
            if (item.contains(flag))
            {
                static_item.createNewItemFunction(item, container);
                bagLimit++;
            }
            else
            {
                continue;
            }
            broadcast(player, "You have received an additional " + bagLimit + " gifts!");
        }
    }

}
