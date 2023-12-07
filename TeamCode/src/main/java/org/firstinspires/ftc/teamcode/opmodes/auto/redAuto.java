package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.teamcode.common.subsystems.ActiveIntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class redAuto extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    MecanumSubsystem mecanumSubsystem;
    ActiveIntakeSubsystem intakeSubsystem;
    OuttakeSubsystem outtakeSubsystem;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initHardware(hardwareMap);


        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                robot.read(intakeSubsystem, outtakeSubsystem);
                robot.loop(intakeSubsystem, outtakeSubsystem);
                robot.write(intakeSubsystem, outtakeSubsystem, mecanumSubsystem);
            }
        }
    }
}
