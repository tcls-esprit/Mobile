/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import static GUI.HomeEvent.ress;
import Services.ServiceEvent;
import Services.userService;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author souissi oussama
 */
public class AjoutEvent {
    Resources res;
    private Resources theme;
    Form form;
    Container f;
    TextField ttitre;
    TextField tdescription;
    TextField tduree;
    TextField image = new TextField();
    TextField tprix;
    Label typeEvent;
    ComboBox <String> type_event;
    Button imgBtn;
    Button btnajout;
    String path;
    int etat =0;
    
    public AjoutEvent()  {
        theme = UIManager.initFirstTheme("/theme_1");
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        

        form = new Form("Ajout d'un nouvel évènement");
        Toolbar tb = form.getToolbar();
        tb.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_FLIP_TO_BACK, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            HomeEvent he = new HomeEvent(ress);
            he.getf().show();
        }
        });
        f = new Container();
        ttitre = new TextField();
        ttitre.setHint("Titre");
        FontImage.setMaterialIcon(ttitre.getHintLabel(), FontImage.MATERIAL_TITLE, 5);
        tdescription = new TextField();
        tdescription.setHint("Description");
        FontImage.setMaterialIcon(tdescription.getHintLabel(), FontImage.MATERIAL_DESCRIPTION, 5);
        tduree = new TextField();
        tduree.setHint("Duree");
        FontImage.setMaterialIcon(tduree.getHintLabel(), FontImage.MATERIAL_TIMER, 5);
        tprix = new TextField();
        tprix.setHint("Prix");
        FontImage.setMaterialIcon(tprix.getHintLabel(), FontImage.MATERIAL_MONEY_OFF, 5);
        typeEvent = new Label();
        typeEvent.setText("type de l'événement");
        FontImage.setMaterialIcon(typeEvent, FontImage.MATERIAL_EVENT, 5);
        type_event= new ComboBox();
        type_event.addItem("seminaire");
        type_event.addItem("conference");
        type_event.addItem("reunion");
        imgBtn = new Button("parcourir");
        FontImage.setMaterialIcon(imgBtn, FontImage.MATERIAL_IMAGE, 5);
        c1.add(typeEvent);
        c1.add(type_event);
        btnajout = new Button("ajouter");
        FontImage.setMaterialIcon(btnajout, FontImage.MATERIAL_ADD, 5);
        f.add(ttitre);
        f.add(tdescription);
        f.add(tduree);
        f.add(imgBtn);
        f.add(tprix);
        f.add(c1);
        f.add(btnajout);
        form.add(f);
        imgBtn.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path);
                        Image img = null;
                        image.setText(path.substring(7));
                        System.out.println(image.getText());
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        btnajout.addActionListener((e) -> {

            System.out.println("owel el add");
            FileUploader fc = new FileUploader("localhost/CDLC/web/EventsImages/");
            try {
                System.out.println("owel el try");
                //String f = fc.upload(image.getText());
                System.out.println("ba3d el upload");
                String nameimg=image.getText().substring(image.getText().lastIndexOf("/")+1);
                System.out.println(f);
                ServiceEvent ser = new ServiceEvent();
                Evenement t = new Evenement(ttitre.getText(),
                        Float.parseFloat(tprix.getText()),
                        tdescription.getText(),
                        Integer.parseInt(tduree.getText()),
                        userService.LoggedUser.getUserId(),
                        0,
                        "ddd.jpg",
                        type_event.getSelectedItem(),
                        "Ev"
                );
                ser.ajoutEvent(t);
                Message m = new Message("Body of message");
            m.getAttachments().put("textAttachmentUri", "text/plain");
            m.getAttachments().put("imageAttachmentUri", "image/png");
            Display.getInstance().sendMessage(new String[]{"souissi.oussama11@gmail.com"}, "APi", m);
                
                HomeEvent he = new HomeEvent(ress);
                he.getf().show();
                

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        
        
    }
    public Form getF() {
        return form;
    }
    
    public void setF(Form f) {
        this.form = f;
    }
    
}
