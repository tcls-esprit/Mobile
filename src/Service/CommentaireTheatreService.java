/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.CommentaireTheatre;
import Entite.Theatre;
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
public class CommentaireTheatreService {
    
    public void ajoutComment(CommentaireTheatre ch) {
        ConnectionRequest con = new ConnectionRequest(); 
        String Url = "http://localhost/CDLC/web/app_dev.php/Comment/?scene=" + ch.getScene() + "&commentaire=" + ch.getCommentaire();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData()); 
            System.out.println(str); 

        });
        NetworkManager.getInstance().addToQueueAndWait(con);  
    }
    public ArrayList<CommentaireTheatre> ListCommentaireJson(String json  ) {
          
        ArrayList<CommentaireTheatre> listCommentaire = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> thes = j.parseJSON(new CharArrayReader(json.toCharArray()));            
            List<Map<String, Object>> list = (List<Map<String, Object>>) thes.get("root");   
      //      
            for (Map<String, Object> obj : list) {                 
                CommentaireTheatre cth = new CommentaireTheatre();
                float id = Float.parseFloat(obj.get("id").toString());
                cth.setId((int) id);
                cth.setScene(obj.get("scene").toString());
                cth.setCommentaire(obj.get("commentaire").toString());
                 
 
                System.out.println(cth);
                
                listCommentaire.add(cth);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listCommentaire);
        
        return listCommentaire;

    }
     
      ArrayList<CommentaireTheatre> listCommentaire = new ArrayList<>(); 
    
    public ArrayList<CommentaireTheatre> ConvertionList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/Comment/All");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentaireTheatreService ser = new CommentaireTheatreService();
                listCommentaire = ser.ListCommentaireJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommentaire;
    }
    
    public void supprimerComment(String scene)
    {
        ConnectionRequest con = new ConnectionRequest(); 
        String Url = "http://localhost/CDLC/web/app_dev.php/Comment/Supprimer/"+ scene;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData()); 
            System.out.println(str); 

        });
        NetworkManager.getInstance().addToQueueAndWait(con);  
    
    }
    
}
