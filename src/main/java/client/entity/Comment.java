package client.entity;

import java.util.Objects;

public class Comment {
    private Integer id;
    private Integer productId;
    private Long userId;
    private String commentText;

    public Comment(Integer id, Integer productId, Long userId, String commentText) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(productId, comment.productId) &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(commentText, comment.commentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, commentText);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(",productId=").append(productId);
        sb.append(",userId=").append(userId);
        sb.append(",commentText=").append(commentText);
        return sb.toString();
    }
}
