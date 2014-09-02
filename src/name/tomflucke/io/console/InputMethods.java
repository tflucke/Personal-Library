package name.tomflucke.io.console;

import java.io.IOException;
import java.io.InputStream;

public class InputMethods
	{
		private static InputStream input = System.in;

		public static String readToCharacter(final char c) throws IOException
			{
				char d;
				String result = new String();
				do
					{
						d = (char) input.read();
						result += d;
					}
				while (d != c && d != -1);
				return result;
			}
		
		public static String readToCharacter(final char c, boolean inclusive) throws IOException
			{
				if (inclusive)
					{return readToCharacter(c);}
				char d;
				String result = new String();
				while ((d = (char) input.read()) != c && d != -1)
					{
						result += d;
					}
				return result;
			}

		/*
		 * public static String readToExpression(String regex) throws
		 * IOException { char d; String result = new String(); do { d = (char)
		 * input.read(); result += d; } while (d != c && d != -1); return
		 * result; }
		 */
		public static String readln() throws IOException
			{
				char d;
				String result = new String();
				do
					{
						d = (char) input.read();
						result += d;
					}
				while (d != '\n' && d != '\r' && d != -1);
				return result;
			}
		public static String readln(boolean inclusive) throws IOException
			{
				if (inclusive)
					{return readln();}
				char d;
				String result = new String();
				while ((d = (char) input.read()) != '\n' && d != '\r' && d != -1)
					{
						result += d;
					}
				return result;
			}

		public static String readLength(final int length) throws IOException
			{
				String result = new String();
				for (int i = 0; i < length; i++)
					{
						result += (char) input.read();
					}
				return result;
			}

		public static String readLength(final long length) throws IOException
			{
				String result = new String();
				for (long i = 0; i < length; i++)
					{
						result += (char) input.read();
					}
				return result;
			}

		public static String readLength(final short length) throws IOException
			{
				String result = new String();
				for (short i = 0; i < length; i++)
					{
						result += (char) input.read();
					}
				return result;
			}

		public static String readLength(final byte length) throws IOException
			{
				String result = new String();
				for (byte i = 0; i < length; i++)
					{
						result += (char) input.read();
					}
				return result;
			}
	}