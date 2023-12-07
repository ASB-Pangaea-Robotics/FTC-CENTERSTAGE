package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp
public class CoolDrivingFrMyGuy extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor leftFront;
    private DcMotor rightBack;
    private DcMotor rightFront;

    BHI260IMU imu;

    Orientation angles;

    /* This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        IMU.Parameters imuParameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        new Orientation(
                                AxesReference.INTRINSIC,
                                AxesOrder.ZYX,
                                AngleUnit.RADIANS,
                                0,
                                0,
                                0,
                                0
                        )
                )
        );

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(imuParameters);

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");

        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
                telemetry.addData("Heading", angles.firstAngle);
                telemetry.addData("Roll", angles.secondAngle);
                telemetry.addData("Pitch", angles.thirdAngle);

                double x = gamepad1.left_stick_x;
                double y = -gamepad1.left_stick_y;
                double turn = gamepad1.right_stick_x;

                double theta = (Math.atan2(y, x));
                double power = (Math.hypot(x, y));

                telemetry.addData("Theta", theta);
                theta -= angles.firstAngle;
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
