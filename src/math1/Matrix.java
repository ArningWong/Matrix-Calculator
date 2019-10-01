package math1;

//import java.math.BigDecimal;
import java.util.*;

class Matrix {

	// ������

	int column;
	int row;
	String[][] m1 = new String[row][column];
	String[][] m2 = new String[row][column];
	
	Matrix(int row, int column, String m1[][], String m2[][]) {
		this.row = row;
		this.column = column;
		this.m1 = m1;
		this.m2 = m2;
	}

	//ȥ����ĸ�е�1
	public String[][] printValue(String [][]s) {
		//��ӡ�����������
		String []tmp;
		String [][]re = new String[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
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
	
	public String matrixAdd() {
		// ���������
		String[][] sum = new String[row][column];
		Fraction f=new Fraction();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				f.getValue(m1[i][j], m2[i][j]);
				sum[i][j] = f.fAdd();
			}
		}
		
		//�������е�1ȥ��
		sum = printValue(sum);

		// �����תΪString��
		StringBuffer ans = new StringBuffer("");

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				ans.append(sum[i][j]);
				if (j == column - 1) {
					ans.append("\n");
				} else {
					ans.append("  ");
				}
			}
		}

		return ans.toString();
	}

	public String matrixSubstract() {
		// ���������
		String[][] sum = new String[row][column];
		Fraction f = new Fraction();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				f.getValue(m1[i][j],m2[i][j]);
				sum[i][j] = f.fSub();
			}
		}

		//�������е�1ȥ��
		sum = printValue(sum);
		// �����תΪString��
		StringBuffer ans = new StringBuffer("");

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				ans.append(sum[i][j]);
				if (j == column - 1) {
					ans.append("\n");
				} else {
					ans.append("  ");
				}
			}
		}
		return ans.toString();
	}
}

class MulMatrix {

	int column1;
	int row1;
	int column2;

	String[][] m1 = new String[row1][column1];
	String[][] m2 = new String[column1][column2];

	MulMatrix(int row1, int column1, int column2, String m1[][], String m2[][]) {
		this.row1 = row1;
		this.column1 = column1;
		this.column2 = column2;
		this.m1 = m1;
		this.m2 = m2;

	}

//ȥ����ĸ�е�1
	public String[][] printValue(String [][]s,int row,int column) {
		//��ӡ�����������
		String []tmp;
		String [][]re = new String[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
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
	
	public String matrixMultiply() {
		// ���������
		Fraction f = new Fraction();
		String[][] sum = new String[row1][column2];
		for (int i = 0; i < row1; i++) {
			for (int j = 0; j < column2; j++) {
				String s = "0";
				for (int k = 0; k < column1; k++) {
					f.getValue(m1[i][k],m2[k][j]);
					f.getValue(s,f.fMult());
					s = f.fAdd();
				}
				sum[i][j] = s;
			}
		}

		//�������е�1ȥ��
		sum = printValue(sum,row1,column2);
		// �����תΪString��
		StringBuffer ans = new StringBuffer("");

		for (int i = 0; i < row1; i++) {
			for (int j = 0; j < column2; j++) {
				ans.append(sum[i][j]);
				if (j == column2 - 1) {
					ans.append("\n");
				} else {
					ans.append(" ");
				}
			}
		}
		return ans.toString();
	}
}





//package math1;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//class Matrix {
//
//	// ������
//
//	int column;
//	int row;
//	String[][] m1 = new String[row][column];
//	String[][] m2 = new String[row][column];
//
//	Matrix(int row, int column, String m1[][], String m2[][]) {
//		this.row = row;
//		this.column = column;
//		this.m1 = m1;
//		this.m2 = m2;
//	}
//
//	public String matrixAdd() {
//		// ���������
//		String[][] sum = new String[row][column];
//
//		BigDecimal a = new BigDecimal("1");
//		BigDecimal b = new BigDecimal("1");
//		BigDecimal s = new BigDecimal("1");
//
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//
//				a = new BigDecimal(m1[i][j]);
//				b = new BigDecimal(m2[i][j]);
//				s = a.add(b);
//
//				sum[i][j] = s.toPlainString();
//
//			}
//		}
//
//		// �����תΪString��
//		StringBuffer ans = new StringBuffer("");
//
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//				ans.append(sum[i][j]);
//				if (j == column - 1) {
//					ans.append("\n");
//				} else {
//					ans.append("  ");
//				}
//			}
//		}
//
//		return ans.toString();
//	}
//
//	public String matrixSubstract() {
//		// ���������
//		String[][] sum = new String[row][column];
//
//		BigDecimal a = new BigDecimal("1");
//		BigDecimal b = new BigDecimal("1");
//		BigDecimal s = new BigDecimal("1");
//
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//
//				a = new BigDecimal(m1[i][j]);
//				b = new BigDecimal(m2[i][j]);
//				s = a.subtract(b);
//
//				sum[i][j] = s.toPlainString();
//
//			}
//		}
//
//		// �����תΪString��
//		StringBuffer ans = new StringBuffer("");
//
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//				ans.append(sum[i][j]);
//				if (j == column - 1) {
//					ans.append("\n");
//				} else {
//					ans.append("  ");
//				}
//			}
//		}
//		return ans.toString();
//	}
//}
//
//class MulMatrix {
//
//	int column1;
//	int row1;
//	int column2;
//
//	String[][] m1 = new String[row1][column1];
//	String[][] m2 = new String[column1][column2];
//
//	MulMatrix(int row1, int column1, int column2, String m1[][], String m2[][]) {
//		this.row1 = row1;
//		this.column1 = column1;
//		this.column2 = column2;
//		this.m1 = m1;
//		this.m2 = m2;
//
//	}
//
//	public String matrixMultiply() {
//		// ���������
//
//		String[][] sum = new String[row1][column2];
//		BigDecimal a = new BigDecimal("1");
//		BigDecimal b = new BigDecimal("1");
//		BigDecimal m = new BigDecimal("1");
//		BigDecimal s = new BigDecimal("0");
//
//		for (int i = 0; i < row1; i++) {
//			for (int j = 0; j < column2; j++) {
//				s = new BigDecimal("0");
//				for (int k = 0; k < column1; k++) {
//					a = new BigDecimal(m1[i][k]);
//					b = new BigDecimal(m2[k][j]);
//
//					m = a.multiply(b);
//					s = s.add(m);
//				}
//				sum[i][j] = s.toPlainString();
//			}
//		}
//
//		// �����תΪString��
//		StringBuffer ans = new StringBuffer("");
//
//		for (int i = 0; i < row1; i++) {
//			for (int j = 0; j < column2; j++) {
//				ans.append(sum[i][j]);
//				if (j == column2 - 1) {
//					ans.append("\n");
//				} else {
//					ans.append(" ");
//				}
//			}
//		}
//		return ans.toString();
//	}
//}
