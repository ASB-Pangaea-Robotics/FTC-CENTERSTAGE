package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp
public class CoolDrivingFrMyGuy extends LinearOpMode {

    private DcMotorEx leftBack;
    private DcMotorEx leftFront;
    private DcMotorEx rightBack;
    private DcMotorEx rightFront;

    BHI260IMU imu;
    YawPitchRollAngles orientation;

    /* This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                        )
                )
        );
        imu.resetYaw();

        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");

        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {

                orientation = imu.getRobotYawPitchRollAngles();

                telemetry.addData("Heading", orientation.getYaw(AngleUnit.RADIANS));
                telemetry.addData("Roll", orientation.getRoll(AngleUnit.RADIANS));
                telemetry.addData("Pitch", orientation.getPitch(AngleUnit.RADIANS));

                double x = gamepad1.left_stick_x;
                double y = -gamepad1.left_stick_y;
                double turn = gamepad1.right_stick_x;

                double theta = (Math.atan2(y, x));
                double power = (Math.hypot(x, y));
                telemetry.addData("Theta", theta);
                theta -= orientation.getYaw(AngleUnit.RADIANS);
                telemetry.addData("New Theta", theta);
                telemetry.update();

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

                leftBack.setPower(leftBackPow);
                leftFront.setPower(leftFrontPow);
                rightBack.setPower(rightBackPow);
                rightFront.setPower(rightFrontPow);



                telemetry.update();
            }
        }
    }
}
