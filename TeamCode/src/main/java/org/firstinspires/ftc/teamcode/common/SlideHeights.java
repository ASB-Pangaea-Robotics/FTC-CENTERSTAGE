package org.firstinspires.ftc.teamcode.common;

public enum SlideHeights {


    GROUND(0),
    LOW_STRIPE(100),
    MED_STRIPE(200),
    HIGH_STRIPE(300);

    private final int ticks;

    SlideHeights(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }
}
