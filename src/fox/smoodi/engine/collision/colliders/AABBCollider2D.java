package fox.smoodi.engine.collision.colliders;

import org.joml.Vector2f;

import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.world.objects.DynamicWorldObject;

public class AABBCollider2D {

	public Location position;
	public DynamicWorldObject parent;
	private Vector2f halfExtent;
	
	/**
	 * Creates a new Aligned Axis Bounding Box collider.
	 * @param pos The position of the collider in the world.
	 * @param halfExtent A vector relatively pointing to the right upper corner of the bounding box.
	 */
	public AABBCollider2D(Location pos, Vector2f halfExtent) {
		this.position = pos;
		this.halfExtent = halfExtent;
	}

	/**
	 * Determines if the collider is intersecting with another collider.
	 * @param box2 The other collider to check for.
	 * @return True if the colliders are intersecting - otherwise false.
	 */
	public boolean isIntersecting(AABBCollider2D box2) {
		Vector2f distance = box2.position.toVector2f().sub(position.toVector2f(), new Vector2f());
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		
		distance.sub(halfExtent.add(box2.halfExtent, new Vector2f()));
		
		
		return (distance.x < 0 && distance.y < 0);
	}
	
}