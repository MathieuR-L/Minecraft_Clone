package fr.math.minecraft.client;

public class GameWindow {

    private final Renderer renderer;

    public GameWindow() {
        this.renderer = new Renderer();
    }

    public void render() {
        Game.getInstance().render(renderer);
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
