/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cart;
import Entities.Produit;
import Services.ServiceCart;
import Store.Main;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Saidi Khaled
 */
public class UserCart extends Form {
    double amount = 0;
    
    public UserCart(Resources theme, Produit p, int choice){
        
        setTitle("#Shopping Cart");
        setLayout(BoxLayout.y());
        
        TableLayout tl = new TableLayout(1,4);
        Container h = new Container(tl);
        //Column Name
        Label nm = new Label("Name");
        h.add(tl.createConstraint().widthPercentage(40),nm);
        nm.getAllStyles().setFgColor(0xffffff);
        //Column Quantity
        Label qty = new Label("Quantity");
        h.add(tl.createConstraint().widthPercentage(24),qty);
        qty.getAllStyles().setFgColor(0xffffff);
        //Column SubTotal
        Label subtotal = new Label("SubTotal");
        h.add(tl.createConstraint().widthPercentage(23),subtotal);
        subtotal.getStyle().setFgColor(0xffffff);
        //Column Remove Space
        Label clear = new Label("");
        h.add(tl.createConstraint().widthPercentage(13),clear);
        subtotal.getStyle().setFgColor(0xffffff);
        //Adding Header row
        add(h);
        //Styling the Header Row
        h.getUnselectedStyle().setMarginTop(50);
        h.getUnselectedStyle().setMarginBottom(10);
        Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 2);
        h.getStyle().setBorder(RoundBorder.create().
        rectangle(true).
        color(0x000000).
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        
        //Retrieve Items 
        ServiceCart serviceCart=new ServiceCart();
        ArrayList<Cart> Items = serviceCart.getList(Main.MemberId);
        
        //Adding each item's row
        for(Cart c:Items)
         {  
              add(additem(c,theme,p)); 
              amount = amount + c.getTotal();
         }
        
        //Add Label when user's cart is empty
        if(amount == 0.0){
            add(new Label("Your Card is empty!"));
        }
        
        /* with tablelayout could be less code */
        Container tt = new Container(new TableLayout(2,2));
        Container xt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container xo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label tol = new Label("Total");
        xt.add(tol);
        tt.add(xt);
        xt.getStyle().setBorder(Border.createLineBorder(1));
        xt.getStyle().setBgColor(0xf2f2f2);
        xt.getStyle().setBgTransparency(255);
        xt.getStyle().setPadding(0, 0, 180, 180);
       
        Button pay = new Button("Check Out");
        pay.getUnselectedStyle().setBgColor(0xe0dede);
        pay.getStyle().setBgTransparency(120);
        pay.getStyle().setMarginTop(20);
        add(pay);
        
        Label order = new Label(amount+"$");
        xo.add(order);
        order.getStyle().setFgColor(0xef133b);
        tt.add(xo);
        xo.getStyle().setBorder(Border.createLineBorder(1));
        xo.getStyle().setPadding(0, 0, 160, 160);
        order.getStyle().setPadding(1, 0, 3, 3);
        add(tt);
        tt.getStyle().setMarginTop(36);
        /* could be less end*/
        
    pay.addActionListener((ActionListener) (ActionEvent evt) -> {
        CreditCard card = new CreditCard(theme);
        card.show();
        });
        
    getToolbar().setBackCommand("", e -> {
        System.out.println(p);
                if(p.getId() == 0){
                    StoreHome s = new StoreHome(theme);
                    s.showBack();
                }else{
                    if(choice == 0){
                    SingleProduct s = new SingleProduct(theme,p,0);
                    s.showBack();}
                    else{
                    SingleProduct s = new SingleProduct(theme,p,1);
                    s.showBack(); 
                    }
                    
                }
                });
    }
    
    public Container additem(Cart c,Resources theme, Produit p)
    {
        TableLayout tl = new TableLayout(1,4);
        Container c2 = new Container(tl);
        
        //Column Name
        Label name = new Label(c.getName());
        name.getAllStyles().setFgColor(0x2e776f);
        c2.add(tl.createConstraint().widthPercentage(40),name);
        
        //Column Quantity
        Label qty = new Label("X"+c.getQuantity()+"");
        c2.add(tl.createConstraint().widthPercentage(24),qty);
        
        //Column SubTotal
        Label subtotal = new Label(Double.toString(c.getTotal())+" DT");
        c2.add(tl.createConstraint().widthPercentage(26),subtotal);
        
        //Remove Icon Column
        FontImage img = FontImage.createMaterial(FontImage.MATERIAL_REMOVE_CIRCLE_OUTLINE,subtotal.getStyle());
        Label remove = new Label("",img);
        c2.add(tl.createConstraint().widthPercentage(10),remove);
 
        remove.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (Dialog.show("Confirm", "Do you want to proceed?", "OK", "Cancel")) {
                        ServiceCart serviceCar = new ServiceCart();
                        serviceCar.doRemove(c.getId());
                        //System.out.println(c.getId());
                        UserCart s = new UserCart(theme,p,0);
                        s.show();
                    }
                
            }
        });
        
        //Styling the item row
        c2.getUnselectedStyle().setMarginBottom(10);
        Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 2);
        c2.getStyle().setBorder(RoundBorder.create().
        rectangle(true).
        color(0xffffff).
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));

        return c2;
    }
}
