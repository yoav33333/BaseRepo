package org.firstinspires.ftc.teamcode.PurePursuit.Odometry;

import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.EncoderHDisFromCenter;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.EncoderLeftDisFromCenter;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.EncoderRightDisFromCenter;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchH;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchL;
import static org.firstinspires.ftc.teamcode.PurePursuit.Odometry.DriveConstants.ticksPerInchR;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

public class Odometry {

    static double GlobalX;
    static double GlobalY;
    Rotation2d GlobalAngle;
    double wheelL;
    double wheelR;
    double wheelH;


    TelemetryPacket packet;
    public static Pose2d robotGlobalPos;

    DrivetrainSubsystem drivetrainSubsystem;
    public void ResetPose(double globalX, double globalY, Rotation2d globalAngle){
        GlobalX = globalX;
        GlobalY = globalY;
        GlobalAngle = globalAngle;
    }

    public Odometry(double wheelH, double wheelL, double wheelR){
        this.wheelH = wheelH;
        this.wheelL = wheelL;
        this.wheelR = wheelR;

    }



    public void update(){
        Read();
        GlobalX += RelativeXPos();
        GlobalY += RelativeYPos();
        GlobalAngle.rotateBy(new Rotation2d(Math.toRadians(Angle())));
        robotGlobalPos = new Pose2d(GlobalX,GlobalY,GlobalAngle);
        packet.fieldOverlay().drawImage("/dash/ftc.jpg", 24, 24, 48, 48);
        Canvas c = packet.fieldOverlay();
        c.strokeCircle(GlobalX, GlobalY, 10);



    }


    public static Pose2d getGlobalsPos(){
        return robotGlobalPos;
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
