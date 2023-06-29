package app.simplexdev.arcanumocculta.spell;

import app.simplexdev.arcanumocculta.api.effect.SpellEffect;
import app.simplexdev.arcanumocculta.api.player.Caster;
import app.simplexdev.arcanumocculta.base.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.spell.effect.FireballEffect;
import app.simplexdev.arcanumocculta.spell.projectile.FireballProjectile;

import java.time.Duration;

public final class FireballSpell extends AbstractSpell<FireballEffect> {
    private final Caster caster;

    public FireballSpell(final Caster caster) {
        super("Fireball", "Fires a fireball in the direction you're facing.", new FireballEffect(Duration.ofSeconds(1), 1));
        this.caster = caster;
    }

    @Override
    public FireballProjectile getSpellProjectile() {
        return new FireballProjectile(this, this.caster);
    }
}
