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

package app.simplexdev.arcanumocculta.spells.flame;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

public class FlameSling extends AbstractSpell
{
    protected FlameSling()
    {
        super("Flame Sling", "flame_sling",
              "Lob a ball of fire at your enemies!",
              CasterLevel.APPRENTICE,
              Damages.LIGHT,
              Durations.MEDIUM,
              ManaCosts.LIGHT_CAST,
              10L);
    }

    @Override
    public SpellEffect[] getSpellEffects()
    {
        final SpellEffect[] effects = new SpellEffect[1];
        effects[0] = SpellUtils.flameEffectBase(baseDamage());
        return effects;
    }

    @Override
    public void cast(Caster caster, Wand wand)
    {
        if (!checkManaCosts(caster))
            return;

        final Entity projectile = prepareProjectile(caster, Material.FIRE_CHARGE,
                                                    caster.bukkit()
                                                          .getLocation().clone()
                                                          .getDirection().multiply(2));

        while (!projectile.isDead()) {
            if (!projectile.getNearbyEntities(1, 1, 1).isEmpty()) {
                applyEffects(projectile.getNearbyEntities(1, 1, 1), caster);
                projectile.remove();
            }

            if (projectile.isOnGround()) {
                projectile.remove();
            }
        }
    }
}