import string
import sys
import csv
import math
from collections import Counter
def main():
  lines = []
  csv.field_size_limit(2**31-1)
  with open('state-of-the-union.txt', 'r') as f:
    reader = csv.reader(f)
    lines = [line[1].translate(str.maketrans('', '', string.punctuation)).lower().split() for line in reader]

  TFs = [Counter(line) for line in lines]
  words = []
  for line in TFs:
    words.extend(line.keys())

  counts = Counter(words)
  idfs = {}
  for word, count in counts.items():
    idfs[word] = math.log(len(TFs)/ count)


  tfidfs = [{word : tf * idfs[word] for word, tf in doc_tf.items()} for doc_tf in TFs]
  normalized = []
  for doc in tfidfs:
    tot = sum(doc.values())
    normalized.append({word: tfidf/tot for word, tfidf in doc.items()})

  #random_speech = sorted(dict(normalized[4].items()), key=dict(normalized[4].items()).get)
  random_speech = normalized[3]
  inversed = sorted(random_speech.items(), key=lambda x: x[1])








  print(inversed[-1:-21:-1])








if __name__ == '__main__':
  main()
