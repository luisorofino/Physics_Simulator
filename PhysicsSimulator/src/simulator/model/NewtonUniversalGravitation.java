package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation(double G){
		if(G <= 0)
			throw new IllegalArgumentException("Invalid value of G");
		this.G = G;
	}
	
	@Override
	public void apply(List<Body> bs) {
		for(Body b1 : bs) {
			Vector2D f = new Vector2D();
			for(Body b2 : bs) {
				if(!forceIsZero(b1,b2))
					f = f.plus(unitaryDirection(b1.getPosition(),b2.getPosition()).scale(forceValue(b1,b2)));
			}
			b1.addForce(f);
		}
	}
	
	private Vector2D unitaryDirection(Vector2D v1, Vector2D v2) {
		return v2.minus(v1).direction();
	}
	
	private double forceValue(Body b1, Body b2) {
		double distance = b1.getPosition().distanceTo(b2.getPosition());
		return G*b1.getMass()*b2.getMass()/(distance*distance);
	}
	
	private boolean forceIsZero(Body b1, Body b2) {
		if(b1.getMass()==0 || b2.getMass()==0 || b1.getPosition().equals(b2.getPosition()))
			return true;
		return false;
	}

}
