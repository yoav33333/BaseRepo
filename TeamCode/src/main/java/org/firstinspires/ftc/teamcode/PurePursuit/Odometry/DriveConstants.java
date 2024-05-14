package org.firstinspires.ftc.teamcode.PurePursuit.Odometry;

public class DriveConstants {

    public final static double EncoderLeftDisFromCenter = 8;
    public final static double EncoderRightDisFromCenter = -8;
    public final static double EncoderHDisFromCenter = 8;

    static final double wheelDiameterL = 2;
    static final double wheelDiameterR = 2;
    static final double wheelDiameterH = 2;

    static final double wheelCircumferenceL  = wheelDiameterL*Math.PI;
    static final double wheelCircumferenceR = wheelDiameterR*Math.PI;
    static final double wheelCircumferenceH = wheelDiameterH*Math.PI;

    static final int ticksPerRotationR = 8192;
    static final int ticksPerRotationL = 8192;
    static final int ticksPerRotationH = 8192;

    public final static double ticksPerInchR = ticksPerRotationR*wheelCircumferenceR;
    public final static double ticksPerInchL = ticksPerRotationL*wheelCircumferenceL;
    public final static double ticksPerInchH = ticksPerRotationH*wheelCircumferenceH;
}
