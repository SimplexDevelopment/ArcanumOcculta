package app.simplexdev.arcanumocculta.base.effect;

import app.simplexdev.arcanumocculta.api.effect.Effect;

import java.time.Duration;

public abstract class AbstractEffect implements Effect {
    private final Duration duration;
    private final float amplifier;
    private final boolean ambient;
    private final boolean forceDisplay;

    protected AbstractEffect(Duration duration, float amplifier, boolean ambient, boolean forceDisplay) {
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.forceDisplay = forceDisplay;
    }

    protected AbstractEffect(Duration duration, float amplifier, boolean forceDisplay) {
        this(duration, amplifier, false, forceDisplay);
    }

    protected AbstractEffect(Duration duration, float amplifier) {
        this(duration, amplifier, false, false);
    }

    protected AbstractEffect(Duration duration) {
        this(duration, 1, false, false);
    }

    protected AbstractEffect() {
        this(Duration.ofSeconds(5L), 1, false, false);
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public float getAmplifier() {
        return amplifier;
    }

    @Override
    public boolean isAmbient() {
        return ambient;
    }

    @Override
    public boolean forceDisplay() {
        return forceDisplay;
    }
}
