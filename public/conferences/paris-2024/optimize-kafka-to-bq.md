# Optimizing Data Transfer Kafka to BQ: let's use Scala to make it custom

- Kind: Talk
- Slug: optimize-data-transfer-kafka-to-bq
- Category: Data
- confirmed: true
- DateTime: 2024-11-08T11:15:00
- Room: A

## Abstract

```
I will explore how our team developed a custom solution to address the escalating data and cost challenges of traditional cloud services in our data pipeline. Initially, we used Dataflow to move 2 billion daily events from Kafka to BigQuery, but rising costs prompted a shift to a more sustainable model. I will describe our transition to a two-part custom application that stages data in GCP cloud storage before loading it into BigQuery, and highlight how Scala’s features and the ZIO ecosystem contributed significantly to the performance and reliability of this application.
```

## Speakers

### Dario Amorosi

- photoRelPath: /images/profiles/paris-2024/dAmorosi.webp
- job: Big Data Engineer @ Adevinta

#### Links

- [Linkedin](https://www.linkedin.com/in/dario-amorosi-019317151)

#### Bio

```
I will introduce the basic components that are part of the architecture of the application, so I do not foresee a problem for anyone. Same goes for ZIO, I will not go deep into the ZIO basics, but I will have an overview where it is needed to make the talk be for everyone. I have been working with Scala for 3 years and using it together with ZIO in production for 2 years to build large scale applications and complex systems. I will also give the talk at the following meetup https://www.eventbrite.com/e/amsterdam-scala-matters-meet-up-tickets-1003400607227 We are in the process of making the application open source to share the code with everyone.
```
