package app.simplexdev.arcanumocculta.base.spell;

import app.simplexdev.arcanumocculta.api.effect.Effect;
import app.simplexdev.arcanumocculta.api.spell.Spell;

import java.time.Duration;

public abstract class AbstractSpell<T extends Effect> implements Spell<T> {
    private final String name;
    private final String description;
    private final double manaCost;
    private final Duration coolDown;
    private final int spellLevel;
    private final T spellEffect;

    private long coolDownEnd;

    protected AbstractSpell(String name, String description, double manaCost, Duration coolDown, int spellLevel, T effect) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
        this.spellLevel = spellLevel;
        this.spellEffect = effect;
    }

    protected AbstractSpell(String name, String description, double manaCost, Duration coolDown, T effect) {
        this(name, description, manaCost, coolDown, 1, effect);
    }

    protected AbstractSpell(String name, String description, double manaCost, T effect) {
        this(name, description, manaCost, Duration.ofSeconds(10L), effect);
    }

    protected AbstractSpell(String name, String description, T effect) {
        this(name, description, 5.0, effect);
    }

    @Override
    public String getSpellName() {
        return name;
    }

    @Override
    public String getSpellDescription() {
        return description;
    }

    @Override
    public double getManaCost() {
        return manaCost;
    }

    @Override
    public Duration getCoolDown() {
        return coolDown;
    }

    @Override
    public int getSpellLevel() {
        return spellLevel;
    }

    @Override
    public T getEffect() {
        return spellEffect;
    }
}
