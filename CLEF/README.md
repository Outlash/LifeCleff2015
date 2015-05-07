LEFF

##### Inainte sa incepeti scrieti in comentarii ce arhiva cu wav-uri din torente aveti pentru ca sa nu faca doua persoane procesul pe aceleasi fisiere.

-  In functie de fisierul ales trebuie sa setati variabila train in clasa App[\mffcExtraction\CLEF\src\audio\App.java] (training files=true|test files=false) (linia 16)
-  Dati extract in functie de ce fisiere aveti in folderul input/test sau input/train la fisierele wav (mffcExtraction\CLEF\input)
- In clasa FileIterator.java [mffcExtraction\CLEF\src\files] setati calea exacta catre folderul cu wav-uri pentru test si train la liniile 24 respectiv 26.
-  Rulati si la final arhivati fisierele rezultate din folderul mffcExtraction\CLEF\output.
-  Creati un torent si postati-l.
