/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TicketsFilm;
import Entities.TicketsTheatre;
import Entities.Ticketsevent;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
public class TicketsService {
       
    String result ; 
    public void ajoutTicketfilm(TicketsFilm ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/CDLC/web/app_dev.php/service/new/myticketsfilm/?idses=" + ta.getIdsession()+ "&iduser=" + ta.getOwnerId();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    

    public ArrayList<TicketsFilm> parseListTicketsfilmJson(String json) {

        ArrayList<TicketsFilm> listTasks = new ArrayList<>();

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
                TicketsFilm e  = new TicketsFilm();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setIdTicket((int) id);
                float idsession = Float.parseFloat(((Map<String, Object>)obj.get("idSesion")).get("idsession").toString());
                e.setIdsession((int) idsession);
               // e.setIdsession((obj.get('idSesion').get('Idfilm').get('titre').toString());
               float ownerid = Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString());
                e.setOwnerId((int) ownerid);
                System.out.println(e);
                
                listTasks.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listTasks);
        return listTasks;

    }
       public String parseMovieNameJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSesion")).get("idfilm")).get("titre").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
        public String parseMovieDateJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = new 
                Date(((Double)((Map<String, Object>)((Map<String, Object>)obj.get("idSesion")).get("heure")).get("timestamp")).longValue()*1000).toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
               public String parsefilmprixJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name =  
              
                 ((Map<String, Object>)obj.get("idSesion")).get("prix").toString() ;

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
                       public String parseMovieSalleJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSesion")).get("idsalle")).get("label").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
    
    ArrayList<TicketsFilm> ListeTicketsFilm = new ArrayList<>();
    
    public ArrayList<TicketsFilm> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                ListeTicketsFilm = ser.parseListTicketsfilmJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeTicketsFilm;
    }
    public String nomfilm(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseMovieNameJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
        public String datefilm(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseMovieDateJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
            public String prixfilm(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parsefilmprixJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
                public String sallefilm(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseMovieSalleJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
     
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################

       public void ajoutTicketsTheatre(TicketsTheatre ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/CDLC/web/app_dev.php/service/new/myticketstheatre/?idses=" + ta.getIdsession()+ "&iduser=" + ta.getOwnerId();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    

    public ArrayList<TicketsTheatre> parseListTicketstheatreJson(String json) {

        ArrayList<TicketsTheatre> ListTicketstheatre = new ArrayList<>();

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
                TicketsTheatre e  = new TicketsTheatre();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setIdTicket((int) id);
                float idsession = Float.parseFloat(((Map<String, Object>)obj.get("idSession")).get("idSes").toString());
                e.setIdsession((int) idsession);
               // e.setIdsession((obj.get('idSesion').get('Idfilm').get('titre').toString());
               float ownerid = Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString());
                e.setOwnerId((int) ownerid);
                System.out.println(e);
                
                ListTicketstheatre.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(ListTicketstheatre);
        return ListTicketstheatre;

    }
    
    
    ArrayList<TicketsTheatre> ListeTicketstheatre = new ArrayList<>();
    
    public ArrayList<TicketsTheatre> getListTickettheatre(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketstheatre/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                ListeTicketstheatre = ser.parseListTicketstheatreJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeTicketstheatre;
    }
            public String parsetheatreNameJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSession")).get("idsceneFK")).get("titreScene").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
        public String parsetheatreDateJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = new 
                Date(((Double)
                 ((Map<String, Object>)
                 ((Map<String, Object>)obj.get("idSession")).get("DateDebut")).get("timestamp")).longValue()*1000).toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
       public String parsetheatreprixJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name =  
              
                 ((Map<String, Object>)obj.get("idSession")).get("prix").toString() ;

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
                  public String parsetheatreSalleJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSession")).get("idSalle")).get("label").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
        public String nometheatre(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventNameJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
        public String datetheatre(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventDateJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
            public String prixtheatre(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventprixJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
                public String salletheatre(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventSalleJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
      //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################
    //##################################################################################################

       public void ajoutTicketsevent(Ticketsevent ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/CDLC/web/app_dev.php/service/new/myticketsevent/?idses=" + ta.getIdsession()+ "&iduser=" + ta.getOwnerId();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    

    public ArrayList<Ticketsevent> parseListTicketseventJson(String json) {

        ArrayList<Ticketsevent> ListTicketsevent = new ArrayList<>();

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
                Ticketsevent e  = new Ticketsevent();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setIdTicket((int) id);
                float idsession = Float.parseFloat(((Map<String, Object>)obj.get("idSession")).get("idSession").toString());
                e.setIdsession((int) idsession);
               // e.setIdsession((obj.get('idSesion').get('Idfilm').get('titre').toString());
               float ownerid = Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString());
                e.setOwnerId((int) ownerid);
                System.out.println(e);
                
                ListTicketsevent.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(ListTicketsevent);
        return ListTicketsevent;

    }
    
    
    ArrayList<Ticketsevent> ListeTicketsevent = new ArrayList<>();
    
    public ArrayList<Ticketsevent> getListTicketevent(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsevent/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                ListeTicketsevent = ser.parseListTicketseventJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeTicketsevent;
    }
            public String parseeventNameJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSession")).get("idEvent")).get("titre").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
        public String parseeventDateJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = new 
                Date(((Double)
                 ((Map<String, Object>)
                 ((Map<String, Object>)obj.get("idSession")).get("DateDeb")).get("timestamp")).longValue()*1000).toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
                public String parseeventprixJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name =  
                 ((Map<String, Object>)
                 ((Map<String, Object>)obj.get("idSession")).get("idEvent")).get("prix").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
                public String parseeventSalleJson(String json,int idtick) {

                String name="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Ticketsevent e  = new Ticketsevent();
                float id = Float.parseFloat(obj.get("id").toString());
                if (idtick==id)
                 name = ((Map<String, Object>)((Map<String, Object>)obj.get("idSession")).get("idSalle")).get("label").toString();

            }

        } catch (IOException ex) {
        }
        System.out.println(name);
        return name;
    }
   public String nomevent(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventNameJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
        public String dateevent(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventDateJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
            public String prixevent(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventprixJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
                public String salleevent(int id)
    {
            ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CDLC/web/app_dev.php/service/myticketsfilm/"+userService.LoggedUser.getUserId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketsService ser = new TicketsService();
                result = ser.parseeventSalleJson(new String(con.getResponseData()),id);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
}
