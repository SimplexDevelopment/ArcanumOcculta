package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.effect.CompoundEffect;

public interface CompoundSpell extends Spell
{
    CompoundEffect getEffect();
}
