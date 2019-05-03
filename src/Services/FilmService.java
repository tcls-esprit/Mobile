/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Film;
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
 * @author mouna
 */
public class FilmService {
    
      
    public ArrayList<Film> parseListTaskJsonn(String json) {

        ArrayList<Film> listFilms = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Film e = new Film();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setNomrealisateur(obj.get("nomrealisateur").toString());
                e.setDescription(obj.get("description").toString());
                e.setDuree(obj.get("duree").toString());
                e.setAnneesortie(obj.get("anneesortie").toString());
                e.setCategorie(obj.get("categorie").toString());
                e.setImage(obj.get("image").toString());            
                e.setRate( (double) Double.parseDouble(obj.get("rate").toString())) ;
                e.setVideo(obj.get("video").toString());
                
                System.out.println(e);                
                listFilms.add(e);
            }

        } catch (IOException ex) {
        }
 
        System.out.println(listFilms);
        return listFilms;

    }
    
    
    ArrayList<Film> listFilms = new ArrayList<>();
    public ArrayList<Film> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/P/CDLC/web/app_dev.php/films/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FilmService ser = new FilmService();
                listFilms = ser.parseListTaskJsonn(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFilms;
    }}
