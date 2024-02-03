# ScalaIO website code source

Note : `main` is deployed to https://preprod-scala-io.cleverapps.io/

Previews on PR are available with the bot.


## Data

Data for talks, speakers, schedule are here: https://github.com/ScalaIO/scala.io/tree/main/src/main/scala/io/scala/data


## Pre-requisites

- [Node.js](https://nodejs.org/en/) (>= 18)
- [Sass](https://sass-lang.com/): `npm install -g sass`

## Running dev

```bash
npm install
make dev
```

The app wil be available at http://localhost:5173

## Fixing a speaker / talk information

- Create a fork
- Edit the content in `src/main/scala/io/scala/data`
- Open a PR

You can use the following git commit message template:

```sh
fix(talk): <talk title>
fix(speaker): <speaker name>
```
