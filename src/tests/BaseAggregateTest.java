package tests;

import org.junit.Before;
import org.junit.Test;

import cuboid.AggregateFunction;
import cuboid.BaseAggregate;

public class BaseAggregateTest {
	
	AggregateFunction[] functionGraphCube;

	@Before
	public void setUp() throws Exception {
		
		functionGraphCube = new AggregateFunction[8];
		for(int i = 0; i < functionGraphCube.length; i++){
			functionGraphCube[i] = new BaseAggregate();
		}
		functionGraphCube[0].parseFunction("0,1,2");
		functionGraphCube[1].parseFunction("0,1");
		functionGraphCube[2].parseFunction("0,2");
		functionGraphCube[3].parseFunction("1,2");
		functionGraphCube[4].parseFunction("0");
		functionGraphCube[5].parseFunction("1");
		functionGraphCube[6].parseFunction("2");
		functionGraphCube[7].parseFunction("");
	}

	@Test
	public void testIsDescendant() {
		assert(functionGraphCube[1].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[2].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[3].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[4].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[5].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[6].isDescendant(functionGraphCube[0]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[0]));
		
		assert(functionGraphCube[7].isDescendant(functionGraphCube[1]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[2]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[3]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[4]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[5]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[6]));
		assert(functionGraphCube[7].isDescendant(functionGraphCube[7]));
		
		assert(functionGraphCube[4].isDescendant(functionGraphCube[1]));
		assert(functionGraphCube[4].isDescendant(functionGraphCube[2]));
		assert(!functionGraphCube[4].isDescendant(functionGraphCube[3]));
		assert(functionGraphCube[5].isDescendant(functionGraphCube[1]));
		assert(!functionGraphCube[5].isDescendant(functionGraphCube[2]));
		assert(functionGraphCube[5].isDescendant(functionGraphCube[3]));
		assert(!functionGraphCube[5].isDescendant(functionGraphCube[4]));
	}

}
