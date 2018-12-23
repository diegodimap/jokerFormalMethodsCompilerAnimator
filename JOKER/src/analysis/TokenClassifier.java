package analysis;

import base.Patterns;
import java.util.ArrayList;
import base.Token;

/**
 * This class has the responsibility of recognizing all the characters in a LOTOS
 * specification code. This class also puts these characters together forming words.
 * These words are classified in types, according to the selected language.
 * @author Diego Henrique Oliveira de Souza
 */
public class TokenClassifier {

    private String codigo;
    private ArrayList<Token> tokens = null;
    private ArrayList<String> palavrasChave = null;
    private ArrayList<String> ends = null;
    private ArrayList<String> numeros = null;
    private ArrayList<String> operadores = null;
    private ArrayList<String> igual = null;
    private ArrayList<String> parenteses = null;
    private ArrayList<String> seq = null;
    private ArrayList<String> key = null;
    private ArrayList<String> espaco = null;
    private ArrayList<String> enter = null;
    private ArrayList<String> tabulacao = null;
    private ArrayList<String> simbolosInvalidos = null;
    private ArrayList<String> mensagensLexicas = null;
    private boolean isErroLexico = false;

    /**
     * This constructor receives a specification code, initializes all
     * the arrays, and put the contents of the arrays.
     * @param codigo is the code itself
     */
    public TokenClassifier(String codigo) {
        this.codigo = codigo;
        mensagensLexicas = new ArrayList<String>();

        //initializing arrays
        palavrasChave = new ArrayList<String>();
        igual = new ArrayList<String>();
        ends = new ArrayList<String>();
        numeros = new ArrayList<String>();
        operadores = new ArrayList<String>();
        parenteses = new ArrayList<String>();

        igual = new ArrayList<String>();
        parenteses = new ArrayList<String>();
        seq = new ArrayList<String>();
        key = new ArrayList<String>();
        espaco = new ArrayList<String>();
        enter = new ArrayList<String>();
        tabulacao = new ArrayList<String>();
        simbolosInvalidos = new ArrayList<String>();

        //adding the keywords

        //CSP
        palavrasChave.add("START");
        palavrasChave.add("SYSTEM");
        palavrasChave.add("channel");
        palavrasChave.add("datatype");

        //B
        palavrasChave.add("MAIN");
        palavrasChave.add("MACHINE");
        palavrasChave.add("SETS");
        palavrasChave.add("CONSTANTS");
        palavrasChave.add("PROPERTIES");
        palavrasChave.add("VARIABLES");
        palavrasChave.add("INVARIANT");
        palavrasChave.add("INITIALISATION");
        palavrasChave.add("OPERATIONS");
        palavrasChave.add("EXTENDS");
        palavrasChave.add("INCLUDES");
        palavrasChave.add("PROMOTES");
//        palavrasChave.add("PRE");
//        palavrasChave.add("IF");
//        palavrasChave.add("THEN");
//        palavrasChave.add("ELSE");
//        palavrasChave.add("ELSIF");
//        palavrasChave.add("END");
        palavrasChave.add("NAT");
        palavrasChave.add("NAT1");
        palavrasChave.add("TRUE");
        palavrasChave.add("FALSE");
        palavrasChave.add("ANY");
        palavrasChave.add("WHERE");

        //LATEX for Z
        palavrasChave.add("documentclass");
        palavrasChave.add("usepackage");
        palavrasChave.add("fuzz");
        palavrasChave.add("document");
        palavrasChave.add("a4paper");
        palavrasChave.add("fuzz");
        palavrasChave.add("article");
        palavrasChave.add("section");
        palavrasChave.add("subsection");
        palavrasChave.add("schema");
        palavrasChave.add("Init");
        palavrasChave.add("Data");
        palavrasChave.add("types");
        palavrasChave.add("begin");
        palavrasChave.add("end");
        palavrasChave.add("Proc");
        palavrasChave.add("axdef");
        palavrasChave.add("zed");


        palavrasChave.add("langle");
        palavrasChave.add("rangle");
        palavrasChave.add("lbag");
        palavrasChave.add("rbag");
        palavrasChave.add("ldata");
        palavrasChave.add("rdata");
        palavrasChave.add("rimg");
        palavrasChave.add("limg");

//        palavrasChave.add("in");
//        palavrasChave.add("notin");
//        palavrasChave.add("dom");
//        palavrasChave.add("ran");
//        palavrasChave.add("part");
//        palavrasChave.add("nat");
//        palavrasChave.add("cup");
//        palavrasChave.add("bij");
//        palavrasChave.add("new");
//        palavrasChave.add("rel");
//        palavrasChave.add("leq");
//        palavrasChave.add("inv");
//        palavrasChave.add("rres");
//        palavrasChave.add("ndres");
//        palavrasChave.add("pfun");
//        palavrasChave.add("oplus");
//        palavrasChave.add("mapsto");

        palavrasChave.add("proc");
        palavrasChave.add("defs");
        palavrasChave.add("emptyset");
        palavrasChave.add("State");
        //palavrasChave.add("Delta");
        palavrasChave.add("ProcBase");
        palavrasChave.add("ProcOperation");

        //LOTOS
        palavrasChave.add("specification");
        palavrasChave.add("where");
        palavrasChave.add("behaviour");
        palavrasChave.add("process");
        palavrasChave.add("endproc");
        palavrasChave.add("endspec");

        //adding the exit types ROXO
        //CSP
        ends.add("SKIP");
        ends.add("STOP");

        //LOTOS
        ends.add("exit");
        ends.add("noexit");

        //B
        ends.add("PRE");
        ends.add("IF");
        ends.add("THEN");
        ends.add("ELSE");
        ends.add("ELSIF");
        ends.add("END");
        ends.add("SELECT");

        //Z
        ends.add("Delta");
        ends.add("in");
        ends.add("notin");
        ends.add("dom");
        ends.add("ran");
        ends.add("part");
        ends.add("nat");
        ends.add("cup");
        ends.add("bij");
        ends.add("new");
        ends.add("rel");
        ends.add("leq");
        ends.add("inv");
        ends.add("rres");
        ends.add("ndres");
        ends.add("pfun");
        ends.add("oplus");
        ends.add("mapsto");
        ends.add("exists");
        ends.add("power");
        ends.add("lambda");
        ends.add("cross");
        ends.add("mu");
        ends.add("mid");
        ends.add("spot");
        ends.add("theta");
        ends.add("Xi");
        ends.add("lnot");
        ends.add("land");
        ends.add("lor");
        ends.add("iplies");
        ends.add("iff");
        ends.add("forall");
        ends.add("exists_1");
        ends.add("hide");
        ends.add("project");
        ends.add("pre");
        ends.add("semi");
        ends.add("empty");
        ends.add("bigcup");
        ends.add("bigcap");
        ends.add("num");
        ends.add("nat_1");
        ends.add("#");
        ends.add("dcat");
        ends.add("upto");
        ends.add("setminus");
        ends.add("cat");
        ends.add("uplus");
        ends.add("div");
        ends.add("mod");
        ends.add("mod");
        ends.add("cap");
        ends.add("comp");
        ends.add("circ");
        ends.add("filter");
        ends.add("dres");
        ends.add("rres");
        ends.add("ndres");
        ends.add("nrres");
        ends.add("notin");
        ends.add("subseteq");
        ends.add("subset");
        ends.add("leq");
        ends.add("geq");
        ends.add("partition");
        ends.add("inbag");
        ends.add("rel");
        ends.add("pfun");
        ends.add("fun");
        ends.add("pinj");
        ends.add("inj");
        ends.add("psurj");
        ends.add("surj");
        ends.add("bij");
        ends.add("ffun");
        ends.add("finj");
        ends.add("power_1");
        ends.add("id");
        ends.add("finset");
        ends.add("finset_1");
        ends.add("seq");
        ends.add("seq_1");
        ends.add("iseq");
        ends.add("bag");

        //adding the equals symbol
        igual.add("=");

        //adding valid symbols
//        numeros.add("0");
//        numeros.add("1");
//        numeros.add("2");
//        numeros.add("3");
//        numeros.add("4");
//        numeros.add("5");
//        numeros.add("6");
//        numeros.add("7");
//        numeros.add("8");
//        numeros.add("9");

        //adding two-points
        //parenteses.add(")");
        //parenteses.add("(");

        //adding comma
        seq.add("--");

        //adding semicolon
        key.add("GP_HashMD");

        //adding space
        espaco.add(" ");

        //adding the ENTER
        enter.add("\n");

        //adding the TAB character
        tabulacao.add("\t");

        //adding the operators
        operadores.add("?");
        operadores.add("!");
        operadores.add("@");
        operadores.add("&");
        operadores.add(">");
        operadores.add("<");
        operadores.add(":=");
        operadores.add("[]");
        operadores.add("|||");
        operadores.add("||");
        operadores.add(";");
        operadores.add("==");
        operadores.add("+");
        operadores.add("-");
        operadores.add("*");
        operadores.add("\\");
        operadores.add("|");
        operadores.add("/");
        operadores.add("%");
        operadores.add("?");
        operadores.add("!");
        operadores.add("->");
        operadores.add("}");
        operadores.add("{");
        operadores.add("(");
        operadores.add(")");
        operadores.add(":");
        operadores.add(";");
        operadores.add("]");
        operadores.add("[");
        operadores.add("=");
        operadores.add(",");
        operadores.add(".");
        operadores.add("~");
        operadores.add("\'");

        //adding the invalid symbols
        //simbolosInvalidos.add("$");
        simbolosInvalidos.add("£");
        simbolosInvalidos.add("¢");
        simbolosInvalidos.add("¨");
        simbolosInvalidos.add("¬");
        simbolosInvalidos.add("&");
        simbolosInvalidos.add("^");
        simbolosInvalidos.add("´");
        simbolosInvalidos.add("`");
        simbolosInvalidos.add("\"");
        simbolosInvalidos.add("à");
        simbolosInvalidos.add("á");
        simbolosInvalidos.add("ã");
        simbolosInvalidos.add("â");
        simbolosInvalidos.add("õ");
        simbolosInvalidos.add("ô");
        simbolosInvalidos.add("ò");
        simbolosInvalidos.add("ó");
        simbolosInvalidos.add("ê");
        simbolosInvalidos.add("é");
        simbolosInvalidos.add("é");
        simbolosInvalidos.add("î");
        simbolosInvalidos.add("ì");
        simbolosInvalidos.add("í");
        simbolosInvalidos.add("û");
        simbolosInvalidos.add("ú");
        simbolosInvalidos.add("ù");

        //calls the method which read the code by caracter
        this.lerCodigoSequencialmente();
    }

