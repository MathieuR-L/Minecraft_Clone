package fr.math.minecraft.client.network;

public class AuthUser {

    private final String name;
    private final String email;
    private final String skinUrl;
    private final String token;

    public AuthUser(String name, String email, String skinUrl, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.skinUrl = skinUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public String getToken() {
        return token;
    }
}
