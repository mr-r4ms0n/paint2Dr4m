/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

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
public class Murcielago implements Shape
{

    GeneralPath path;

    //x = 35, y = 40, w = 210, h= 110
    public Murcielago(float x, float y, float w, float h)
    {
        path = new GeneralPath();
        //Coordenadas del murcielago
        //Coordenadas del murcielago
        float x0 = x + (w * 0.5f);
        float y0 = y + (h * 0.5f);
        
        float x1 = x0 - (w * 0.15f);
        float y1 = y0 - (h * 0.25f);
        System.out.println("X1:" + x1 + "Y1:" + y1);

        float x2 = x0 - (w * 0.5f); //Curva de la derecha
        float y2 = y0 - (h * 0.25f);
        System.out.println("X2:" + x2 + "Y2:" + y2);

        float x3 = x0 - (w * 0.25f); //Se resta para que la curva vaya hacia adentro
        float y3 = y0 - (h * 0.14f);
        System.out.println("X3:" + x3 + "Y3:" + y3);

        float x4 = x0 - (w * 0.35f); //Se resta para que la curva vaya hacia adentro
        float y4 = y0 - (h * 0.08f);
        System.out.println("X4:" + x4 + "Y4:" + y4);

        float x5 = x0 - (w * 0.05f);
        float y5 = y0 - (h * 0.08f);
        System.out.println("X5:" + x5 + "Y5:" + y5);

        float x6 = x0 + w * 0.f;
        float y6 = y0 + (h * 0.05f);
        System.out.println("X6:" + x6 + "Y6:" + y6);

        float x7 = x0 + (w * 0.05f);
        float y7 = y0 - (h * 0.085f);
        System.out.println("X7:" + x7 + "Y7:" + y7);

        float x8 = x0 + (w * 0.35f);
        float y8 = y0 - (h * 0.08f);
        System.out.println("X8:" + x8 + "Y8:" + y8);

        float x9 = x0 + (w * 0.25f);
        float y9 = y0 - (h * 0.14f);
        System.out.println("X9:" + x9 + "Y9:" + y9);

        float x10 = x0 + (w * 0.5f);
        float y10 = y0 - (h * 0.25f);
        System.out.println("X10:" + x10 + "Y10:" + y10);

        float x11 = x0 + (w * 0.15f);
        float y11 = y0 - (h * 0.25f);
        System.out.println("X11:" + x11 + "Y11:" + y11);

        float x12 = x0 + (w * 0.1f);
        float y12 = y0 - (h * 0.085f);
        System.out.println("X12:" + x12 + "Y12:" + y12);

        float x13 = x0 + (w * 0.0550f);
        float y13 = y0 - (h * 0.22f);
        System.out.println("X12:" + x12 + "Y12:" + y12);

        float x14 = x0 + (w * 0.0445f);
        float y14 = y0 - (h * 0.167f);
        System.out.println("X12:" + x12 + "Y12:" + y12);

        float x15 = x0 - (w * 0.0500f);
        float y15 = y0 - (h * 0.167f);
        System.out.println("X12:" + x12 + "Y12:" + y12);

        float x16 = x0 - (w * 0.0650f);
        float y16 = y0 - (h * 0.22f);
        System.out.println("X12:" + x12 + "Y12:" + y12);

        float x19 = x0 - (w * 0.15f);
        float y19 = y0 + (h * 0.25f);
        System.out.println("X12:" + x19 + "Y12:" + y19);

        float x20 = x0 - (w * 0.1f);
        float y20 = y0 - (h * 0.085f);
        System.out.println("X20:" + x20 + "Y20:" + y20);

        float x21 = x0 - (w * 0.13f);
        float y21 = y0 - (h * 0.2f);
        System.out.println("X21:" + x21 + "Y21:" + y21);

        path.moveTo(x1, y1);
        path.lineTo(x2, y2); //Linea recta del lado derecho
        path.quadTo(x3, y3, x4, y4);
        path.quadTo(x5, y5, x6, y6);
        path.quadTo(x7, y7, x8, y8);
        path.quadTo(x9, y9, x10, y10);
        path.lineTo(x11, y11);
        path.quadTo(x12, y12, x13, y13);
        path.lineTo(x14, y14);
        path.lineTo(x15, y15);
        path.lineTo(x16, y16);
        path.quadTo(x20, y20, x1, y1);
        //path.quadTo(x21, y21, x1, y1);
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
