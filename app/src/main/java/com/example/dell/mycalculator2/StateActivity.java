package com.example.dell.mycalculator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.lang.String;
//有限状态机实现简单计算
public class StateActivity extends AppCompatActivity {
    private String stringShow = new String(""); // 屏显字符串
    private long x = 0; // 左操作数
    private long y = 0; // 右操作数
    private long res = 0; // 计算结果
    // 操作枚举
    private enum operator {
        add, sub, mul, div;
    }
    private operator op = operator.add; // 当前操作符
    // 状态枚举
    // 本计算器的状态转换状态机见文档
    private enum status {
        stat_0, stat_1, stat_2, stat_3, stat_4, err;
    }
    private status stat = status.stat_0; // 当前状态
    // 错误标志(当前只有除0错误)
    private boolean errorExist = false;
    private TextView textView;// 显示栏
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        // 初始显示
        this.textView = (TextView) this.findViewById(R.id.textView1);
        this.textView.setText("0");
        // 为每个按钮设置onClickListener
        View button1 = this.findViewById(R.id.button1);
        button1.setOnClickListener(this.numOnClick);
        View button2 = this.findViewById(R.id.button2);
        button2.setOnClickListener(this.numOnClick);
        View button3 = this.findViewById(R.id.button3);
        button3.setOnClickListener(this.numOnClick);
        View button4 = this.findViewById(R.id.button4);
        button4.setOnClickListener(this.numOnClick);
        View button5 = this.findViewById(R.id.button5);
        button5.setOnClickListener(this.numOnClick);
        View button6 = this.findViewById(R.id.button6);
        button6.setOnClickListener(this.numOnClick);
        View button7 = this.findViewById(R.id.button7);
        button7.setOnClickListener(this.numOnClick);
        View button8 = this.findViewById(R.id.button8);
        button8.setOnClickListener(this.numOnClick);
        View button9 = this.findViewById(R.id.button9);
        button9.setOnClickListener(this.numOnClick);
        View button0 = this.findViewById(R.id.button0);
        button0.setOnClickListener(this.numOnClick);
        View buttonAdd = this.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this.opOnClick);
        View buttonSub = this.findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener(this.opOnClick);
        View buttonMul = this.findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(this.opOnClick);
        View buttonDiv = this.findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this.opOnClick);
        View buttonClear = this.findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this.clearOnClick);
        View buttonRes = this.findViewById(R.id.buttonRes);
        buttonRes.setOnClickListener(this.resOnClick);
    }
    // 监听数字键的函数对象
    private OnClickListener numOnClick = new OnClickListener() {
        public void onClick(View v) {
            // 按钮临时变量
            Button btnNum = (Button) v;
            switch (stat) {
                case stat_0:
                    // 更新操作数x
                    x = Integer.parseInt((String) btnNum.getText());
                    stringShow = "" + x; // 更新x的显示字符串
                    textView.setText(stringShow); // 显示之
                    // 转到状态1
                    stat = status.stat_1;
                    break;
                case stat_1:
                    // 更新操作数x
                    x *= 10;
                    x += Integer.parseInt((String) btnNum.getText());
                    // 显示
                    stringShow = "" + x;
                    textView.setText(stringShow);
                    // 仍在状态1
                    stat = status.stat_1;
                    break;
                case stat_2:
                    // 更新操作数y
                    y *= 10;
                    y += Integer.parseInt((String) btnNum.getText());
                    // 显示
                    stringShow = "" + y;
                    textView.setText(stringShow);
                    // 仍在状态2
                    stat = status.stat_2;
                    break;
                case stat_3:
                    // 更新操作数y
                    y = Integer.parseInt((String) btnNum.getText());
                    // 先清屏
                    stringShow = "" + y;
                    textView.setText(stringShow);
                    // 转到状态2
                    stat = status.stat_2;
                    break;
                case stat_4:
                    // 更新操作数x
                    x = Integer.parseInt((String) btnNum.getText());
                    stringShow = "" + x; // 更新x字符串
                    textView.setText(stringShow); // 显示之
                    // 转到状态1
                    stat = status.stat_1;
                    break;
                default:
                    // 显示错误提示
                    textView.setText("E");
                    stat = status.err;
                    return;
            }
        }
    };
    // 监听操作符键的函数对象
    private OnClickListener opOnClick = new OnClickListener() {
        public void onClick(View v) {
            // 按钮临时变量
            Button btnNum = (Button) v;
            // 不同状态分别处理
            switch (stat) {
                case stat_0:
                    // 更新操作数x
                    x = 0;
                    // 转到状态3
                    stat = status.stat_3;
                    break;
                case stat_1:
                    // 转到状态3
                    stat = status.stat_3;
                    break;
                case stat_2:
                    // 计算结果
                    res = opNumbers(x, y, op);
                    // 如果有错误
                    if (true == errorExist) {
                        // 显示错误提示
                        textView.setText("E");
                        stat = status.err;
                    } else { // 计算并显示结果
                        stringShow = "" + res;
                        textView.setText(stringShow);
                        return;
                    }
                    // 更新x
                    x = res;
                    // 转到状态3
                    stat = status.stat_3;
                    break;
                case stat_3:
                    // 仍在状态3
                    stat = status.stat_3;
                    break;
                case stat_4:
                    // 将结果更新为操作数x
                    x = res;
                    // 转到状态3
                    stat = status.stat_3;
                    break;
                default:
                    // 显示错误提示
                    textView.setText("E");
                    stat = status.err;
                    return;
            }
            // 更新当前操作符
            String opStr = (String) btnNum.getText();
            if (opStr.equals("+")) {
                op = operator.add;
            } else if (opStr.equals("-")) {
                op = operator.sub;
            } else if (opStr.equals("*")) {
                op = operator.mul;
            } else if (opStr.equals("/")) {
                op = operator.div;
            }
        }
    };
    // 获取计算结果
    private long opNumbers(long x, long y, operator op) {
        switch (op) {
            case add:
                return x + y;
            case sub:
                return x - y;
            case mul:
                return x * y;
            case div:
                if (0 == y) { // 除0错误
                    res = -1;
                    errorExist = true;
                    return res;
                } else {
                    return x / y;
                }
            default:
                return 0;
        }
    }
    // 监听等号键的函数对象
    private OnClickListener resOnClick = new OnClickListener() {
        public void onClick(View v) {
            // 根据当前状态来处理运算结果
            switch (stat) {
                case stat_0:
                    res = 0;
                    break;
                case stat_1:
                    res = x;
                    break;
                case stat_2:
                    // 计算结果
                    res = opNumbers(x, y, op);
                    // 如果有错误
                    if (true == errorExist) {
                        // 显示错误提示
                        textView.setText("E");
                        stat = status.err;
                        return;
                    }
                    break;
                case stat_3:
                    y = x;
                    res = opNumbers(x, y, op);
                    // 如果有错误
                    if (true == errorExist) {
                        // 显示错误提示
                        textView.setText("E");
                        stat = status.err;
                        return;
                    }
                    break;
                case stat_4:
                    break;
                default:
                    // 显示错误提示
                    textView.setText("E");
                    stat = status.err;
                    return;
            }
            // 显示结果
            stringShow = "" + res;
            textView.setText(stringShow);
            // 更新x
            x = res;
            // 都转移到状态4
            stat = status.stat_4;
        }
    };
    // 监听清除键的函数对象
    private OnClickListener clearOnClick = new OnClickListener() {
        public void onClick(View v) {
            // 一律转到状态0
            x = 0;
            y = 0;
            res = 0;
            stringShow = "";
            stat = status.stat_0;
            errorExist = false;
            // 清屏
            textView.setText("0");
        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prefcalu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent=new Intent(StateActivity.this,DateCaluActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings1:
                Intent intent1=new Intent(StateActivity.this,unitchangeActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_settings2:
                Intent intent2=new Intent(StateActivity.this,HexchangeActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_settings3:
                Intent intent3=new Intent(StateActivity.this,HelpActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.action_settings4:
                System.exit(0);
                break;
            case R.id.action_settings5:
                Intent intent4=new Intent(StateActivity.this,MainActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.action_settings6:
                Intent intent5=new Intent(StateActivity.this,prefctcaluActivity.class);
                startActivity(intent5);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
