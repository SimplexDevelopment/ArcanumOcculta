/*
 * Copyright (c) 2023 Simplex Development Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * with the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS," WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package app.simplexdev.arcanumocculta.spells.wither;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public final class WitherMeteorite extends AbstractSpell
{

    public WitherMeteorite()
    {
        super("Wither Meteorite",
              "wither_meteorite",
              "Call down a wither skull from the heavens to wreak havoc on your enemies.",
              CasterLevel.ARCH_MAGE,
              Damages.OVERKILL,
              Durations.MEDIUM,
              ManaCosts.IMMENSE_CAST,
              600L);
    }

    @Override
    public SpellEffect[] getSpellEffects()
    {
        final SpellEffect[] effects = new SpellEffect[2];
        effects[0] = SpellUtils.witherEffectBase(baseDamage(), effectDuration());
        return effects;
    }

    @Override
    public void cast(Caster caster, Wand wand)
    {
        if (this.checkManaCosts(caster))
            return;

        final Location location = caster.bukkit().getEyeLocation().clone().add(0, 256, 0);
        final Vector direction = location.getDirection().add(new Vector(0, -5, 0));
        final Entity projectile = prepareProjectile(caster,
                                                    Material.WITHER_SKELETON_SKULL,
                                                    direction);

        getSpellEffects()[1] = SpellUtils.meteorLikeEffectBase(baseDamage(), direction, 4F);

        while (!projectile.isDead())
        {
            if (!projectile.getNearbyEntities(5, 5, 5).isEmpty())
            {
                final List<Entity> e = projectile.getNearbyEntities(5, 5, 5);
                applyEffects(e, caster);
                projectile.remove();
            }

            if (projectile.isOnGround())
            {
                simulateExplosion(projectile.getLocation(), 4F, true);
                projectile.remove();
            }
        }
    }
}
