
//importing dependencies for the main algorithm
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

//importing dependencies for the PrintStream thing to store results in a text file
import java.io.*;
public class Maximum_Area_Polygon
{
	//********************* CORE CLASS *********************//
    public ArrayList<Point> Maximum_Area_Polygon (ArrayList<Point> points)
    {
    	//creating an other instance of arraylist for storing the set of points that were in final result
        ArrayList<Point> maximumAreaPoints = new ArrayList<Point>();

        //if 3 points were set,return a shallow copy of set of points that were in ArrayList.
        if (points.size() < 3)
            return (ArrayList) points.clone();

        int minPoint = -1, maxPoint = -1;
        //seting the max & min values that an integer takes to the minX & maxX
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++)
        {

        	//if the inputed coordinates are smaller or bigger than the defualt minX and maxX , replace them.
            if (points.get(i).x < minX)
            {
                minX = points.get(i).x;
                minPoint = i;
            }
            if (points.get(i).x > maxX)
            {
                maxX = points.get(i).x;
                maxPoint = i;
            }
        }
        //and store them in the new Arraylist
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        maximumAreaPoints.add(A);
        maximumAreaPoints.add(B);
        points.remove(A);
        points.remove(B);

        //divide into two subsets , deviding section of algorithm.
        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);
            if (pointLocation(A, B, p) == -1)
                leftSet.add(p);
            else if (pointLocation(A, B, p) == 1)
                rightSet.add(p);
        }
        borderSet(A, B, rightSet, maximumAreaPoints);
        borderSet(B, A, leftSet, maximumAreaPoints);

        return maximumAreaPoints;
    }


    public void borderSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull)
    {
    	// if the called sets have one or zero points, what to do?
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1)
        {
            Point p = set.get(0);
            set.remove(p);
            //store the position in final hull ArrayList
            hull.add(insertPosition, p);
            return;
        }

        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++)
        {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);


        // Determine who's to the left of AP
        ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(A, P, M) == 1)
            {
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of PB
        ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(P, B, M) == 1)
            {
                leftSetPB.add(M);
            }
        }
        borderSet(A, P, leftSetAP, hull);
        borderSet(P, B, leftSetPB, hull);

    }

    //distance caculator in cartesian coordinates function
    public int distance(Point A, Point B, Point C)
    {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0)
            num = -num;
        return num;
    }


    public int pointLocation(Point A, Point B, Point P)
    {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }



    //main class of program,the control flow strats from here!

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Welcome to secound algorithm project test client \n It's a java program that uses Divide & Conquer Algorithm to compute the Area of a set of points");
        Scanner SCAN = new Scanner(System.in);
        System.out.println("Enter the number of points you want to input");
        int N = SCAN.nextInt();
        //creating the point object from ArrayList class
        ArrayList<Point> points = new ArrayList<Point>();
        //taking the coordianates and add to the set of points
        System.out.println("Enter the coordinates of each points: <x> <y>");
        for (int i = 0; i < N; i++)
        {
            int x = SCAN.nextInt();
            int y = SCAN.nextInt();
            Point e = new Point(x, y);
            points.add(i, e);
        }
        //creating the instance from the main class
        Maximum_Area_Polygon PointsAreaInstance = new Maximum_Area_Polygon();
        //execute the Maximum_Area_Polygon class for running the algorithm
        ArrayList<Point> p = PointsAreaInstance.Maximum_Area_Polygon(points);

        //printing the results section
        System.out.println("The maximum points in the set of inputed points are printed in the results file ");

        //preparing the output stream to save the file for us
        File myF = new File("results.txt");
        FileOutputStream FOS = new FileOutputStream(myF);
        PrintStream PS = new PrintStream(FOS);
        System.setOut(PS);


        for (int i = 0; i < p.size(); i++)
            System.out.println("(" + p.get(i).x + ", " + p.get(i).y + ")");

        //closing the scanner object
        SCAN.close();
    }
}