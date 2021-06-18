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
import figuras.Estrella6Puntas;
import figuras.FlechaDer;
import figuras.Murcielago;
import figuras.Rayo;
import figuras.RelojArena;
import figuras.Triangulo;
import interfaz.Menu;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author kenit
 */
public class JavaDraw2DPanelM extends JPanel implements MouseListener, MouseMotionListener
{

    private ArrayList<MyShape> shapes = new ArrayList<>();
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
    public static final int RIGHTARROW = 17;
    public static final int LIGHTNING = 18;
    public static final int SANDCLOCK = 19;
    public static final int STAR6 = 20;
    public static final int SHARKASS = 21;
    public static final int NO_SELECTED = 22;
    public static final int ARBOL = 23;
    public int shapeType = RECTANGLE;

    // vector of input points
    Vector points = new Vector();
    int pointIndex = 0;
    Shape partialShape = null;
    Point p = null;

    Point selectedPoint;

    //Atributos para la configuracion
    //Parte del contorno
    public static Color strokeColor = Color.BLACK;
    public static Stroke stroke = new BasicStroke(2);
    public static Object strokeTexture = null;
    /////////////////////////////////

    //Coordenadas para dibujar el plano cartesiano
    int alto = 1200;
    int ancho = 654;

    //Variables para las tranmformaciones 
    public static AffineTransform CONFIG_AFFINE_TRANSFORM = new AffineTransform();

    public JavaDraw2DPanelM()
    {
        super();
        setBackground(Color.white);
        setPreferredSize(new Dimension(1155, 504));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.RED);

        Line2D linea_y = new Line2D.Double(alto / 2, 0, alto / 2, ancho);
        Line2D linea_x = new Line2D.Double(0, ancho / 2, alto, ancho / 2);

