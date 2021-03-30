/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author kenit
 */
public class Rayo implements Shape
{

    Polygon path;

    public Rayo(float x, float y, float w, float h)
    {

        //Puntos del rayo
        //A
        int x0 = (int) (x + 0f * w);
        int y0 = (int) (y + 0.1f * h);
        //C
        int x1 = (int) (x + 0.18f * w);
        int y1 = (int) (y + 0.22f * h);
        //D
        int x2 = (int) (x + 0.11 * w);
        int y2 = (int) (y + 0.25f * h);
        //E
        int x3 = (int) (x + 0.3f * w);
        int y3 = (int) (y + 0.35f * h);
        //F
        int x4 = (int) (x + 0.248f * w);
        int y4 = (int) (y + 0.388f * h);
        //G
        int x5 = (int) (x + 0.54f * w);
        int y5 = (int) (y + 0.52f * h);
        //K
        int x6 = (int) (x + 0.36f * w);
        int y6 = (int) (y + 0.32f * h);
        //J
        int x7 = (int) (x + 0.401f * w);
        int y7 = (int) (y + 0.283f * h);
        //I
        int x8 = (int) (x + 0.29f * w);
        int y8 = (int) (y + 0.17f * h);
        //H
        int x9 = (int) (x + 0.33f * w);
        int y9 = (int) (y + 0.14f * h);
        //B
        int x10 = (int) (x + 0.245f * w);
        int y10 = (int) (y + 0.0f * h);

        int[] puntosX =
        {
            x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10
        };
        int[] puntosY =
        {
            y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10
        };

        path = new Polygon(puntosX, puntosY, 11);
        //path.addPoint(x0, y0);
        //path.addPoint(x1, y1);
        //path.addPoint(x2, y2);
        //path.addPoint(x3, y3);

    }

    @Override
    public boolean contains(Rectangle2D rect)
    {
        return path.contains(rect);
    }

    @Override
    public boolean contains(Point2D pd)
    {
        return path.contains(pd);
    }

    @Override
    public boolean contains(double x, double y)
    {
        return path.contains(x, y);
    }

    @Override
    public boolean contains(double x, double y, double w, double h)
    {
        return path.contains(x, y, w, h);
    }

    @Override
    public Rectangle getBounds()
    {
        return path.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        return path.getBounds2D();
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at)
    {
        return path.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness)
    {
        return path.getPathIterator(at, flatness);
    }

    @Override
    public boolean intersects(Rectangle2D rd)
    {
        return path.intersects(rd);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h)
    {
        return path.intersects(x, y, w, h);
    }

}
