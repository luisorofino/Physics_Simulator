package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	
	private static final double DEFAULT_G = 6.67E-11;
	
	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Creates Newton Universal Gravitation force law");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		if(data == null || !data.has("G")) return new NewtonUniversalGravitation(DEFAULT_G);
		return new NewtonUniversalGravitation(data.getDouble("G"));
	}

}
