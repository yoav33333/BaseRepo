package org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;

public class Line {
    Pose2d pose2d;
    public Line(Pose2d pose2d){
        this.pose2d = pose2d;
    }

    public Vector2d FinalPoint(Pose2d pose2d){
        return new Vector2d(pose2d.getX(), pose2d.getY());
    }

    public Rotation2d FinalAngle(Pose2d pose2d){
        return pose2d.getRotation();
    }
}
