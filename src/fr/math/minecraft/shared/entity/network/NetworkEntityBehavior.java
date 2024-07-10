package fr.math.minecraft.shared.entity.network;

public interface NetworkEntityBehavior {

    void packetHandler();
    void packetSender();
    void unlockPort();
    void repairService();
    void onClick();
    void configServeur();

}
