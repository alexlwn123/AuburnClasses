# https://www.cs.rochester.edu/~brown/173/readings/05_grammars.txt
#
#  "TINY" Grammar
#
# PGM        -->   STMT+
# STMT       -->   ASSIGN   |   "print"  EXP
# ASSIGN     -->   ID  "="  EXP
# EXP        -->   TERM   ETAIL
# ETAIL      -->   "+" TERM   ETAIL  | "-" TERM   ETAIL | EPSILON
# TERM       -->   FACTOR  TTAIL
# TTAIL      -->   "*" FACTOR TTAIL  | "/" FACTOR TTAIL | EPSILON
# FACTOR     -->   "(" EXP ")" | INT | ID
# EPSILON    -->   ""
# ID         -->   ALPHA+
# ALPHA      -->   a  |  b  | … | z  or
#                  A  |  B  | … | Z
# INT        -->   DIGIT+
# DIGIT      -->   0  |  1  | …  |  9
# WHITESPACE -->   Ruby Whitespace

#
#  Parser Class
#
load "Lexer.rb"
class Parser < Scanner

  def initialize(filename)
    super(filename)
    consume()
  end

  def consume()
    @lookahead = nextToken()
    while(@lookahead.type == Token::WS)
      @lookahead = nextToken()
    end
  end

  def match(dtype)
    if (@lookahead.type != dtype)
      puts "Expected #{dtype} found #{@lookahead.type}"
      consume()
      @errors_found+=1
    end
    consume()
  end

  def program()
    @errors_found = 0
    p = AST.new(Token.new("program","program"))
    while(@lookahead.type != Token::EOF)
      p.addChild(statement())
    end

    if (@errors_found == 0)
      puts "Program parsed with no errors."
    else
      puts "There were #{@errors_found} parse errors found."
    end
    return p
  end

  def statement()
    stmt = AST.new(Token.new("statement","statement"))
    if (@lookahead.type == Token::PRINT)
      stmt = AST.new(@lookahead)
      match(Token::PRINT)
      stmt.addChild(exp())
    else
      stmt = assign()
    end
    return stmt
  end

  def exp()
    ter = term()
    eta = etail()
    if not eta.nil?
      eta.addChild(ter)
      return eta
    end
    return ter
  end

  def term()
    ter = factor()
    tail = ttail() 
    if not tail.nil?
      tail.addChild(ter)
      return tail
    else
      return ter
    end
  end

  def factor()
    if (@lookahead.type == Token::LPAREN)
      match(Token::LPAREN)
      fct = exp()
      match(Token::RPAREN)
    elsif (@lookahead.type == Token::INT)
      fct = AST.new(@lookahead)
      match(Token::INT)
    elsif (@lookahead.type == Token::ID)
      fct = AST.new(@lookahead)
      match(Token::ID)
    else
      puts "Expected to see ( or INT Token or ID Token. Instead found #{@lookahead.text}"
      @errors_found+=1
      consume()
      fct = nil
    end
    return fct
  end

  def ttail()
    if (@lookahead.type == Token::MULTOP)
      multop = AST.new(@lookahead)
      match(Token::MULTOP)
      multop.addChild(factor())
      multop.addChild(ttail())
      return multop 
    elsif (@lookahead.type == Token::DIVOP)
      divop = AST.new(@lookahead)
      match(Token::DIVOP)
      divop.addChild(factor())
      divop.addChild(ttail())
      return divop
    else
      return nil
    end
  end

  def etail()
    if (@lookahead.type == Token::ADDOP)
      addop = AST.new(@lookahead)
      match(Token::ADDOP)
      addop.addChild(term())
      addop.addChild(etail())
      return addop    
    elsif (@lookahead.type == Token::SUBOP)
      subop = AST.new(@lookahead)
      match(Token::SUBOP)
      subop.addChild(term())
      subop.addChild(etail())
      return subop
    else
      return nil
    end
  end

  def assign()
    assgn = AST.new(Token.new("assignment","assignment"))
    if (@lookahead.type == Token::ID)
      idtok = AST.new(@lookahead)
      match(Token::ID)
      if (@lookahead.type == Token::ASSGN)
        assgn = AST.new(@lookahead)
        assgn.addChild(idtok)
        match(Token::ASSGN)
        assgn.addChild(exp())
      else
        match(Token::ASSGN)
      end
    else
      match(Token::ID)
    end
    return assgn
  end
end
