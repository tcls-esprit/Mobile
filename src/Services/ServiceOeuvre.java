/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Oeuvre;
import Entities.SessionEvent;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
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
public class ServiceOeuvre {
   
    public List<Oeuvre> getAllOeuvreEvent(Evenement e) {
        List<Oeuvre> listOeuvre = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/oeuvreEventMobile/"+e.getId());
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                String DateCreate = obj.get("datecreate").toString();
                DateCreate=DateCreate.substring(1, DateCreate.indexOf("T"));
                float prix = Float.parseFloat(obj.get("prix").toString());
                
                
                listOeuvre.add(
                        new Oeuvre(prix,DateCreate,(String) obj.get("titre") )
                );
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listOeuvre;

    }
}

