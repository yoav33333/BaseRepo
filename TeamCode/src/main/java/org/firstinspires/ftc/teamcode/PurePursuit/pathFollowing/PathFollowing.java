package org.firstinspires.ftc.teamcode.PurePursuit.pathFollowing;

import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchH;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchL;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchR;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.PurePursuit.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning.PathPlanning;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
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
    Pose2d bestPoint;
    Vector2d Difference;

    double wheelH;

    double wheelL;
    double wheelR;

    PID_H pidH;

    int CurrentPointIndex = 0;

    Vector2d MovingVector;
    Rotation2d FinalAngle;

    public PathFollowing(DrivetrainSubsystem drivetrainSubsystem, PathPlanning pathPlanning){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pathPlanning = pathPlanning;
        FinalAngle = pathPlanning.MainPoints.get(pathPlanning.MainPoints.size()-1).getRotation();

    }

    public void execute(){

        Run();

    }


    public void ReadOdometry(){
        wheelH = drivetrainSubsystem.hEncoder()*ticksPerInchH- wheelH;
        wheelL = drivetrainSubsystem.lvEncoder()*ticksPerInchL-wheelL;
        wheelR = drivetrainSubsystem.rvEncoder()*ticksPerInchR-wheelR;
    }

    public Pose2d Read(){
        ReadOdometry();
        odometry = new Odometry(wheelH,wheelL,wheelR);
        return Odometry.getGlobalsPos();
    }

    public Pose2d NextPoint(){

        //TODO
        return PointsList.get(PointIndex--);
    }

    public Vector2d Pose2dToVector2d(Pose2d pose2d){

        return new Vector2d(pose2d.getX(), pose2d.getY());
    }

    public void RunMecanum(){
        drivetrainSubsystem.AutoDrive(MovingVector.getX(), MovingVector.getY(), pidH.out, Read().getHeading());
    }

    public void Run() {
        if (NextPoint() == bestPoint){
            PID = new PID_V(Pose2dToVector2d(bestPoint), Pose2dToVector2d(Read()));
            MovingVector = PID.RunPID();
        }
        pidH = new PID_H(FinalAngle, Read().getRotation());
        RunMecanum();

    }









}