        g2.draw(linea_x);
        g2.draw(linea_y);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < shapes.size(); i++)
        {
            Shape s = shapes.get(i).getShape();

            //Si tiene transparencia se la colocamos
            //AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, shapes.get(i).getAlphaComposite());
            //g2.setComposite(ac);
            g2.setColor(shapes.get(i).getStrokeColor());
            g2.setStroke(shapes.get(i).getStroke());
            g2.draw(s);

            if (shapes.get(i).getSelected())
            {
                float dash1[] =
                {
                    10.0f
                };//la cantidad de largo de las lineas que se puntean
                BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
                g2.setStroke(dashed);
                g2.setPaint(new Color(0, 80, 157));
                g2.draw(getSelectedRectangle(shapes.get(i).getShape()));
                //g2.setStroke(new BasicStroke(1));
                shapes.get(i).setStrokeColor(strokeColor);
                shapes.get(i).setStroke(stroke);
                shapes.get(i).setStrokeTexture(strokeTexture);

                if ((shapes.get(i).getStrokeTexture() == null))
                {
                    g2.setColor(shapes.get(i).getStrokeColor());
                } else
                {
                    g2.setPaint((Paint) shapes.get(i).getStrokeTexture());
                }

                g2.setStroke(shapes.get(i).getStroke());
                g2.draw(shapes.get(i).getShape());
                //g2.fill(shapes.get(i).getShape());

                repaint();
            }
        }

    }

    /**
     * Nos retorna el rectangulo que pintamos en el Contexto Grafico cuando la
     * figura se selecciona.
     *
     * @param s figura a obtener el rectangulo
     * @return rectangulo punteado
     */
    public Rectangle getSelectedRectangle(Shape s)
    {
        Rectangle rec = s.getBounds();
        rec.setBounds((int) rec.getX() - 10, (int) rec.getY() - 10, (int) rec.getWidth() + 20, (int) rec.getHeight() + 20);
        return rec;
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
        if (ev != null)
        {
            points.add(ev.getPoint());
            pointIndex++;
            p = null;
            //Cuando activamos el boton de seleccion simple
            selectedPoint = ev.getPoint(); //Extraemos el punto exacto del raton

            if (Menu.selected)
            {
                //En primer lugar hacemos todas falsas para evitar confusiones
                setSelectedRentagle();
                for (int i = shapes.size() - 1; i >= 0; i--)
                {
                    if (shapes.get(i).getShape().contains(selectedPoint))
                    {
                        shapes.get(i).setSelected(true);
                        break;
                    } else
                    {
                        shapes.get(i).setSelected(false);
                    }
                }
            } else
            {
                //Cuando activamos el boton de seleccion acumulada
                if (Menu.selectedM)
                {
                    for (int i = shapes.size() - 1; i >= 0; i--)
                    {
                        if (shapes.get(i).getShape().contains(selectedPoint))
                        {
                            shapes.get(i).setSelected(true);

                        }

                    }
                }
            }
        } else
        {
            if (Menu.selectedT)
            {
                for (int i = shapes.size() - 1; i >= 0; i--)
                {

                    shapes.get(i).setSelected(true);

                }
            }
        }
        repaint();
    }

    /**
     * Seteamos la seleccion de las figuras a false (Metodo complementario de la
     * parte del evento MousePressed)
     */
    public void setSelectedRentagle()
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            shapes.get(i).setSelected(false);
        }
    }

    public void mouseReleased(MouseEvent ev)
    {
        Graphics g = getGraphics();
        Point p1 = (Point) (points.get(pointIndex - 1));
        p = ev.getPoint();
        Shape s = null;

        if (Menu.jCTransformaciones.getSelectedIndex() == 0) //Si no hay niguna tranformacion activa entonces es hora de dibujar
        {
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
                case RIGHTARROW:
                    s = new FlechaDer(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case LIGHTNING:
                    s = new Rayo(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case SANDCLOCK:
                    s = new RelojArena(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case STAR6:
                    s = new Estrella6Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case BAT:
                    s = new Murcielago(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case NO_SELECTED:
                    System.out.println("No se dibuja nada");
                    break;

            }
        } else//Si hay alguna transformacion activa entonces...
        {
            CONFIG_AFFINE_TRANSFORM = new AffineTransform();
            for (int i = 0; i <= shapes.size() - 1; i++)
            {
                if (shapes.get(i).getSelected())
                {
                    double x0 = shapes.get(i).getShape().getBounds().x + shapes.get(i).getShape().getBounds().width / 2;
                    double y0 = shapes.get(i).getShape().getBounds().y + shapes.get(i).getShape().getBounds().height / 2;
                    switch (Menu.jCTransformaciones.getSelectedIndex())
                    {
                        case 1:
                            CONFIG_AFFINE_TRANSFORM.setToTranslation(p.x - p1.x, p.y - p1.y);
                            break;
                        case 2:
                            CONFIG_AFFINE_TRANSFORM.setToRotation(Math.atan2(p1.y - y0, p1.x - x0) - Math.atan2(p.y - y0, p.x - x0), x0, y0);
                            break;
                        case 3:
                            //getGraphics().translate(0, 0);
                            double sx = Math.abs((double) (0 - p.x) / (0 - p1.x));
                            double sy = Math.abs((double) (0 - p.y) / (0 - p1.y));
                            CONFIG_AFFINE_TRANSFORM.setToScale(sx, sy);
                            break;
                        case 4:
                            double shx = ((double) (p1.x) / (p.x)) - 1;
                            double shy = ((double) (p1.y) / (p.y)) - 1;
                            CONFIG_AFFINE_TRANSFORM.setToShear(shx, shy);
                            break;
                    }
                    shapes.get(i).setShape(CONFIG_AFFINE_TRANSFORM.createTransformedShape(shapes.get(i).getShape()));
                }
            }
        }

        if (s != null)
        {
            MyShape s1 = new MyShape(s);
            shapes.add(s1);
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
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setXORMode(Color.white);
        Point p1 = (Point) points.get(pointIndex - 1);
        if (Menu.jCTransformaciones.getSelectedIndex() == 0) //Si no hay niguna tranformacion activa entonces es hora de dibujar
        {
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
                case RIGHTARROW:
                    if (p != null)
                    {
                        g.draw(new FlechaDer(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    }
                    p = ev.getPoint();
                    g.draw(new FlechaDer(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    break;

                case LIGHTNING:
                    if (p != null)
                    {
                        g.draw(new Rayo(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    }
                    p = ev.getPoint();
                    g.draw(new Rayo(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    break;
                case SANDCLOCK:
                    if (p != null)
                    {
                        g.draw(new RelojArena(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    }
                    p = ev.getPoint();
                    g.draw(new RelojArena(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    break;
                case STAR6:
                    if (p != null)
                    {
                        g.draw(new Estrella6Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    }
                    p = ev.getPoint();
                    g.draw(new Estrella6Puntas(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    break;
                case BAT:
                    if (p != null)
                    {
                        g.draw(new Murcielago(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    }
                    p = ev.getPoint();
                    g.draw(new Murcielago(p1.x, p1.y, p.x - p1.x, p.y - p1.y));
                    break;
            }
        } else
        {
            for (int i = 0; i <= shapes.size() - 1; i++)
            {
                if (shapes.get(i).getSelected())
                {
                    double x0 = shapes.get(i).getShape().getBounds().x + shapes.get(i).getShape().getBounds().width / 2;
                    double y0 = shapes.get(i).getShape().getBounds().y + shapes.get(i).getShape().getBounds().height / 2;
                    p = ev.getPoint();
                    switch (Menu.jCTransformaciones.getSelectedIndex())
                    {
                        case 1:
                            CONFIG_AFFINE_TRANSFORM.setToTranslation(p.x - p1.x, p.y - p1.y);
                            break;
                        case 2:
                            CONFIG_AFFINE_TRANSFORM.setToRotation(Math.atan2(p1.y - y0, p1.x - x0) - Math.atan2(p.y - y0, p.x - x0), x0, y0);
                            break;
                        case 3:

                            double sx = Math.abs((double) (0 - p.x) / (0 - p1.x));
                            double sy = Math.abs((double) (0 - p.y) / (0 - p1.y));
                            CONFIG_AFFINE_TRANSFORM.setToScale(sx, sy);
                            break;
                        case 4:
                            double shx = ((double) (p1.x - 0) / (p.x - 0)) - 1;
                            double shy = ((double) (p1.y - 0) / (p.y - 0)) - 1;
                            CONFIG_AFFINE_TRANSFORM.setToShear(shx, shy);
                            break;
                    }
                    if (p != null)
                    {
                        g.draw(CONFIG_AFFINE_TRANSFORM.createTransformedShape(shapes.get(i).getShape()));
                    }
                    g.draw(CONFIG_AFFINE_TRANSFORM.createTransformedShape(shapes.get(i).getShape()));
                }
            }
        }
    }

    public ArrayList<MyShape> getShapes()
    {
        return shapes;
    }

    public void setShapes(ArrayList<MyShape> shapes)
    {
        this.shapes = shapes;
    }
}
