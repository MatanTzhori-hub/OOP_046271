package homework2;

//import static org.junit.Assert.*;
//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * SimulatorTest contains JUnit block-box unit tests for Simulator.
 */
public class SimulatorTest {

	@Test
	public void testAddingPipes() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addPipe("sim1", "p1");
		driver.addPipe("sim1", "p2");
		driver.addPipe("sim1", "p3");

		assertEquals("", driver.listContents("sim1", "p1"), "not empty");
		assertEquals("", driver.listContents("sim1", "p2"), "not empty");
		assertEquals("", driver.listContents("sim1", "p3"), "not empty");
	}

	@Test
	public void testSinglePlusFilter() {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		// add some pipes and a filter
		driver.addPipe("sim1", "p1");
		driver.addPipe("sim1", "p2");
		driver.addPipe("sim1", "p3");
		driver.addPlusFilter("sim1", "pf1");

		// connect pipes to filter
		driver.addEdge("sim1", "p1", "pf1", "a");
		driver.addEdge("sim1", "p2", "pf1", "b");
		driver.addEdge("sim1", "pf1", "p3", "c");

		// perform 1 step, should output 0
		driver.simulate("sim1");

		assertEquals("0", driver.listContents("sim1", "p3"), "not 0");

		// insert values to pipes
		driver.injectInput("sim1", "p1", -3);
		driver.injectInput("sim1", "p1", 7);
		driver.injectInput("sim1", "p1", 0);
		driver.injectInput("sim1", "p2", 5);
		driver.injectInput("sim1", "p2", -2);
		driver.injectInput("sim1", "p2", 70);

		assertEquals("0 7 -3", driver.listContents("sim1", "p1"), "not 0 7 -3");
		assertEquals("70 -2 5", driver.listContents("sim1", "p2"), "not 70 -2 5");

		driver.simulate("sim1");
		driver.simulate("sim1");
		driver.simulate("sim1");

		assertEquals("70 5 2 0", driver.listContents("sim1", "p3"), "not 70 5 2 0");
	}

	@Test
	public void testTriplePlusFilter() {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		// add some pipes and a filter
		driver.addPipe("sim1", "p1");
		driver.addPipe("sim1", "p2");
		driver.addPipe("sim1", "p3");
		driver.addPipe("sim1", "p4");
		driver.addPipe("sim1", "p5");
		driver.addPipe("sim1", "p6");
		driver.addPipe("sim1", "p7");
		driver.addPlusFilter("sim1", "pf1");
		driver.addPlusFilter("sim1", "pf2");
		driver.addPlusFilter("sim1", "pf3");

		// connect pipes to filter
		driver.addEdge("sim1", "p1", "pf1", "a");
		driver.addEdge("sim1", "p2", "pf1", "b");
		driver.addEdge("sim1", "pf1", "p3", "c");
		driver.addEdge("sim1", "p4", "pf2", "d");
		driver.addEdge("sim1", "p5", "pf2", "e");
		driver.addEdge("sim1", "pf2", "p6", "f");
		driver.addEdge("sim1", "p3", "pf3", "g");
		driver.addEdge("sim1", "p6", "pf3", "h");
		driver.addEdge("sim1", "pf3", "p7", "i");

		// perform 1 step, should output 0
		driver.simulate("sim1");

		assertEquals("0", driver.listContents("sim1", "p3"), "not 0");
		assertEquals("0", driver.listContents("sim1", "p6"), "not 0");
		assertEquals("0", driver.listContents("sim1", "p7"), "not 0");

		// insert values to pipes
		driver.injectInput("sim1", "p1", 5);
		driver.injectInput("sim1", "p1", -7);
		driver.injectInput("sim1", "p2", 11);
		driver.injectInput("sim1", "p2", 17);
		driver.injectInput("sim1", "p4", -9);
		driver.injectInput("sim1", "p4", -11);
		driver.injectInput("sim1", "p5", 3);
		driver.injectInput("sim1", "p5", 17);

		assertEquals("-7 5", driver.listContents("sim1", "p1"), "not -7 5");
		assertEquals("17 11", driver.listContents("sim1", "p2"), "not 17 11");
		assertEquals("-11 -9", driver.listContents("sim1", "p4"), "not -11 -9");
		assertEquals("17 3", driver.listContents("sim1", "p5"), "not 17 3");

		driver.simulate("sim1");
		driver.simulate("sim1");
		driver.simulate("sim1");

		assertEquals("0", driver.listContents("sim1", "p3"), "not 0");
		assertEquals("0", driver.listContents("sim1", "p6"), "not 0");
		assertEquals("16 10 0 0", driver.listContents("sim1", "p7"), "not 16 10 0 0");
	}

	@Test
	public void testMultipleOutputsPlusFilter() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addPlusFilter("sim1", "pf1");
		driver.addPipe("sim1", "p1");
		driver.addPipe("sim1", "p2");
		driver.addPipe("sim1", "p3");

		driver.addEdge("sim1", "pf1", "p1", "a");
		
		BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("sim1", "pf1", "p2", "b");}, "no exception thrown");
        assertEquals("Plus-Filter pf1 already have an output pipe", e1.getMessage());
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("sim1", "pf1", "p3", "c");}, "no exception thrown");
        assertEquals("Plus-Filter pf1 already have an output pipe", e2.getMessage());
	}
}
