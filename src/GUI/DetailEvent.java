/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.HomeEvent.ress;
import Entities.Concert;
import Entities.Evenement;
import Entities.Exposition;
import Entities.Oeuvre;
import Entities.SessionEvent;
import Services.ServiceCommentaire;
import Services.ServiceConcert;
import Services.ServiceEvent;
import Services.ServiceExposition;
import Services.ServiceOeuvre;
import Services.ServiceSession;
import Services.userService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.CN;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author souissi oussama
 */
public class DetailEvent {
    Resources res;
    private Resources theme;
    Form f ;
    public DetailEvent (int idEvent) 
    {
        
        Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        List<SessionEvent> listsession = new ArrayList<>();
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
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ServiceEvent es = new ServiceEvent();
        System.out.println(idEvent);
        Evenement e = es.getEventById(idEvent);
        System.out.println(e);
        Image placeholder = Image.createImage(900, 900);
        EncodedImage encImg = EncodedImage.createFromImage(placeholder, false);
        URLImage imgUrl= URLImage.createToStorage(encImg,e.getImage() , "http://127.0.0.1/CDLC/web/EventsImages/"+e.getImage());
        imgUrl.fetch();
        ImageViewer img = new ImageViewer(imgUrl);
        f.add(img);
        
        Label titre = new Label("Titre :"+e.getTitre());
        FontImage.setMaterialIcon(titre, FontImage.MATERIAL_TITLE, 5);
        f.add(titre);
        Label Description = new Label("Description : "+e.getDescription());
        FontImage.setMaterialIcon(Description, FontImage.MATERIAL_DESCRIPTION, 5);
        f.add(Description);
        Label Prix = new Label("Prix : "+e.getPrix());
        FontImage.setMaterialIcon(Prix, FontImage.MATERIAL_MONEY_OFF, 5);
        f.add(Prix);
        Label Duree = new Label("Duree : "+e.getDuree());
        FontImage.setMaterialIcon(Duree, FontImage.MATERIAL_TIMER, 5);
        f.add(Duree);
        Label type_event = new Label("type_event : "+e.getType_event());
        FontImage.setMaterialIcon(type_event, FontImage.MATERIAL_EVENT, 5);
        f.add(type_event);
        ServiceSession ss = new ServiceSession();
        try {
            listsession=ss.getAllSessionsEvent(e);
        } catch (ParseException ex) {
            
        }
        int i=0;
        for (SessionEvent se : listsession)
        {
            i++;
        }
        
        if (e.getType_event().equals("concert"))
        {
            ServiceConcert cs = new ServiceConcert();
            Concert c = cs.getEventById(idEvent);
            Label TypeConcert = new Label("TypeConcert : "+c.getType_concert());
            FontImage.setMaterialIcon(TypeConcert, FontImage.MATERIAL_EVENT, 5);
            f.add(TypeConcert);
            Label artistes = new Label("Les artistes : "+c.getArtistes());
            FontImage.setMaterialIcon(artistes, FontImage.MATERIAL_PEOPLE, 5);
            f.add(artistes);
            
        }
        
        if (e.getType_event().equals("exposition"))
        {
            int io=0;
            ServiceExposition expos = new ServiceExposition();
            Exposition c = expos.getEventById(idEvent);
            ServiceOeuvre so = new ServiceOeuvre();
            List<Oeuvre> listOeuvre = new ArrayList<>();
            listOeuvre = so.getAllOeuvreEvent(e);
            for ( Oeuvre o : listOeuvre)
            {
               io++;
            }
            Label nombreRayon = new Label("NombreRayon : "+c.getNombre_rayon());
            FontImage.setMaterialIcon(nombreRayon, FontImage.MATERIAL_NOTE, 5);
            f.add(nombreRayon); 
            Form hii = new Form("Les Oeuvres", new TableLayout(i, 3));
            hii.add(new Label("Titre")).
            add(new Label("Date de creation")).add("Prix");
            for (Oeuvre o : listOeuvre)
            {
                hii.add(new Label(o.getTitre())).
                add(new Label(o.getDateCreate())).
                        add(new Label((Float.toString(o.getPrix()))));
            }
            f.add(hii);
            
        }
        Form hi = new Form("Les sessions", new TableLayout(i, 2));
        hi.add(new Label("Date Debut")).
        add(new Label("Date Fin"));
        for (SessionEvent se : listsession)
        {
            hi.add(new Label(se.getDate_deb())).
        add(new Label(se.getDate_deb()));
        }
        f.add(hi);
        if (e.getEtat()==1)
        {
        tb.addCommandToOverflowMenu("Les commentaires",null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    Commentaires he = new Commentaires(ress,e);
                    he.getDisplayName().show();
                }});
        }
        if(userService.LoggedUser.getUserId()==e.getId_user())
        {
        tb.addCommandToOverflowMenu("Supprimer l'événement",null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceEvent se = new ServiceEvent();
                    se.supprimerEvent(e);
                    HomeEvent he = new HomeEvent(ress);
                    he.getf().show();
                }});
        }
        if(userService.LoggedUser.getUserId()==e.getId_user())
        {
         tb.addCommandToOverflowMenu("Modifier l'événement",null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ModifierEvent me = new ModifierEvent(e);
                    me.getF().show();
                }});
        }
        
        f.show();
        
        
        
        
    }
     public Form getf()
    {
        return f;
    }
    
    
}
