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
    String getName();

    String getId();

    default UUID getUniqueId()
    {
        return UUID.nameUUIDFromBytes(this.getName().getBytes());
    }

    String getDescription();

    CasterLevel getLevelRequirement();

    Damages baseDamage();

    SpellEffect[] getSpellEffects();

    Durations effectDuration();

    ManaCosts manaCost();

    long coolDown();

    boolean isUnlocked(final Caster caster);

    void cast(final Caster caster, final Wand wand);

    /**
     * Used to create a copy of the spell for player spell books.
     * Should only ever be used by the primary list when initializing a player's spell book.
     *
     * @return a copy of the spell.
     */
    Spell dupe();
}
