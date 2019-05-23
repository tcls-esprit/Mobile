package GUI;

import Entities.Gender;
import Entities.User;
import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.Random;

public abstract class ProfileView {
    private Form form;
    private Form parentForm;
    private User member;
            private Resources theme;

    private static ProfileView instance;
    
    public static void update(){
        if(instance != null)
            instance.updateView();
    }
    private Image imgg;
    
    private void updateView(){
        Dialog i = new InfiniteProgress().showInifiniteBlocking();
        member = userService.LoggedUser ; 
        buildContainer();
        form.revalidate();
        i.dispose();
    }
    
    public ProfileView(Form parentForm){
                        theme = UIManager.initFirstTheme("/theme");

        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
        instance = this;
        this.parentForm = parentForm;
        form = new Form("Profile", new BorderLayout());
        member = userService.LoggedUser ; 
        
        if(member == null){
            getMemberFromLocal();
        }else{
            populateBd();
           
        }
         form.getToolbar().addMaterialCommandToRightSideMenu("Edit", FontImage.MATERIAL_EDIT, (e) -> {
                new EditFormView(this.form, member).getForm().show();
            });
                        form.getToolbar().addMaterialCommandToRightSideMenu("Mytickets",FontImage.MATERIAL_MOVIE , (e) -> {
                new Tickets(this.form, member).getF().show();
            });
                    /*    form.getToolbar().addMaterialCommandToRightSideMenu("Calendar",FontImage.MATERIAL_TIMER, (e) -> {
                new PhotosListView(this.form).getForm().show();
            });*/
           form.getToolbar().addMaterialCommandToRightSideMenu("ChangePassword",FontImage.MATERIAL_KEYBOARD_CAPSLOCK, (e) -> {
                new Changepasssword().getF().show();
            });
           form.getToolbar().addMaterialCommandToRightSideMenu("Store",FontImage.MATERIAL_SHOPPING_CART, (e) -> {
                StoreHome lo = new StoreHome(theme);
                lo.show();
            });
            form.getToolbar().addMaterialCommandToRightSideMenu("films",FontImage.MATERIAL_MOVIE, (e) -> {
                Start lo = new Start();
                lo.getF0().show();
            });
            form.getToolbar().addMaterialCommandToRightSideMenu("evenements",FontImage.MATERIAL_EVENT, (e) -> {
                HomeEvent lo = new HomeEvent(theme);
                lo.getf().show();
            });
                form.getToolbar().addMaterialCommandToRightSideMenu("musee",FontImage.MATERIAL_ART_TRACK, (e) -> {
                    HomeForm lo = new HomeForm();
                lo.getF().show();
            });
                       form.getToolbar().addMaterialCommandToRightSideMenu("Theatre",FontImage.MATERIAL_WALLPAPER, (e) -> {
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
                           HomeTheatre lo = new HomeTheatre();
                lo.getFhome().show();
            }}});
        form.getToolbar().addCommandToLeftBar("logout", theme.getImage("back-command.png"), (e) -> {
            parentForm.showBack();
        });

        buildContainer();
        ip.dispose();
    }
    
    private void populateBd(){
        try {
            Database database = Database.openOrCreate("tcls");
            database.execute("create table if not exists members(id INTEGER, firstname text, lastname text,"
                    + "username text, gender text, birthday date"
                    +", email text,password text , numero text, co INTEGER,image text);");
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
    
    private void getMemberFromLocal(){
        try {
            Database database = Database.openOrCreate("tcls");
            Cursor c = database.executeQuery("select * from members");
            c.next();
            Row r = c.getRow();
            member = new User();
            member.setBirthDate((new SimpleDateFormat("dd-MM-yyyy").parse(r.getString(5))));
            member.setUserId(r.getInteger(0));
            member.setUsername(r.getString(3));
            member.setNom(r.getString(1));
            member.setPrenom(r.getString(2));
            member.setGender(Gender.valueOf(r.getString(4)));
            member.setEmail(r.getString(6));
            member.setPassword((r.getString(7))); 
            member.setNumero(r.getString(8));
            member.setCin(9628841);
            member.setImage(r.getString(10));
            System.out.println(member);
            userService.LoggedUser = member ; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void buildContainer(){
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginBottom(20);
        
        //Cover Picture
        Container coverContainer = new Container();
        Button bCover = new Button();
        coverContainer.setLeadComponent(bCover);
      
        
        c.add(coverContainer);
        c.add(buildTopProfileInfo());
        c.add(buildProfileInfo());
        
        c.setScrollableY(true);
        form.add(BorderLayout.CENTER, c);
    }
    
    public Form getContainer(){
        return this.form;
    }
    
    private Container buildProfileInfo(){
        Container c = new Container(BoxLayout.y());
        c.getAllStyles().setMarginLeft(40);
        c.getAllStyles().setPaddingTop(20);
        
        Label infoLabel = new Label("Info");
        infoLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
        Container mainContainer = new Container(BoxLayout.y());
       // mainContainer.getAllStyles().setMarginLeft(30);
       ImageViewer img = new ImageViewer() ; 
        EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("logo.png"), false);
        System.out.println(member.getImage());
            URLImage urlImage = URLImage.createToStorage(enc, (new Random()).nextInt()+"","http://localhost/ImagesServeur/uploads/"+member.getImage().trim());
           /* new ConnectionRequest().downloadImageToStorage("http://localhost/ImagesServeur/uploads/"+member.getImage().trim(),i->{
                
                imgg = i ;
                img.setImage(imgg);
                
            });*/
           
            img.setImage(urlImage);
            
           // coverImg = new Label(urlImage);
        /*    ConnectionRequest cr = new ConnectionRequest("http://localhost/ImagesServeur/uploads/"+member.getImage().trim(), false);
            cr.downloadImageToStorage("http://localhost/ImagesServeur/uploads/"+member.getImage().trim(), i -> {
                i.scale(1100, 850) ; 
                img.setImage(i);
            });*/
        mainContainer.add(img) ; 
        mainContainer.add(buildPairLabel("gender", member.getGender().name()));
        mainContainer.add(buildPairLabel("Birthdate", (new SimpleDateFormat("dd MMMM, yyy").format(member.getBirthDate()))));
        mainContainer.add(buildPairLabel("email", member.getEmail()));
        mainContainer.add(buildPairLabel("Nom", member.getNom()));
        mainContainer.add(buildPairLabel("prenom", member.getPrenom()));
        mainContainer.add(buildPairLabel("username", member.getUsername()));
        mainContainer.add(buildPairLabel("telephone", member.getNumero()));

        c.add(infoLabel);
        c.add(mainContainer);
        return c;
    }
    
    private Container buildPairLabel(String label, String value){
        Container c = new Container(BoxLayout.x());
        
        Label labelLabel = new Label(label+":");
        labelLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        
        Label labelValue = new Label(value);
        labelValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        
        c.add(labelLabel);
        c.add(labelValue);
        return c;
    }
    
    private Container buildTopProfileInfo(){
        //Profile Picture
        Label profileImg;
        Container profileContainer = new Container();
        Button bProfile = new Button();
        profileContainer.setLeadComponent(bProfile);
       
        
        
        
        Container profileSideContainer = new Container(BoxLayout.y());
        
        //Name
        Container nameAgeContainer = new Container(BoxLayout.x());
        nameAgeContainer.getAllStyles().setMarginLeft(5);
        
        Label nameLabel = new Label(member.getNom()+" "+member.getPrenom());
        nameLabel.getAllStyles().setPaddingTop(0);
        nameLabel.getAllStyles().setPaddingRight(0);
        nameLabel.getAllStyles().setPaddingBottom(0);
        nameLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        nameAgeContainer.add(nameLabel);
        //Age
        Label ageLabel = new Label("("+member.getEmail()+")");
        ageLabel.getAllStyles().setPaddingTop(0);
        ageLabel.getAllStyles().setPaddingBottom(0);
        ageLabel.getAllStyles().setMarginRight(0);
        ageLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        nameAgeContainer.add(ageLabel);
        
        profileSideContainer.add(nameAgeContainer);
        
        //Address
        
        //Since
       
        
        Container profileAddressContainer = new Container(BoxLayout.x());
        profileAddressContainer.add(profileContainer);
        profileAddressContainer.add(profileSideContainer);
        
        return profileAddressContainer;
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
