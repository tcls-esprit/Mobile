/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ServiceProduit;
import Store.Main;
import com.codename1.components.ImageViewer;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Saidi Khaled
 */
public class StoreHome extends Form {
    
    public StoreHome(Resources theme){
        
        setTitle("#Product List");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        Image dukeImage = Image.createImage(230, 230, 0);
        Graphics g = dukeImage.getGraphics();
        g.drawImage(theme.getImage("flag-round-250.png"), 0, 0, 230, 230);
        g.drawImage(theme.getImage("flag-round-250.png"), 0, 0, 230, 230);
        
        Label duke = new Label(dukeImage);
        Label circle = new Label(theme.getImage("flag-round-250.png"));
        Container dukeImageContainer = LayeredLayout.encloseIn(duke, circle);
        Label name = new Label(Main.MemberNm+" "+Main.MemberLs);
        name.setUIID("DukeName");
        Container dukeContainer = BorderLayout.west(BoxLayout.encloseY(dukeImageContainer, name));
        dukeContainer.setUIID("ProfileContainer");
        getToolbar().addComponentToSideMenu(dukeContainer);
        getToolbar().addCommandToOverflowMenu("My Cart", null, ev->{
                Produit c = new Produit();
                UserCart w = new UserCart(theme,c,0);
                w.show();
                });
        getToolbar().addMaterialCommandToSideMenu("My Wish List", 
                FontImage.MATERIAL_FAVORITE, e -> {
                UserFavor uf = new UserFavor(theme);
                uf.show();
                });
        getToolbar().addMaterialCommandToSideMenu("Log Out", 
                FontImage.MATERIAL_LOCK_OPEN, e -> {
                Login lo = new Login(theme);
                lo.showBack();
                });
        //---------------------------
        ComboBox cb = new ComboBox();
        cb.addItem("Order By Price");
        cb.addItem("Order By Alphabetic");
        add(cb);
        //----------------------------------------------
        ServiceProduit serviceProduit=new ServiceProduit();
        ArrayList<Produit> Produits = serviceProduit.getList2();
        Container gridLay = new Container(new GridLayout(2,2));
        for(Produit c:Produits)
         {  
              gridLay.add(additem(c,theme)); 
         }
        add(gridLay);
        //---------------- 
        cb.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt){
                        if(cb.getSelectedIndex()==0){
                            removeComponent(gridLay);
                            ServiceProduit serviceProduit=new ServiceProduit();
                            ArrayList<Produit> Produits = new ArrayList<>();
                            Produits = serviceProduit.getListHigh();
                            Container gridLay = new Container(new GridLayout(2,2));
                            for(Produit c:Produits)
                            {  
                                 gridLay.add(additem(c,theme)); 
                            }
                            add(gridLay);
                            show();
                        }
                        else if(cb.getSelectedIndex()==1){
                            removeAll();
                            ComboBox cb = new ComboBox();
                            cb.addItem("Order By Price");
                            cb.addItem("Order By Alphabetic");
                            add(cb);
                            removeComponent(gridLay);
                            ServiceProduit serviceProduit=new ServiceProduit();
                            ArrayList<Produit> Produits = new ArrayList<>();
                            Produits = serviceProduit.getListAlpha();
                            Container gridLay = new Container(new GridLayout(2,2));
                            for(Produit c:Produits)
                            {  
                                 gridLay.add(additem(c,theme)); 
                            } 
                            add(gridLay);
                            show();
                        }
                    }
            });
        
        
    }
    public Container additem(Produit c,Resources theme)
    {
        //Wrapper
        Container c1 =new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        //Product Iamge
        ImageViewer imgCon = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("flag-round-250.png").fill(430,320), false);
        URLImage urlImage = URLImage.createToStorage(placeholder, c.getName(), "http://localhost/CDLC/web/images/products/"+c.getImage());
        imgCon.setImage(urlImage);
        
        //Info Container
        Container c2 =new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        //Product Name
        Label l = new Label(c.getName());
        c2.add(l);
        l.getAllStyles().setAlignment(CENTER);
        
        l.addPointerPressedListener(e->{
            SingleProduct d = new SingleProduct(theme,c,0);
            d.show();
        });
        
        //Product Price
        Label l1 = new Label(Double.toString(c.getPrice())+" DT");
        c2.add(l1);
        l1.getAllStyles().setAlignment(CENTER);
        
        //Styling Container
        imgCon.getAllStyles().setMarginTop(15);
        c1.getAllStyles().setMargin(50, BOTTOM, 25, 25);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        c1.getStyle().setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        c1.getStyle().setBgColor(0x99CCCC);
        c1.getStyle().setBgTransparency(180);
        c2.getAllStyles().setMarginTop(15);
        c2.getStyle().setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        c2.getStyle().setBgColor(0xffffff);
        c2.getStyle().setBgTransparency(205);
        //Setting Container 1 Contents
        c1.add(imgCon);
        c1.add(c2);
        c1.setLeadComponent(c2);
        return c1;
    }

}
