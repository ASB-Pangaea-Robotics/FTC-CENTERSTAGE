package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Omnidirectional (Blocks to Java)")
public class Omnidirectional extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor leftFront;

    private Servo Servo1;
    private Servo Servo2;
    private DcMotor rightBack;
    private DcMotor rightFront;
//    private DcMotor ArmMotor1;

    BNO055IMU imu;

    Orientation angles;

    /* This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        int heightVal;
        double MotorPower;
        int ArmMotorPower;
        double dpadSens;
        double joyStickSens;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");

        Servo1 = hardwareMap.get(Servo.class, "Servo1");
        Servo2 = hardwareMap.get(Servo.class, "Servo2");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        // ArmMotor1 = hardwareMap.get(DcMotor.class, "ArmMotor1");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        heightVal = 0;
        MotorPower = .3;
        ArmMotorPower = 1;
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
                telemetry.addData("Heading", angles.firstAngle);
                telemetry.addData("Roll", angles.secondAngle);
                telemetry.addData("Pitch", angles.thirdAngle);

                double x = gamepad1.left_stick_x;
                double y = -gamepad1.left_stick_y;
                double turn = gamepad1.right_stick_x;

                double theta = (Math.atan2(y, x));
                double power = (Math.hypot(x, y))*.6;

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