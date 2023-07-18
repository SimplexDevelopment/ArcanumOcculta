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

package app.simplexdev.arcanumocculta.api.spell.enums;

public enum Damages
{
    MINIMAL(0.5),
    LIGHT(1.0),
    NEUTRAL(1.5),
    MODERATE(2.0),
    INTERMEDIATE(2.5),
    HEAVY(3.0),
    MAJOR(3.5),
    SEVERE(4.0),
    EXTREME(4.5),
    IMMENSE(5.0),
    CRITICAL(6.5),
    FATAL(10.0),
    OVERKILL(20.0),
    DEVASTATION(50.0);

    private final double damage;

    Damages(double damage)
    {
        this.damage = damage;
    }

    public double getDamage()
    {
        return damage;
    }

    public double multiply(double multiplier) {
        return damage * multiplier;
    }
}
