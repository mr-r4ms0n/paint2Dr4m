/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 *
 * @author kenit
 */
public class MyShape
{
    private Shape shape = null;
    private Object strokeTexture = null;
    private Object fillTexture = null;
    private Color color = new Color(0, 0, 0);
    private Stroke stroke = null;
    private AlphaComposite alphaComposite = null;
    private Boolean fillOk;
    private Boolean outlineOk;
    private Boolean strokeOk;
    private Boolean selected = false;
    
    public MyShape(Shape shape, Color color)
    {
        this.shape = shape;
        this.color = color;
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
        this.strokeTexture = strokeTexture;
    }

    public Object getFillTexture()
    {
        return fillTexture;
    }

    public void setFillTexture(Object fillTexture)
    {
        this.fillTexture = fillTexture;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Stroke getStroke()
    {
        return stroke;
    }

    public void setStroke(Stroke stroke)
    {
        this.stroke = stroke;
    }

    public AlphaComposite getAlphaComposite()
    {
        return alphaComposite;
    }

    public void setAlphaComposite(AlphaComposite alphaComposite)
    {
        this.alphaComposite = alphaComposite;
    }

    public Boolean getFillOk()
    {
        return fillOk;
    }

    public void setFillOk(Boolean fillOk)
    {
        this.fillOk = fillOk;
    }

    public Boolean getOutlineOk()
    {
        return outlineOk;
    }

    public void setOutlineOk(Boolean outlineOk)
    {
        this.outlineOk = outlineOk;
    }

    public Boolean getStrokeOk()
    {
        return strokeOk;
    }

    public void setStrokeOk(Boolean strokeOk)
    {
        this.strokeOk = strokeOk;
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
