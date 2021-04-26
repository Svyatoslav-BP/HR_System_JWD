package com.epam.domain;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Applicant extends Account{
private String resume;
private Map<Skill,Integer> skillSet;
private Set<VacancyRequest> vacancyRequests;

    public Applicant(long id, String login, String email, String password) {
        super(id, login, email, password);
    }

    public Applicant() {
    }

    public Set<VacancyRequest> getVacancyRequests() {
        return vacancyRequests;
    }

    public void setVacancyRequests(Set<VacancyRequest> vacancyRequests) {
        this.vacancyRequests = vacancyRequests;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Map<Skill, Integer> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Map<Skill, Integer> skillSet) {
        this.skillSet = skillSet;
    }

    public Applicant(long id, String login, String email, String password, String resume, Map<Skill, Integer> skillSet,Set<VacancyRequest> vacancyRequests) {
        super(id, login, email, password);
        this.resume = resume;
        this.skillSet = skillSet;
        this.vacancyRequests = vacancyRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(resume, applicant.resume) && Objects.equals(skillSet, applicant.skillSet) && Objects.equals(vacancyRequests, applicant.vacancyRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), resume, skillSet, vacancyRequests);
    }
}
