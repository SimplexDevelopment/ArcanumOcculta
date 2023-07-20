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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.bukkit.command.CommandSender;

public interface Commander
{
    /**
     * Runs the command.
     *
     * @param sender The sender of the command.
     * @param args   The command arguments.
     * @return A message which can be sent to both logs and the command sender.
     * @see CommandResponse
     */
    CommandResponse run(final CommandSender sender, final String[] args);

    default Info getInfoData()
    {
        return this.getClass().getDeclaredAnnotation(Info.class);
    }

    default Completion[] getCompletionData()
    {
        return this.getClass().getDeclaredAnnotationsByType(Completion.class);
    }

    default Permissions getPermissionData()
    {
        return this.getClass().getDeclaredAnnotation(Permissions.class);
    }

    default String getName()
    {
        return this.getInfoData().name();
    }

    default String getDescription()
    {
        return this.getInfoData().description();
    }

    default String getUsage()
    {
        return this.getInfoData().usage();
    }

    default List<String> getAliasList()
    {
        return List.of(getAliasArray());
    }

    default String[] getAliasArray()
    {
        return this.getInfoData().aliases();
    }

    default String getPermission()
    {
        return this.getPermissionData().permission();
    }

    default String getPermissionMessage()
    {
        return this.getPermissionData().permissionMessage();
    }

    default boolean allowConsole()
    {
        return this.getPermissionData().allowConsole();
    }

    default List<String> getTabCompletions(final String[] args)
    {
        final List<Completion> completions = List.of(this.getCompletionData());
        final List<String> returnable = new ArrayList<>();
        completions.stream()
                   .filter(completion -> completion.index() == args.length - 1)
                   .map(Completion::args)
                   .flatMap(Stream::of)
                   .filter(arg -> arg.startsWith(args[args.length - 1]))
                   .forEach(returnable::add);
        return returnable;
    }
}
