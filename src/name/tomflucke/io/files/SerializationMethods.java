package name.tomflucke.io.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializationMethods
	{
		public static void writeObject(File file, Serializable obj)
				throws IOException, FileNotFoundException
			{
				OutputStream fileOut = new java.io.FileOutputStream(
						file.getPath());
				ObjectOutput out = new java.io.ObjectOutputStream(fileOut);
				try
					{
						out.writeObject(obj);
					}
				finally
					{
						out.close();
						fileOut.close();
					}
			}

		public static Serializable readObject(File file) throws IOException,
				ClassNotFoundException, FileNotFoundException
			{
				InputStream fileIn = new java.io.FileInputStream(file.getPath());
				ObjectInput in = new java.io.ObjectInputStream(fileIn);
				try
					{
						return (Serializable) in.readObject();
					}
				finally
					{
						in.close();
						fileIn.close();
					}
			}
	}
