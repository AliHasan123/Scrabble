package tests;

//james
//noah and ali 4/21 d and n

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import code.model.Board_024_062;
import code.model.Player_024_062;

public class CheckIfWordIsValidTests_062 {
	@Test
	public void Test0() throws IOException{
		commonCode("bad",true);
	}
	@Test
	public void Test1() throws IOException{
		commonCode("WALL",true);
	}
	@Test
	public void Test2() throws IOException{
		commonCode("abcd",false);
	}
	@Test
	public void Test3() throws IOException{
		commonCode("g0aT$",false);
	}
	@Test
	public void Test4() throws IOException{
		commonCode("",true); //c
	}
	@Test
	public void Test5() throws IOException{
		commonCode("a",false);
	}
	@Test
	public void Test6() throws IOException{
		commonCode("CSE",false);
	}
	public void commonCode(String word,boolean expected) throws IOException{
		Board_024_062 board = new Board_024_062();
		boolean actual = board.checkIfWordIsValid(word);
		assertTrue("",expected == actual);
	}
}
