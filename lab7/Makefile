#------------------------------------------------------------------------------
#   Makefile for Dictionary ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = DictionaryClient
MAINCLASS  = DictionaryClient
SOURCES    = DictionaryInterface.java Dictionary.java DictionaryClient.java DuplicateKeyException.java KeyNotFoundException.java
CLASSES    = DictionaryInterface.class Dictionary.class DictionaryClient.class DuplicateKeyException.class KeyNotFoundException.class

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

test:
	java $(JARFILE)

clean:
	rm -f $(CLASSES) $(JARFILE) *.class

