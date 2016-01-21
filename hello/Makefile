JLIBS = $$CLASSPATH

ANTLR4 = java -cp "$(JLIBS)" org.antlr.v4.Tool

all: Hello.class

run: Hello.class
	java -cp "$(JLIBS)" Hello test.txt

HelloParser.java: Hello.g4
	$(ANTLR4) Hello.g4

Hello.class: HelloParser.java Hello.java HelloWalker.java
	javac -cp "$(JLIBS)" Hello*.java

clean:
	rm -f *.class *.tokens HelloLexer.java HelloParser.java HelloListener.java HelloBaseListener.java
