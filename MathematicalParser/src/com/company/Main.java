package com.company;

public class Main {

    public static void main(String[] args) {

        Parser p = new Parser();
        System.out.println(p.parse("E-4*2+4^(-2+5)-E-8/(9-(1+2^(10.8-4.4*2)))-PI+1+PI"));

    }

}
