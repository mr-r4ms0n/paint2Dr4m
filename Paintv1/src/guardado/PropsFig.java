/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardado;

import java.awt.Color;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 *
 * @author kenit
 */
public class PropsFig
{
    private ArrayList<PathSegment> pathSegments;
    private Object texturaContorno = null;
    private Object texturaRelleno = null; //
    private Color colorContorno = null; //Nadamas es para guardar un color solido
    private Color colorRelleno = null;
    private Stroke tipoContorno = null; //El contorno con la separacion y demas
    private float transparencia;
    //boolean creada = false; //Determina si ua figura fue creada por el usuario.

    public PropsFig(ArrayList<PathSegment> pathseg, Object texturaC, Object texturaR, Color colorC, Color colorR, Stroke contorno, float trans)
    {
        this.pathSegments = pathseg;
        this.texturaContorno = texturaC;
        this.texturaRelleno = texturaR;
        this.colorContorno = colorC;
        this.colorRelleno = colorR;
        this.tipoContorno = contorno;
        this.transparencia = trans;
    }
    
    public PropsFig(ArrayList<PathSegment> pathseg)
    {
        this.pathSegments = pathseg;
    }

    public PropsFig()
    {
    }

    public Object getTexturaContorno()
    {
        return texturaContorno;
    }

    public void setTexturaContorno(Object texturaContorno)
    {
        this.texturaContorno = texturaContorno;
    }

    public Object getTexturaRelleno()
    {
        return texturaRelleno;
    }

    public void setTexturaRelleno(Object texturaRelleno)
    {
        this.texturaRelleno = texturaRelleno;
    }

    public Color getColorContorno()
    {
        return colorContorno;
    }

    public void setColorContorno(Color colorContorno)
    {
        this.colorContorno = colorContorno;
    }

    public Color getColorRelleno()
    {
        return colorRelleno;
    }

    public void setColorRelleno(Color colorRelleno)
    {
        this.colorRelleno = colorRelleno;
    }

    public Stroke getTipoContorno()
    {
        return tipoContorno;
    }

    public void setTipoContorno(Stroke tipoContorno)
    {
        this.tipoContorno = tipoContorno;
    }

    public float getTransparencia()
    {
        return transparencia;
    }

    public void setTransparencia(float transparencia)
    {
        this.transparencia = transparencia;
    }

    public ArrayList<PathSegment> getPathSegments()
    {
        return pathSegments;
    }

    public void setPathSegments(ArrayList<PathSegment> pathSegments)
    {
        this.pathSegments = pathSegments;
    }

}
