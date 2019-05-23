/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Film;
import Entities.Filmfav;
import Entities.Rate;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mouna
 */
public class FilmfavService {
    
     public ArrayList<Filmfav> parseListTaskJson(String json) {

        ArrayList<Filmfav> listFilms = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Filmfav e = new Filmfav();
                
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                
                float iduser = Float.parseFloat(obj.get("iduser").toString());
                e.setIduser((int) iduser);
                
                 float filmid = Float.parseFloat(((Map<String, Object>)obj.get("idfilm")).get("id").toString());
                        e.setIdfilm((int) filmid);
                
               
                
                System.out.println(e);                
                listFilms.add(e);
            }

        } catch (IOException ex) {
        }
 
        System.out.println(listFilms);
        return listFilms;

    }
    
    
    ArrayList<Filmfav> listFilms = new ArrayList<>();
    
    public ArrayList<Filmfav> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/allfav");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FilmfavService ser = new FilmfavService();
                listFilms = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFilms;
    }
    
    
    public void Favoris(Filmfav f ) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/CDLC/web/app_dev.php/newfav?iduser=" + 4 +"&idfilm=" + f.getIdfilm();
                // création de l'URL
     
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    public void supprimerfav(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CDLC/web/app_dev.php/deletefav/" + id;
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("SuccÃ©s", "Favoris supprimÃ©e", "ok", null);

//            Affichage a = new Affichage();
//            a.getF().show();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    
    
}
