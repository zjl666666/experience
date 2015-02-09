package com.basis.io.inputOrReader;

//: io/MemoryInput.java
import java.io.*;

public class MemoryInput {
	public static void main(String[] args) throws IOException {
		StringReader in = new StringReader("MemoryInput.java");
		int c;
		while ((c = in.read()) != -1)
			System.out.println((char) c);
	}
} /* (Execute to see output) */// :~
