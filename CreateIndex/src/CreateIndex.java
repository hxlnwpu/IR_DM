import java.io.File; 
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.standard.StandardAnalyzer; 
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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.TextField;
public class CreateIndex {
	public static void main(String[] args) throws Exception{ 
		
		//建立索引
		// 创建Directory ：决定索引创建在内存中还是硬盘中
        Directory directory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
        // 创建IndexWriter:写索引
        IndexWriterConfig writerconfig = new IndexWriterConfig(new StandardAnalyzer());
        writerconfig.setOpenMode(OpenMode.CREATE);
        IndexWriter writer = null;
        try
        {
            writer = new IndexWriter(directory, writerconfig);
            // 创建Documents对象:该对象存储文件的信息，包括标题，类型，大小，内容等等的所有
            Document document = null;
            // 为Document添加Field：这里的Field就是文件的标题类型等域
            File file = new File("F:\\Workspace\\Eclipse\\Collections");
            System.out.println("一共对"+file.listFiles().length+"个文件");
            for (File f : file.listFiles())
            {
                document = new Document();
                document.add(new Field("content", new FileReader(f), TextField.TYPE_NOT_STORED));
                document.add(new Field("filename", f.getName(),StringField.TYPE_STORED));
                // 通过IndexWriter添加文件
                writer.addDocument(document);
            }
            System.out.println("索引建立成功！");
        } catch (IOException e)
        {
            
            e.printStackTrace();
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                } catch (IOException e)
                {
                    
                    e.printStackTrace();
                }
            }
	    } 
        //查询测试
        
        try
        {
            // 1.创建Directory
            Directory Sdirectory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
            // 2.创建IndexReader
            IndexReader reader = DirectoryReader.open(Sdirectory);
            // 3.根据IndexReader创建Searcher
            IndexSearcher searcher = new IndexSearcher(reader);

            // 4.创建Query
            QueryParser parser = new QueryParser("content", new StandardAnalyzer());
            // 5.使用Searcher使用返回TopDocs
            Scanner s = new Scanner(System.in);
            String wquery = null;
            System.out.println("请输入您想查询的内容：");
            wquery = s.next();
            Query query = parser.parse(wquery);
            //System.out.println("Searching for: " + query.toString("contents"));
            // 6.根据TopDocs得到ScoreDoc
            TopDocs topdocs = searcher.search(query, 5);
            // 7.根据ScoreDoc得到具体的Document
            ScoreDoc[] sds = topdocs.scoreDocs;
            System.out.println("共查询到"+sds.length+"篇文档");
            for (ScoreDoc sd : sds)
            {
                Document d = searcher.doc(sd.doc);
                System.out.println(d.toString());
                System.out.println("end "+d.get("filename"));
            }
            // 8.根据Document对象获取具体的值
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

  
	}
}
