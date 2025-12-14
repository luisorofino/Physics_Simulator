package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodiesGroup {
	private String id;
	private ForceLaws fl;
	private List<Body> bl;
	
	public BodiesGroup(String id, ForceLaws fl){
		if(id==null || fl==null || id.trim().length()<=0)
			throw new IllegalArgumentException("Invalid BodiesGroup id/ForceLaws");
		this.id = id;
		this.fl = fl;
		bl = new ArrayList<Body>();
	}
	
	public String getId() {
		return id;
	}
	
	void setForceLaws(ForceLaws fl){
		if(fl==null)
			throw new IllegalArgumentException("Invalid ForceLaws");
		this.fl = fl;
	}
	
	void addBody(Body b){
		if(b==null || !validId(b))
			throw new IllegalArgumentException("Invalid Body");
		bl.add(b);
	}
	
	private boolean validId(Body b) {
		String id = b.getId();
		for(Body body: bl) {
			if(body.getId().equals(id))
				return false;
		}
		return true;
	}
	
	void advance(double dt){ 
		//No hace falta q throwee si ya lo hace physics simulator en el constructor???????????????
		if(dt <= 0) throw new IllegalArgumentException("Invalid time interval");
		for(Body b: bl)
			b.resetForce();
		fl.apply(bl);
		for(Body b: bl)
			b.advance(dt);
	}
	
	public JSONObject getState() { //¿¿¿¿¿?????????
		JSONObject js = new JSONObject();
		js.put("id", id);
		JSONArray ja = new JSONArray();
		for(Body b: bl) {
			ja.put(b.getState());
		}
		js.put("bodies", ja );
		return js;
	}
	
	public String toString() {
		return getState().toString();
	}
}
