# SonarQube Custom Java Checks

This project provides a few simple checks for Java to demonstrate the 
implementation and use of custom rules in SonarQube.

*  `NullableReturnCheck` - Shows an issue on `INFO` level to the developer 
suggesting to consider using `java.util.Optional` or `io.vavr.control.Option`
instead of a potentially nullable return value. Implementation is done using 
the visitor pattern available in Sonar.
* `NullnessAnnotationCheck` - Forbids the use of any nullness annotations 
besides those of the [Checker Framework](https://checkerframework.org/). The 
rule itself is marked _deprecated_. There is a more generic approach 
available that does not require a custom rule -- 
[rule templates](https://github.com/frieder/sonar-rules/wiki/RuleTemplate). 
* `VavrPublicSignatureCheck` - Forbid the use of [vavr.io](https://www.vavr.io) 
classes in public API. This includes public/protected methods used in 
interfaces and abstract classes. 

Check the [setup page](https://github.com/frieder/sonar-rules/wiki/Setup) to 
get a brief overview of how to install this plugin.

## Known Issues

It seems that Sonar is not able to catch type annotations. Both the 
`NullnessAnnotationCheck` as well as the OOTB template "_Track uses of 
disallowed classes_" are not able to match the use of a forbidden annotation. 
