package com.nyeintnyeint.mysql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nametext;
    Button add,delete;
    TextView outputtext;
    MyDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametext=(EditText)findViewById(R.id.name_text);
        add=(Button)findViewById(R.id.add_button);
        delete=(Button)findViewById(R.id.delete_button);
        outputtext=(TextView)findViewById(R.id.output_text);
        handler=new MyDBHandler(this,null,null,1);
        printDatabae();
    }
    public void Add_product(View v)
    {
        String s1=nametext.getText().toString();
        handler.addProduct(s1);
        printDatabae();
    }
    public void Delete_product(View v){
        String s=nametext.getText().toString();
        handler.deleteProduct(s);
        printDatabae();
    }
    public void printDatabae()
    {
        String s=handler.dbtoString();
        outputtext.setText(s);
    }
}
