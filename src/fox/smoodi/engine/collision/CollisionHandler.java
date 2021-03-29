package fox.smoodi.engine.collision;

import fox.smoodi.engine.collision.colliders.AABBCollider2D;

public class CollisionHandler {

	/**
	 * Checks if two given colliders are colliding / intersecting with each other.
	 * @param col1 The first collider to check for.
	 * @param col2 The second collider to check for.
	 * @return True if the two colliders are colliding otherwise false.
	 */
	public static boolean collide(AABBCollider2D col1, AABBCollider2D col2) {
				
		return col1.isIntersecting(col2);
	}
	
}
