package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

public abstract class AbstractSpellBook implements SpellBook
{
    private final List<Spell> spells = new ArrayList<>();

    @Override
    public boolean hasSpell(final Spell spell)
    {
        return getSpells().contains(spell);
    }

    @Override
    public List<Spell> getSpells()
    {
        return this.spells;
    }

    @Override
    public void addSpell(Spell spell)
    {
        this.spells.add(spell);
    }

    @Override
    public void removeSpell(Spell spell)
    {
        this.spells.remove(spell);
    }

    @Override
    public Spell getSpell(String id)
    {
        return getSpells().stream()
                          .filter(spell -> spell.getId().equalsIgnoreCase(id))
                          .findFirst()
                          .orElse(getSpells().get(0));
    }

    /**
     * Get a spell by its unique id
     *
     * @param uuid the unique id of the spell
     * @return The spell, or null if none found.
     */
    public Spell getSpell(UUID uuid)
    {
        return getSpells().stream()
                          .filter(spell -> spell.getUniqueId().equals(uuid))
                          .findFirst()
                          .orElse(getSpells().get(0));
    }

    @Override
    public Spell getSpell(int index)
    {
        if (index < 0 || index > getSpells().size() - 1)
            return getSpells().get(0);
        return getSpells().get(index);
    }

    @Override
    public int getSpellCount()
    {
        return getSpells().size();
    }

    @Override
    public void setSpell(int index, Spell spell)
    {
        this.spells.set(index, spell);
    }

    @Override
    public void setSpell(String name, Spell spell)
    {
        this.spells.set(getSpells().indexOf(getSpell(name)), spell);
    }

    @Override
    public void clearSpells()
    {
        this.spells.clear();
    }

    /**
     * Add a collection of spells to the spell book
     *
     * @param spells The spells to add
     */
    public void addAll(Collection<? extends Spell> spells)
    {
        this.spells.addAll(spells);
    }

    @Override
    public Spell randomSpell()
    {
        final SplittableRandom random = new SplittableRandom();
        return getSpells().get(random.nextInt(getSpells().size()));
    }
}
