package app.simplexdev.arcanumocculta.api.effect;

import java.time.Duration;
import org.bukkit.potion.PotionEffectType;

public interface SpellEffect extends Effect
{
    PotionEffectType getEffectType();
}
