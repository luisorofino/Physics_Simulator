package simulator.factories;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {
	
	private Map<String,Builder<T>> _builders;
	private List<JSONObject> _buildersInfo;
	
	public BuilderBasedFactory() {
		_builders = new HashMap<String, Builder<T>>();
		_buildersInfo = new LinkedList<JSONObject>();
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		for(Builder<T> b : builders)
			addBuilder(b);
	}
	public void addBuilder(Builder<T> b) {
		_builders.putIfAbsent(b.getTypeTag(), b);
		_buildersInfo.add(b.getInfo());
	}
	@Override
	public T createInstance(JSONObject info) {
		if (info == null) throw new IllegalArgumentException("Invalid value for createInstance: null");
		if(!info.has("type")) throw new IllegalArgumentException("Invalid value for createInstance: no 'type' tag");
		JSONObject data;
		if(!info.has("data")) data = null;
		else data = info.getJSONObject("data");
		if(!_builders.containsKey(info.getString("type"))) throw new IllegalArgumentException("Invalid value for createInstance: no Builder with that typeTag");
		T builder = _builders.get(info.getString("type")).createInstance(data);
		if(builder != null) return builder; //si es null devuelve excepcion??? creo que no puede devolver null??
		throw new IllegalArgumentException("Invalid value for createInstance: " + info.toString());
	}
	@Override
	public List<JSONObject> getInfo() {
		return Collections.unmodifiableList(_buildersInfo);
	}
}

