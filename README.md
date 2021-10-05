# Zalizniak-2010 Waxeye Grammar

This repo hosts a formal grammar of [Zalizniak's Grammatical Dictionary](https://github.com/gramdict/zalizniak-2010) 
in [Waxeye](https://github.com/waxeye-org/waxeye) format. 
The grammar is not finished yet but it already covers a large variety of dictionary entries.

The grammar ([zal2010.waxeye](https://github.com/gramdict/zalizniak-2010-waxeye-grammar/blob/master/zal2010.waxeye)) 
comes with a suite of tests 
([zal2010.waxeye.tests](https://github.com/gramdict/zalizniak-2010-waxeye-grammar/blob/master/zal2010.waxeye.tests)).
A typical test has the following form:

```
"ма́ма жо 1a"
(entry (headword #\м #\а #\́ #\м #\а) (def (main_symbol #\ж #\о) (index #\1 (scheme #\a))
```

The first line is the input and the second line is the expected output in the form of a Racket S-expression.
In the above example we are expecting an (entry) with a (headword) of ма́ма and a definition comprising 
the main_symbol жо and an index of 1a.

The file run_tests.bat contains the command line to run the tests. It needs version 0.8.1 of Waxeye
which has not been released yet but can be built from latest master using these instructions:
[Building from Source](https://github.com/waxeye-org/waxeye#building-from-source). The grammar and tests rely on
[these changes](https://github.com/waxeye-org/waxeye/pull/64) (adding Unicode support) which are not found in version 0.8.0.

## Roadmap

The next logical step would be creating a script that would run the generated parser on each dictionary entry, 
report entries that failed to parse. This will help us finish the grammar.
The script will also write the successfully parsed entries to a file of a popular format
such as XML, JSON or YAML. This would be the end goal of this project, having the dictionary in a format
that can be easily consumed by most programming languages.

## Why Waxeye?

Wikipedia lists [hundreds](https://en.wikipedia.org/wiki/Comparison_of_parser_generators) of parser generators.
Of course, I haven't tried them all.
Before Waxeye I tried ANTLR and I really struggled trying to get it to do what I wanted
(see my StackOverflow questions 
[1](https://stackoverflow.com/a/69410054/1291717) and 
[2](https://stackoverflow.com/q/69415886/1291717)).

With ANTLR, I had to write my own test runner and my development cycle was:

1. Generate the parser.
2. Build the parser and run tests.

With Waxeye's built-in test runner, the development cycle is just one step: click on run_tests.bat. 
The results are available immediately as no intermediate code is generated.

Waxeye also has an [online workbench](https://waxeye-org.github.io/waxeye/demo.html#calc) which is even faster:
the results are available as you type.
I tried it and it simply worked, unlike ANTLR. That's what got me hooked on Waxeye.



