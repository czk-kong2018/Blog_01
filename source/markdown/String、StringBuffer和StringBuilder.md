title: String、StringBuffer和StringBuilder
date: 2018-06-08 22:39:33
categories: JavaSE
tags:
    - java
---
### String
字符串常量，String对象不可变，当对String类型做修改时，本质上是创建了一个新的对象，然后将该引用指向新的对象。所以字符串修改频繁就别用String，会生成许多对象给GC造成负担。
观察源码，可以看到String定义的成员变量value,其中value是个字节数组，而且是final修饰，这个才是String不可变的关键点；
### StringBuffer
线程安全，


### StringBuilder
和StringBuffer差不多。但是是线程不安全的，效率高，基本可以替代StringBuffer。