package checks;

import io.github.frieder.sonar.java.checks.NullnessAnnotationCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class NullnessAnnotationCheckTest {
    private static final NullnessAnnotationCheck NAC = new NullnessAnnotationCheck();
    private static final String PATH = "src/test/files/checks/nullness/";

    @Test
    public void test() {
        JavaCheckVerifier.verify(PATH + "JavaxValidations.java", NAC);
        JavaCheckVerifier.verify(PATH + "JavaxAnnotations.java", NAC);
        JavaCheckVerifier.verify(PATH + "FindbugsAnnotations.java", NAC);
        JavaCheckVerifier.verify(PATH + "JetbrainsAnnotations.java", NAC);
        JavaCheckVerifier.verify(PATH + "EclipseJdtAnnotations.java", NAC);
        JavaCheckVerifier.verify(PATH + "LombokAnnotations.java", NAC);
        JavaCheckVerifier.verifyNoIssue(PATH + "CheckerFwAnnotations.java", NAC);
    }
}
