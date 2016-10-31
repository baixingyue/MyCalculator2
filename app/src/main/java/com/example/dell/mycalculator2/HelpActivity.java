package com.example.dell.mycalculator2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
    //菜单项切换到进制转换和单位换算
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
        switch (id) {
            case R.id.action_settings:
                Intent intent=new Intent(HelpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(HelpActivity.this,unitchangeActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(HelpActivity.this,HexchangeActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                finish();
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(HelpActivity.this,DateCaluActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(HelpActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.action_settings7:
                Intent intent6=new Intent(HelpActivity.this,StateActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.action_settings8:
                Intent intent7=new Intent(HelpActivity.this,normalActivity.class);
                startActivity(intent7);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
