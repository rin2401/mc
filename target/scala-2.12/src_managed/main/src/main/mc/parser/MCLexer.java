// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

  package mc.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTLIT=1, FLOATLIT=2, BOOLLIT=3, STRINGLIT=4, BOOLEAN=5, INT=6, FLOAT=7, 
		STRING=8, VOID=9, IF=10, ELSE=11, FOR=12, DO=13, WHILE=14, BREAK=15, CONTINUE=16, 
		RETURN=17, TRUE=18, FALSE=19, ADD=20, SUB=21, MUL=22, DIV=23, MOD=24, 
		AND=25, OR=26, EQUAL=27, NOTEQUAL=28, LE=29, GE=30, NOT=31, LT=32, GT=33, 
		ASSIGN=34, LB=35, RB=36, LP=37, RP=38, LSB=39, RSB=40, SEMI=41, COMMA=42, 
		ID=43, WS=44, BLOCK_COMMENT=45, LINE_COMMENT=46, ILLEGAL_ESCAPE=47, UNCLOSE_STRING=48, 
		ERROR_CHAR=49;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"INTLIT", "FLOATLIT", "EP", "BOOLLIT", "STRINGLIT", "STRINGCHAR", "ESCAPE", 
		"BOOLEAN", "INT", "FLOAT", "STRING", "VOID", "IF", "ELSE", "FOR", "DO", 
		"WHILE", "BREAK", "CONTINUE", "RETURN", "TRUE", "FALSE", "ADD", "SUB", 
		"MUL", "DIV", "MOD", "AND", "OR", "EQUAL", "NOTEQUAL", "LE", "GE", "NOT", 
		"LT", "GT", "ASSIGN", "LB", "RB", "LP", "RP", "LSB", "RSB", "SEMI", "COMMA", 
		"ID", "WS", "BLOCK_COMMENT", "LINE_COMMENT", "ILLEGAL_ESCAPE", "UNCLOSE_STRING", 
		"ERROR_CHAR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'boolean'", "'int'", "'float'", "'string'", 
		"'void'", "'if'", "'else'", "'for'", "'do'", "'while'", "'break'", "'continue'", 
		"'return'", "'true'", "'false'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&&'", 
		"'||'", "'=='", "'!='", "'<='", "'>='", "'!'", "'<'", "'>'", "'='", "'('", 
		"')'", "'{'", "'}'", "'['", "']'", "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "INTLIT", "FLOATLIT", "BOOLLIT", "STRINGLIT", "BOOLEAN", "INT", 
		"FLOAT", "STRING", "VOID", "IF", "ELSE", "FOR", "DO", "WHILE", "BREAK", 
		"CONTINUE", "RETURN", "TRUE", "FALSE", "ADD", "SUB", "MUL", "DIV", "MOD", 
		"AND", "OR", "EQUAL", "NOTEQUAL", "LE", "GE", "NOT", "LT", "GT", "ASSIGN", 
		"LB", "RB", "LP", "RP", "LSB", "RSB", "SEMI", "COMMA", "ID", "WS", "BLOCK_COMMENT", 
		"LINE_COMMENT", "ILLEGAL_ESCAPE", "UNCLOSE_STRING", "ERROR_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	  @Override
	  public Token emit() {
	    switch (getType()) {
	    case UNCLOSE_STRING:       
	      Token result = super.emit();
	      // you'll need to define this method
	      throw new UncloseString(result.getText());
	        
	    case ILLEGAL_ESCAPE:
	      result = super.emit();
	      throw new IllegalEscape(result.getText());
	    
	    case ERROR_CHAR:
	      result = super.emit();
	      throw new ErrorToken(result.getText()); 

	    default:
	      return super.emit();
	    }
	  }


	public MCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 4:
			STRINGLIT_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			ILLEGAL_ESCAPE_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			UNCLOSE_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRINGLIT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			  String s = getText();
			  s = s.substring(1,s.length()-1);
			  setText(s);

			break;
		}
	}
	private void ILLEGAL_ESCAPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			  setText(getText().substring(1));

			break;
		}
	}
	private void UNCLOSE_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

			  setText(getText().substring(1));

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\63\u0164\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\6\2m\n\2\r\2\16\2n\3\3\3\3\3\3\5\3t\n\3\3\3\5\3w\n"+
		"\3\3\3\3\3\3\3\5\3|\n\3\3\3\3\3\3\3\5\3\u0081\n\3\3\4\3\4\5\4\u0085\n"+
		"\4\3\4\3\4\3\5\3\5\5\5\u008b\n\5\3\6\3\6\7\6\u008f\n\6\f\6\16\6\u0092"+
		"\13\6\3\6\3\6\3\6\3\7\3\7\5\7\u0099\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3"+
		"\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+"+
		"\3,\3,\3-\3-\3.\3.\3/\3/\7/\u0128\n/\f/\16/\u012b\13/\3\60\6\60\u012e"+
		"\n\60\r\60\16\60\u012f\3\60\3\60\3\61\3\61\3\61\3\61\7\61\u0138\n\61\f"+
		"\61\16\61\u013b\13\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\7\62"+
		"\u0146\n\62\f\62\16\62\u0149\13\62\3\62\3\62\3\63\3\63\7\63\u014f\n\63"+
		"\f\63\16\63\u0152\13\63\3\63\3\63\5\63\u0156\n\63\3\63\3\63\3\64\3\64"+
		"\7\64\u015c\n\64\f\64\16\64\u015f\13\64\3\64\3\64\3\65\3\65\3\u0139\2"+
		"\66\3\3\5\4\7\2\t\5\13\6\r\2\17\2\21\7\23\b\25\t\27\n\31\13\33\f\35\r"+
		"\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67\329\33;\34"+
		"=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62i\63\3\2\13"+
		"\3\2\62;\4\2GGgg\3\2//\6\2\f\f\17\17$$^^\n\2$$))^^ddhhppttvv\5\2C\\aa"+
		"c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u0171\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2"+
		"\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2"+
		"\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2"+
		"-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2"+
		"\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2"+
		"E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3"+
		"\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2"+
		"\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3"+
		"l\3\2\2\2\5\u0080\3\2\2\2\7\u0082\3\2\2\2\t\u008a\3\2\2\2\13\u008c\3\2"+
		"\2\2\r\u0098\3\2\2\2\17\u009a\3\2\2\2\21\u009d\3\2\2\2\23\u00a5\3\2\2"+
		"\2\25\u00a9\3\2\2\2\27\u00af\3\2\2\2\31\u00b6\3\2\2\2\33\u00bb\3\2\2\2"+
		"\35\u00be\3\2\2\2\37\u00c3\3\2\2\2!\u00c7\3\2\2\2#\u00ca\3\2\2\2%\u00d0"+
		"\3\2\2\2\'\u00d6\3\2\2\2)\u00df\3\2\2\2+\u00e6\3\2\2\2-\u00eb\3\2\2\2"+
		"/\u00f1\3\2\2\2\61\u00f3\3\2\2\2\63\u00f5\3\2\2\2\65\u00f7\3\2\2\2\67"+
		"\u00f9\3\2\2\29\u00fb\3\2\2\2;\u00fe\3\2\2\2=\u0101\3\2\2\2?\u0104\3\2"+
		"\2\2A\u0107\3\2\2\2C\u010a\3\2\2\2E\u010d\3\2\2\2G\u010f\3\2\2\2I\u0111"+
		"\3\2\2\2K\u0113\3\2\2\2M\u0115\3\2\2\2O\u0117\3\2\2\2Q\u0119\3\2\2\2S"+
		"\u011b\3\2\2\2U\u011d\3\2\2\2W\u011f\3\2\2\2Y\u0121\3\2\2\2[\u0123\3\2"+
		"\2\2]\u0125\3\2\2\2_\u012d\3\2\2\2a\u0133\3\2\2\2c\u0141\3\2\2\2e\u014c"+
		"\3\2\2\2g\u0159\3\2\2\2i\u0162\3\2\2\2km\t\2\2\2lk\3\2\2\2mn\3\2\2\2n"+
		"l\3\2\2\2no\3\2\2\2o\4\3\2\2\2pq\5\3\2\2qs\7\60\2\2rt\5\3\2\2sr\3\2\2"+
		"\2st\3\2\2\2tv\3\2\2\2uw\5\7\4\2vu\3\2\2\2vw\3\2\2\2w\u0081\3\2\2\2xy"+
		"\7\60\2\2y{\5\3\2\2z|\5\7\4\2{z\3\2\2\2{|\3\2\2\2|\u0081\3\2\2\2}~\5\3"+
		"\2\2~\177\5\7\4\2\177\u0081\3\2\2\2\u0080p\3\2\2\2\u0080x\3\2\2\2\u0080"+
		"}\3\2\2\2\u0081\6\3\2\2\2\u0082\u0084\t\3\2\2\u0083\u0085\t\4\2\2\u0084"+
		"\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\5\3"+
		"\2\2\u0087\b\3\2\2\2\u0088\u008b\5+\26\2\u0089\u008b\5-\27\2\u008a\u0088"+
		"\3\2\2\2\u008a\u0089\3\2\2\2\u008b\n\3\2\2\2\u008c\u0090\7$\2\2\u008d"+
		"\u008f\5\r\7\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0094\7$\2\2\u0094\u0095\b\6\2\2\u0095\f\3\2\2\2\u0096\u0099\n\5\2\2"+
		"\u0097\u0099\5\17\b\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\16"+
		"\3\2\2\2\u009a\u009b\7^\2\2\u009b\u009c\t\6\2\2\u009c\20\3\2\2\2\u009d"+
		"\u009e\7d\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7n\2\2"+
		"\u00a1\u00a2\7g\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4\7p\2\2\u00a4\22\3\2"+
		"\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7p\2\2\u00a7\u00a8\7v\2\2\u00a8\24"+
		"\3\2\2\2\u00a9\u00aa\7h\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7q\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad\u00ae\7v\2\2\u00ae\26\3\2\2\2\u00af\u00b0\7u\2\2\u00b0"+
		"\u00b1\7v\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7p\2\2"+
		"\u00b4\u00b5\7i\2\2\u00b5\30\3\2\2\2\u00b6\u00b7\7x\2\2\u00b7\u00b8\7"+
		"q\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7f\2\2\u00ba\32\3\2\2\2\u00bb\u00bc"+
		"\7k\2\2\u00bc\u00bd\7h\2\2\u00bd\34\3\2\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0"+
		"\7n\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7g\2\2\u00c2\36\3\2\2\2\u00c3\u00c4"+
		"\7h\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7t\2\2\u00c6 \3\2\2\2\u00c7\u00c8"+
		"\7f\2\2\u00c8\u00c9\7q\2\2\u00c9\"\3\2\2\2\u00ca\u00cb\7y\2\2\u00cb\u00cc"+
		"\7j\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7g\2\2\u00cf"+
		"$\3\2\2\2\u00d0\u00d1\7d\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7g\2\2\u00d3"+
		"\u00d4\7c\2\2\u00d4\u00d5\7m\2\2\u00d5&\3\2\2\2\u00d6\u00d7\7e\2\2\u00d7"+
		"\u00d8\7q\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7v\2\2\u00da\u00db\7k\2\2"+
		"\u00db\u00dc\7p\2\2\u00dc\u00dd\7w\2\2\u00dd\u00de\7g\2\2\u00de(\3\2\2"+
		"\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3"+
		"\7w\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7p\2\2\u00e5*\3\2\2\2\u00e6\u00e7"+
		"\7v\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7w\2\2\u00e9\u00ea\7g\2\2\u00ea"+
		",\3\2\2\2\u00eb\u00ec\7h\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee\7n\2\2\u00ee"+
		"\u00ef\7u\2\2\u00ef\u00f0\7g\2\2\u00f0.\3\2\2\2\u00f1\u00f2\7-\2\2\u00f2"+
		"\60\3\2\2\2\u00f3\u00f4\7/\2\2\u00f4\62\3\2\2\2\u00f5\u00f6\7,\2\2\u00f6"+
		"\64\3\2\2\2\u00f7\u00f8\7\61\2\2\u00f8\66\3\2\2\2\u00f9\u00fa\7\'\2\2"+
		"\u00fa8\3\2\2\2\u00fb\u00fc\7(\2\2\u00fc\u00fd\7(\2\2\u00fd:\3\2\2\2\u00fe"+
		"\u00ff\7~\2\2\u00ff\u0100\7~\2\2\u0100<\3\2\2\2\u0101\u0102\7?\2\2\u0102"+
		"\u0103\7?\2\2\u0103>\3\2\2\2\u0104\u0105\7#\2\2\u0105\u0106\7?\2\2\u0106"+
		"@\3\2\2\2\u0107\u0108\7>\2\2\u0108\u0109\7?\2\2\u0109B\3\2\2\2\u010a\u010b"+
		"\7@\2\2\u010b\u010c\7?\2\2\u010cD\3\2\2\2\u010d\u010e\7#\2\2\u010eF\3"+
		"\2\2\2\u010f\u0110\7>\2\2\u0110H\3\2\2\2\u0111\u0112\7@\2\2\u0112J\3\2"+
		"\2\2\u0113\u0114\7?\2\2\u0114L\3\2\2\2\u0115\u0116\7*\2\2\u0116N\3\2\2"+
		"\2\u0117\u0118\7+\2\2\u0118P\3\2\2\2\u0119\u011a\7}\2\2\u011aR\3\2\2\2"+
		"\u011b\u011c\7\177\2\2\u011cT\3\2\2\2\u011d\u011e\7]\2\2\u011eV\3\2\2"+
		"\2\u011f\u0120\7_\2\2\u0120X\3\2\2\2\u0121\u0122\7=\2\2\u0122Z\3\2\2\2"+
		"\u0123\u0124\7.\2\2\u0124\\\3\2\2\2\u0125\u0129\t\7\2\2\u0126\u0128\t"+
		"\b\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a^\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012e\t\t\2\2"+
		"\u012d\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\b\60\3\2\u0132`\3\2\2\2\u0133"+
		"\u0134\7\61\2\2\u0134\u0135\7,\2\2\u0135\u0139\3\2\2\2\u0136\u0138\13"+
		"\2\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u013a\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u013d\7,"+
		"\2\2\u013d\u013e\7\61\2\2\u013e\u013f\3\2\2\2\u013f\u0140\b\61\3\2\u0140"+
		"b\3\2\2\2\u0141\u0142\7\61\2\2\u0142\u0143\7\61\2\2\u0143\u0147\3\2\2"+
		"\2\u0144\u0146\n\n\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145"+
		"\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014b\b\62\3\2\u014bd\3\2\2\2\u014c\u0150\7$\2\2\u014d\u014f\5\r\7\2"+
		"\u014e\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151"+
		"\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0155\7^\2\2\u0154"+
		"\u0156\n\6\2\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2"+
		"\2\2\u0157\u0158\b\63\4\2\u0158f\3\2\2\2\u0159\u015d\7$\2\2\u015a\u015c"+
		"\5\r\7\2\u015b\u015a\3\2\2\2\u015c\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d"+
		"\u015e\3\2\2\2\u015e\u0160\3\2\2\2\u015f\u015d\3\2\2\2\u0160\u0161\b\64"+
		"\5\2\u0161h\3\2\2\2\u0162\u0163\13\2\2\2\u0163j\3\2\2\2\23\2nsv{\u0080"+
		"\u0084\u008a\u0090\u0098\u0129\u012f\u0139\u0147\u0150\u0155\u015d\6\3"+
		"\6\2\b\2\2\3\63\3\3\64\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}