/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardado;

import canvas.MyShape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kenit
 */
public class SalvarCargarProyecto
{
    public static void serializeToXML(ControladorCanvas settings) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(JOptionPane.showInputDialog(null, "Ingrese nombre del proyecto") + ".xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener()
        {
            public void exceptionThrown(Exception e)
            {
                System.out.println("Exception! :" + e.toString());
            }
        });
        encoder.writeObject(settings);
        encoder.close();
        fos.close();
    }

    public static ControladorCanvas deserializeFromXML(File file) throws IOException
    {
        // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
        if ((file == null) || (file.getName().equals("")))
        {
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
        } else
        {
            FileInputStream fis = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fis);
            ControladorCanvas decodedSettings = (ControladorCanvas) decoder.readObject();
            decoder.close();
            fis.close();
            return decodedSettings;
        }
        return null;
    }
    
    
    public static void serializeShapes(ArrayList<MyShape> cf) throws FileNotFoundException
    {
        FileOutputStream fos = new FileOutputStream(JOptionPane.showInputDialog(null, "Ingrese nombre del proyecto") + ".xml");
        XMLEncoder xmle = new XMLEncoder(fos);
        ArrayList pathSegmentsCollection = new ArrayList<>();
        for (int i = 0; i < cf.size(); i++)
        {
            ArrayList pathSegments = SalvarObjetos.getSegmentsFromShape(cf.get(i).getShape());
            PropsFig props = new PropsFig(pathSegments);
            //PathBean as = new PathBean(pathSegments);
            pathSegmentsCollection.add(props);
        }
        xmle.writeObject(pathSegmentsCollection);
        xmle.flush();
        xmle.close();
    }

    /**
     * Load the list of shapes from the file system.
     */
    public static ArrayList<MyShape> deserializeShapes(File file) throws FileNotFoundException
    {
        XMLDecoder xmld = new XMLDecoder(new FileInputStream(file));
        ArrayList pathSegProps = (ArrayList) xmld.readObject();
        ArrayList<MyShape> shapes = new ArrayList();
        for (int i = 0; i < pathSegProps.size(); i++)
        {
            PropsFig setprops = (PropsFig) pathSegProps.get(i);
            PathBean pathsegments = new PathBean(setprops.getPathSegments());
            shapes.add(new MyShape(SalvarObjetos.getShapeFromSegments(pathsegments)));
        }
        return shapes;
    }
}
