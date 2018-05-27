package com.tanykoo.cc;

public class EnumTest {

/** 在类或方法中引用枚举类型 **/
MsEnum msEnum;

/** 构造方法，用于参数传递 **/
public EnumTest(MsEnum msEnum) {
super();
this.msEnum = msEnum;
}

public static void main(String[] args) {
EnumTest one = new EnumTest(MsEnum.ONE), 
two = new EnumTest(MsEnum.TWO), 
three = new EnumTest(MsEnum.THREE);
System.out.println("参数是One吗：" + one.setMsEnum());
System.out.println("参数是Two吗：" + two.setMsEnum());
System.out.println("参数是Three吗：" + three.setMsEnum());
}

/** 在switch中使用枚举 **/
public String setMsEnum() {
StringBuffer str = new StringBuffer();
str.append("参数是");
switch (msEnum) {
case ONE:
str.append("One");
break;
case TWO:
str.append("Two");
break;
default:
str.append("Three");
break;
}
str.append("...");
return str.toString();
}

}

/**
 * 创建一个枚举 <br>
 * 因枚举类型的实例都是常量，因此都采用大写
 * 
 * @author RSun
 * @date 2012-2-28下午10:49:28
 */
enum MsEnum {
ONE, TWO, THREE
}