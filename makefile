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
	-rm MontyHall.class
	-rm Door.class
	-rm Statistics.class
	-rm Results.csv