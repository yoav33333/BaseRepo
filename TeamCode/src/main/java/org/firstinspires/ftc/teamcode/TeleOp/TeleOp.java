package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class TeleOp extends LinearOpMode{
    int x = 1;
    double f = 0.5;
    MotorEx motor;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = new MotorEx(hardwareMap, "motor");
        motor.setRunMode(Motor.RunMode.PositionControl);
        motor.setRunMode(Motor.RunMode.RawPower);
        motor.setRunMode(Motor.RunMode.VelocityControl);
        motor.setInverted(true);
        motor.setInverted(false);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()){
            motor.getVelocity();
            motor.getCurrentPosition();
            motor.setTargetPosition(1000);
            motor.set(1);
            motor.set(0);
            motor.set(-1);
        }
    }
}
