# PaymentTracker

This program is used for keeping a records of payments. 
Each payment includes a currency and an amount.

The program output a list of all the currency and amounts to the console once per minute.
The input can be typed into the command line and also be loaded from a file when starting up.

###Assumptions
1. Program starts with print out the all payments and sum of payments (loaded from file).
2. File is saved in resource directory. File name is "Payments.txt".
3. If file is broken or missing, program notify user and continue without payments from file.
4. Input is case sensitive. Please input commands and payments in uppercase.
5. User can input commands like **PRINTOUT** and **QUIT**.
6. Commands **PRINTOUT** is used for enforceable print out the all payments and sum of payments.
7. With command **QUIT** user exits the program.
8. Payment format consist from currency code (3 letters) and amount of money. Currency and amount of money are divided by one space.
9. Available currencies: USD, HKD, RMB, EUR, GBP.
10. If user input unknown currency, program display available currencies.
11. For amount of money, decimal number are divided by dot and only 2 digits are allowed after dot. 
12. If user input unknown command or payment, program notify user.
13. Every one print out has USD equivalent amount next to it.
14. Once per minute, program automatically shows the state of account witch all payments (from file and from memory) and sum of payments. 
15. If the net amount is 0, that currency isn't be displayed. 


###Run the program
- run the ain method in App
- run the command: mvn package exec:java

###Sample<br/>
All payments:<br/>
USD 100<br/>
HKD 100<br/>
USD -100<br/>
RMB 2000<br/>
HKD 200.01<br/>
GBP 100.56<br/>
GBP 9870.60<br/>
GBP -5000<br/>

Payments summarization:<br/>
GBP 4971.16<br/>
RMB 2000<br/>
HKD 300.01<br/>

Payments summarization in USD:<br/>
GBP 4971.16(USD 6164.24)<br/>
RMB 2000(USD 280.00)<br/>
HKD 300.01(USD 36.00)<br/>