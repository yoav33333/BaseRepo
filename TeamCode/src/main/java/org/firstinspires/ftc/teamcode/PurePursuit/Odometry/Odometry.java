package org.firstinspires.ftc.teamcode.PurePursuit.Odometry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

public class Odometry extends CommandBase {

    double GlobalX;
    double GlobalY;
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

    public double RelativeXPos(){
        return FWD()*Math.cos(Angle()) - STR()*Math.sin(Angle());
    }

    public double RelativeYPos(){
        return STR()*Math.cos(Angle()) + FWD()*Math.sin(Angle());
    }




}
