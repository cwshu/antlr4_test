import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;

public class FetchSymbolListener extends JavaBaseListener {
    public FetchSymbolListener() {}

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

        if (ctx.getChildCount() == 5) {
            // count 5 means import with .*
            System.out.print("*");
        }
        System.out.print("\n");
    }

    // TODO: full method/field support for ClassCon/Func/Var
    // memberDeclaration
    //     :   methodDeclaration
    //     |   genericMethodDeclaration: TODO
    //     |   fieldDeclaration
    //     |   constructorDeclaration: TODO
    //     |   genericConstructorDeclaration: TODO
    
    // constructorDeclaration : Identifier formalParameters 
    //                          ('throws' qualifiedNameList)? constructorBody

    // methodDeclaration : (type|'void') Identifier formalParameters ('[' ']')* 
    //                     ('throws' qualifiedNameList)? ( methodBody | ';')
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx){
        // 1. function return type
        // constructor use void return type
        String retType = "void";
        if ( ctx.type() != null ) {
            retType = ctx.type().getText();
        }

        // 2. function name
        String funcName = ctx.Identifier().getText();

        // 3. arguments type
        ArrayList<String> funcArgsType = parseFormalParametersType(ctx.formalParameters());

        System.out.print("ClassFunc: "+funcName+":"+retType+":");
        for (String arg : funcArgsType) {
            System.out.print(arg+",");
        }
        System.out.print('\n');
    }

    ArrayList<String> parseFormalParametersType(JavaParser.FormalParametersContext formalParametersCtx) {
        // formalParameters : '(' formalParameterList? ')'
        // formalParameterList : formalParameter (',' formalParameter)* (',' lastFormalParameter)? 
        //                     | lastFormalParameter
        // formalParameter : variableModifier* type variableDeclaratorId
        // lastFormalParameter : variableModifier* type '...' variableDeclaratorId
        ArrayList<String> paraTypeList = new ArrayList<String>();
        JavaParser.FormalParameterListContext paraListCtx = formalParametersCtx.formalParameterList();

        if (paraListCtx != null) {
            List<JavaParser.FormalParameterContext> parameterCtxList = paraListCtx.formalParameter();

            for (JavaParser.FormalParameterContext parameterCtx : parameterCtxList) {
                String type = parameterCtx.type().getText();
                paraTypeList.add(type);
            }

            if (paraListCtx.lastFormalParameter() != null) {
                String type = paraListCtx.lastFormalParameter().type().getText();
                paraTypeList.add(type+"...");
            }
        }
        return paraTypeList;
    }

    int getVariableSuffixArrayDimension(JavaParser.VariableDeclaratorIdContext varDeclIdCtx){
        // variableDeclaratorId : Identifier ('[' ']')*
        return (varDeclIdCtx.getChildCount() - 1) / 2;
    }

    // fieldDeclaration: type variableDeclarators ';'
    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx){
        // 1. base variable type
        String varType = ctx.type().getText();
        
        // 2. each variables
        // 3. variable array dimension. e.g. int a[][]
        ArrayList<String> varNameList = new ArrayList<String>();
        ArrayList<Integer> varArrayDimensionList = new ArrayList<Integer>();

        List<JavaParser.VariableDeclaratorContext> varDeclCtxList = ctx.variableDeclarators().variableDeclarator();
        for (JavaParser.VariableDeclaratorContext varDeclCtx : varDeclCtxList) {
            String varName = varDeclCtx.variableDeclaratorId().Identifier().getText();
            // variableDeclaratorId : Identifier ('[' ']')*
            Integer arrayDimension = new Integer( getVariableSuffixArrayDimension(varDeclCtx.variableDeclaratorId()) );

            varNameList.add(varName);
            varArrayDimensionList.add(arrayDimension);
        }

        for (int i=0; i<varNameList.size(); i++) {
            String varName = varNameList.get(i);
            Integer arrayDimension = varArrayDimensionList.get(i);
            System.out.print("ClassVar: "+varType);
            for (int j=0; j<arrayDimension; j++) {
                System.out.print("[]");
            }
            System.out.println(":"+varName);
        }
    }
}
