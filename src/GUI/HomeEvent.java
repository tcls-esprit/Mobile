/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author souissi oussama
 */


import Entities.Evenement;
import Services.ServiceEvent;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;



public class HomeEvent extends Form {
    
    public static Resources ress;
    private Resources theme;
    Label ltitre;
    Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    public HomeEvent(Resources res) {
        this.ress = res;
        setLayout(new BorderLayout());
        Form cc = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        //loadProfile();
        //loadEvent();
        theme = UIManager.initFirstTheme("/theme_1");
        Container south = new Container(new FlowLayout(Component.CENTER));
        south.setUIID("footer");

        Button Exit = new Button("Sortir", res.getImage("power-signal.png"));
        Exit.setGap(Exit.getStyle().getFont().getHeight());
        Exit.setUIID("LoginBtn");
        Exit.addActionListener(e
                -> System.exit(0)
        );

        south.add(Exit);
        this.add(BorderLayout.SOUTH, south);
        
        
        this.getToolbar().addMaterialCommandToSideMenu("Postuler une demande pour un événement", FontImage.MATERIAL_ADD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               ProfileView lo = new ProfileView(cc) {
                @Override
                protected void showOtherForm(Resources res) {
                }
            } ; 
                    lo.getContainer().show();
                }
                //FillActualite f = new FillActualite(theme);
                //f.show();

            
        });
           this.getToolbar().addMaterialCommandToSideMenu("Postuler une demande pour un événement", FontImage.MATERIAL_ADD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AjoutEvent ae = new AjoutEvent();
                ae.getF().show();
                //FillActualite f = new FillActualite(theme);
                //f.show();

            }
        });
        this.getToolbar().addMaterialCommandToSideMenu(" Consulter mes demandes ", FontImage.MATERIAL_VIEW_LIST, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               MesDemandes m = new MesDemandes();
               m.getf().show();
            }
        });
       
        this.getToolbar().addMaterialCommandToSideMenu(" Consulter mes événements", FontImage.MATERIAL_VIEW_ARRAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               AfficheEventUser aeu = new AfficheEventUser();
               aeu.getf().show();
            }
        });
        this.getToolbar().addMaterialCommandToSideMenu(" les événements les plus populaires", FontImage.MATERIAL_INFO, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               EventPopulaires aeu = new EventPopulaires();
               aeu.getf().show();
            }
        });
        this.getToolbar().addCommandToOverflowMenu(" Deconnexion", res.getImage("logout.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Login l = new Login(this.getClass());
                l.show();
                
            }
        });
        List<Evenement> listEvent = new ArrayList<>();
        ServiceEvent es = new ServiceEvent();
        listEvent=es.getAllEvents();
        
        for (Evenement e : listEvent)
        {
            if (e.getEtat()==1)
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
        Label ltype = new Label();
        ltype.setText("Type_event: "+e.getType_event());
        ltype.getUnselectedStyle().setFont(smallBoldSystemFont);
        FontImage.setMaterialIcon(ltype, FontImage.MATERIAL_EVENT, 5);
        ccc.add(ltype);
        c.add(ccc);
        cc.add(c);
         ltitre.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {                 
                
                    DetailEvent de = new DetailEvent(e.getId());
                
            }
        });
            }
        }
        this.add(BorderLayout.CENTER, cc);
        this.refreshTheme();
        
    }
    
    public Form getf()
    {
        return this;
    }
    
}
