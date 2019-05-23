/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Concert;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author souissi oussama
 */
public class ServiceConcert {
    public Concert getEventById(int idd) {
        Concert ev= new Concert() ;
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/afficheEventMobile");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                float id = Float.parseFloat(obj.get("id").toString());
                if ((int)id == idd)
                {
                float id_user;
                
                float duree = Float.parseFloat(obj.get("duree").toString());
                if (obj.get("id_user")!=null)
                    id_user = Float.parseFloat(obj.get("id_user").toString());
                else
                    id_user = 0;
                float etat = Float.parseFloat(obj.get("etat").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                
                 ev=   new Concert(
                         (String) obj.get("listeArtistes"),
                         (String) obj.get("typeConcert"),
                                (int) id,
                                (String) obj.get("titre"),
                                (float) prix,
                                (String) obj.get("description"),
                                (int) duree,
                                (int) id_user,
                                (int) etat,
                                (String) obj.get("image"),
                                (String) obj.get("typeEvent")                      
                        );
                }
                }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return ev;

    }
}
