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

package app.simplexdev.arcanumocculta.api.event;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public final class ExperienceUpdateEvent extends PlayerEvent
{
    private final HandlerList handlerList = new HandlerList();
    private final double newExperience;
    private final Caster caster;

    private final boolean didLevel;

    public ExperienceUpdateEvent(final Caster who, final double newExperience)
    {
        super(who.bukkit());

        this.newExperience = newExperience;
        this.caster = who;
        this.didLevel = checkLevelUp();
    }

    @Override
    @NotNull
    public HandlerList getHandlers()
    {
        return handlerList;
    }

    public double getNewExperience() {
        return newExperience;
    }

    public Caster getCaster() {
        return caster;
    }

    private boolean checkLevelUp() {
        final double expReq = caster.getCurrentLevel().getNextLevelExp();

        if (newExperience >= expReq) {
            caster.setExperienceToZero();
            caster.setCurrentLevel(CasterLevel.fromOrdinal(caster.getCurrentLevel().getLevel() + 1));
            caster.bukkit().sendMessage("You have leveled up to " + caster.getCurrentLevel().getName() + "!");
            return true;
        }
        return false;
    }

    public boolean didCasterLevelUp() {
        return didLevel;
    }
}
