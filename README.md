# Matrix-Calculator
---
## 目录
* [Matrix-Calculator](#Matrix-Calculator)
	* [一、项目介绍](#一项目介绍)
	* [二、功能介绍](#二功能介绍)
		* [1、矩阵的运算](#1矩阵的运算)
		* [2、求方阵的行列式](#2求方阵的行列式)
		* [3、求方阵的逆矩阵](#3求方阵的逆矩阵)
	* [三、特性](#三特性)
	* [四、输入规则](#四输入规则)
---
## 一、项目介绍
- 去年与@overflowzhang 一同参加了一个本校的程序设计比赛，开发语言为Java，感觉编写的还能入眼
- 主要实现矩阵的运算（加法、减法、乘法）、求行列式、求逆矩阵的功能
- 输入规则相对宽松，输入不正确会有反馈信息
- 可以输出分数结果与小数结果
- 未来可能有移植Android的打算，方便线性代数这门课程的学习
- 除了行列式的计算，其余代码都是一年前写的，很多都已经忘了怎么写的了。。。
- 所以日后readme还会在完善，代码也会进行一些优化
---
## 二、功能介绍
### 1、矩阵的计算
- 加法与减法的算法相对最简单，只需每一个对应的元素相加减即可
- 用到Matrix.java中的class Matrix矩阵类，包括matrixAdd()与matrixSubstract()两个方法
- 代码比较简单，不给予展示了
- 乘法的算法与加减法相比略困难，需要A矩阵的列数等于B矩阵的行数并做相乘求和操作
- 所以在Matrix.java中单独写一个class MulMatrix，并写一个用来使矩阵相乘的方法matrixMultiply()

```java
public String matrixMultiply() {
// 两矩阵相乘
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

	//将分数中的1去掉
	sum = printValue(sum,row1,column2);
	// 将结果转为String型
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
```
### 2、求方阵的行列式
- 计算方法取自《工程数学——线性代数 第五版》同济大学编写，第17页定理3
> 定理3 行列式等于它的任一行(列)的各元素与其对应的代数余子式乘积之和
- 这里采用：
- 若行列式或余子式只有一个元素，直接输出；
```java
if(size == 1) {
	f.getValue(Sm[0][0], "1");
	detvalue = f.fMult();
}
```
- 若余子式为两行，作交叉相乘并相减；
```java
if(m_size == 2) {
	f.getValue(minor[0][0], minor[1][1]);
	minor[0][0] = f.fMult();
	f.getValue(minor[0][1], minor[1][0]);
	minor[0][1] = f.fMult();
	f.getValue(minor[0][0], minor[0][1]);
	detvalue = f.fSub();

}
```
- 若行列式或余子式为两行以上，取行列式的第一行的各元素与其对应的代数余子式乘积之和
- 这里运用了递归的算法
```java
if(m_size > 2) {
	String detmp;
	String [][]re_minor = new String[m_size-1][m_size-1];
	String minor_det;
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
```
### 3、求方阵的逆矩阵
- 求解方法运用初等变换法，先初始化一个带有增广矩阵的矩阵M
```java
String [][]M = new String[size][2*size];

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
```
- 通过初等变换将矩阵M的左半部分变为单位矩阵
```java
//将M矩阵的左半部分下三角变成0
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
		
//将M矩阵的左半部分上三角变成0
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
		
//将左方阵对角线化为1
for (int i = 0; i < size; i++) {
	tmp1 = M[i][i];
	for (int j = size; j < 2 * size; j++) {
		f.getValue(M[i][j] ,tmp1);
		M[i][j] = f.fDivid();
	}	
}
```
- 矩阵M的右半部分便是原矩阵的逆矩阵
- 为了避免方阵对角线元素出现零的情况时程序出现错误，将矩阵中此列不为零的那一行加到这一行即可
```java
for (int i = 0; i < size; i++) {
	if (!M[i][i].equals("0"))
		continue;
	if (M[i][i].equals("0")) {
		//j=i+1 --> j = 0
		for (int j = 0; j < size; j++) {
			//找到此列中不为零的那一行
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
```
---
## 三、特性
- 结果分别有分数结果和小数结果，其中分数结果如果分母为1则省去，小数结果保留后两位小数
- 先用class Fraction分数类中的getValue(String s1,String s2)方法取得需要计算的值(要求严格计算先后顺序)
- 然后fAdd()，fSub()，fMult()，fDivid()可以用来做分数的加减乘数
- 为了实现分数运算后的约分，需要知道分子分母的最大公约数，所以写了一个求最大公约数的方法getGcd(int a,int b)，并运用了递归
- 采用分数结果可以有效解决精度问题，但取值范围也会变得有限，不过我想也没人算这么大的结果。。。
- 求最大公约数的方法的代码如下，其余代码见[Fraction.java](/src/math1/Fraction.java)
```java
public int getGcd(int a,int b) {
//求最大公约数
	if (a % b == 0)
		return b;
	else
		return getGcd(b,a%b);
}
```
- 输出的小数结果其实是借用了分数结果，用分子除以分母便得到了小数结果
```java
String []a = s.split("\\s+");
String []b = new String[a.length];

for (int i=0;i<a.length;i++) {
	String []temp = a[i].split("/");
	if (temp.length==1) {
		b[i] = a[i];
	}
	if (temp.length==2) {
		double d1 = Double.valueOf(temp[0]);
		double d2 = Double.valueOf(temp[1]);
		b[i] = String.format("%.2f", d1/d2);
	}
}
```
---
## 四、输入规则
- **输入输出形式：**
- 先从输入文本框得到数据并转换成String型
- 用正则表达式去掉空行以及空格、回车、tab并存入String数组
- 在转化过程中计算各矩阵的行数列数，并来判断是否符合矩阵或方阵
- 用得到的String数组进行计算，输出为String型
- **输入规则：**
- 每行开头不允许有空格与tab，其余地方(每个元素之间、每行的最后)都可以有很多个空格
- 每行之间可以有很多个空行，但每个空行不能有空格
- 可以输入分数，输入格式为：分子/分母
- 确定这个输入规则的原因：使每个元素每列对其，~~解决强迫症~~实现美观
