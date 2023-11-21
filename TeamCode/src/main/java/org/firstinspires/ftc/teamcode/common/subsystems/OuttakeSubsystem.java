package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.common.Hardware;
import org.firstinspires.ftc.teamcode.common.SlideHeights;

public class OuttakeSubsystem extends SubsystemBase {

    private final Hardware robotHardware;

    PIDController controller;

    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;

    int currentPosition;
    int targetPosition;

    int allowedError = 20;

    public OuttakeSubsystem(Hardware robotHardware) {
        this.robotHardware = robotHardware;

        this.controller.setPID(kP, kI, kD);
    }


    public void update(SlideHeights height) {
        targetPosition = height.getTicks();
    }

    public void read() {
        currentPosition = (robotHardware.slidesLeft.getCurrentPosition() +
                robotHardware.slidesRight.getCurrentPosition())/2;
    }

    public void loop() {
        
        this.controller.calculate(currentPosition, targetPosition);
    }

    public void write() {

    }
}
