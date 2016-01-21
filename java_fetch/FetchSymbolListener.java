import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;

public class FetchSymbolListener extends JavaBaseListener {
    JavaParser parser;
    public FetchSymbolListener(JavaParser parser) { this.parser = parser; }

    // classDeclaration : 'class' Identifier typeParameters? ('extends' type)? ('implements' typeList)? classBody ;
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx){
        System.out.println("Class: "+ctx.Identifier());
        // System.out.println("interface I"+ctx.Identifier()+" {");
    }

    // importDeclaration : 'import' 'static'? qualifiedName ('.' '*')? ';'
       // qualifiedName : Identifier ('.' Identifier)*
    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx){
        List<TerminalNode> importNameSequence = ctx.qualifiedName().Identifier();
        System.out.print("ClassImport: ");
        for (TerminalNode nameNode : importNameSequence) {
            String name = nameNode.getText();
            System.out.print(name+".");
        }
        System.out.print("\n");
    }

    // TODO: constructor

    // methodDeclaration : (type|'void') Identifier formalParameters ('[' ']')* 
    //                     ('throws' qualifiedNameList)? ( methodBody | ';')
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx){
        TokenStream tokens = parser.getTokenStream();

        // 1. function return type
        // constructor use void return type
        String retType = "void";
        if ( ctx.type() != null ) {
            retType = tokens.getText(ctx.type());
        }

        // 2. function name
        String funcName = ctx.Identifier().getText();

        // 3. arguments type
            // formalParameters : '(' formalParameterList? ')'
            // formalParameterList : formalParameter (',' formalParameter)* (',' lastFormalParameter)? 
            //                     | lastFormalParameter
            // formalParameter : variableModifier* type variableDeclaratorId
            // lastFormalParameter : variableModifier* type '...' variableDeclaratorId
        ArrayList<String> funcArgs = new ArrayList<String>();
        JavaParser.FormalParameterListContext paraList = ctx.formalParameters().formalParameterList();

        if (paraList != null) {
            List<JavaParser.FormalParameterContext> parameterCtxList = paraList.formalParameter();

            for (JavaParser.FormalParameterContext parameterCtx : parameterCtxList) {
                String type = tokens.getText(parameterCtx.type());
                funcArgs.add(type);
            }

            if (paraList.lastFormalParameter() != null) {
                String type = tokens.getText(paraList.lastFormalParameter().type());
                funcArgs.add(type+"...");
            }
        }

        System.out.print("ClassFunc: "+funcName+":"+retType+":");
        for (String arg : funcArgs) {
            System.out.print(arg+",");
        }
        System.out.print('\n');
    }

    // fieldDeclaration: type variableDeclarators ';'
    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx){
        TokenStream tokens = parser.getTokenStream();

        // 1. base variable type
        String varType = tokens.getText(ctx.type());
        
        // 2. each variables
        // TODO: variable array type 
        //       (maybe we need modify parser(ANTLR Action?), because array info doesn't exist in AST)
        ArrayList<String> varNameList = new ArrayList<String>();

        List<JavaParser.VariableDeclaratorContext> varDeclCtxList = ctx.variableDeclarators().variableDeclarator();
        for (JavaParser.VariableDeclaratorContext varDeclCtx : varDeclCtxList) {
            String varName = varDeclCtx.variableDeclaratorId().Identifier().getText();
            varNameList.add(varName);
        }

        for (String varName : varNameList) {
            System.out.println("ClassVar: "+varType+":"+varName);
        }
    }
}
