package ru.stqa.pft.sandbox;

public class Program {

    public static void main(String[] args) {

        Square s = new Square(5);
        System.out.println("area = "+ s.l+ " = " + s.area());

        Rectangle r = new Rectangle(5,7);
        System.out.println("area 2 = "+ r.a + " * "+r.b +" = " + r.area());
    }

    public static void hello(String sombody){

        System.out.println("Hello, "+sombody+"!");
    }






}