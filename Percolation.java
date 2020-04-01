//package com.company;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import edu.princeton.cs.algs4.StdRandom;


public class Percolation {
    private int n;
    private int OpenSite = 0;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufdown;
    private int top;
    private int down;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        validateSize(n);
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n*n+2);
        this.ufdown = new WeightedQuickUnionUF(n*n+2);
        top = n*n;
        down = n*n+1;
        grid = new boolean[n*n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        validateIndex(row, col);
        grid[index(row, col)] = true;
        OpenSite++;
        if(row == 1)
        {
            union(index(row, col), top);
        }
        connectedToAdjoining(row, col);
        if (row == n) {
            union(down, index(row, col));
        }
    }

    private void union(int row, int col)
    {
        uf.union(row, col);
        ufdown.union(row, col);
    }

    private void connectedToAdjoining (int row, int col) {
        connectTop(row, col);
        connectBottom(row, col);
        connectLeft(row, col);
        connectRight(row, col);
    }

    private void connectTop(int row, int col) {
        if (row > 1 && isOpen(row - 1, col)) {
            union(index(row - 1, col), index(row, col));
        }
    }

    private void connectBottom(int row, int col) {
        if (row < n && isOpen(row + 1, col)) {
            union(index(row + 1, col), index(row, col));
        }
    }

    private void connectLeft(int row, int col) {
        if (col > 1 && isOpen(row, col - 1)) {
            union(index(row, col - 1), index(row, col));
        }
    }

    private void connectRight(int row, int col) {
        if (col < n && isOpen(row, col + 1)) {
            union(index(row, col + 1), index(row, col));
        }
    }

    private int index(int row, int col)
    {
        return (row-1)*n+col-1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        validateIndex(row, col);
        return grid [ index (row, col) ];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        validateIndex(row, col);
        return uf.connected (top, index ( row, col ) );
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return OpenSite;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return ufdown.connected(top, down);
    }


    // check the size of a table
    private void validateSize(int n)
    {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException
                    ("Size of the grid cannot be less than or equal to zero");
        }
    }

    // check the coordinates
    private void validateIndex(int row, int col)
    {
        if (row < 1 || row > n ||
                col < 1 || col > n) {
            throw new java.lang.IndexOutOfBoundsException(String.format(
                    "Requested cell %d %d was out of the grid's bounds.", row, col));
        }
    }

    // test client (optional)
    public static void main(String[] args)
    {
         /*int tableSide = 200 ;
        int OpenSite;
        Perculation percolation = new Perculation(tableSide);
        while(!percolation.percolates())
        {
            Random raw = new Random();
            Random col = new Random();
            percolation.open(raw.nextInt(tableSide), col.nextInt(tableSide));
        }
        OpenSite = percolation.numberOfOpenSites();
        System.out.println((double)OpenSite/((double)(tableSide*tableSide)));*/
    }
}