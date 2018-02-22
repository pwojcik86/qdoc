package com.wikia.qdoc.services.qdoc.flow.qdocnumber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QDocNumberGeneratorDatePolicy implements QDocNumberGeneratorPolicy {

  private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Override
  public QDocNumber generate(LocalDateTime createdAt) {
    return new QDocNumber(createdAt.format(FORMAT));
  }
}
