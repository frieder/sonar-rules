package io.github.frieder.sonar.java;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonarsource.api.sonarlint.SonarLintSide;

import static io.github.frieder.sonar.java.CustomRuleDefinition.REPO_KEY;

/**
 * Provide the "checks" (implementations of rules) classes that are going be executed during
 * source code analysis.
 */
@SonarLintSide
public class CustomCheckRegistrar implements CheckRegistrar {
    @Override
    public void register(RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(REPO_KEY, RulesList.getJavaChecks(), RulesList.getJavaTestChecks());
    }
}
