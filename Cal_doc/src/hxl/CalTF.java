package hxl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CalTF    // �����ĵ��ڲ������ʵĴ�Ƶ
{ 

	BufferedReader br;
	List<String> lists = new ArrayList<String>(); // �洢���˺󵥴ʵ��б�
	Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // �洢���ʼ�����Ϣ��keyֵΪ���ʣ�valueΪ������
	
	public CalTF(String filepath) throws FileNotFoundException {   //���캯��
		br = new BufferedReader(new FileReader(filepath));
	}
	
	public void calFreq() throws IOException    //�����Ƶ����wordsCount 
	{
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			String[] wordsArr1 = readLine.split("[^a-zA-Z]"); // ���˳�ֻ������ĸ��
			for (String str : wordsArr1) {
				if (str.length()!= 0) { // ȥ������Ϊ0����
					lists.add(str);
				}
			}
		}
		br.close();
		// ���ʵĴ�Ƶͳ��
		for (String li : lists) {
			if (wordsCount.get(li) != null) {
				wordsCount.put(li, wordsCount.get(li) + 1);
			} else {
				wordsCount.put(li, 1);
			}
		}
	}
	
	public int getFreq(String term)   //�������������ʵĴ�Ƶ
	{
		if(wordsCount.containsKey(term)) return wordsCount.get(term);
		else return 0;
	}
}


