package org.firstinspires.ftc.teamcode.PurePursuit.pathFollowing;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.PurePursuit.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning.PathPlanning;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

import java.util.HashMap;
import java.util.List;

public class PathFollowing extends CommandBase {
    DrivetrainSubsystem drivetrainSubsystem;
    Odometry odometry;
    PathPlanning pathPlanning;
    HashMap<Integer, Pose2d> PointsList;
    PID_V PID;
    int PointIndex = 0;
    int bestPoint = 0;
    Vector2d Difference;
    public PathFollowing(DrivetrainSubsystem drivetrainSubsystem, Odometry odometry, PathPlanning pathPlanning, int bestPoint, int PointIndex){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pathPlanning = pathPlanning;
        this.odometry = odometry;
        PointsList = pathPlanning.Points;
        this.bestPoint = bestPoint;
        this.PointIndex = PointIndex;
    }
    public PathFollowing(DrivetrainSubsystem drivetrainSubsystem, Odometry odometry, PathPlanning pathPlanning, int bestPoint){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pathPlanning = pathPlanning;
        this.odometry = odometry;
        PointsList = pathPlanning.Points;
        this.bestPoint = bestPoint;
    }

    public PathFollowing(DrivetrainSubsystem drivetrainSubsystem, Odometry odometry, PathPlanning pathPlanning){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pathPlanning = pathPlanning;
        this.odometry = odometry;
        PointsList = pathPlanning.Points;
    }

    public void execute(){

        PID = new PID_V(new Vector2d(NextPoint().getX(), NextPoint().getY()), new Vector2d(Read().getX(), Read().getY()));
        PID.RunPID();

    }


    public Pose2d Read(){
        return Odometry.getGlobalsPos();
    }

    public Pose2d NextPoint(){
        for (int i = 0; i < PointsList.size(); i++) {
            if (PointsList.get(i).minus(Read()<Difference)
        }
        return PointsList.get(PointIndex--);
    }

    public void Run() {
        if (NextPoint().)
    }









}
