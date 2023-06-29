package app.simplexdev.arcanumocculta.base.effect;

import app.simplexdev.arcanumocculta.api.effect.SpellEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Duration;

public abstract class AbstractSpellEffect extends AbstractEffect implements SpellEffect {
    private final PotionEffectType effectType;

    protected AbstractSpellEffect(Duration duration, float amplifier, boolean ambient, boolean forceDisplay, PotionEffectType effectType) {
        super(duration, amplifier, ambient, forceDisplay);
        this.effectType = effectType;
    }

    protected AbstractSpellEffect(Duration duration, float amplifier, boolean forceDisplay, PotionEffectType effectType) {
        super(duration, amplifier, forceDisplay);
        this.effectType = effectType;
    }

    protected AbstractSpellEffect(Duration duration, float amplifier, PotionEffectType effectType) {
        super(duration, amplifier);
        this.effectType = effectType;
    }

    protected AbstractSpellEffect(Duration duration, PotionEffectType effectType) {
        super(duration);
        this.effectType = effectType;
    }

    protected AbstractSpellEffect(PotionEffectType effectType) {
        super();
        this.effectType = effectType;
    }

    @Override
    public PotionEffectType getEffectType() {
        return effectType;
    }
}
