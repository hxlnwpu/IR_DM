package hxl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CalVSM {
	
	public static String path;

	public CalVSM()
	{
		
	}
	public CalVSM(String catPath)  //构造函数
	{
		path=catPath;
	}
    
	public void getVSM() throws IOException   //计算VSM存于double[][] vsm中
	{
		System.out.println("请输入要查询词：");
		Scanner input=new Scanner(System.in);   
		String str = input.nextLine();      
		String[] arr=str.split("[^a-zA-Z0-9]+");
    
		File file = new File(path);
		double[][] vsm = new double[arr.length][file.listFiles().length];  //用于存放计算出来的tf-idf
		//System.out.println("一共有"+file.listFiles().length+"个文件");
		for (File f : file.listFiles())
		{
			for(int i=0,j=0;i<arr.length;i++,j++)
			{
				CalTF cal = new CalTF(f.getPath());
				String queryWord = arr[i];
				cal.calFreq();
				//System.out.println(queryWord + "的词频为：" + cal.getFreq(queryWord));		
				CalIDF alltxt=new CalIDF(queryWord,path);
				double idf=alltxt.getDocfeq();
				vsm[i][j]=Math.log(1+cal.getFreq(queryWord))*idf;
				//System.out.println(queryWord+"的idf为:"+idf);
				//System.out.println(queryWord+"对于文档"+path+"的tf-idf为:"+Math.log(1+cal.getFreq(queryWord))*idf);
			}		
		}
		for(int m=0;m<arr.length;m++)
		{
			for(int n=0;n<file.listFiles().length;n++)
			{
				System.out.print(vsm[m][n]+"  ");
			}
			System.out.println();
		}
		
    }
}
