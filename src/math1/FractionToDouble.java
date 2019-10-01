package math1;

public class FractionToDouble {

	public static String convert(String s,int row,int column) {
		
		String []a = s.split("\\s+");
		String []b = new String[a.length];
		
//		System.out.println(a.length);
		for (int i=0;i<a.length;i++) {
			String []temp = a[i].split("/");
			if (temp.length==1) {
				b[i] = a[i];
			}
			if (temp.length==2) {
				double d1 = Double.valueOf(temp[0]);
				double d2 = Double.valueOf(temp[1]);
				b[i] = String.format("%.2f", d1/d2);
//				System.out.println(b[i]);	
			}
		}
		
		StringBuffer ans = new StringBuffer("");
		for(int i = 0;i < row;i++) {
			for(int j = 0;j < column;j++) {
				ans.append(b[i*column+j]);
				if(j == column-1) {
					ans.append("\n");
				}
				else {
					ans.append("   ");
				}
			}
		}
	
		return ans.toString();
	}
//	public static void main(String[] args) {
//		
//		String s1="1/2 1/3 1/4 1/5";
//		String s2=convert(s1,2,2); 
//
//	}

}