/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.SessionEvent;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author souissi oussama
 */
public class ServiceSession {
    public List<SessionEvent> getAllSessionsEvent(Evenement e) throws ParseException {
        List<SessionEvent> listsession = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/sessionEventMobile/"+e.getId());
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                String DateDeb = obj.get("dateDeb").toString();
                String dateStr=DateDeb.substring(1, DateDeb.indexOf("T"));
                String dateStr2 = DateDeb.substring(DateDeb.indexOf("T") );
                DateDeb =dateStr+dateStr2;                
                System.out.println(DateDeb);
                String DateFin = obj.get("dateFin").toString();
                 dateStr=DateFin.substring(1, DateFin.indexOf("T"));
                 dateStr2 = DateFin.substring(DateFin.indexOf("T") );
                DateFin =dateStr+dateStr2;
                
                listsession.add(
                        new SessionEvent( DateDeb, DateFin)
                );
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listsession;

    }
}
