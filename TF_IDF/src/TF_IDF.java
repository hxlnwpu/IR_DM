import java.util.List;
import java.util.regex.Pattern;

public class TF_IDF {

	private static Pattern r = Pattern.compile("([ \\t{}()\",:;. \n])");
	private static List<String> documentCollection;

	// Calculates TF-IDF weight for each term t in document d
	private static float findTFIDF(String document, String term) {
		float tf = findTermFrequency(document, term);
		float idf = findInverseDocumentFrequency(term);
		return tf * idf;
	}

	private static float findTermFrequency(String document, String term) {
		int count = getFrequencyInOneDoc(document, term);

		return (float) ((float) count / (float) (r.split(document).length));
	}

	private static int getFrequencyInOneDoc(String document, String term) {
		int count = 0;
		for (String s : r.split(document)) {
			if (s.toUpperCase().equals(term.toUpperCase())) {
				count++;
			}
		}
		return count;
	}

	private static float findInverseDocumentFrequency(String term) {
		// find the no. of document that contains the term in whole document
		// collection
		int count = 0;
		for (String doc : documentCollection) {
			count += getFrequencyInOneDoc(doc, term);
		}
		/*
		 * log of the ratio of total no of document in the collection to the no.
		 * of document containing the term we can also use
		 * Math.Log(count/(1+documentCollection.Count)) to deal with divide by
		 * zero case;
		 */
		return (float) Math.log((float) documentCollection.size() / (float) count);

	}

}
