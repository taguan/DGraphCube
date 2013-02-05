package tests;


import static org.junit.Assert.*;
import materialization.SortedListKeeper;

import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import cuboid.AggregateFunction;
import cuboid.BaseAggregate;
import cuboid.CuboidEntry;

public class SortedListKeeperTest {

	SortedListKeeper cube;
	AggregateFunction[] functionGraphCube;
	Path path;
	
	@Before
	public void setUp() throws Exception {
		path = new Path("testPath");
		this.cube = new SortedListKeeper(3,path,2);
		
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
	public void testGetNearestDescendant() {
		cube.addCuboid(new CuboidEntry(functionGraphCube[4], 15,path));
		cube.addCuboid(new CuboidEntry(functionGraphCube[5], 8,path));
		cube.addCuboid(new CuboidEntry (functionGraphCube[6], 40,path));
		assertEquals(cube.getNearestDescendant(functionGraphCube[0]).getAggregateFunction(),functionGraphCube[5]);
		assertEquals(cube.getNearestDescendant(functionGraphCube[1]).getAggregateFunction(),functionGraphCube[5]);
		assertEquals(cube.getNearestDescendant(functionGraphCube[2]).getAggregateFunction(),functionGraphCube[4]);
		assertEquals(cube.getNearestDescendant(functionGraphCube[3]).getAggregateFunction(),functionGraphCube[5]);

	}

	@Test
	public void testGetLevel() {
		assertEquals(cube.getLevel(functionGraphCube[0]),0);
		assertEquals(cube.getLevel(functionGraphCube[2]), 1);
		assertEquals(cube.getLevel(functionGraphCube[5]), 2);
		assertEquals(cube.getLevel(functionGraphCube[7]), 3);
	}

}
