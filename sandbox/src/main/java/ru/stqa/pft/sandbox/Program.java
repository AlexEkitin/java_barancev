package ru.stqa.pft.sandbox;

public class Program {

    public static void main(String[] args) {
        hello("User");
        hello("144");
        double l = 5;
        System.out.println("area = "+ l+ " = " + area(l));

        double a = 5;
        double b = 7;
        System.out.println("area 2 = "+ a + " * "+b +" = " + area(a, b));

        //double l=8;
        //double s = l*l;
        //System.out.println("squere " + l +" = " + s);
    }

    public static void hello(String sombody){

        System.out.println("Hello, "+sombody+"!");
    }

    public static double area(double len){
        return len * len;
    }

    public static double area (double a, double b){
        return a * b;
    }


}