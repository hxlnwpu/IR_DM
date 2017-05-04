package hxl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CalSim {
	
	public static String path;

	public CalSim()
	{
		
	}
	public CalSim(String catPath)  //���캯��
	{
		path=catPath;
	}
    
	public  double[] getVSM() throws IOException   //����VSM����double[][] vsm��
	{
		System.out.println("������Ҫ��ѯ�ʣ�");
		Scanner input=new Scanner(System.in);   
		String str = input.nextLine();      
		String[] arr=str.split("[^a-zA-Z0-9]+");
    
		File file = new File(path);
		double[][] vsm = new double[arr.length][file.listFiles().length];  //���ڴ�ż��������tf-idf
		//System.out.println("һ����"+file.listFiles().length+"���ļ�");
		for (File f : file.listFiles())
		{
			for(int i=0,j=0;i<arr.length;i++,j++)
			{
				CalTF cal = new CalTF(f.getPath());
				String queryWord = arr[i];
				cal.calFreq();
				//System.out.println(queryWord + "�Ĵ�ƵΪ��" + cal.getFreq(queryWord));		
				CalIDF alltxt=new CalIDF(queryWord,path);
				double idf=alltxt.getDocfeq();
				vsm[i][j]=Math.log(1+cal.getFreq(queryWord))*idf;
				//System.out.println(queryWord+"��idfΪ:"+idf);
				//System.out.println(queryWord+"�����ĵ�"+path+"��tf-idfΪ:"+Math.log(1+cal.getFreq(queryWord))*idf);
			}		
		}
		System.out.println("�����ռ�ģ�ͼ������£�");
		for(int m=0;m<arr.length;m++)
		{
			for(int n=0;n<file.listFiles().length;n++)
			{
				System.out.print(vsm[m][n]+"  ");
			}
			System.out.println();
		}
		
		
		double[] sortvec = new double[file.listFiles().length];  //���������ʵ��ĵ��ľ��루ÿ����ͣ�  һ������
		for(int p=0;p<file.listFiles().length;p++)   //��
			for(int q=0;q<arr.length;q++)   //��
				sortvec[p]=sortvec[p]+vsm[q][p];
		
		System.out.println("��ѯ�����ĵ����ƶȼ������£�");
		for(int z=0;z<file.listFiles().length;z++)
			System.out.print(sortvec[z]+"  ");
		System.out.println();
		
		return sortvec;
			
    }
}
