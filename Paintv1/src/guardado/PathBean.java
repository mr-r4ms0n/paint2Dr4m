/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardado;

import java.util.ArrayList;

/**
 *
 * @author kenit
 */
public class PathBean
{

    private ArrayList<PathSegment> pathSegments;

    public PathBean()
    {
    }

    public PathBean(ArrayList<PathSegment> pathSegments)
    {
        this.pathSegments = pathSegments;
    }

    public ArrayList<PathSegment> getPathSegments()
    {
        return pathSegments;
    }

    public void setPathSegments(ArrayList<PathSegment> pathSegments)
    {
        this.pathSegments = pathSegments;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{");
        for (PathSegment pathSegment : pathSegments)
        {
            sb.append(" \n\t");
            sb.append(pathSegment.toString());
        }
        sb.append("  \n");
        sb.append("}");
        return "PathSegments: " + sb.toString();
    }

}
