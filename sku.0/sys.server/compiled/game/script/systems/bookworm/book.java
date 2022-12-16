package script.systems.bookworm;/*
@Filename: script.systems.bookworm.book
@Author: BubbaJoeX
@Purpose:
*/

import script.library.colors_hex;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;

public class book extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, unlocalizedString("Read"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int menu) throws InterruptedException
    {
        if (menu == menu_info_types.ITEM_USE)
        {
            openBook(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public int openBook(obj_id book, obj_id who) throws InterruptedException
    {
        int page = createSUIPage("/Script.editScript", book, who);
        setSUIProperty(page, "pageText.text", "Text", colors_hex.Default(getStringObjVar(book, "book.text")));
        setSUIProperty(page, "pageText.text", "Font", "starwarslogo_optimized_56");
        setSUIProperty(page, "bg.caption.text", "LocalText", getStringObjVar(book, "book.title"));
        subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "nextPage");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "pageText.text", "LocalText");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "outputPage.text", "LocalText");
        setSUIAssociatedObject(page, book);
        boolean showResult = showSUIPage(page);
        flushSUIPage(page);
        String trackPage = "scriptFileName" + page;
        if (!utils.hasScriptVar(who, "pageId"))
        {
            utils.setScriptVar(book, "pageId", page);
        }

        return SCRIPT_OVERRIDE;
    }
}
