package name.tomflucke.io.stream;

import java.io.IOException;
import java.io.Reader;

public final class StreamInputMethods
	{
		public static String readToCharacter(final Reader input, final char c) throws IOException
			{
				char d;
				String result = new String();
				do
					{
						d = (char) input.read();
						if (d == -1)
							{
								break;
							}
						result += d;
					}
				while (d != c && d != -1);
				return result;
			}
	}
