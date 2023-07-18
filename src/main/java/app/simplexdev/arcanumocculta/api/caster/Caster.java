package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.wand.Wand;
import java.util.UUID;
import org.bukkit.entity.Player;

public interface Caster
{
    Wand getWand();

    void setWand(Wand wand);

    SpellBook getSpellBook();

    String getName();

    UUID getUniqueId();

    double getCurrentMana();

    void setCurrentMana(double mana);

    double getMaxMana();

    void setMaxMana(double maxMana);

    CasterLevel getCurrentLevel();

    void setCurrentLevel(CasterLevel level);

    double getCurrentExperience();

    void setCurrentExperience(double experience);

    void addMana(double mana);

    void removeMana(double mana);

    void addExperience(double experience);

    void removeExperience(double experience);

    Player bukkit();
}
