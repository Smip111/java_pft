package ru.stqa.pft.sandbox;

/**
 * Created by admin on 19.11.2016.
 */
public class PointsActions {
    public static void main(String[] args) {
        Point p1=new Point(11,8);
        Point p2=new Point(14,4);
        System.out.println("Расстояние между точками с координатами ("+p1.x+","+p1.y+") и ("+p2.x+","+p2.y+") = "+p1.distanceTo(p2));
    }

}
