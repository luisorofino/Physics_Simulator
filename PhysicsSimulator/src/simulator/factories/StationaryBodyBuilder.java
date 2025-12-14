package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.StationaryBody;

public class StationaryBodyBuilder extends Builder<Body>{
	
	public StationaryBodyBuilder() {
		super("st_body", "Creates Sationary Bodies");
	}

	@Override
	protected Body createInstance(JSONObject data) {
		if(data!=null) {
			if(!data.has("id")) throw new IllegalArgumentException("'id' argument missing");
			if(!data.has("gid")) throw new IllegalArgumentException("'desc' argument missing");
			if(!data.has("p")) throw new IllegalArgumentException("'position' argument missing");
			if(!data.has("m")) throw new IllegalArgumentException("'mass' argument missing");
			Vector2D p;
			try {
				p = readVector(data.getJSONArray("p"));
			}catch(Exception e) {
				throw new IllegalArgumentException("p must be 2D");
			}
			return new StationaryBody(data.getString("id"), data.getString("gid"), p, data.getDouble("m"));
		}else throw new IllegalArgumentException("Invalid value for createInstance: no 'data' tag");
	}
	
	private Vector2D readVector(JSONArray j) {
		if(j.length()!=2) throw new IllegalArgumentException();
		double x = j.getDouble(0);
		double y = j.getDouble(1);
		return new Vector2D(x,y);
	}
}
