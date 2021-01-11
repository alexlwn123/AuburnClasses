load "Parser.rb"
load "Lexer.rb"
load "Token.rb"
load "AST.rb"

parse = Parser.new("input1.txt")
mytree = parse.program()
puts mytree.toStringList()
