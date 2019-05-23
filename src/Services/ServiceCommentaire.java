/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Evenement;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
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
public class ServiceCommentaire {
    public static String dateString;
    public static int nthIndexOf(String text, char needle, int n)
{
    for (int i = 0; i < text.length(); i++)
    {
        if (text.charAt(i) == needle)
        {
            n--;
            if (n == 0)
            {
                return i;
            }
        }
    }
    return -1;
}

    public String getDateS() {
        return dateString;
    }
    ;

    private ConnectionRequest connectionRequest;
    ConnectionRequest con = new ConnectionRequest();
    String url = "http://localhost/CDLC/web/app_dev.php/";
    public void ajoutComm(Commentaire ta) {
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://localhost/CDLC/web/app_dev.php/ajoutCommMobile/new?contenu="+ ta.getContenu()
                + "&idUser=" + ta.getId_user()
                + "&idEvent=" + ta.getId_event()
                
                
                
                               
                ;
        System.out.println(url);
        con.setUrl(Url);
      
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Dialog.show("Succés", "Commentaire ajouté", "ok", null);
    }
        public List<Commentaire> getAllCommentsEvent(Evenement e) {
        List<Commentaire> listCommentaire = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/commEventMobile/"+e.getId());
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                
                float id_user;
                float id_event;
                float id = Float.parseFloat(obj.get("id").toString());
                               id_user = Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString());
             
           
//                System.out.println(id_user);
                
                
                String Date = obj.get("date").toString();
                
                
                listCommentaire.add(
                        new Commentaire(
                                (int)id,
                                (String) obj.get("contenu"),
                                Date,
                                
                                (String) obj.get("owner"),
                                (String) obj.get("image"),
                                (int)id_user
                                
                        ));
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listCommentaire;

    }
        public void supprimerComm(Commentaire e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CDLC/web/app_dev.php/SupprimerCommMobile/" + e.getId();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Commentaire supprimé", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
}
