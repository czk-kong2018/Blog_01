title: java反射笔记
date: 2018-06-08 22:39:33
categories: JavaSE
tags:
    - java
---
### 子类对象取父类class对象
父类：Toy   子类:FancyToy
由子类对象获取父类的.class对象。编译器只允许你声明超类的引用是“某个类”这个类是 FancyToy的父类。而不会接受Class<Toy>这样的声明。
```
            FancyToy fancyToy = ftClass.newInstance();
            Class<? super FancyToy> up= ftClass.getSuperclass();

            Class<Toy> toy=ftClass.getSuperclass();// 报错
```
### 反射中的新转型方法
```
    // 用cast转型，对于无法使用普通转型的情况非常有用
        Building b=new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        
        //传统转型
        h=(House)b;
```
### Class类的使用
万物皆对象，类也是对象，类是java.lang.Class类的实例对象。
```
  //Foo的实例对象
        Foo foo1=new Foo();
        
        //Foo这个类也是实例对象，Class类的实例对象。成为Foo的class type
        //任何一个类都是Class的实例对象，三种表示方式
        //第一只表示方式：说明每个类都有个隐含的静态成员变量class
        Class c1=Foo.class;
        //第二种表达方式，通过该类的对象调用.getClass方法
        Class c2=foo1.getClass();
        //第三种表达方式
        Class c3=Class.forName("imooc.Foo");
        c1==c2==c3返回true
        
        //通过type class创建该类的实例对象-》通过c1或c2或c3创建对象.前提是具备无参构造方法
        Foo foo=(Foo)c.newInstance();
```
### 区分静态加载和动态加载
- 静态加载
编译时刻加载类是静态加载类，运行时刻加载类是动态加载。
用cmd进行操作。先来看一个例子;有这么一个Office类
```
class Office
{
    public static void main(String[] args) {
        if("Word".equals(args[0])){
            Word w=new Word();
            w.start();
        }
        if("Excel".equals(args[0])){
            Excel e=new Excel();
            e.start();
        }
    }
}
```
在cmd下运行javac Office.java会有明显的报错，提示Word和Excel类不存在。即使我们给
它一个Word类.
```
class Word
{
    public  void start(){
        System.out.println("word..start...");
    }
}
```
1.javac Word 2.javac Office 运行后之后只报Excel类不存在。这就是静态加载，在编译时就需要加载所有需要的类，这很明显有问题。如果这个程序有100个功能因为一个有问题而跑不起来，那怎么办。我们需要的是程序能运行，用到哪一个，哪一个不存在了你再个告诉我有错。这便是接下来讲的动态加载。
- 动态加载
三个类: 1.接口OfficeAble  2.OfficeBetter   3.Word
```
interface OfficeAble
{
    public void start();
}

class Word implements OfficeAble
{
    public  void start(){
        System.out.println("word..start...");
    }
}

class OfficeBetter
{
    public static void main(String[] args) {
        try{
            Class c=Class.forName(args[0]);
            OfficeAble of=(OfficeAble)c.newInstance();
            of.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
```
现在编译就能够过去，运行java OfficeBetter Word 就能打印正确，运行java OfficeBetter，Excel才会报错。这样当以后想添加新功能就直接添加新类就行了。可扩展性变强了.
### 基本类型的类类型(class type)
```
    Class c1=int.class; //int 的类类型
    Class c2=String.class;
    Class c3=double.class;
    Class c4=Double.class;
    c3==c4 return false 两个class type不一样
    Class c5=void.class;

    System.out.println(c1.getName());
    System.out.println(c2.getName());
    System.out.println(c2.getSimpleName()); //不包含包名的类名称
out:    int
        java.lang.String
        String
```
### 获取一个类的所有方法
         **getMethods()获取的是所有public的方法，包括父类**
         **getDeclaredMethods获取的是该类自己的声明方法，无论权限，不包括父类**
```
   private static void printFieldMessage(Object obj) {
        /**
         * 成员变量也是对象
         * java.lang,reflect.Field
         * FieLd类封装了关于成员变量的操作
         * getFields(）方法获取的是所有public的成员变量信息
         * getDeclaredField获取该类自己声明的成员变量信息
         */
        Class c=obj.getClass();
        Field[] fs = c.getDeclaredFields();
        for(Field field:fs){
            Class<?> fieldType = field.getType();
            String typename = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(typename+" "+fieldName);
        }
    }
```
### 获取成员变量信息
```
  private static void printFieldMessage(Object obj) {
        /**
         * 成员变量也是对象
         * java.lang,reflect.Field
         * FieLd类封装了关于成员变量的操作
         * getFields(）方法获取的是所有public的成员变量信息
         * getDeclaredField获取该类自己声明的成员变量信息
         */
        Class c=obj.getClass();
        Field[] fs = c.getDeclaredFields();
        for(Field field:fs){
            Class<?> fieldType = field.getType();
            String typename = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(typename+" "+fieldName);
        }
    }
```
### 打印构造函数
```
 public static void printConMessage(Object obj){
        Class c=obj.getClass();
        /**
         * 构造函数也是对象
         * java.lang.Constructor中封装了构造方法的信息
         * getConstructors 获取所有public构造方法
         * getDeclaredConstructors得到所有自己的构造方法
         */
        Constructor[] cs = c.getDeclaredConstructors();
        for(Constructor constructor:cs){
            System.out.print(constructor.getName()+"(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for(Class class1:parameterTypes){
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
```
### 方法的反射
```
 public static void main(String[] args) {
        A a = new A();
        //获取print(int,int)方法  1.首先获取类的类类型
        Class<? extends A> c = a.getClass();
        //2.获取方法 名称和参数列表
        try {
            Method m = c.getMethod("print", int.class, int.class);
            
            //方法的反射操作 传统是 a.print(10,20) 和 m.invoke调用相同
           //相当于反过来操作用方法对象 m.invoke(对象a, 方法参数)
           //若方法返回void会返回一个null  object o = null;
            Object o = m.invoke(a, 10, 20);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
class A{
    public void print(int a,int b){
        System.out.println(a+b);
    }
    public void print(String a,String b){
        System.out.println(a.toUpperCase()+","+b.toUpperCase());
    }

    out: 30
```
### 泛型问题
反射操作都是编译之后的操作
```
        ArrayList list1=new ArrayList();
        ArrayList<String> list=new ArrayList<>();
    
        list1.add("hello");
        Class c1=list1.getClass();
        Class c2=list.getClass();
        System.out.println(c1==c2);

      out :true
```
说明泛型只在编译阶段有效，过了编译阶段后就失效了。验证:通过方法反射操作绕过编译
```
  public static void main(String[] args) {
        ArrayList list=new ArrayList();

        ArrayList<String> list1=new ArrayList<>();

        list1.add("hello");
        Class c1=list.getClass();
        Class c2=list1.getClass();
        System.out.println(c1==c2);

        try {
            Method m = c2.getMethod("add",Object.class);
            m.invoke(list1,20);  //绕过了编译操作也就绕过了泛型,可以添加int类型
            System.out.println(list1.size());   
            System.out.println(list1);
            //不能遍历 会抛出 Integer无法转为String 的异常
/*            for(String string:list1){
                System.out.println(string);
            }*/

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
```


