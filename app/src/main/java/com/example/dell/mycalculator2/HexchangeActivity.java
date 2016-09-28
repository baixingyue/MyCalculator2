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
import java.lang.String;

public class HexchangeActivity extends AppCompatActivity {
    private EditText etn;
    private TextView txt;
    private EditText etn2;
    private TextView txt2;
    private EditText etn3;
    private TextView txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexchange);
        //二进制转换成十六进制
        etn=(EditText)findViewById(R.id.editText);
        txt=(TextView)findViewById(R.id.textView11);
        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=etn.getText().toString();
                String b = Integer.toHexString(Integer.valueOf(toD(a, 2)));
                txt.setText(b);
            }
            public String toD(String a, int b) {
                int r = 0;
                for (int i = 0; i < a.length(); i++) {
                    r = (int) (r + formatting(a.substring(i, i + 1))
                            * Math.pow(b, a.length() - i - 1));
                }
                return String.valueOf(r);
            }
            // 将十六进制中的字母转为对应的数字
            public int formatting(String a) {
                int i = 0;
                for (int u = 0; u < 10; u++) {
                    if (a.equals(String.valueOf(u))) {
                        i = u;
                    }
                }
                if (a.equals("a")) {
                    i = 10;
                }
                if (a.equals("b")) {
                    i = 11;
                }
                if (a.equals("c")) {
                    i = 12;
                }
                if (a.equals("d")) {
                    i = 13;
                }
                if (a.equals("e")) {
                    i = 14;
                }
                if (a.equals("f")) {
                    i = 15;
                }
                return i;
            }
        });
        //十进制转换二进制
        etn2=(EditText)findViewById(R.id.editText2);
        txt2=(TextView)findViewById(R.id.textView15);
        Button btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=etn2.getText().toString();
                String b = Integer.toBinaryString(Integer.valueOf(a));
                txt2.setText(b);
            }
        });
        //十进制转换十六进制
        etn3=(EditText)findViewById(R.id.editText3);
        txt3=(TextView)findViewById(R.id.textView19);
        Button btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=etn3.getText().toString();
                String b = Integer.toHexString(Integer.valueOf(a));
                txt3.setText(b);
            }
        });

    }
    //菜单项切换到计算器和单位换算
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
                Intent intent=new Intent(HexchangeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(HexchangeActivity.this,unitchangeActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(HexchangeActivity.this,HelpActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                finish();
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(HexchangeActivity.this,DateCaluActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(HexchangeActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
