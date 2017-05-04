
public class VSM {
	public static float findCosineSimilarity(float[] vecA, float[] vecB) {
		float dotProduct = dotProduct(vecA, vecB);
		float magnitudeOfA = magnitude(vecA);
		float magnitudeOfB = magnitude(vecB);
		float result = dotProduct / (magnitudeOfA * magnitudeOfB);
		// when 0 is divided by 0 it shows result NaN so return 0 in such case.
		if (Float.isNaN(result))
			return 0;
		else
			return (float) result;
	}

	public static float dotProduct(float[] vecA, float[] vecB) {

		float dotProduct = 0;
		for (int i = 0; i < vecA.length; i++) {
			dotProduct += (vecA[i] * vecB[i]);
		}

		return dotProduct;
	}

	// Magnitude of the vector is the square root of the dot product of the
	// vector with itself.
	public static float magnitude(float[] vector) {
		return (float) Math.sqrt(dotProduct(vector, vector));
	}
}
