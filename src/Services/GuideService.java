/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import Entities.Guide;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Bilel
 */
public class GuideService {
    

      public ArrayList<Guide> getListGuide(String json)  {
        ArrayList<Guide> listGuides = new ArrayList<>();
                try {

            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> reclamations = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(reclamations);
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamations.get("root");
                        for (Map<String, Object> obj : list) {
                        Guide g = new Guide();
                    float id = Float.parseFloat(obj.get("id").toString());
                                    System.out.println(id);
                            //        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                             //       String date = formater.format(rec.getDateenvoie());
                    g.setIdGuide((int) id);
                    g.setNom(obj.get("nom").toString());
                    g.setPrenom(obj.get("prenom").toString());
                    g.setDescription(obj.get("description").toString());
                   
              //      rec.setDateenvoie((Date)obj.get(date));

                System.out.println(g);
                listGuides.add(g);



                        }                        
                        
                        
                }catch (IOException ex) {
        }
        System.out.println(listGuides);
        return listGuides;
                                          
            }
    
    
    ArrayList<Guide> listAllguides = new ArrayList<>();
   
    public ArrayList<Guide> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/guidesall");  
        con.addResponseListener((NetworkEvent evt) -> {
            GuideService gs = new GuideService();
                            listAllguides=gs.getListGuide(new String(con.getResponseData()));
                            });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAllguides;
    }
    
    
    
    /*Liste des noms des guides*/
     public ArrayList<Guide> getListGuides(String json)  {
        ArrayList<Guide> listGuides = new ArrayList<>();
                try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> guides = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(guides);
            List<Map<String, Object>> list = (List<Map<String, Object>>) guides.get("root");
                        for (Map<String, Object> obj : list) {
                        Guide g = new Guide();
                    float id = Float.parseFloat(obj.get("id").toString());
//                                    System.out.println(id);
                            //        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                             //       String date = formater.format(rec.getDateenvoie());
                    g.setIdGuide((int) id);
                    g.setNom(obj.get("nom").toString());
                    g.setPrenom(obj.get("prenom").toString());
                    System.out.println(g);
                    listGuides.add(g);
            }                        
                }catch (IOException ex) {
        }
        System.out.println(listGuides);
        return listGuides;
            }
    ArrayList<Guide> nomGuides = new ArrayList();
    
    
    public ArrayList<Guide> getListguides2(){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/guidesall");  
        con.addResponseListener((NetworkEvent evt) -> {
            GuideService gs = new GuideService();
                            nomGuides=gs.getListGuides(new String(con.getResponseData()));
                            });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nomGuides;
    }
}
