/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;

/**
 *
 * @author kenit
 */
public class MyShape
{
    private Shape shape = null;
    private Object strokeTexture = null;
    private Object fillTexture = null;
    private Color strokeColor = new Color(0, 0, 0);
    private Stroke stroke;
    private float alphaComposite;
    private Boolean selected = false;
    
    public MyShape(Shape shape)
    {
        this.shape = shape;
        this.stroke = new BasicStroke(2);
    }
    
    public Shape getShape()
    {
        return shape;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }

    public Object getStrokeTexture()
    {
        return strokeTexture;
    }

    public void setStrokeTexture(Object strokeTexture)
    {
        if (strokeTexture instanceof TexturePaint )
        {
            this.strokeTexture = (TexturePaint) strokeTexture;
        } else
        {
            if (strokeTexture instanceof GradientPaint)
            {
                this.strokeTexture = (GradientPaint) strokeTexture;
            } else
            {
                //Color
            }
        }
    }

    public Object getFillTexture()
    {
        return fillTexture;
    }

    public void setFillTexture(Object fillTexture)
    {
        this.fillTexture = fillTexture;
    }

    public Color getStrokeColor()
    {
        return strokeColor;
    }

    public void setStrokeColor(Color color)
    {
        this.strokeColor = color;
    }

    public Stroke getStroke()
    {
        return stroke;
    }

    public void setStroke(Stroke stroke)
    {
        this.stroke = stroke;
    }

    public float getAlphaComposite()
    {
        return alphaComposite;
    }

    public void setAlphaComposite(float alphaComposite)
    {
        this.alphaComposite = alphaComposite;
    }

    public Boolean getSelected()
    {
        return selected;
    }

    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }
    
}
