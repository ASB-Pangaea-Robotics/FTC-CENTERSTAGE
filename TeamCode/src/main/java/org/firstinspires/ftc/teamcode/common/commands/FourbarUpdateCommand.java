package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.FourbarPositions;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class FourbarUpdateCommand extends InstantCommand {
    OuttakeSubsystem outtake;
    FourbarPositions position;

    FourbarUpdateCommand(OuttakeSubsystem outtake, FourbarPositions position) {
        this.outtake = outtake;
        this.position = position;
    }

    @Override
    public void execute() {
        outtake.update(position);
    }
}
