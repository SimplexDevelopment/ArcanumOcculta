package app.simplexdev.arcanumocculta.base.effect;

import app.simplexdev.arcanumocculta.api.effect.CompoundEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Duration;

public abstract class AbstractCompoundEffect extends AbstractEffect implements CompoundEffect {
    private final PotionEffectType[] effectTypes;

    protected AbstractCompoundEffect(Duration duration, float amplifier, boolean ambient, boolean forceDisplay, PotionEffectType... effectTypes) {
        super(duration, amplifier, ambient, forceDisplay);
        this.effectTypes = effectTypes;
    }

    protected AbstractCompoundEffect(Duration duration, float amplifier, boolean forceDisplay, PotionEffectType... effectTypes) {
        super(duration, amplifier, forceDisplay);
        this.effectTypes = effectTypes;
    }

    protected AbstractCompoundEffect(Duration duration, float amplifier, PotionEffectType... effectTypes) {
        super(duration, amplifier);
        this.effectTypes = effectTypes;
    }

    protected AbstractCompoundEffect(Duration duration, PotionEffectType... effectTypes) {
        super(duration);
        this.effectTypes = effectTypes;
    }

    protected AbstractCompoundEffect(PotionEffectType... effectTypes) {
        super();
        this.effectTypes = effectTypes;
    }

    @Override
    public PotionEffectType[] getEffectTypes() {
        return new PotionEffectType[0];
    }
}
