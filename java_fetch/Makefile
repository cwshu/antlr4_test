JLIBS = $$CLASSPATH
BIN = bin

ANTLR4 = java -cp "$(JLIBS)" org.antlr.v4.Tool

all: $(BIN)/FetchSymbolTool.class

run: $(BIN)/FetchSymbolTool.class
	java -cp "$(JLIBS):$(BIN)" FetchSymbolTool test_code/hello.java

JavaParser.java: Java.g4
	$(ANTLR4) Java.g4

$(BIN)/FetchSymbolTool.class: FetchSymbolTool.java JavaParser.java 
	javac -cp "$(JLIBS)" -d $(BIN) *.java

clean:
	rm -f $(BIN)/*.class *.tokens JavaLexer.java JavaParser.java JavaListener.java JavaBaseListener.java
