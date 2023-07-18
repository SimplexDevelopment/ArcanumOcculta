package app.simplexdev.arcanumocculta.base;

import app.simplexdev.arcanumocculta.api.caster.AbstractCaster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.caster.SpellBook;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import org.bukkit.entity.Player;

public class SimpleCaster extends AbstractCaster
{
    public SimpleCaster(Player player, Wand wand,
                           SpellBook spellBook,
                           CasterLevel level)
    {
        super(player, wand, spellBook, level);
    }


}
