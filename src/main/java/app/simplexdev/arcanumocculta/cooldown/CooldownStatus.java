package app.simplexdev.arcanumocculta.cooldown;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class CooldownStatus
{
    private final Duration duration;
    private final Instant currentTime;
    private final Instant endTime;
    private final UUID spellUUID;

    public CooldownStatus(final UUID spellUUID, final Duration duration)
    {
        this.duration = duration;
        this.currentTime = Instant.now();
        this.endTime = this.currentTime.plus(duration);
        this.spellUUID = spellUUID;
    }

    public UUID getSpellUUID()
    {
        return spellUUID;
    }

    public boolean hasExpired()
    {
        return Instant.now().isAfter(this.endTime);
    }
}
