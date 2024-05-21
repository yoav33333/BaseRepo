package org.firstinspires.ftc.teamcode.PurePursuit.pathFollowing;

import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PID_H {

    double Reference;
    double Position;
    int Kp = 1;
    int Ki = 1;
    int Kd = 1;


    double integralSum;

    double lastError;

    double error;
    double Derivative;
    double out;

    // Elapsed timer class from SDK, please use it, it's epic
    ElapsedTime timer = new ElapsedTime();

    public PID_H(Rotation2d reference, Rotation2d position) {
        this.Position = position.getDegrees();
        this.Reference = reference.getDegrees();
    }


    public double RunPID() {


        // calculate the error
        error = Reference - Position;

        // rate of change of the error
        Derivative = (error - lastError) / timer.seconds();

        // sum of all error over time
        integralSum = integralSum + (error * timer.seconds());

        out = (Kp * error) + (Ki * integralSum) + (Kd * Derivative);


        lastError = error;

        // reset the timer for next time
        timer.reset();

        return out;


    }
}