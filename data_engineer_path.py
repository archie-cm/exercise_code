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

