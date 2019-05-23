/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.SessionTheatre;
import Entities.Theatre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AQuel
 */
public class SessionService {
    public ArrayList<SessionTheatre> ListSessionJson(String json  ) {
          
        ArrayList<SessionTheatre> listSession = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> thes = j.parseJSON(new CharArrayReader(json.toCharArray()));            
            List<Map<String, Object>> list = (List<Map<String, Object>>) thes.get("root");   
      //       Map<String, Object> map ;
            for (Map<String, Object> obj : list) {                 
                SessionTheatre a = new SessionTheatre();
                float id = Float.parseFloat(obj.get("idSes").toString());
                a.setId_sess((int) id);
                System.out.println(""+id);
                String db = obj.get("dateDebut").toString();
                System.out.println(db);
                String db1 = obj.get("dateFin").toString();   
                System.out.println(db1);
                 a.setDate_debut(db);
                 a.setDate_fin(db1);
                    
              
                System.out.println(a);
                
                listSession.add(a);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listSession);
        return listSession;

    }
     
      ArrayList<SessionTheatre> listsession = new ArrayList<>(); 
    
    public ArrayList<SessionTheatre> ConvertionList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/Sessions/All");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    SessionService ser = new SessionService();
                listsession = ser.ListSessionJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listsession;
    }
    
    
}
