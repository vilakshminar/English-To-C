package etoc;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
public class EnterSentence extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton translate;
	private JTextField sentence;
	int next=0,id=0,macroId=1,choice,idPtr=0,idPtr1=0;
	private JComboBox<?> jcb;
	String scanSentence,func,declarations,headers,processSentence[],arithStr[],tagged;
	StringBuffer sb;
	FileInputStream fl;
	String sortAlgos[]={"Insertion","Bubble","Heap","Merge","Quick","Count"};
    private BufferedReader reader;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterSentence frame = new EnterSentence();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public String readFile( String file ) throws IOException 
    {
	    reader = new BufferedReader( new FileReader (file));
	    String line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String ls = System.getProperty("line.separator");
	    while( ( line = reader.readLine() ) != null ) 
	    {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    return stringBuilder.toString();
    }
    public void finish()
    {
    	File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw;
        try 
		{
			fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			String nl=System.getProperty("line.separator");
			bw.write("#include<stdio.h>"+nl);
			bw.write("#include<stdlib.h>"+nl);
			bw.write("//For float,double,longint,shortint change the datatype of a[] accordingly"+nl);
			if(choice==1)
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/insertionsort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"insertionsort(a,n)"+nl+"return 0;"+nl+"}");
			}
			else if(choice==2)
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/bubblesort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"bubblesort(a,n)"+nl+"return 0;"+nl+"}");
			}
			else if(choice==3)
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/heapsort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"heapsort(a,n)"+nl+"return 0;"+nl+"}");
			}
			else if(choice==4)
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/mergesort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"partition(a,0,n)"+nl+"return 0;"+nl+"}");
			}
			else if(choice==5)
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/quicksort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"quicksort(a,0,n)"+nl+"return 0;"+nl+"}");
			}
			else
			{
				func=readFile("F:/CompilerProject/EnglishToC/src/etoc/countsort.txt");
				bw.write(func);
				bw.write("int main()"+nl+"{"+nl+"int i,n;"+nl+"int a[1000];"+nl+"scanf(\"%d\",&n);"+nl);
				bw.write("for(i=0;i<n;i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl+"countsort(a,n)"+nl+"return 0;"+nl+"}");
			}
			bw.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void display()
	{
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        String str;
        JScrollPane scrollPane=null;
		try 
		{
			FileReader fr = new FileReader(file);
		    BufferedReader readFile = new BufferedReader(fr);
		    StringBuffer sb=new StringBuffer();
		    while((str=readFile.readLine())!=null)
		    {
		    	sb.append(str);
		    	sb.append("\n");
		    }
		    JTextPane displayArea=new JTextPane();
		    displayArea.setEditable(false);
		    //displayArea.setBackground(Color.green);
		    //label.setBounds(getX(), getY(),50,400);
		    JFrame displayFrame=new JFrame("Display");
			displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			displayFrame.setSize(500,500);
	        displayFrame.setLocationByPlatform(true);
	        //label=new JLabel();
	       // displayFrame.add(label);
	        displayFrame.setLayout(new FlowLayout());
	      //  label.setText("Code:"+"<br>");
	        displayFrame.add(displayArea);
	        scrollPane=new JScrollPane();
	        scrollPane.setEnabled(true);
	        scrollPane.getVerticalScrollBar();
	        scrollPane.getAccessibleContext();
	        displayFrame.add(scrollPane);
	      //  displayFrame.getContentPane().add(new JScrollPane(displayArea));
		  //  JPanel panel= new JPanel(new BorderLayout());
		    
	       // panel.add(label,BorderLayout.NORTH);
	       // JScrollPane scrollPane=new JScrollPane();
	       // scrollPane.setEnabled(true);
	       // panel.add(scrollPane);
	        
	        //displayFrame.add(panel);
	        displayFrame.setVisible(true);
	        displayArea.setText(sb.toString());
	        readFile.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void loop(String n,String type) throws IOException
	{
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        bw.write("#include<stdio.h>"+nl);
        bw.write("int main()"+nl+"{"+nl);
		if(type.equals("integer"))
		{
		    String loopStr="int a["+n+"];"+nl+"for(i=0;i<"+n+";i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl;
	        bw.write(loopStr);
	    }
		else if(type.equals("float"))
		{
	        String loopStr="float a["+n+"];"+nl+"for(i=0;i<"+n+";i++)"+nl+"{"+nl+"scanf(\"%f\",&a[i]);"+nl+"}"+nl;
	        bw.write(loopStr);
	       
		}
		else if(type.equals("char"))
		{
	        String loopStr="char a["+n+"];"+nl+"for(i=0;i<"+n+";i++)"+nl+"{"+nl+"scanf(\"%c\",&a[i]);"+nl+"}"+nl;
	        bw.write(loopStr);
	        
		}
		else if(type.equals("double"))
		{
	        String loopStr="double a["+n+"];"+nl+"for(i=0;i<"+n+";i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl;
	        bw.write(loopStr);
	  
		}
		else //it is of type string
		{
	        String loopStr="char a["+n+"];"+nl+"for(i=0;i<"+n+";i++)"+nl+"{"+nl+"scanf(\"%d\",&a[i]);"+nl+"}"+nl;
	        bw.write(loopStr);
		}
		bw.close();
    }
	public void input(String subString) throws IOException
	{	
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        if(subString.contains("input")&&!subString.contains("array"))
        {
        	bw.write("#include<stdio.h>"+nl);
        	bw.write("int main()"+nl+"{"+nl);
        }
    	if(subString.contains("array"))  									//if the sentence contains the word array
    	{
    		Matcher matcher = Pattern.compile("\\d+").matcher(subString);  	//matches the numbers from subString
    		while(matcher.find()) 											//loop till there are numbers in subString
    		{
        		String numStr=matcher.group();								//store the numbers in numStr
        		System.out.println("numstr="+numStr);
        		if(subString.contains("integers")||subString.contains("integer"))
        			loop(numStr,"integer");
        		else if(subString.contains("floating point")||subString.contains("float"))
        			loop(numStr,"float");
        		else if(subString.contains("char")||subString.contains("character"))
        			loop(numStr,"char");
        		else if(subString.contains("double")||subString.contains("real"))
        			loop(numStr,"double");
        		else if(subString.contains("string"))
        			loop(numStr,"char[]");
    		}
    	}
    	else if(subString.contains("pointer"))								//if the sentence contains the word pointer
    	{
    		String numPointer="",str="";
    		idPtr1=idPtr;
    		Matcher matcher = Pattern.compile("\\d+").matcher(subString);  	//matches the numbers from subString
    		while(matcher.find()) 											//loop till there are numbers in subString
    		{
    			numPointer=matcher.group();
    		}
			//System.out.println("numstr="+numPointer);
			int numPtr=Integer.parseInt(numPointer);
			if(subString.contains("integer"))
			{	
				bw.write("int ");
				for(int i=0;i<numPtr;i++)
				{
					idPtr++;
					if(numPtr==1||i==numPtr-1)
						bw.write("*intptr"+idPtr+";"+nl);
					else
						bw.write("*intptr"+idPtr+",");		
				}
    			for(int i=0;i<numPtr;i++)
    			{
    				idPtr1++;
					str="scanf(\"%d\",*intptr"+idPtr1+");"+nl;
					bw.write(str);
    			}
    		}
			else if(subString.contains("float"))
			{	
				bw.write("float ");
				for(int i=0;i<numPtr;i++)
				{
					idPtr++;
					if(numPtr==1||i==numPtr-1)
						bw.write("*floatptr"+idPtr+";"+nl);
					else
						bw.write("*floatptr"+idPtr+",");
				}
				for(int i=0;i<numPtr;i++)
				{
					idPtr1++;
					str="scanf(\"%f\",*floatptr"+idPtr1+");"+nl;
					bw.write(str);
				}
			}
			else if(subString.contains("char"))
			{	
				bw.write("char ");
				for(int i=0;i<numPtr;i++)
				{
					idPtr++;
					if(numPtr==1||i==numPtr-1)
						bw.write("*charptr"+idPtr+";"+nl);
					else
						bw.write("*charptr"+idPtr+",");
				}
				for(int i=0;i<numPtr;i++)
				{
					idPtr1++;
					str="scanf(\"%c\",*charptr"+idPtr1+");"+nl;
					bw.write(str);
				}
			}
			else if(subString.contains("double"))
			{	
				bw.write("double ");
				for(int i=0;i<numPtr;i++)
				{
					idPtr++;
					if(numPtr==1||i==numPtr-1)
						bw.write("*doubleptr"+idPtr+";"+nl);
					else
						bw.write("*doubleptr"+idPtr+",");
				}
				for(int i=0;i<numPtr;i++)
				{
					idPtr1++;
					str="scanf(\"%lf\",*doubleptr"+idPtr1+");"+nl;
					bw.write(str);
				}
			}
    	}
    	else	
		{
    		System.out.println("string:"+subString);
    		int numStr=0;
    		String numberStr="";
    		Matcher matcher = Pattern.compile("\\d+").matcher(subString);  	//matches the numbers from subString
    		while(matcher.find()) 											//loop till there are numbers in subString
    		{
        		numberStr=matcher.group();	
    		}
    		if(numberStr!=null)
    			numStr=Integer.parseInt(numberStr);
			if(subString.contains("integer")||subString.contains("int")&&!subString.contains("print")&&!subString.contains("floating point"))
			{
				if(!subString.contains("arguments"))
					bw.write("int ");
				for(int i=1;i<=numStr;i++)
				{
					id++;
					if(i!=numStr)
					{
						if(subString.contains("arguments"))
							bw.write("int intvar"+id+",");
						else
							bw.write("intvar"+id+",");
					}
					else
					{
						if(subString.contains("arguments"))
							//bw.write("int intvar"+id+")"+nl+"{"+nl);
							bw.write("int intvar"+id);
						else
							bw.write("intvar"+id+";"+nl);
					}
				}
				if(!subString.contains("arguments"))
				{
					id=id-(numStr-1);
					for(int i=1;i<=numStr;i++)
					{
						bw.write("scanf(\"%d\",&intvar"+id+");"+nl);										//write to file
						id++;
					}
				}
			}
			else if(subString.contains("floating point")||subString.contains("float"))
			{
				if(!subString.contains("arguments"))
					bw.write("float ");
				for(int i=1;i<=numStr;i++)
				{
					id++;
					if(i!=numStr)
					{
						if(subString.contains("arguments"))
							bw.write("float floatvar"+id+",");
						else
							bw.write("floatvar"+id+",");
					}
					else
					{
						if(subString.contains("arguments"))
							//bw.write("float floatvar"+id+")"+nl+"{"+nl);
							bw.write("int intvar"+id);
						else
							bw.write("floatvar"+id+";"+nl);
					}
				}
				if(!subString.contains("arguments"))
				{
					id=id-(numStr-1);
					for(int i=1;i<=numStr;i++)
					{
						bw.write("scanf(\"%f\",&floatvar"+id+");"+nl);										//write to file
						id++;
					}	
				}
			}
			else if(subString.contains("char")||subString.contains("character"))
			{
				if(!subString.contains("arguments"))
					bw.write("char ");
				for(int i=1;i<=numStr;i++)
				{
					id++;
					if(i!=numStr)
					{
						if(subString.contains("arguments"))
							bw.write("char charvar"+id+",");
						else
							bw.write("charvar"+id+",");
					}
					else
					{
						if(subString.contains("arguments"))
							bw.write("char charvar"+id+")"+nl+"{"+nl);
						else
							bw.write("charvar"+id+";"+nl);
					}
				}
				if(!subString.contains("arguments"))
				{
					id=id-(numStr-1);
					for(int i=1;i<=numStr;i++)
					{
						bw.write("scanf(\"%c\",&charvar"+id+");"+nl);										//write to file
						id++;
					}
				}
			}
			else if(subString.contains("double")||subString.contains("real"))
			{
				if(!subString.contains("arguments"))
					bw.write("double ");
				for(int i=1;i<=numStr;i++)
				{
					id++;
					if(i!=numStr)
					{
						if(subString.contains("arguments"))
							bw.write("double doublevar"+id+",");
						else
							bw.write("doublevar"+id+",");
					}
					else
					{
						if(subString.contains("arguments"))
							bw.write("double doublevar"+id+")"+nl+"{"+nl);
						else
							bw.write("doublevar"+id+";"+nl);
					}
				}
				if(!subString.contains("arguments"))
				{
					id=id-(numStr-1);
					for(int i=1;i<=numStr;i++)
					{
						bw.write("scanf(\"%lf\",&doublevar"+id+");"+nl);										//write to file
						id++;
					}	
				}
			}	
			else if(subString.contains("string"))
			{
				if(!subString.contains("arguments"))
					bw.write("char ");
				for(int i=1;i<=numStr;i++)
				{
					id++;
					if(i!=numStr)
					{
						if(subString.contains("arguments"))
							bw.write("string stringvar"+id+"[100],");
						else
							bw.write("stringvar"+id+"[100],");
					}
					else
					{
						if(subString.contains("arguments"))
							bw.write("string stringvar"+id+"[100])"+nl+"{"+nl);
						else
							bw.write("stringvar"+id+"[100];"+nl);
					}
				}
				if(!subString.contains("arguments"))
				{
					id=id-(numStr-1);
					for(int i=1;i<=numStr;i++)
					{
						bw.write("scanf(\"%s\",stringvar"+id+");"+nl);										//write to file
						id++;
					}
				}
			}
		}
    	//if(!subString.contains("arguments"))
    		//bw.write("}"+nl);
    	if(subString.contains("input")&&(!subString.contains("add")||!subString.contains("subtract")||!subString.contains("difference")
    	  ||!subString.contains("multiply")||!subString.contains("product")||!subString.contains("divide")
    	  ||!subString.contains("quotient")||!subString.contains("remainder")))
    	{
    		bw.write("return 0;"+nl+"}");
    	}
		bw.close();
		if(subString.contains("input"))
		{	
			display();
		}	
	}
	
	public void arithmeticOperations(String arithmeticStr)throws IOException 
	{
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        bw.write("#include<stdio.h>"+nl);
        bw.write("int main()"+nl+"{"+nl);
        if(arithmeticStr.contains("integers")||arithmeticStr.contains("integer"))
			bw.write("int result;"+nl);
        else if(arithmeticStr.contains("floating point")||arithmeticStr.contains("float"))
			bw.write("float result;"+nl);
        else if(arithmeticStr.contains("double")||arithmeticStr.contains("real"))
			bw.write("double result;"+nl);
        bw.flush();
		input(arithmeticStr);
	    int varId=id;
		String numberStr="";
		Matcher matcher = Pattern.compile("\\d+").matcher(arithmeticStr);  	//matches the numbers from subString
		while(matcher.find()) 												//loop till there are numbers in subString
		{
    		numberStr=matcher.group();	
		}
		int numStr=Integer.parseInt(numberStr);
		varId=varId-numStr;
		System.out.println("VarId:"+varId); 
		bw.write("result=");
		for(int i=1;i<=numStr;i++)
		{
			if(arithmeticStr.contains("integers")||arithmeticStr.contains("integer"))
			{
				if(arithmeticStr.contains("add")||arithmeticStr.contains("sum"))
				{
					if(i!=numStr)
					{
						bw.write("intvar"+varId+"+");
					}
					else
						bw.write("intvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("subtract")||arithmeticStr.contains("difference"))
				{
					if(i!=numStr)
					{
						bw.write("intvar"+varId+"-");
					}
					else
						bw.write("intvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("product")||arithmeticStr.contains("multiply"))
				{
					if(i!=numStr)
					{
						bw.write("intvar"+varId+"*");
					}
					else
						bw.write("intvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("divide")||arithmeticStr.contains("quotient"))
				{
					if(i!=numStr)
					{
						bw.write("intvar"+varId+"/");
					}
					else
						bw.write("intvar"+varId+";"+nl);
				}
			}
			else if(arithmeticStr.contains("float")||arithmeticStr.contains("floating point"))
			{
				if(arithmeticStr.contains("add")||arithmeticStr.contains("sum"))
				{
					if(i!=numStr)
					{
						bw.write("floatvar"+varId+"+");
					}
					else
						bw.write("floatvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("subtract")||arithmeticStr.contains("difference"))
				{
					if(i!=numStr)
					{
						bw.write("floatvar"+varId+"-");
					}
					else
						bw.write("floatvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("product")||arithmeticStr.contains("multiply"))
				{
					if(i!=numStr)
					{
						bw.write("floatvar"+varId+"*");
					}
					else
						bw.write("floatvar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("divide")||arithmeticStr.contains("quotient"))
				{
					if(i!=numStr)
					{
						bw.write("floatvar"+varId+"/");
					}
					else
						bw.write("floatvar"+varId+";"+nl);
				}
			}
			else if(arithmeticStr.contains("double")||arithmeticStr.contains("real"))
			{
				if(arithmeticStr.contains("add")||arithmeticStr.contains("sum"))
				{
					if(i!=numStr)
					{
						bw.write("doublevar"+varId+"+");
					}
					else
						bw.write("doublevar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("subtract")||arithmeticStr.contains("difference"))
				{
					if(i!=numStr)
					{
						bw.write("doublevar"+varId+"-");
					}
					else
						bw.write("doublevar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("product")||arithmeticStr.contains("multiply"))
				{
					if(i!=numStr)
					{
						bw.write("doublevar"+varId+"*");
					}
					else
						bw.write("doublevar"+varId+";"+nl);
				}
				else if(arithmeticStr.contains("divide")||arithmeticStr.contains("quotient"))
				{
					if(i!=numStr)
					{
						bw.write("doublevar"+varId+"/");
					}
					else
						bw.write("doublevar"+varId+";"+nl);
				}
			}
			varId++;
		}
		if(arithmeticStr.contains("return"))
		{
			bw.write("return result;"+nl);
		}
		else if(!arithmeticStr.contains("return"))
		{
			if(arithmeticStr.contains("integers")||arithmeticStr.contains("integer"))
				bw.write("printf(\"%d\",result);"+nl);
			if(arithmeticStr.contains("float"))
				bw.write("printf(\"%f\",result);"+nl);
			if(arithmeticStr.contains("double")||arithmeticStr.contains("real"))
				bw.write("printf(\"%lf\",result);"+nl);
		}
		bw.write("return 0;"+nl+"}");
		bw.close();
		if(arithmeticStr.contains("display")||arithmeticStr.contains("print")||arithmeticStr.contains("output"))
		{
			display();
		}
		
	}
	public void structureBuilder(String structString) throws Exception
	{
		String structName="",structParam="",structNum="";
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        bw.write("#include<stdio.h>"+nl);
        Matcher matcher = Pattern.compile("[a-zA-Z]+ (has|contains|possess|part of)").matcher(structString);  
        while(matcher.find())
        {
        	structName=matcher.group();
        }
        structName=structName.replaceAll("(has|contains|possess|part of)","");
        bw.write("struct "+structName+nl+"{"+nl);
        System.out.println("struct "+structName+nl+"{"+nl);
        matcher = Pattern.compile("(\\d+\\.\\d+|\\d+|has [a-zA-Z]+) [a-zA-Z]+").matcher(structString);
        while(matcher.find())
        {
        	structParam=matcher.group();
        	//System.out.println("structParam"+structParam);
        	structParam=structParam.replaceAll("has ","");
        	System.out.println("structParam"+structParam);
        	Matcher matcher1=Pattern.compile("\\d+\\.\\d+|\\d+|[a-zA-Z]+ [a-zA-Z]+").matcher(structParam);
        	while(matcher1.find())
        	{
        		structNum=matcher1.group();
        		System.out.println(structNum);
        	}
        	System.out.println(nl+structNum+nl);
        	System.out.println(nl+structParam+nl);
        	if(structNum.contains("."))
    		{
    			structParam=structParam.replaceAll("\\d+\\.\\d+","");
        		bw.write("float "+structParam+"="+structNum+";"+nl);
        	}
        	else if((structNum.charAt(0)>='a'&& structNum.charAt(0)<='z') ||(structNum.charAt(0)>='A'&& structNum.charAt(0)<='Z') )
        	{
        		

        		 String[] arr = structNum.split(" ");    

        		 for ( String ss : arr) {

        		       System.out.println(ss);
        		  }
        	}
        	else 
    		{
    			structParam=structParam.replaceAll("\\d+","");
    			bw.write("int" +structParam+"="+structNum+";"+nl);
    		}	
    	    
    	}
        bw.write("};"+nl);
        bw.close();
        display();
    }
	public void macroBuilder(String macroString) throws Exception
	{
		System.out.println("macroString:"+ macroString);
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        bw.write("#include<stdio.h>"+nl);
        String macroName="",macroArgs="",macroConstant="",constName="";
        if(macroString.contains("value")||macroString.contains("constant"))
        {
        	if(macroString.contains("called")||macroString.contains("named"))
        	{
        		Matcher matcher=Pattern.compile("(called|named) [a-zA-Z0-9]+").matcher(macroString);
        		while(matcher.find())
        		{
        			constName=matcher.group();
        		}
        		constName=constName.replaceAll("called","");
    			constName=constName.replaceAll("named","");
    			matcher=Pattern.compile("\\d+").matcher(macroString);
            	while(matcher.find())
            	{
            		macroConstant=matcher.group();
            	}
            	bw.write("# define "+constName+" "+macroConstant+nl);
        	}
        	else
        	{
        		Matcher matcher=Pattern.compile("\\d+").matcher(macroString);
        		while(matcher.find())
        		{
        			macroConstant=matcher.group();
        			bw.write("# define CONST"+macroId+" "+macroConstant+nl);
        			macroId++;
        		}
        	}
        }
        if(macroString.contains("arguments")||macroString.contains("parameters"))
        {
	        Matcher matcher = Pattern.compile("(called|named) [a-zA-Z]+").matcher(macroString);  								//matches the function name from funcString
			while(matcher.find()) 																								//loop till match occurs in funcString
			{
	    		macroName=matcher.group();	
			}
			macroName=macroName.replaceAll("(called|named)","");
			System.out.println(macroName);
			matcher = Pattern.compile("\\d+").matcher(macroString);  															//matches the no and type of arguments
			while(matcher.find()) 																								//loop till match occurs in funcString
			{
	    		macroArgs=matcher.group();	
			}
			int argNumber=Integer.parseInt(macroArgs);
			macroArgs="";
			for(int i=1;i<=argNumber;i++)
			{
				if(i!=argNumber)
					macroArgs=macroArgs+"arg"+i+",";
				else
					macroArgs=macroArgs+"arg"+i;
			}
			bw.write("# define "+ macroName+"("+macroArgs+")"+"  "+"\\"+nl+"{"+"  "+"\\"+nl);
			/*bw.flush();
			matcher=Pattern.compile("(performs|does|computes|to) \\w+").matcher(macroString);
			while(matcher.find())
			{
				macroBody=matcher.group();
			}
			System.out.println("fn:"+macroBody);
			if(macroBody.contains("add")||macroBody.contains("sum")||macroBody.contains("subtract")||macroBody.contains("difference")||macroBody.contains("multiply")||macroBody.contains("product")||macroBody.contains("quotient")||macroBody.contains("divide"))
			{
				arithmeticOperations(argNumber+macroBody);
			}
			if(macroBody.contains("sort"))
			{
				sort(macroBody);
			}*/
			bw.write("}"+"\t"+"\\"+nl);
        }
		bw.close();
		display();
	}
	public void functionBuilder(String funcString) throws IOException
	{
		int l=0;
		File file = new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String nl=System.getProperty("line.separator");
        bw.write("#include<stdio.h>"+nl);
		String type="integer|int|float|char|double|string|void";
		String functionName="",argumentStr[]=null,returnTypeStr="";
		funcString.replaceAll(","," ");
		Matcher matcher = Pattern.compile("(called|named) [a-zA-Z]+").matcher(funcString);  								//matches the function name from funcString
		while(matcher.find()) 																								//loop till match occurs in funcString
		{
    		functionName=matcher.group();	
		}
		//System.out.println(functionName);
		matcher = Pattern.compile("returns [a-zA-Z]+ ("+type+")|return type ("+type+")").matcher(funcString);  				//matches the return type
		while(matcher.find()) 																								//loop till match occurs in funcString
		{
    		returnTypeStr=matcher.group();	
		}
		//System.out.println(returnTypeStr);
		matcher = Pattern.compile("\\d+ ("+type+") arguments|\\d+ ("+type+")|\\d+\\s+arguments of type ("+type+")").matcher(funcString);  	//matches the no and type of arguments
		argumentStr=new String[20];
		while(matcher.find()) 																								//loop till match occurs in funcString
		{
    		argumentStr[l]=matcher.group();
    		if(!argumentStr[l].contains("arguments"))
    			argumentStr[l]=argumentStr[l]+" arguments";
    		l++;
		}
		//System.out.println(argumentStr);
		functionName=functionName.replaceAll("(called|named|\\s+)","");
		returnTypeStr=returnTypeStr.replaceAll("returns [a-zA-Z]|return type|\\s+","");
		if(returnTypeStr.contains("integer"))
			returnTypeStr=returnTypeStr.replace("integer","int");
	//	numberStr=new String[10];
		//for(int i=0;i<l;i++)
	//	{
	//		numberStr[i]=argumentStr[i].replaceAll("arguments","inputs");
	//	}
	//	bw.write(functionName+"();"+nl+"}"+nl);
		bw.write(returnTypeStr+" "+functionName+"(");
		bw.flush();
		//System.out.println(returnTypeStr);
		for(int i=0;i<l;i++)
		{
			input(argumentStr[i]);
			if(argumentStr[i+1]!=null)
			{
				System.out.println(argumentStr[i+1]);
				bw.write(",");
			}
		}
		bw.write(")"+nl+"{"+nl);
	/*	matcher=Pattern.compile("(performs|does|computes|to) \\w+").matcher(funcString);
		while(matcher.find())
		{
			functionBodyStr=matcher.group();
		}
		System.out.println("fn:"+functionBodyStr);
		if(functionBodyStr.contains("add")||functionBodyStr.contains("sum")||functionBodyStr.contains("subtract")||functionBodyStr.contains("difference")||functionBodyStr.contains("multiply")||functionBodyStr.contains("product")||functionBodyStr.contains("quotient")||functionBodyStr.contains("divide"))
		{
			if(returnTypeStr.contains("void"))
				arithmeticOperations(numberStr+functionBodyStr);
			else
				arithmeticOperations(numberStr+functionBodyStr+" return");
		}
		if(functionBodyStr.contains("sort"))
		{
			sort(functionBodyStr);
		}*/
		bw.write("}"+nl);
		bw.close();
		display();
	}
	public void sort(String subString)
	{
		JFrame f= new JFrame("SortFrame");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(350,80);
        f.setLocationByPlatform(true);
        f.setVisible(true);
        jcb.setSize(100,50);
        f.setLayout(new FlowLayout());
        JLabel selectSort=new JLabel();
        selectSort.setText("Choose a Sorting Algorithm:");
        f.add(selectSort);
        f.add(jcb);
		jcb.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				try
				{
					BufferedWriter output = new BufferedWriter(new FileWriter(new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt")));
					output.flush();
					output.close();
				}
				catch(IOException ex)
				{
					ex.getMessage();
				}
				String s = (String) jcb.getSelectedItem();
				if(s.equals("Insertion"))
				{	
					choice=1; 
				}
				else if(s.equals("Bubble"))
				{	
					choice=2;
				}
				else if(s.equals("Heap"))
				{
					choice=3;
				}
				else if(s.equals("Merge"))
				{	 
					choice=4;
				}
				else if(s.equals("Quick"))
				{ 
					choice=5;
				}
				else
				{
					choice=6; 
				}
				finish();									//Function call to generate code for the corresponding sort
				display();									//Display code
			}//end of actionPerformed			
		});	//end of actionListener
}	//end of sort
		
	        		
	/**
	 * Create the frame.
	 */
	public EnterSentence() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 400);
    //    headers=readFile("F:/CompilerProject/EnglishToC/src/etoc/headers.txt"); 
//		System.out.print(headers);
		//output.write(headers);
	//	final String ls = System.getProperty("line.separator");
	//	output.write("void main()"+ls+"{"+ls);
	//	output.close();
		//declarations=readFile("F:/CompilerProject/EnglishToC/src/etocvar_declarations.txt"); 
		//output.write(declarations); 
		//output.write(ls);
		System.out.println("Declarations has been written to op file"); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		JLabel label=new JLabel();
		label.setText("Enter your English Sentence");
		contentPane.add(label);
		sentence=new JTextField(50);
		sentence.setSize(200, 200);
		contentPane.add(sentence);
		translate= new JButton("Translate");
		contentPane.add(translate);
		jcb=new JComboBox<Object>(sortAlgos);
		translate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) 
			{	
				try
				{
					BufferedWriter output = new BufferedWriter(new FileWriter(new File("F:/CompilerProject/EnglishToC/src/etoc/output.txt")));
					output.flush();
					output.close();
				}
				catch(IOException ex)
				{
					ex.getMessage();
				}
				scanSentence=sentence.getText();						//Getting The English Sentence from the TextBox
				try
				{
					MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");	//Initializing the POS Tagger
					tagged = tagger.tagString(scanSentence);		
			    }
				catch(Exception ex)
				{
				}
				tagged=tagged.replaceAll("[a-zA-Z]*/DT","");					//Removing unnecessary words from the Sentence
				tagged=tagged.replaceAll("[a-zA-Z]*/IN","");					//and retaining the keywords required to generate code
				tagged=tagged.replaceAll("/CD","");
				tagged=tagged.replaceAll("/JJ","");
				tagged=tagged.replaceAll("/NNS","");
				tagged=tagged.replaceAll("/NN","");
				tagged=tagged.replaceAll("/VBP","");
				tagged=tagged.replaceAll("/VBN","");
				tagged=tagged.replaceAll("/VBZ","");
				tagged=tagged.replaceAll("/VBG","");
				tagged=tagged.replaceAll("/VB","");
				tagged=tagged.replaceAll("/RB","");
				if(tagged.contains("function")||tagged.contains("macro"))
					tagged=tagged.replaceAll("/CC","");
				else
					tagged=tagged.replaceAll("[a-zA-Z]*/CC","");
					
				
				tagged=tagged.replaceAll("[a-zA-Z]*/PRP","");
				tagged=tagged.replaceAll("[a-zA-Z]*/PRPS","");
				tagged=tagged.replaceAll("[a-zA-Z]*/UH","");
				tagged=tagged.replaceAll("[a-zA-Z]*/WDT","");
				tagged=tagged.replaceAll("[a-zA-Z]*/WP","");
				tagged=tagged.replaceAll("[a-zA-Z]*/WP$","");
				tagged=tagged.replaceAll("[a-zA-Z]*/WRB","");
				tagged=tagged.replaceAll("[a-zA-Z]*/EX","");
				tagged=tagged.replaceAll("[a-zA-Z]*/FW","");
				tagged=tagged.replaceAll("[a-zA-Z]*/MD","");
				tagged=tagged.replaceAll("[a-zA-Z]*/RP","");
				
				System.out.println(tagged);
				processSentence=tagged.split(",");
				System.out.println(processSentence);
				for(String subString:processSentence)
				{
					if(subString.contains("sort")||subString.contains("arrange"))
					{
						if(subString.contains("function"))
								;
						sort(subString);
					}
					if(subString.contains("has")||subString.contains("contains")||subString.contains("part of")||subString.contains("possess"))
					{
						try
						{
							structureBuilder(subString);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}	
					}
					if(subString.contains("macro"))
					{
						try 
						{
							macroBuilder(subString);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					if(subString.contains("display")||subString.contains("print")||subString.contains("show")||subString.contains("output"))
					{
					}
					if(subString.contains("input")||subString.contains("scan")||subString.contains("get")||subString.contains("enter"))
					{
						//if(subString.matches("(\\d{5})"))
						try 
						{
							input(subString);
						}
						catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(subString.contains("function"))
					{
						try 
						{
							functionBuilder(subString);
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					if(subString.contains("add")||subString.contains("sum")||subString.contains("subtract")||subString.contains("difference")||subString.contains("multiply")||subString.contains("product")||subString.contains("quotient")||subString.contains("divide"))
					{
						if(subString.contains("function"))
							;
						else
						{
							try 
							{
								arithmeticOperations(subString);
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
						}
					}
					
				}//end of for each loop
			}	//end of actionPerformed
		});//end of actionListener
	}
}