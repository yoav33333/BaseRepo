package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class DrivetrainSubsystem extends SubsystemBase {

    MotorEx leftFront;
    MotorEx leftBack;
    MotorEx rightBack;
    MotorEx rightFront;


    public DrivetrainSubsystem(HardwareMap hardwareMap){
        leftFront = new MotorEx(hardwareMap, "leftFront");// 0
        leftBack = new MotorEx(hardwareMap, "leftBack"); // 3
        rightBack = new MotorEx(hardwareMap, "rightBack"); // 1/2
        rightFront = new MotorEx(hardwareMap, "rightFront");


    }

    public void AutoDrive(double powerToLF, double powerToLB, double powerToRF, double powerToRB){
        leftFront.set(powerToLF);
        leftBack.set(powerToLB);
        rightBack.set(powerToRB);
        rightFront.set(powerToRF);
    }

    public int lvEncoder() {
        return leftBack.encoder.getPosition();

    }

    public int rvEncoder() {

        return leftFront.encoder.getPosition();

    }

    public int hEncoder() {
        return rightBack.encoder.getPosition();
    }
}
