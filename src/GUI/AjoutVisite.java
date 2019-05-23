/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import Entities.Guide;
import Entities.Visite;
import Services.GuideService;
import Services.VisiteService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

import java.util.Date;





/**
 *
 * @author Bilel
 */
public class AjoutVisite {
    
    Form f;
//    Picker dateV;
//    //Picker dateV;
//    Picker timeD;
//    Picker timeF;
    TextField dateV;
    //Picker dateV;
    TextField timeD;
    TextField timeF;
    Button reserver;
    Date hd;
    Date hf;
    String nom;
    String prenom;
    String description;
    public AjoutVisite(){
        GuideService gs = new GuideService();
        VisiteService vs = new VisiteService();
        
        Integer[] nbrPers={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//        String [] hours={"8","9","10","11","12","13","14","15","16","17","18"};
//        String [] mins={"00","05","10","15","20","25","30","35","40","45","50","55","60"};
//        ComboBox<String> heures = new ComboBox<>(hours);
//        ComboBox<String> minutes = new ComboBox<>(mins);
        ComboBox<Integer> nbr = new ComboBox<>(nbrPers);
        ComboBox<String> cb = new ComboBox<>();
        for(Guide g :gs.getListguides2()){
                cb.addItem(g.getNom()+" "+g.getPrenom());
           
            
            
        }
//        dateV = new Picker();
        f = new Form("Reservation de visite");
        Label da = new Label("Choisissez la date");
        Label tf = new Label("Choisissez l'heure de fin");
        Label td = new Label("Choisissez l'heure de début");
        Label nb = new Label("Choisissez le nombre de personnes(1-20)");
        Label gu = new Label("Choisissez votre guide");
        dateV=new TextField("", "yyyy-mm-dd");
//        dateV.setType(Display.PICKER_TYPE_DATE);
       
//        dateV.setFormatter(tempss);
        timeD=new TextField("","hh:mm");
//        timeD.setType(Display.PICKER_TYPE_TIME);
        
        timeF=new TextField("","hh:mm");
//        timeF.setType(Display.PICKER_TYPE_TIME);
        
        
        
        reserver=new Button("Reserver");
        f.add(da);
        f.add(dateV);
        f.add(td);
//        f.add(heures);
//        f.add(minutes);
        f.add(timeD);
        f.add(tf);
        f.add(timeF);
//        f.add(heures);
//        f.add(minutes);
        f.add(nb);
        f.add(nbr);
        f.add(gu);
        f.add(cb);
        f.add(reserver);
        
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
       
        reserver.addActionListener((evt) -> {
            
            
                
                SimpleDateFormat heure=new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                
                
                
                if(dateV.getText().equals("")){
                    Dialog.show("ERREUR", "LE CHAMP DATE NE PEUT PAS ETRE VIDE", "OK", "ANNULER");
                }
           

         
                
                if(timeD.getText().equals("")){
                    Dialog.show("ERREUR", "LE CHAMP HEURE DEBUT NE PEUT PAS ETRE VIDE", "OK", "ANNULER");
                }
                if(timeF.getText().equals("")){
                    Dialog.show("ERREUR", "LE CHAMP HEURE FIN NE PEUT PAS ETRE VIDE", "OK", "ANNULER");
                }
                
//            String heu=heure.format(heures);
//            Date hd =new Date(timeD.getDate().getTime());
//            Date hf =new Date(timeF.getDate().getTime());
            String heureD = timeD.getText();
            String heureF = timeF.getText();
            try {
                hd = heure.parse(heureD);
            } catch (com.codename1.l10n.ParseException ex) {
                System.out.println("---------");
            }
            try {
                hf = heure.parse(heureF);
            } catch (com.codename1.l10n.ParseException ex) {
                System.out.println("----------");
            }

//Visite v = new Visite(cb.getSelectedItem().getIdGuide(), nbr.getSelectedItem(),dateV.getDate(),hd,hf);
Visite v = new Visite();


                String date=dateV.getText();
                String heureDeb =timeD.getText();
                String heureFin= timeF.getText();
                int nbrP = nbr.getSelectedItem();
                int idg;
                if(cb.getSelectedItem().equals("Bassem Hamraoui")){
                    idg=13;
                    vs.ajoutVisite(date,heureDeb,heureFin,idg,nbrP);
                }
                if (cb.getSelectedItem().equals("Karim Felhi")){
                    idg=14;
                    vs.ajoutVisite(date,heureDeb,heureFin,idg,nbrP);
                }
                if(cb.getSelectedItem().equals("Mohamed Ali")){
                    idg=15;
                    vs.ajoutVisite(date,heureDeb,heureFin,idg,nbrP);
                }
                if(cb.getSelectedItem().equals("Yesser Rebai")){
                    idg=16;
                    vs.ajoutVisite(date,heureDeb,heureFin,idg,nbrP);
                }



//            v.setHdeb(hd);
//            v.setHfin(hf);
//            v.setNbPers(nbr.getSelectedItem());
//            v.setIdGuide(cb.getSelectedItem().getIdGuide());

              Dialog.show("felicitation", " votre reservation a ete ajoute", "ok", null);



            

                       

                    });

                }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
