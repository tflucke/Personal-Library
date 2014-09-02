package name.tomflucke.arrays;

import java.lang.reflect.Array;
import java.util.Comparator;

import org.apache.commons.lang3.ArrayUtils;

public final class Sort
	{
/*		public boolean[] mergeSort(boolean[] array, boolean lowToHigh)
			{
				boolean[] result = new boolean[array.length];
				int trueCount = 0;
				for (boolean b : array)
					{
						if (b)
							{
								trueCount++;
							}
					}
				int start;
				int stop;
				int increment;
				if (lowToHigh)
					{
						start = result.length - 1;
						stop = 0;
						increment = -1;
					}
				else
					{
						start = 0;
						stop = result.length;
						increment = 1;
					}
									lessThan or greaterThan
				for (int i = start; i < trueCount; i += increment)
					{
						result[i] = true;
					}
				for (int i = trueCount; i < stop; i+= increment)
					{
						
					}
				return result;
			}*/
		private static short[] sortMerge(short[] A, short[] B, boolean lowToHeigh)
			{
				short[] result = new short[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static short[] mergeSort(short[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						short[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						short[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static char[] sortMerge(char[] A, char[] B, boolean lowToHeigh)
			{
				char[] result = new char[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static char[] mergeSort(char[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						char[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						char[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static int[] sortMerge(int[] A, int[] B, boolean lowToHeigh)
			{
				int[] result = new int[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static int[] mergeSort(int[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						int[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						int[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static long[] sortMerge(long[] A, long[] B, boolean lowToHeigh)
			{
				long[] result = new long[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static long[] mergeSort(long[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						long[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						long[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static float[] sortMerge(float[] A, float[] B, boolean lowToHeigh)
			{
				float[] result = new float[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static float[] mergeSort(float[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						float[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						float[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static double[] sortMerge(double[] A, double[] B, boolean lowToHeigh)
			{
				double[] result = new double[A.length + B.length];
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] <= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1] >= B[i2]))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static double[] mergeSort(double[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						double[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						double[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}
		private static<T extends Comparable<T>> T[] sortMerge(T[] A, T[] B, boolean lowToHeigh)
			{
				@SuppressWarnings("unchecked")
				T[] result = (T[]) Array.newInstance(A.getClass().getComponentType(), A.length + B.length);
				int i1 = 0, i2 = 0;
				if (lowToHeigh)
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1].compareTo(B[i2]) < 0))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				else
					{
						for (int i = 0; i < result.length; i++)
							{
								if (i2 >= B.length || (i1 < A.length && A[i1].compareTo(B[i2]) > 0))
									{
										result[i] = A[i1];
										i1++;
									}
								else
									{
										result[i] = B[i2];
										i2++;
									}
							}
					}
				return result;
			}
		public static<T extends Comparable<T>> T[] mergeSort(T[] array, boolean lowToHeigh)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						T[] low = mergeSort((T[]) ArrayUtils.subarray(array, 0, middle), lowToHeigh);
						T[] heigh = mergeSort((T[]) ArrayUtils.subarray(array, middle, array.length), lowToHeigh);
						return sortMerge(low, heigh, lowToHeigh);
					}
				return array;
			}	
		private static<T> T[] sortMerge(T[] A, T[] B, Comparator<T> c)
			{
				@SuppressWarnings("unchecked")
				T[] result = (T[]) Array.newInstance(A.getClass().getComponentType(), A.length + B.length);
				int i1 = 0, i2 = 0;
				for (int i = 0; i < result.length; i++)
					{
						if (i2 >= B.length || (i1 < A.length && c.compare(A[i1], B[i2]) < 0))
							{
								result[i] = A[i1];
								i1++;
							}
						else
							{
								result[i] = B[i2];
								i2++;
							}
					}
				return result;
			}
		public static<T> T[] mergeSort(T[] array, Comparator<T> c)
			{
				if (array.length > 1)
					{
						int middle = array.length/2;
						T[] low = mergeSort(ArrayUtils.subarray(array, 0, middle), c);
						T[] heigh = mergeSort(ArrayUtils.subarray(array, middle, array.length), c);
						return sortMerge(low, heigh, c);
					}
				return array;
			}
	}