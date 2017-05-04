package hxl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CalSim {
	
	public static String path;

	public CalSim()
	{
		
	}
	public CalSim(String catPath)  //构造函数
	{
		path=catPath;
	}
    
	public  double[] getVSM() throws IOException   //计算VSM存于double[][] vsm中
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
		System.out.println("向量空间模型计算如下：");
		for(int m=0;m<arr.length;m++)
		{
			for(int n=0;n<file.listFiles().length;n++)
			{
				System.out.print(vsm[m][n]+"  ");
			}
			System.out.println();
		}
		
		
		double[] sortvec = new double[file.listFiles().length];  //计算索引词到文档的距离（每列求和）  一行四列
		for(int p=0;p<file.listFiles().length;p++)   //列
			for(int q=0;q<arr.length;q++)   //行
				sortvec[p]=sortvec[p]+vsm[q][p];
		
		System.out.println("查询词与文档相似度计算如下：");
		for(int z=0;z<file.listFiles().length;z++)
			System.out.print(sortvec[z]+"  ");
		System.out.println();
		
		return sortvec;
			
    }
}
