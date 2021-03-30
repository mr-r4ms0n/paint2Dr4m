/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import figuras.C1;
import figuras.Casa;
import figuras.Diamante;
import figuras.Estrella4Puntas;
import figuras.Estrella5Puntas;
import figuras.Triangulo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author kenit
 */
public class JavaDraw2DPanelM extends JPanel implements MouseListener, MouseMotionListener
{

    private Vector shapes = new Vector();
    public static final int RECTANGLE = 0;
    public static final int ROUNDRECTANGLE2D = 1;
    public static final int ELLIPSE2D = 2;
    public static final int ARC2D = 3;
    public static final int LINE2D = 4;
    public static final int QUADCURVE2D = 5;
    public static final int CUBICCURVE2D = 6;
    public static final int POLYGON = 7;
    public static final int GENERAL = 8;
    public static final int AREA = 9;

    //Figuras propias
    public static final int HEART = 10;
    public static final int HOUSE = 11;
    public static final int BAT = 12;
    public static final int TRIANGLE = 13;
    public static final int DIAMOND = 14;
    public static final int STAR5 = 15;
    public static final int STAR4 = 16;

    public int shapeType = RECTANGLE;
    // vector of input points
    Vector points = new Vector();
    int pointIndex = 0;
    Shape partialShape = null;
    Point p = null;

    //Coordenadas para dibujar el plano cartesiano
    int alto = 1370;
    int ancho = 654;

    public JavaDraw2DPanelM()
    {
        super();
        setBackground(Color.white);
        setPreferredSize(new Dimension(1155, 504));
        addMouseListener(this);
        addMouseMotionListener(this);
        System.out.println("Entre");
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.black);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < shapes.size(); i++)
        {
            Shape s = (Shape) shapes.get(i);
            g2.draw(s);
        }
    }

    public void mouseClicked(MouseEvent ev)
    {
    }

    public void mouseEntered(MouseEvent ev)
    {
    }

    public void mouseExited(MouseEvent ev)
    {
    }

    public void mousePressed(MouseEvent ev)
    {
        points.add(ev.getPoint());
        pointIndex++;
        p = null;
    }

    public void mouseReleased(MouseEvent ev)
    {
        Graphics g = getGraphics();
        Point p1 = (Point) (points.get(pointIndex - 1));
        p = ev.getPoint();
        Shape s = null;
        switch (shapeType)
        {
            case RECTANGLE:
                s = new Rectangle(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case ROUNDRECTANGLE2D:
                s = new RoundRectangle2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                break;
            case ELLIPSE2D:
                s = new Ellipse2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case ARC2D:
                s = new Arc2D.Float(p1.x, p1.y, p.x - p1.x,
                        p.y - p1.y,
                        30, 120, Arc2D.OPEN
                );
                break;
            case LINE2D:
                s = new Line2D.Float(p1.x, p1.y, p.x, p.y);
                break;
            case QUADCURVE2D:
                if (pointIndex > 1)
                {
                    Point p2 = (Point) points.get(0);
                    s = new QuadCurve2D.Float(p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                }
                break;
            case CUBICCURVE2D:
                if (pointIndex > 2)
                {
                    Point p2 = (Point) points.get(pointIndex - 2);
                    Point p3 = (Point) points.get(pointIndex - 3);
                    s = new CubicCurve2D.Float(p3.x, p3.y, p2.x, p2.y,
                            p1.x, p1.y, p.x, p.y);
                }
                break;
            case POLYGON:
                if (ev.isShiftDown())
                {
                    s = new Polygon();
                    for (int i = 0; i < pointIndex; i++)
                    {
                        ((Polygon) s).addPoint(((Point) points.get(i)).x,
                                ((Point) points.get(i)).y);
                    }
                    ((Polygon) s).addPoint(p.x, p.y);
                }
                break;
            case HEART:
                s = new C1(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case HOUSE:
                s = new Casa(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case TRIANGLE:
                s = new Triangulo(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case DIAMOND:
                s = new Diamante(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case STAR5:
                s = new Estrella5Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case STAR4:
                s = new Estrella4Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
        }
        if (s != null)
        {
            shapes.add(s);
            points.clear();
            pointIndex = 0;
            p = null;
            repaint();
        }
    }

    public void mouseMoved(MouseEvent ev)
    {
    }

    public void mouseDragged(MouseEvent ev)
    {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setXORMode(Color.white);
        Point p1 = (Point) points.get(pointIndex - 1);
        switch (shapeType)
        {
            case RECTANGLE:
                if (p != null)
                {
                    g.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                }
                p = ev.getPoint();
                g.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case ROUNDRECTANGLE2D:
                if (p != null)

                {
                    g.drawRoundRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                }
                p = ev.getPoint();
                g.drawRoundRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                break;
            case ELLIPSE2D:
                if (p != null)
                {
                    g.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                }
                p = ev.getPoint();
                g.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                break;
            case ARC2D:
                if (p != null)
                {
                    g.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
                }
                p = ev.getPoint();
                g.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
                break;
            case LINE2D:
            case POLYGON:
                if (p != null)
                {
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                }
                p = ev.getPoint();
                g.drawLine(p1.x, p1.y, p.x, p.y);
                break;
            case QUADCURVE2D:
                if (pointIndex == 1)
                {
                    if (p != null)
                    {
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    p = ev.getPoint();
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                } else
                {
                    Point p2 = (Point) points.get(pointIndex - 2);
                    if (p != null)
                    {
                        g.draw(partialShape);
                    }
                    p = ev.getPoint();
                    partialShape = new QuadCurve2D.Float(p2.x, p2.y,
                            p1.x, p1.y, p.x, p.y);
                    g.draw(partialShape);
                }
                break;
            case CUBICCURVE2D:
                if (pointIndex == 1)
                {
                    if (p != null)
                    {
                        g.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    p = ev.getPoint();
                    g.drawLine(p1.x, p1.y, p.x, p.y);
                } else if (pointIndex == 2)
                {

                    Point p2 = (Point) points.get(pointIndex - 2);
                    if (p != null)
                    {
                        g.draw(partialShape);
                    }
                    p = ev.getPoint();
                    partialShape = new QuadCurve2D.Float(p2.x, p2.y,
                            p1.x, p1.y, p.x, p.y);
                    g.draw(partialShape);
                } else
                {
                    Point p2 = (Point) points.get(pointIndex - 2);
                    Point p3 = (Point) points.get(pointIndex - 3);
                    if (p != null)
                    {
                        g.draw(partialShape);
                    }
                    p = ev.getPoint();
                    partialShape = new CubicCurve2D.Float(p3.x, p3.y,
                            p2.x, p2.y, p1.x, p1.y, p.x, p.y);
                    g.draw(partialShape);
                }
                break;
            case HEART:

                if (p != null)
                {
                    g.draw(new C1(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new C1(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;

            case HOUSE:

                if (p != null)
                {
                    g.draw(new Casa(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new Casa(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;

            case TRIANGLE:
                if (p != null)
                {
                    g.draw(new Triangulo(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new Triangulo(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;
            case DIAMOND:
                if (p != null)
                {
                    g.draw(new Diamante(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new Diamante(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;
            case STAR5:
                if (p != null)
                {
                    g.draw(new Estrella5Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new Estrella5Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;
            case STAR4:
                if (p != null)
                {
                    g.draw(new Estrella4Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                }
                p = ev.getPoint();
                g.draw(new Estrella4Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                break;
        }
    }
}
