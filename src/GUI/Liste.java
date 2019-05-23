/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Film;
import Entities.SessionFilm;
import Services.FilmService;
import Services.SessionFilmService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author mouna
 */
public class Liste {
     Form f;
     String url = "http://localhost/P/CDLC/web/images";
     Container cc;
    public Liste(String p){
        
        
         cc = new Container(BoxLayout.y());
         
        cc.setScrollableY(true);
        
        
        
        
         SessionFilmService SP = new SessionFilmService();
        FilmService SP2 = new FilmService();
         ArrayList<Film> lis2 = SP2.getList2();
        
        ArrayList<SessionFilm> lis = SP.getList2();
        for (SessionFilm li : lis) {
          
          for (Film lii : lis2)

            if ((lii.getId()==li.getIdfilm()) && (p.equals(li.getDate())) ){
                
            Container c = new Container(BoxLayout.y());
            
        
           
            Label aa = new Label("Movie : " +   lii.getTitre());
            
            Label desc2 = new Label("Time : " + li.getHeure());

            Label desc = new Label("Date : " + li.getDate().toString());
            
            Label des = new Label("----------------------" );
            
            
            
            Image placeholder1 = Image.createImage(500, 500);
                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
                URLImage urli = URLImage.createToStorage(en, lii.getImage(), url + "/" + lii.getImage());
                ImageViewer img = new ImageViewer();
                img.setImage(urli);
            

            
           
 c.add(img);           
c.add(aa);

            c.add(desc);
            c.add(desc2);
            
            c.add(des);
            
            //f.add(c);
        
            cc.add(c);
            
          
            
          

        }
         
         
         
     }
     }
    
    public Container getF5() {
        
         return cc;
        
        
    }
}
