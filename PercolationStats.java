//package com.company;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private int n;
    private int trials;
    private double[] arrValue;//for open sites numbers
    public PercolationStats(int n, int trials)
    {
        if(n <= 0 || trials <= 0)
            throw new IllegalArgumentException(" Sorry, parameters isn't correct ");

        this.n = n;
        this.trials = trials;
        this.arrValue = new double[trials];
        double value;

        for(int i = 0; i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates())
            {
                int raw = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                percolation.open(raw, col);
            }
            value = (double)percolation.numberOfOpenSites()/(double)(n*n);
            this.arrValue[i] = (double) value;
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(this.arrValue);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        if(this.arrValue.length == 1)
        {
            return Double.NaN;
        }
        return StdStats.stddev(this.arrValue);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return this.mean() - 1.96*this.stddev()/(double)Math.sqrt((double)this.arrValue.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return this.mean() + 1.96*this.stddev()/(double) Math.sqrt((double)this.arrValue.length);
    }

    // test client (see below)
    public static void main(String[] args)
    {
        /*int gridSize = Integer.parseInt(args[0]);
        int sampleSize = Integer.parseInt(args[1]);
        PercolationStats tester = new PercolationStats(gridSize, sampleSize);
        System.out.println(tester.mean());
        System.out.println(tester.stddev());
        System.out.println(tester.confidenceLo());
        System.out.println(tester.confidenceHi());*/
    }
}






















