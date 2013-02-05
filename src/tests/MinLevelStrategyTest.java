package tests;

import static org.junit.Assert.*;

import materialization.MinLevelStrategy;

import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import cuboid.CuboidEntry;

public class MinLevelStrategyTest {

	private MinLevelStrategy strategy;
	
	@Before
	public void setUp() throws Exception {
		this.strategy = new MinLevelStrategy(1,25,3,new Path("testPath"));
	}

	@Test
	public void testFinished() {
		CuboidEntry lastComputed = null;
		int i = 0;
		while(!this.strategy.finished(lastComputed)){
			lastComputed = this.strategy.nextAggregate()[1];
			i++;
		}
		assertEquals(i,6);
		
		i = 0;
		lastComputed = null;
		this.strategy = new MinLevelStrategy(2,15,6,new Path("testPath"));
		while(!(this.strategy.finished(lastComputed))){
			lastComputed = this.strategy.nextAggregate()[1];
			i++;
		}
		assertEquals(i,15);
	}

	@Test
	public void testNextAggregate() {
		CuboidEntry lastComputed = null;
		CuboidEntry [] results;
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"0,1");
		
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"0,2");
		
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"1,2");
		
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"0");
		
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"1");
		
		this.strategy.finished(lastComputed);
		results = this.strategy.nextAggregate();
		assertEquals(results[0].getAggregateFunction().toString(),"");
		assertEquals(results[1].getAggregateFunction().toString(),"2");
	}

}
