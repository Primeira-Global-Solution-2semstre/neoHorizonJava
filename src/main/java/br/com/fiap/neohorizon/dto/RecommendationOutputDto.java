package br.com.fiap.neohorizon.dto;

import java.util.Date;

public class RecommendationOutputDto {
    Date recomemendedLaunchDate;
    Float riskScore;

    public Date getRecomemendedLaunchDate() {
        return recomemendedLaunchDate;
    }

    public void setRecomemendedLaunchDate(Date recomemendedLaunchDate) {
        this.recomemendedLaunchDate = recomemendedLaunchDate;
    }

    public Float getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Float riskScore) {
        this.riskScore = riskScore;
    }
}
