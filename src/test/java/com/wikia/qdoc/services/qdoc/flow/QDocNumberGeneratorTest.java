package com.wikia.qdoc.services.qdoc.flow;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wikia.qdoc.services.qdoc.flow.qdocnumber.QDocNumberGenerator;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class QDocNumberGeneratorTest {

  private CurrentUserProvider mock;

  @Before
  public void setup() {
    this.mock = mock(CurrentUserProvider.class);
  }

  @Test
  public void basicNumber() {
    when(mock.isAuditor()).thenReturn(false);
    QDocNumberGenerator generator = new QDocNumberGenerator(mock, false);

    LocalDateTime now = LocalDateTime.of(2000, 12, 28, 0, 0, 0);

    String generate = generator.generate("ISO", now).getValue();
    assertThat(generate).isEqualTo("ISO/28-12-2000");
  }

  @Test
  public void basicNumberForAuditor() {
    when(mock.isAuditor()).thenReturn(true);
    QDocNumberGenerator generator = new QDocNumberGenerator(mock, false);
    LocalDateTime now = LocalDateTime.of(2000, 12, 28, 0, 0, 0);

    String generate = generator.generate("ISO", now).getValue();
    assertThat(generate).isEqualTo("ISO/28-12-2000/AUDIT");
  }

  @Test
  public void basicNumberForDemo() {
    when(mock.isAuditor()).thenReturn(false);
    QDocNumberGenerator generator = new QDocNumberGenerator(mock, true);
    LocalDateTime now = LocalDateTime.of(2000, 12, 28, 0, 0, 0);

    String generate = generator.generate("ISO", now).getValue();
    assertThat(generate).isEqualTo("DEMO/ISO/28-12-2000");
  }

  @Test
  public void basicNumberForDemoAudit() {
    when(mock.isAuditor()).thenReturn(true);
    QDocNumberGenerator generator = new QDocNumberGenerator(mock, true);
    LocalDateTime now = LocalDateTime.of(2000, 12, 28, 0, 0, 0);

    String generate = generator.generate("ISO", now).getValue();
    assertThat(generate).isEqualTo("DEMO/ISO/28-12-2000/AUDIT");
  }

}