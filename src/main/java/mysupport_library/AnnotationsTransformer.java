package mysupport_library;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public abstract class AnnotationsTransformer implements IAnnotationTransformer{

	@SuppressWarnings("rawtypes")
	public void transfrom(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		//annotation.setRetryAnalyzer(RetryAnalyzer.class);
		//new code start
		IRetryAnalyzer retry  = annotation.getRetryAnalyzer();
		if(retry == null) {
			annotation.setRetryAnalyzer(IRetryAnalyzer.class);
		}
		//new code end
	}
}
