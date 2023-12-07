package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepmeep = new MeepMeep(800);

        RoadRunnerBotEntity redStacksCenterBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40, 30, Math.toRadians(120), Math.toRadians(100), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(-37.5,-61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(-36,-34))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(-36, -59, Math.toRadians(180)))
                                .lineTo(new Vector2d(25,-59))
                                .splineToConstantHeading(new Vector2d(48, -36), 0)
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redStacksLeftBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40,30,Math.toRadians(120),Math.toRadians(100),15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(-37.5, -61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(-36, -34, 0))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(-36, -59, Math.toRadians(180)))
                                .lineTo(new Vector2d(25,-59))
                                .splineToConstantHeading(new Vector2d(48, -30), 0)
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redStacksRightBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40,30,Math.toRadians(120),Math.toRadians(100),15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(-37.5, -61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(-36, -34, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(-36, -59, Math.toRadians(180)))
                                .lineTo(new Vector2d(25,-59))
                                .splineToConstantHeading(new Vector2d(48, -42), 0)
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redBackdropCenterBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40, 30, Math.toRadians(120), Math.toRadians(100), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(14, -61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(12,-34))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(48, -36, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redBackdropLeftBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40, 30, Math.toRadians(120), Math.toRadians(100), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(14, -61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(12,-34, 0))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(48, -30, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redBackdropRightBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40, 30, Math.toRadians(120), Math.toRadians(100), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(14, -61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(12,-34, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(48, -42, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());

        RoadRunnerBotEntity redAltStacksCenterBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(40, 30, Math.toRadians(120), Math.toRadians(100), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(-37.5,-61.5,Math.toRadians(270)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(-36,-34))
                                .waitSeconds(2)
                                .lineTo(new Vector2d(25,-34))
                                .lineToLinearHeading(new Pose2d(48, -36, Math.toRadians(180)))
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(48, -61))
                                .lineToConstantHeading(new Vector2d(60, -61))
                                .build());



        meepmeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                .addEntity(redStacksCenterBot)
//                .addEntity(redStacksLeftBot)
//                .addEntity(redStacksRightBot)
//                .addEntity(redBackdropCenterBot)
//                .addEntity(redBackdropLeftBot)
//                .addEntity(redBackdropRightBot)
                .addEntity(redAltStacksCenterBot)
                .start();
    }
}