package com.example.alex.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Alex on 25.9.2015.
 */
public class Feeds extends Activity {

    //Instance variables

    TextView title,description;
    Button b1,b2;
    private HandleXML obj;
    //private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Defining buttons and texts

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.desc);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);

        //GETTING URL FROM THE FEEDLIST
        Bundle b = getIntent().getExtras();
        final String finalUrl = b.getString("url");

        fetch();

        //Button1 and Button2 is set to do fetching and showing in webview
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj = new HandleXML(finalUrl); //GIVING FINAL URL TO HANDLEXML
                obj.fetchXML();

                while(obj.parsingComplete);
                title.setText(obj.getTitle());
                description.setText(obj.getDescription()); // GETTING TITLE AND DESCRIPTION
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Feeds.this,Second.class); // New intent for webview
                startActivity(in);
            }
        });


    }
    public void fetch(){

        //GETTING URL FROM THE FEEDLIST
        Bundle b = getIntent().getExtras();
        final String finalUrl = b.getString("url");

        obj = new HandleXML(finalUrl); //GIVING FINAL URL TO HANDLEXML
        obj.fetchXML();

        while(obj.parsingComplete);
        title.setText(obj.getTitle());
        description.setText(obj.getDescription()); // GETTING TITLE AND DESCRIPTION
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
