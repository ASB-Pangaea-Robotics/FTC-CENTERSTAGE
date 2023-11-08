package org.firstinspires.ftc.teamcode.opmodes.auto;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous
public class TagTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Hardware robotHardware = Hardware.getInstance();

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .build();

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            while (opModeIsActive() && !isStopRequested()) {
                if (tagProcessor.getDetections().size() > 0) {
                    AprilTagDetection tag = tagProcessor.getDetections().get(0);

                    telemetry.addData("X", tag.ftcPose.x);
                    telemetry.addData("Y", tag.ftcPose.y);
                    telemetry.addData("Z", tag.ftcPose.z);
                    telemetry.addData("Roll", tag.ftcPose.roll);
                    telemetry.addData("Pitch", tag.ftcPose.pitch);
                    telemetry.addData("Yaw", tag.ftcPose.yaw);
                }
                telemetry.update();
            }
        }
    }
}
