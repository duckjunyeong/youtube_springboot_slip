package my_slipp.slipp.web.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Answer {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "WRITER", nullable = false)
  private String writer;

  @Column(name = "CONTENTS", nullable = false)
  private String contents;

  @Column(name = "CREATEDAT", nullable = false)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "qestion_id", nullable = false)
  private Question question;

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @Override
  public String toString() {
    return "Answer{" +
        "writer='" + writer + '\'' +
        ", contents='" + contents + '\'' +
        ", createdAt=" + createdAt +
        ", question=" + question +
        '}';
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;
    Answer answer = (Answer) object;
    return Objects.equals(writer, answer.writer) && Objects.equals(contents, answer.contents) && Objects.equals(createdAt, answer.createdAt) && Objects.equals(question, answer.question);
  }

  @Override
  public int hashCode() {
    return Objects.hash(writer, contents, createdAt, question);
  }
}
