package analysis;

import base.Patterns;
import base.Token;
import java.util.ArrayList;

/**
 * This class have the responsibility of verify if the words in a specification
 * are written in the correct order.
 * Currently in stand-by in Joker 1.6.
 * Can be used for syntactic analysis for the integration of other languages.
 * @author Diego Henrique Oliveira de Souza
 */
public class SyntacticAnalysis2 {

    private ArrayList<Token> tokensConteudo = new ArrayList<Token>();
    private ArrayList<String> mensagensSintaticas = new ArrayList<String>();
    private boolean isErroSintatico = false;
    private int i = 0;

    /**
     * This constructor receives a LOTOS specification code in an array of
     * tokens form. This array is returned by the lexical analysis.
     * @param tokensLexica is the array of tokens
     */
    public SyntacticAnalysis2(ArrayList<Token> tokensLexica) {
        this.tokensConteudo = this.removerTabulacoes(tokensLexica);
        this.iniciarDescidaRecursiva();
    }

    /**
     * This method returns a token in the position received as parameter
     * @param pos is the position of the wished token
     * @return the token in this position
     */
    public Token getToken(int pos) {
        return tokensConteudo.get(pos);
    }

    /**
     * This method increments a field that indicates the actual position
     * of the wished token.
     */
    public void nextToken() {
        i++;
    }

