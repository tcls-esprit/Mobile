/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Acteur;
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
 * @author AQuel
 */
public class ActeurService {
    
     public ArrayList<Acteur> ListActeurJson(String json) {

        ArrayList<Acteur> listActeurs = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> acteurs = j.parseJSON(new CharArrayReader(json.toCharArray()));            
            List<Map<String, Object>> list = (List<Map<String, Object>>) acteurs.get("root");   
            
            for (Map<String, Object> obj : list) {                 
                Acteur a = new Acteur();
                float id = Float.parseFloat(obj.get("idActeur").toString());
                a.setId((int) id);
                a.setNom(obj.get("nom").toString());
                a.setPrenom(obj.get("prenom").toString());
                System.out.println(a);
                
                listActeurs.add(a);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listActeurs);
        return listActeurs;

    }
     
      ArrayList<Acteur> listActeurs = new ArrayList<>(); 
    
    public ArrayList<Acteur> ConvertionList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/Acteurs/All");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ActeurService ser = new ActeurService();
                listActeurs = ser.ListActeurJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listActeurs;
    }
    
    
}
