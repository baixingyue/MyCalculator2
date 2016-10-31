package com.example.dell.mycalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class normalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btn_handdraw=(Button)findViewById(R.id.button_HandDraw);
        btn_handdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_handdraw=new Intent(normalActivity.this,HandDrawActivity.class);
                startActivity(intent_handdraw);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_handdrawing, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent=new Intent(normalActivity.this,unitchangeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(normalActivity.this,HexchangeActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(normalActivity.this,HelpActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                finish();
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(normalActivity.this,DateCaluActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(normalActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.action_settings7:
                Intent intent6=new Intent(normalActivity.this,StateActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.action_settings8:
                Intent intent7=new Intent(normalActivity.this,MainActivity.class);
                startActivity(intent7);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
