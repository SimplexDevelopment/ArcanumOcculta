package app.simplexdev.arcanumocculta.api.effect;

public interface EffectProvider<T extends Effect> {
    T getEffect(Class<T> type);
}
