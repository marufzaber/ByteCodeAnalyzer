package bytecodeanalyzer;

import java.io.IOException;
import java.util.Set;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

/**
 * To compile this project, cd to the directory of this project and run -- 
 * mvn -q compile exec:java -Dexec.args=<class file path>
 *
 */

public class Analyzer {
	private String classFileName;
	
	// Array of all the methods. Method is a class provided by 
	// Apache BCEL library, see Java Doc.
	private Method[] methods;
	
	// Set of methods that has reflection on it. 
	private Set<String> methodsWithReflection;
	
	// Set of methods that has file IO on it.
	private Set<String> methodsWithFileIo;

	public Analyzer(String classFileName) {
		try {
			// The name of the class file is passed as command line argument
			this.classFileName = classFileName;
			ClassParser parser = new ClassParser(this.classFileName);	
			JavaClass parsedClass = parser.parse();
			
			// Get all the methods and classes
			this.methods = parsedClass.getMethods();
		} catch (ClassFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement this method. Just detect if relevant method call for
	 * reflection occurs inside {@link code} string. Read details about 
	 * Java reflection - 
	 * https://www.geeksforgeeks.org/reflection-in-java/
	 * https://www.journaldev.com/1789/java-reflection-example-tutorial
	 * https://www.baeldung.com/java-reflection
	 * @param method
	 * @return
	 */
	private boolean containsReflection(Method method) {
		// Gets the byte code of the method.
		String code = method.getCode().toString();
		return false;
	}
	
	/**
	 * Implement this method. Just detect if relevant method call for
	 * reflection occurs inside {@link code} string. Read 
	 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
	 * @param method. 
	 * @return
	 */
	private boolean containsFileIO(Method method) {
		// Gets the byte code of the method.
		String code = method.getCode().toString();
		return false;
	}
	
	public void analyzeFileIO() {
		for (int i = 0; i < methods.length; i++) {
			if (containsFileIO(methods[i])) {
				methodsWithFileIo.add(methods[i].getName());
			}
		}
	}
	
	public void analyzeReflection() {
		for (int i = 0; i < methods.length; i++) {
			if (containsReflection(methods[i])) {
				methodsWithReflection.add(methods[i].getName());
			}
		}
	}
	
	/**
	 * implement this method.
	 */
	public void print() {
		// Write log to a file
	}
	
	public static void main(String[] args) {		
		Analyzer analyzer = new Analyzer(args[0]);
		analyzer.analyzeFileIO();
		analyzer.analyzeReflection();
		analyzer.print();
	}
}
