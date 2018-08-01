package io.github.frieder.sonar.java;

import com.google.common.collect.Iterables;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.api.utils.AnnotationUtils;
import org.sonar.squidbridge.annotations.RuleTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * Server extension.
 * Gets instantiated during SonarQube startup. Defines repository name and supported language.
 */
public class CustomRuleDefinition implements RulesDefinition {
    static final String REPO_KEY = "Custom SonarQube Java Rules";
    private static final String RESOURCE_BASE_PATH = "/io/github/frieder/sonar/java/rules";
    private final Gson gson = new Gson();

    @Override
    public void define(Context context) {
        NewRepository repo = context.createRepository(REPO_KEY, "java").setName(REPO_KEY);
        List<Class> checks = RulesList.getChecks();
        new RulesDefinitionAnnotationLoader().load(repo, Iterables.toArray(checks, Class.class));

        checks.forEach(ruleClass -> newRule(ruleClass, repo));
        repo.done();
    }

    private void newRule(Class<?> ruleClass, NewRepository repo) {
        org.sonar.check.Rule ruleAnnotation = AnnotationUtils.getAnnotation(ruleClass, org.sonar.check.Rule.class);
        if (ruleAnnotation == null) {
            throw new IllegalArgumentException("No Rule annotation was found on " + ruleClass);
        }
        String ruleKey = ruleAnnotation.key();
        if (StringUtils.isEmpty(ruleKey)) {
            throw new IllegalArgumentException("No key is defined in Rule annotation of " + ruleClass);
        }
        NewRule rule = repo.rule(ruleKey);
        if (rule == null) {
            throw new IllegalStateException("No rule was created for " + ruleClass + " in " + repo.key());
        }
        ruleMetadata(rule);
        rule.setTemplate(AnnotationUtils.getAnnotation(ruleClass, RuleTemplate.class) != null);
    }

    private void ruleMetadata(NewRule rule) {
        String metadataKey = rule.key();
        addHtmlDescription(rule, metadataKey);
        addMetadata(rule, metadataKey);
    }

    private void addMetadata(NewRule rule, String metadataKey) {
        URL resource = CustomRuleDefinition.class.getResource(RESOURCE_BASE_PATH + "/" + metadataKey + "_java.json");
        if (resource != null) {
            RuleMetadata metatada = gson.fromJson(readResource(resource), RuleMetadata.class);
            rule.setSeverity(metatada.defaultSeverity.toUpperCase(Locale.US));
            rule.setName(metatada.title);
            rule.addTags(metatada.tags);
            rule.setType(RuleType.valueOf(metatada.type));
            rule.setStatus(RuleStatus.valueOf(metatada.status.toUpperCase(Locale.US)));
            if (metatada.remediation != null) {
                rule.setDebtRemediationFunction(metatada.remediation.remediationFunction(rule.debtRemediationFunctions()));
                rule.setGapDescription(metatada.remediation.linearDesc);
            }
        }
    }

    private static void addHtmlDescription(NewRule rule, String metadataKey) {
        URL resource = CustomRuleDefinition.class.getResource(RESOURCE_BASE_PATH + "/" + metadataKey + "_java.html");
        if (resource != null) {
            rule.setHtmlDescription(readResource(resource));
        }
    }

    private static String readResource(URL resource) {
        try {
            return Resources.toString(resource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read: " + resource, e);
        }
    }

    private static class RuleMetadata {
        String title;
        String status;
        Remediation remediation;

        String type;
        String[] tags;
        String defaultSeverity;
    }

    private static class Remediation {
        String func;
        String constantCost;
        String linearDesc;
        String linearOffset;
        String linearFactor;

        public DebtRemediationFunction remediationFunction(DebtRemediationFunctions drf) {
            if (func.startsWith("Constant")) {
                return drf.constantPerIssue(constantCost.replace("mn", "min"));
            }
            if ("Linear".equals(func)) {
                return drf.linear(linearFactor.replace("mn", "min"));
            }
            return drf.linearWithOffset(linearFactor.replace("mn", "min"), linearOffset.replace("mn", "min"));
        }
    }
}
