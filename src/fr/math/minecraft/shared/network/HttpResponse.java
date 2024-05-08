package fr.math.minecraft.shared.network;

public class HttpResponse {

    private final StringBuilder response;
    private final int code;

    public HttpResponse(StringBuilder response, int code) {
        this.response = response;
        this.code = code;
    }

    public StringBuilder getResponse() {
        return response;
    }

    public int getCode() {
        return code;
    }
}
