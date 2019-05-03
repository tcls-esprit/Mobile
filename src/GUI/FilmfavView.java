/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Film;
import Entities.Filmfav;
import Entities.SessionFilm;
import Services.FilmService;
import Services.FilmfavService;
import Services.SessionFilmService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
public class FilmfavView {
    Form f, form;
    
    Container cc;
    Button btnsupp;
  String url = "http://localhost/P/CDLC/web/images";
    public FilmfavView(){
        
         form = new Form(BoxLayout.x());
                Toolbar tb1 = form.getToolbar();
                 tb1.addCommandToOverflowMenu("retour", null,new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    
                    Start s=new Start();
                    s.getF0().show();
        
                }
            } );
            
            
        
        
    cc = new Container(BoxLayout.y());
         
        cc.setScrollableY(true);
        
        FilmfavService SP = new FilmfavService();
        FilmService SP2 = new FilmService();
        ArrayList<Filmfav> lis = SP.getList2();
        ArrayList<Film> lis2 = SP2.getList2();
        
        for (Filmfav li : lis) {
            for (Film lii : lis2)

if (lii.getId()==li.getIdfilm()){
            Container c = new Container(BoxLayout.y());
           
            Label aa = new Label("Film favoris : " +   lii.getTitre() );
            Image placeholder1 = Image.createImage(500, 500);
                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
                URLImage urli = URLImage.createToStorage(en, lii.getImage(), url + "/" + lii.getImage());
                ImageViewer img = new ImageViewer();
                img.setImage(urli);
           
            c.add(aa);
            c.add(img);
            
            cc.add(c);
            
            
            
      
             
             
                 
                 //ajouter au fav
                 
                 aa.addPointerPressedListener((e) -> {
                     
                     
                     
            FilmfavService servv = new FilmfavService();
            
            servv.supprimerfav(li.getId());
           //Double.valueOf(createStarRankSlider().getProgress())
              //R1 = new Label("Rate :" + createStarRankSlider().getProgress() );  
              

        });
           
                form.show();
}
    }   }public Container getF3() {
        
        return cc;
        
    }}
    
