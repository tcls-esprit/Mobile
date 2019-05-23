/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.MyApplication.theme;

/**
 *
 * @author asus
 */            

public class qrcodeview {
private Resources theme;
    public Form f = new Form("Cité de la culture",BoxLayout.x());

    public qrcodeview() {
                               f.getToolbar().addMaterialCommandToLeftBar("Mytickets",FontImage.MATERIAL_EXIT_TO_APP , (e) -> {
                new Tickets(this.f, userService.LoggedUser).getF().show();
            });
        theme = UIManager.initFirstTheme("/theme");

         ImageViewer img = new ImageViewer(Image.createImage(10, 10));
        img.setImage(theme.getImage("QRCode.png"));
        f.add(img);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
      

}
