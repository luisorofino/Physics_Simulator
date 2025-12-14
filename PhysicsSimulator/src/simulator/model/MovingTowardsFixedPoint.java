package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	private Vector2D c;
	private double g;
	
	public MovingTowardsFixedPoint(Vector2D c, double g){
		if(c==null || g <= 0)
			throw new IllegalArgumentException("Invalid c vector/value of g");
		this.c = c;
		this.g = g;
	}
	
	@Override
	public void apply(List<Body> bs) {
		for(Body b : bs)
			b.addForce(c.minus(b.getPosition()).direction().scale(g*b.getMass()));	
	}

}
