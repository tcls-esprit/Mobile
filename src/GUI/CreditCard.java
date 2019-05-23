/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cart;
import Entities.Produit;
import Services.PaymentServices;
import Services.ServiceCart;
import Services.userService;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.stripe.exception.StripeException;
import java.util.ArrayList;

/**
 *
 * @author Saidi Khaled
 */
public class CreditCard extends Form {
    
    CreditCard(Resources theme){
        
    setTitle("Payment Details");
    TableLayout tl = new TableLayout(14, 1); 
    int spanButton = 1;

    tl.setGrowHorizontally(true);
    setLayout(tl);
    
    getToolbar().setBackCommand("", e -> {
                    Produit p = new Produit();
                    UserCart s = new UserCart(theme,p,0);
                    s.showBack();
                });

    TextField firstName = new TextField("", "Card Holder", 20, TextArea.ANY);
    

    TextField num1 = new TextField("", "1234", 4, TextField.NUMERIC);
    TextField num2 = new TextField("", "1234", 4, TextArea.NUMERIC);
    TextField num3 = new TextField("", "1234", 4, TextArea.NUMERIC);
    TextField num4 = new TextField("", "1234", 4, TextArea.NUMERIC);
    TextField num5 = new TextField("", "02", 2, TextArea.NUMERIC);
    TextField num6 = new TextField("", "20", 2, TextArea.NUMERIC);
    TextField num7 = new TextField("", "Ex: 489", 3, TextArea.NUMERIC);
    
    Button submit = new Button("Submit");
    
    TableLayout.Constraint cn = tl.createConstraint();
    cn.setHorizontalSpan(spanButton);
    cn.setHorizontalAlign(Component.RIGHT);
        add("Card Holder").add(firstName).
        add("Credit Card").
        add(GridLayout.encloseIn(4, num1, num2, num3, num4)).
        add("Expiration Date").
        add(GridLayout.encloseIn(4, num5, num6)).
        add("CVC").
        add(GridLayout.encloseIn(2, num7)).
        add(submit);
        
        submit.addActionListener((ActionListener) (ActionEvent evt) -> {
            try {
                ServiceCart serviceCart=new ServiceCart();
                ArrayList<Cart> Items = serviceCart.getList(userService.LoggedUser.getUserId());
                double amount = 0.0;
        
                for(Cart c:Items)
                {
                    amount = amount + c.getTotal();
                }
                if(!(amount == 0.0)){
                    if(firstName.getText().isEmpty()){
                        firstName.setHint("please enter the name on your card!");
                    }
                    else if(num1.getText().isEmpty() || num2.getText().isEmpty() || num3.getText().isEmpty() || num4.getText().isEmpty()){
                        num1.setHint("fill");
                        num2.setHint("this");
                        num3.setHint("please");
                        num4.setHint("!");
                    }
                    else if(num5.getText().isEmpty() && num6.getText().isEmpty()){
                        num5.setHint("fill this");
                        num6.setHint("please!");
                    }
                    else if(num7.getText().isEmpty()){
                        num7.setHint("Provide your cvc!");
                    } else {
                        
                        PaymentServices payout = new PaymentServices();
                        String Token = payout.createToken(firstName.getText(), num1.getText()+num2.getText()+num3.getText()+num4.getText(), num5.getText(), num6.getText(), num7.getText());
                        payout.chargeCreditCard(amount, Token);
                        
                        Items = serviceCart.getList(userService.LoggedUser.getUserId());
                        
                        for(Cart c:Items)
                        {
                            serviceCart.doRemove(c.getId());
                        }
                        setTitle("Payment Success!");
                        add(new Label("Congratulation, your order was successfully paid!"));
                        submit.setText("Enjoy!");
                    }
                }else{
                    submit.setText("Nothing to pay!");
                }
            } catch (StripeException ex) {
                setTitle("Payment Failed");
                add(new Label("Check your Connection please! or Try later"));
            }
            firstName.clear();
            num1.clear();
            num2.clear();
            num3.clear();
            num4.clear();
            num5.clear();
            num6.clear();
            num7.clear();
    });
        
}
}