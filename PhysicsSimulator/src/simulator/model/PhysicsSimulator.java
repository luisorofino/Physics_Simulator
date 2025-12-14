package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double dt;
	private double currentTime;
	private ForceLaws fl;
	private Map<String, BodiesGroup> m;
	private List<String> ids;
	
	public PhysicsSimulator(ForceLaws fl, double dt){
		if(dt <= 0 || fl==null)
			throw new IllegalArgumentException("Invalid time interval/ForceLaws");
		this.dt = dt;
		this.fl = fl;
		m = new HashMap<String,BodiesGroup>();
		ids = new ArrayList<String>();
		currentTime = 0.0;
	}
	
	public void advance(){//throws???
		for(String bgs : m.keySet())
			m.get(bgs).advance(dt);
		currentTime += dt;
	}
	
	public void addGroup(String id){
		for(String bgs : m.keySet()) {
			if(bgs.equals(id)) throw new IllegalArgumentException("Invalid BodiesGroup to add");
		}
		m.put(id, new BodiesGroup(id,fl));
		ids.add(id);
	}
	
	public void addBody(Body b){
		boolean found = false;
		for(String bgs : m.keySet()) {
			if(bgs.equals(b.getgId())) {
				found = true;
				m.get(bgs).addBody(b);
			}
		}
		if(!found) throw new IllegalArgumentException("Invalid BodiesGroup to add the Body");
	}
	
	public void setForceLaws(String id, ForceLaws fl){
		boolean found = false;
		for(String bgs : m.keySet()) {
			if(bgs.equals(id)) {
				found = true;
				m.get(bgs).setForceLaws(fl);;
			}
		}
		if(!found) throw new IllegalArgumentException("Invalid BodiesGroup to add the ForceLaws");
	}
	
	public JSONObject getState() {
		JSONObject js = new JSONObject();
		js.put("time", currentTime);
		JSONArray ja = new JSONArray();
		for(String s:ids) ja.put(m.get(s).getState());
		js.put("groups", ja);
		return js;
	}
	
	public String toString() {
		return getState().toString();
	}
}
