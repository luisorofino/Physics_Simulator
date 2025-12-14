package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	
	private static final Vector2D DEFAULT_C = new Vector2D();
	private static final double DEFAULT_g = 9.81;
	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "Creates Moving Towards Fixed Point bodies");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		if(data!=null) {
			if(!data.has("c") && !data.has("g")) return new MovingTowardsFixedPoint(DEFAULT_C, DEFAULT_g);
			if(!data.has("c")) return new MovingTowardsFixedPoint(DEFAULT_C, data.getDouble("g"));
			Vector2D c;
			try {
				c = readVector(data.getJSONArray("c"));
			}catch(Exception e) {
				throw new IllegalArgumentException("c must be 2D");
			}
			if(!data.has("g")) return new MovingTowardsFixedPoint(c, DEFAULT_g);
			return new MovingTowardsFixedPoint(c, data.getDouble("g"));
		}else return new MovingTowardsFixedPoint(DEFAULT_C, DEFAULT_g);
	}

	private Vector2D readVector(JSONArray j) {
		if(j.length()!=2) throw new IllegalArgumentException();
		double x = j.getDouble(0);
		double y = j.getDouble(1);
		return new Vector2D(x,y);
	}
}
