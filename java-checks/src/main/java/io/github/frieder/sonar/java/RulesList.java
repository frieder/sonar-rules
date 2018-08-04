package io.github.frieder.sonar.java;

import com.google.common.collect.ImmutableList;
import io.github.frieder.sonar.java.checks.NullableReturnCheck;
import io.github.frieder.sonar.java.checks.NullnessAnnotationCheck;
import io.github.frieder.sonar.java.checks.VavrPublicSignatureCheck;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.List;

public class RulesList {
    private RulesList() {
    }

    public static List<Class> getChecks() {
        return ImmutableList.<Class>builder()
                .addAll(getJavaChecks())
                .addAll(getJavaTestChecks())
                .build();
    }

    static List<Class<? extends JavaCheck>> getJavaChecks() {
        return ImmutableList.<Class<? extends JavaCheck>>builder()
                .add(NullnessAnnotationCheck.class)
                .add(NullableReturnCheck.class)
                .add(VavrPublicSignatureCheck.class)
                .build();
    }

    static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return ImmutableList.of();
    }
}