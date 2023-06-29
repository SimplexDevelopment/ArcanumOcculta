package app.simplexdev.arcanumocculta.base.player;

import app.simplexdev.arcanumocculta.api.player.SpellResistance;

public class SimpleSpellResistance implements SpellResistance {
    private final String name;
    private final double resistance;
    private final int ordinal;

    public SimpleSpellResistance(String spellName, double resistance, int ordinal) {
        this.name = spellName;
        this.resistance = resistance;
        this.ordinal = ordinal;
    }

    @Override
    public String getSpellName() {
        return name;
    }

    @Override
    public double getResistance() {
        return resistance;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }
}
