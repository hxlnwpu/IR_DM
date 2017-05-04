import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IK_test {
	public static void main(String[] args) {

		String str = "微软是一家总部位于美国的跨国科技公司 " + "共同创造美好的新世纪 " + "工信处女干事每月经过下属科室都要交代交换机的维护工作 " + "南京市长江大桥";
		IKAnalysis(str);
	}

	public static List<String> IKAnalysis(String str) {
		List<String> keywordList = new ArrayList<String>();
		try {
			byte[] bt = str.getBytes();
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			IKSegmenter iks = new IKSegmenter(read, true);
			Lexeme t;
			while ((t = iks.next()) != null) {
				keywordList.add(t.getLexemeText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(keywordList);
		return keywordList;
	}
}