package fr.math.minecraft.shared;

public enum ChatColor {

    YELLOW(0xFFFF55),
    WHITE(0xFFFFFF);

    private final int hexCode;

    ChatColor(int hexCode) {
        this.hexCode = hexCode;
    }

    public int getHexCode() {
        return hexCode;
    }
}
