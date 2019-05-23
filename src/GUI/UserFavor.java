/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ServiceFavor;
import Store.Main;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.URLImage;
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
public class UserFavor extends Form{
    private Resources res;
    private Object imageMask;
    private int maskWidth;
    private int maskHeight;
    private Object circleMask;
    private int circleMaskWidth;
    private int circleMaskHeight;
    public UserFavor(Resources theme){
        
        setTitle("#Wish List");
        setLayout(new GridLayout(2,2));
        
        Image dukeImage = Image.createImage(230, 230, 0);
        Graphics g = dukeImage.getGraphics();
        g.drawImage(theme.getImage("flag-round-250.png"), 0, 0, 230, 230);
        g.drawImage(theme.getImage("flag-round-250.png"), 0, 0, 230, 230);
        //dukeImage = dukeImage.applyMask(circleMask);
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
                UserCart w = new UserCart(theme,c,1);
                w.show();
                });
        getToolbar().addMaterialCommandToSideMenu("Home", 
                FontImage.MATERIAL_HOME, e -> {
                StoreHome sh = new StoreHome(theme);
                sh.show();
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
        
        ServiceFavor serviceFavor=new ServiceFavor();
        ArrayList<Produit> Produits = serviceFavor.getList(Main.MemberId);
  
        for(Produit c:Produits)
         {  
              add(additem(c,theme)); 
         }
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
            SingleProduct d = new SingleProduct(theme,c,1);
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
