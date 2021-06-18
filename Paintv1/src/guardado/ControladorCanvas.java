/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardado;

import canvas.MyShape;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kenit
 */
public class ControladorCanvas
{
    private ArrayList<MyShape> shapes;


    public ControladorCanvas()
    {
    }
    
    public ArrayList<MyShape> getSavedProject()
    {
        return shapes;
    }

    public void setShapes(ArrayList<MyShape> shapes)
    {
        this.shapes = shapes;
    }
}
