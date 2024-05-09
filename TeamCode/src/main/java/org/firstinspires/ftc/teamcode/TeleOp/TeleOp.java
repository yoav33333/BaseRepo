package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TeleOp extends LinearOpMode {
    //Variables
    @Override
    public void runOpMode() throws InterruptedException {
        //init


        waitForStart();
        while (opModeIsActive()){
            func(1);
            //Running Code
        }


    }
    //function

    public void func(double x){
    }
}
