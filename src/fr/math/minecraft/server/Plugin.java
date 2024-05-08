package fr.math.minecraft.server;

public interface Plugin {

    void onEnable();
    void onPlayerJoin(int onlinePlayers);
    void onDisable();

}
