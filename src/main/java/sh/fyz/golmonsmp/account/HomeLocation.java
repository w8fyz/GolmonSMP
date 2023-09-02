package sh.fyz.golmonsmp.account;

import java.util.UUID;

public class HomeLocation {
	
	private UUID worldID;
	private double x,y,z;
	private float yaw, pitch;
	
	public HomeLocation(UUID worldID, double x, double y, double z, float yaw, float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.worldID = worldID;
	}
	
	public UUID getWorldID() {
		return worldID;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}	
	public double z() {
		return z;
	}	
	public float yaw() {
		return yaw;
	}	
	public float pitch() {
		return pitch;
	}
}
