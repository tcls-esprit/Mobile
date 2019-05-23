/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Random;
/**
 *
 * @author badis
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;



import java.io.IOException;



import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.JSONParser;
import com.codename1.io.Storage;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Random;

import java.io.IOException;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author asus
 */
public class Login {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */







    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";
     public static int MEMBER_ID = 0;
    public Form Login = new Form("Cité de la culture",BoxLayout.y());
    TextField loginField  ;
    TextField mdpField ;
    Container loginContainer;
    Button confirmerBtn;
    Button registerBtn;
    Button fbButton;
    public Form mySoulMate;
    private String url = "http://localhost/mysoulmate/web/app_dev.php/service/Login/";
    private ConnectionRequest connectionRequest;
    String login = "";
    String pw = "";
    String status = "";
    String appSecret = "15378d7426361fe464f5af2e08f780e3";
    String domain = "http://localhost";
    String appId = "212394559315715";
    Button qr ;
    Button msg ;
        private Resources theme;


    /**
     *
     * @throws IOException
     */
    public Login(Class fo) {
                theme = UIManager.initFirstTheme("/theme");

         ImageViewer img = new ImageViewer(Image.createImage(10, 10));
        img.setImage(theme.getImage("logo.png"));
        loginField = new TextField();
        mdpField = new TextField();
        loginField.setHint("UserName");
           
        mdpField.setHint("Password") ; 
        mdpField.setConstraint(TextField.PASSWORD);
        confirmerBtn = new Button("Connexion");
        registerBtn = new Button("Register");
        //fbButton = new Button("Facebook Login");
        //qr = new Button("qr");
        

        confirmerBtn.addActionListener(e -> {
                        userService us = new userService() ; 
                if (loginField.getText().equals(""))
                {Dialog.show("Wrong credentials", "must put the username", "OK",null);
                     return ; }
                if (mdpField.getText().equals(""))
                {Dialog.show("Error", "must put the password", "OK",null);
                    return ;}
               if (!us.usernameexist(loginField.getText()))
            {
                    Dialog.show("Wrong credentials", "username doesn't exist", "OK",null);
                    return ; 
            }
            
            if (us.Login(loginField.getText(), mdpField.getText()))
            { System.out.println("connected");  
                ProfileView pfv = new ProfileView(Login) {
                @Override
                protected void showOtherForm(Resources res) {
                    
                }
            } ; 
                pfv.getContainer().show() ; 
            }
            else 
                  
                Dialog.show("Wrong credentials", "wrong password", "OK",null);
        });
        registerBtn.addActionListener(e->{
            InscriptionView ins = new InscriptionView(); 
            ins.getF().show() ; 
                    
        });
        loginContainer = new Container(BoxLayout.y());
        Login.add(img);
        Login.add(loginContainer);
        Login.add(loginField);
        Login.add(mdpField);
        Login.add(confirmerBtn);
        Login.add(registerBtn);
        if (fo.equals(Changepasssword.class))
        Login.add(new Label("your paassword have been changed")) ; 
//        loginContainer.add(fbButton);
    //    loginContainer.add(msg);
        

    }

    public void show() {
        Login.show();
    }

}

