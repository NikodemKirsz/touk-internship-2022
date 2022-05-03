# touk-internship-2022
Repository containing recruitment task from TouK for internship 2022.

## **Application Info**
#### **Technical Info**
The application is running on Java 17 (Oracle OpenJDK v17) and is using Spring Boot Framework v2.6.7.</br>
It also uses Hibernate Framework for communication with a database, which was created and is connected to using PostreSQL. 

To run the application open the terminal in the directory in which You cloned the repository and run CompileAndRun.bat script.

The script takes 2 arguments:
- path to *jdk17/bin* directory (default: *"C:\Program Files\Java\jdk-17\bin"*)
- port number (default *8080*)

So the example running commands look like this:

- <code>touk-internship-2022>.\CompileAndRun.bat</code> (compiles and runs the app using java.exe from ***default*** directory and starts on port ***8080***)
- <code>touk-internship-2022>.\CompileAndRun.bat "C:\Program Files\Java\jdk-17\bin"</code> (compiles and runs the app using java.exe from ***given*** directory and starts on port ***8080***)
- <code>touk-internship-2022>.\CompileAndRun.bat "C:\Program Files\Java\jdk-17\bin" 8081</code> (compiles and runs the app using java.exe from ***given*** directory and starts on port ***8081***)
	
If the project is already compiled You can just call Run.bat (the same set of arguments).

#### **App Description**
This application is a seat reservation system for a multiplex.</br>
It lets clients search for screenings in given time interval, list information about particular screening and reserve arbitrary number of seats for a screening.</br>
Program also validates user input and checks, if it agrees with provided business logic.

## **Model**
Image

## **Endpoints**
#### REST endpoints available in the application:
- <code>/search/screenings</code> - lists all screenings starting from now to 24 hours
- <code>/search/screenings?start={*date_time*}</code> - lists all screenings starting from given *date_time* to 24 hours
- <code>/search/screenings?start={*date_time*}&length={*length*}</code> - lists all screenings starting from given *date_time* for specified *length* (in minutes)
- <code>/search/screenings/{*id*}</code> - gives detailed info about a screening with given *id*
- <code>/reservation/{*screeningId*}?name={*name*}&surname={*surname*}</code> - make a reservation for a screening with specified *screeningId* for client with name *name* and surname *surname*; this endpoint requires a JSON RequestBody with format as follows:
```json
 {
    "{seatId}": "{ticketType}",
    "{seatId}": "{ticketType}",
    ...
    "{seatId}": "{ticketType}"
 }
```

#### Example calls:
- list all screenings from 29-04-29 17:00 to 29-04-29 22:00</br>
  <code>***host***/search/screenings?length=300&start=2022-04-29_17:00</code>
- give detailed info about a screening with id 1</br>
  <code>***host***/search/screenings/1</code>
- make a reservation for a screening with id 1 for client Robert Zieliński for 2 people with seats of ids 10 and 11, where one is adult and one is a child </br>
  <code>***host***/reservation/2?name=Robert&surname=Zieliński</code>
  ```json
  {
    "10": "ADULT:
    "11": "CHILD"
  }
  ```

## Test case
For a testing purpose I wrote a Powershell scripts which uses `Invoke-RestMethod` command for calling the endpoint and also uses .NET library System.String.</br>
It accepts an optional argument for port number.</br>
To run it You need to open new Powershell instance in the directory of the repository touk-internship-2022. You can always change the directory with</br>
  `cd "Path to directory"`</br>
Then run it with</br>
  <code>.\ExampleRun.ps1 *portNumber*</code></br>
or in case it does not work, use</br>
  <code>powershell -ExecutionPolicy ByPass -File ".\ExampleRun.ps1" *portNumber*</code></br>
  
## **Notes & Comments**
Over the course of developing the application I might have taken a couple of presumptions, and so I'm listing them below:
- I put all of the constants in a *.\ticket-booker\src\main\java\com\touk\ticketbooker\Multiplex.java* file. (I will refer to it as a 'Constants File')
- All of the rooms have the same capacity (stated in a Constants File)
- All of the rooms have the same seats structure (stated in a Constants File) and they are rectangular
- Client's Name and Surname create a unique pair identifying specific client (as stated in the instructions)




