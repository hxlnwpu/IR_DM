package hxl;


//������
 public class CalDoc {

	public static void main(String[] args) throws Exception {

		
		String path = "F:\\Workspace\\Eclipse\\Java\\Cal_doc\\test";
		double[] simvec = new double[100];
		CalSim testVSM=new CalSim(path);
		simvec=testVSM.getVSM();

		//��simvec����
		double temp = 0.0;
		int size = simvec.length,index=0;
		int[] pindex=new int[simvec.length];
		for(int i = 0; i < simvec.length; i++){   //�±����鸳��ֵ		
			     pindex[i] = i+1;
			}
		for(int i = 0 ; i < size-1; i ++)
		{
		    for(int j = 0 ;j < size-1-i ; j++)
		    {
		        if(simvec[j] <simvec[j+1])  //��������λ��
		        {
		        temp = simvec[j];simvec[j] = simvec[j+1];simvec[j+1] = temp;
		        index=pindex[j];pindex[j]=pindex[j+1];pindex[j+1]=index;        
		        }
		    }
		 }
		System.out.println("��ѯ�����������");
		for(int r=0;r<simvec.length;r++)
			System.out.println("test"+pindex[r]+".txt");		
    }
}

