package com.story.story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Show_Activity extends AppCompatActivity {
    DB db=new DB (this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_show_);
        TextView full_text =findViewById (R.id.textView7);
        Intent intent=getIntent ();
        String title=intent.getStringExtra ("title");
        String full_story=db.get_full_story (title);
        full_text.setText (full_story);
        this.setTitle (title);

    }
}
