package name.tomflucke.network;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketMethods
	{
		public static boolean isPortAvalible(final int port)
			{
				ServerSocket testSocket = null;
				try
					{
						testSocket = new ServerSocket(port);
						return true;
					}
				catch (final IOException e)
					{
						return false;
					}
				finally
					{
						if (testSocket != null)
							{
								try
									{
										testSocket.close();
										testSocket = null;
									}
								catch (final IOException e)
									{/* Shouldn't happen. */
									}
							}

					}
			}
	}
