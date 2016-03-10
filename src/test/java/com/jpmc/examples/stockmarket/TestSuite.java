package com.jpmc.examples.stockmarket;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite to run all the test cases
 * 
 * @author santoshini.jami
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ StockTest.class, GBCETest.class })
public class TestSuite {
}
