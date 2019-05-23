/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Film;
import Entities.Rate;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mouna
 */
public class RateService {
    
    private ConnectionRequest connectionRequest;
    ConnectionRequest con = new ConnectionRequest();
    
    public void ajoutRate(Rate ta ) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/CDLC/web/app_dev.php/newrate?ratee=" + ta.getRatee() +"&idfilm=" + ta.getIdfilm();
                // création de l'URL
     
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public ArrayList<Rate> parseListTaskJsonn(String json) {

        ArrayList<Rate> listFilms = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Rate e = new Rate();
                
                float idrate = Float.parseFloat(obj.get("idrate").toString());
                e.setIdrate((int) idrate);
                
                e.setRatee( (double) Double.parseDouble(obj.get("ratee").toString())) ;
                
                  float filmid = Float.parseFloat(((Map<String, Object>)obj.get("idfilm")).get("id").toString());
                        e.setIdfilm((int) filmid);
                        
                listFilms.add(e);
            }

        } catch (IOException ex) {
        }
 
        System.out.println(listFilms);
        return listFilms;

    }
    
    
    ArrayList<Rate> listFilms = new ArrayList<>();
    public ArrayList<Rate> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/rateaff");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RateService ser = new RateService();
                listFilms = ser.parseListTaskJsonn(new String(con.getResponseData()));
                System.out.println(listFilms);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFilms;
    }
    
}