    /**
     * This method reads the code character by character, separating the words
     * and symbols.
     */
    public void lerCodigoSequencialmente() {
        tokens = new ArrayList<Token>();
        int linha = 1;

        String palavra = "";
        int aspas = 0;
        int comentario = 0;

        for (int i = 0; i < codigo.length(); i++) {
            if (codigo.charAt(i) == '\'') {
                if (codigo.charAt(i + 1) == '\'') {
                    aspas = 1;
                }
            }
            if (codigo.charAt(i) == '\n') {
                aspas = 0;
            }
            if (codigo.charAt(i) == '(') {
                if (codigo.charAt(i + 1) == '*') {
                    comentario = 1;
                }
            }
            if (codigo.charAt(i) == '*') {
                if (codigo.charAt(i + 1) == ')') {
                    comentario = 0;
                }
            }
            if ((aspas == 1) || (comentario == 1)) {
                if ((codigo.charAt(i) == '\n') && (comentario == 1)) {
                    tokens.add(
                            new Token(
                            i - palavra.length() + 1,
                            i - 1,
                            linha++,
                            palavra,
                            this.verificarTipo(palavra.trim(),
                            linha)));
                    palavra = "";
                } else if ((codigo.charAt(i) == '\n') && (comentario == 0)) {
                    tokens.add(
                            new Token(i - palavra.length() + 1,
                            i - 1,
                            linha++,
                            palavra,
                            this.verificarTipo(palavra.trim(),
                            linha)));
                    palavra = "";
                } else {
                    palavra = palavra + codigo.charAt(i);
                }
            } else {
                if ((codigo.charAt(i) == ' ')
                        || (codigo.charAt(i) == '.')
                        || (codigo.charAt(i) == '*')
                        || (codigo.charAt(i) == '/')
                        || (codigo.charAt(i) == '=')
                        || (codigo.charAt(i) == '+')
                        || (codigo.charAt(i) == '-') && (codigo.charAt(i + 1) != '>')
                        || (codigo.charAt(i) == '%')
                        || (codigo.charAt(i) == '~')
                        || (codigo.charAt(i) == '?')
                        || (codigo.charAt(i) == '!')
                        || (codigo.charAt(i) == '}')
                        || (codigo.charAt(i) == '{')
                        || (codigo.charAt(i) == '\\')
                        || (codigo.charAt(i) == '\n')
                        || (codigo.charAt(i) == '\t')
                        || (codigo.charAt(i) == ':')
                        && (codigo.charAt(i + 1) != ':')
                        || (codigo.charAt(i) == ';')
                        || (codigo.charAt(i) == ',')
                        || (codigo.charAt(i) == '[')
                        || (codigo.charAt(i) == '\'')
                        || (codigo.charAt(i) == ']')
                        || ((codigo.charAt(i) == '|')
                        && (codigo.charAt(i - 1) != '|')
                        && (codigo.charAt(i + 1) != '|'))
                        || (codigo.charAt(i) == '(')
                        && (codigo.charAt(i + 1) != '*')
                        || (codigo.charAt(i) == ')')
                        && (codigo.charAt(i - 1) != '*')) {

                    //mount the TOKEN if the word has a content
                    if (!palavra.equalsIgnoreCase("")) {
                        tokens.add(
                                new Token(
                                i - palavra.length() + 1,
                                i,
                                linha,
                                palavra,
                                this.verificarTipo(palavra, linha)));
                    }
                    //after commes the symbols
                    if (((codigo.charAt(i) == ':')
                            && (codigo.charAt(i + 1) == '='))
                            || ((codigo.charAt(i) == '[')
                            && (codigo.charAt(i + 1) == '>'))
                            || ((codigo.charAt(i) == '[')
                            && (codigo.charAt(i + 1) == ']'))) {

                        String conteudo = codigo.charAt(i) + ""
                                + codigo.charAt(i + 1);
                        i++;
                        tokens.add(
                                new Token(
                                i - conteudo.length() + 1,
                                i,
                                linha,
                                conteudo,
                                this.verificarTipo(conteudo, linha)));
                    } else {
                        String conteudo = codigo.charAt(i) + "";
                        tokens.add(
                                new Token(
                                i - conteudo.length() + 1,
                                i + 1,
                                linha,
                                conteudo,
                                this.verificarTipo(conteudo, linha)));
                    }
                    //incremments the line number if there is an ENTER
                    if ((codigo.charAt(i) == '\n')) {
                        linha++;
                    }
                    palavra = "";
                } else {
                    palavra = palavra + codigo.charAt(i);
                }
            }
        }
    }

