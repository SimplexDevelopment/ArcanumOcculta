package app.simplexdev.arcanumocculta.spell.effect;

import app.simplexdev.arcanumocculta.base.effect.AbstractSpellEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Duration;

public class FireballEffect extends AbstractSpellEffect {
    public FireballEffect(Duration duration, float amplifier) {
        super(duration, amplifier, PotionEffectType.HARM);
    }
}
