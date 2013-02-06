package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
 
 
@RunWith(Suite.class)
@SuiteClasses({
	BaseAggregateTest.class,
	CombinationsGeneratorTester.class,
	MinLevelStrategyTest.class,
	SortedListKeeperTest.class
})
public class AllTestSuite {
 
 
}
