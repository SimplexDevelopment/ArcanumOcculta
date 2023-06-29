package app.simplexdev.arcanumocculta.api.effect;

import java.time.Duration;

public enum PassiveEffects {
    MANA_REGEN(Duration.ofSeconds(10L), 2.0),
    LIFE_STEAL(Duration.ofSeconds(5L), 1.0),
    ABSORPTION(Duration.ofSeconds(30L), 4.0),
    SPELL_SHIELD(Duration.ofSeconds(5L), 5.0),
    IMMUNITY(Duration.ofSeconds(5L), 100.0),
    WITHER(Duration.ofSeconds(5L), 2.0),
    DAMAGE_BOOST(Duration.ofSeconds(5L), 2.75),
    IMPROVED_ACCURACY(Duration.ofSeconds(5L), 5.0);

    private final Duration duration;
    private final double amount;

    PassiveEffects(final Duration duration, final double amount) {
        this.duration = duration;
        this.amount = amount;
    }

    Duration getDuration() {
        return this.duration;
    }

    double getAmount() {
        return this.amount;
    }
}
