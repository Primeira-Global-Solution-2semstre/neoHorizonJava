package br.com.fiap.neohorizon.dto;

public class PredictionInputDto {
    Long periodo;
    String spaceObjectTag;

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public String getSpaceObjectTag() {
        return spaceObjectTag;
    }

    public void setSpaceObjectTag(String spaceObjectTag) {
        this.spaceObjectTag = spaceObjectTag;
    }
}