    /**
     * This method receives the array of tokens representing the LOTOS
     * specification code and removes all the TAB, ENTER, SPACE and COMMENTS.
     * @param tokensTabulacoes the complete array
     * @return the configured array
     */
    private ArrayList<Token> removerTabulacoes(
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

    /**
     * This method verifies if an error happened in the syntactic analysis.
     * If yes, it configures the field isErroSintatico as TRUE.
     */
    private void verificarErrosSintaticos() {
        for (String msg : mensagensSintaticas) {
            if (msg.startsWith("ERROR:")) {
                this.isErroSintatico = true;
            }
        }
    }

    /**
     * Returns true if happened an error in this analysis fase, and false if not.
     * @return a boolean indicating an error or not
     */
    public boolean isIsErroSintatico() {
        return this.isErroSintatico;
    }

    /**
     * This method returns an array containing the messages of this fase.
     * @return an array of syntactic messages
     */
    public ArrayList<String> getMensagensSintaticas() {
        return mensagensSintaticas;
    }

    /**
     * This method starts the syntactic analysis
     */
    public void iniciarDescidaRecursiva() {
        this.programaLotos();
    }

    /**
     * This method calls especificacao(), after verifies the errors and
     * append a message ----LOTOS PROGRAM WITH GOOD SINTAX---- if the code
     * has no errors.
     */
    private void programaLotos() {
        //chama o primeiro bloco de uma especificação lotos
        this.especificacao();

        //verifica se houve erro sintático
        this.verificarErrosSintaticos();
        if (!this.isErroSintatico) {
            mensagensSintaticas.add("----LOTOS PROGRAM WITH GOOD SINTAX----");
        }
    }

    /**
     * This method verifies the first line of a LOTOS specification CODE.
     * It must contains a keyword specification a specification name,
     * a list of gates used in this specification and a exit type.
     */
    private void especificacao() {
        if (getToken(i).getConteudo().equalsIgnoreCase("specification")) {
            mensagensSintaticas.add("READ: specification");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                mensagensSintaticas.add("READ: identificador");
                nextToken();
                this.listaGates();
                if (getToken(i).getTipo().equalsIgnoreCase(Patterns.doisPontos)) {
                    mensagensSintaticas.add("READ: ':'");
                    nextToken();
                    this.saida();
                    //fim especificação, chama o behaviour
                    this.behaviour();
                } else {
                    mensagensSintaticas.add("ERROR: expected a ':' on line " +
                            getToken(i).getLinha());
                }
            } else {
                mensagensSintaticas.add("ERROR: expected a 'identificador' " +
                        "on line " + getToken(i).getLinha());
            }
        } else {
            mensagensSintaticas.add("ERROR: expected 'specification' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies if the behaviour is writen in the correct order.
     * The correct order is a process, an operator and another process.
     * This combination may repeat many times.
     */
    private void behaviour() {
        if (getToken(i).getConteudo().equalsIgnoreCase("behaviour")) {
            mensagensSintaticas.add("READ: 'behaviour'");
            nextToken();
            //dentro do behaviour há processos
            this.processo();
            //entre um processo e outro há uma operação
            this.operacao();
            //depois do behaviour vem o where
            this.where();
        } else {
            mensagensSintaticas.add("ERROR: expected 'behaviour' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies if the where block is writen correctly.
     * The correct way is an keyword 'where', a process declaration one or more
     * times, and after a end of the specifiation.
     */
    private void where() {
        if (getToken(i).getConteudo().equalsIgnoreCase("where")) {
            mensagensSintaticas.add("READ: 'where'");
            nextToken();
            this.declaracoesProcessos();
            //depois do where vem o fim da especificacao
            this.fimEspecificacao();
        } else {
            mensagensSintaticas.add("ERROR: expected 'where' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies if a process is written correctly.
     * The correct way is the keyword 'process' and a list of gates.
     */
    private void processo() {
        if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
            mensagensSintaticas.add("READ: 'identificador'");
            nextToken();
            this.listaGates();
        } else {
            mensagensSintaticas.add("ERROR: expected 'identificador' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies the operations. It must be on form: id operator id
     */
    private void operacao() {
        if (getToken(i).getTipo().equalsIgnoreCase(Patterns.operador)) {
            mensagensSintaticas.add("READ: 'operador'");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                mensagensSintaticas.add("READ: 'identificador'");
                nextToken();
                //verifica se há gate de sincronização
                if (getToken(i).getTipo().equalsIgnoreCase(Patterns.operador)) {
                    operacao();
                } else {
                    this.listaGates();
                    this.operacao();
                }
            } else {
                if (getToken(i).getConteudo().equals("[")) {
                    nextToken();
                    if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                        nextToken();
                        if (getToken(i).getConteudo().equals("]")) {
                            nextToken();
                            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.operador)) {
                                operacao();
                            }else{
                                this.listaGates();
                                this.operacao();
                            }
                        } else {
                            // ]
                            mensagensSintaticas.add("ERROR: expected a ']' " +
                            "on line " + getToken(i).getLinha());
                        }
                    } else {
                        //id
                        mensagensSintaticas.add("ERROR: expected a 'IDENTIFICATION' " +
                            "on line " + getToken(i).getLinha());
                    }
                } else {
                    //[
                    mensagensSintaticas.add("ERROR: expected a '[' " +
                            "on line " + getToken(i).getLinha());
                }
            }
        } else {
            if (getToken(i).getConteudo().equals("where")) {
                //DO NOTHING
            } else {
                mensagensSintaticas.add("ERROR: expected a 'operador' " +
                        "on line " + getToken(i).getLinha());
            }
        }
    }

    /**
     * This method verifies a list of gates. It is in the form: [gate1, gate2,
     * ...]
     */
    private void listaGates() {
        if (getToken(i).getConteudo().equalsIgnoreCase("[")) {
            mensagensSintaticas.add("READ: '['");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                mensagensSintaticas.add("READ: 'identificador'");
                nextToken();
                //lê todos os gates separados por vírgula
                this.gates();
                if (getToken(i).getConteudo().equalsIgnoreCase("]")) {
                    mensagensSintaticas.add("READ: ']'");
                    nextToken();
                } else {
                    mensagensSintaticas.add("ERROR: expected a ']' on line " +
                            getToken(i).getLinha());
                }
            } else {
                mensagensSintaticas.add("ERROR: expected a 'identificador' " +
                        "on line " + getToken(i).getLinha());
            }
        } else {
            mensagensSintaticas.add("ERROR: expected a '[' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies many gates, separated by comma.
     */
    private void gates() {
        if (getToken(i).getTipo().equalsIgnoreCase(Patterns.virgula)) {
            mensagensSintaticas.add("READ: ','");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                mensagensSintaticas.add("READ: 'identificador'");
                nextToken();
                this.gates();
            } else {
                mensagensSintaticas.add("ERROR: expected a 'identificador' " +
                        "on line " + getToken(i).getLinha());
            }
        } else {
            if (getToken(i).getConteudo().equals("]")) {
                //DO NOTHING
            } else {
                mensagensSintaticas.add("ERROR: expected a ',' on line " +
                        getToken(i).getLinha());
            }
        }
    }

    /**
     * This method verifies an exit type
     */
    private void saida() {
        if (getToken(i).getTipo().equalsIgnoreCase(Patterns.saida)) {
            mensagensSintaticas.add("READ: 'exit type'");
            nextToken();
        } else {
            mensagensSintaticas.add("ERROR: expected an 'exit type' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verrifies a process declaration.
     * A process has a name, a list of gates, an exit type and a body.
     */
    private void declaracoesProcessos() {
        if (getToken(i).getConteudo().equalsIgnoreCase("process")) {
            mensagensSintaticas.add("READ: 'process'");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
                mensagensSintaticas.add("READ: 'identificador'");
                nextToken();
                this.listaGates();
                if (getToken(i).getConteudo().equalsIgnoreCase(":")) {
                    mensagensSintaticas.add("READ: ':'");
                    nextToken();
                    this.saida();
                    this.fim();
                    this.corpoProcesso();
                    //this.saida();
                    //this.fimProcesso();
                    this.declaracoesProcessos();
                } else {
                    mensagensSintaticas.add("ERROR: expected a ':' on line " +
                            getToken(i).getLinha());
                }
            } else {
                mensagensSintaticas.add("ERROR: expected a 'identificador' " +
                        "on line " + getToken(i).getLinha());
            }
        } else {
            if (getToken(i).getConteudo().equals("endspec")) {
                //DO NOTHING
            } else {
                mensagensSintaticas.add("ERROR: expected a 'process' on line " +
                        getToken(i).getLinha());
            }
        }
    }

    /**
     * This method verifies a body of a process. The body can have operations
     * or gates only.
     */
    private void corpoProcesso() {
        if (getToken(i).getTipo().equalsIgnoreCase(Patterns.identificador)) {
            mensagensSintaticas.add("READ: 'identificador'");
            nextToken();
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.pontoEvirgula)) {
                mensagensSintaticas.add("READ: ';'");
                nextToken();
                this.corpoProcesso();
            } else {
                mensagensSintaticas.add("ERROR: expected a ';' on line " +
                        getToken(i).getLinha());
            }
        } else {
            if (getToken(i).getTipo().equalsIgnoreCase(Patterns.operador)) {
                mensagensSintaticas.add("READ: operator " +
                        getToken(i).getConteudo());
                nextToken();
                this.corpoProcesso();
            } else {
                if (getToken(i).getTipo().equals(Patterns.saida)) {
                    mensagensSintaticas.add("READ: exit type = " +
                            getToken(i).getConteudo());
                    nextToken();
                    this.corpoProcesso();
                } else {
                    fimProcesso();
                }
            }
        }
    }

    /**
     * This method verifies if before the exit type is present the atribution
     * operator.
     */
    private void fim() {
        if (getToken(i).getConteudo().equalsIgnoreCase(":=")) {
            mensagensSintaticas.add("READ: ':='");
            nextToken();
        } else {
            mensagensSintaticas.add("ERROR: expected a ':=' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies the end of the process by the keyword 'endproc'
     */
    private void fimProcesso() {
        if (getToken(i).getConteudo().equalsIgnoreCase("endproc")) {
            mensagensSintaticas.add("READ: 'endproc'");
            nextToken();
        } else {
            mensagensSintaticas.add("ERROR: expected a 'endproc' on line " +
                    getToken(i).getLinha());
        }
    }

    /**
     * This method verifies the end of the specification by the keyword 'endspec'
     */
    private void fimEspecificacao() {
        if (getToken(i).getConteudo().equalsIgnoreCase("endspec")) {
            mensagensSintaticas.add("READ: 'endspec'");
            nextToken();
        } else {
            mensagensSintaticas.add("ERROR: expected a 'endspec' on line " +
                    getToken(i).getLinha());
        }
    }
}

