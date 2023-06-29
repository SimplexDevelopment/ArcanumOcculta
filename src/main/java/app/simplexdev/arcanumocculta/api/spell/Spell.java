package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.effect.Effect;
import app.simplexdev.arcanumocculta.api.effect.EffectProvider;
import org.bukkit.entity.Projectile;

import java.time.Duration;

public interface Spell<T extends Effect> {
    String getSpellName();

    String getSpellDescription();

    double getManaCost();

    Duration getCoolDown();

    int getSpellLevel();

    SpellProjectile<? extends Projectile> getSpellProjectile();

    T getEffect();
}
