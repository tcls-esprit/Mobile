/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
 

/**
 *
 * @author AQuel
 */
public  class FavorisList {
    
    private Form f1 ; 
    private Label lab , labe ; 
    private Database data ;
    public FavorisList()
    {
        
        
        f1 = new Form("Vos Favoris", new BoxLayout(BoxLayout.Y_AXIS));
      
        try {
                   data = Database.openOrCreate("pi.db");  
                  Cursor   c1 = data.executeQuery("select * from favoris");
                
            while (c1.next()){
                Row row = c1.getRow();
                String name = row.getString(0);
                String indice = row.getString(1);
                 lab = new Label(name);
                 labe = new Label(indice);
                 System.out.println(name);
                 System.out.println(indice);
                 f1.add(new Label("Votre Favoris Des  Scénes"));
                 
            Container     con1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 con1.add(lab);
                 con1.add(labe);
                 f1.add(con1);
            }} catch (IOException ex) {
                     
               }
        
        f1.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt) -> {
             HomeTheatre ls = new HomeTheatre();
             ls.getFhome().show();
        });
       f1.getToolbar().addCommandToOverflowMenu("LogOUt", null, (ActionListener) (ActionEvent evt) -> {
             LoginStatic ls = new LoginStatic() {
                 @Override
                 protected void showOtherForm(Resources res) {
                     
                  }
             };
             ls.getF().show();
        });
    
    }

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
    
    
}
