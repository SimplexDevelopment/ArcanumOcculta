package app.simplexdev.arcanumocculta;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcanumOcculta extends JavaPlugin
{
    @Override
    public void onEnable() {

    }

    public static ArcanumOcculta getInstance()
    {
        return JavaPlugin.getPlugin(ArcanumOcculta.class);
    }
}
