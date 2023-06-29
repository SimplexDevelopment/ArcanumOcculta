package app.simplexdev.arcanumocculta.api.effect;

import java.time.Duration;

public interface Effect
{
    Duration getDuration();

    float getAmplifier();

    boolean isAmbient();

    boolean forceDisplay();
}
