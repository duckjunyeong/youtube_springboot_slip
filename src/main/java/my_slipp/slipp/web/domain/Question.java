package my_slipp.slipp.web.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "WRITER", nullable = false)
  private String writer;

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Column(name = "CONTENTS", nullable = false)
  private String contents;

  @Column(name = "CREATEDAT", nullable = false)
  private LocalDateTime createdAt;

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  @Override
  public String toString() {
    return "Question{" +
        "id=" + id +
        ", writer='" + writer + '\'' +
        ", title='" + title + '\'' +
        ", contents='" + contents + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;
    Question question = (Question) object;
    return Objects.equals(id, question.id) && Objects.equals(writer, question.writer) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(createdAt, question.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, writer, title, contents, createdAt);
  }
}
