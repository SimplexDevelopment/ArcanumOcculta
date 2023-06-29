package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.effect.SpecialEffect;

public interface SpecialSpell extends Spell
{
    SpecialEffect getSpecialEffect();
}
