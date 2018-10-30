package com.tdefense.world;

public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    
    public WorldRenderrer(WorldController worldController) {
        this.worldController = worldController;
    }
    
    private void initialize() {}
    
    public void render() {}
    
    public void resize(int width, int height) {}
    
    @Override
    public void dispose() {}
    
   0
}
