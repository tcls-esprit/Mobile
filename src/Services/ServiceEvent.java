/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class ServiceEvent {
     public static String dateString;

    public String getDateS() {
        return dateString;
    }
    ;

    private ConnectionRequest connectionRequest;
    ConnectionRequest con = new ConnectionRequest();
    String url = "http://localhost/CDLC/web/app_dev.php/";
    public void ajoutEvent(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://localhost/CDLC/web/app_dev.php/ajoutEventMobile/new?titre="+ ta.getTitre()
                + "&prix=" + ta.getPrix()
                + "&description=" + ta.getDescription()
                + "&duree=" + ta.getDuree()
                + "&etat=" + ta.getEtat()
                + "&image=" + ta.getImage()
                + "&idUser=" + ta.getId_user()
                + "&typeEvent=" + ta.getType_event()
                
                ;
        System.out.println(url);
        con.setUrl(Url);
      
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Dialog.show("Succés", "Demande envoyé", "ok", null);
    }
    public void modifierEvent(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CDLC/web/app_dev.php/modifierEventMobile/modif?id=" + ta.getId()+ "&titre="+ ta.getTitre()
                + "&prix=" + ta.getPrix()
                + "&description=" + ta.getDescription()
                + "&duree=" + ta.getDuree()
                + "&etat=" + ta.getEtat()
                ;
        con.setUrl(Url);
         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Event modifié", "ok", null);

           
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimerEvent(Evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CDLC/web/app_dev.php/supprimerevent/" + e.getId();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Evenement supprimé", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    public List<Evenement> getAllEvents() {
        List<Evenement> listevenement = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/afficheEventMobile");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                
                float id_user;
                float id = Float.parseFloat(obj.get("id").toString());
                float duree = Float.parseFloat(obj.get("duree").toString());
                System.out.println(obj.get("idUser"));
                if (obj.get("idUser")!=null)
                {
                    String s =obj.get("idUser").toString();
                    s=s.substring(4, s.indexOf("."));
                    float f =Float.parseFloat(s);
                    id_user = Math.round(f);
                }   
                else
                    id_user = 0;
                float etat = Float.parseFloat(obj.get("etat").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                listevenement.add(
                        new Evenement(
                                (int) id,
                                (String) obj.get("titre"),
                                (float) prix,
                                (String) obj.get("description"),
                                (int) duree,
                                (int) id_user,
                                (int) etat,
                                (String) obj.get("image"),
                                (String) obj.get("typeEvent")
                        ));
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listevenement;

    }
    
 public Evenement getEventById(int idd) {
        Evenement ev= new Evenement() ;
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
                if (obj.get("idUser")!=null)
                {
                    String s =obj.get("idUser").toString();
                    s=s.substring(4, s.indexOf("."));
                    float f =Float.parseFloat(s);
                    id_user = Math.round(f);
                }   
                else
                    id_user = 0;
                float etat = Float.parseFloat(obj.get("etat").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                
                 ev=   new Evenement(
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
 public List<Evenement> getPopularEvents() {
        List<Evenement> listevenement = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/CDLC/web/app_dev.php/popularEventMobile");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                
                float id_user;
                float id = Float.parseFloat(obj.get("id").toString());
                float duree = Float.parseFloat(obj.get("duree").toString());
                System.out.println(obj.get("idUser"));
                if (obj.get("idUser")!=null)
                {
                    String s =obj.get("idUser").toString();
                    s=s.substring(4, s.indexOf("."));
                    float f =Float.parseFloat(s);
                    id_user = Math.round(f);
                }   
                else
                    id_user = 0;
                float etat = Float.parseFloat(obj.get("etat").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                listevenement.add(
                        new Evenement(
                                (int) id,
                                (String) obj.get("titre"),
                                (float) prix,
                                (String) obj.get("description"),
                                (int) duree,
                                (int) id_user,
                                (int) etat,
                                (String) obj.get("image"),
                                (String) obj.get("typeEvent")
                        ));
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listevenement;

    }
 
    
}
