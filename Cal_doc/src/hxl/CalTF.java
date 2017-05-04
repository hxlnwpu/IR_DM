package hxl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CalTF    // 计算文档内部索引词的词频
{ 

	BufferedReader br;
	List<String> lists = new ArrayList<String>(); // 存储过滤后单词的列表
	Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // 存储单词计数信息，key值为单词，value为单词数
	
	public CalTF(String filepath) throws FileNotFoundException {   //构造函数
		br = new BufferedReader(new FileReader(filepath));
	}
	
	public void calFreq() throws IOException    //计算词频存于wordsCount 
	{
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			String[] wordsArr1 = readLine.split("[^a-zA-Z]"); // 过滤出只含有字母的
			for (String str : wordsArr1) {
				if (str.length()!= 0) { // 去除长度为0的行
					lists.add(str);
				}
			}
		}
		br.close();
		// 单词的词频统计
		for (String li : lists) {
			if (wordsCount.get(li) != null) {
				wordsCount.put(li, wordsCount.get(li) + 1);
			} else {
				wordsCount.put(li, 1);
			}
		}
	}
	
	public int getFreq(String term)   //返回所给索引词的词频
	{
		if(wordsCount.containsKey(term)) return wordsCount.get(term);
		else return 0;
	}
}


