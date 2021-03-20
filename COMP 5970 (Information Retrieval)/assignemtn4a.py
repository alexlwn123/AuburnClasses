import sys

for line in sys.stdin.read().splitlines():
  a, b = line.split('=')
  word = a[5:-1]
  print(b)
