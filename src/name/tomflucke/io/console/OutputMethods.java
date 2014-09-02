package name.tomflucke.io.console;

import java.io.PrintStream;

public class OutputMethods
	{
		private static PrintStream out = System.out;

		public static void write(final String str)
			{
				out.print(str);
			}
	}