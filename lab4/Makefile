# Makefile for FileReverse.c with macros

FLAGS   = -std=c99 -Wall
VGFLAGS = --leak-check=full
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN  = charType
OUTTEST = charTypeOut


all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

check :
	valgrind $(VGFLAGS) ./$(EXEBIN) $(SOURCES) $(OUTTEST) 

clean :
	rm -f $(EXEBIN) $(OBJECTS) $(OUTTEST)
