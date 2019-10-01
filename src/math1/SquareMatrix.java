package math1;

import java.util.Random;

//������
public class SquareMatrix{
	int size;
	String [][]Sm = new String[size][size];
	
	SquareMatrix(int n,String [][] s){
		this.size = n;
		this.Sm = s;
	}
	
	public String[][] printValue(String [][]s) {
		//��ӡ�����������
		String []tmp;
		String [][]re = new String[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				//��ĸΪ1��ʱ��ֻ��ӡ������
				tmp = s[i][j].split("/");
				if (tmp.length == 2 && tmp[1].equals("1"))
					re[i][j]= tmp[0];
				else
					re[i][j] = s[i][j];
			}
		}
		return re;	
	}
	
	public String detValue() {
	//������ʽ��ֵ
		Fraction f = new Fraction();
		String detvalue = "0";
		String detmp;
		String [][]minor = new String[size-1][size-1];
		String minor_det;
		
		if(size == 1) {
			f.getValue(Sm[0][0], "1");
			detvalue = f.fMult();
		}

		if(size > 1) {
			for(int i = 0; i < size; i++) {
				minor = getMinor(Sm, size, i);
				minor_det = detValueII(minor, size-1);
				f.getValue(Sm[0][i], minor_det);
				detmp = f.fMult();
			
				if(i%2 == 0) {
					f.getValue(detvalue, detmp);
					detvalue = f.fAdd();
				}
				if(i%2 == 1) {
					f.getValue(detvalue, detmp);
					detvalue = f.fSub();
				}
			}
			//��ֹ����"1/-1"��"-1.00"�����
			f.getValue(detvalue, "1");
			detvalue = f.fMult();
		}
		if (detvalue.split("/")[1].equals("1"))
			return detvalue.split("/")[0];
		else
			return detvalue;
	}
	
	public String detValueII(String [][]minor, int m_size) {
	//��ԭ����ʽ������ʽ
		Fraction f = new Fraction();
		String detvalue = "0";
		
		if(m_size == 1) {
			f.getValue(minor[0][0], "1");
			detvalue = f.fMult();
			//return f.fMult();
		}
		if(m_size == 2) {
			f.getValue(minor[0][0], minor[1][1]);
			minor[0][0] = f.fMult();
			f.getValue(minor[0][1], minor[1][0]);
			minor[0][1] = f.fMult();
			f.getValue(minor[0][0], minor[0][1]);
			detvalue = f.fSub();
			//return f.fSub();
		}
		
		if(m_size > 2) {
			String detmp;
			String [][]re_minor = new String[m_size-1][m_size-1];
			String minor_det;
			/*
			re_minor = getMinor(minor, m_size, 0);
			minor_det = detValueII(re_minor, m_size-1);
			f.getValue(minor[0][0], minor_det);
			detvalue = f.fMult();
			*/
			for(int i = 0; i < m_size; i++) {
				re_minor = getMinor(minor, m_size, i);
				minor_det = detValueII(re_minor, m_size-1);
				f.getValue(minor[0][i], minor_det);
				detmp = f.fMult();
				
				if(i%2 == 0) {
					f.getValue(detvalue, detmp);
					detvalue = f.fAdd();
				}
				if(i%2 == 1) {
					f.getValue(detvalue, detmp);
					detvalue = f.fSub();
				}
			}
		}
		
		return detvalue;
	}
	
	public String[][] getMinor(String [][]ori_m, int o_size, int tmp){
	//������ʽĳһԪ������Ӧ������ʽ�ķ���
		String [][]minor = new String[o_size-1][o_size-1];
		for(int j = 0; j < tmp; j++)
			for(int i = 1; i < o_size; i++)
				minor[i-1][j] = ori_m[i][j];
		
		for(int j = tmp+1; j < o_size; j++)
			for(int i = 1; i < o_size; i++)
				minor[i-1][j-1] = ori_m[i][j];
		return minor;
	}
	
	/*
	public String detValue() {
	//�����Ӧ����ʽ��ֵ
		String [][]L = new String[size][size];
		String [][]U = new String[size][size];
		String [][]det = new String[size][size];
		String sum;
		String tmp;
		String detvalue;
		Fraction f = new Fraction();
		
		//Copyһ�ݶ�λSm����
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j++) {
				det[i][j] = Sm[i][j];
			}
		}
		
		//���ⷽ��Խ���Ԫ�س���������ʱ������ִ���
		for (int i = 0; i < size; i++) {
			if (!det[i][i].equals("0"))
				continue;
			if (det[i][i].equals("0")) {
				for (int j = 0; j < size; j++) {
					//�ҵ������в�Ϊ�����һ��
					if (! det[j][i].equals("0")) {
						for (int k = 0; k < size; k++) {
							f.getValue(det[j][k],det[i][k]);
							det[i][k] = f.fAdd();
							}
						break;
						}
					}
				}
			}
		
		
		//��ʼ�������Ǿ��� L
		for (int i = 0; i < size; i++)
			for (int j = i; j < size; j++) {
				if (i == j)
					L[i][j] = "1";
				else
					L[i][j] = "0";
			}
		
		//��ʼ�������Ǿ��� U
		for (int i = 1; i < size; i++)
			for (int j = 0; j < i; j++) {
				U[i][j] = "0";
			}
		
		//U[0][j] = det[0][j];
		for (int j = 0; j < size; j++)
			U[0][j] = det[0][j];
		
		//L[i][0] = det[i][0] / U[0][0]
		for (int i = 1; i < size; i++) {
			f.getValue(det[i][0],U[0][0]);
			L[i][0] = f.fDivid();
		}
		

		//����27ҳ3.28
		for (int r = 1; r < size; r++) {
			for (int j = r; j < size; j++) {
				sum = "0";
				for (int k = 0; k < r; k++) {
					f.getValue(L[r][k],U[k][j]);
					tmp = f.fMult();
					f.getValue(sum, tmp);
					sum = f.fAdd();
					//sum += L[r][k] * U[k][j];
				}
					
				f.getValue(det[r][j],sum);
				U[r][j] = f.fSub();
				//U[r][j] = det[r][j] - sum;
			}
			//����27ҳ3.28
			//r != N
			if (r != size - 1) {
				for (int i = r+1; i < size; i++) {
					sum = "0";
					for (int k = 0; k < r; k++) {
						//sum += L[i][k] * U[k][r];
						f.getValue(L[i][k],U[k][r]);
						tmp = f.fMult();
						f.getValue(sum, tmp);
						sum = f.fAdd();
					}
					//L[i][r] = (det[i][r] - sum) / U[r][r];
					f.getValue(det[i][r] , sum);
					tmp = f.fSub();
					f.getValue(tmp, U[r][r]);
					L[i][r] = f.fDivid();
				}
			}
			
		}

		detvalue = "1";
		//����Խ��ߵĳ˻�
		for (int i = 0; i < size; i++) {
			f.getValue(detvalue, U[i][i]);
			detvalue = f.fMult();
		}
		//��ӡ������ʽ��ֵdetvalue
		if (detvalue.split("/")[1].equals("1"))
			return detvalue.split("/")[0];
		else
			return detvalue;
	}
	*/
	
	public String getInverse() {
	//����������
		String [][]M = new String[size][2*size];
		String [][]inverse = new String[size][size];
		String []row = new String[2*size];
		String [][]r;
		String tmp1 = null,tmp2 = null,tmp3 = null;
		boolean flag = false;
		Random random = new Random();
		
		Fraction f = new Fraction();
		//��ʼ��һ���������
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				M[i][j] = Sm[i][j];
			}
			for (int j = size; j < 2*size; j++) {
				if (i == (j-size))
					M[i][j] = "1";
				else
					M[i][j] = "0";
			}
		}
		
		//���ⷽ��Խ���Ԫ�س���������ʱ������ִ���
		for (int i = 0; i < size; i++) {
			if (!M[i][i].equals("0"))
				continue;
			if (M[i][i].equals("0")) {
				//j=i+1 --> j = 0
				for (int j = 0; j < size; j++) {
					//�ҵ������в�Ϊ�����һ��
					if (! M[j][i].equals("0")) {
						String s = String.valueOf(random.nextInt(9)%(8) + 1);
						for (int k = 0; k < 2 * size; k++) {
							f.getValue(s, M[j][k]);
							f.getValue(f.fMult(),M[i][k]);
							M[i][k] = f.fAdd();
							}
						break;
						}
					}
				}
			}

		
