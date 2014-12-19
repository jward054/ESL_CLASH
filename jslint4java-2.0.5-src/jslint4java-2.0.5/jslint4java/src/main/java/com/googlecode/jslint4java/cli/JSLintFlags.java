package com.googlecode.jslint4java.cli;

import com.beust.jcommander.Parameter;

/**
 * Flags which correspond directly to JSLint options.
 */
class JSLintFlags {
    // BEGIN-OPTIONS
    @Parameter(names = "--ass", description = "If assignment expressions should be allowed")
    public Boolean ASS = null;

    @Parameter(names = "--bitwise", description = "If bitwise operators should be allowed")
    public Boolean BITWISE = null;

    @Parameter(names = "--browser", description = "If the standard browser globals should be predefined")
    public Boolean BROWSER = null;

    @Parameter(names = "--closure", description = "If google closure idioms should be tolerated")
    public Boolean CLOSURE = null;

    @Parameter(names = "--continue", description = "If the continuation statement should be tolerated")
    public Boolean CONTINUE = null;

    @Parameter(names = "--debug", description = "If debugger statements should be allowed")
    public Boolean DEBUG = null;

    @Parameter(names = "--devel", description = "If logging should be allowed (console, alert, etc.)")
    public Boolean DEVEL = null;

    @Parameter(names = "--eqeq", description = "If == should be allowed")
    public Boolean EQEQ = null;

    @Parameter(names = "--es5", description = "If es5 syntax should be allowed")
    public Boolean ES5 = null;

    @Parameter(names = "--evil", description = "If eval should be allowed")
    public Boolean EVIL = null;

    @Parameter(names = "--forin", description = "If for in statements need not filter")
    public Boolean FORIN = null;

    @Parameter(names = "--indent", description = "The indentation factor")
    public String INDENT = null;

    @Parameter(names = "--maxerr", description = "The maximum number of errors to allow")
    public String MAXERR = null;

    @Parameter(names = "--maxlen", description = "The maximum length of a source line")
    public String MAXLEN = null;

    @Parameter(names = "--newcap", description = "If constructor names capitalization is ignored")
    public Boolean NEWCAP = null;

    @Parameter(names = "--node", description = "If node.js globals should be predefined")
    public Boolean NODE = null;

    @Parameter(names = "--nomen", description = "If names may have dangling _")
    public Boolean NOMEN = null;

    @Parameter(names = "--passfail", description = "If the scan should stop on first error")
    public Boolean PASSFAIL = null;

    @Parameter(names = "--plusplus", description = "If increment/decrement should be allowed")
    public Boolean PLUSPLUS = null;

    @Parameter(names = "--predef", description = "The names of predefined global variables")
    public String PREDEF = null;

    @Parameter(names = "--properties", description = "If all property names must be declared with /*properties*/")
    public Boolean PROPERTIES = null;

    @Parameter(names = "--regexp", description = "If the . should be allowed in regexp literals")
    public Boolean REGEXP = null;

    @Parameter(names = "--rhino", description = "If the rhino environment globals should be predefined")
    public Boolean RHINO = null;

    @Parameter(names = "--sloppy", description = "If the 'use strict'; pragma is optional")
    public Boolean SLOPPY = null;

    @Parameter(names = "--stupid", description = "If really stupid practices are tolerated")
    public Boolean STUPID = null;

    @Parameter(names = "--sub", description = "If all forms of subscript notation are tolerated")
    public Boolean SUB = null;

    @Parameter(names = "--todo", description = "If todo comments are tolerated")
    public Boolean TODO = null;

    @Parameter(names = "--unparam", description = "If unused parameters should be tolerated")
    public Boolean UNPARAM = null;

    @Parameter(names = "--vars", description = "If multiple var statements per function should be allowed")
    public Boolean VARS = null;

    @Parameter(names = "--warnings", description = "Unused")
    public Boolean WARNINGS = null;

    @Parameter(names = "--white", description = "If sloppy whitespace is tolerated")
    public Boolean WHITE = null;

    @Parameter(names = "--windows", description = "If ms windows-specific globals should be predefined")
    public Boolean WINDOWS = null;

    // END-OPTIONS
}
