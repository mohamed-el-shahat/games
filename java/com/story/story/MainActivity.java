package com.story.story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DB db=new DB (this);
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        listView =findViewById (R.id.list);
        //  Toast.makeText (MainActivity.this,db.DBLOCATION,Toast.LENGTH_SHORT).show ();;
        File database=getApplicationContext ().getDatabasePath (db.DBNAME);
        if (false==database.exists ()){
            db.getReadableDatabase ();
            if (copyDatabase(this)){
                //     Toast.makeText (MainActivity.this,"تم بنجاح")

            }
            else return;

        }
        ArrayList listTitles=db.get_All_Titles();
        ArrayAdapter arrayAdapter =new ArrayAdapter(this ,android.R.layout.simple_list_item_1,listTitles);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                String title =String.valueOf (parent.getItemAtPosition (position));
                Intent intent=new Intent (MainActivity.this,Show_Activity.class);
                intent.putExtra ("title",title);
                startActivity (intent);
            }
        });
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets ().open (db.DBNAME);
            String outFileName = db.DBLOCATION + db.DBNAME;
            OutputStream outputStream = new FileOutputStream (outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read (buff)) > 0) {
                outputStream.write (buff, 0, length);
            }
            outputStream.flush ();
            outputStream.close ();
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
            return false;
        }
    }

}