    /**
     * This method gives a type to a received word.
     * @param conteudo is the word received
     * @param linha is the line of the received word
     * @return the type of the word
     */
    public String verificarTipo(String conteudo, int linha) {
        if (conteudo.startsWith("(*")) {
            return "COMMENT-IN-BLOCK";
        } else {
            if (conteudo.startsWith("''")) {
                return "COMMENT-IN-LINE";
            } else if (palavrasChave.contains(conteudo)) {
                return Patterns.palavraChave;
            } else if (operadores.contains(conteudo)) {
                return Patterns.operador;
            } else if (numeros.contains(conteudo)) {
                return Patterns.simbolo;
            } else if (ends.contains(conteudo)) {
                return Patterns.saida;
            } else if (enter.contains(conteudo)) {
                return Patterns.enter;
            } else if (espaco.contains(conteudo)) {
                return Patterns.espaco;
            } else if (parenteses.contains(conteudo)) {
                return Patterns.doisPontos;
            } else if (key.contains(conteudo)) {
                return Patterns.pontoEvirgula;
            } else if (seq.contains(conteudo)) {
                return Patterns.virgula;
            } else if (igual.contains(conteudo)) {
                return Patterns.atribuicao;
            } else if (simbolosInvalidos.contains(conteudo)) {
                isErroLexico = true;
                mensagensLexicas.add("ERROR: It's a symbol\nToken: "
                        + conteudo + " Line: " + linha);
                return "ERROR: It's a symbol";
            } else if (this.isCaracteresInvalidos(conteudo)) {
                isErroLexico = true;
                mensagensLexicas.add("ERROR: It contains symbol\nToken: "
                        + conteudo + " Line: " + linha);
                return "ERROR: It contains symbol";
            } else {
                return Patterns.identificador;
            }
        }
    }

    /**
     * This method verifies if the word is a valid word.
     * @param conteudo is the word received
     * @return true if the word is valid, and false if not
     */
    public boolean isCaracteresInvalidos(String conteudo) {
        for (String simbol : simbolosInvalidos) {
            if (conteudo.contains(simbol)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean identifying or not an error in lexical analysis
     * @return true if there is error in lexical analysis, and false if not
     */
    public boolean isIsErroLexico() {
        return isErroLexico;
    }

    /**
     * Return an array containing the messages of this analysis
     * @return the array of lexical messages
     */
    public ArrayList<String> getMensagensLexicas() {
        return mensagensLexicas;
    }

    /**
     * This method returns an array of Tokens.
     * A Token is an object that contains a word, the line and the type of this
     * word. It also contains the position of the word inside the code.
     * @return an array of tokens representing the code
     */
    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
