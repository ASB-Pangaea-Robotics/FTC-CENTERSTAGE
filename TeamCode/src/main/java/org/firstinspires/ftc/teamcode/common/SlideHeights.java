package org.firstinspires.ftc.teamcode.common;

public enum SlideHeights {


    GROUND(0),
    LOW_STRIPE(1),
    MED_STRIPE(2),
    HIGH_STRIPE(3);

    private final int ticks;

    SlideHeights(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }
}
