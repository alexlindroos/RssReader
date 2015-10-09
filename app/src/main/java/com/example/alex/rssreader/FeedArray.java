package com.example.alex.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Alex on 29.9.2015.
 */
public class FeedArray extends Activity {

    private List<FeedList> myFeeds = new ArrayList<>();
    private Button b1,b2;
    FeedList feedlist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedlist);

        b1=(Button)findViewById(R.id.refresh_button);
        b2=(Button)findViewById(R.id.addfeed_button);

        final ArrayAdapter<FeedList> adapter = new MyListAdapter();
        final ListView list = (ListView) findViewById(R.id.feedListView);
        list.setAdapter(adapter);

        populateFeedList();
        populateListView();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();
                //When feedlist contains added feed do this "refresh"
                if(feedlist !=null) {
                    adapter.clear();
                    adapter.add(feedlist);
                    adapter.notifyDataSetChanged();
                    list.setAdapter(adapter);
                }
                //When feedlist doesnt contain any data do this "refresh"
                populateFeedList();
                populateListView();


            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final PopupWindow mpopup;
                View popUpView = getLayoutInflater().inflate(R.layout.add_feed, null); // inflating popup layout
                mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true); // Creation of popup
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
                mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0); // Displaying popup

               final EditText title = (EditText) popUpView.findViewById(R.id.add_title);
               final EditText desc = (EditText) popUpView.findViewById(R.id.add_desc);
                final EditText url = (EditText) popUpView.findViewById(R.id.add_url);
                Button btnAdd = (Button) popUpView.findViewById(R.id.button5);
                Button btnCancel = (Button) popUpView.findViewById(R.id.button6);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mpopup.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        feedlist = new FeedList(url.getText().toString(),title.getText().toString(),desc.getText().toString(), R.drawable.hearts);
                        myFeeds.add(feedlist);
                    }

                });
            }
        });


        }

    //Default arraylist add method
     private void populateFeedList(){
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/kotimaa.xml", "Homeland news", "Newest homeland news", R.drawable.finland)); //1
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/ulkomaat.xml", "Global news", "Newest global news", R.drawable.globe)); //2
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/formulat.xml", "Formula", "Newest F1 news", R.drawable.gokart));//3
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/viihde.xml", "Entertaiment", "Newest entertaiment news", R.drawable.coconut)); //4
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/musiikki.xml", "Music", "Newest music news", R.drawable.music)); //5
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/terveys.xml", "Health", "Newest health news", R.drawable.doctor)); //6
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/ruoka.xml", "Food", "Newest food news", R.drawable.food));//7
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/digi.xml", "Technology", "Newest tech news", R.drawable.computer)); //8
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/autot.xml", "Cars", "Newest car news", R.drawable.car)); //9
         myFeeds.add(new FeedList("http://www.iltalehti.fi/rss/leffat.xml", "Movies", "Newest movie news", R.drawable.filmrelel50)); //10

     }

    // Setting adapter to the list

    private void populateListView(){
        ArrayAdapter<FeedList> adapter = new MyListAdapter();
       ListView list = (ListView) findViewById(R.id.feedListView);
       list.setAdapter(adapter);

    }




    // MyListAdapter class

    private class MyListAdapter extends ArrayAdapter<FeedList>{
        public MyListAdapter(){

            super(FeedArray.this, R.layout.item,myFeeds);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent){
           //Make sure you have view to work with ( may have been given null)
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item, parent, false);
            }
            //Find the feed to work with
            FeedList currentFeed = myFeeds.get(position);

            //Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_imageView3);
            imageView.setImageResource(currentFeed.getIconID());

            //Description
            TextView descText = (TextView) itemView.findViewById(R.id.item_textDesc);
            descText.setText(currentFeed.getDescription());

            //Headline
            TextView headText = (TextView) itemView.findViewById(R.id.item_textHead);
            headText.setText(currentFeed.getHeadline());

            //LINK
            final TextView urlText = (TextView) itemView.findViewById(R.id.item_textUrl);
            urlText.setText(currentFeed.getUrl());
            final String finalUrl=currentFeed.getUrl(); // Passing found URL to finalUrl



        //BUTTON

            Button buttonView = (Button) itemView.findViewById(R.id.item_button);
            buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedArray.this, Feeds.class);
                Bundle b = new Bundle();
                b.putString("url", finalUrl);
                intent.putExtras(b);
                startActivity(intent);


            }

            });
                return itemView;
    }
    }
}