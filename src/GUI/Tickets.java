/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.TicketsFilm;
import Entities.TicketsTheatre;
import Entities.Ticketsevent;
import Entities.User;
import Services.TicketsService;
import Services.userService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import javafx.scene.paint.Color;

/**
 *
 * @author bhk
 */
public class Tickets {

    Form form =new Form();
    SpanLabel lb;
  
    public Tickets(Form Parent , User member) {
        
        form = new Form(BoxLayout.y());
        TicketsService ts=new TicketsService();
        
          form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_EXIT_TO_APP, (ev)->{
          Parent.show(); 
          });
                      form.getToolbar().addMaterialCommandToRightSideMenu("Edit", FontImage.MATERIAL_EDIT, (e) -> {
                new EditFormView(this.form, member).getForm().show();
            });
                        form.getToolbar().addMaterialCommandToRightSideMenu("My profile",FontImage.MATERIAL_MOVIE , (e) -> {
                ProfileView a = new ProfileView(this.form) {
                    @Override
                    protected void showOtherForm(Resources res) {
                    }
                } ; 
                            a.getContainer().show();
                
            });
                        form.getToolbar().addMaterialCommandToRightSideMenu("Calendar",FontImage.MATERIAL_TIMER, (e) -> {
                new EditFormView(this.form, member).getForm().show();
            });
        Label events = new Label("evenement : ") ;
        //form.add(events) ; 
        for (Ticketsevent te : ts.getListTicketevent())
            createevent(te) ; 
        Label theatre = new Label("piéces de theatres : ") ;
        //form.add(theatre) ; 
        for (TicketsTheatre te : ts.getListTickettheatre())
            createtheatre(te) ; 
        Label films = new Label("films : ") ;
        //form.add(films) ; 
        for (TicketsFilm te : ts.getList2())
            createfilms(te) ; 
        
    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }
   public void createevent(Ticketsevent t)
   {
       TicketsService ts = new TicketsService() ; 
   Container cx = new Container(BoxLayout.x()) ;
   Container cy = new Container(BoxLayout.y()) ; 
   Label l = new Label("tickets numero") ; 
   Label nom = new Label() ; 
   nom.setText("nom : "+ts.nomevent(t.getIdTicket()));
      nom.getStyle().setBgColor(0xff000);
      l.setText("ticket num :"+t.getIdTicket());
   Label date = new Label() ; 
   date.setText("la date : "+ts.dateevent(t.getIdTicket()));
   Label Salle = new Label() ;
   Salle.setText("la salle : "+ts.salleevent(t.getIdTicket()));
   Label id = new Label() ; 
   id.setText(String.valueOf(t.getIdTicket()));
   Label owner = new Label() ;
   owner.setText("nom user : "+userService.LoggedUser.getNom()+" "+userService.LoggedUser.getPrenom());
   Button qr = new Button("get QR Code", MyApplication.theme.getImage("MyQRCode.png"));
           qr.addActionListener(e->{
   new qrcodeview().f.show(); 
   
   }
       );
   
    
   form.add(l).add(owner).add(nom).add(date).add(Salle).add(qr) ; 
   }
      public void createtheatre(TicketsTheatre t)
   {
       TicketsService ts = new TicketsService() ; 
   Container cx = new Container(BoxLayout.x()) ;
   Container cy = new Container(BoxLayout.y()) ; 
   Label l = new Label("tickets numero") ; 
   Label nom = new Label() ; 
   nom.setText("nom : "+ts.nometheatre(t.getIdTicket()));
      nom.getStyle().setBgColor(0xff000);
      l.setText("ticket num :"+t.getIdTicket());
   Label date = new Label() ; 
   date.setText("la date : "+ts.datetheatre(t.getIdTicket()));
   Label Salle = new Label() ;
   Salle.setText("la salle : "+ts.salletheatre(t.getIdTicket()));
   Label id = new Label() ; 
   id.setText(String.valueOf(t.getIdTicket()));
   Label owner = new Label() ;
   owner.setText("nom user : "+userService.LoggedUser.getNom()+" "+userService.LoggedUser.getPrenom());
   Button qr = new Button("get QR Code", MyApplication.theme.getImage("MyQRCode.png")); 
              qr.addActionListener(e->{
   new qrcodeview().f.show(); 
   
   }
       );
   form.add(l).add(owner).add(nom).add(date).add(Salle).add(qr) ; 
   }
        public void createfilms(TicketsFilm t)
   {
       TicketsService ts = new TicketsService() ; 
   Container cx = new Container(BoxLayout.x()) ;
   Container cy = new Container(BoxLayout.y()) ; 
   Label l = new Label("tickets numero") ; 
   Label nom = new Label() ; 
   nom.setText("nom : "+ts.nomfilm(t.getIdTicket()));
      nom.getStyle().setBgColor(0xff000);
      l.setText("ticket num :"+t.getIdTicket());
   Label date = new Label() ; 
   date.setText("la date : "+ts.datefilm(t.getIdTicket()));
   Label Salle = new Label() ;
   Salle.setText("la salle : "+ts.sallefilm(t.getIdTicket()));
   Label id = new Label() ; 
   id.setText(String.valueOf(t.getIdTicket()));
   Label owner = new Label() ;
   owner.setText("nom user : "+userService.LoggedUser.getNom()+" "+userService.LoggedUser.getPrenom());
   Button qr = new Button("get QR Code", MyApplication.theme.getImage("MyQRCode.png")); 
              qr.addActionListener(e->{
   new qrcodeview().f.show(); 
   
   }
       );
   form.add(l).add(owner).add(nom).add(date).add(Salle).add(qr) ; 
   }    
}
