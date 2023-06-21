package script.systems.bookworm;/*
@Filename: script.systems.bookworm.book
@Author: BubbaJoeX
@Purpose: Allows players to read/write books. Currently, the book is editable only by the owner.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.buff;
import script.library.sui;
import script.library.utils;

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
        mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Open Book"));
        if (getCrafter(self) == player) //only the creator of the book can edit it.
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU2, unlocalized("Name Book"));
            mi.addRootMenu(menu_info_types.SERVER_MENU3, unlocalized("Describe Book"));
        }
        if (isGod(player))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU4, unlocalized("[GodMode] Claim Book"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int menu) throws InterruptedException
    {
        if (menu == menu_info_types.ITEM_USE)
        {
            openBook(self, player);
        }
        if (menu == menu_info_types.SERVER_MENU2)
        {
            sui.inputbox(self, player, "Enter a name for your book.", "handleName");
        }
        if (menu == menu_info_types.SERVER_MENU3)
        {
            sui.inputbox(self, player, "Enter a description for your book.", "handleDescribe");
        }
        if (menu == menu_info_types.SERVER_MENU4)
        {
            setCrafter(self, player);
        }
        return SCRIPT_CONTINUE;
    }

    public int openBook(obj_id book, obj_id who) throws InterruptedException
    {
        int page = createSUIPage("/Script.editScript", book, who);
        setSUIProperty(page, "pageText.text", "Text", getStringObjVar(book, "book.text"));
        setSUIProperty(page, "pageText.text", "Font", "starwarslogo_optimized_56");
        if (getCrafter(book) == who) //only the creator of the book can edit it.
        {
            setSUIProperty(page, "pageText.text", "Editable", "True");
        }
        else
        {
            setSUIProperty(page, "pageText.text", "Editable", "False");
        }
        setSUIProperty(page, "pageText.text", "GetsInput", "True"); // allow copy and pasting.
        setSUIProperty(page, "outputPage.text", "Text", getStringObjVar(book, "book.title"));//title
        setSUIProperty(page, "btnOk", "Text", "Save");//save button
        if (getCrafter(book) == who) //only the creator of the book can edit it -- show View Book instead.
        {
            setSUIProperty(page, "btnOk", "Text", "Save");//save button
            setSUIProperty(page, "bg.caption.text", "LocalText", "Edit Book");
            subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "saveText");
        }
        else
        {
            setSUIProperty(page, "btnOk", "Text", "Close");//
            setSUIProperty(page, "bg.caption.text", "LocalText", "View Book");
            subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "readText");
        }

        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "pageText.text", "LocalText");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "outputPage.text", "LocalText");
        setSUIAssociatedObject(page, book);
        showSUIPage(page);
        flushSUIPage(page);
        if (!utils.hasScriptVar(who, "pageId"))
        {
            utils.setScriptVar(book, "pageId", page);
        }
        setObjVar(book, "bookPage", page);

        return SCRIPT_OVERRIDE;
    }

    public int saveText(obj_id self, dictionary params) throws InterruptedException
    {
        String bookText = params.getString("pageText.text.LocalText");
        obj_id player = sui.getPlayerId(params);
        if (bookText.length() < 2000) //max length for objvars fyi per elour
        {
            setObjVar(self, "book.text", bookText);
            broadcast(player, "You have modified this text within this book.");
        }
        else
        {
            broadcast(player, "The maximum word count for this book is 2000 characters.");
        }
        sui.closeSUI(player, getIntObjVar(self, "bookPage"));
        return SCRIPT_CONTINUE;
    }

    public int readText(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        if (!buff.hasBuff(player, "content_bookworm"))
        {
            buff.applyBuff(player, "content_bookworm");
            playClientEffectLoc(player, "clienteffect/of_scatter.cef", getLocation(player), 1.0f);
            broadcast(player, "You have read the contents of this book, and have gained the Bookworm buff.");
        }
        else
        {
            broadcast(player, "You had read the contents of this book.");
        }
        return SCRIPT_CONTINUE;
    }

    public int handleName(obj_id self, dictionary paramsDict) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(paramsDict);
        String bookTitle = sui.getInputBoxText(paramsDict);
        setName(self, bookTitle);
        setObjVar(self, "book.title", bookTitle);
        broadcast(player, "You have renamed this book to: " + bookTitle);
        return SCRIPT_CONTINUE;
    }

    public int handleDescribe(obj_id self, dictionary paramsDict) throws InterruptedException
    {
        String descInput = sui.getInputBoxText(paramsDict);
        if (descInput == null || descInput.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        string_id desc = new string_id(descInput);
        setDescriptionStringId(self, desc);
        setObjVar(self, "null_desc", descInput);
        if (!hasScript(self, "developer.bubbajoe.sync"))
        {
            attachScript(self, "developer.bubbajoe.sync");
        }
        return SCRIPT_CONTINUE;
    }

}
