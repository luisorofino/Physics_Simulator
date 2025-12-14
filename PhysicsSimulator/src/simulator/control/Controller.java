package simulator.control;

import simulator.model.PhysicsSimulator;
import simulator.model.Body;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class Controller {
	
	private PhysicsSimulator ps;
	private Factory<Body> bf;
	private Factory<ForceLaws> flf;
	
	public Controller(PhysicsSimulator ps, Factory<Body> bf, Factory<ForceLaws> flf) {
		this.ps = ps;
		this.bf = bf;
		this.flf = flf;
	}
	
	public void loadData(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray jo = jsonInput.getJSONArray("groups");
		for(int i = 0;i<jo.length();i++) {
			ps.addGroup(jo.getString(i));
		}
		if(jsonInput.has("laws")) {
			jo = jsonInput.getJSONArray("laws");
			for(int i = 0;i<jo.length();i++)
				ps.setForceLaws(jo.getJSONObject(i).getString("id"), flf.createInstance(jo.getJSONObject(i).getJSONObject("laws")));
		}
		else {
			for(int i = 0;i<jo.length();i++)
				ps.setForceLaws(jo.getString(i), flf.createInstance(null));
		}
		jo = jsonInput.getJSONArray("bodies");
		for(int i = 0;i<jo.length();i++) {
			ps.addBody(bf.createInstance(jo.getJSONObject(i)));
		}
	}
	
	public void run(int n, OutputStream out) {
		PrintStream p = new PrintStream(out);
		p.println("{");
		p.println("\"states\": [");
		p.println(ps.toString());
		for(int i=0;i<n;i++) {
			p.print(",");
			ps.advance();
			p.println(ps.toString());
		}
		p.println("]");
		p.println("}");

	}
	
}
