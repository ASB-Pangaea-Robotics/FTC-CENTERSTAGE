package org.firstinspires.ftc.teamcode.common.commands;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.common.roadrunner.trajectorysequence.TrajectorySequence;

public class DriveToPointCommand extends CommandBase {

    Trajectory trajectory;
    TrajectorySequence sequence;
    SampleMecanumDrive drive;

    public DriveToPointCommand(SampleMecanumDrive drive, Trajectory trajectory) {
        this.trajectory = trajectory;
        this.drive = drive;
    }

    public DriveToPointCommand(SampleMecanumDrive drive, TrajectorySequence sequence) {
        this.sequence = sequence;
        this.drive = drive;
    }

    @Override
    public void execute() {
        if (sequence == null && trajectory != null)
            drive.followTrajectoryAsync(trajectory);
        else if (sequence != null && trajectory == null)
            drive.followTrajectorySequenceAsync(sequence);
        else
            throw new IllegalArgumentException(
                    "Both Sequence & Trajectory were initialized, I don't know how!"
            );
    }

    @Override
    public boolean isFinished() {
        return !drive.isBusy();
    }
}
