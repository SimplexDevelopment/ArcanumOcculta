package app.simplexdev.arcanumocculta;

import app.simplexdev.arcanumocculta.command.CasterCommand;
import app.simplexdev.arcanumocculta.command.base.CommandLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcanumOcculta extends JavaPlugin
{

    public static ArcanumOcculta getInstance()
    {
        return JavaPlugin.getPlugin(ArcanumOcculta.class);
    }

    @Override
    public void onEnable()
    {
        // Registers every command contained in the package which CasterCommand is located.
        new CommandLoader(this)
            .register(CasterCommand.class);
    }
}
