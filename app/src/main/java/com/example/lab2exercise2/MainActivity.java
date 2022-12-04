package com.example.lab2exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Private model data member
    private Coffee coffee;

    //Component members
    private Button increment;
    private Button decrement;
    private Button order;

    private TextView quantity;
    private TextView order_summary;

    private CheckBox hasCream;
    private CheckBox hasChocolate;

    private EditText emailET;
    private EditText subjectET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        //Initialize
        coffee = new Coffee();
        initialize();

        //Setup action listeners
        hasCream.setOnClickListener(creamListener);
        hasChocolate.setOnClickListener(chocolateListener);
        order.setOnClickListener(orderListener);
        increment.setOnClickListener(increaseListener);
        decrement.setOnClickListener(decreaseListener);
    }

    //Get components from view
    public void initialize()
    {
        decrement = (Button) findViewById(R.id.minus_btn);
        increment = (Button) findViewById(R.id.plus_btn);
        order = (Button) findViewById(R.id.order_button);

        quantity = (TextView) findViewById(R.id.coffee_count);
        order_summary = (TextView) findViewById(R.id.order_summary);

        hasCream = (CheckBox) findViewById(R.id.cream);
        hasChocolate = (CheckBox) findViewById(R.id.chocolate);

        emailET = (EditText) findViewById(R.id.email_edit);
        subjectET = (EditText) findViewById(R.id.subject_edit);
    }

    //Increase listener
    private OnClickListener orderListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //Set the text to display the order
            String order = getOrder();
            order_summary.setText(order);

            //Get email and subject strings
            String email = emailET.getText().toString();
            String subject = subjectET.getText().toString();

            //Create a new intent for sending the email
            //This will allow email apps to use the intent
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");

            //Add email addresses
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            //Add email subject
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            //Email body
            intent.putExtra(Intent.EXTRA_TEXT, getOrder());

            //If there exists an application to send the email use it
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    private String getOrder()
    {
        //Local variables to hold the values of cream and chocolate
        String cream;
        String chocolate;

        //Check if the user selected cream
        if (coffee.getHasCream())
            cream = "yes";
        else
            cream = "no";

        //Check if the user selected chocolate
        if (coffee.getHasChocolate())
            chocolate = "yes";
        else
            chocolate = "no";

        //Return the order summary
        return "Add whipped cream? " + cream + "\n" + "Add chocolate? " + chocolate + "\n" + "Quantity " + String.valueOf(coffee.getQuantity()) + "\n\n" + "Price: $" + String.format("%.2f", coffee.getCost()) + "\n" + "THANK YOU!";
    }

    //Increase listener
    private OnClickListener increaseListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            coffee.increaseQuantity();
            quantity.setText(String.valueOf(coffee.getQuantity()));
        }
    };

    //Increase listener
    private OnClickListener decreaseListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            coffee.decreaseQuantity();
            quantity.setText(String.valueOf(coffee.getQuantity()));
        }
    };

    //Check box listener
    private OnClickListener creamListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //If checked, set the value, else reset
            if (((CheckBox) view).isChecked())
                coffee.setHasCream(true);
            else
                coffee.setHasCream(false);
        }
    };

    //Check box listener
    private OnClickListener chocolateListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //If checked, set the value, else reset
            if (((CheckBox) view).isChecked())
                coffee.setHasChocolate(true);
            else
                coffee.setHasChocolate(false);
        }
    };
}