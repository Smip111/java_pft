package ru.stqa.pft.sandbox;

/**
 * Created by admin on 19.11.2016.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double distanceTo(Point p){
        double l=this.x-p.x;
        double h=this.y-p.y;
        return Math.sqrt(l*l+h*h);
    }

}
