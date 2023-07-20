package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import java.util.UUID;

public interface Spell
{
    /**
     * @return The display name of the spell.
     */
    String getName();

    /**
     * @return The id of the spell.
     */
    String getId();

    /**
     * @return The unique id of the spell.
     */
    default UUID getUniqueId()
    {
        return UUID.nameUUIDFromBytes(this.getName().getBytes());
    }

    /**
     * @return The description of the spell.
     */
    String getDescription();

    /**
     * @return The level requirement of the spell.
     */
    CasterLevel getLevelRequirement();

    /**
     * @return The base damage of the spell.
     */
    Damages baseDamage();

    /**
     * @return An array of all the applicable spell effects.
     */
    SpellEffect[] getSpellEffects();

    /**
     * @return How long each effect should last.
     */
    Durations effectDuration();

    /**
     * @return How much mana this spell consumes.
     */
    ManaCosts manaCost();

    /**
     * @return How long until the spell can be used again.
     */
    long coolDown();

    /**
     * Checks if the caster has this spell unlocked.
     * This is based on their level and if the spell is in their spell book.
     *
     * @param caster The caster to check.
     * @return True if the caster has this spell unlocked.
     */
    boolean isUnlocked(final Caster caster);

    /**
     * Casts the spell.
     *
     * @param caster The caster casting the spell.
     * @param wand   The wand the caster is using.
     */
    void cast(final Caster caster, final Wand wand);

    /**
     * Used to create a copy of the spell for player spell books.
     * Should only ever be used by the primary list when initializing a player's spell book.
     *
     * @return a copy of the spell.
     */
    Spell dupe();
}
