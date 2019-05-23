/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Gender;
import Entities.User;
import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
//import javax.mail.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import rest.file.uploader.tn.FileUploader;
//import com.sun.mail.smtp.SMTPTransport;
//import java.util.Date;
////import java.util.Properties;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author asus
 */
public class InscriptionView {
    String str ; 
    Form f= new Form();
    TextField tfirstname;
    TextField tlastname;
    TextField tpseudo;
    TextField tphone;
    TextField tabout;
    TextField tchildrennbr;
    TextField tminage;
    TextField tmaxage;
    RadioButton maleRadio;
    RadioButton femaleRadio;
    ButtonGroup group;
    RadioButton smokerRadio;
    RadioButton NoSmokerRadio;
    ButtonGroup group2;
    RadioButton DrinkerRadio;
    RadioButton NoDrinkerRadio;
    ButtonGroup group3;
    Picker birthdate;
    Picker body;
    Picker religion;
    Picker importance;
    Picker maritalstatus;
    Button btnajout;
    private TextField aboutField;
    private boolean selected;
    private Resources theme;
        private User member;
    private String path ; 
    private Button btnajout1 = new Button("confirm image") ; 
        private Button ch = new Button("add image") ; 


//    Picker body;
    public InscriptionView() {
                      theme = UIManager.initFirstTheme("/theme");

        f = new Form("Inscription", BoxLayout.y());
                f.getToolbar().addCommandToLeftBar("back", theme.getImage("back-command.png"), (e) -> {
            new Login(this.getClass()).Login.show();
        });
           ImageViewer img = new ImageViewer(Image.createImage(10, 10));
        img.setImage(theme.getImage("logo.png"));
        tfirstname = new TextField();
        tfirstname.setHint("Firstname");
        Label labelname = new Label();
        labelname.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelname.getAllStyles().setFgColor(0xFF0000);
        tlastname = new TextField();
        tlastname.setHint("Lastname");
        Label labelLast = new Label();
        labelLast.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelLast.getAllStyles().setFgColor(0xFF0000);
        TextField email = new TextField("", "E-Mail", 30, TextField.EMAILADDR);
        Label labelemail = new Label();
        labelemail.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelemail.getAllStyles().setFgColor(0xFF0000);
        Label labelemailV = new Label();
        labelemailV.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelemailV.getAllStyles().setFgColor(0xFF0000);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label labelPassword = new Label();
        labelPassword.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelPassword.getAllStyles().setFgColor(0xFF0000);
        TextField confirmPassword = new TextField("", "Confirm Password", 30, TextField.PASSWORD);
        Label labelRPassword = new Label();
        labelRPassword.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelRPassword.getAllStyles().setFgColor(0xFF0000);
        tpseudo = new TextField();
        tpseudo.setHint("pseudo");
        Label labelPseudo = new Label();
        labelPseudo.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelPseudo.getAllStyles().setFgColor(0xFF0000);
        tphone = new TextField();
        tphone.setHint("phone");
        Label labelPhone = new Label();
        labelPhone.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelPhone.getAllStyles().setFgColor(0xFF0000);
        tabout = new TextField();
        tabout.setHint("about");
        Label labelAbout = new Label();
        labelAbout.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        labelAbout.getAllStyles().setFgColor(0xFF0000);
        Container genderContainer = new Container(BoxLayout.x());
        Label lGender = new Label("Gender :");
        lGender.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        lGender.getAllStyles().setFgColor(0x09345E);
        Label male = new Label("Male");
        male.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        Label female = new Label("Female");
        female.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        maleRadio = new RadioButton();   
        femaleRadio = new RadioButton();
                male.addPointerPressedListener(e->{
            maleRadio.setSelected(true) ; 
            femaleRadio.setSelected(false);
                }
        );
                                female.addPointerPressedListener(e->{
            maleRadio.setSelected(false) ; 
            femaleRadio.setSelected(true);
                }
        );
        Label Vgender = new Label();
        Vgender.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Vgender.getAllStyles().setFgColor(0xFF0000);
        genderContainer.add(lGender) ;
                genderContainer.add(male) ;
                genderContainer.add(maleRadio);
                genderContainer.add(female);
                genderContainer.add(femaleRadio);
        group = new ButtonGroup(maleRadio, femaleRadio);
        tminage = new TextField();
        tminage.setHint("Prefered min_age");
        Label minageLabel = new Label();
        minageLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        minageLabel.getAllStyles().setFgColor(0xFF0000);
        tmaxage = new TextField();
        tmaxage.setHint("Prefered max_age");
        Label maxageLabel = new Label();
        maxageLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        maxageLabel.getAllStyles().setFgColor(0xFF0000);
        Container cntBirth = new Container(BoxLayout.x());
        birthdate = new Picker();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(this.birthdate.getDate());
        Label birthText = new Label("Birth Date :");
        cntBirth.add(birthText).add(birthdate);
        birthText.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        birthText.getAllStyles().setFgColor(0x09345E);
        Label sl = new Label("Email address or username already used !") ;
        sl.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        sl.getAllStyles().setFgColor(0xFF0000);
        sl.setVisible(false);

        //Address
        final DefaultListModel<String> options = new DefaultListModel<>();
      



        btnajout = new Button("Register");
        ImageViewer img1 = new ImageViewer() ; 
        EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("logo.png"), false);
        //System.out.println(member.getImage());
            //URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"","http://localhost/ImagesServeur/uploads/"+member.getImage().trim());
            img.setImage(enc);
            ch.addActionListener((evt) -> {
                 Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        Image img = null;
                        //userService.LoggedUser.setImage(path.substring(7));
                        //System.out.println(userService.LoggedUser.getImage());
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);

                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            System.out.println("hhhh");
                        }
                    }
                }


            }, Display.GALLERY_IMAGE);
        });
        btnajout1.addActionListener((e) -> {

            System.out.println("owel el add");
            FileUploader fc = new FileUploader("localhost/ImagesServeur");

            try {
            str= fc.upload(path.substring(7));
            System.out.println(str);
            URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"","http://localhost/ImagesServeur/uploads/"+str.trim());
            img.setImage(urlImage);
                //System.out.println(member.getImage());
             //userService us = new userService()  ; 
             //us.editUser(member) ; 
            } catch (Exception ex) {
                System.out.println("hh");
                ex.printStackTrace();
            }
            
     
            
            
            
            });
        
           // coverImg = new Label(urlImage);
        f.add(img) ; 
        f.add(ch) ; 
        f.add(btnajout1) ;         
        f.add(tfirstname);
        f.add(labelname);
        f.add(tlastname);
        f.add(labelLast);
        f.add(tpseudo);
        f.add(labelPseudo);
        f.add(email);
        f.add(labelemail);
        f.add(labelemailV);
        f.add(tphone);
        f.add(labelPhone);
        f.add(password);
        f.add(labelPassword);
        f.add(confirmPassword);
        f.add(labelRPassword);
        f.add(genderContainer);
        f.add(Vgender);
        maleRadio.isSelected();
        f.add(cntBirth);
        f.add(sl) ;   
        f.add(btnajout);

        Validator v = new Validator();
        v.addConstraint(email, RegexConstraint.validEmail("Unvalid email!"));
        v.addSubmitButtons(btnajout);

        btnajout.addActionListener((l) -> {
            boolean valid = true;
                        userService us = new userService() ; 

            if (tfirstname.getText().equals("")) {
                labelname.setText("Field is empty !");
                labelname.setVisible(true);
                valid = false;
            } else {
                labelname.setText("");

            }
            if (tlastname.getText().equals("")) {
                labelLast.setText("Field is empty !");
                labelLast.setVisible(true);
                valid = false;
            } else {
                labelLast.setText("");

            }
            if (email.getText().equals("")) {
                labelemail.setText("Field is empty !");
                labelemail.setVisible(true);
                valid = false;
            } else {
                labelemail.setText("");

            }
            if (!email.getText().equals("") && !email.getText().contains("@") && !email.getText().contains(".")) {
                labelemailV.setText("Mail not valid !");
                labelemailV.setVisible(true);
                valid = false;
            } else {
                labelemailV.setText("");

            }

            if (tpseudo.getText().equals("")) {
                labelPseudo.setText("Field is empty !");
                labelPseudo.setVisible(true);
                valid = false;
            } else {
                labelPseudo.setText("");

            }

            if (tphone.getText().equals("")) {
                labelPhone.setText("Field is empty !");
                labelPhone.setVisible(true);
                valid = false;
            }
            if (tphone.getText().length()!=8)
            {
              labelPhone.setText("phone number must be 8 numbers !");
                labelPhone.setVisible(true);
                valid = false;
            }
            else {
                labelPhone.setText("");

            }
          

           
            
            if (password.getText().equals("")) {
                labelPassword.setText("Field is empty !");
                labelPassword.setVisible(true);
                valid = false;
            } else {
                labelPassword.setText("");
            }

            if (confirmPassword.getText().equals("")) {
                labelRPassword.setText("Field is empty !");
                labelRPassword.setVisible(true);
                valid = false;
            }

            if (!password.getText().equals(confirmPassword.getText())) {
                labelRPassword.setText("Password doesn't match !");
                labelRPassword.setVisible(true);
                valid = false;
            } else if (password.getText().equals(confirmPassword.getText()) && !password.getText().equals("")) {
                labelRPassword.setText("Password match !");
                labelRPassword.getAllStyles().setFgColor(0x388D38);
                labelRPassword.setVisible(true);
            }
           
            if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
                Vgender.setText("You should select a choice !");
                Vgender.setVisible(true);
                valid = false;
            } else {
                Vgender.setText("");
            }
            if (!valid) {
                return;
            }
            Gender gender ; 
            if(maleRadio.isSelected())
                gender=Gender.Male ; 
            else 
                gender = Gender.Female;  

            if (us.usernameexist(tpseudo.getText()))
            {
                sl.setText("username already been used");
                sl.setVisible(true);
            }
            else if (us.emailexist(email.getText()))
            {
               sl.setText("email already been used");
               sl.setVisible(true);
            }
            else
            {
             User u = new User(tfirstname.getText(), tlastname.getText(), email.getText(), password.getText()
             , birthdate.getDate(),gender ,tphone.getText(),9628841,tpseudo.getText(),str ) ; 
            int response = us.Inscription(u);
            Login log = new Login(this.getClass()) ; 
            log.show(); 
            }
            

           

         
//            Mail sm = new Mail(u1.getEmail(), " Confirmation d'inscription ", " Bonjour " + u1.getFirstname() + "Felicitations! Vous etes maintenant inscrit à MySoulMate");
//                            try {
//               
//                Properties props = new Properties();
//                props.put("mail.transport.protocol", "smtp");
//                props.put("mail.smtps.host", "smtp.gmail.com");
//                props.put("mail.smtps.auth", "true");
//                Session session = Session.getDefaultInstance(null, null);
//                
//                MimeMessage msg = new MimeMessage(session);
//                
//                msg.setFrom(new InternetAddress("SocialPro <my_email@myDomain.com>"));
//                msg.setRecipients(Message.RecipientType.TO, email.getText());
//                msg.setSubject("Bienvenue sur SocialPro ");
//                msg.setSentDate(new Date(System.currentTimeMillis()));
//                
//                String txt = "Bonjour,\n "+tpseudo.getText()+"\n Cordialement";
//                
//                msg.setText(txt);
//                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
//                st.connect("smtp.gmail.com","benjaziaachref@gmail.com","achref07");
//                st.sendMessage(msg, msg.getAllRecipients());
//                System.out.println("ServerResponse : " + st.getLastServerResponse());
//                 
//            } catch (MessagingException ex) {
//            }

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
