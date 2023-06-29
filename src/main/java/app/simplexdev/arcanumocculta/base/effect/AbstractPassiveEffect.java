package app.simplexdev.arcanumocculta.base.effect;

import app.simplexdev.arcanumocculta.api.effect.PassiveEffect;
import app.simplexdev.arcanumocculta.api.effect.PassiveEffects;
import app.simplexdev.arcanumocculta.api.player.Caster;

import java.time.Duration;

public abstract class AbstractPassiveEffect extends AbstractEffect implements PassiveEffect {

    private final Caster wandHolder;
    private final PassiveEffects passiveEffect;

    protected AbstractPassiveEffect(Duration duration, float amplifier, boolean ambient, boolean forceDisplay, Caster wandHolder, PassiveEffects passiveEffect) {
        super(duration, amplifier, ambient, forceDisplay);
        this.wandHolder = wandHolder;
        this.passiveEffect = passiveEffect;
    }

    public AbstractPassiveEffect(Duration duration, float amplifier, boolean forceDisplay, Caster wandHolder, PassiveEffects passiveEffect) {
        super(duration, amplifier, forceDisplay);
        this.wandHolder = wandHolder;
        this.passiveEffect = passiveEffect;
    }

    protected AbstractPassiveEffect(Duration duration, float amplifier, Caster wandHolder, PassiveEffects passiveEffect) {
        super(duration, amplifier);
        this.wandHolder = wandHolder;
        this.passiveEffect = passiveEffect;
    }

    protected AbstractPassiveEffect(Duration duration, Caster wandHolder, PassiveEffects passiveEffect) {
        super(duration);
        this.wandHolder = wandHolder;
        this.passiveEffect = passiveEffect;
    }

    protected AbstractPassiveEffect(Caster wandHolder, PassiveEffects passiveEffect) {
        super();
        this.wandHolder = wandHolder;
        this.passiveEffect = passiveEffect;
    }

    @Override
    public Caster getWandHolder() {
        return wandHolder;
    }

    @Override
    public PassiveEffects getPassiveEffect() {
        return passiveEffect;
    }
}
