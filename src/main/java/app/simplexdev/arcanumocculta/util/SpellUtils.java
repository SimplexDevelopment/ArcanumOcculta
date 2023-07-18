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

package app.simplexdev.arcanumocculta.util;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.spells.PrimarySpellList;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public final class SpellUtils
{
    public static final String SPELL_PACKAGE = PrimarySpellList.class.getPackageName();
    private static final PrimarySpellList primarySpellList = new PrimarySpellList();

    private SpellUtils()
    {
        throw new AssertionError();
    }

    public static void damage(final Damageable target, final LivingEntity damager, final double damage)
    {
        if (target instanceof Player)
            target.damage(damage % 20.0, damager);
        else
            target.damage(damage);
    }

    public static void applyEffects(final SpellEffect[] effects, final LivingEntity target, final Caster caster)
    {
        for (final SpellEffect effect : effects)
            effect.apply(target, caster);
    }

    public static Spell copyFromPrimaryList(final String id) {
        return SpellUtils.primarySpellList.getSpell(id).dupe();
    }
}
