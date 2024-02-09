# import a Python package that helps us connect to the new database
import sqlite3
import pandas as pd

# connect to the billing database
con = sqlite3.connect('billing.db')
cur = con.cursor()

# show us the three tables that exist there
table_list = [a[0] for a in cur.execute("SELECT name FROM sqlite_master WHERE type = 'table'")]

for table in table_list:
  print('Existing table named ' + table + '\n')

# build a query to create the new billing view

# first, we'll include some code that tells the database we're creating a new view of the data
query = 'CREATE VIEW billing AS'

# next, we'll tell the database which columns we want in our final table
query += ' SELECT BillingEvent.ID AS ID, username, Date, Event FROM BillingEvent'

# lastly, we'll explain how to connect the three tables
query += ' INNER JOIN Customer on BillingEvent.CustomerID = Customer.ID'
query += ' INNER JOIN BillingEventTypes on BillingEvent.EventType = BillingEventTypes.ID'

# now that the query in SQL is built, we'll use Python to send it to the database. We've added some code to catch an error if you run the exercise more than once!

print("Creating new billing view combining the three: ")
try:
  cur.execute(query)
except sqlite3.OperationalError:
  print("You've already run this exercise! Here's the table: ")
  
# finally, let's take a look at our table!
# we'll do this the way a data scientist might - using the package pandas!
billing = pd.read_sql('SELECT * FROM billing',con)
print(billing)

# close our connection to the database
con.close()


# magic8.py

import random

name = ""

question = "Will I win the lottery?"

answer = ""

random_number = random.randint(1,9)

# print(random_number)

if random_number == 1:
  answer = "Yes - definitely"
elif random_number == 2:
  answer = "It is decidedly so"
elif random_number == 3:
  answer = "Without a doubt"
elif random_number == 4:
  answer = "Reply hazy, try again"
elif random_number == 5:
  answer = "Ask again later"
elif random_number == 6:
  answer = "Better not tell you now"
elif random_number == 7:
  answer = "My sources say no"
elif random_number == 8:
  answer = "Outlook not so good"
elif random_number == 9:
  answer = "Very doubtful"
else:
  answer = "Error"

if name == "":
  print("Question:", question)
  print("Magic 8-Ball's answer:", answer)
else:
  print(name, "asks:", question)
  print("Magic 8-Ball's answer:", answer)


# customer data with 2D list 
first_names = ["Ainsley",
"Ben",
"Chani",
"Depak"]

preferred_size = ["Small", "Large", "Medium"]

preferred_size.append("Medium")

print(preferred_size)

customer_data = [
  ["Ainsley",	"Small",	True],
["Ben",	"Large",	False],
["Chani",	"Medium",	True],
["Depak",	"Medium",	False]
]

print(customer_data)

customer_data[2][-1] = False

customer_data[1].remove(False)

print(customer_data)

customer_data_final = customer_data + [["Amit", "Large", True], ["Karim", "X-Large", False]]

print(customer_data_final)

# gradebook

last_semester_gradebook = [["politics", 80], ["latin", 96], ["dance", 97], ["architecture", 65]]

# Your code below: 
subject = [
  "physics",
"calculus",
"poetry",
"history"
]

grades = [98, 97, 85, 88]

gradebook = [
  ["physics",	98],
["calculus",	97],
["poetry",	85],
["history",	88]
]

print(gradebook)

gradebook.append(["computer science", 100])

gradebook.append(["visual arts", 93])

gradebook[-1][-1] = 97

print(gradebook)

gradebook[2].remove(85)

gradebook[2].append("Pass")

print(gradebook)

full_gradebook = last_semester_gradebook + gradebook 

print(full_gradebook)

#  list comprehensions with conditionals
numbers = [2, -1, 79, 33, -45]

no_if   = [num * 2 for num in numbers]
if_only = [num * 2 for num in numbers if num < 0]
if_else = [num * 2 if num < 0 else num * 3 for num in numbers]

# Data Analyst at Carly’s Clippers
hairstyles = ["bouffant", "pixie", "dreadlocks", "crew", "bowl", "bob", "mohawk", "flattop"]

prices = [30, 25, 40, 20, 20, 35, 50, 35]

last_week = [2, 3, 5, 8, 4, 4, 6, 2]

total_price = 0

for price in prices:
  total_price += price

average_price = total_price / len(prices)

print("Average Haircut Prices:", average_price)

new_prices = [price-5 for price in prices]

print(new_prices)

total_revenue = 0

for i in range(len(hairstyles)):
  total_revenue += (prices[i] * last_week[i])

print("Total Revenue:", total_revenue)

average_daily_revenue = total_revenue / 7

print(average_daily_revenue)

cuts_under_30 = [hairstyles[i] for i in range(len(new_prices)-1) if new_prices[i] < 30]

print(cuts_under_30)

# physic class
train_mass = 22680
train_acceleration = 10
train_distance = 100
bomb_mass = 1


# Write your code below: 
def f_to_c(f_temp):
  c_temp = (f_temp - 32) * 5/9
  return c_temp

def c_to_f(c_temp):
  f_temp = c_temp * 9/5 + 32
  return f_temp

print(f_to_c(100))

c0_in_fahrenheit = c_to_f(0)

def get_force(mass, acceleration):
  return mass * acceleration

train_force = get_force(train_mass, train_acceleration)

print("The GE train supplies "+ str(train_force) + " Newtons of force.")

def get_energy(mass, c = 3*10**8):
  return mass * (c**2)

bomb_energy = get_energy(bomb_mass)

print(bomb_energy)

def get_work(mass, acceleration, distance):
  return get_force(mass, acceleration) * distance

train_work = get_work(train_mass, train_acceleration, train_distance)

print("The GE train does " + str(train_work) +" Joules of work over "+ str(train_distance) + " meters.")

# Attribute Functions
can_we_count_it = [{'s': False}, "sassafrass", 18, ["a", "c", "s", "d", "s"]]

for i in can_we_count_it:
  if hasattr(i, "count"):
    print(str(type(i)) + " has the count attribute!")
  else:
    print(str(type(i)) + " does not have the count attribute :(")
