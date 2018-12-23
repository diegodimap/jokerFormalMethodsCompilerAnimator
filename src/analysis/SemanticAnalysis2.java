package analysis;

import base.Token;
import java.util.ArrayList;
import base.Patterns;

/**
 * This class verifies if the specification is semantically correct.
 * Currently in stand-by in Joker 1.6.
 * Can be used for semantic analysis for the integration of other languages.
 * @author Diego Henrique Oliveira de Souza
 */
public class SemanticAnalysis2 {

    private ArrayList<Token> tokens = new ArrayList<Token>();
    private ArrayList<String> gates = new ArrayList<String>();
    private ArrayList<String> process = new ArrayList<String>();
    private ArrayList<String> processBody = new ArrayList<String>();
    private ArrayList<String> processExitType = new ArrayList<String>();
    private ArrayList<String> gatesBehaviour = new ArrayList<String>();
    private ArrayList<String> processBehaviour = new ArrayList<String>();
    private ArrayList<String> tokensBehaviour = new ArrayList<String>();
    private ArrayList<String> operatorsBehaviour = new ArrayList<String>();
    private ArrayList<String> mensagensSemanticas = new ArrayList<String>();
    private String gatesBehaviorConcatenados = "";
    private String gatesConcatenados = "";
    private String gatesSpecification = "";
    private String nomeEspecificacao = "";
    private boolean isErroSemantico = false;
    private long quantidadeGates = 0;
    private long quantidadeProcessos = 0;

    public SemanticAnalysis2(ArrayList<Token> tokens) {
        this.tokensBehaviour = new ArrayList<String>();
        this.tokens = removeSpaceTokens(tokens);
        this.getProcessAndGates();
        this.isAllGatesDeclared();
        this.isAllProcessDeclared();

        long temp = 0;
        for (String string : gates) {
            String[] partes = string.split(",");
            temp += partes.length;
        }

        quantidadeGates = temp;
        quantidadeProcessos = process.size();
    }

    private void isAllProcessDeclared() {
        for (int i = 0; i < tokensBehaviour.size(); i++) {
            //localiza os operadores dentro de um behaviour
            if(tokensBehaviour.get(i).equalsIgnoreCase(Patterns.enable) ||
               tokensBehaviour.get(i).equalsIgnoreCase(Patterns.disable) ||
               tokensBehaviour.get(i).equalsIgnoreCase(Patterns.choice) ||
               tokensBehaviour.get(i).equalsIgnoreCase(Patterns.interleaving) ||
               tokensBehaviour.get(i).equalsIgnoreCase(Patterns.paralel)){
               operatorsBehaviour.add(tokensBehaviour.get(i));
            }else{
                if(tokensBehaviour.get(i).equalsIgnoreCase("|")){
                    operatorsBehaviour.add(
                            tokensBehaviour.get(i) +
                            tokensBehaviour.get(i+1) +
                            tokensBehaviour.get(i+2) +
                            tokensBehaviour.get(i+3) +
                            tokensBehaviour.get(i+4));

                    //testes
//                    System.out.println("i: " + tokensBehaviour.get(i));
//                    System.out.println("i+1: " + tokensBehaviour.get(i+1));
//                    System.out.println("i+2: " + tokensBehaviour.get(i+2));

                    //verifica se há | fechando e gate de sincronização
//                    System.out.println("i: " + tokensBehaviour.get(i));
//                    System.out.println("i+4: " + tokensBehaviour.get(i+4));
                    if(!tokensBehaviour.get(i).equalsIgnoreCase(tokensBehaviour.get(i+4))){
                        this.isErroSemantico = true;
                        mensagensSemanticas.add("ERROR: There is no closing parallel '|' and no " +
                                "sincronization IDENTIFICATION");
                    }

                    //pula o próximo '|'
                    i += 5;
                }
            }

            if (tokensBehaviour.get(i).equals("[")) {
                processBehaviour.add(tokensBehaviour.get(i - 1));
                gatesBehaviorConcatenados = "";
                while (!tokensBehaviour.get(i).equals("]")) {
                    gatesBehaviorConcatenados += tokensBehaviour.get(i);
                    i++;
                }
                gatesBehaviour.add(gatesBehaviorConcatenados.trim());
            }
        }

        //remoçao do [ no início da string
        for (int i = 0; i < gatesBehaviour.size(); i++) {
            gatesBehaviour.set(i, gatesBehaviour.get(i).substring(1));
        }

        //adiociona mensagens contendo os processos do behaviour e seus gates
        for (String proc : processBehaviour) {
            mensagensSemanticas.add("PROCESS IN BEHAVIOR: " + proc);
            mensagensSemanticas.add("PROCESS' GATES: " +
                    gatesBehaviour.get(processBehaviour.indexOf(proc)));
        }

        //verifica se há processos utilizados no behaviour e não declarados
        //no where
        for (String proc : process) {
            if (!processBehaviour.contains(proc)) {
                mensagensSemanticas.add("ERROR: Process " + proc +
                        " is used but is not declared in 'where'!");
            }
        }

        //verifica se há processos declarados no where e não utilizados no
        //behaviour
        for (String proc : processBehaviour) {
            if (!proc.contains(proc)) {
                mensagensSemanticas.add("ERROR: Process " + proc +
                      " is declared in 'where' but is not used in behaviour!");
            }
        }

        //verifica se há processos com nomes iguais
        for (int i = 0; i < process.size(); i++) {
            for (int j = i+1; j < process.size(); j++) {
                if(process.get(j).equals(process.get(i))){
                    mensagensSemanticas.add("ERROR: There are process with the " +
                            "same name: " + process.get(i));
                }
            }
        }
    }

