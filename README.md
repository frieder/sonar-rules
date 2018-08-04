# SonarQube Custom Java Checks

This project provides a few simple checks for Java to demonstrate the implementation and
use of custom rules in SonarQube.

*  `NullableReturnCheck` - Shows an issue on `INFO` level to the developer suggesting
to consider using `java.util.Optional` or `io.vavr.control.Option` instead of a potentially 
nullable return value.
a nullable return value.
* `NullnessAnnotationCheck` - Forbid the use of any nullness annotations besides those
of the [Checker Framework](https://checkerframework.org/). We consider this the best
solution when it comes to nullable annotations at the moment and made it an important 
part of the build process (especially in fighting NPEs). The issue is therefore 
considered a `BLOCKER` in the project.
* `VavrPublicSignatureCheck` - Forbid the use of [vavr.io](https://www.vavr.io) 
collections in public API. We use this library to some extend in internal 
implementations since it provides a few handy features that Java is still lacking.
However we don't want to enforce its use on others using our public APIs. Therefore
we want to keep our interfaces as generic as possible. At the moment we have limited
this check on vavr collections only but might extend it later on to cover more/all 
vavr classes. Currently marked as `MAJOR` so it won't block a release but important 
enough for devs to see it when checking their feature branch analysis. 

Check the [setup page](https://github.com/frieder/sonar-rules/wiki/Setup) to get a 
brief overview of how to install this plugin.

## Known Issues

I was not able to get hold of type annotations in the `NullnessAnnotationCheck` as 
described at [StackOverflow](https://stackoverflow.com/questions/51596453). That was
the main reason why I decided to include the check of the imports as well. If you
have any insights on this a quick reply or pull request is highly appreciated.
 