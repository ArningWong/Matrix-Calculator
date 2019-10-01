package math1;

//������
public class Fraction{
	
	int m1;
	int m2;
	int d1;
	int d2;
	
	public void getValue(String s1,String s2){
		
		//s1��С�������
		if (s1.split("\\.").length==2) {
		String str1[]=s1.split("\\.");
		int len=str1[1].length();
		int a=Integer.valueOf(str1[0]).intValue();
		int b=Integer.valueOf(str1[1]).intValue();
		m1=(int) (a * Math.pow(10, len)) + b;
		d1=(int)Math.pow(10, len);
		}
		//s1�Ƿ��������������
		else {
			String []str1 = s1.split("/");
			m1 = Integer.valueOf(str1[0]).intValue();
			if (str1.length == 1)  //���������
				d1 = 1;
			else
				//�ַ���ת��������
				d1 = Integer.valueOf(str1[1]).intValue();
			}
				//s2��С�������
			if (s2.split("\\.").length==2) {
				String str2[]=s2.split("\\.");
				int len=str2[1].length();
				int a=Integer.valueOf(str2[0]).intValue();
				int b=Integer.valueOf(str2[1]).intValue();
				m2=(int) (a * Math.pow(10, len)) + b;
				d2=(int)Math.pow(10, len);
				}
				//s2�Ƿ��������������
			else {
				String []str2 = s2.split("/");
				m2 = Integer.valueOf(str2[0]).intValue();
				if (str2.length == 1)
					d2 = 1;
				else
						//�ַ���ת��������
					d2 = Integer.valueOf(str2[1]).intValue();
				}
				
		
//		String []str1 = s1.split("/");
//		String []str2 = s2.split("/");
//		m1 = Integer.valueOf(str1[0]).intValue();
//		m2 = Integer.valueOf(str2[0]).intValue();
//		if (str1.length == 1)  //���������
//			d1 = 1;
//		else
//			//�ַ���ת��������
//			d1 = Integer.valueOf(str1[1]).intValue();
//		if (str2.length == 1)
//			d2 = 1;
//		else
//			//�ַ���ת��������
//			d2 = Integer.valueOf(str2[1]).intValue();
		
	}
	
	//���������
	public String fAdd() {
		int m = m1 * d2 + m2 * d1;
		int d = d1 * d2;
		if (m1 == 0 && m2 ==0)
			return "0";
		if(m == 0)
			return "0";
		int gcd = getGcd(d,m);
		//���ַ�������ʽ����
		return m/gcd + "/" + d/gcd;
	}
	
	//���������
	public String fSub() {
		int d = d1 * d2;
		int m = m1 * d2 - m2 * d1;
		if (m1 == 0 && m2 == 0)
			return "0";
		if(m == 0)
			return "0";
		int gcd = getGcd(m,d);
		//���ַ�������ʽ����
		return m/gcd + "/" + d/gcd;
	}
	
	//���������
	public String fMult() {
		int m = m1 * m2;
		int d = d1 * d2;
		if (m1 == 0 || m2 == 0) {
			return "0";
		}
		else {
			int gcd = getGcd(m,d);
			return m/gcd + "/" + d/gcd;
		}
		
	}
	
	//���������
	public String fDivid() {
		if (m2 == 0) {
	//		System.out.println("��������Ϊ0��");
			return "0";
		}

		else {
			int m = m1 * d2;
			int d = d1 * m2;
			if (m1 == 0 || m2 == 0) {
				return "0";
			}
			else {
				int gcd = getGcd(m,d);
				return m/gcd + "/" + d/gcd;
			}
		}
	}
	
	public int getGcd(int a,int b) {//�����Լ��
		if (a % b == 0)
			return b;
		else
			return getGcd(b,a%b);
	}
}