/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Evenement;
import static GUI.HomeEvent.ress;
import Services.ServiceCommentaire;
import Services.userService;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author souissi oussama
 */
public class Commentaires  {
    
    private Form current;
 
      public int idd;
    Form hi ;
   private Button back;
   Label User;
   Label Contenu;
   Label Date;
   Label Supp;
   Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL); 
    public Commentaires(Resources res,Evenement ee){
        
        
        hi= new Form( new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = hi.getToolbar();
        tb.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_FLIP_TO_BACK, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            DetailEvent he = new DetailEvent(ee.getId());
            he.getf().show();
        }
        });

  
  
        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
        TextField name = new TextField("", "Name", 20, TextField.ANY);
        FontImage.setMaterialIcon(name.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField email = new TextField("", "E-mail", 2,20);
        email.getUnselectedStyle().setFgColor(-16777216);
        c.add(name);
        c.add(email);
        FontImage.setMaterialIcon(email.getHintLabel(), FontImage.MATERIAL_EMAIL);
       
        TextField contenu = new TextField("", "Contenu", 2, 200);
        contenu.getUnselectedStyle().setFgColor(-16777216);
        FontImage.setMaterialIcon(contenu.getHintLabel(), FontImage.MATERIAL_LIBRARY_BOOKS);
        
          contenu.setSingleLineTextArea(false);
          
          
           Button save = new Button("Save");
           FontImage.setMaterialIcon(save, FontImage.MATERIAL_SAVE);
           
    
        Container comps = new Container();
  
//        hi.add(c);
   
        hi.add(contenu);
        hi.add(save);
           
     
        hi.show();
        
        ServiceCommentaire sc = new ServiceCommentaire();
        List<Commentaire> listComm = new ArrayList<>();
        listComm= sc.getAllCommentsEvent(ee);
        save.addActionListener((e) -> {
            Commentaire comm = new Commentaire(contenu.getText(), userService.LoggedUser.getUserId(), ee.getId());
            sc.ajoutComm(comm);
            hi.refreshTheme();
        });
        
        for (Commentaire com : listComm)
        {
         c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ccc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Image placeholder = Image.createImage(380, 300);
        EncodedImage encImg = EncodedImage.createFromImage(placeholder, false);
        URLImage imgUrl= URLImage.createToStorage(encImg,com.getImage() , "http://127.0.0.1/CDLC/web/EventsImages/"+com.getImage());
        imgUrl.fetch();
        ImageViewer img = new ImageViewer(imgUrl);
        c.add(img);
        User = new Label();
        User.setText("User: "+com.getOwner());
        User.getUnselectedStyle().setFont(smallBoldSystemFont);
        FontImage.setMaterialIcon(User, FontImage.MATERIAL_PERSON, 5);
        ccc.add(User);
        System.out.println(User.getText());
        Date  = new Label();
        Date.setText("Date: "+com.getDate());
        Date.getUnselectedStyle().setFont(smallBoldSystemFont);
        FontImage.setMaterialIcon(Date, FontImage.MATERIAL_TIMER, 5);
        ccc.add(Date);
        
        Contenu  = new Label();
        Contenu.setText(com.getContenu());
        ccc.add(Contenu);
            System.out.println(com.getId_user());
        if (userService.LoggedUser.getUserId()==com.getId_user())
        {
            Supp = new Label("supprimer");
            FontImage.setMaterialIcon(Supp, FontImage.MATERIAL_DELETE, 5);
            System.out.println("hhhhhhh");
            ccc.add(Supp);
            Supp.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {                 
                ServiceCommentaire sc = new ServiceCommentaire();
                sc.supprimerComm(com);
                hi.show();
            }
            
        });
        }
        
        
        c.add(ccc);
        hi.add(c);
        }
    
    }
   
    public Form getDisplayName() {
        return hi;
    }
}
