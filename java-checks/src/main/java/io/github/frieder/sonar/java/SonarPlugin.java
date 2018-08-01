package io.github.frieder.sonar.java;

import org.sonar.api.Plugin;

/**
 * Entry point of the Sonar plugin.
 */
public class SonarPlugin implements Plugin {
    @Override
    public void define(Context context) {
        context.addExtension(CustomRuleDefinition.class);
        context.addExtension(CustomCheckRegistrar.class);
    }
}
