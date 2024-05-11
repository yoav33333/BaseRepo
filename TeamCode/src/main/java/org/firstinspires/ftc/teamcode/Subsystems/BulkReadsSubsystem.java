package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class BulkReadsSubsystem extends SubsystemBase {
    List<LynxModule> allHubs;
    LynxModule ControlHub;

    public BulkReadsSubsystem(HardwareMap hardwareMap){
        ControlHub = hardwareMap.get(LynxModule.class, "ControlHub");

        allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
    }

    public void readAll(){
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
    }
    public void ReadControlHub(){
        ControlHub.clearBulkCache();
    }
}
