package com.annsnrlzhr.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
    }

    //alt+enter if the View color is red
    public void submitOrder(View view) {
        EditText text = (EditText)findViewById(R.id.name_field);
        String value = text.getText().toString();
        displayName(value);
        CheckBox checkBoxWhip = (CheckBox) findViewById(R.id.whip);
        boolean hasWhipCream = checkBoxWhip.isChecked();
        int price = calculatePrice(quantity, hasWhipCream);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT, Message(value,price,hasWhipCream));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayPrice(price);



    }
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" +number);
    }
    public void displayPrice(int sum) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(sum));
    }
    public void displayName(String name) {
        TextView messageTextView = (TextView) findViewById(R.id.message);
        messageTextView.setText(Name(name));
    }

    private int calculatePrice(int quantity, boolean whip){
        int topping = 2;
        int baseprice = 5;
        if (whip) {
            baseprice += topping;
        }
            return (quantity*baseprice);
    }
    public void tambah (View view) {
        quantity = quantity +1;
        display(quantity);
    }

    public void kurang (View view) {
        quantity = quantity -1;
        display(quantity);
    }

   private String Name (String name) {
       String message = "Name: " + name;
       return message;
   }

    private String Message (String name, int price, boolean topping){
        String message = "Halo, " + name + "\n";
        if (topping) {
            message += "Topping : Whipped Cream\n";
        }
        message += "Total: Rp. " + price +"\n";
        return message;
    }
}
