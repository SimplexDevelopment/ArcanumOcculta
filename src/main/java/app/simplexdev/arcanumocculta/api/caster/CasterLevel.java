package app.simplexdev.arcanumocculta.api.caster;

import java.text.MessageFormat;
import java.util.stream.Stream;
import org.bukkit.ChatColor;

public enum CasterLevel
{

    APPRENTICE(1, 250D, "Apprentice", "an Apprentice", ChatColor.WHITE, "Lvl1"),
    PRIMARY(2, 500D, "Primary", "a Primary", ChatColor.RED, "Lvl2"),
    SCHOLAR(3, 750D, "Scholar", "a Scholar", ChatColor.AQUA, "Lvl3"),
    PREFECT(4, 1000D, "Prefect", "a Prefect", ChatColor.BLUE, "Lvl4"),
    GRADUATE(5, 1250D, "Graduate", "a Graduate", ChatColor.GRAY, "Lvl5"),
    ADEPT(6, 1500D, "Adept", "an Adept", ChatColor.DARK_PURPLE, "Lvl6"),
    MAGISTRATE(7, 1750D, "Magister", "a Magister", ChatColor.DARK_GREEN, "Lvl7"),
    HEADMASTER(8, 2000D, "Headmaster", "a Headmaster", ChatColor.GOLD, "Lvl8"),
    ARCH_MAGE(9, 4000D, "Arch-Mage", "an Arch-Mage", ChatColor.BLACK, "Lvl9");

    private final int level;
    private final double nextLevelExp;
    private final String name;
    private final String plural;
    private final ChatColor rankColor;
    private final String suffix;

    CasterLevel(final int level, final double nextLevelExp, final String name, final String plural,
                final ChatColor rankColor,
                final String suffix)
    {
        this.nextLevelExp = nextLevelExp;
        this.level = level;
        this.name = name;
        this.plural = plural;
        this.rankColor = rankColor;
        this.suffix = MessageFormat.format("{0}[{1}{2}{3}]{4}",
                                           ChatColor.DARK_GRAY,
                                           rankColor,
                                           suffix,
                                           ChatColor.DARK_GRAY,
                                           ChatColor.RESET);
    }

    public static CasterLevel fromOrdinal(final int ordinal)
    {
        return Stream.of(CasterLevel.values())
                     .filter(lvl -> lvl.getLevel() == ordinal)
                     .findFirst()
                     .orElse(CasterLevel.APPRENTICE);
    }

    public int getLevel()
    {
        return this.level;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPlural()
    {
        return this.plural;
    }

    public ChatColor getRankColor()
    {
        return this.rankColor;
    }

    public String getSuffix()
    {
        return this.suffix;
    }

    public double getNextLevelExp()
    {
        return this.nextLevelExp;
    }

    public boolean isAtLeast(final CasterLevel level) {
        return this.getLevel() >= level.getLevel();
    }
}
