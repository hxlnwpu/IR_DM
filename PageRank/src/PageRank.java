import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageRank {
	private static final double ALPHA = 0.50;
	private static final double DISTANCE = 0.0000001;
	public static void main(String[] args) {
		// List<Double> q1=getInitQ(4);
		System.out.println("alpha的值为: " + ALPHA);
		List<Double> q1 = new ArrayList<Double>();
		q1.add(new Double(1.5));
		q1.add(new Double(1.2));
		q1.add(new Double(1.1));
		q1.add(new Double(1.0));
		System.out.println("初始的向量q为:");
		printVec(q1);
		System.out.println("初始的矩阵G为:");
		printMatrix(getG(ALPHA));
		List<Double> pageRank = calPageRank(q1, ALPHA);
		System.out.println("PageRank为:");
		printVec(pageRank);
		System.out.println();
	}
	public static void printMatrix(List<List<Double>> m) {
		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.get(i).size(); j++) {
				System.out.print(m.get(i).get(j) + ", ");
			}
			System.out.println();
		}
	}
	public static void printVec(List<Double> v) {
		for (int i = 0; i < v.size(); i++) {
			System.out.print(v.get(i) + ", ");
		}
		System.out.println();
	}
	public static List<Double> getInitQ(int n) {
		Random random = new Random();
		List<Double> q = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			q.add(new Double(5 * random.nextDouble()));
		}
		return q;
	}
	public static double calDistance(List<Double> q1, List<Double> q2) {
		double sum = 0;

		if (q1.size() != q2.size()) {
			return -1;
		}

		for (int i = 0; i < q1.size(); i++) {
			sum += Math.pow(q1.get(i).doubleValue() - q2.get(i).doubleValue(), 2);
		}
		return Math.sqrt(sum);
	}
	public static List<Double> calPageRank(List<Double> q1, double a) {

		List<List<Double>> g = getG(a);
		List<Double> q = null;
		while (true) {
			q = vectorMulMatrix(g, q1);
			double dis = calDistance(q, q1);
			if (dis <= DISTANCE) {
				System.out.print("特征向量q计算如下:\n");
				printVec(q);
				break;
			}
			q1 = q;
		}
		return q;
	}
	public static List<List<Double>> getG(double a) {

		int n = getS().size();
		List<List<Double>> aS = numberMulMatrix(getS(), a);
		List<List<Double>> nU = numberMulMatrix(getU(), (1 - a) / n);
		List<List<Double>> g = addMatrix(aS, nU);
		return g;
	}
	public static List<Double> vectorMulMatrix(List<List<Double>> m, List<Double> v) {
		if (m == null || v == null || m.size() <= 0 || m.get(0).size() != v.size()) {
			return null;
		}

		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < m.size(); i++) {
			double sum = 0;
			for (int j = 0; j < m.get(i).size(); j++) {
				double temp = m.get(i).get(j).doubleValue() * v.get(j).doubleValue();
				sum += temp;
			}
			list.add(sum);
		}

		return list;
	}
	public static List<List<Double>> addMatrix(List<List<Double>> list1, List<List<Double>> list2) {
		List<List<Double>> list = new ArrayList<List<Double>>();
		if (list1.size() != list2.size() || list1.size() <= 0 || list2.size() <= 0) {
			return null;
		}
		for (int i = 0; i < list1.size(); i++) {
			list.add(new ArrayList<Double>());
			for (int j = 0; j < list1.get(i).size(); j++) {
				double temp = list1.get(i).get(j).doubleValue() + list2.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}
	public static List<List<Double>> numberMulMatrix(List<List<Double>> s, double a) {
		List<List<Double>> list = new ArrayList<List<Double>>();

		for (int i = 0; i < s.size(); i++) {
			list.add(new ArrayList<Double>());
			for (int j = 0; j < s.get(i).size(); j++) {
				double temp = a * s.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}
	public static List<List<Double>> getS() {
		List<Double> row1 = new ArrayList<Double>();
		row1.add(new Double(0));
		row1.add(new Double(1/2.0));
		row1.add(new Double(1));
		row1.add(new Double(0));
		List<Double> row2 = new ArrayList<Double>();
		row2.add(new Double(1 / 3.0));
		row2.add(new Double(0));
		row2.add(new Double(0));
		row2.add(new Double(1/2.0));
		List<Double> row3 = new ArrayList<Double>();
		row3.add(new Double(1 / 3.0));
		row3.add(new Double(0));
		row3.add(new Double(0));
		row3.add(new Double(1/2.0));
		List<Double> row4 = new ArrayList<Double>();
		row4.add(new Double(1 / 3.0));
		row4.add(new Double(1 / 2.0));
		row4.add(new Double(0));
		row4.add(new Double(0));

		List<List<Double>> s = new ArrayList<List<Double>>();
		s.add(row1);
		s.add(row2);
		s.add(row3);
		s.add(row4);

		return s;
	}
	public static List<List<Double>> getU() {
		List<Double> row1 = new ArrayList<Double>();
		row1.add(new Double(1));
		row1.add(new Double(1));
		row1.add(new Double(1));
		row1.add(new Double(1));
		List<Double> row2 = new ArrayList<Double>();
		row2.add(new Double(1));
		row2.add(new Double(1));
		row2.add(new Double(1));
		row2.add(new Double(1));
		List<Double> row3 = new ArrayList<Double>();
		row3.add(new Double(1));
		row3.add(new Double(1));
		row3.add(new Double(1));
		row3.add(new Double(1));
		List<Double> row4 = new ArrayList<Double>();
		row4.add(new Double(1));
		row4.add(new Double(1));
		row4.add(new Double(1));
		row4.add(new Double(1));

		List<List<Double>> s = new ArrayList<List<Double>>();
		s.add(row1);
		s.add(row2);
		s.add(row3);
		s.add(row4);

		return s;
	}
}
