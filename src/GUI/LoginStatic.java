/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import GUI.toast;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static jdk.nashorn.internal.objects.NativeArray.map;
/**
 *
 * @author AQuel
 */
public abstract class LoginStatic {
    
   private Form f ;
 
  private  TextField login ;
  private   TextField passwd ;
    
    Button btnc ; 
    
    public LoginStatic()
    {
                    
         f = new Form("Cité De La Culture", new BoxLayout(BoxLayout.Y_AXIS));
         login = new TextField("","Votre Login");
          
         passwd = new TextField("","Votre Mot de passe",20,TextArea.PASSWORD);
         
         btnc = new Button("Connecter");
         
        f.add(login);
        
        f.add(passwd);
        f.add(btnc);
        f.show();
        btnc.addActionListener((ActionListener) (ActionEvent evt) -> 
        {
           if (getLogin().getText().equals("user") && getPasswd().getText().equals("user") )
             {
             ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Les Scénes Théatrales !");
            status.setProgress(0);
            status.setIcon(createIcon(FontImage.MATERIAL_WALLPAPER));
            status.show();
            
            for (int progress=0; progress <= 100; progress += 2 ) {
                Display.getInstance().invokeAndBlock(()->{Util.sleep(500);});
                status.setProgress(progress);
                status.show();
                if (progress == 100) {
                    status.setIcon(createIcon(FontImage.MATERIAL_DONE));
                    status.setProgress(-1);
                    status.setExpires(3000);
                    status.setMessage("Bienvenue !");
                    status.show();
               HomeTheatre ht = new HomeTheatre();
               ht.getFhome().show();
         }}}
         else 
         {
             Dialog d1 = new Dialog();    
              d1.show("Information", "Veuillez verifier votre login ou mot de passe","Annuler","OK");
         }
            
            
         });
                
    }
     
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getLogin() {
        return login;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public TextField getPasswd() {
        return passwd;
    }

    public void setPasswd(TextField passwd) {
        this.passwd = passwd;
    }

    
    protected abstract void showOtherForm(Resources res);
       private Image createIcon(char charcode) {
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
    
}
