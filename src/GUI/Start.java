/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author mouna
 */
public class Start {
    Form hi;
    public Start(){
       
        
         hi = new Form("Movies", BoxLayout.y());
       
        Form Films = new Form("Film List", BoxLayout.y());
        Form Program = new Form("Program", BoxLayout.y());
        Form fav = new Form("My Favorite", BoxLayout.y());
        
        Toolbar tb = hi.getToolbar();    
        Toolbar tb1 = Films.getToolbar();
        Toolbar tb2 = Program.getToolbar();
        Toolbar tb3 = fav.getToolbar();
       
        
        Form start = new Form();
        
        FilmView h = new FilmView();
        Films.add(h.getF1());
        
         SessionFilmView h1 = new SessionFilmView();
        Program.add(h1.getF2());
        
        
        FilmfavView h2 = new FilmfavView();
        fav.add(h2.getF3());
        
      
       tb.addMaterialCommandToSideMenu("",FontImage.MATERIAL_ADD , 
                (ActionListener) (ActionEvent evt) -> { 
            
                 
          
        }); 
       
         tb.addMaterialCommandToSideMenu("Film List",FontImage.MATERIAL_MOVIE_CREATION, 
                (ActionListener) (ActionEvent evt) -> { 
                    
                    tb1.addCommandToOverflowMenu("retour", null,new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    hi.show();
                }
            } );
            
                 Films.show();
          
        });
                  tb.addMaterialCommandToSideMenu("Film List",FontImage.MATERIAL_MOVIE_CREATION, 
                (ActionListener) (ActionEvent evt) -> { 
                    
                    tb1.addCommandToOverflowMenu("retour", null,new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    hi.show();
                }
            } );
            
                 Films.show();
          
        });
         
         tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME , 
         e->{   ProfileView lo = new ProfileView(this.getF0()) {
                @Override
                protected void showOtherForm(Resources res) {
                }
            } ; 
                    lo.getContainer().show();
                });
         
             tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME , 
         e->{         Login lo = new Login(this.getClass());
                lo.show();
                });
          tb.addMaterialCommandToSideMenu("Favorite",FontImage.MATERIAL_MOVIE_FILTER , 
                (ActionListener) (ActionEvent evt) -> { 
            
                    tb3.addCommandToOverflowMenu("retour", null,new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    hi.show();
                }
            } );
                    
                fav.show();
          
        });
         
       hi.show(); 
       
        
    }
    
    public Form getF0(){
        hi.show();
    return hi;
    }
    
    }
    

