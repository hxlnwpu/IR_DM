import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.TextField;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

public class StopWords {
	public static void main(String[] args) throws Exception {

		BufferedReader stopwordsReader = new BufferedReader(new FileReader("stop.txt"));
		Analyzer analyzer = new StandardAnalyzer(stopwordsReader);
		BufferedReader fileReader = null;
		fileReader = new BufferedReader(new FileReader(new File("Casablanca.txt")));
		TokenStream ts = analyzer.tokenStream(null, fileReader);
		ts = new PorterStemFilter(ts);
		CharTermAttribute charTermAttribute = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		FileWriter fw = new FileWriter("Casablancas.txt", true);
		String data = "";
		while (ts.incrementToken()) {
			String term = charTermAttribute.toString();
			System.out.println(term);
			data = data + " " + term;
			fw.write(term + " ");
		}
		fw.close();
		ts.end();
		ts.close();

		Directory directory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
		// 创建IndexWriter:写索引
		IndexWriterConfig writerconfig = new IndexWriterConfig(new StandardAnalyzer());
		writerconfig.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory, writerconfig);
			// 创建Documents对象:该对象存储文件的信息，包括标题，类型，大小，内容等等的所有
			Document document = null;
			// 为Document添加Field：这里的Field就是文件的标题类型等域
			File file = new File("Casablancas.txt");
			document = new Document();
			document.add(new Field("content", new FileReader(file), TextField.TYPE_NOT_STORED));
			document.add(new Field("filename", file.getName(), StringField.TYPE_STORED));
			// 通过IndexWriter添加文件
			writer.addDocument(document);
			System.out.println("索引建立成功！");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		// 查询测试

		// 1.创建Directory
        Directory Sdirectory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
        // 2.创建IndexReader
        IndexReader reader = DirectoryReader.open(Sdirectory);
        // 3.根据IndexReader创建Searcher
        IndexSearcher searcher = new IndexSearcher(reader);
	    QueryParser parser = new QueryParser("content", analyzer);
	    Scanner s = new Scanner(System.in);
        String wquery = null;
        System.out.println("请输入您想查询的内容：");
        wquery = s.next();
        Query query = parser.parse(wquery);
	    ScoreDoc[] hits = searcher.search(query, 1000).scoreDocs;
	    System.out.println("共有"+hits.length+"个");
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	    	
	      Document hitDoc = searcher.doc(hits[i].doc);
	      System.out.println(hitDoc.toString());
          System.out.println("end "+hitDoc.get("filename"));
	      
	    }
	    reader.close();
	    directory.close();
	}
}
