package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.common.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.common.subsystems.ActiveIntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class Hardware {

    /**= Singleton Logic (DO NOT CHANGE) =**/
    private static Hardware instance;
    public static Hardware getInstance(){
        if (instance == null)
            instance = new Hardware();
        enabled = true;
        return instance;
    }

    /**= Everything Else =**/
    public Motor leftBack;
    public Motor leftFront;
    public Motor rightBack;
    public Motor rightFront;

    public Motor intakeMotor;

    public MotorEx slidesRight;
    public MotorEx slidesLeft;

    public Motor.Encoder slidesRightEncoder;
    public Motor.Encoder slidesLeftEncoder;

    public SampleMecanumDrive drive;

    public static Boolean enabled = false;

    public void initHardware(HardwareMap hardwareMap) {
        leftBack = new Motor(hardwareMap, "leftBack", Motor.GoBILDA.RPM_312);
        leftFront = new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312);
        rightBack = new Motor(hardwareMap, "rightBack", Motor.GoBILDA.RPM_312);
        rightFront = new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312);

        intakeMotor = new Motor(hardwareMap, "intakeMotor", Motor.GoBILDA.RPM_1150);

        slidesRight = new MotorEx(hardwareMap, "slidesRight", Motor.GoBILDA.RPM_435);
        slidesLeft = new MotorEx(hardwareMap, "slidesLeft", Motor.GoBILDA.RPM_435);


        leftBack.setInverted(true);
        leftFront.setInverted(true);

        drive = new SampleMecanumDrive(hardwareMap);
    }


    public void read(ActiveIntakeSubsystem intake, OuttakeSubsystem outtake) {
        intake.read();
        outtake.read();
    }

    public void loop(ActiveIntakeSubsystem intake, OuttakeSubsystem outtake) {
        intake.loop();
        outtake.loop();
    }

    public void write(ActiveIntakeSubsystem intake, OuttakeSubsystem outtake, MecanumSubsystem mecanum) {
        intake.write();
        outtake.write();
        mecanum.write();
    }
}
