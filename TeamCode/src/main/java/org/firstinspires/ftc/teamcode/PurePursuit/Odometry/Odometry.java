package org.firstinspires.ftc.teamcode.PurePursuit.Odometry;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

public class Odometry extends CommandBase {

    static double GlobalX;
    static double GlobalY;
    double GlobalAngle;
    double wheelL;
    double wheelR;
    double wheelH;

    final double EncoderLeftDisFromCenter = 8;
    final double EncoderRightDisFromCenter = -8;
    final double EncoderHDisFromCenter = 8;

    final double wheelDiameterL = 2;
    final double wheelDiameterR = 2;
    final double wheelDiameterH = 2;

    final double wheelCircumferenceL  = wheelDiameterL*Math.PI;
    final double wheelCircumferenceR = wheelDiameterR*Math.PI;
    final double wheelCircumferenceH = wheelDiameterH*Math.PI;

    final int ticksPerRotationR = 8192;
    final int ticksPerRotationL = 8192;
    final int ticksPerRotationH = 8192;

    final double ticksPerInchR = ticksPerRotationR*wheelCircumferenceR;
    final double ticksPerInchL = ticksPerRotationL*wheelCircumferenceL;
    final double ticksPerInchH = ticksPerRotationH*wheelCircumferenceH;
    TelemetryPacket packet = new TelemetryPacket();


    DrivetrainSubsystem drivetrainSubsystem;
    public Odometry(DrivetrainSubsystem drivetrainSubsystem){
        this.drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    public void execute(){
        update();
    }

    public void update(){
        Read();
        GlobalX += RelativeXPos();
        GlobalY += RelativeYPos();
        GlobalAngle += Angle();
        packet.fieldOverlay().drawImage("/dash/ftc.jpg", 24, 24, 48, 48);
        Canvas c = packet.fieldOverlay();
        c.strokeCircle(GlobalX, GlobalY, 10);


    }
    public static void drawRobot(Canvas c) {
        final double ROBOT_RADIUS = 9;

        c.setStrokeWidth(1);
        c.strokeCircle(GlobalX, GlobalY, ROBOT_RADIUS);

    }


    public void Read(){
        wheelH = drivetrainSubsystem.hEncoder()*ticksPerInchH- wheelH;
        wheelL = drivetrainSubsystem.lvEncoder()*ticksPerInchL-wheelL;
        wheelR = drivetrainSubsystem.rvEncoder()*ticksPerInchR-wheelR;
    }

    public double FWD(){
        return (wheelR*EncoderLeftDisFromCenter-wheelL*EncoderRightDisFromCenter)/(EncoderRightDisFromCenter*EncoderLeftDisFromCenter);
    }

    public double Angle(){
        return (wheelR-wheelL)/(EncoderLeftDisFromCenter-EncoderRightDisFromCenter);
    }

    public double STR(){
        return wheelH - EncoderHDisFromCenter*Angle();
    }

    public double R0(){
        return FWD()/Angle();
    }

    public double R1(){
        return STR()/Angle();
    }

    public double RelativeXPos(){
        return R0()*Math.sin(Angle()) - R1()*(1-Math.cos(Angle()));
    }

    public double RelativeYPos(){
        return R1()*Math.sin(Angle()) + R0()*(1-Math.cos(Angle()));
    }




}
