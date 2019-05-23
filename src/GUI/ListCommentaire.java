/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CommentaireTheatre;
import Services.CommentaireTheatreService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author AQuel
 */
public class ListCommentaire {
     private Form f1 ; 
    private Label lab  ;
    private Label labe ; 
    
    public ListCommentaire ()
    {
        f1 = new Form("Les Commentaire", new BoxLayout(BoxLayout.Y_AXIS));
         Container     con1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         con1.add(new Label("Les Scénes "));
        con1.add(new Label("Commentaires"));
        f1.add(con1);
        CommentaireTheatreService cts = new CommentaireTheatreService();
         for (CommentaireTheatre ct : cts.ConvertionList()){
             Container     con2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
        
        Container     con3 = new Container (new BoxLayout(BoxLayout.X_AXIS));
        lab = new Label();
        labe = new Label();
        lab.setText(ct.getScene());
        lab.getStyle().setAlignment(0);
        labe.setText("   "+ct.getCommentaire());
        
        labe.getStyle().setAlignment(0);
        
        
        con3.add(lab);        
        con3.add(labe);
        con2.addAll(con3);
       
        f1.add(con2);
        
        lab.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     Dialog d1 = new Dialog();    
              
              if ( d1.show("Information", "Vous supprimez ce commentaire ?", null , "OK") == true )
                {
                     CommentaireTheatreService src = new CommentaireTheatreService();
                     src.supprimerComment(lab.getText());
                }
                  }
             });
         }
        f1.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt) -> {
             HomeTheatre ls = new HomeTheatre();
             ls.getFhome().show();
        });
       f1.getToolbar().addCommandToOverflowMenu("LogOUt", null, (ActionListener) (ActionEvent evt) -> {
            Login l  = new Login(this.getClass()) ; 
            l.Login.show(); 
        });
    }

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
    
}
