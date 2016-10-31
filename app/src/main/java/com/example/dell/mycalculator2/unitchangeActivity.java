package com.example.dell.mycalculator2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class unitchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitchange);
        Button btn_length=(Button)findViewById(R.id.button_length);
        btn_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(unitchangeActivity.this,lengthchangeActivity.class);
                startActivity(intent);
            }
        });
        Button btn_temperature=(Button)findViewById(R.id.button_temperature);
        btn_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(unitchangeActivity.this,temperaturechangeActivity.class);
                startActivity(intent);
            }
        });
        Button btn_energy=(Button)findViewById(R.id.button_energy);
        btn_energy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(unitchangeActivity.this,energychangeActivity.class);
                startActivity(intent);
            }
        });
        Button btn_angle=(Button)findViewById(R.id.button_angle);
        btn_angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(unitchangeActivity.this,anglechangeActivity.class);
                startActivity(intent);
            }
        });

    }
    //菜单项切换到计算器和单位换算
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
                Intent intent=new Intent(unitchangeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(unitchangeActivity.this,HexchangeActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(unitchangeActivity.this,HelpActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                finish();
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(unitchangeActivity.this,DateCaluActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(unitchangeActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.action_settings7:
                Intent intent6=new Intent(unitchangeActivity.this,StateActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.action_settings8:
                Intent intent7=new Intent(unitchangeActivity.this,normalActivity.class);
                startActivity(intent7);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