//		//���ⷽ��Խ���Ԫ�س���������ʱ������ִ���
//		for (int i = 0; i < size; i++) {
//			if (!M[i][i].equals("0"))
//				continue;
//			if (M[i][i].equals("0")) {
//				for (int j = 0; j < size; j++) {
//					//�ҵ������в�Ϊ�����һ��
//					if (! M[j][i].equals("0")) {
//						for (int k = 0; k < 2 * size; k++) {
//							f.getValue(M[j][k],M[i][k]);
//							M[i][k] = f.fAdd();
//							}
//						break;
//						}
//					}
//				}
//			}	
		
//		System.out.println("------------------------------------");
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < 2*size; j++) {
//				System.out.print(M[i][j] + '\t');
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------------------");

		
		//��M�������벿�������Ǳ��0
		for (int i = 0; i < size - 1; i++) {
			for (int j = i+1; j < size; j++) {
				f.getValue(M[j][i], M[i][i]);
					tmp1 = f.fDivid();
				for (int k = 0; k < 2*size; k++) {
					f.getValue(M[i][k], tmp1);
					tmp2 = f.fMult();
					f.getValue(M[j][k], tmp2);
					M[j][k] = f.fSub();
				}
			}
		}
		
		//��M�������벿�������Ǳ��0
		for (int i = size-1; i > 0; i--) {
			for (int j = i-1; j >= 0; j--) {
				f.getValue(M[j][i], M[i][i]);
				tmp1 = f.fDivid();
				for (int k = 0; k < 2*size; k++) {
					f.getValue(M[i][k], tmp1);
					tmp2 = f.fMult();
					f.getValue(M[j][k], tmp2);
					M[j][k] = f.fSub();
				}
			}
		}
		
		//������Խ��߻�Ϊ1
		for (int i = 0; i < size; i++) {
			tmp1 = M[i][i];
			for (int j = size; j < 2 * size; j++) {
				f.getValue(M[i][j] ,tmp1);
				M[i][j] = f.fDivid();
			}	
		}
				
		//ȡ����Ϊ����������
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				inverse[i][j] = M[i][size+j];
			}
		}
		
//		System.out.println("--------------------����������------------------------------------");
		
		
		r = printValue(inverse);
		
		StringBuffer ans = new StringBuffer("");
		
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				ans.append(r[i][j]);
				if(j == size-1) {
					ans.append("\n");
				}
				else {
					ans.append("   ");
				}
			}
		}
		
		return ans.toString();
		
		
		
		
		
		
	}
}