package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.effect.SpellEffect;

public interface StandardSpell extends Spell
{
    SpellEffect getSpellEffect();
}
