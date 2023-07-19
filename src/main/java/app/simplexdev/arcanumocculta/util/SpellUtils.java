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

import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.spells.PrimarySpellList;
import java.util.SplittableRandom;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public final class SpellUtils
{
    public static final String SPELL_PACKAGE = PrimarySpellList.class.getPackageName();
    private static final PrimarySpellList primarySpellList = new PrimarySpellList();
    private static final SplittableRandom RANDOM = new SplittableRandom();

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

    public static Spell copyFromPrimaryList(final String id)
    {
        return SpellUtils.primarySpellList.getSpell(id).dupe();
    }

    public static SpellEffect soulEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            if (target instanceof Player player)
                // Player is freezing when hit, as if they were in powdered snow for 1 minute.
                player.setFreezeTicks(1200);

            damage(target, caster.bukkit(), damage);
        };
    }

    public static SpellEffect flameEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Fire ticks for 15 seconds.
            target.setFireTicks(300);

            damage(target, caster.bukkit(), damage);
        };
    }

    public static SpellEffect witherEffectBase(final Damages baseDamage, final Durations duration)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Wither for 10 seconds.
            target.addPotionEffect(PotionEffectType.WITHER.createEffect((int) duration.getTicks(), 3));

            damage(target, caster.bukkit(), damage);
        };
    }

    public static SpellEffect lightningEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Lightning on target
            target.getWorld().strikeLightning(target.getLocation());

            damage(target, caster.bukkit(), damage);
        };
    }

    public static SpellEffect healEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Heal target
            target.setHealth(target.getHealth() + damage);
        };
    }

    public static SpellEffect regenEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Regen target
            target.addPotionEffect(PotionEffectType.REGENERATION.createEffect(300, 3));
            target.setHealth(target.getHealth() + ((target.getHealth() + damage) / 2));
        };
    }

    public static SpellEffect meteorLikeEffectBase(final Damages baseDamage,
                                                   final Vector incomingVelocity,
                                                   final float size)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Meteor like effect
            // TODO: Make a configuration option to enable/disable explosions destroying blocks.
            target.getWorld().createExplosion(target.getLocation(), size, true, false);
            target.setVelocity(incomingVelocity.normalize().multiply(2).multiply(-1));
            damage(target, caster.bukkit(), damage);
        };
    }

    public static SpellEffect arsLikeEffectBase(final Damages baseDamage)
    {
        return (target, caster) ->
        {
            final var damage = baseDamage.multiply(caster.getWand().getSpellBonus());
            // Ars Magica like effect
            target.setVelocity(caster.bukkit().getLocation().clone().getDirection().multiply(2));
            if (target instanceof Player player)
                player.setFreezeTicks(1200);
            for (int i = 0; i < 50; i++)
            {
                target.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,
                                                target.getLocation(),
                                                0,
                                                RANDOM.nextDouble(-2, 2),
                                                RANDOM.nextDouble(-2, 2),
                                                RANDOM.nextDouble(-2, 2));
            }

            damage(target, caster.bukkit(), damage);
        };
    }
}
