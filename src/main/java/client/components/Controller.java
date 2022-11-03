package client.components;

import client.gameObjects.Projectile;

import java.awt.geom.Point2D;

public class Controller extends GameComponent  {
    @Override
    public String key() {
        return Controller.Key();
    }
    public static String Key() {
        return "Controller";
    }
    @Override
    public Controller cloneShallow() {
        return new Controller();
    }
    @Override
    public Controller cloneDeep() {
        return new Controller();
    }
}