    private void isAllGatesDeclared() {
        mensagensSemanticas.add("SPECIFICATION NAME: " + nomeEspecificacao +
                "\nSPECIFICATION GATES: " + gatesSpecification + "\n");

        String[] gatesDeclared = new String[1];
        String[] gatesProcessos = new String[1];
        String allGates = "";

        //separando gates da especificação em um array
        if (gatesSpecification.contains(",")) {
            gatesDeclared = gatesSpecification.split(",");
        } else if (gatesSpecification.equals("")) {
            isErroSemantico = true;
            mensagensSemanticas.add("ERROR: There is no declared gates!");
        } else {
            gatesDeclared[0] = gatesSpecification;
        }

        //concatenando todos os gates separados por ','
        for (String g : gates) {
            allGates += "," + g;
        }

        //retirando a primeira vírgula
        if(allGates.contains(","))
        allGates = allGates.substring(1);

        //separando gates dos processos em um array
        if (allGates.contains(",")) {
            gatesProcessos = allGates.split(",");
        } else if (allGates.equals("")) {
            isErroSemantico = true;
            mensagensSemanticas.add("ERROR: There is no gates in process!");
        } else {
            gatesProcessos[0] = allGates;
        }

        mensagensSemanticas.add("ALL GATES IN THIS SPECIFICATION PROCESS: " +
                allGates + "\n");

        ArrayList<String> gs = new ArrayList<String>();
        ArrayList<String> gp = new ArrayList<String>();

        for (int i = 0; i < gatesDeclared.length; i++) {
            gs.add(gatesDeclared[i]);
        }

        for (int i = 0; i < gatesProcessos.length; i++) {
            gp.add(gatesProcessos[i]);
        }

        //verifica se todos os gates usados foram declarados
        for (String string : gp) {
            if (!gs.contains(string)) {
                mensagensSemanticas.add("ERROR: The gate " + string +
                        " is used but is not declared.");
                isErroSemantico = true;
            }
        }

        //verifica se todos os gates declarados são utilizados
        for (String string : gs) {
            if (!gp.contains(string)) {
                mensagensSemanticas.add("ERROR: The gate " + string +
                        " is declared but is not used.");
                isErroSemantico = true;
            }
        }
    }

    public void getProcessAndGates() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getConteudo().equals("specification")) {
                this.nomeEspecificacao = tokens.get(i + 1).getConteudo();
                if (tokens.get(i + 2).getConteudo().equals("[")) {
                    int l = i + 3;
                    gatesSpecification = "";
                    while (!tokens.get(l).getConteudo().equals("]")) {
                        gatesSpecification += tokens.get(l).getConteudo();
                        l++;
                    }
                }
            }

            if (tokens.get(i).getConteudo().equals("behaviour")) {
                while (!tokens.get(i).getConteudo().equals("where")) {
                    tokensBehaviour.add(tokens.get(i).getConteudo());
                    i++;
                }
            }

            if (tokens.get(i).getConteudo().equals("process")) {
                if (tokens.get(i + 2).getConteudo().equals("[")) {
                    process.add(tokens.get(i + 1).getConteudo());
                    int j = i + 3;
                    gatesConcatenados = "";
                    while (!tokens.get(j).getConteudo().equals("]")) {
                        gatesConcatenados += tokens.get(j).getConteudo();
                        j++;
                    }
                    gates.add(gatesConcatenados.trim());
                    j+=4;
                    
                    if (tokens.get(j-1).getConteudo().equals(":=")) {
                        processExitType.add(tokens.get(j-2).getConteudo());
                        String teste = "";
                        while (!tokens.get(j).getConteudo().equals("endproc")) {
                            teste += (tokens.get(j).getConteudo());
                            j++;
                        }
                        processBody.add(teste);
                    }
                }
            }
        }
    }

    private ArrayList<Token> removeSpaceTokens(
            ArrayList<Token> tokensTabulacoes) {
        ArrayList<Token> tokensSemTabulacaoEComentarios =
                new ArrayList<Token>();
        for (Token token : tokensTabulacoes) {
            if ((!token.getConteudo().trim().equals("")) &&
                    (!token.getTipo().startsWith("COMMENT"))) {
                tokensSemTabulacaoEComentarios.add(token);
            }
        }
        return tokensSemTabulacaoEComentarios;
    }

    public ArrayList<String> getGates() {
        return gates;
    }

    public ArrayList<String> getProcessos() {
        return process;
    }

    public String getNomeEspecificacao() {
        return nomeEspecificacao;
    }

    public boolean isIsErroSemantico() {
        return isErroSemantico;
    }

    public ArrayList<String> getMensagensSemanticas() {
        return mensagensSemanticas;
    }

    public ArrayList<String> getGatesBehaviour() {
        return gatesBehaviour;
    }

    public ArrayList<String> getProcessosBehaviour() {
        return processBehaviour;
    }

    public ArrayList<String> getProcessBody() {
        return processBody;
    }

    public ArrayList<String> getOperatorsBehaviour() {
        return operatorsBehaviour;
    }

    public ArrayList<String> getProcessExitType() {
        return processExitType;
    }

    public long getQuantidadeGates() {
        return quantidadeGates;
    }

    public long getQuantidadeProcessos() {
        return quantidadeProcessos;
    }
}
