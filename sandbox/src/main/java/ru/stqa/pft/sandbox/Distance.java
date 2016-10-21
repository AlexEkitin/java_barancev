package ru.stqa.pft.sandbox;

public class Distance {

    public static void main(String[] args) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        System.out.println("distance = "+ distance(p1,p2));

        System.out.println("distance = "+ p1.distance2());


    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }

}
