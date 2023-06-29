package app.simplexdev.arcanumocculta.api.display;

import app.simplexdev.arcanumocculta.api.player.Caster;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Display;

public interface SpellDisplay<T extends Display> {
    T getDisplay();

    Location getLocation();

    World getWorld();

    Caster getWhoCast();

    Spell getCastSpell();

    Particle[] getParticles();

    void display(boolean force);
}
