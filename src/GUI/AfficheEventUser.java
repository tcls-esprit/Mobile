/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import static GUI.HomeEvent.ress;
import Services.ServiceEvent;
import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author souissi oussama
 */
public class AfficheEventUser {
    
    private Resources theme;
    Label ltitre;
    Form f;
    Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    public AfficheEventUser()
    {
        theme = UIManager.initFirstTheme("/theme_1");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_FLIP_TO_BACK, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            HomeEvent he = new HomeEvent(ress);
            he.getf().show();
        }
        });
        List<Evenement> listEvent = new ArrayList<>();
        ServiceEvent es = new ServiceEvent();
        listEvent=es.getAllEvents();
        
        for (Evenement e : listEvent)
        {
            
            if (e.getId_user()==userService.LoggedUser.getUserId())
            {
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ccc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Image placeholder = Image.createImage(380, 300);
        EncodedImage encImg = EncodedImage.createFromImage(placeholder, false);
        URLImage imgUrl= URLImage.createToStorage(encImg,e.getImage() , "http://127.0.0.1/CDLC/web/EventsImages/"+e.getImage());
        imgUrl.fetch();
        ImageViewer img = new ImageViewer(imgUrl);
        c.add(img);
        ltitre = new Label();
        ltitre.setText("Titre: "+e.getTitre());
        ltitre.getUnselectedStyle().setFont(smallBoldSystemFont);
        FontImage.setMaterialIcon(ltitre, FontImage.MATERIAL_TITLE, 5);
        ccc.add(ltitre);
                System.out.println(ltitre.getText());
        Label ltype = new Label();
        ltype.setText("Type_event: "+e.getType_event());
        ltype.getUnselectedStyle().setFont(smallBoldSystemFont);
        FontImage.setMaterialIcon(ltype, FontImage.MATERIAL_EVENT, 5);
        ccc.add(ltype);
        c.add(ccc);
        f.add(c);
         ltitre.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {                 
                
                    DetailEvent de = new DetailEvent(e.getId());
                
            }
        });
            }
        }
        }
       
        public Form getf()
    {
        return f;
    }
}
            

