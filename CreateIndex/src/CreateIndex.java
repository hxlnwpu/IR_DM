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
		
		//��������
		// ����Directory �����������������ڴ��л���Ӳ����
        Directory directory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
        // ����IndexWriter:д����
        IndexWriterConfig writerconfig = new IndexWriterConfig(new StandardAnalyzer());
        writerconfig.setOpenMode(OpenMode.CREATE);
        IndexWriter writer = null;
        try
        {
            writer = new IndexWriter(directory, writerconfig);
            // ����Documents����:�ö���洢�ļ�����Ϣ���������⣬���ͣ���С�����ݵȵȵ�����
            Document document = null;
            // ΪDocument���Field�������Field�����ļ��ı������͵���
            File file = new File("F:\\Workspace\\Eclipse\\Collections");
            System.out.println("һ����"+file.listFiles().length+"���ļ�");
            for (File f : file.listFiles())
            {
                document = new Document();
                document.add(new Field("content", new FileReader(f), TextField.TYPE_NOT_STORED));
                document.add(new Field("filename", f.getName(),StringField.TYPE_STORED));
                // ͨ��IndexWriter����ļ�
                writer.addDocument(document);
            }
            System.out.println("���������ɹ���");
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
        //��ѯ����
        
        try
        {
            // 1.����Directory
            Directory Sdirectory = FSDirectory.open(Paths.get("F:\\Workspace\\Eclipse\\Index"));
            // 2.����IndexReader
            IndexReader reader = DirectoryReader.open(Sdirectory);
            // 3.����IndexReader����Searcher
            IndexSearcher searcher = new IndexSearcher(reader);

            // 4.����Query
            QueryParser parser = new QueryParser("content", new StandardAnalyzer());
            // 5.ʹ��Searcherʹ�÷���TopDocs
            Scanner s = new Scanner(System.in);
            String wquery = null;
            System.out.println("�����������ѯ�����ݣ�");
            wquery = s.next();
            Query query = parser.parse(wquery);
            //System.out.println("Searching for: " + query.toString("contents"));
            // 6.����TopDocs�õ�ScoreDoc
            TopDocs topdocs = searcher.search(query, 5);
            // 7.����ScoreDoc�õ������Document
            ScoreDoc[] sds = topdocs.scoreDocs;
            System.out.println("����ѯ��"+sds.length+"ƪ�ĵ�");
            for (ScoreDoc sd : sds)
            {
                Document d = searcher.doc(sd.doc);
                System.out.println(d.toString());
                System.out.println("end "+d.get("filename"));
            }
            // 8.����Document�����ȡ�����ֵ
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

  
	}
}
