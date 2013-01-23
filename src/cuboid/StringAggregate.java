package cuboid;

public class StringAggregate extends BaseAggregate<String> {

	@Override
	public String aggregateDimension() {
		return "*";
	}

}
