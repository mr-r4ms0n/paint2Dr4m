/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author saipr
 */
public class GradientPanel extends JPanel
{

    private Color c1 = new Color(0,0,0), c2= new Color(0, 0, 0);
    private String textura = "";

    public Color getC1()
    {
        return c1;
    }

    public void setC1(Color aC1)
    {
        c1 = aC1;
    }

    public Color getC2()
    {
        return c2;
    }

    public void setC2(Color aC2)
    {
        c2 = aC2;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        if (textura.equals("Gr"))
        {
             GradientPaint gp = new GradientPaint(
                0, 0, c1, 0, h, c2);
             g2d.setPaint(gp);
             g2d.fillRect(0, 0, w, h);
        }
        if (textura.equals("Color"))
        {
            g2d.setPaint(c1);
            g2d.fillRect(0, 0, w, h);
        }
        g2d.dispose();
    }

    public String getTextura()
    {
        return textura;
    }

    public void setTextura(String textura)
    {
        this.textura = textura;
    }

}
