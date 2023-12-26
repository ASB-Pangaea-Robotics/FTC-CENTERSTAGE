package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.teamcode.common.subsystems.ActiveIntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class SoloTeleOp extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    MecanumSubsystem mecanumSubsystem;
    ActiveIntakeSubsystem intakeSubsystem;
    OuttakeSubsystem outtakeSubsystem;

    private double loopTime = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initHardware(hardwareMap);

        mecanumSubsystem = new MecanumSubsystem(robot.drive);
        intakeSubsystem = new ActiveIntakeSubsystem(robot);
        outtakeSubsystem = new OuttakeSubsystem(robot);

        waitForStart();

        while (opModeIsActive()) {

            robot.setDrivePowers(mecanumSubsystem.fieldCentricCalc(robot, gamepad1));

            double loop = System.nanoTime();
            telemetry.addData("hz ", 1000000000 / (loop - loopTime));
            loopTime = loop;

            robot.read(intakeSubsystem, outtakeSubsystem);
            robot.loop(intakeSubsystem, outtakeSubsystem);
            robot.write(intakeSubsystem, outtakeSubsystem);
        }
    }
}
