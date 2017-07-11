package com.jc.io.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 进程控制，将操作系统的其他进程的输出，转为java程序的输入
 * @author jevoncode
 *
 */
public class OSExecute {
	public static void command(String command) {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = results.readLine()) != null)
				System.out.println(s);
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// Report errors and return nonzero value
			// to calling process if there are problems:
			while ((s = errors.readLine()) != null) {
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			// Compensate for Windows 2000, which throws an
			// exception for the default command line:
			e.printStackTrace();
			if (!command.startsWith("CMD /C"))
				command("CMD /C " + command);
			else
				throw new RuntimeException(e);
		}
		if (err)
			throw new OSExecuteException("Errors executing " + command);
	}
}
