package app.simplexdev.arcanumocculta.api.effect;

import org.bukkit.potion.PotionEffectType;

public interface CompoundEffect extends Effect {
    PotionEffectType[] getEffectTypes();
}
