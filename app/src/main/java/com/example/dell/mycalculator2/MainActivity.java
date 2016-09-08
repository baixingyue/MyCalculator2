package com.example.dell.mycalculator2;


import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0-9十个按键
    private Button btn[] = new Button[10];
    //显示器，用于显示输出结果；
    public TextView input;
    //保存原来的算式样子，为了输出时好看，因计算时算式样子改变
    public String str_old;
    //变换样子后的算式
    public String str_new;
    //输入控制，true为重新输入，false为接着输入
    public boolean vbegin = true;
    //π值：3.14
    public double pi = 4 * Math.atan(1);
    //true表示正确，可以继续输入；false表示有误，输入被锁定
    public boolean tip_lock = true;
    //判断是否是按“=”之后的输入，true表示输入在“=”之前，false反之
    public boolean equals_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //切换calculator布局
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
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
            Button btn_c = (Button) findViewById(R.id.button_c);
            Button btn_del = (Button) findViewById(R.id.button_del);
            //除法功能的button
            Button btn_div = (Button) findViewById(R.id.button_div);
            //小数点功能的button
            Button btn_dot = (Button) findViewById(R.id.button_dot);
            //等于功能的button
            Button btn_equal = (Button) findViewById(R.id.button_equal);
            //左括号功能的button
            Button btn_leftBracket = (Button) findViewById(R.id.button_leftBracket);
            //减法功能的button
            Button btn_minus = (Button) findViewById(R.id.button_minus);
            //右括号功能的button
            Button btn_rightBracket = (Button) findViewById(R.id.button_rightBracket);
            //乘法功能的button
            Button btn_mul = (Button) findViewById(R.id.button_mul);
            //加法功能的button
            Button btn_plus = (Button) findViewById(R.id.button_plus);
            //为数字键绑定监听器
            for (int i = 0; i < 10; i++) {
                btn[i].setOnClickListener(actionPerformed);
            }
            btn_c.setOnClickListener(actionPerformed);
            btn_del.setOnClickListener(actionPerformed);
            btn_div.setOnClickListener(actionPerformed);
            btn_dot.setOnClickListener(actionPerformed);
            btn_equal.setOnClickListener(actionPerformed);
            btn_leftBracket.setOnClickListener(actionPerformed);
            btn_mul.setOnClickListener(actionPerformed);
            btn_plus.setOnClickListener(actionPerformed);
            btn_rightBracket.setOnClickListener(actionPerformed);
            btn_minus.setOnClickListener(actionPerformed);
        }
        else{
            setContentView(R.layout.calculatorland);
            //结果值
            input = (TextView) findViewById(R.id.textView_result);
            //数字键
            //数字0的button
            btn[0] = (Button) findViewById(R.id.button_0);
            //数字pi的button
            Button btn_pi = (Button) findViewById(R.id.button_pi);
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
            Button btn_c = (Button) findViewById(R.id.button_c);
            //余弦功能的button
            Button btn_cos = (Button) findViewById(R.id.button_cos);
            //删除功能的button
            Button btn_del = (Button) findViewById(R.id.button_del);
            //除法功能的button
            Button btn_div = (Button) findViewById(R.id.button_div);
            //小数点功能的button
            Button btn_dot = (Button) findViewById(R.id.button_dot);
            //等于功能的button
            Button btn_equal = (Button) findViewById(R.id.button_equal);
            //平方功能的button
            Button btn_exp = (Button) findViewById(R.id.button_exp);
            //开方功能的button
            Button btn_exroot = (Button) findViewById(R.id.button_exroot);
            //左括号功能的button
            Button btn_leftBracket = (Button) findViewById(R.id.button_leftBracket);
            //计算log函数功能的button
            Button btn_log = (Button) findViewById(R.id.button_log);
            //计算幂函数功能的button
            Button btn_mi = (Button) findViewById(R.id.button_mi);
            //减法功能的button
            Button btn_minus = (Button) findViewById(R.id.button_minus);
            //右括号功能的button
            Button btn_rightBracket = (Button) findViewById(R.id.button_rightBracket);
            //计算余数功能的button
            Button btn_mod = (Button) findViewById(R.id.button_mod);
            //乘法功能的button
            Button btn_mul = (Button) findViewById(R.id.button_mul);
            //加法功能的button
            Button btn_plus = (Button) findViewById(R.id.button_plus);
            //计算正弦功能的button
            Button btn_sin = (Button) findViewById(R.id.button_sin);
            //计算正切功能的button
            Button btn_tan = (Button) findViewById(R.id.button_tan);
            //为数字键绑定监听器
            for (int i = 0; i < 10; i++) {
                btn[i].setOnClickListener(actionPerformed);
            }
            //为功能键绑定监听器
            btn_pi.setOnClickListener(actionPerformed);
            btn_c.setOnClickListener(actionPerformed);
            btn_cos.setOnClickListener(actionPerformed);
            btn_del.setOnClickListener(actionPerformed);
            btn_div.setOnClickListener(actionPerformed);
            btn_dot.setOnClickListener(actionPerformed);
            btn_equal.setOnClickListener(actionPerformed);
            btn_exp.setOnClickListener(actionPerformed);
            btn_exroot.setOnClickListener(actionPerformed);
            btn_leftBracket.setOnClickListener(actionPerformed);
            btn_log.setOnClickListener(actionPerformed);
            btn_mi.setOnClickListener(actionPerformed);
            btn_minus.setOnClickListener(actionPerformed);
            btn_mod.setOnClickListener(actionPerformed);
            btn_mul.setOnClickListener(actionPerformed);
            btn_plus.setOnClickListener(actionPerformed);
            btn_rightBracket.setOnClickListener(actionPerformed);
            btn_sin.setOnClickListener(actionPerformed);
            btn_tan.setOnClickListener(actionPerformed);
        }




    }

    /*键盘命令捕捉*/
    //命令缓存，用于检测输入合法性
    String[] Tipcommand = new String[500];
    //Tipcommand的指针
    int tip_i = 0;
    public View.OnClickListener actionPerformed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取按键上的命令
            String command = ((Button)v).getText().toString();
            //显示器上的字符串
            String str = input.getText().toString();
            //检测输入是否合法
            if (equals_flag = false && "0123456789.()sincostanlogπx²^√×+-%÷".indexOf(command) != -1) {
                //检测显示屏上的字符串是否合法
                if (right(str)) {
                    if ("+-×÷^√".indexOf(command) != -1) {
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
                TipChecker(Tipcommand[tip_i - 1], command);
            else if (tip_i == 0) {
                TipChecker("#", command);
            }
            if ("0123456789.()sincostanlogπx²^√×+-%÷".indexOf(command) != -1 && tip_lock) {
                Tipcommand[tip_i] = command;
                tip_i++;
            }
            //若输入正确，则将信息显示到显示器上
            if ("0123456789.()sincostanlogπx²^√×+-%÷".indexOf(command) != -1 && tip_lock) {
                print(command);
            }
            //如果输入的是退格键，并且在按“=”之前
            else if (command.compareTo("del") == 0 && equals_flag) {
                if (TTO(str) == 3) {
                    if (str.length() > 3)
                        input.setText(str.substring(0, str.length() - 3));
                    else if (str.length() == 3) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                    }
                    //依次删除两个字符
                } else if (TTO(str) == 2) {
                    if (str.length() > 2)
                        input.setText(str.substring(0, str.length() - 2));
                    else if (str.length() == 2) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                    }
                    //依次删除一个字符
                } else if (TTO(str) == 1) {
                    //若之前输入的字符串合法，则删除一个字符
                    if (right(str)) {
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
            } else if (command.compareTo("del") == 0 && equals_flag == false) {
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
            else if (command.compareTo("=") == 0 && tip_lock && right(str) && equals_flag) {
                tip_i = 0;
                //表明不可以继续输入
                tip_lock = false;
                //表明输入=之后
                equals_flag = false;
                //保存原来算式的样子
                str_old=str;
                //替换算式中的运算符，便于计算
                str=str.replaceAll("sin","s");
                str=str.replaceAll("cos","o");
                str=str.replaceAll("tan","t");
                str=str.replaceAll("log","g");
                str=str.replaceAll("x²","²");
                //重新输入标志设置为true
                vbegin=true;
                //将-1x转换成-x
                str_new=str.replaceAll("-","-1x");
                //算式结果
                new calc().process(str_new);
            }
            //表明可以继续输入
            tip_lock=true;
        }
    };

    //向input输出字符
    private void print(String str) {
//清屏输出
        if(vbegin)
            input.setText(str);
            //在屏幕原str后增添字符
        else
            input.append(str);
        vbegin=false;
    }

    /*判断一个str是否是合法的，返回值为true、false
     *只包含0123456789.()sincostanlogπx²del^√×+-%÷c的是合法的str，返回true
     * 包含除了0123456789.()sincostanlogπx²del^√×+-%÷c以外的字符的str为非法的，返回false
     */
    private boolean right(String str)
    {
        int i=0;
        for(i=0;i<str.length();i++){
            if (str.charAt(i)!='0'&&str.charAt(i)!='1'&&str.charAt(i)!='2'&&str.charAt(i)!='3'&&str.charAt(i)!='4'&&str.charAt(i)!='5'&&str.charAt(i)!='6'&&str.charAt(i)!='7'&&str.charAt(i)!='8'&&str.charAt(i)!='9'&&str.charAt(i)!='π'&&str.charAt(i)!='.'&&str.charAt(i)!='+'&&str.charAt(i)!='-'&&str.charAt(i)!='×'&&str.charAt(i)!='÷'&&str.charAt(i)!='^'&&str.charAt(i)!='√'&&str.charAt(i)!='s'&&str.charAt(i)!='i'&&str.charAt(i)!='n'&&str.charAt(i)!='c'&&str.charAt(i)!='o'&&str.charAt(i)!='t'&&str.charAt(i)!='a'&&str.charAt(i)!='l'&&str.charAt(i)!='π'&&str.charAt(i)!='x'&&str.charAt(i)!='%'&&str.charAt(i)!='²')
                break;
        }
        if (i==str.length()){
            return true;
        }else {
            return false;
        }
    }

    /*
     *检测函数，返回值为3、2、1，表示应当一次删除几个TTO（即Three Two One）为函数名
     * 为del按钮的删除方式提供依据
     * 返回3，表示str尾部为sin、cos、tan、log中的一个，应当一次删除3个
     * 返回2，表示str尾部为x²、ln、n！中的一个，应当删除2个（当然目前的程序中未加入x²这两个功能，保留是为了增加功能时使用）
     * 返回1，表示为除返回3、2外的所有情况，只需删除一个（包含非法字符时要另外考虑，应该清屏）
     */
    private int TTO(String str)
    {
        if((str.charAt(str.length()-1)=='n'
                &&str.charAt(str.length()-2)=='i'
                &&str.charAt(str.length()-3)=='s')
                ||(str.charAt(str.length()-1)=='c'
                &&str.charAt(str.length()-2)=='o'
                &&str.charAt(str.length()-3)=='s')
                ||(str.charAt(str.length()-1)=='n'
                &&str.charAt(str.length()-2)=='a'
                &&str.charAt(str.length()-3)=='t')
                ||(str.charAt(str.length()-1)=='g'
                &&str.charAt(str.length()-2)=='o'
                &&str.charAt(str.length()-3)=='l')){
            return 3;
        }
        else if ((str.charAt(str.length()-1)=='²'&&str.charAt(str.length()-2)=='x'))
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    /*
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
    private void TipChecker(String tipcommand1, String tipcommand2) {
//Tipcode1表示错误类型，Tipcode2表示名词解释类型
        int Tipcode1=0,Tipcode2=0;
        //表示命令类型
        int tiptype1=0,tiptype2=0;
        //括号数
        int bracket=0;
        //"+-×÷^√x²"不能作为第一位
        if(tipcommand1.compareTo("#")==0&&(tipcommand2.compareTo("÷")==0||tipcommand2.compareTo("×")==0||tipcommand2.compareTo("+")==0||tipcommand2.compareTo(")")==0||tipcommand2.compareTo("√")==0||tipcommand2.compareTo("^")==0||tipcommand2.compareTo("x²")==0)||tipcommand2.compareTo("%")==0){
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

}

