package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.teamcode.common.commands.DriveToPointCommand;
import org.firstinspires.ftc.teamcode.common.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.common.subsystems.ActiveIntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

@Autonomous
public class AutoTest extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    MecanumSubsystem mecanumSubsystem;
    ActiveIntakeSubsystem intakeSubsystem;
    OuttakeSubsystem outtakeSubsystem;


    TrajectorySequence SpikePlaceTrajectory;
    Trajectory parkTraj;

    @Override
    public void runOpMode() throws InterruptedException {
        CommandScheduler.getInstance().reset();

        robot.initHardware(hardwareMap);
        mecanumSubsystem = new MecanumSubsystem(robot.drive);
        outtakeSubsystem = new OuttakeSubsystem(robot);

        if(opModeInInit()) {}

        robot.drive.setPoseEstimate(new Pose2d(0, 0, 0));

        SpikePlaceTrajectory = robot.drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(20.0, 20.0, 180))
                .splineTo(new Vector2d(60,60), 0)
                .build();

        parkTraj = robot.drive.trajectoryBuilder(new Pose2d())
                .lineToConstantHeading(new Vector2d(0,60))
                .build();

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new DriveToPointCommand(robot.drive, SpikePlaceTrajectory),
                                new DriveToPointCommand(robot.drive, parkTraj)
//                                new SlideUpdateCommand(outtakeSubsystem, SlideHeights.GROUND)

                        )
                )
        );

        waitForStart();

        while (opModeIsActive()) {
            CommandScheduler.getInstance().run();
            robot.read(intakeSubsystem, outtakeSubsystem);
            robot.loop(intakeSubsystem, outtakeSubsystem);
            robot.write(intakeSubsystem, outtakeSubsystem, mecanumSubsystem);
        }
    }
}
