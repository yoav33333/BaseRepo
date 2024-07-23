package org.firstinspires.ftc.teamcode.pedroPathing.localization;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;
import org.firstinspires.ftc.teamcode.pedroPathing.util.NanoTimer;

public class OTOS_localizer extends Localizer {
    SparkFunOTOS otos;
    private Pose startPose;
    private Pose displacementPose;


    public OTOS_localizer(HardwareMap hardwareMap, Pose startPose) {
        otos = hardwareMap.get(SparkFunOTOS.class, "otos");
        otos.begin();
        setStartPose(startPose);
        displacementPose = new Pose();
    }
    /**
     * This returns the current pose estimate from the Localizer.
     *
     * @return returns the pose as a Pose object.
     */
    @Override
    public Pose getPose() {
        return new Pose(otos.getPosition().x, otos.getPosition().y, otos.getPosition().h);
    }


    /**
     * This returns the current velocity estimate from the Localizer.
     *
     * @return returns the velocity as a Pose object.
     */
    @Override
    public Pose getVelocity() {
        return new Pose(otos.getVelocity().x, otos.getVelocity().y, otos.getVelocity().h);
    }


    /**
     * This returns the current velocity estimate from the Localizer as a Vector.
     *
     * @return returns the velocity as a Vector.
     */
    @Override
    public Vector getVelocityVector() {
        return new Vector(otos.getVelocity().x, otos.getVelocity().y);
    }


    /**
     * This sets the start pose of the Localizer. Changing the start pose should move the robot as if
     * all its previous movements were displacing it from its new start pose.
     *
     * @param setStart the new start pose
     */
    @Override
    public void setStartPose(Pose setStart) {
        otos.resetTracking();
        otos.setOffset(new SparkFunOTOS.Pose2D(setStart.getX(), setStart.getY(), setStart.getHeading()));
    }


    /**
     * This sets the current pose estimate of the Localizer. Changing this should just change the
     * robot's current pose estimate, not anything to do with the start pose.
     *
     * @param setPose the new current pose estimate
     */
    @Override
    public void setPose(Pose setPose) {
        otos.resetTracking();
        otos.setPosition(new SparkFunOTOS.Pose2D(setPose.getX(), setPose.getY(), setPose.getHeading()));
    }

    /**
     * This calls an update to the Localizer, updating the current pose estimate and current velocity
     * estimate.
     */
    @Override
    public void update() {

    }

    /**
     * This returns how far the robot has turned in radians, in a number not clamped between 0 and
     * 2 * pi radians. This is used for some tuning things and nothing actually within the following.
     *
     * @return returns how far the robot has turned in total, in radians.
     */
    @Override
    public double getTotalHeading() {
        return otos.getPosition().h;
    }

    /**
     * This returns the multiplier applied to forward movement measurement to convert from encoder
     * ticks to inches. This is found empirically through a tuner.
     *
     * @return returns the forward ticks to inches multiplier
     */
    @Override
    public double getForwardMultiplier() {
        return 0;
    }

    /**
     * This returns the multiplier applied to lateral/strafe movement measurement to convert from
     * encoder ticks to inches. This is found empirically through a tuner.
     *
     * @return returns the lateral/strafe ticks to inches multiplier
     */
    @Override
    public double getLateralMultiplier() {
        return 0;
    }

    /**
     * This returns the multiplier applied to turning movement measurement to convert from encoder
     * ticks to radians. This is found empirically through a tuner.
     *
     * @return returns the turning ticks to radians multiplier
     */
    @Override
    public double getTurningMultiplier() {
        return 0;
    }

    @SuppressLint("DefaultLocale")
    private void configureOtos(SparkFunOTOS.Pose2D offset) {
        telemetry.addLine("Configuring OTOS...");
        telemetry.update();


        otos.setLinearUnit(DistanceUnit.INCH);
        otos.setAngularUnit(DEGREES);


        otos.setOffset(offset);


        otos.setLinearScalar(1.0);
        otos.setAngularScalar(1.0);

        otos.calibrateImu();


        otos.resetTracking();


        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        otos.setPosition(currentPosition);

        SparkFunOTOS.Version hwVersion = new SparkFunOTOS.Version();
        SparkFunOTOS.Version fwVersion = new SparkFunOTOS.Version();
        otos.getVersionInfo(hwVersion, fwVersion);

        telemetry.addLine("OTOS configured! Press start to get position data!");
        telemetry.addLine();
        telemetry.addLine(String.format("OTOS Hardware Version: v%d.%d", hwVersion.major, hwVersion.minor));
        telemetry.addLine(String.format("OTOS Firmware Version: v%d.%d", fwVersion.major, fwVersion.minor));
        telemetry.update();
    }
}

