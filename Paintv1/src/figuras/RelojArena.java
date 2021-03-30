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
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author kenit
 */
public class RelojArena implements Shape
{

    Polygon path;

    public RelojArena(float x, float y, float w, float h)
    {
        //Puntos del diamante
        int x0 = (int) (x + 0f * w);
        int y0 = (int) (y + 0f * h);
        int x1 = (int) (x + 1.5f * w);
        int y1 = (int) (y + 0f * h);
        int x2 = (int) (x + 1f * w);
        int y2 = (int) (y + 1f * h);
        int x3 = (int) (x + 1.5f * w);
        int y3 = (int) (y + 2f * h);
        int x4 = (int) (x + 0f * w);
        int y4 = (int) (y + 2f * h);
        int x5 = (int) (x + 0.5f * w);
        int y5 = (int) (y + 1f * h);
        
        int [] puntosX = {x0,x1,x2,x3,x4,x5};
        int [] puntosY = {y0,y1,y2,y3,y4,y5};
        
        path = new Polygon(puntosX, puntosY, 6);
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
