package ru.stqa.pft.sandbox;

public class Program {

    public static void main(String[] args) {
        hello("User");
        hello("144");

        Square s = new Square(5);
        System.out.println("area = "+ s.l+ " = " + s.area());

        Rectangle r = new Rectangle(5,7);

        System.out.println("area 2 = "+ r.a + " * "+r.b +" = " + r.area());

        //double l=8;
        //double s = l*l;
        //System.out.println("squere " + l +" = " + s);
    }

    public static void hello(String sombody){

        System.out.println("Hello, "+sombody+"!");
    }






}