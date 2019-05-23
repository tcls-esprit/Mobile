/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CommentaireTheatre;
import Entities.SessionTheatre;
import Entities.Theatre;
import Services.CommentaireTheatreService;
import Services.SessionService;

import Services.TheatreService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
 
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
 
 
 
 
 

/**
 *
 * @author AQuel
 */
public class HomeTheatre {
    
    private Form fhome ;
    private SpanLabel l;
    private String url= "http://localhost/CDLC/web/images/"; 
    private EncodedImage imgc; 
    private Image img ;
    private ImageViewer imgv;
    private  Database data;
    
     public HomeTheatre() {
        
        fhome = new Form("ThEaTrE", new BoxLayout(BoxLayout.Y_AXIS));
        
        l = new SpanLabel("");
        Label l = new Label();
        
        fhome.add(l);
         TheatreService tht = new  TheatreService();
         SessionService ses = new SessionService();
           
       
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("Bienvenu Dans L'Espace Théatrale!");
        n.setAlertTitle("Cité De La Culture!");
        System.out.println("Notification"+n);
        

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_DAY    
        );
           
        
            // l.setText(tht.ConvertionList().toString());
            // System.out.println(tht.ConvertionList().toString());
        try {
            imgc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            
        }
         
       
        for (Theatre t : tht.ConvertionList()) {
            
      
            Container     con1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container     con2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container     con3 = new Container (new BoxLayout(BoxLayout.X_AXIS));
            Label l1 = new Label(t.getTitre_scene());
            SpanLabel l2 = new SpanLabel(t.getDescription());
            
            
            url += t.getImage();
            img = URLImage.createToStorage(imgc, url , url, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(img);
            url = "http://localhost/CDLC/web/images/";
            System.out.println(url);
//             fhome.add(l1);
            // fhome.add(imgv);
           // con1.setLeadComponent(l1);
            l1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                          try {
            data = Database.openOrCreate("pi.db");       
            data.execute("create table if not exists favoris(name TEXT,indice TEXT);");                   
            
        } catch (IOException ex) {
            
          }
             Dialog d1 = new Dialog();    
              
              if ( d1.show("Information", "Vous Choisissez Vos Favoris ?", "Annuler", "OK") == true )
                {
                     fhome.show();
                }else {
                  try {
                   System.out.println(l1.getText());
                   System.out.println(l2.getText());
                              
                                data.execute("insert into favoris(name,indice) values('"+l1.getText()+"','"+l2.getText()+"');");
                                  System.out.println("ajout avec succes");
                              } catch (IOException ex) {
                                  
                              }
                }
                   try {
                  Cursor   c1 = data.executeQuery("select * from favoris");
                
            while (c1.next()){
                Row row = c1.getRow();
                String name = row.getString(0);
                String indice = row.getString(1);
                 System.out.println(name);
                 System.out.println(indice);
            }} catch (IOException ex) {
                     
                }
                }
            });
            TextArea ta = new TextArea();
            ta.setVisible(true);
             
            con1.add(l1);
            con1.add(l2);
            
            con2.add(imgv);
             con1.add(ta);
            Button commentaire = new Button("Add Comment");
            con1.add(commentaire);
           
            con2.add(con1);
            con3.add(con2);
          //  con3.add(ta);
            
            fhome.add(con3);
            System.out.println(t.getTitre_scene());
            System.out.println(t.getImage());
          
            commentaire.addActionListener((ActionListener) (ActionEvent evt) -> {
                CommentaireTheatreService cts = new CommentaireTheatreService();
                CommentaireTheatre test = new CommentaireTheatre(l1.getText(),ta.getText());
                System.out.println(ta.getText());
                System.out.println(l1.getText());
                cts.ajoutComment(test);
                //System.out.println("ajouter success");
               
            });
            
            ta.setText("");
            
        }
        
        
                         fhome.getToolbar().addCommandToOverflowMenu("Home", null, (ActionListener) (ActionEvent evt) -> {
             ProfileView lo = new ProfileView(fhome) {
                @Override
                protected void showOtherForm(Resources res) {
                }
            } ; 
                    lo.getContainer().show();
                
        });
         fhome.getToolbar().addCommandToOverflowMenu("LogOUt", null,  new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Login l = new Login(this.getClass());
                l.show();
                
            }
        });
         
         fhome.getToolbar().addCommandToOverflowMenu("Vos Favoris", null, (ActionListener) (ActionEvent evt) -> {
             FavorisList ls = new FavorisList();
             ls.getF1().show();
        });
         
         fhome.getToolbar().addCommandToOverflowMenu("Les Commentaires", null, (ActionListener) (ActionEvent evt) -> {
              ListCommentaire lc = new ListCommentaire();
              lc.getF1().show();
        });
       
     }

    public Form getFhome() {
        return fhome;
    }

    public void setFhome(Form fhome) {
        this.fhome = fhome;
    }
     
     
}
