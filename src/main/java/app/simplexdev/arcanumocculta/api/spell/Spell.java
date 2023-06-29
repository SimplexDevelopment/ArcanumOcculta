package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.effect.SpellEffect;
import org.bukkit.entity.Projectile;

import java.time.Duration;

public interface Spell
{
    String getSpellName();

    String getSpellDescription();

    double getManaCost();

    Duration getCoolDown();

    int getSpellLevel();

    boolean isOnCoolDown();

    SpellProjectile<? extends Projectile> getSpellProjectile();
}
