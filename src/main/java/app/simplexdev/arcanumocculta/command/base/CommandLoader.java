/*
 * Copyright (c) 2023 Simplex Development Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * with the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS," WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package app.simplexdev.arcanumocculta.command.base;

import app.simplexdev.arcanumocculta.ArcanumOcculta;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.reflections.Reflections;

public class CommandLoader
{
    private final ArcanumOcculta plugin;

    public CommandLoader(final ArcanumOcculta plugin)
    {
        this.plugin = plugin;
    }

    private void registerCommand(final Commander command)
    {
        final CommandDelegate delegate = new CommandDelegate(this.plugin, command);
        Bukkit.getCommandMap().register("arcaneum", delegate);
    }

    public void register(final Class<? extends Commander> reference)
    {
        new Reflections(reference.getPackage().getName())
            .getSubTypesOf(Commander.class)
            .stream()
            .map(c ->
                 {
                     try
                     {
                         return c.getDeclaredConstructor();
                     }
                     catch (ReflectiveOperationException ex)
                     {
                         Bukkit.getLogger().severe(ex.getMessage());
                         return null;
                     }
                 })
            .filter(Objects::nonNull)
            .map(c ->
                 {
                     try
                     {
                         return c.newInstance();
                     }
                     catch (ReflectiveOperationException ex)
                     {
                         Bukkit.getLogger().severe(ex.getMessage());
                         return null;
                     }
                 })
            .filter(Objects::nonNull)
            .map(Commander.class::cast)
            .forEach(this::registerCommand);
    }
}
