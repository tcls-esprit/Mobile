/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;



/**
 *
 * @author Bilel
 */
public class HomeForm {
    private Resources theme;
    Form f;
    Label text;
    Label text1;
    Label text2;
    ImageViewer img;
    Button btnres,btnguide;
    public HomeForm(){
        f = new Form("Bienvenue");
        text= new Label("Bienvenue au musée de");
        text.getAllStyles().setFgColor(0xff000);
        text1=new Label("la cité de la culture.");
        text1.getAllStyles().setFgColor(0xff000);
        text2=new Label("Que voulez-vous faire ?");
        text2.getAllStyles().setFgColor(0xff000);
        theme = UIManager.initFirstTheme("/theme");
        img = new ImageViewer();
        img.setImage(theme.getImage("logo.png"));
        btnres = new Button("Reserver votre visite");
        btnguide=new Button("Consulter la liste des guides");
        f.add(img);
        f.add(text);
        f.add(text1);
        f.add(text2);
        f.add(btnres);
        f.add(btnguide);
        f.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
               ProfileView lo = new ProfileView(f) {
                @Override
                protected void showOtherForm(Resources res) {
                }
            } ; 
                    lo.getContainer().show();
                }
        });
            f.getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_HOME,  new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Login l = new Login(this.getClass());
                l.show();
                
            }
        });
        btnguide.addActionListener((e)->{
        AffichageGuide a=new AffichageGuide();
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
