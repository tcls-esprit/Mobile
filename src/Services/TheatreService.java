/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Theatre;
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
public class TheatreService {
     public ArrayList<Theatre> ListTheatreJson(String json  ) {
          
        ArrayList<Theatre> listTheatre = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> thes = j.parseJSON(new CharArrayReader(json.toCharArray()));            
            List<Map<String, Object>> list = (List<Map<String, Object>>) thes.get("root");   
      //       Map<String, Object> map ;
            for (Map<String, Object> obj : list) {                 
                Theatre a = new Theatre();
                float id = Float.parseFloat(obj.get("idScene").toString());
                a.setCode_scene((int) id);
                a.setTitre_scene(obj.get("titreScene").toString());
                a.setDescription(obj.get("description").toString());
                a.setImage(obj.get("image").toString());
             //  a.setId_acteur(((Double)((Map)map.get("idacteurFk")).get("idActeur")).intValue());
                System.out.println(a);
                
                listTheatre.add(a);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listTheatre);
        return listTheatre;

    }
     
      ArrayList<Theatre> listTheatres = new ArrayList<>(); 
    
    public ArrayList<Theatre> ConvertionList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/Theatres/All");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TheatreService ser = new TheatreService();
                listTheatres = ser.ListTheatreJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTheatres;
    }

    
}
