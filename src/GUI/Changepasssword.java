/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.userService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;


/**
 *
 * @author asus
 */
public class Changepasssword {
  
      Form f;
    TextField old;
    TextField pwd;
    TextField pwd1;
    Button btnajout,btnaff;

    public Changepasssword() {
       
        
                Label sucsess = new Label();
        sucsess.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        sucsess.getAllStyles().setFgColor(0xFF0000);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label labelold = new Label();
        labelold.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelold.getAllStyles().setFgColor(0xFF0000);
        Label labelpwd = new Label();
        labelpwd.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelpwd.getAllStyles().setFgColor(0xFF0000);
        Label labelpwd1 = new Label();
        labelpwd1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelpwd1.getAllStyles().setFgColor(0xFF0000);
        f = new Form("Change password");
        old = new TextField("","ancien mot de passe");
        old.setConstraint(TextField.PASSWORD);
        pwd = new TextField("","nouveau mot de passe");
        pwd.setConstraint(TextField.PASSWORD);
        pwd1 = new TextField("","confirmer le mot de passe");
        pwd1.setConstraint(TextField.PASSWORD);
        btnajout = new Button("confirmer");
        btnaff=new Button("profile");
        f.add(old);
        f.add(labelold) ; ;
        f.add(pwd);
        f.add(labelpwd); 
        f.add(pwd1);
        f.add(labelpwd1) ; 
        f.add(btnajout);
        f.add(btnaff);
        f.add(sucsess) ; 
        btnajout.addActionListener((e) -> {
            userService ser = new userService();
              boolean valid = true ; 
            if (old.getText().equals("")) {
                Dialog.show("empty password field", "try again", "retry", null) ; 
                valid = false ; 
            } else if(!ser.Login(userService.LoggedUser.getUsername(), old.getText())) {
                Dialog.show("wrong password", "try again", "retry", null) ; 
                valid = false ; 
            }
            else 
            {labelold.setText("");}

            if (pwd.getText().equals("")) {
                Dialog.show("empty password field", "try again", "retry", null) ; 
                valid = false ; 
            }
            if (pwd1.getText().equals("")) {
                Dialog.show("empty password field", "try again", "retry", null) ; 
                valid = false ;
            }
            if (!pwd.getText().equals(pwd1.getText())) {
                Dialog.show("Passwords don't match", "try again", "retry", null) ; 
                valid = false ; 
            } else if (pwd.getText().equals(pwd1.getText()) && !pwd.getText().equals("")&& valid) {
                labelpwd1.setText("Password match !");
                labelpwd1.getAllStyles().setFgColor(0x388D38);
                labelpwd1.setVisible(true);
                valid = true ; 
            }
            if(!valid)
                return ; 
             else
            {    System.out.println("hhhh");
                ser.newpassword(pwd.getText());
                sucsess.setText("password changed") ;
                sucsess.setVisible(true);
                UITimer.timer(2000, false, new Runnable() {
                @Override
                public void run() {
                   new Login( Changepasssword.class).Login.show();
                }
            } ) ; 
                
            }
            
            

        });
        btnaff.addActionListener((e)->{
        ProfileView a= new ProfileView(new Login(this.getClass()).Login) {
            @Override
            protected void showOtherForm(Resources res) {
            }
        };
        a.getContainer().show(); 
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
