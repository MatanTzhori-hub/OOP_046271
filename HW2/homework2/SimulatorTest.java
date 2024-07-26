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
	public void testPlusFilterMultipleOutputs() throws BipartiteGraphException {
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

	@Test
	public void testPlusFilterNoOutputs() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addPlusFilter("sim1", "pf1");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("No output pipe for Plus-Filter pf1", e2.getMessage());
	}

	@Test
	public void testPlusFilterNoInputs() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addPlusFilter("sim1", "pf1");
		driver.addPipe("sim1", "p1");

		driver.addEdge("sim1", "pf1", "p1", "a");
		
		driver.simulate("sim1");

		assertEquals("0", driver.listContents("sim1", "p1"), "not 0");
	}

	@Test
	public void testGCDFilterNoOutput_a() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("Node gcd1 doesn't have an out edge a", e2.getMessage());
	}

	@Test
	public void testGCDFilterNoOutput_b() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("Node gcd1 doesn't have an out edge b", e2.getMessage());
	}

	@Test
	public void testGCDFilterNoOutput_gcd() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("Node gcd1 doesn't have an out edge gcd", e2.getMessage());
	}

	@Test
	public void testGCDFilterNoInput_a() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("Node gcd1 doesn't have an in edge a", e2.getMessage());
	}

	@Test
	public void testGCDFilterNoInput_b() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		
		BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.simulate("sim1");}, "no exception thrown");
        assertEquals("Node gcd1 doesn't have an in edge b", e2.getMessage());
	}

	@Test
	public void testGCDFilterAllInOutputs() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		driver.addEdge("sim1", "p2", "gcd1", "b");
		
		driver.simulate("sim1");
		assertEquals("0", driver.listContents("sim1", "p3"), "not 0");
	}

	@Test
	public void testGCDFIlterSimple() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		driver.addEdge("sim1", "p2", "gcd1", "b");
		
		driver.injectInput("sim1", "p1", 50);
		driver.injectInput("sim1", "p2", 5);

		driver.simulate("sim1");
		assertEquals("5", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("5", driver.listContents("sim1", "p3"), "wrong value");
	}

	@Test
	public void testGCDFIlterPrime() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		driver.addEdge("sim1", "p2", "gcd1", "b");
		
		driver.injectInput("sim1", "p1", 11);
		driver.injectInput("sim1", "p2", 17);

		driver.simulate("sim1");
		assertEquals("17", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("11", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("11", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("6", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("6", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("5", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("5", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("1", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("1", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("1", driver.listContents("sim1", "p3"), "wrong value");
	}

	@Test
	public void testGCDFIlterNegative() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		driver.addEdge("sim1", "p2", "gcd1", "b");
		
		driver.injectInput("sim1", "p1", -72);
		driver.injectInput("sim1", "p2", -60);

		driver.simulate("sim1");
		assertEquals("-60", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("-12", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("-12", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("-12", driver.listContents("sim1", "p3"), "wrong value");
	}

	@Test
	public void testGCDFIlterSame() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPipe("sim1", "p1");
		driver.addEdge("sim1", "gcd1", "p1", "a");
		driver.addPipe("sim1", "p2");
		driver.addEdge("sim1", "gcd1", "p2", "b");
		driver.addPipe("sim1", "p3");
		driver.addEdge("sim1", "gcd1", "p3", "gcd");
		driver.addEdge("sim1", "p1", "gcd1", "a");
		driver.addEdge("sim1", "p2", "gcd1", "b");
		
		driver.injectInput("sim1", "p1", 500);
		driver.injectInput("sim1", "p2", 500);

		driver.simulate("sim1");
		assertEquals("500", driver.listContents("sim1", "p1"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p2"), "wrong value");
		driver.simulate("sim1");
		assertEquals("500", driver.listContents("sim1", "p3"), "wrong value");
	}

	@Test
	public void testGCDFIlterPlusFilterScenario() throws BipartiteGraphException {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		driver.addGCDFilter("sim1", "gcd1");
		driver.addPlusFilter("sim1", "pf1");
		driver.addPlusFilter("sim1", "pf2");
		driver.addPipe("sim1", "p1");
		driver.addPipe("sim1", "p2");
		driver.addPipe("sim1", "p3");
		driver.addPipe("sim1", "p4");
		driver.addPipe("sim1", "p5");
		driver.addPipe("sim1", "p6");
		driver.addPipe("sim1", "p7");
		driver.addEdge("sim1", "p1", "pf1", "a");
		driver.addEdge("sim1", "p2", "pf1", "b");
		driver.addEdge("sim1", "pf1", "p3", "c");
		driver.addEdge("sim1", "p4", "pf2", "d");
		driver.addEdge("sim1", "p5", "pf2", "e");
		driver.addEdge("sim1", "pf2", "p6", "f");
		driver.addEdge("sim1", "p3", "gcd1", "a");
		driver.addEdge("sim1", "gcd1", "p3", "a");
		driver.addEdge("sim1", "p6", "gcd1", "b");
		driver.addEdge("sim1", "gcd1", "p6", "b");
		driver.addEdge("sim1", "gcd1", "p7", "gcd");
		
		driver.injectInput("sim1", "p1", 11);
		driver.injectInput("sim1", "p2", 19);
		driver.injectInput("sim1", "p4", -100);
		driver.injectInput("sim1", "p5", 750);
		
		driver.simulate("sim1");
		assertEquals("30", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("650", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p7"), "wrong value");
		driver.simulate("sim1");
		assertEquals("0 650", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("0 30", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p7"), "wrong value");
		driver.simulate("sim1");
		assertEquals("0 0 30", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("0 0 20", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p7"), "wrong value");
		driver.simulate("sim1");
		assertEquals("0 0 0 20", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("0 0 0 10", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p7"), "wrong value");
		driver.simulate("sim1");
		assertEquals("0 0 0 0 10", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("0 0 0 0 0", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("0", driver.listContents("sim1", "p7"), "wrong value");
		driver.simulate("sim1");
		assertEquals("0 0 0 0 0", driver.listContents("sim1", "p3"), "wrong value");
		assertEquals("0 0 0 0 0", driver.listContents("sim1", "p6"), "wrong value");
		assertEquals("10 0", driver.listContents("sim1", "p7"), "wrong value");
	}
}
