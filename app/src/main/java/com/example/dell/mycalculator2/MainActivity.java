package com.example.dell.mycalculator2;


import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    //竖屏
    //0-9十个按键
    private Button btn[] = new Button[10];
    //功能键
    private Button
            btn_c,btn_del,btn_div,btn_dot,btn_equal,
            btn_leftBracket,btn_minus,btn_rightBracket
            ,btn_mul,btn_plus;
    //显示器，用于竖屏显示输出结果；
    public TextView input;
    //竖屏输入控制，true为重新输入，false为接着输入
    public boolean vbegin = true;
    //保存竖屏原来的算式样子，为了输出时好看，因计算时算式样子改变
    public String str_old;
    //竖屏变换样子后的算式
    public String str_new;
    //竖屏true表示正确，可以继续输入；false表示有误，输入被锁定
    public boolean tip_lock = true;
    //竖屏判断是否是按“=”之后的输入，true表示输入在“=”之前，false反之
    public boolean equals_flag = true;


    //横屏
    //0-9十个按键
    private Button btn_land[] = new Button[10];
    //功能键
    private Button
            btn_land_c,btn_land_cos,
            btn_land_del,btn_land_div,
            btn_land_dot,btn_land_equal,
            btn_land_exp,btn_land_exroot,
            btn_land_leftBracket,btn_land_log
            ,btn_land_mi,btn_land_minus,btn_land_rightBracket
            ,btn_land_mod,btn_land_mul,btn_land_plus
            ,btn_land_sin,btn_land_tan;
    //显示器，用于显示横屏输出结果；
    public TextView input_land;
    //保存横屏原来的算式样子，为了输出时好看，因计算时算式样子改变
    public String str_land_old;
    //横屏变换样子后的算式
    public String str_land_new;
    //横屏输入控制，true为重新输入，false为接着输入
    public boolean vbegin_land = true;
    //横屏：true表示正确，可以继续输入；false表示有误，输入被锁定
    public boolean tip_land_lock = true;
    //横屏：判断是否是按“=”之后的输入，true表示输入在“=”之前，false反之
    public boolean equals_land_flag = true;


    //全局变量
    //π值：3.14
    public double pi = 4 * Math.atan(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //切换calculator布局
        //设置横屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.calculatorland);
            //结果值
            input_land = (TextView) findViewById(R.id.textView_result_land);
            //数字键
            //数字0的button
            btn_land[0] = (Button) findViewById(R.id.button_land_0);
            //数字pi的button
            Button btn_pi = (Button) findViewById(R.id.button_land_pi);
            //数字1的button
            btn_land[1] = (Button) findViewById(R.id.button_land_1);
            //数字2的button
            btn_land[2] = (Button) findViewById(R.id.button_land_2);
            //数字3的button
            btn_land[3] = (Button) findViewById(R.id.button_land_3);
            //数字4的button
            btn_land[4] = (Button) findViewById(R.id.button_land_4);
            //数字5的button
            btn_land[5] = (Button) findViewById(R.id.button_land_5);
            //数字6的button
            btn_land[6] = (Button) findViewById(R.id.button_land_6);
            //数字7的button
            btn_land[7] = (Button) findViewById(R.id.button_land_7);
            //数字8的button
            btn_land[8] = (Button) findViewById(R.id.button_land_8);
            //数字9的button
            btn_land[9] = (Button) findViewById(R.id.button_land_9);
            //功能键
            //清空功能的button
            btn_land_c = (Button) findViewById(R.id.button_land_c);
            //余弦功能的button
            btn_land_cos = (Button) findViewById(R.id.button_land_cos);
            //删除功能的button
            btn_land_del = (Button) findViewById(R.id.button_land_del);
            //除法功能的button
            btn_land_div = (Button) findViewById(R.id.button_land_div);
            //小数点功能的button
            btn_land_dot = (Button) findViewById(R.id.button_land_dot);
            //等于功能的button
            btn_land_equal = (Button) findViewById(R.id.button_land_equal);
            //平方功能的button
            btn_land_exp = (Button) findViewById(R.id.button_land_exp);
            //开方功能的button
            btn_land_exroot = (Button) findViewById(R.id.button_land_exroot);
            //左括号功能的button
            btn_land_leftBracket = (Button) findViewById(R.id.button_land_leftBracket);
            //计算log函数功能的button
            btn_land_log = (Button) findViewById(R.id.button_land_log);
            //计算幂函数功能的button
            btn_land_mi = (Button) findViewById(R.id.button_land_mi);
            //减法功能的button
            btn_land_minus = (Button) findViewById(R.id.button_land_minus);
            //右括号功能的button
            btn_land_rightBracket = (Button) findViewById(R.id.button_land_rightBracket);
            //计算余数功能的button
            btn_land_mod = (Button) findViewById(R.id.button_land_mod);
            //乘法功能的button
            btn_land_mul = (Button) findViewById(R.id.button_land_mul);
            //加法功能的button
            btn_land_plus = (Button) findViewById(R.id.button_land_plus);
            //计算正弦功能的button
            btn_land_sin = (Button) findViewById(R.id.button_land_sin);
            //计算正切功能的button
            btn_land_tan = (Button) findViewById(R.id.button_land_tan);
            //为数字键绑定监听器
            for (int i = 0; i < 10; i++) {
                btn_land[i].setOnClickListener(actionPerformed_land);
            }
            //为横屏按钮功能键绑定监听器
            btn_pi.setOnClickListener(actionPerformed_land);
            btn_land_c.setOnClickListener(actionPerformed_land);
            btn_land_cos.setOnClickListener(actionPerformed_land);
            btn_land_del.setOnClickListener(actionPerformed_land);
            btn_land_div.setOnClickListener(actionPerformed_land);
            btn_land_dot.setOnClickListener(actionPerformed_land);
            btn_land_equal.setOnClickListener(actionPerformed_land);
            btn_land_exp.setOnClickListener(actionPerformed_land);
            btn_land_exroot.setOnClickListener(actionPerformed_land);
            btn_land_leftBracket.setOnClickListener(actionPerformed_land);
            btn_land_log.setOnClickListener(actionPerformed_land);
            btn_land_mi.setOnClickListener(actionPerformed_land);
            btn_land_minus.setOnClickListener(actionPerformed_land);
            btn_land_mod.setOnClickListener(actionPerformed_land);
            btn_land_mul.setOnClickListener(actionPerformed_land);
            btn_land_plus.setOnClickListener(actionPerformed_land);
            btn_land_rightBracket.setOnClickListener(actionPerformed_land);
            btn_land_sin.setOnClickListener(actionPerformed_land);
            btn_land_tan.setOnClickListener(actionPerformed_land);

        }
        //设置竖屏
        else {
            setContentView(R.layout.calculatorport);
            //结果值
            input = (TextView) findViewById(R.id.textView_result);
            //数字键
            //数字0的button
            btn[0] = (Button) findViewById(R.id.button_0);
            //数字1的button
            btn[1] = (Button) findViewById(R.id.button_1);
            //数字2的button
            btn[2] = (Button) findViewById(R.id.button_2);
            //数字3的button
            btn[3] = (Button) findViewById(R.id.button_3);
            //数字4的button
            btn[4] = (Button) findViewById(R.id.button_4);
            //数字5的button
            btn[5] = (Button) findViewById(R.id.button_5);
            //数字6的button
            btn[6] = (Button) findViewById(R.id.button_6);
            //数字7的button
            btn[7] = (Button) findViewById(R.id.button_7);
            //数字8的button
            btn[8] = (Button) findViewById(R.id.button_8);
            //数字9的button
            btn[9] = (Button) findViewById(R.id.button_9);
            //功能键
            //清空功能的button
            btn_c = (Button) findViewById(R.id.button_c);
            btn_del = (Button) findViewById(R.id.button_del);
            //除法功能的button
            btn_div = (Button) findViewById(R.id.button_div);
            //小数点功能的button
            btn_dot = (Button) findViewById(R.id.button_dot);
            //等于功能的button
           btn_equal = (Button) findViewById(R.id.button_equal);
            //左括号功能的button
           btn_leftBracket = (Button) findViewById(R.id.button_leftBracket);
            //减法功能的button
           btn_minus = (Button) findViewById(R.id.button_minus);
            //右括号功能的button
           btn_rightBracket = (Button) findViewById(R.id.button_rightBracket);
            //乘法功能的button
           btn_mul = (Button) findViewById(R.id.button_mul);
            //加法功能的button
           btn_plus = (Button) findViewById(R.id.button_plus);
            //为数字键绑定监听器
            for (int i = 0; i < 10; i++) {
                btn[i].setOnClickListener(actionPerformed_port);
            }
            btn_c.setOnClickListener(actionPerformed_port);
            btn_del.setOnClickListener(actionPerformed_port);
            btn_div.setOnClickListener(actionPerformed_port);
            btn_dot.setOnClickListener(actionPerformed_port);
            btn_equal.setOnClickListener(actionPerformed_port);
            btn_leftBracket.setOnClickListener(actionPerformed_port);
            btn_mul.setOnClickListener(actionPerformed_port);
            btn_plus.setOnClickListener(actionPerformed_port);
            btn_rightBracket.setOnClickListener(actionPerformed_port);
            btn_minus.setOnClickListener(actionPerformed_port);
        }
    }

    /*键盘命令捕捉*/
    //命令缓存，用于检测输入合法性
    String[] Tipcommand = new String[500];
    //命令缓存，用于检测横屏输入合法性
    String[] Tipcommand_land = new String[500];
    //Tipcommand的指针
    int tip_i = 0;
    //Tipcommand的指针
    int tip_land_i = 0;
    //横屏按钮的监听器
    public View.OnClickListener actionPerformed_land = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取按键上的命令
            String command = ((Button) v).getText().toString();
            //显示器上的字符串
            String str = input_land.getText().toString();
            //检测输入是否合法
            if (equals_land_flag = false && "0123456789÷x²×sin^-cos√π.+tan()%log".indexOf(command) != -1) {
                //检测显示屏上的字符串是否合法
                if (right_land(str)) {
                    if ("+-×÷√^)".indexOf(command) != -1) {
                        for (int i = 0; i < str.length(); i++) {
                            Tipcommand_land[tip_land_i] = String.valueOf(str.charAt(i));
                            tip_land_i++;
                        }
                        vbegin_land = false;
                    }
                } else {
                    input_land.setText("0");
                    vbegin_land = true;
                    tip_land_i = 0;
                    tip_land_lock = true;
                }
                equals_land_flag = true;
            }
            if (tip_i > 0)
                TipChecker_land(Tipcommand_land[tip_land_i - 1], command);
            else if (tip_land_i == 0) {
                TipChecker_land("#", command);
            }
            if ("0123456789÷x²×sin^-cos√π.+tan()%log".indexOf(command) != -1 && tip_land_lock) {
                Tipcommand_land[tip_land_i] = command;
                tip_land_i++;
            }
            //若输入正确，则将信息显示到显示器上
            if ("0123456789÷x²×sin^-cos√π.+tan()%log".indexOf(command) != -1 && tip_land_lock) {
                print_land(command);
            }
            //如果输入的是退格键，并且在按“=”之前
            else if (command.compareTo("DEL") == 0 && equals_land_flag) {
                if (TTO_land(str) == 3) {
                    if (str.length() > 3)
                        input_land.setText(str.substring(0, str.length() - 3));
                    else if (str.length() == 3) {
                        input_land.setText("0");
                        vbegin_land = true;
                        tip_land_i = 0;
                    }
                    //依次删除两个字符
                } else if (TTO_land(str) == 2) {
                    if (str.length() > 2)
                        input_land.setText(str.substring(0, str.length() - 2));
                    else if (str.length() == 2) {
                        input_land.setText("0");
                        vbegin_land = true;
                        tip_land_i = 0;
                    }
                    //依次删除一个字符
                } else if (TTO_land(str) == 1) {
                    //若之前输入的字符串合法，则删除一个字符
                    if (right_land(str)) {
                        if (str.length() > 1)
                            input_land.setText(str.substring(0, str.length() - 1));
                        else if (str.length() == 1) {
                            input_land.setText("0");
                            vbegin_land = true;
                            tip_land_i = 0;
                        }
                        //若之前输入的字符串合法，则删除全部字符
                    } else {
                        input_land.setText("0");
                        vbegin_land = true;
                        tip_land_i = 0;
                    }
                }
                if (input_land.getText().toString().compareTo("-") == 0 || equals_land_flag == false) {
                    input_land.setText("0");
                    vbegin_land = true;
                    tip_land_i = 0;
                }
                tip_land_lock = true;
                if (tip_land_i > 0)
                    tip_land_i--;
                //如果是在按=之后输入退格键
            } else if (command.compareTo("DEL") == 0 && equals_land_flag == false) {
                //将显示器的内容设置为0
                input_land.setText("0");
                vbegin_land = true;
                tip_land_i = 0;
                tip_land_lock = true;
            } else if (command.compareTo("C") == 0) {
                //将显示器内容设置为0
                input_land.setText("0");
                //重新输入标志设置为true
                vbegin_land = true;
                //缓存命令位数清零
                tip_land_i = 0;
                //表名可以继续输入
                tip_land_lock = true;
                //表明输入=之前
                equals_land_flag = true;
            }
            //如果输入的是=号
            else if (command.compareTo("=") == 0 && tip_land_lock && right_land(str) && equals_land_flag) {
                tip_land_i = 0;
                //表明不可以继续输入
                tip_land_lock = false;
                //表明输入=之后
                equals_land_flag = false;
                //保存原来算式的样子
                str_land_old = str;
                //替换算式中的运算符，便于计算
                str = str.replaceAll("sin", "s");
                str = str.replaceAll("cos", "o");
                str = str.replaceAll("tan", "t");
                str = str.replaceAll("log", "g");
                str = str.replaceAll("x²", "²");
                //重新输入标志设置为true
                vbegin_land = true;
                //将-1x转换成-x
                str_land_new = str.replaceAll("-", "-1x");
                //算式结果
                new calc_land().process(str_land_new);
            }
            //表明可以继续输入
            tip_land_lock = true;
        }
    };
    //竖屏监听器
    public View.OnClickListener actionPerformed_port = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取按键上的命令
            String command = ((Button) v).getText().toString();
            //显示器上的字符串
            String str = input.getText().toString();
            //检测输入是否合法
            if (equals_flag = false && "0123456789.()×+-÷".indexOf(command) != -1) {
                //检测显示屏上的字符串是否合法
                if (right_port(str)) {
                    if ("+-×÷)".indexOf(command) != -1) {
                        for (int i = 0; i < str.length(); i++) {
                            Tipcommand[tip_i] = String.valueOf(str.charAt(i));
                            tip_i++;
                        }
                        vbegin = false;
                    }
                } else {
                    input.setText("0");
                    vbegin = true;
                    tip_i = 0;
                    tip_lock = true;
                }
                equals_flag = true;
            }
            if (tip_i > 0)
                TipChecker_port(Tipcommand[tip_i - 1], command);
            else if (tip_i == 0) {
                TipChecker_port("#", command);
            }
            if ("0123456789.()×+-÷".indexOf(command) != -1 && tip_lock) {
                Tipcommand[tip_i] = command;
                tip_i++;
            }
            //若输入正确，则将信息显示到显示器上
            if ("0123456789.()×+-÷".indexOf(command) != -1 && tip_lock) {
                print_port(command);
            }
            //如果输入的是退格键，并且在按“=”之前
            else if (command.compareTo("DEL") == 0 && equals_flag) {
                if (TTO_port(str) == 3) {
                    if (str.length() > 3)
                        input.setText(str.substring(0, str.length() - 3));
                    else if (str.length() == 3) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                    }
                    //依次删除两个字符
                } else if (TTO_port(str) == 2) {
                    if (str.length() > 2)
                        input.setText(str.substring(0, str.length() - 2));
                    else if (str.length() == 2) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                    }
                    //依次删除一个字符
                } else if (TTO_port(str) == 1) {
                    //若之前输入的字符串合法，则删除一个字符
                    if (right_port(str)) {
                        if (str.length() > 1)
                            input.setText(str.substring(0, str.length() - 1));
                        else if (str.length() == 1) {
                            input.setText("0");
                            vbegin = true;
                            tip_i = 0;
                        }
                        //若之前输入的字符串合法，则删除全部字符
                    } else {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                    }
                }
                if (input.getText().toString().compareTo("-") == 0 || equals_flag == false) {
                    input.setText("0");
                    vbegin = true;
                    tip_i = 0;
                }
                tip_lock = true;
                if (tip_i > 0)
                    tip_i--;
                //如果是在按=之后输入退格键
            } else if (command.compareTo("DEL") == 0 && equals_flag == false) {
                //将显示器的内容设置为0
                input.setText("0");
                vbegin = true;
                tip_i = 0;
                tip_lock = true;
            } else if (command.compareTo("C") == 0) {
                //将显示器内容设置为0
                input.setText("0");
                //重新输入标志设置为true
                vbegin = true;
                //缓存命令位数清零
                tip_i = 0;
                //表名可以继续输入
                tip_lock = true;
                //表明输入=之前
                equals_flag = true;
            }
            //如果输入的是=号
            else if (command.compareTo("=") == 0 && tip_lock && right_port(str) && equals_flag) {
                tip_i = 0;
                //表明不可以继续输入
                tip_lock = false;
                //表明输入=之后
                equals_flag = false;
                //保存原来算式的样子
                str_old = str;
//                //替换算式中的运算符，便于计算
//                str=str.replaceAll("sin","s");
//                str=str.replaceAll("cos","o");
//                str=str.replaceAll("tan","t");
//                str=str.replaceAll("log","g");
//                str=str.replaceAll("x²","²");
                //重新输入标志设置为true
                vbegin = true;
                //将-1x转换成-x
                str_new = str.replaceAll("-", "-1x");
                //算式结果
                new calc_port().process(str_new);
            }
            //表明可以继续输入
            tip_lock = true;
        }
    };

    //向横屏input输出字符
    private void print_land(String str) {
//清屏输出
        if (vbegin_land)
            input_land.setText(str);
            //在屏幕原str后增添字符
        else
            input_land.append(str);
        vbegin_land = false;
    }

    //向竖屏input输出字符
    private void print_port(String str) {
//清屏输出
        if (vbegin)
            input.setText(str);
            //在屏幕原str后增添字符
        else
            input.append(str);
        vbegin = false;
    }

    /*横屏*/
    /*判断一个str是否是合法的，返回值为true、false
     *只包含0123456789.()sincostanlogπx²del^√×+-%÷c的是合法的str，返回true
     * 包含除了0123456789.()sincostanlogπx²del^√×+-%÷c以外的字符的str为非法的，返回false
     */
    private boolean right_land(String str) {
        int i = 0;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1'
                    && str.charAt(i) != '2' && str.charAt(i) != '3'
                    && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7'
                    && str.charAt(i) != '8' && str.charAt(i) != '9'
                    && str.charAt(i) != 'π' && str.charAt(i) != '.'
                    && str.charAt(i) != '+' && str.charAt(i) != '-'
                    && str.charAt(i) != '×' && str.charAt(i) != '÷'
                    && str.charAt(i) != '^' && str.charAt(i) != '√'
                    && str.charAt(i) != 's' && str.charAt(i) != 'i'
                    && str.charAt(i) != 'n' && str.charAt(i) != 'c'
                    && str.charAt(i) != 'o' && str.charAt(i) != 't'
                    && str.charAt(i) != 'a' && str.charAt(i) != 'l'
                    && str.charAt(i) != 'π' && str.charAt(i) != 'x'
                    && str.charAt(i) != '%' && str.charAt(i) != '²'
                    && str.charAt(i) != '(' && str.charAt(i) != ')')
                break;
        }
        if (i == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    /*竖屏*/
        /*判断一个str是否是合法的，返回值为true、false
     *只包含0123456789.()del×+-÷c的是合法的str，返回true
     * 包含除了0123456789.()del×+-÷c以外的字符的str为非法的，返回false
     */
    private boolean right_port(String str) {
        int i = 0;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1'
                    && str.charAt(i) != '2' && str.charAt(i) != '3'
                    && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7'
                    && str.charAt(i) != '8' && str.charAt(i) != '9'
                    && str.charAt(i) != '.' && str.charAt(i) != '+'
                    && str.charAt(i) != '-' && str.charAt(i) != '×'
                    && str.charAt(i) != '÷' && str.charAt(i) != '(' && str.charAt(i) != ')')
                break;
        }
        if (i == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    /*横屏
     *检测函数，返回值为3、2、1，表示应当一次删除几个TTO（即Three Two One）为函数名
     * 为del按钮的删除方式提供依据
     * 返回3，表示str尾部为sin、cos、tan、log中的一个，应当一次删除3个
     * 返回2，表示str尾部为x²、ln、n！中的一个，应当删除2个（当然目前的程序中未加入x²这两个功能，保留是为了增加功能时使用）
     * 返回1，表示为除返回3、2外的所有情况，只需删除一个（包含非法字符时要另外考虑，应该清屏）
     */
    private int TTO_land(String str) {
        if ((str.charAt(str.length() - 1) == 'n'
                && str.charAt(str.length() - 2) == 'i'
                && str.charAt(str.length() - 3) == 's')
                || (str.charAt(str.length() - 1) == 's'
                && str.charAt(str.length() - 2) == 'o'
                && str.charAt(str.length() - 3) == 'c')
                || (str.charAt(str.length() - 1) == 'n'
                && str.charAt(str.length() - 2) == 'a'
                && str.charAt(str.length() - 3) == 't')
                || (str.charAt(str.length() - 1) == 'g'
                && str.charAt(str.length() - 2) == 'o'
                && str.charAt(str.length() - 3) == 'l')) {
            return 3;
        } else if ((str.charAt(str.length() - 1) == '²' && str.charAt(str.length() - 2) == 'x')) {
            return 2;
        } else {
            return 1;
        }
    }

    /*竖屏
     *检测函数，返回值为1，表示应当一次删除几个TTO（即Three Two One）为函数名
     * 为DEL按钮的删除方式提供依据
     *
     */
    private int TTO_port(String str) {
        return 1;
    }

    /*竖屏：
     *检测函数，对str进行前后语法检测
     * 编号 字符   其后可以跟随的合法字符
     * 1    （     数字|（|-|.|函数
     * 2     ）    算符|）|√^
     * 3     .     数字|算符|）|√^
     * 4    数字   .|数字|算符|）|√^
     * 5    算符   数字|（|.|函数
     * 小数点前后均可省略，表示0
     * 数字第一位可以为0
     */
    private void TipChecker_port(String tipcommand1, String tipcommand2) {
//Tipcode1表示错误类型，Tipcode2表示名词解释类型
        int Tipcode1 = 0, Tipcode2 = 0;
        //表示命令类型
        int tiptype1 = 0, tiptype2 = 0;
        //括号数
        int bracket = 0;
        //"+-×÷）"不能作为第一位
        if (tipcommand1.compareTo("#") == 0
                && (tipcommand2.compareTo("÷") == 0 ||
                tipcommand2.compareTo("×") == 0 ||
                tipcommand2.compareTo("+") == 0 ||
                tipcommand2.compareTo("-") == 0 ||
                tipcommand2.compareTo(")") == 0)) {
            Tipcode1 = -1;
        }
        //定义存储字符串中最后一位的类型
        else if (tipcommand1.compareTo("#") != 0) {
            if (tipcommand1.compareTo("(") == 0) {
                tiptype1 = 1;
            } else if (tipcommand1.compareTo(")") == 0) {
                tiptype1 = 2;
            } else if (tipcommand1.compareTo(".") == 0) {
                tiptype1 = 3;
            } else if ("0123456789".indexOf(tipcommand1) != -1) {
                tiptype1 = 4;
            } else if ("×+-÷".indexOf(tipcommand1) != -1) {
                tiptype1 = 5;
            }
        }
        //定义欲输入的按键类型
        if (tipcommand2.compareTo("(") == 0) {
            tiptype2 = 1;
        } else if (tipcommand2.compareTo(")") == 0) {
            tiptype2 = 2;
        } else if (tipcommand2.compareTo(".") == 0) {
            tiptype2 = 3;
        } else if ("0123456789".indexOf(tipcommand2) != -1) {
            tiptype2 = 4;
        } else if ("×+-÷".indexOf(tipcommand2) != -1) {
            tiptype2 = 5;
        }
        switch (tiptype1) {
            case 1:
                //左括号后面直接接右括号，“+x÷”（负号“-”不算）
                if (tiptype2 == 2 || (tiptype2 == 5 && tipcommand2.compareTo("-") != 0))
                    Tipcode1 = 1;
                break;
            case 2:
                //右括号后面接左括号、数字、“+-x÷”
                if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4)
                    Tipcode1 = 2;
                break;
            case 3:
                //"."后面接左括号
                if (tiptype2 == 1)
                    Tipcode1 = 3;
                //连续输入两个“.”
                if (tiptype2 == 3)
                    Tipcode1 = 8;
                break;
            case 4:
                //数字后面直接接左括号
                if (tiptype2 == 1)
                    Tipcode1 = 4;
                break;
            case 5:
                //“×+-÷”后面直接接右括号、“×+-÷”
                if (tiptype2 == 2 || tiptype2 == 5)
                    Tipcode1 = 5;
                break;
        }
        //检测小数点的重复性，Tipcode1=0,表明满足前面的规则
        if (Tipcode1 == 0 && tipcommand2.compareTo(".") == 0)

        {
            int tip_point = 0;
            for (int i = 0; i < tip_i; i++) {
                //若之前出现一个小数点，则小数点计数加1
                if (Tipcommand[i].compareTo(".") == 0) {
                    tip_point++;
                }
                //若出现以下几个运算符之一，小数点计数清零
                if (Tipcommand[i].compareTo("×") == 0 ||
                        Tipcommand[i].compareTo("+") == 0 ||
                        Tipcommand[i].compareTo("-") == 0 ||
                        Tipcommand[i].compareTo("÷") == 0) {
                    tip_point = 0;
                }
            }
            tip_point++;
            //若小数点计数大于1，表明小数点重复了
            if (tip_point > 1) {
                Tipcode1 = 8;
            }
        }
        //检测右括号是否匹配
        if (Tipcode1 == 0 && tipcommand2.compareTo(")") == 0)

        {
            int tip_right_bracket = 0;
            for (int i = 0; i < tip_i; i++) {
                //如果出现一个左括号，则计数加1
                if (Tipcommand[i].compareTo("(") == 0) {
                    tip_right_bracket++;
                }
                if (Tipcommand[i].compareTo(")") == 0) {
                    tip_right_bracket--;
                }
            }
            //如果右括号计数=0，表明没有相应的左括号与当前右括号匹配
            if (tip_right_bracket == 0) {
                Tipcode1 = 10;
            }
        }
        //检查输入=的合法性
        if (Tipcode1 == 0 && tipcommand2.compareTo("=") == 0)

        {
            //括号匹配
            int tip_bracket = 0;
            for (int i = 0; i < tip_i; i++) {
                if (Tipcommand[i].compareTo("(") == 0) {
                    tip_bracket++;
                }
                if (Tipcommand[i].compareTo(")") == 0) {
                    tip_bracket--;
                }
            }
            //若大于0，表明左括号还有未匹配的
            if (tip_bracket > 0) {
                Tipcode1 = 9;
                bracket = tip_bracket;
            } else if (tip_bracket == 0) {
                //若前一个字符是以下之一，表明=不合法
                if ("×÷+-".indexOf(tipcommand1) != -1) {
                    Tipcode1 = 5;
                }
            }
        }
    }




    /*横屏：
     *检测函数，对str进行前后语法检测
     * 为Tip的提示方式提供依据，与TipShow（）配合使用
     * 编号 字符   其后可以跟随的合法字符
     * 1    （     数字|（|-|.|函数
     * 2     ）    算符|）|√^
     * 3     .     数字|算符|）|√^
     * 4    数字   .|数字|算符|）|√^
     * 5    算符   数字|（|.|函数
     * 6    √^    (|.|数字
     * 7    函数   数字|（|.
     *
     * 小数点前后均可省略，表示0
     * 数字第一位可以为0
     */
    private void TipChecker_land(String tipcommand1, String tipcommand2) {
//Tipcode1表示错误类型，Tipcode2表示名词解释类型
        int Tipcode1=0,Tipcode2=0;
        //表示命令类型
        int tiptype1=0,tiptype2=0;
        //括号数
        int bracket=0;
        //"+-×÷^)√x²"不能作为第一位
        if(tipcommand1.compareTo("#")==0&&
                (tipcommand2.compareTo("÷")==0
                        ||tipcommand2.compareTo("×")==0
                        ||tipcommand2.compareTo("+")==0
                        ||tipcommand2.compareTo(")")==0
                        ||tipcommand2.compareTo("√")==0
                        ||tipcommand2.compareTo("^")==0
                        ||tipcommand2.compareTo("x²")==0
                        ||tipcommand2.compareTo("%")==0)){
            Tipcode1=-1;
        }
        //定义存储字符串中最后一位的类型
        else if (tipcommand1.compareTo("#")!=0){
            if (tipcommand1.compareTo("(")==0){
                tiptype1=1;
            }
            else if (tipcommand1.compareTo(")")==0){
                tiptype1=2;
            }
            else if (tipcommand1.compareTo(".")==0){
                tiptype1=3;
            }
            else if ("0123456789".indexOf(tipcommand1)!=-1){
                tiptype1=4;
            }
            else if ("×+-÷%x²".indexOf(tipcommand1)!=-1){
                tiptype1=5;
            }
            else if("√^".indexOf(tipcommand1)!=-1){
                tiptype1=6;
            }
            else if("sincostanlog".indexOf(tipcommand1)!=-1){
                tiptype1=7;
            }
            //定义欲输入的按键类型
            if(tipcommand2.compareTo("(")==0){
                tiptype2=1;
            }
            else if (tipcommand2.compareTo(")")==0){
                tiptype2=2;
            }
            else if (tipcommand2.compareTo(".")==0){
                tiptype2=3;
            }
            else if("0123456789".indexOf(tipcommand2)!=-1){
                tiptype2=4;
            }
            else if ("×+-÷%x²".indexOf(tipcommand2)!=-1){
                tiptype2=5;
            }
            else if ("√^".indexOf(tipcommand2)!=-1){
                tiptype2=6;
            }
            else if ("sincostanlog".indexOf(tipcommand2)!=-1){
                tiptype2=7;
            }
            switch (tiptype1){
                case 1:
                    //左括号后面直接接右括号，“+x÷”（负号“-”不算），或者“√^”
                    if(tiptype2==2||(tiptype2==5&&tipcommand2.compareTo("-")!=0)||tiptype2==6)
                        Tipcode1=1;
                    break;
                case 2:
                    //右括号后面接左括号、数字、“+-x÷sincostanlogx²^”
                    if (tiptype2==1||tiptype2==3||tiptype2==4||tiptype2==7)
                        Tipcode1=2;
                    break;
                case 3:
                    //"."后面接左括号或者“sincostan....”
                    if (tiptype2==1||tiptype2==7)
                        Tipcode1=3;
                    //连续输入两个“.”
                    if (tiptype2==3)
                        Tipcode1=8;
                    break;
                case 4:
                    //数字后面直接接左括号或者“sincos...”
                    if(tiptype2==1||tiptype2==7)
                        Tipcode1=4;
                    break;
                case 5:
                    //“×+-÷”后面直接接右括号、“×+-÷√^”
                    if (tiptype2==2||tiptype2==5||tiptype2==6)
                        Tipcode1=5;
                    break;
                case 6:
                    //"√^"后面直接接右括号、“×+-÷√^”以及“sincos...”
                    if (tiptype2==2||tiptype2==5||tiptype2==6||tiptype2==7)
                        Tipcode1=6;
                    break;
                case 7:
                    //"sincos..."后面直接接右括号“×+-÷√^”以及“sincos...”
                    if (tiptype2==2||tiptype2==5||tiptype2==6||tiptype2==7)
                        Tipcode1=7;
                    break;
            }
        }
        //检测小数点的重复性，Tipcode1=0,表明满足前面的规则
        if(Tipcode1==0&&tipcommand2.compareTo(".")==0){
            int tip_point=0;
            for (int i=0;i<tip_i;i++){
                //若之前出现一个小数点，则小数点计数加1
                if (Tipcommand[i].compareTo(".")==0){
                    tip_point++;
                }
                //若出现以下几个运算符之一，小数点计数清零
                if(Tipcommand[i].compareTo("sin")==0||Tipcommand[i].compareTo("cos")==0||
                        Tipcommand[i].compareTo("tan")==0||Tipcommand[i].compareTo("log")==0
                        ||Tipcommand[i].compareTo("×")==0||Tipcommand[i].compareTo("+")==0||
                        Tipcommand[i].compareTo("-")==0||Tipcommand[i].compareTo("÷")==0||
                        Tipcommand[i].compareTo("√")==0||Tipcommand[i].compareTo("^")==0
                        ||Tipcommand[i].compareTo("x²")==0||Tipcommand[i].compareTo("%")==0){
                    tip_point=0;
                }
            }
            tip_point++;
            //若小数点计数大于1，表明小数点重复了
            if(tip_point>1){
                Tipcode1=8;
            }
        }
        //检测右括号是否匹配
        if(Tipcode1==0&&tipcommand2.compareTo(")")==0){
            int tip_right_bracket=0;
            for (int i=0;i<tip_i;i++){
                //如果出现一个左括号，则计数加1
                if(Tipcommand[i].compareTo("(")==0){
                    tip_right_bracket++;
                }
                if (Tipcommand[i].compareTo(")")==0){
                    tip_right_bracket--;
                }
            }
            //如果右括号计数=0，表明没有相应的左括号与当前右括号匹配
            if(tip_right_bracket==0){
                Tipcode1=10;
            }
        }
        //检查输入=的合法性
        if(Tipcode1==0&&tipcommand2.compareTo("=")==0){
            //括号匹配
            int tip_bracket=0;
            for (int i=0;i<tip_i;i++){
                if (Tipcommand[i].compareTo("(")==0){
                    tip_bracket++;
                }
                if (Tipcommand[i].compareTo(")")==0){
                    tip_bracket--;
                }
            }
            //若大于0，表明左括号还有未匹配的
            if (tip_bracket>0){
                Tipcode1=9;
                bracket=tip_bracket;
            }
            else if (tip_bracket==0){
                //若前一个字符是以下之一，表明=号不合法
                if("sincostanlog√^".indexOf(tipcommand1)!=-1){
                    Tipcode1=6;
                }
                //若前一个字符是以下之一，表明=不合法
                if("×÷+-x²%".indexOf(tipcommand1)!=-1){
                    Tipcode1=5;
                }
            }
        }

    }
    /*横屏*/
    /*整个计算核心，只要将表达式的整个字符串传入calc().process()就可以实行计算了
    * 算法包括以下几个部分
    * 1.计算部分     process(String str) 当然，这个是建立在毫无错误的情况下
    * 2.数据格式化  FP(double n)         使数据有相当的精确度
    * 3.阶乘算法    N(double n)           计算结成的函数(没有在按钮中加这个功能，写下为了以后拓展功能用)
            * 4.错误提示    showError(int code,String str)  将错误返回
    */

    public class calc_land  {
        public calc_land() {
        }

        final int MAXLEN = 500;

        /*
         *计算表达式
         * 从左向右扫描，数字入number栈，运算符入operator栈
         * +-基本优先级为1，×÷x²%基本优先级为2，log、cos、sin、tan基本优先级为3，^√基本优先级为4
         * 括号内层运算符比外层统计运算符优先级高4
         * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数进行运算
         * 重复直到当前运算符大于栈顶
         * 扫描完后对剩下的运算符与数字依次计算
         */
        public void process(String str) {
            int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
        /*weightPlus为同一（）下的优先级，weightTemp临时记录优先级的变化
         *topOp为weight[]、operator[]的计数器；topNum为number[]的计数器
         * flag为正负数的计数器，1为正数，-1为负数
         */
            int weight[];//保存operator栈中运算符的优先级，以topOp计数
            double number[];//保存数字，以topNum计数
            char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
            String num;//记录数字，str以+-×÷()sotg^√分段，+-×÷()sotg^√%字符之间的字符串即为数字
            weight = new int[MAXLEN];
            number = new double[MAXLEN];
            operator = new char[MAXLEN];
            String expression = str;
            StringTokenizer expToken = new StringTokenizer(expression, "+-×÷()sotg^√%²");
            int i = 0;
            while (i < expression.length()) {
                ch = expression.charAt(i);
                //判断正负数
                if (i == 0) {
                    if (ch == '-')
                        flag = -1;
                } else if (expression.charAt(i - 1) == '(' && ch == '-')
                    flag = -1;
                //取得数字，并将正负符号转移给数字
                if (ch <= '9' && ch >= '0' || ch == '.' ) {
                    num = expToken.nextToken();
                    ch_gai = ch;
                    Log.e("guojs", ch + "---->" + i);
                    //取得整个数字
                    while (i < expression.length() && (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.' || ch_gai == 'E')) {
                        ch_gai = expression.charAt(i++);
                        Log.e("guojs", "i的值为：" + i);
                    }
                    //将指针退回之前的位置
                    if (i >= expression.length())
                        i = -1;
                    else {
                        i -= 2;
                    }
                    if (num.compareTo(".") == 0)
                        number[topNum++] = 0;
                        //将正负符号转移给数字
                    else {
                        number[topNum++] = Double.parseDouble(num) * flag;
                        flag = 1;
                    }
                }
                //计算运算符的优先级
                if (ch == '(')
                    weightPlus += 4;
                if (ch == ')')
                    weightPlus -= 4;
                if (ch == '-' && flag == 1 || ch == '+' || ch == '×' || ch == '÷' || ch == '²' || ch == 'g' || ch == 't' || ch == 's' || ch == 'o' || ch == '%' || ch == '^' || ch == '√') {
                    switch (ch) {
                        //+-的优先级最低，为1
                        case '+':
                        case '-':
                            weightTemp = 1 + weightPlus;
                            break;
                        //×÷%²的优先级稍高，为2
                        case '×':
                        case '÷':
                        case '%':
                        case '²':
                            weightTemp = 2 + weightPlus;
                            break;
                        //sincostanlog之类的优先级为3
                        case 's':
                        case 'o':
                        case 't':
                        case 'g':
                            weightTemp = 3 + weightPlus;
                            break;
                        //其余优先级为4
                        //case'√':
                        //case'^':
                        default:
                            weightTemp = 4 + weightPlus;
                            break;
                    }
                    //如果当前优先级大于堆栈顶部元素，则直接入栈
                    if (topOp == 0 || weight[topOp - 1] < weightTemp) {
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                        //否则将堆栈中的运算符逐个去除，直到当前堆栈顶部运算符优先级小于当前运算符
                    } else {
                        while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
                            switch (operator[topOp - 1]) {
                                //取出数字数组的相应元素进行运算
                                case '+':
                                    number[topNum - 2] += number[topNum - 1];
                                    break;
                                case '-':
                                    number[topNum - 2] -= number[topNum - 1];
                                    break;
                                case '×':
                                    number[topNum - 2] *= number[topNum - 1];
                                    break;
                                //判断除数为0的情况
                                case '÷':
                                    if (number[topNum - 1] == 0) {
                                        showError(1, str_old);
                                        return;
                                    }
                                    number[topNum - 2] /= number[topNum - 1];
                                    break;
                                case '%':
                                    number[topNum - 2] %= number[topNum - 1];
                                    break;
                                case '²':
                                    number[topNum - 1] *= number[topNum - 1];
                                    topNum++;
                                    break;
                                case '√':
                                    if (number[topNum - 1] == 0 || number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0) {
                                        showError(2, str_old);
                                        return;
                                    }
                                    number[topNum - 2] = Math.pow(number[topNum - 2], 1 / number[topNum - 1]);
                                    break;
                                case '^':
                                    number[topNum - 2] = Math.pow(number[topNum - 2], number[topNum - 1]);
                                    break;
                                //计算三角函数，因为忘记添加弧度和角度转换的按钮，所以就只计算弧度不计算角度
                                //sin
                                case 's':
                                    number[topNum - 1] = Math.sin(number[topNum - 1]);
                                    topNum++;
                                    break;
                                //cos
                                case 'o':
                                    number[topNum - 1] = Math.cos(number[topNum - 1]);
                                    topNum++;
                                    break;
                                //tan
                                case 't':
                                    if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
                                        showError(2, str_old);
                                        return;
                                    }
                                    number[topNum - 1] = Math.tan(number[topNum - 1]);
                                    topNum++;
                                    break;
                                //log
                                case 'g':
                                    if (number[topNum - 1] <= 0) {
                                        showError(2, str_old);
                                        return;
                                    }
                                    number[topNum - 1] = Math.log10(number[topNum - 1]);
                                    topNum++;
                                    break;
                            }
                            //继续取堆栈的下一个元素进行判断
                            topNum--;
                            topOp--;
                        }
                        //将运算符压入堆栈
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                    }

                }
                i++;
            }
            //依次取出堆栈的运算符进行运算
            while (topOp > 0) {
                //+-x%直接将数组的后两位数取出运算
                switch (operator[topOp - 1]) {
                    case '+':
                        number[topNum - 2] += number[topNum - 1];
                        break;
                    case '-':
                        number[topNum - 2] -= number[topNum - 1];
                        break;
                    case '×':
                        number[topNum - 2] *= number[topNum - 1];
                        break;
                    //涉及到除法时要考虑除数不能为0的状况
                    case '÷':
                        if (number[topNum - 1] == 0) {
                            showError(1, str_old);
                            return;
                        }
                        number[topNum - 2] /= number[topNum - 1];
                        break;
                    case '√':
                        if (number[topNum - 1] == 0 || (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
                            showError(2, str_old);
                            return;
                        }
                        number[topNum - 2] = Math.pow(number[topNum - 2], 1 / number[topNum - 1]);
                        break;
                    case '^':
                        number[topNum - 2] = Math.pow(number[topNum - 2], number[topNum - 1]);
                        break;
                    case '%':
                        number[topNum - 2] %= number[topNum - 1];
                        break;
                    case '²':
                        number[topNum - 1] *= number[topNum - 1];
                        topNum++;
                        break;
                    //计算三角函数，因为忘记添加弧度和角度转换的按钮，所以就只计算弧度不计算角度
                    //sin
                    case 's':
                        number[topNum - 1] = Math.sin(number[topNum - 1]);
                        topNum++;
                        break;
                    //cos
                    case 'o':
                        number[topNum - 1] = Math.cos(number[topNum - 1]);
                        topNum++;
                        break;
                    //tan
                    case 't':
                        if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
                            showError(2, str_old);
                            return;
                        }
                        number[topNum - 1] = Math.tan(number[topNum - 1]);
                        topNum++;
                        break;
                    //log
                    case 'g':
                        if (number[topNum - 1] <= 0) {
                            showError(2, str_old);
                            return;
                        }
                        number[topNum - 1] = Math.log10(number[topNum - 1]);
                        topNum++;
                        break;

                }
                topNum--;
                topOp--;

            }
            if (number[0] > 7.3E306) {
                showError(3, str_old);
                return;
            }
            input.setText(String.valueOf(FP(number[0])));

        }
        /*
         *FP=floating point 控制小数位数，达到精度
         * 否则会出现0.6-0.2=0.399999999999999997的状况，用FP即可解决使得数为4
         * 本格式精度为15位
         */
        public double FP(double n) {
        /*两种方法，因为只用一种所以注释掉另一种，因为我觉得注释掉的这种不好用*/
            //NumberFormat format=NumberFormat.getCurrencyInstance();//创建一个格式化类
            //format.setMaximumFractionDigits(18);//设置小数为的格式
            DecimalFormat format=new DecimalFormat("0.##########");
            return Double.parseDouble(format.format(n));//待会修改
        }
        /*阶乘算法，虽然没有在计算器中加这个功能，但是为了以后能拓展，就在此写下算法*/
        public double N(double n) {
            int i=0;
            double sum=1;
            //依次将小于等于n的值相乘
            for (i=1;i<=n;i++){
                sum=sum*i;
            }
            return sum;
        }

        public void showError(int code, String str) {
            String message="";
            switch (code){
                case 1:
                    message="零不能做除数";
                    break;
                case 2:
                    message="函数格式不对";
                    break;
                case 3:
                    message="数值太大超出范围";
                    break;
            }
            input.setText("\""+str+"\""+":"+message);
        }
    }
    /*竖屏*/
    /*整个计算核心，只要将表达式的整个字符串传入calc().process()就可以实行计算了
    * 算法包括以下几个部分
    * 1.计算部分     process(String str) 当然，这个是建立在毫无错误的情况下
    * 2.数据格式化  FP(double n)         使数据有相当的精确度
    * 3.阶乘算法    N(double n)           计算结成的函数(没有在按钮中加这个功能，写下为了以后拓展功能用)
            * 4.错误提示    showError(int code,String str)  将错误返回
    */

    public class calc_port  {
        public calc_port() {
        }

        final int MAXLEN = 500;

        /*
         *计算表达式
         * 从左向右扫描，数字入number栈，运算符入operator栈
         * +-基本优先级为1，×÷基本优先级为2
         * 括号内层运算符比外层统计运算符优先级高4
         * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数进行运算
         * 重复直到当前运算符大于栈顶
         * 扫描完后对剩下的运算符与数字依次计算
         */
        public void process(String str) {
            int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
        /*weightPlus为同一（）下的优先级，weightTemp临时记录优先级的变化
         *topOp为weight[]、operator[]的计数器；topNum为number[]的计数器
         * flag为正负数的计数器，1为正数，-1为负数
         */
            int weight[];//保存operator栈中运算符的优先级，以topOp计数
            double number[];//保存数字，以topNum计数
            char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
            String num;//记录数字，str以+-×÷()分段，+-×÷()字符之间的字符串即为数字
            weight = new int[MAXLEN];
            number = new double[MAXLEN];
            operator = new char[MAXLEN];
            String expression = str;
            StringTokenizer expToken = new StringTokenizer(expression, "+-×÷()");
            int i = 0;
            while (i < expression.length()) {
                ch = expression.charAt(i);
                //判断正负数
                if (i == 0) {
                    if (ch == '-')
                        flag = -1;
                } else if (expression.charAt(i - 1) == '(' && ch == '-')
                    flag = -1;
                //取得数字，并将正负符号转移给数字
                if (ch <= '9' && ch >= '0' || ch == '.') {
                    num = expToken.nextToken();
                    ch_gai = ch;
                    Log.e("guojs", ch + "---->" + i);
                    //取得整个数字
                    while (i < expression.length() && (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.')) {
                        ch_gai = expression.charAt(i++);
                        Log.e("guojs", "i的值为：" + i);
                    }
                    //将指针退回之前的位置
                    if (i >= expression.length())
                        i = -1;
                    else {
                        i -= 2;
                    }
                    if (num.compareTo(".") == 0)
                        number[topNum++] = 0;
                        //将正负符号转移给数字
                    else {
                        number[topNum++] = Double.parseDouble(num) * flag;
                        flag = 1;
                    }
                }
                //计算运算符的优先级
                if (ch == '(')
                    weightPlus += 4;
                if (ch == ')')
                    weightPlus -= 4;
                if (ch == '-' && flag == 1 || ch == '+' || ch == '×' || ch == '÷' ) {
                    switch (ch) {
                        //+-的优先级最低，为1
                        case '+':
                        case '-':
                            weightTemp = 1 + weightPlus;
                            break;
                        //×÷%²的优先级稍高，为2
                        case '×':
                        case '÷':
                            weightTemp = 2 + weightPlus;
                            break;
                        default:
                            weightTemp = 4 + weightPlus;
                            break;
                    }
                    //如果当前优先级大于堆栈顶部元素，则直接入栈
                    if (topOp == 0 || weight[topOp - 1] < weightTemp) {
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                        //否则将堆栈中的运算符逐个去除，直到当前堆栈顶部运算符优先级小于当前运算符
                    } else {
                        while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
                            switch (operator[topOp - 1]) {
                                //取出数字数组的相应元素进行运算
                                case '+':
                                    number[topNum - 2] += number[topNum - 1];
                                    break;
                                case '-':
                                    number[topNum - 2] -= number[topNum - 1];
                                    break;
                                case '×':
                                    number[topNum - 2] *= number[topNum - 1];
                                    break;
                                //判断除数为0的情况
                                case '÷':
                                    if (number[topNum - 1] == 0) {
                                        showError(1, str_old);
                                        return;
                                    }
                                    number[topNum - 2] /= number[topNum - 1];
                                    break;
                            }
                            //继续取堆栈的下一个元素进行判断
                            topNum--;
                            topOp--;
                        }
                        //将运算符压入堆栈
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                    }

                }
                i++;
            }
            //依次取出堆栈的运算符进行运算
            while (topOp > 0) {
                //+-x直接将数组的后两位数取出运算
                switch (operator[topOp - 1]) {
                    case '+':
                        number[topNum - 2] += number[topNum - 1];
                        break;
                    case '-':
                        number[topNum - 2] -= number[topNum - 1];
                        break;
                    case '×':
                        number[topNum - 2] *= number[topNum - 1];
                        break;
                    //涉及到除法时要考虑除数不能为0的状况
                    case '÷':
                        if (number[topNum - 1] == 0) {
                            showError(1, str_old);
                            return;
                        }
                        number[topNum - 2] /= number[topNum - 1];
                        break;

                }
                topNum--;
                topOp--;

            }
            if (number[0] > 7.3E306) {
                showError(3, str_old);
                return;
            }
            input.setText(String.valueOf(FP(number[0])));

        }
        /*
         *FP=floating point 控制小数位数，达到精度
         * 否则会出现0.6-0.2=0.399999999999999997的状况，用FP即可解决使得数为4
         * 本格式精度为15位
         */
        public double FP(double n) {
        /*两种方法，因为只用一种所以注释掉另一种，因为我觉得注释掉的这种不好用*/
            //NumberFormat format=NumberFormat.getCurrencyInstance();//创建一个格式化类
            //format.setMaximumFractionDigits(18);//设置小数为的格式
            DecimalFormat format=new DecimalFormat("0.##########");
            return Double.parseDouble(format.format(n));//待会修改
        }
        public void showError(int code, String str) {
            String message="";
            switch (code){
                case 1:
                    message="零不能做除数";
                    break;
                case 2:
                    message="函数格式不对";
                    break;
                case 3:
                    message="数值太大超出范围";
                    break;
            }
            input.setText("\""+str+"\""+":"+message);
        }
    }
}

