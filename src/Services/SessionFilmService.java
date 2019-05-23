/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Filmfav;
import Entities.SessionFilm;
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
public class SessionFilmService {
 
    public ArrayList<SessionFilm> parseListTaskJson(String json) {

        ArrayList<SessionFilm> listFilms = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                SessionFilm e = new SessionFilm();
                
                float idsession = Float.parseFloat(obj.get("idsession").toString());
                e.setIdsession((int) idsession);
                
                e.setDate(obj.get("date").toString());
                
                String datedeb=obj.get("heure").toString();
                String datestr=datedeb.substring(1,datedeb.indexOf("T"));
                String datestr2=datedeb.substring(datedeb.indexOf("T"));
                datedeb =datestr2; 
                e.setHeure(datedeb);
                
                 String datefin=obj.get("heurefin").toString();
                String datestr3=datedeb.substring(1,datefin.indexOf("T"));
                String datestr4=datedeb.substring(datefin.indexOf("T"));
                datefin =datestr3+datestr4;
                e.setHeurefin(datefin);
              //  
        
              float filmid = Float.parseFloat(((Map<String, Object>)obj.get("idfilm")).get("id").toString());
                        e.setIdfilm((int) filmid);
                
              float salleid = Float.parseFloat(((Map<String, Object>)obj.get("idsalle")).get("id").toString());
                        e.setIdsalle((int) salleid);
                
                float idp = Float.parseFloat(obj.get("prix").toString());
                e.setPrix((int) idp);
                
                System.out.println(e);                
                listFilms.add(e);
            }

        } catch (IOException ex) {
        }
 
        System.out.println(listFilms);
        return listFilms;

    }
    
    
    ArrayList<SessionFilm> listFilms = new ArrayList<>();
    
    public ArrayList<SessionFilm> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/sessionfilm/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                SessionFilmService ser = new SessionFilmService();
                listFilms = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFilms;
    }
    
    
}
