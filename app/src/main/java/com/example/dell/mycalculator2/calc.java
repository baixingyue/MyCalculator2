package com.example.dell.mycalculator2;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * Created by DELL on 2016/9/7.
 * Made by Violet_Bai
 */
/*
 *整个计算核心，只要将表达式的整个字符串传入calc().process()就可以实行计算了
 * 算法包括以下几个部分
 * 1.计算部分     process(String str) 当然，这个是建立在毫无错误的情况下
 * 2.数据格式化  FP(double n)         使数据有相当的精确度
 * 3.阶乘算法    N(double n)           计算结成的函数(没有在按钮中加这个功能，写下为了以后拓展功能用)
 * 4.错误提示    showError(int code,String str)  将错误返回
 */

public class calc extends MainActivity {
    public calc() {
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
            if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
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
