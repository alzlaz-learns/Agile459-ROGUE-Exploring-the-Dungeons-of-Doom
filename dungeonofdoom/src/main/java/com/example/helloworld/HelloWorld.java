package com.example.helloworld;

public class HelloWorld {

    public static String test(){
        return "Hello World";
    }
    public static void main(String[] arg){
        String hello = HelloWorld.test();
        System.out.println(hello);
    }
}