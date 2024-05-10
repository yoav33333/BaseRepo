package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class TeleOp extends LinearOpMode{
    final int x = 100;
    int y = 1;
    double f = 0.5;
    MotorEx motor;
    SimpleServo simpleServo;
    Servo servo;
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }
    Level level;
    @Override
    public void runOpMode() throws InterruptedException {

        motor = new MotorEx(hardwareMap, "motor");
        simpleServo = new SimpleServo(hardwareMap, "sServo", 0, 180);
        servo = hardwareMap.get(Servo.class, "rServo");

        y = x;
        y = 3;

        motor.setRunMode(Motor.RunMode.PositionControl);
        motor.setRunMode(Motor.RunMode.RawPower);
        motor.setRunMode(Motor.RunMode.VelocityControl);
        motor.setInverted(true);
        motor.setInverted(false);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()){
            level = Level.HIGH;
            switch (level){
                case LOW:
                    motor.set(1);
                    break;
                case HIGH:
                    motor.set(0);
                    break;

                case MEDIUM:

                    break;

                default:


            }




            if (motor.getCurrentPosition() == x){
                motor.stopMotor();
            }
            else if (motor.getVelocity() == x){
                motor.stopMotor();
            }
            else {
                motor.set(1);
            }

            servo.setPosition(1-0);
            simpleServo.getAngle();
            simpleServo.turnToAngle(60);
            simpleServo.disable();
            servo.close();
            MOVE_MOTOR_POS(x);
            motor.getVelocity();
            motor.getCurrentPosition();
            motor.set(1);
            motor.set(0);
            motor.set(-1);
        }
    }

    public void MOVE_MOTOR_POS(int TargetPos){
        motor.setTargetPosition(TargetPos);
    }
}
