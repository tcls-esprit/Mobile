package GUI;

import Entities.Gender;
import Entities.User;
import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rest.file.uploader.tn.FileUploader;

public class EditFormView {
    private Form parentForm;
    private Form form;
    private User member;
    private String path ; 
    private Button btnajout = new Button("ajout") ; 
        private Button ch = new Button("change") ; 

                private Resources theme;
       CheckBox ck = new CheckBox("keep me connected") ; 


    private Picker birthdateField;
    private TextField usernameField;
    private TextField firstnameField;
    private TextField lastnameField;
    private RadioButton maleField;
    private RadioButton femaleField;
    private TextField heightField;
    private TextField childrenField;
    private CheckBox smokerField;
    private CheckBox drinkerField;
    private TextField minAgeField;
    private TextField maxAgeField;
    private TextField phoneField;
    private TextField emailField;
    private AutoCompleteTextField addressField;
    private TextField aboutField;
    
    private boolean selected = true;
    public EditFormView(Form parentForm, User member){
                                    theme = UIManager.initFirstTheme("/theme");

        Dialog i = new InfiniteProgress().showInifiniteBlocking();
        this.member = member;
        this.parentForm = parentForm;
        form = new Form("Edit", new BorderLayout());
        
        birthdateField = new Picker();
        birthdateField.setDate(member.getBirthDate());
        usernameField = new TextField(member.getUsername(), "Username", 60, TextField.ANY);
        firstnameField = new TextField(member.getNom(), "Firstname", 60, TextField.ANY);
        lastnameField = new TextField(member.getPrenom(), "Firstname", 60, TextField.ANY);
        maleField = new RadioButton("Male");
        femaleField = new RadioButton("Female");
        new ButtonGroup(maleField, femaleField);
        if(member.getGender().name()==Gender.Male.name()) maleField.setSelected(true); else femaleField.setSelected(true);
      
        emailField = new TextField(member.getEmail(), "Email", 60, TextField.EMAILADDR);
        phoneField = new TextField(member.getNumero(), "Firstname", 60, TextField.ANY) ; 
        final DefaultListModel<String> options = new DefaultListModel<>();
       

        
        buildContainer();
        
        form.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), (e) -> {
            parentForm.showBack();
        });
        i.dispose();
    }
    
    private void buildContainer(){
        Container c = new Container(BoxLayout.y());
        c.setScrollableY(true);
        c.getAllStyles().setMarginBottom(20);
               ImageViewer img = new ImageViewer() ; 
        EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("logo.png"), false);
        System.out.println(member.getImage());
            URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"","http://localhost/ImagesServeur/uploads/"+member.getImage().trim());
            img.setImage(urlImage);
            ch.addActionListener((evt) -> {
                 Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        Image img = null;
                        userService.LoggedUser.setImage(path.substring(7));
                        System.out.println(userService.LoggedUser.getImage());
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
        btnajout.addActionListener((e) -> {

            System.out.println("owel el add");
            FileUploader fc = new FileUploader("localhost/ImagesServeur");

            try {
             member.setImage(fc.upload(path.substring(7)));
                System.out.println(member.getImage());
             userService us = new userService()  ; 
             us.editUser(member) ; 
            } catch (Exception ex) {
                System.out.println("hh");
                ex.printStackTrace();
            }
            
     
            
            
            
            });
        
           // coverImg = new Label(urlImage);
        c.add(img) ; 
        c.add(ch) ; 
        c.add(btnajout) ; 
        c.add(new Label("Email: ")).add(emailField);
        c.add(new Label("Username: ")).add(usernameField);
        c.add(new Label("Firstname: ")).add(firstnameField);
        c.add(new Label("Lastname: ")).add(lastnameField);
        c.add(new Label("Birthdate: ")).add(birthdateField);
        c.add(new Label("Phone Number: ")).add(phoneField);
        Container genderContainer = new Container(BoxLayout.x());
        c.add(new Label("Gender: "));
        genderContainer.add(maleField).add(femaleField);
        c.add(genderContainer);
        
        Button submitButton = new Button("Confirm");
        submitButton.getUnselectedStyle().setMarginTop(5);
        submitButton.addActionListener((ActionEvent e) -> {
            if(checkFormValidity()){
                Dialog i = new InfiniteProgress().showInifiniteBlocking();
                member.setBirthDate(birthdateField.getDate());
                member.setUsername(usernameField.getText());
                member.setNom(firstnameField.getText());
                member.setPrenom(lastnameField.getText());
                if(maleField.isSelected())
                { member.setGender(Gender.Male);}
                else 
                { member.setGender(Gender.Female);}
                member.setNumero("50725707");
                member.setEmail(emailField.getText());
                if (ck.isSelected())
                {
                    MyApplication.Connected = 1 ; 
        try {
            Database database = Database.openOrCreate("tcls");
            database.execute("create table if not exists members(id INTEGER, firstname text, lastname text,"
                    + "username text, gender text, birthday date"
                    +", email text,password text , numero text, co INTEGER);");
            String deleteQuery = "delete from members";
            database.execute(deleteQuery);
            String insertQuery = "insert into members values("+member.getUserId()+", '"+member.getNom()+"', '"+member.getPrenom()
                    +"', '"+member.getUsername()+"', '"+member.getGender().name()+"', '"+(new SimpleDateFormat("dd-MM-yyyy")).format(member.getBirthDate())+"', '"
                    +member.getEmail()+"', '"+member.getPassword()+"','"+member.getNumero()+"',"+MyApplication.Connected+",'"+member.getImage()+"')";
                 
            database.execute(insertQuery);
            System.out.println(member);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                }    
                else 
                {
                    MyApplication.Connected= 0 ; 
        try {
            Database database = Database.openOrCreate("tcls");
            database.execute("create table if not exists members(id INTEGER, firstname text, lastname text,"
                    + "username text, gender text, birthday date"
                    +", email text,password text , numero text, co INTEGER);");
            String deleteQuery = "delete from members";
            database.execute(deleteQuery);
            String insertQuery = "insert into members values("+member.getUserId()+", '"+member.getNom()+"', '"+member.getPrenom()
                    +"', '"+member.getUsername()+"', '"+member.getGender().name()+"', '"+(new SimpleDateFormat("dd-MM-yyyy")).format(member.getBirthDate())+"', '"
                    +member.getEmail()+"', '"+member.getPassword()+"','"+member.getNumero()+"',"+MyApplication.Connected+",'"+member.getImage()+"')";
                 
            database.execute(insertQuery);
            System.out.println(member);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                }
                userService us = new userService();
                us.editUser(member) ; 
                ProfileView.update();
                i.dispose();
                parentForm.showBack();
            }
        });
        
        Validator v = new Validator();
        v.addConstraint(emailField, RegexConstraint.validEmail("Unvalid email!"));
        v.addSubmitButtons(submitButton);
        c.add(ck) ; 
        c.add(submitButton);
        
        form.removeAll();
        form.add(BorderLayout.CENTER, c);
    }
    
    private boolean checkFormValidity(){
        User m = new User();
        userService us = new userService() ; 
        m.setBirthDate(birthdateField.getDate());
            if(usernameField.getText().isEmpty()){
            Dialog.show("Error", "Username is mandatory!", "Ok", null);
            return false;
        }else if(firstnameField.getText().isEmpty()){
            Dialog.show("Error", "Firstname is mandatory!", "Ok", null);
            return false;
        }else if(lastnameField.getText().isEmpty()){
            Dialog.show("Error", "Lastname is mandatory!", "Ok", null);
            return false;
        
        }else if(phoneField.getText().isEmpty()){
            Dialog.show("Error", "Phone number is mandatory!", "Ok", null);
            return false;
        }else if(phoneField.getText().length() != 8){
            Dialog.show("Error", "Phone number must contain exactly 8 digits!", "Ok", null);
                        return false;

        }else if ((us.usernameexist(usernameField.getText())&& !(usernameField.getText().equals(member.getUsername())))){
            Dialog.show("Error", "username already exists!", "Ok", null);
                        return false;

            }else if ((us.emailexist(emailField.getText())&&!(usernameField.getText().equals(member.getUsername())))){
            Dialog.show("Error", "email already used!", "Ok", null);
                        return false;

            }
        
        
        return true;
    }
    
    public Form getForm(){
        return form;
    }
    
    
    
}
