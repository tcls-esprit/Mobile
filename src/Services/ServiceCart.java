/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Cart;
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
public class ServiceCart {
     public ArrayList<Cart> parseListCartJson(String json) {

        ArrayList<Cart> listItems = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String, Object> items = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            List<Map<String, Object>> list = (List<Map<String, Object>>) items.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Cart e = new Cart();

                float id = Float.parseFloat(obj.get("id").toString());
                float price = Float.parseFloat(obj.get("price").toString());
                float qty = Float.parseFloat(obj.get("quantity").toString());
                float total = Float.parseFloat(obj.get("total").toString());
                
                e.setId((int) id);
                e.setName(obj.get("name").toString());
                e.setPrice((double) price);
                e.setQuantity((int) qty);
                e.setTotal((double) total);
                //System.out.println(e);
                
                listItems.add(e);

            }

        } catch (IOException ex) {
        }
        
        //System.out.println(listItems);
        return listItems;

    }
    ArrayList<Cart> listItems = new ArrayList<>();
    
    public ArrayList<Cart> getList(int idu){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/cart/"+idu);  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCart ser = new ServiceCart();
                listItems = ser.parseListCartJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listItems;
    }
    
    public void doAdd(int idu, int prodId, int qty, float price){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/CDLC/web/app_dev.php/service/"+idu+"/"+prodId+"/"+qty+"/"+price;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void doRemove(int id){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/CDLC/web/app_dev.php/service/remove/"+id;
        con.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
