package ru.stqa.pft.sandbox;


public class Point {

    public double x;
    public double y;

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double distance2(){
    return Math.sqrt((this.x-this.x)*(this.x-this.x)+(this.y-this.y)*(this.y-this.y));
    }



}
