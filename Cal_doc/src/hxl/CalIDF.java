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
	public double getDocfeq() throws IOException    //���ظ�����������������ĵ���tf-idf
	{
		double i=0.0;
		File file = new File(path);
        //System.out.println("һ����"+file.listFiles().length+"���ļ�");
        for (File f : file.listFiles())
        {
        	CalTF doc=new CalTF(f.getPath());
        	doc.calFreq();
    		if (doc.getFreq(term)!=0) 
    			i++;
        }
		//System.out.println("һ����"+i+"���ļ������������");
		if(i!=0) return Math.log((file.listFiles().length)/i);
		else return 0;
				
			
	}
	
}
