JLIBS = $$CLASSPATH
BIN = bin

ANTLR4 = java -cp "$(JLIBS)" org.antlr.v4.Tool

all: $(BIN)/ExtractInterfaceTool.class

run: $(BIN)/ExtractInterfaceTool.class
	java -cp "$(JLIBS):$(BIN)" ExtractInterfaceTool test/Demo.java

JavaParser.java: Java.g4
	$(ANTLR4) Java.g4

$(BIN)/ExtractInterfaceTool.class: ExtractInterfaceTool.java JavaParser.java 
	javac -cp "$(JLIBS)" -d $(BIN) *.java

clean:
	rm -f $(BIN)/*.class *.tokens JavaLexer.java JavaParser.java JavaListener.java JavaBaseListener.java
