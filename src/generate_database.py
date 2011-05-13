#!/usr/bin/python

import sys
import random
import string

MAX_ID = 0

def get_next_ID():
	global MAX_ID
	MAX_ID += 1
	return MAX_ID

def random_string(length):
	return ''.join([random.choice(string.letters) for i in xrange(length)])

class CarAdsRecord:
	def __init__(self, id=None, contact=None):
		if id == None:
			id = get_next_ID()
		if contact == None:
			contact = random_string(20)

		self.id = id
		self.contact = contact

	def __str__(self):
		return "%s\t%s" %(self.id, self.contact)

def main():
	n = int(sys.argv[1])
	
	for i in xrange(n):
		print CarAdsRecord()

if __name__ == "__main__":
	main()
