package com.epam.domain;

import java.util.Objects;

public class VacancyRequest {
    private long id;
    private Answer answer;
    private Applicant applicant;
    private Vacancy vacancy;
    private String coveringLetter;

    public VacancyRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getCoveringLetter() {
        return coveringLetter;
    }

    public void setCoveringLetter(String coveringLetter) {
        this.coveringLetter = coveringLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyRequest that = (VacancyRequest) o;
        return id == that.id && answer == that.answer && Objects.equals(applicant, that.applicant) && Objects.equals(vacancy, that.vacancy) && Objects.equals(coveringLetter, that.coveringLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, applicant, vacancy, coveringLetter);
    }
}
