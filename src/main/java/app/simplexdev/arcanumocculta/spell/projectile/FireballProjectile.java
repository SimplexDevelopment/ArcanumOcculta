package app.simplexdev.arcanumocculta.spell.projectile;

import app.simplexdev.arcanumocculta.api.player.Caster;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.base.spell.AbstractSpellProjectile;
import app.simplexdev.arcanumocculta.spell.FireballSpell;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;

public class FireballProjectile extends AbstractSpellProjectile<Fireball> {
    public FireballProjectile(FireballSpell spell,
                                 Caster caster) {
        super(spell, caster, Fireball.class, Particle.FLAME, Particle.SMALL_FLAME, Particle.LAVA);
    }
}
