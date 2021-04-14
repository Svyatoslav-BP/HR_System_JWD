package com.epam.entity;

public class VacancyRequest {
    private long id;
    private Answer answer;
    private Applicant applicant;
    private Vacancy vacancy;
    private String coveringLetter;

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

    public VacancyRequest() {
    }
}
