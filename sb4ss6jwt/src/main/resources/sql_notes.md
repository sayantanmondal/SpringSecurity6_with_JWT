SELECT cust_code, cust_name, cust_city, cust_country
//Selecting specific columns: cust_code, cust_name, cust_city, cust_country
FROM customer
//From the table named "customer"
WHERE cust_name LIKE 'R_m%';
//Where the value in the column cust_name starts with 'R', followed by any single character ('_'), and then followed by 'm' and any number of characters after that

////////////////////////
like Operator
Pattern 	Description 	                            Example Match
'a%'	Starts with "a"	                                    "apple", "alpha"
'%a'	Ends with "a"	                                    "banana", "data"
'%or%'	Contains "or" at any position	                    "world", "fort"
'_r%'	Has "r" in the second position	                    "iron", "area"
'a_%'	Starts with "a" and is at least 2 characters long	"at", "ace"
'a__%'	Starts with "a" and is at least 3 characters long	"abc", "area"
'a%o'	Starts with "a" and ends with "o"	                "alto", "audio"
/////////////////////////