package com.example.asus.lrucachepracticewithdatabase;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    ToDoModel models;
    Adapter adapter;
    EditText inp;
    EditText desc;
    ListView list;
    Button butt;
    ArrayAdapter<String> mAdapter;
    List<ToDoModel> inputss;
    LruCache <String, String>mMemoryCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        inp = (EditText)findViewById(R.id.input);
        list = (ListView)findViewById(R.id.lista);
        desc = (EditText)findViewById(R.id.descInput);
        butt = (Button)findViewById(R.id.but);
        adapter = new Adapter(this,R.layout.listviewlayout, new ArrayList<ToDoModel>());
        final int memClass = ((ActivityManager) this.getSystemService(
                Context.ACTIVITY_SERVICE)).getMemoryClass();

        final int cacheSize = 1024 * 1024 * memClass / 8;
        mMemoryCache = new LruCache<String,String>(cacheSize){

        };

        Toast.makeText(this, ""+cacheSize, Toast.LENGTH_LONG).show();
        //mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>());
        list.setAdapter(adapter);

        /*Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        if(res.getCount() > 0){
            while(res.moveToNext()){
                models = new ToDoModel(res.getString(0),res.getString(1));
                adapter.add(models);
                adapter.notifyDataSetChanged();
            }
        }*/


        String d="";
        String titt="";


        if(mMemoryCache.get("anton")!=null && mMemoryCache.get("kf")!=null){
            Toast.makeText(this, "nay sud", Toast.LENGTH_SHORT).show();
             titt = mMemoryCache.get("anton");
             d = mMemoryCache.get("kf");
            models = new ToDoModel(titt,d);
            adapter.add(models);
            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this, "way sud", Toast.LENGTH_LONG).show();
        }

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ti = inp.getText().toString();
                String des = desc.getText().toString();


                //mMemoryCache.put("String",inp.getText().toString());
               // boolean val = myDb.insertData(ti,des);
                mMemoryCache.put("anton",inp.getText().toString());
                mMemoryCache.put("kf",desc.getText().toString());
               /* Cache.getInstance().getLru().put("kf",desc.getText().toString());
                Cache.getInstance().getLru().put("anton",desc.getText().toString());

*/

                String titt = mMemoryCache.get("anton");
                String d = mMemoryCache.get("kf");
                Toast.makeText(MainActivity.this, titt+" "+d, Toast.LENGTH_SHORT).show();

                models = new ToDoModel(inp.getText().toString(),desc.getText().toString());
                adapter.add(models);
                adapter.notifyDataSetChanged();

               /* if(val == true){
                    Toast.makeText(MainActivity.this, "Succesfully Added", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Error Adding data", Toast.LENGTH_SHORT).show();*/

            }
        });
    }
}
