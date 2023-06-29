package app.simplexdev.arcanumocculta.api.effect;

import org.bukkit.potion.PotionEffectType;

public interface SpellEffect extends Effect {
    PotionEffectType getEffectType();
}
