package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.SlideHeights;
import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class SlideUpdateCommand extends CommandBase {

    OuttakeSubsystem outtake;
    SlideHeights height;

    public SlideUpdateCommand(OuttakeSubsystem outtake, SlideHeights height) {
        this.outtake = outtake;
        this.height = height;
    }

    @Override
    public void execute() {
        outtake.update(height);
    }
}
