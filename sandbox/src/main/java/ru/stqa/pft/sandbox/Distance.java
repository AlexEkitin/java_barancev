package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Distance {

    public static void main(String[] args) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);


        System.out.println("distance = "+ p1.distance(p2));

    }

}