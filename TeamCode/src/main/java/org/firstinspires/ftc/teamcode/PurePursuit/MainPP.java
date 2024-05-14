package org.firstinspires.ftc.teamcode.PurePursuit;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Commands.BulkReadCommand;
import org.firstinspires.ftc.teamcode.PurePursuit.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.PurePursuit.pathFollowing.PathFollowing;
import org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning.PathPlanning;
import org.firstinspires.ftc.teamcode.Subsystems.BulkReadsSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

import java.util.List;

public class MainPP extends CommandOpMode {
    DrivetrainSubsystem drivetrainSubsystem;
    BulkReadsSubsystem bulkReadsSubsystem;
    TelemetryPacket packet;
    BulkReadCommand bulkReadCommand;
    Odometry odometry;
    PathFollowing pathFollowing;
    PathPlanning pathPlanning;
    List<Pose2d> MainPoints;


    @Override
    public void initialize() {
        MainPoints.add(new Pose2d(0, 0, new Rotation2d(0)));
        MainPoints.add(new Pose2d(10, 0, new Rotation2d(0)));
        MainPoints.add(new Pose2d(10, 10, new Rotation2d(0)));
        pathPlanning = new PathPlanning(MainPoints);
        packet = new TelemetryPacket();
        bulkReadsSubsystem = new BulkReadsSubsystem(hardwareMap);
        bulkReadCommand = new BulkReadCommand(bulkReadsSubsystem);
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap);
        odometry = new Odometry(drivetrainSubsystem, packet);
        pathFollowing = new PathFollowing(drivetrainSubsystem, odometry, pathPlanning);




        bulkReadsSubsystem.setDefaultCommand(bulkReadCommand);

    }
}
