import http.server
import socketserver

port = 80
Handler = http.server.SimpleHTTPRequestHandler

sock = socketserver.TCPServer(("172.19.38.128", port), Handler)

sock.serve_forever()
