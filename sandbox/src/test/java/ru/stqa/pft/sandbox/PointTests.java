package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by admin on 25.11.2016.
 */
public class PointTests {

    @Test
    public void testDistance1(){
        Point a=new Point(11,8);
        Point b=new Point(14,4);
        Assert.assertEquals(a.distanceTo(b),5.0);
    }

    @Test
    public void testDistance2(){
        Point a=new Point(-6,3);
        Point b=new Point(6,-2);
        Assert.assertEquals(a.distanceTo(b),13.0);
    }

    @Test
    public void testDistance3(){
        Point a=new Point(0,15);
        Point b=new Point(-8,0);
        Assert.assertEquals(a.distanceTo(b),17.0);
    }

    @Test
    public void testDistance4(){
        Point a=new Point(-3,-20);
        Point b=new Point(-10,-44);
        Assert.assertEquals(a.distanceTo(b),25.0);
    }

}
