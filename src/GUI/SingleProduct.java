/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ServiceCart;
import Services.ServiceProduit;
import Store.Main;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Saidi Khaled
 */
public class SingleProduct extends Form {
    //private FontImage img;
    public SingleProduct(Resources theme, Produit c,int choix) {
        
        setTitle("#Product Details");
        setLayout(BoxLayout.y());
        
        getToolbar().setBackCommand("", e -> {
                    if(choix==0){
                        StoreHome s = new StoreHome(theme);
                        s.showBack();}
                    else{
                        UserFavor uf = new UserFavor(theme);
                        uf.showBack();
                    }
                });
        
        //Product Image
        ImageViewer imgCon = new ImageViewer();
        EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("flag-round-250.png").fill(1100, 850), false);
        URLImage urlImage = URLImage.createToStorage(palceHolder, c.getName(), "http://localhost/CDLC/web/images/products/" + c.getImage());
        imgCon.setImage(urlImage);
        add(imgCon);
        //Name and Fav Icon
        Label name = new Label("Name :");
        name.setUIID("ok");
        Style s = UIManager.getInstance().getComponentStyle("ok");
        
        ServiceProduit ps = new ServiceProduit();
        System.out.println(c.getId());
        Produit x = ps.isLiked(c.getId(), Main.MemberId);
        //FavIcon Full if its liked already
        Label like = new Label("");
        if(x.getName().equalsIgnoreCase("yes")){
        FontImage img = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE,s);
        like.setIcon(img);
        }else{
        FontImage img = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER,s);
        like.setIcon(img); 
        }
        like.getAllStyles().setAlignment(RIGHT);
        add(GridLayout.encloseIn(2, name,like));
        Label l2 = new Label(c.getName());
        l2.getStyle().setFgColor(0xb88273);
        add(l2);
        //Description
        add(new Label("Description :"));
        Label l3 = new Label(c.getDescription());
        l3.getStyle().setFgColor(0xb88273);
        add(l3);
        //Price
        add(new Label("Price :"));
        Label l4 = new Label(Double.toString(c.getPrice())+" DT");
        l4.getStyle().setFgColor(0xb88273);
        add(l4);
        //Quantity Slider
        Slider price = new Slider();
        price.setEditable(true);
        price.setMinValue(1);
        price.setMaxValue(10);
        add(price);
        //Add Button
        Button addToCart = new Button("Add to Cart");
        add(addToCart);
        
        price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addToCart.setText("Add To Cart ");
                addToCart.setText(addToCart.getText()+price.getProgress());
                double amount = c.getPrice() * price.getProgress();
                l4.setText(Double.toString(amount)+" DT");
            }
        });
        
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceCart cartser = new ServiceCart();
                if(price.getProgress() > 1){
                double d = c.getPrice() * price.getProgress();
                float amount = (float)d;
                cartser.doAdd(Main.MemberId,c.getId(),price.getProgress(),amount);}
                else{
                cartser.doAdd(Main.MemberId,c.getId(),1,(float)c.getPrice());
                }
                Dialog.show("Info", "Product added succesfully!","Ok",null);
                
            }
        });
        
        like.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            
            ServiceProduit pss = new ServiceProduit();
            Produit xx = pss.isLiked(c.getId(),Main.MemberId);
            if(xx.getName().equalsIgnoreCase("yes")){
                Style s1 = UIManager.getInstance().getComponentStyle("ok");
                FontImage img1 = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, s1);
                pss.dolikeno(c.getId(),Main.MemberId);
                like.setIcon(img1);
                Dialog.show("Favorites", "Product removed from you favorites", "OK", null);
            }else{
                Style s1 = UIManager.getInstance().getComponentStyle("ok");
                FontImage img1 = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s1);
                pss.dolikeno(c.getId(),Main.MemberId);
                like.setIcon(img1);
                Dialog.show("Favorites", "Product added to your favorites", "OK", null);
            }
        });
        
        getToolbar().addCommandToOverflowMenu("Voir Panier", null, ev->{
                UserCart w = new UserCart(theme,c,0);
                w.show();
                });
        
    }
}
