package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.BulkReadsSubsystem;

public class BulkReadCommand extends CommandBase {
    BulkReadsSubsystem bulkReadsSubsystem;
    public BulkReadCommand(BulkReadsSubsystem bulkReadsSubsystem){
        this.bulkReadsSubsystem = bulkReadsSubsystem;
        addRequirements(bulkReadsSubsystem);
    }

    public void execute() {
        bulkReadsSubsystem.readAll();
    }
}
