package checks;

import io.github.frieder.sonar.java.checks.NullableReturnCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class NullableReturnCheckTest {
    NullableReturnCheck NRC = new NullableReturnCheck();
    private static final String PATH = "src/test/files/checks/nullness/";

    @Test
    public void test() {
        JavaCheckVerifier.verify(PATH + "NullableReturn.java", NRC);
    }

}
