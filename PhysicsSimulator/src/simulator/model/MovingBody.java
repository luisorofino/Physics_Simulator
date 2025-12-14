package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{
	
	public MovingBody(String id, String gid, Vector2D p, Vector2D v, double m) {
		super(id,gid,p,v,m);
	}
	
	@Override
	void advance(double dt) {
		Vector2D a = new Vector2D();
		if(m!=0)
			a = f.scale(1.0 / m);
		p = p.plus(v.scale(dt).plus(a.scale(0.5*dt*dt)));
		v = v.plus(a.scale(dt));
	}
	
}

