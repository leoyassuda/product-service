package br.com.lny;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TraceUnitExtension implements AfterEachCallback, BeforeEachCallback {

	private PrintStream sysOut;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Override
	public void afterEach(ExtensionContext extensionContext) throws Exception {
		System.setOut(sysOut);
	}

	@Override
	public void beforeEach(ExtensionContext extensionContext) throws Exception {
		sysOut = System.out;
		System.setOut(new PrintStream(outContent));
	}
}