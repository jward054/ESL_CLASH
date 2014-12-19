Updating JSLint
===============

In order to update to the latest version of JSLint, you need to run a few commands, which will do most of the work.  You will need the following tools installed:

* Ruby
* Curl

This will bring in the latest version directly from jslint.com, then update the Option.java file accordingly.

    $ cd …/jslint4java
    $ jslint4java/etc/update-jslint.sh
    Please update jslint4java-docs now!
    $ mvn test

If you have a version already lying around, you can specify it as a command line parameter to update-jslint.sh.

Assuming all the tests pass, you should then update the documentation.  If any new options have been added, they should be mentioned in:

 * NEWS.txt — mention the version bump
 * jslint4java-docs/src/main/resources/ant.html
 * jslint4java-docs/src/main/resources/cli.html

Finally, commit all changes.
