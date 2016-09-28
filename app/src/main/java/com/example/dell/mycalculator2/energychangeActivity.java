package com.example.dell.mycalculator2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class energychangeActivity extends AppCompatActivity {
    private EditText etn;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energychange);
        Button button=(Button)findViewById(R.id.btn_energyReturn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(energychangeActivity.this, unitchangeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        etn=(EditText)findViewById(R.id.editText5);
        txt=(TextView)findViewById(R.id.textView26);
        Button btn=(Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=etn.getText().toString();
                double b= (Double.valueOf(a))*0.238846;
                txt.setText(String.valueOf(b));
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
                Intent intent=new Intent(energychangeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(energychangeActivity.this,HexchangeActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(energychangeActivity.this,HelpActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                finish();
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(energychangeActivity.this,DateCaluActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(energychangeActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
