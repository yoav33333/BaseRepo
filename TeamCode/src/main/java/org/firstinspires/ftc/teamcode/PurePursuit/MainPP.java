package org.firstinspires.ftc.teamcode.PurePursuit;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.BulkReadCommand;
import org.firstinspires.ftc.teamcode.PurePursuit.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Subsystems.BulkReadsSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

public class MainPP extends CommandOpMode {
    DrivetrainSubsystem drivetrainSubsystem;
    BulkReadsSubsystem bulkReadsSubsystem;

    BulkReadCommand bulkReadCommand;
    Odometry odometry;
    @Override
    public void initialize() {
        bulkReadsSubsystem = new BulkReadsSubsystem(hardwareMap);
        bulkReadCommand = new BulkReadCommand(bulkReadsSubsystem);
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap);
        odometry = new Odometry(drivetrainSubsystem);

        bulkReadsSubsystem.setDefaultCommand(bulkReadCommand);
        schedule(odometry);

    }
}
