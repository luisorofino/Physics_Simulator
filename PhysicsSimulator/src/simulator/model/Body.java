package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Body {
	protected String id;
	protected String gid;
	protected Vector2D p;
	protected Vector2D v;
	protected Vector2D f;
	protected double m;
	
	protected Body(String id, String gid, Vector2D p, Vector2D v, double m){
		if(id==null || id.trim().length()<=0 ||  gid==null || gid.trim().length()<=0 ||
		   p==null || v==null || m<=0)
			throw new IllegalArgumentException("Invalid id/gid/p vector/v vector/value of m");
		this.id = id;
		this.gid = gid;
		this.p = p;
		this.v = v;
		this.m = m;
		f = new Vector2D();
	}
	
	public String getId() {
		return id;
	}
	public String getgId() {
		return gid;
	}
	public Vector2D getVelocity() {
		return v;
	}
	public Vector2D getForce() {
		return f;
	}
	public Vector2D getPosition() {
		return p;
	}
	public double getMass() {
		return m;
	}
	void addForce(Vector2D f) {
		this.f = this.f.plus(f);
	}
	void resetForce() {
		f = new Vector2D();
	}
	abstract void advance(double dt);
	
	public JSONObject getState() {
		JSONObject js = new JSONObject();
		js.put("id", id);
		js.put("m", m);
		js.put("p", p.asJSONArray());
		js.put("v", v.asJSONArray());
		js.put("f", f.asJSONArray());
		return js;
	}
	public String toString() {
		return getState().toString();
	}
}
