package app.simplexdev.arcanumocculta.base.spell;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.api.spell.SpellProjectile;
import org.bukkit.entity.Projectile;

import java.time.Duration;

public class AbstractSpell implements Spell {
    @Override
    public String getSpellName() {
        return null;
    }

    @Override
    public String getSpellDescription() {
        return null;
    }

    @Override
    public double getManaCost() {
        return 0;
    }

    @Override
    public Duration getCoolDown() {
        return null;
    }

    @Override
    public int getSpellLevel() {
        return 0;
    }

    @Override
    public boolean isOnCoolDown() {
        return false;
    }

    @Override
    public SpellProjectile<? extends Projectile> getSpellProjectile() {
        return null;
    }
}
