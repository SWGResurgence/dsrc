package script.event.halloween;
import script.*;
import script.library.*;

public class treat_thief extends script.base_script {
    public treat_thief() {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnCombatEntered(obj_id self, obj_id attacker, obj_id[] attackers, dictionary params) throws InterruptedException
    {
        broadcast(self, "I AM HEREEEEE!");
        messageTo(self, "doAttackPrimary", params, 120.0f, true);
        messageTo(self, "doAttackSecondary", params, 360.0f, true);
        return SCRIPT_CONTINUE;
    }
    public void doAttackPrimary(obj_id self, obj_id[] target, int damageModifier)
    {
        //do some cool attacks here
    }
    public void doAttackSecondary(obj_id self, obj_id[] targets)
    {
        //(possibly) do  some cool attacks here
    }
    public void handleLoot(obj_id self, obj_id player) throws InterruptedException {
        static_item.createNewItemFunction("test_item", getAppearanceInventory(player));
        broadcast(player, "You have taken down the Treat Thief!");
        broadcast(player, "Return to Shackel Emup on Tatooine.");

    }
}
