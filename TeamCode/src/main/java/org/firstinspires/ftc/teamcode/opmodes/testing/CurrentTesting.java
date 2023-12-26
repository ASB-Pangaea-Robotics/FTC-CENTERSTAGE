package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp
public class CurrentTesting extends LinearOpMode {

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

                if (gamepad1.b)
                    leftBack.setPower(1);
                else if (gamepad1.a)
                    leftFront.setPower(1);
                else if (gamepad1.x)
                    rightBack.setPower(1);
                else if (gamepad1.y)
                    rightFront.setPower(1);
                else {
                    leftBack.setPower(0);
                    leftFront.setPower(0);
                    rightBack.setPower(0);
                    rightFront.setPower(0);
                }

                telemetry.addData("leftBack", leftBack.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("leftFront", leftFront.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("rightBack", rightBack.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("rightFront", rightFront.getCurrent(CurrentUnit.AMPS));

                telemetry.update();
            }
        }
    }
}
