/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import Entities.Visite;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bilel
 */
public class VisiteService {
    public void ajoutVisite(String dat,String hd,String hf,int nbr,int id){
        Visite v = new Visite();
        ConnectionRequest con = new ConnectionRequest();
//         SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
//         SimpleDateFormat heure=new SimpleDateFormat("HH:mm");
//         String dateString = tempss.format(v.getDateV());
//         String heureD = heure.format(v.getHdeb());
//         String heureF = heure.format(v.getHfin());
         
         
         
         String Url = "http://localhost/CDLC/web/app_dev.php/newvisite?date="+dat
                    +"&hDebut="+hd
                    +"&hFin="+hf
                    +"&prix="+id
                    +"&idGuide="+nbr;
        con.setUrl(Url);
       
        
        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
