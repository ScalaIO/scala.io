format:
	sbt scalafmt

test:
	sbt test

dev:
	make -j 3 dev-scss dev-sbt dev-vite

dev-scss:
	npx sass --watch ./src/main/resources/css/main.scss ./src/main/resources/css/output.css

dev-sbt:
	sbt "~fastLinkJS"

dev-vite:
	npm run dev

build: build-scss build-sbt build-vite

build-scss:
	npx sass ./src/main/resources/css/main.scss ./src/main/resources/css/output.css

build-sbt:
	sbt "fullLinkJS"

build-vite:
	npm run build
