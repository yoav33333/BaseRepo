package org.firstinspires.ftc.teamcode.PurePursuit.pathFollowing;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PID_V {
    double Kp = 1;
    double Ki = 1;

    double Kd = 1;


    Vector2d lastError = new Vector2d(0, 0);

    Vector2d integralSum = new Vector2d(0, 0);
    Vector2d reference;
    Vector2d position;
    Vector2d error;
    Vector2d derivative;
    Vector2d out;
    public PID_V(Vector2d reference, Vector2d position) {
        this.reference = reference;
        this.position = position;

    }

    // Elapsed timer class from SDK, please use it, it's epic
    ElapsedTime timer = new ElapsedTime();
    public Vector2d RunPID() {
        // obtain the encoder position

        // calculate the error
        error = reference.minus(position);

        // rate of change of the error
        derivative = (error.minus(lastError)).div(timer.seconds());

        // sum of all error over time
        integralSum = integralSum.plus(error.times(timer.seconds()));

        lastError = error;

        // reset the timer for next time
        timer.reset();

        return out = (error.times(Kp)).plus(integralSum.times(Ki)).plus(derivative.times(Kd));



    }

    }

