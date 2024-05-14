package org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning;

public class SPLINE {
    int[] x;
    int[] y;

    // lx and ly store the all x and y co-ordinate generated
    // by the first loop to show the path of curve
    int[] lx = new int[1200];
    int[] ly = new int[1200];

    double t;
    int i = 0;


    public SPLINE(int[] x, int[] y) {
        this.x = x;
        this.y = y;

    }

    // Store the calculated x and y
    // co-ordinate in lx and ly

    public void generatePoints() {
        for (t = 0.0; t <= 1.0; t += 0.001) {
            lx[i] = (int)(Math.pow(1 - t, 3) * x[0] + 3 * t * Math.pow(1 - t, 2) * x[1] + 3 * t * t * (1 - t) * x[2]
                    + Math.pow(t, 3) * x[3]);
            ly[i] = (int)(Math.pow(1 - t, 3) * y[0] + 3 * t * Math.pow(1 - t, 2) * y[1] + 3 * t * t * (1 - t) * y[2]
                    + Math.pow(t, 3) * y[3]);
            i++;
        }

        //TODO return the points
    }


}
