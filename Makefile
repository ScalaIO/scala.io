format:
	sbt scalafmt

test:
	sbt test

dev:
	make -j 2 dev-sbt dev-vite

dev-sbt:
	sbt "~fastLinkJS"

dev-vite:
	npm run dev

build: build-sbt vite

build-sbt:
	sbt "fullLinkJS"

build-vite:
	npm run build
