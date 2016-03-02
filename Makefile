# Makefile to wrap the maven commands necessary to build the
# coursework executable and move it to a safe bin
# directory
#
# This is necessary because the lack of a deploy location means our
# jar file is vulnerable to stomping by the site goal
#

.PHONY : all
all:
	mvn clean install
	mkdir -p bin
	mv target/*-jar-with-dependencies.jar bin
	mvn site

.PHONY : clean
clean:
	rm -fr bin
	rm -fr target