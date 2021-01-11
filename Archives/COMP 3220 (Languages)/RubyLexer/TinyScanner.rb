# https://www.cs.rochester.edu/~brown/173/readings/05_grammars.txt
#
#  "tiny" grammar
#
# PGM        -->   STMT+
# STMT       -->   ASSIGN   |   "print"  EXP                           
# ASSIGN     -->   ID  "="  EXP
# EXP        -->   TERM   ETAIL
# ETAIL      -->   "+" TERM   ETAIL  | "-" TERM   ETAIL | EPSILON
# TERM       -->   FACTOR  TTAIL
# TTAIL      -->   "*" FACTOR TTAIL  | "/" FACTOR TTAIL | EPSILON
# FACTOR     -->   "(" EXP ")" | INT | ID   
#                  
# ID         -->   ALPHA+
# ALPHA      -->   a  |  b  | … | z  or 
#                  A  |  B  | … | Z
# INT        -->   DIGIT+
# DIGIT      -->   0  |  1  | …  |  9
# WHITESPACE -->   Ruby Whitespace
#
#
#  Class Scanner - Reads a TINY program and emits tokens
#
class Scanner 
# Constructor - Is passed a file to scan and outputs a token
#               each time nextToken() is invoked.
#   @c        - A one character lookahead 
	def initialize(filename)
		# Need to modify this code so that the program
		# doesn't abend if it can't open the file but rather
		# displays an informative message
        begin 
		    @f = File.open(filename,'r:utf-8')
	    rescue Errno::ENOENT
            puts "Cannot locate file name in current directory: \nAborting..."
            exit
        end
		# Go ahead and read in the first character in the source
		# code file (if there is one) so that you can begin
		# lexing the source code file 
		if (! @f.eof?)
			@c = @f.getc()
		else
			@c = "!eof!"
			@f.close()
		end
	end
	
	# Method nextCh() returns the next character in the file
	def nextCh()
		if (! @f.eof?)
			@c = @f.getc()
		else
			@c = "!eof!"
		end
		
		return @c
	end

	# Method nextToken() reads characters in the file and returns
	# the next token

    def nextWhite()
        str = ""
        while /\s/.match?(@c)
            str += @c
            nextCh()
        end
     
        return Token.new(Token::WHITESPACE, str)
    end

    def nextNum()
        str = @c
        nextCh()
        while /\d/.match?(@c)
            str += @c
            nextCh()
        end
        return Token.new(Token::INT, str)
    end 
        
    def nextId()
        str = @c 
        nextCh()
        while /[A-Z]/i.match?(@c)
            str += @c
            nextCh()
        end
        if str == "print"
            return Token.new(Token::PRINT,str)
        end
        
        tok = Token.new(Token::ID, str)

        return tok 

    end 

    def nextToken()
        case @c
        when "!eof!"
			return Token.new(Token::EOF,"eof")
        
        when /^(\s)+$/
            tok = nextWhite()
            return tok
        when /^(\d)$/
            tok = nextNum()
            return tok
        when /[a-z]/i
            tok = nextId()
            return tok
        when /^[+\-*\/\()=]$/
            
            case @c
            when "(" 
                tok = Token.new(Token::LPAREN, @c)
                nextCh()
                return tok
            when ")" 
                tok =  Token.new(Token::RPAREN, @c)
                nextCh()
                return tok
            when "+", "-"
                tok = Token.new(Token::ADDOP, @c)
                nextCh()
                return tok
            when "*", "/"
                tok = Token.new(Token::PRODUCTOP, @c)
                nextCh()
                return tok
            when "="
                tok = Token.new(Token::ASSIGNOP, @c)
                nextCh()
                return tok
            end
        else
            tok = Token.new("Unknown", "Unknown")
            nextCh()
            return tok
        end
    end
        # elsif ...
		# more code needed here! complete the code here 
		# so that your scanner can correctly recognize,
		# print (to a text file), and display all tokens
		# in our grammar that we found in the source code file
		
		# FYI: You don't HAVE to just stick to if statements
		# any type of selection statement "could" work. We just need
		# to be able to programatically identify tokens that we 
		# encounter in our source code file.
		
		# don't want to give back nil token!
		# remember to include some case to handle
		# unknown or unrecognized tokens.
		# below is an example of how you "could"
		# create an "unknown" token directly from 
		# this scanner. You could also choose to define
		# this "type" of token in your token class
	
#
# Helper methods for Scanner
#
end
