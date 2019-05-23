/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
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
 * @author PlusUltra
 */
public class ServiceFavor {
    public ArrayList<Produit> parseListFavorJson(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(tasks);
                       
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            System.out.println(list);

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Produit e = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());
                float price = Float.parseFloat(obj.get("price").toString());
                float qty = Float.parseFloat(obj.get("quantity").toString());
                e.setId((int) id);
                e.setName(obj.get("name").toString());
                e.setPrice((double) price);
                e.setDescription(obj.get("description").toString());
                e.setCategory(obj.get("category").toString());
                e.setQuantity((int) qty);
                e.setImage(obj.get("imageName").toString());
                System.out.println(e);
                
                listProduits.add(e);

            }

        } catch (IOException ex) {
        }
        
        //System.out.println(listProduits);
        return listProduits;

    }
    ArrayList<Produit> listFavors = new ArrayList<>();
    
    public ArrayList<Produit> getList(int idu){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/favor/"+idu);  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFavor ser = new ServiceFavor();
                //System.out.println(con.getResponseData());
                listFavors = ser.parseListFavorJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFavors;
    }
    
}
