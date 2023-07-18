package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import java.util.List;

public interface SpellBook
{
    List<Spell> getSpells();

    void addSpell(Spell spell);

    void removeSpell(Spell spell);

    Spell getSpell(String name);

    Spell getSpell(int index);

    int getSpellCount();

    void setSpell(int index, Spell spell);

    void setSpell(String name, Spell spell);

    void clearSpells();

    Spell randomSpell();

    boolean hasSpell(final Spell spell);
}
