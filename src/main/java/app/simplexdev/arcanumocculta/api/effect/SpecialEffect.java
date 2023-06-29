package app.simplexdev.arcanumocculta.api.effect;

import org.bukkit.entity.LivingEntity;

public interface SpecialEffect extends Effect {
    void applyEffect(final LivingEntity target);
}
