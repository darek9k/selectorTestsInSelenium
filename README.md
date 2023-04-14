# selectorTestsInSelenium

Kalkulator BMI - specyfikacja / Testy Selenium JUnit5, AssertJ


Autor: Dariusz Kowalski, 
za pomocą szablonu
Ostatnia aktualizacja: kwi 03, 2023
Kalkulator BMI. Celem aplikacji jest obliczanie BMI oraz wyświetlanie odpowiedniej etykiety wg zakresu podanego w specyfikacji. BMI - wskaźnik masy ciała. BMI obliczane wg wzoru : BMI ==masa/wzrost/wzrost;

Użytkownik podaje masę wyrażoną w kilogramach oraz wzrost wyrażony w cm

Po wciśnięciu przycisku OBLICZ, wyświetlana jest obliczona wartość BMI oraz etykieta NADWAGA/NIEDOWAGA/ PRAWIDŁOWA WAGA w zależności od wartości BMI.

Po wprowadzeniu niepoprawnych danych spoza zakresu, BMI nie jest obliczane oraz wyświetlany jest komunikat “Wprowadzono niepoprawne dane”. Dopuszczalne zakresy to:

waga >=2kg i <=250kg

wzrost >=0.3m i <=3m

Etykiety wyświetlane są wg poniższych widełek: BMI <= 20 - NIEDOWAGA; BMI >= 25 NADWAGA; 20<BMI<25 - PRAWIDŁOWA WAGA;

W implementacji strony celowo został wprowadzony błąd. Pomimo podania danych spoza zakresu, BMI jest obliczane i wyświetlany jest komunikat OK oraz Nan. (html oraz javascript ze szkolenia - twoje.kursy.pl)

 

Po wyprowadzeniu przypadków testowych - uzyskane dane zostaną użyte do wprowadzenia ich do formularza automatycznie przy wykorzystaniu narzędzia Selenium. Biblioteki : JUnit5 oraz AssertJ :slight_smile: 

/*
BMI Calculator - Specification / Selenium JUnit5 Tests, AssertJ

Author: Dariusz Kowalski,
using a template
Last updated: Apr 03, 2023
BMI Calculator. The goal of the application is to calculate BMI and display the appropriate label based on the range specified in the specification. BMI - Body Mass Index. BMI is calculated using the formula: BMI == weight/height/height;

The user enters the weight in kilograms and the height in centimeters.

After clicking the CALCULATE button, the calculated BMI value and the label UNDERWEIGHT/OVERWEIGHT/HEALTHY WEIGHT are displayed, depending on the BMI value.

If incorrect data outside the range is entered, BMI is not calculated and the message "Incorrect data entered" is displayed. The allowable ranges are:

weight >= 2kg and <= 250kg

height >= 0.3m and <= 3m

Labels are displayed based on the following ranges: BMI <= 20 - UNDERWEIGHT; BMI >= 25 OVERWEIGHT; 20<BMI<25 - HEALTHY WEIGHT;

An error has intentionally been introduced in the page implementation. Despite entering data outside the range, BMI is calculated and the message "OK" and "NaN" are displayed. (HTML and JavaScript from the training - twoje.kursy.pl)

After formulating the test cases, the obtained data will be used to automatically input them into the form using the Selenium tool. Libraries: JUnit5 and AssertJ.
*/
