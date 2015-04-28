/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

/**
 *
 * @author Dinu
 */
// import ann.*;
import java.util.Comparator;

public class KnnComparator implements Comparator
{

    public KnnComparator()
    {
    }

    /**
     * Compare two kNNExample based on their realtive distance to a query point
     */
    public int compare(Object o1, Object o2)
    {
        KnnExample example1 = (KnnExample) o1;
        KnnExample example2 = (KnnExample) o2;
        double dist1 = example1.getRelativeDist();
        double dist2 = example2.getRelativeDist();
        if (dist1 < dist2)
            return -1;
        else if (dist1 == dist2)
            return 0;
        else
            return 1;
    }

    public boolean equals(Object o)
    {
        return(this.equals(o));
    }

}
