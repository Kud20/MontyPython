test:
	make build;
	make run;

build:
	javac Door.java;
	javac Statistics.java;
	javac MontyHall.java;

run:
	java MontyHall

clean:
	-rm demo.o
	-rm demo