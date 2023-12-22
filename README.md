# ScalaIO website code source

## Pre-requisites

- [Node.js](https://nodejs.org/en/) (>= 18)
- [Sass](https://sass-lang.com/): `npm install -g sass`

## Running

### Dev mode

Run sass watcher (will compile sass files to css on change):

```bash
sass --watch <input> <output>
```

Run the scalajs compilation watcher (will compile scala to js after each compilation):

```bash
sbt:root> ~fastLinkJS
```

Run the server:

```bash
npm run dev
```

### Prod mode

Should be handled by the GH action.
