package hxl;

import java.io.File;
import java.io.IOException;


public class CalIDF {

	public static String term;
	public static String path;
	
	public CalIDF(String lterm,String lpath)
	{
		path=lpath;
		term=lterm;
	}
	public double getDocfeq() throws IOException    //返回给定索引词针对所给文档的tf-idf
	{
		double i=0.0;
		File file = new File(path);
        //System.out.println("一共有"+file.listFiles().length+"个文件");
        for (File f : file.listFiles())
        {
        	CalTF doc=new CalTF(f.getPath());
        	doc.calFreq();
    		if (doc.getFreq(term)!=0) 
    			i++;
        }
		//System.out.println("一共有"+i+"个文件里存在索引词");
		if(i!=0) return Math.log((file.listFiles().length)/i);
		else return 0;
				
			
	}
	
}
