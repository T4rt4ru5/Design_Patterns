package client.gameObjects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.awt.event.*;
import java.awt.geom.*;

public class GameObject {

    public boolean enabled = true;

    protected Point2D.Float position;
    protected float rotation; // In degrees

    protected int width;
    protected int height;

    private double boxSize;

    BufferedImage texture;

    protected GameObject() {
    }

    public GameObject(String imagePath, Integer width, Integer height) {
        setTexture(imagePath);
        setDimensions(width, height);
    }

    public GameObject(String imagePath) {
        setTexture(imagePath);
        setDimensions(texture.getWidth(), texture.getHeight());
    }

    public GameObject(String imagePath, int size, boolean isWidth) {
        setTexture(imagePath);
        if (isWidth) {
            setDimensions(size, (int) ((float) size * texture.getHeight() / texture.getWidth()));
        } else {
            setDimensions((int) ((float) size * texture.getWidth() / texture.getHeight()), size);
        }
    }

    private void setTexture(String imagePath) {
        try {
            texture = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Resource was not found: " + imagePath);
        }
    }

    private void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;
        boxSize = (int) Math.round(Point2D.distance(0, 0, width, height));
    }

    public GameObject setPosition(float x, float y) {
        this.position = new Point2D.Float(x, y);
        return this;
    }

    public Point2D.Float getPosition() {
        if (position == null) {
            position = new Point2D.Float(0, 0);
        }
        return new Point2D.Float((float) position.getX(), (float) position.getY());
    }

    public float getAngle() {
        return rotation;
    }

    public BufferedImage getImage() {
        BufferedImage newImageFromBuffer = new BufferedImage((int)Math.ceil(boxSize), (int)Math.ceil(boxSize), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = newImageFromBuffer.createGraphics();
        
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(rotation), boxSize / 2, boxSize / 2);
        at.translate((boxSize - width) / 2, (boxSize - height) / 2);
        graphics2D.setTransform(at);
        graphics2D.drawImage(texture, 0, 0, width, height, null);
        graphics2D.dispose();
        
        return newImageFromBuffer;
    }

    public void update() {
    }
}