/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Guide;
import Services.GuideService;

/**
 *
 * @author Bilel
 */
public class AffichageGuide {
    Form f;
    Label nom;
    Label prenom;
    Label description;
    
    SpanLabel sp;
    SpanLabel sp1;
    SpanLabel sp2;
   
    
    public AffichageGuide(){
       
        f = new Form("Liste des guides");
        GuideService gs=new GuideService();
        for(Guide g : gs.getList2()){
            
                Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                C1.setLayout(new BorderLayout());
                
                nom=new Label("Nom : "+g.getNom());
                prenom=new Label("Prenom :"+g.getPrenom());
                description=new Label("Description :"+g.getDescription());
                sp = new SpanLabel(nom.getText());
                sp1 = new SpanLabel(prenom.getText());
                sp2 = new SpanLabel(description.getText());
//                sp.add(nom);
//                sp1.add(prenom);
//                sp2.add(description);
                C1.addComponent(BorderLayout.EAST,sp);
                C1.addComponent(BorderLayout.CENTER,sp1);
                C1.addComponent(BorderLayout.SOUTH,sp2);
                f.add(C1);
               
//                
//                f.add(sp);
//                f.add(sp1);
//                f.add(sp2);
        }
        
       
        
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
