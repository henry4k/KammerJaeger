XCF2PNG = xcf2png

IMG_SRC = $(wildcard *.xcf)
IMG_DST = $(patsubst %.xcf,%.png,$(IMG_SRC))

.PHONY: all clean

all: .gitignore $(IMG_DST)

.gitignore:
	touch .gitignore
	echo '# THIS FILE IS GENERATED AUTOMATICALLY' >> .gitignore
	echo '/.gitignore' >> .gitignore
	echo '/*.png' >> .gitignore

%.png : %.xcf
	$(XCF2PNG) -o $@ $<

clean:
	-rm -f .gitignore $(IMG_DST)
