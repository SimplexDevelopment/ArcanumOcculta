package app.simplexdev.arcanumocculta.api.effect;

import app.simplexdev.arcanumocculta.api.player.Caster;

public interface PassiveEffect extends Effect
{
    Caster getWandHolder();

    void onTick();

    PassiveEffects getPassiveEffect();
}
