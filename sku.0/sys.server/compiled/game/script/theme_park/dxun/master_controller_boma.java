package script.theme_park.dxun;

import script.*;
import script.library.hue;
import script.library.colors;

import script.color;

public class master_controller_boma extends script.base_script {
    public int OnAttach(obj_id self) throws InterruptedException {
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", 23, 53, 11, 0);
        hue.setColor(self, "/private/index_color_1", colors.DXUNGREEN);
        return SCRIPT_CONTINUE;
    }
    public static void setPalcolorCustomVarClosestColor(obj_id target, String varPathName, color c) throws InterruptedException {
        if (varPathName.startsWith("/")) {
            varPathName = varPathName.substring(1);
        }
        setPalcolorCustomVarClosestColor(target, varPathName, c.getR(), c.getG(), c.getB(), c.getA());
    }
}
