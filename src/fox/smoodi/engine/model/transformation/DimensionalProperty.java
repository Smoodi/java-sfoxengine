package fox.smoodi.engine.model.transformation;

import java.lang.reflect.Array;

public class DimensionalProperty<X> {

	private int dimensions;
	private X[] values;
	
	public DimensionalProperty(Class<X> c, int dimension) {
		this.dimensions = dimension;
		
		values = (X[]) Array.newInstance(c, dimensions);
	}
	
	public X getProperty(int dim) {
		return values[dim];
	}
	
	public void setProperty(int dim, X value) {
		if(dim <= values.length-1)
			values[dim] = value;
	}
	
}
