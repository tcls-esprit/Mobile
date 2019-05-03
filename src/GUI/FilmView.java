/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Film;
import Entities.Filmfav;
import Entities.Rate;
import Services.FacebookShareService;
import Services.FilmService;
import Services.FilmfavService;
import Services.RateService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import static com.codename1.ui.Display.isInitialized;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.EventDispatcher;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author mouna
 */

public class FilmView {
    
     double avg=0;
         double sum=0;
    String url = "http://localhost/P/CDLC/web/images";
    Form f, form;
    Label nomD, descD,R, R1,descD2,descD3,descD4,descD5;
    Container cc;
    Button btnRate , btnVid,btnfav,btnshare;
     Slider starRank = new Slider();
     
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
public double getr(){
    double i =starRank.getProgress();
  
    return i;
}
private Slider createStarRankSlider() {
   
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
 
    
    public  FilmView() {
//        InfiniteContainer ic = new InfiniteContainer(10) {
       
         cc = new Container(BoxLayout.y());
         
        cc.setScrollableY(true);
        
        FilmService SP = new FilmService();
        ArrayList<Film> lis = SP.getList2();
        RateService serv = new RateService();
         ArrayList<Rate> liss = serv.getList2();
         
        for (Film li : lis) {
            
            Container c = new Container(BoxLayout.y());

            Image placeholder = Image.createImage(700,700);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getImage(), url + "/" + li.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

           

            Label aa = new Label("Titre : " + li.getTitre().toString());
            Label desc = new Label("Description : " + li.getDescription().toString());
            
            //c.add(a);
            c.add(imgV);
            c.add(aa);
            c.add(desc);
            
            //f.add(c);
            cc.add(c);
            

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            aa.addPointerPressedListener((l) -> {
                
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
            
            
                
                for (Rate lii : liss) {
            System.out.println(lii.getIdfilm());
            if (lii.getIdfilm()==li.getId()){
          
         sum+=lii.getRatee();
                
        }
          
        
        }
        avg=sum/liss.size();
        System.out.println("avg="+avg);

        
           
                Label lbser = new Label();
                Container F3 = new Container(BoxLayout.y());
                Container F4 = new Container(BoxLayout.y());
                Container F5 = new Container(BoxLayout.x());
                //Container x=new Container(BoxLayout.x());

                F3.add(lbser);
                // form.add(share);

                System.out.println("imaage");
                Image placeholder1 = Image.createImage(500, 500);
                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
                URLImage urli = URLImage.createToStorage(en, li.getImage(), url + "/" + li.getImage());
                ImageViewer img = new ImageViewer();
                img.setImage(urli);

                ConnectionRequest con = new ConnectionRequest();

                String url = "http://localhost/P/CDLC/web/app_dev.php/films/find/" + li.getId();
                con.setUrl(url);

                con.addResponseListener((le) -> {
                    String reponse = new String(con.getResponseData());
                    System.out.println(reponse);
                    
                });
                 
                
                    //lbser.setText(reponse);
                    nomD = new Label(  "Titre           :" + li.getTitre());
                    descD2 = new Label("Realisateur :  " + li.getNomrealisateur());
                    descD3 = new Label("Categorie   :" + li.getCategorie());
                    descD4 = new Label("Duree          :" + li.getDuree());
                    descD5 = new Label("Rate            :" + avg +"/10");
                    descD = new Label( "Description :" + li.getDescription());
                    
                    btnRate = new Button("Rate");
                    btnVid = new Button("Watch Trailer");
                    btnfav = new Button("Add to favorite");
                    btnshare = new Button("SareTO Facebook");
                 
                 //ajouter au fav
                 
                 btnfav.addActionListener((e) -> {
            FilmfavService servv = new FilmfavService();
            Filmfav f = new Filmfav(4,li.getId());
            servv.Favoris(f);
           //Double.valueOf(createStarRankSlider().getProgress())
              //R1 = new Label("Rate :" + createStarRankSlider().getProgress() );  
              

        });
                    
                    
                //Trailer
                
                btnVid.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        final Form hi = new Form("MediaPlayer", new BorderLayout());

                String file ="C:\\vid\\"+li.getVideo();
                System.out.println(file);
                try {
                    
                   
                Toolbar tb1 = hi.getToolbar();
                
                
                   tb1.addCommandToOverflowMenu("retour", null,new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    
                    Start s=new Start();
                    s.getF0().show();
        
                }
            } );
                    
                    
                    Media video = MediaManager.createMedia(file, true);
                    hi.removeAll();
                    hi.add(BorderLayout.CENTER, new MediaPlayer(video));
                    video.play();
                    hi.revalidate();
                } catch(IOException err) {
                    Log.e(err);
                } 
            
                       hi.show();
            }
        });
                    
                    
                    
                    
                    
                F3.add(img);
                F3.add(FlowLayout.encloseCenter(createStarRankSlider()));
                F3.add(nomD);
                F3.add(descD4);
                F3.add(descD5);
                F3.add(descD2);
                F3.add(descD3);
                
                F3.add(descD);
                
               //+ createStarRankSlider().getProgress()
               
                F3.add(btnRate);
                F3.add(btnVid);
                F3.add(btnfav);
                F3.add(btnshare);
                
                form.add(F3);
                
                form.show();
          
         btnRate.addActionListener((e) -> {
            RateService servv = new RateService();
            Rate t = new Rate( getr(),li.getId());
            servv.ajoutRate(t);
            
           //  System.out.println(Double.valueOf(createStarRankSlider().getProgress() ));
              //R1 = new Label("Rate :" + createStarRankSlider().getProgress() );  
              

        });
         
         btnshare.addActionListener((e) -> {
             
             FilmService serv3 = new FilmService();
            FacebookShareService fb = new FacebookShareService("EAACEdEose0cBAKH9SvjldA6ZCP27vLOvrpuQxWfEeAFKHWvJuP80JkuUqdSC79pRV5ZC8RewXdsfv3yMOgbzkIXHuotz9fHHwzbnsKmR23AyE6Eo74nLf8DcOLYe4Oqc5LWW3gG8kHVGEY0pBNMpSE6kocg1IzJko10OCgVsN4xZBDzE3BpCSkawQHkT21pmhcO4hZCiYAZDZD");
        
                     try {
                         fb.share("test");
                         
                     } catch(IOException ex) {
                         
                     }
        

        });
         
        
             form.show();   
                
            });
            c.setLeadComponent(aa);
//            cc.add(c);
            System.out.println("test ba3d a");

        }
    
    }
    
    public Container getF1() {
        
        return cc;
        
    }
    
    

  
    
}
