package org.firstinspires.ftc.teamcode.common.subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.teamcode.common.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.common.roadrunner.trajectorysequence.TrajectorySequence;

import java.util.function.DoubleSupplier;

public class MecanumSubsystem extends SubsystemBase  {
    public final SampleMecanumDrive drive;

    public MecanumSubsystem(SampleMecanumDrive drive) {
        this.drive = drive;
    }

    public Command followTrajectory(Trajectory trajectory) {
        return new FunctionalCommand(
                () -> drive.followTrajectory(trajectory),
                drive::update, e -> {},
                () -> !drive.isBusy(),
                this
        );
    }

    public Command followTrajectorySequence(TrajectorySequence trajectory) {
        return new FunctionalCommand(
                () -> drive.followTrajectorySequence(trajectory),
                drive::update, e -> {},
                () -> !drive.isBusy(),
                this
        );
    }

    public Command driveRobotCentric(DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX) {
        return new RunCommand(
                () -> drive.setWeightedDrivePower(
                        new Pose2d(
                                leftX.getAsDouble(),
                                leftY.getAsDouble(),
                                -rightX.getAsDouble()
                        )
                ), this
        );
    }

    public Command driveFieldCentric(DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX) {
        Vector2d rotated = new Vector2d(-leftY.getAsDouble(), -leftX.getAsDouble()).rotated(
                -drive.getPoseEstimate().getHeading()
        );

        return new RunCommand(
                () -> drive.setWeightedDrivePower(
                        new Pose2d(
                                rotated.getX(),
                                rotated.getY(),
                                -rightX.getAsDouble()
                        )
                ), this
        );
    }

    public Command driveFieldCentric(DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX, DoubleSupplier gyroAngle) {
        Vector2d rotated = new Vector2d(-leftY.getAsDouble(), -leftX.getAsDouble()).rotated(
                -gyroAngle.getAsDouble()
        );

        return new RunCommand(
                () -> drive.setWeightedDrivePower(
                        new Pose2d(
                                rotated.getX(),
                                rotated.getY(),
                                -rightX.getAsDouble()
                        )
                ), this
        );
    }

    public double[] fieldCentricCalc(Hardware robot, Gamepad gamepad1) {
        robot.orientation = robot.imu.getRobotYawPitchRollAngles();

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        double theta = (Math.atan2(y, x));
        double power = (Math.hypot(x, y));

        theta -= robot.orientation.getYaw(AngleUnit.RADIANS);

        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = (Math.max(Math.abs(sin),Math.abs(cos)));

        double leftBackPow = power * sin/max + turn;
        double leftFrontPow = power * cos/max + turn;
        double rightBackPow = power * cos/max - turn;
        double rightFrontPow = power * sin/max - turn;

        if ((power + Math.abs(turn)) > 1){
            leftBackPow /= power + Math.abs(turn);
            leftFrontPow /= power + Math.abs(turn);
            rightBackPow /= power + Math.abs(turn);
            rightFrontPow /= power + Math.abs(turn);
        }

        return new double[] {
                leftBackPow,
                leftFrontPow,
                rightBackPow,
                rightFrontPow
        };
    }

    public void write() {
        drive.update();
    }
}
