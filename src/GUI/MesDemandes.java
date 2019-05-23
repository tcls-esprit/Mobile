

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
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author souissi oussama
 */
public class MesDemandes {
    Resources res;
    private Resources theme;
    Form f;
    public MesDemandes()
    {
        
        theme = UIManager.initFirstTheme("/theme_1");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS) );
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
            System.out.println(e.getId_user());
            
           if (e.getId_user()==userService.LoggedUser.getUserId())
           {
                MultiButton m = new MultiButton();
                m.setTextLine1("Titre"+e.getTitre());
                if (e.getEtat()==1)
                {
                    m.setTextLine2("Etat: événement accepté");
                }
                else
                {
                    m.setTextLine2("Etat: votre demande n'est pas encore traitée");
                }
                
                f.add(m);
                
               
        
            }
            
        }
        f.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : f.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        f.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : f.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        f.getContentPane().animateLayout(150);
    }
}, 4);
    


    }
    public Form getf()
    {
        return f;
    }
}
