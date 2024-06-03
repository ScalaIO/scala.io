# ScalaIO website code source

Note : `main` is deployed to https://preprod-scala-io.cleverapps.io/

Previews on PR are available with the bot.


## Data

Data for talks, speakers, schedule are here: https://github.com/ScalaIO/scala.io/tree/main/src/main/scala/io/scala/data

This is currently being move to markdown files in the `public` folder:

- `conferences/<conference>` contains a markdown for each talk alongside speakers' information (document model)
- `images/profiles/<conference>` contains the speaker images
- `scalafr-meetups` contain the markdown for each meetup

The data model can be found [here](docs/datamodel.md).

## Pre-requisites

- [Node.js](https://nodejs.org/en/) (>= 18)
- [Sass](https://sass-lang.com/): `npm install -g sass`

## Build

- `npm install`
- `sbt test`

## Running dev

If you have [mprocs](https://github.com/pvolok/mprocs) installed, you can run at the root of the project:

```zsh
mprocs
```

It will run every necessary process for the app to run. Once it's done, go to the `sbtn` tab to access the corresponding shell and run `~fastLinkJS` to have the app recompiled on every change.

Otherwise you can use

```bash
make dev
```

If this doesn't work try:

- `make dev-scss` in one shell tab
- `make dev-sbt` in a second shell tab
- `make dev-vite` in a third shell tab

The app wil be available at http://localhost:5173

## Fixing a speaker / talk information

- Create a fork
- Edit the content in:
  - `src/main/scala/io/scala/data/SpeakersInfo.scala` for speakers' info
  - `src/main/resources/md/<talk-slug>.md` for talks' content
  - `src/main/scala/io/scala/data/TalksInfo.scala` for talks' info (speakers...)
    - The description to provide is located in `io.scala.data.MarkdownSource.*` and is source-generated from the `<talk-slug>.md` file, with every `-` (dash) replaced by `_` (underscore)
- Open a PR

You can use the following git commit message template:

```sh
fix(talk): <talk title>
fix(speaker): <speaker name>
```
