package app.simplexdev.arcanumocculta;

import app.simplexdev.arcanumocculta.api.event.ExperienceUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcanumOcculta extends JavaPlugin
{

    @Override
    public void onEnable() {
        ;
    }

    public static ArcanumOcculta getInstance()
    {
        return JavaPlugin.getPlugin(ArcanumOcculta.class);
    }
}
