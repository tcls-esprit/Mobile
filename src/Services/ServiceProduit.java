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
 * @author Saidi Khaled
 */
public class ServiceProduit {
    public ArrayList<Produit> parseListProduitJson(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

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
                //System.out.println(e);
                
                listProduits.add(e);

            }

        } catch (IOException ex) {
        }
        
        //System.out.println(listProduits);
        return listProduits;

    }
    ArrayList<Produit> listTasks = new ArrayList<>();
    
    public ArrayList<Produit> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                //System.out.println(con.getResponseData());
                listTasks = ser.parseListProduitJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Produit> getListAlpha(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/alpha");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                //System.out.println(con.getResponseData());
                listTasks = ser.parseListProduitJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Produit> getListHigh(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/high");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                //System.out.println(con.getResponseData());
                listTasks = ser.parseListProduitJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    Produit single = new Produit();
    public Produit dolikeno(int idp, int idu){
        
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/CDLC/web/app_dev.php/service/like/"+idp+"/"+idu;
        con.setUrl(url); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                //System.out.println(con.getResponseData());
                single = ser.parseProduitJson(new String(con.getResponseData()));
            }
        });
         
         
        NetworkManager.getInstance().addToQueueAndWait(con);
        return single;
    }
    public Produit isLiked(int idp, int idu){
        
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/CDLC/web/app_dev.php/service/likecheck/"+idp+"/"+idu;
        con.setUrl(url); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                //System.out.println(con.getResponseData());
                single = ser.parseProduitJson(new String(con.getResponseData()));
            }
        });
         
         
        NetworkManager.getInstance().addToQueueAndWait(con);
        return single;
    }
     
     private List<Produit> list;
     public Produit parseProduitJson(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Produit e = new Produit();

                
                e.setName(obj.get("name").toString());
                
                //System.out.println(e);
                
                listProduits.add(e);

            }

        } catch (IOException ex) {
        }
        
        //System.out.println(listProduits);
        return listProduits.get(0);

    }
    }

