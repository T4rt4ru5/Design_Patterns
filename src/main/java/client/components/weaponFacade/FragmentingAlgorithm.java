package client.components.weaponFacade;
import java.util.concurrent.TimeUnit;

import client.components.GameComponent;
import client.components.Transform;
import client.gameObjects.GameObject;
import client.gameObjects.Projectile;
import network.server.SEngine;

public class FragmentingAlgorithm extends ProjectileAlgorithm {
  private long end;
  private float speed;

  public void createFragment(float rotation) {
    String fragmentImage = "images/fragment.png";
    Transform transform = gameObject.getComponent(Transform.Key());

    Projectile obj = new Projectile(transform.position.x, transform.position.y, rotation, fragmentImage, 11)
            .setAlgorithm(new StraightFlyAlgorithm(7, TimeUnit.MILLISECONDS, 300));

    obj.owner = ((Projectile)gameObject).owner;

    SEngine.GetInstance()
        .Add(obj);
  }

  public FragmentingAlgorithm(float speed, TimeUnit unit, long duration) {
    end = System.currentTimeMillis() + unit.toMillis(duration);
    this.speed = speed;
  }

  @Override
  public void fly(Transform transform, float delta) {
    if (System.currentTimeMillis() > end) {
      SEngine.GetInstance().Destroy(gameObject);
      for (int i = 0; i < 8; i++) {
        float rotation = i * 45;
        createFragment(rotation);
      }

      return;
    }

    transform.moveForward(speed * delta);
  }

  @Override
  public String key() {
    return getClass().getName();
  }
}
