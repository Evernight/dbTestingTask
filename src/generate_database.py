#!/usr/bin/python

import sys
import random
import string
from datetime import timedelta, datetime

MAX_ID = 0
MODELS_NUM = 10
DELIMETER = '\t'

rand_string_symbols = string.letters + " "

models_list = []

def get_next_ID():
	global MAX_ID
	MAX_ID += 1
	return MAX_ID

def random_string(length):
	return ''.join([random.choice(rand_string_symbols) for i in xrange(length)])

def get_random_model():
	return random.choice(models_list)

def generate_models_list():
	global models_list
	models_list = [random_string(random.randint(10, 15)) for i in xrange(MODELS_NUM)]

def get_random_condition():
	return random.choice(["Bad", "Good", "Totally broken", "Awesome", "Helicopter"])

def get_random_color():
	return random.choice(["Red", "Orange", "Yellow", "Green"])

def get_random_class():
	return random.choice(["Sport", "Racing", "Truck"])

def random_date(start = datetime.strptime('1/1/2003 1:30 PM', '%m/%d/%Y %I:%M %p'),
                end   = datetime.strptime('1/1/2011 4:50 AM', '%m/%d/%Y %I:%M %p')):
	delta = end - start
	int_delta = (delta.days * 24 * 60 * 60) + delta.seconds
	random_second = random.randrange(int_delta)
	return (start + timedelta(seconds=random_second))

class CarAdsRecord:
	def __init__(self, id=None, contact=None, model=None):
		if id == None:
			id = get_next_ID()
		if contact == None:
			contact = random_string(random.randint(20, 40))
		if model == None:
			model = get_random_model()
		year = random.randint(1800, 2011)
		price = random.randint(300, 300500)
		condition = get_random_condition()
		date = random_date()
		rating = random.random()
		color = get_random_color()
		car_class = get_random_class()

		self.model = model
		self.car_class = car_class
		self.id = id
		self.contact = contact
		self.year = year
		self.price = price
		self.condition = condition
		self.date = date
		self.rating = rating
		self.color = color

	def __str__(self):
		return DELIMETER.join(map(str, [self.id, self.model, self.car_class, self.color, self.year, self.condition, self.price, self.contact, self.date, self.rating]))

def main():
	n = int(sys.argv[1])

	generate_models_list()
	
	for i in xrange(n):
		print CarAdsRecord()

if __name__ == "__main__":
	main()
