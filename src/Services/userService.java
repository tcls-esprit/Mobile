/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Gender;
import Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class userService {

    public userService() {
    }

     public static User LoggedUser;
     int response = 0 ;

        public User parseloggeduser(String json) {

                       User e = new User();


        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                float id = Float.parseFloat(obj.get("id").toString());

                e.setUserId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setBirthDate(new Date((((Double)((Map<String, Object>)(obj.get("ddn"))).get("timestamp")).longValue()*1000)));
                e.setEmail(obj.get("email").toString());
                e.setPassword(obj.get("password").toString());
                e.setNumero(obj.get("tel").toString());
                e.setGender(Gender.valueOf(obj.get("sexe").toString()));
                float cin = Float.parseFloat(obj.get("ci").toString());
                e.setCin((int) cin);
                e.setUsername(obj.get("username").toString());
                if(!obj.get("image").toString().equals("null"))
                e.setImage(obj.get("image").toString());
                System.out.println(e);
                

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return e;

    }
    
        
        public int Inscription(User u) {
        ConnectionRequest con = new ConnectionRequest();
            System.out.println(u.getImage());
        
        String Url = "http://localhost/CDLC/web/app_dev.php/service/inscription/new?"
                + "&ddn=" + u.getBirthDate().toString().substring(0, 10)
                + "&nom=" +u.getNom()
                +"&prenom=" +u.getPrenom()
                +"&email="+u.getEmail()
                +"&password="+u.getPassword()
                +"&username="+u.getUsername()
                +"&tel=" +u.getNumero()
                +"&gender="+u.getGender().toString()
                +"&ci="+u.getCin() 
                +"&image="+u.getImage() ; 

        con.setUrl(Url);
            
//        System.out.println("tt");
          
        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());
            //con.setReadResponseForErrors(true);
            response = con.getResponseCode() ; 
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
            System.out.println(response);
        return response ; 
    }
    
     String str ;
     public boolean Login(String username ,String password)
     {         
            String url = "http://localhost/CDLC/web/app_dev.php/service/Login/";
            ConnectionRequest connectionRequest = new ConnectionRequest();
                              connectionRequest.setPost(true);
                              connectionRequest.setUrl(url + username + "-" + password);

 connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  str = new String(connectionRequest.getResponseData());
                  if(str.length()>10) 
                     LoggedUser = parseloggeduser(str) ; 
                  System.out.println(str);
            }
        });
                  //LoggedUser = parseloggeduser(str)  ; 
                       NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
                       
                       return  str.length()>10 ; 
     }
        
     
     
     public User editUser(User u){
                 ConnectionRequest con = new ConnectionRequest();

        String url = "http://localhost/CDLC/web/app_dev.php/service/seif/editUser/"+u.getUserId()
                   + "?ddn=" + u.getBirthDate().toString().substring(0, 10)
                + "&nom=" +u.getNom()
                +"&prenom=" +u.getPrenom()
                +"&email="+u.getEmail()
                +"&password="+u.getPassword()
                +"&username="+u.getUsername()
                +"&tel=" +u.getNumero()
                +"&sexe="+u.getGender().name()
                +"&ci="+u.getCin()  
                +"&image="+u.getImage() ; 
         System.out.println(u.getImage());

        con.setUrl(url);
            
//        System.out.println("tt");
          
        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());
            //con.setReadResponseForErrors(true);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return u ; 
        
    }
       

     
     public String changeemail(String oldemail,String newemail,String pass) {
        ConnectionRequest con = new ConnectionRequest();
       
        
        String Url = "http://localhost/CDLC/web/app_dev.php/service/badis/changeemail/"
                +LoggedUser.getUserId()
                +"?oldemail="+oldemail
                + "&pass=" +pass
                +"&email=" +newemail ; 

        con.setUrl(Url);
            
//        System.out.println("tt");

        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                    String str = new String(con.getResponseData());
          return str ; 
    }
        
     public void newpassword(String password)
     {
                 ConnectionRequest con = new ConnectionRequest();
            String url = "http://localhost/CDLC/web/app_dev.php/service/Newpass/";
            ConnectionRequest connectionRequest = new ConnectionRequest();
                                          connectionRequest.setPost(true);
                                          System.out.println(LoggedUser.getUserId());
                              connectionRequest.setUrl(url + LoggedUser.getUserId() + "-" + password);
                               con.addResponseListener((l) -> {
                     str = new String(connectionRequest.getResponseData());
                    System.out.println(str);
                       });
                                       NetworkManager.getInstance().addToQueueAndWait(connectionRequest);

     }
     
    public ArrayList<User> parseListUsersJson(String json) {

        ArrayList<User> listusers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User e = new User();

                float id = Float.parseFloat(obj.get("id").toString());


                e.setUserId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setBirthDate(new Date((((Double)((Map<String, Object>)(obj.get("ddn"))).get("timestamp")).longValue()*1000)));
                e.setEmail(obj.get("email").toString());
                e.setPassword(obj.get("password").toString());
                e.setNumero(obj.get("tel").toString());
                e.setGender(Gender.valueOf(obj.get("sexe").toString()));
                float cin = Float.parseFloat(obj.get("ci").toString());
                e.setCin((int) cin);
                e.setUsername(obj.get("username").toString());
                
                System.out.println(e);
                
                listusers.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listusers);
        return listusers;

    }
         ArrayList<User> listusers = new ArrayList<>();
    
    public ArrayList<User> getallusers(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/edit/getUsers");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listusers = parseListUsersJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listusers;
    }
    public boolean usernameexist(String username)
    {             ArrayList<User> listusers = new ArrayList<>();
                    listusers= getallusers() ; 
                    for (User e : listusers)
                    {
                    if (e.getUsername().equals(username))
                        return true ; 
                    }
                    return false ; 
    }
    public boolean emailexist(String email)
    {
        ArrayList<User> listusers = new ArrayList<>();
                    listusers= getallusers() ; 

                    for (User e : listusers)
                    {
                    if (e.getEmail().equals(email))
                        return true ; 
                    }
                    return false ;     }
}

