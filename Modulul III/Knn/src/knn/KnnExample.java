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

/**
 * This class describes a particular example for the kNN algorithm.
 */
public class KnnExample extends Example
{

    private double relativeDistance;         // The distance this example is from another example
    private double weight;                   // Weight given to this example
    private int id;                          // A unique id for this example
    private static double[] featureWeights;  // Used to compute weighted distance

    /**
     * Constructor
     */
    public KnnExample(Example example, int id)
    {
        super(example.getClassLabel(), example.getAttributes());
        this.id = id;
        this.weight = 1;  // by default each weight is set to 1
    }

    /**
     * Set the weight of this example based on the kernel width.
     * If kernel = 0 then unweighted kNN.
     * If kernel = 1 then weighted kNN.
     */
    public void setWeight(int kernel)
    {
        this.weight = 1.0/Math.exp(kernel * relativeDistance);
    }

    /**
     * Assumes feature weights have been set
     * Compute the weighted distance relative to example
     */
    public void setRelativeDist(Example example)
    {
        double sum=0;
        for (int i=0; i < example.getAttributes().length; i++)
        {
            sum = sum + featureWeights[i] * (Math.pow(getAttribute(i) - example.getAttribute(i), 2));
        }
        this.relativeDistance = Math.sqrt(sum);
    }

    /**
     * Two kNN examples are equal if they have the same ids
     */
    public boolean equals(KnnExample example)
    {
        return (this.id == example.getId());
    }


    /**
     * Return the relative distance of this kNN example
     */
    public double getRelativeDist()
    {
        return relativeDistance;
    }

    /**
     * Return the weight of this kNN example
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Return the id of this kNN example
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the feature weights to the specified values
     */
    public static void setFeatureWeights(double[] featureWeights)
    {
        int numWeights = featureWeights.length;
        KnnExample.featureWeights = new double[numWeights];
        for (int i=0; i < numWeights; i++)
            KnnExample.featureWeights[i] = featureWeights[i];
    }

    /**
     * Default feature weights to one
     */
    public static void setFeatureWeights(int attributeNum)
    {
        KnnExample.featureWeights = new double[attributeNum];
        for (int i=0; i < attributeNum; i++)
            KnnExample.featureWeights[i] = 1;
    }
}
