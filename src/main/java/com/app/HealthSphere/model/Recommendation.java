package com.app.HealthSphere.model;

import java.time.LocalDateTime;

public class Recommendation {
    private Long recommendationId;
    private Long userId;
    private String type;
    private String recommendedItem;
    private String reason;
    private String userFeedback;
    private LocalDateTime recommendedDate;

    public Recommendation() {}

    public Recommendation(Long recommendationId,Long userId, String type, String recommendedItem, String reason, String userFeedback) {
        this.recommendationId=recommendationId;
        this.userId = userId;
        this.type = type;
        this.recommendedItem = recommendedItem;
        this.reason = reason;
        this.userFeedback = userFeedback;
        this.recommendedDate = LocalDateTime.now();
    }

    public Long getRecommendationId() { return recommendationId; }
    public void setRecommendationId(Long recommendationId) { this.recommendationId = recommendationId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRecommendedItem() { return recommendedItem; }
    public void setRecommendedItem(String recommendedItem) { this.recommendedItem = recommendedItem; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getUserFeedback() { return userFeedback; }
    public void setUserFeedback(String userFeedback) { this.userFeedback = userFeedback; }

    public LocalDateTime getRecommendedDate() { return recommendedDate; }
    public void setRecommendedDate(LocalDateTime recommendedDate) { this.recommendedDate = recommendedDate; }
}
