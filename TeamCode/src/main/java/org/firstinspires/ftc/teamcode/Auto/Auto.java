package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class Auto extends LinearOpMode {
    //Variables
    private String VisioResult;
    private PathChain gotoSpike;
    private Pose StartingPose = new Pose(0, 0, 0);

    private Pose SpikeLeftPose = new Pose(5, 5, 5);
    private Pose SpikeRightPose = new Pose(5, 5, 5);
    private Pose SpikeMidPose = new Pose(5, 5, 5);

    private Pose SpikePose;
    private Follower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        //init
        follower = new Follower(hardwareMap, true);

        switch (VisioResult) {
            case "Left":
                SpikePose = SpikeLeftPose;
                break;
            case "Right":
                SpikePose = SpikeRightPose;
                break;
            default:
                SpikePose = SpikeMidPose;
        }

        gotoSpike = follower.pathBuilder().addPath(
                new BezierCurve(new Point(StartingPose), new Point(SpikePose))).setConstantHeadingInterpolation(SpikePose.getHeading()).build();


        waitForStart();
        follower.followPath(gotoSpike);
        //Running Code



    }
    //function

    public void func(double x){
    }
}
