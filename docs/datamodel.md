# Data models

## Talks

```md
# <title>

- Kind: (Keynote|Lightning|Short|Talk|Workshop)
- Category: <String>
- DateTime: <date> | <time>
- Room: <Int>
- Slides: <url>
- Replay: <url>

## Abstract

<abstract>

## Speakers

### <name_1>

- photoRelPath: <url> # relative path to the image from the `public` folder
- job: <String> @ <String>
- confirmed: true # optional. A confirmed talk will appear on the website without the `?withDraft=true` query param

#### Links

- [Twitter](<url>) # optional
- [Linkedin](<url>) # optional
- [Github](<url>) # optional
- [Other](<url>) # optional

#### Bio

<bio>

### <name_2>

...
```

## Meetups

```md
# <name>

- Date: <date> | <time>
- Location: <String>
- Link: [<name>](<url>)

<description>

## Talks

### <title_1>

- [<name_1>](<url>) # speaker's name and link to a profile

<abstract>

### <title_2>
```