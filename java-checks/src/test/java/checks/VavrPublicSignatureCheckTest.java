package checks;

import io.github.frieder.sonar.java.checks.VavrPublicSignatureCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class VavrPublicSignatureCheckTest {
    private static final String PATH = "src/test/files/checks/vavr/";

    @Test
    public void test() {
        JavaCheckVerifier.verify(PATH + "VavrInterface.java", new VavrPublicSignatureCheck());
        JavaCheckVerifier.verify(PATH + "VavrAbstractClass.java", new VavrPublicSignatureCheck());
        JavaCheckVerifier.verifyNoIssue(PATH + "VavrRegularClass.java", new VavrPublicSignatureCheck());
    }

}
