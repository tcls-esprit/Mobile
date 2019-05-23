package GUI;

import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import rest.file.uploader.tn.FileUploader;

public class PhotosListView {
    private Form parentForm;
    private Form form = new Form(BoxLayout.y());
    private Container mainContainer;
    private Button imgBtn ; 
    private Button btnajout ; 
    private String path ; 
    private Image img ; 
    //private List<Photo> photos;
    
    private static PhotosListView instance;
    

    
    public PhotosListView(Form parentForm){
        Dialog i = new InfiniteProgress().showInifiniteBlocking();
        this.parentForm = parentForm;
        imgBtn = new Button("img") ; 
           form.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), (e) -> {
            parentForm.showBack();
        });
        btnajout = new Button("ajoiut") ; 
         imgBtn.addActionListener(e -> {
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
                fc.upload(path.substring(7));
            } catch (Exception ex) {
                System.out.println("hh");
                ex.printStackTrace();
            }
            
     
            
            
            }) ; 
    form.add(imgBtn) ; 
    form.add(btnajout) ; 
    }
    
    public Form getForm(){
        return this.form;
    }
    
    public Form getParentForm(){
        return parentForm;
    }
    
}
