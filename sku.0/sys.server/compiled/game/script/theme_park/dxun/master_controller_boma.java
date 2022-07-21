package script.master_controller_boma;

import script.*;
import script.library.hue;

public class master_controller_boma extends script.base_script {
    public int OnAttach(obj_id self) throws InterruptedException {
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", 23, 53, 11, 0);
        hue.setColor(self, [1/2], color);
        return SCRIPT_CONTINUE;
    }
}
