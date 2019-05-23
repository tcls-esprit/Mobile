/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import static GUI.HomeEvent.ress;
import Services.ServiceEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author souissi oussama
 */
public class ModifierEvent {
    Resources res;
    private Resources theme;
    Form form;
    Container f;
    TextField ttitre;
    TextField tdescription;
    TextField tduree;
    TextField tprix;
    Button btnmodifier;
    public ModifierEvent (Evenement e)
    {
        theme = UIManager.initFirstTheme("/theme_1");
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        form = new Form("Modifier l'évènement");
        f = new Container();
        Toolbar tb = form.getToolbar();
        tb.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_FLIP_TO_BACK, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            HomeEvent he = new HomeEvent(ress);
            he.getf().show();
        }
        });
        ttitre = new TextField();
        ttitre.setText(e.getTitre());
        
        tdescription = new TextField();
        tdescription.setText(e.getDescription());
        
        tduree = new TextField();
        tduree.setText(Integer.toString(e.getDuree()));
        tprix = new TextField();
        tprix.setText(Float.toString(e.getPrix()));
        btnmodifier = new Button("modifier");
        f.add(ttitre);
        f.add(tdescription);
        f.add(tduree);
        f.add(tprix);
        f.add(btnmodifier);
        form.add(f);
        btnmodifier.addActionListener((ee) -> {

            
            try {
               
                ServiceEvent ser = new ServiceEvent();
                Evenement t = new Evenement(e.getId(),
                        ttitre.getText(),
                        Float.parseFloat(tprix.getText()),
                        tdescription.getText(),
                        Integer.parseInt(tduree.getText()),
                        17,
                        e.getEtat(),
                        e.getImage(),
                        e.getType_event(),
                        "Ev"
                );
                ser.modifierEvent(t);
                System.out.println(t);
                DetailEvent he = new DetailEvent(e.getId());
                he.getf().show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        
    }
     public Form getF() {
        return form;
    }
    
    public void setF(Form f) {
        this.form = f;
    }
}
