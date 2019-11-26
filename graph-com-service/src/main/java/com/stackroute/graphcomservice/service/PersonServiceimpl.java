package com.stackroute.graphcomservice.service;

import com.stackroute.graphcomservice.model.*;
import com.stackroute.graphcomservice.repository.GraphComRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class PersonServiceimpl implements PersonService {


    @Autowired
    GraphComRepository graphComRepository;

    @Override
    public Person savePerson(Person person,String gender) {
        return graphComRepository.createPerson(person,gender);
    }

    @Override
    public Person setLabel(Person person, String gender) {
        return graphComRepository.setLabel(person,gender);
    }


    @Override
    public Person setLabel1(Person person, String gender) {
        return graphComRepository.setLabel1(person,gender);
    }

    @Override
    public Religion saveReligion(Religion religion){
        return graphComRepository.createReligion(religion);
    }

    @Override
    public Manglik saveManglik(Manglik manglik) {
        return graphComRepository.createManglik(manglik);
    }

    @Override
    public CurrentCity saveCurrentCity(CurrentCity currentCity) {
        return graphComRepository.createCurrentCity(currentCity);
    }

    @Override
    public Language saveLanguage(Language language) {
        return graphComRepository.createLanguage(language);
    }

    @Override
    public NativeCity saveNativeCity(NativeCity nativeCity) {
        return graphComRepository.createNativeCity(nativeCity);
    }

    @Override
    public Hobbies saveHobbies(Hobbies hobbies) {
        return graphComRepository.createHobbies(hobbies);
    }

    @Override
    public Interests saveInterests(Interests interests) {
        return graphComRepository.createInterests(interests);
    }

    @Override
    public AgeRange saveAgeRange(AgeRange ageRange) {
        return graphComRepository.createAgeRange(ageRange);
    }

    @Override
    public HeightRange saveHeightRange(HeightRange heightRange) {
        return graphComRepository.createHeightRange(heightRange);
    }

    @Override
    public Drink saveDrink(Drink drink) {
        return graphComRepository.createDrink(drink);
    }

    @Override
    public Smoke saveSmoke(Smoke smoke) {
        return graphComRepository.createSmoke(smoke);
    }

    @Override
    public Challenged saveChallenged(Challenged challenged) {
        return graphComRepository.createChallenged(challenged);
    }

    @Override
    public Diet saveDiet(Diet diet) {
        return graphComRepository.createDiet(diet);
    }

    @Override
    public Marital saveMarital(Marital marital) {
        return graphComRepository.createMarital(marital);
    }

    @Override
    public Profession saveProfession(Profession profession) {
        return graphComRepository.createProfession(profession);
    }

    @Override
    public Education saveEducation(Education education) {
        return graphComRepository.createEducation(education);
    }

    @Override
    public Company saveCompany(Company company) {
        return graphComRepository.createCompany(company);
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return graphComRepository.createExperience(experience);
    }

    @Override
    public IncomeRange saveIncomeRange(IncomeRange incomeRange) {
        return graphComRepository.createIncomeRange(incomeRange);
    }

    @Override
    public Field saveField(Field field) {
        return graphComRepository.createField(field);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return graphComRepository.createInstitution(institution);
    }


    @Override
    public Collection<String> PersonReligion(Person person, Religion religion) {
        return graphComRepository.PersonReligion(person,religion);
    }

    @Override
    public Collection<String> PersonManglik(String email, Manglik manglik) {
        return graphComRepository.PersonManglik(email,manglik);
    }

    @Override
    public Collection<String> PersonLanguage(Person person, Language language) {
        return graphComRepository.PersonLanguage(person,language);
    }

    @Override
    public Collection<String> PersonCCity(Person person, CurrentCity currentCity) {
        return graphComRepository.PersonCCity(person,currentCity);
    }

    @Override
    public Collection<String> PersonnNCity(Person person, NativeCity nativeCity) {
        return graphComRepository.PersonNCity(person,nativeCity);
    }

    @Override
    public Collection<String> PersonHobbies(Person person, Hobbies hobbies) {
        return graphComRepository.PersonHobbies(person,hobbies);
    }

    @Override
    public Collection<String> PersonInterests(Person person, Interests interests) {
        return graphComRepository.PersonInterests(person, interests);
    }

    @Override
    public Collection<String> PersonAgeRange(Person person, AgeRange ageRange) {
        return graphComRepository.PersonAgeRange(person,ageRange);
    }

    @Override
    public Collection<String> PersonHeightRange(Person person, HeightRange heightRange, float height) {
        return graphComRepository.PersonHeightRange(person,heightRange,height);
    }

    @Override
    public Collection<String> PersonDrink(Person person, Drink drink) {
        return graphComRepository.PersonDrink(person, drink);
    }

    @Override
    public Collection<String> PersonSmoke(Person person, Smoke smoke) {
        return graphComRepository.PersonSmoke(person, smoke);
    }

    @Override
    public Collection<String> PersonChallenged(Person person, Challenged challenged) {
        return graphComRepository.PersonChallenged(person,challenged);
    }

    @Override
    public Collection<String> PersonDiet(Person person, Diet diet) {
        return graphComRepository.PersonDiet(person,diet);
    }

    @Override
    public Collection<String> PersonMarital(Person person, Marital marital) {
        return graphComRepository.PersonMarital(person,marital);
    }

    @Override
    public Collection<String> PersonProfession(String email, Profession profession) {
        return graphComRepository.PersonProfession(email,profession);
    }

    @Override
    public Collection<String> PersonEducation(String email, Education education) {
        return graphComRepository.PersonEducation(email,education);
    }

    @Override
    public Collection<String> PersonCompany(Profession profession, Company company) {
        return graphComRepository.PersonCompany(profession,company);
    }

    @Override
    public Collection<String> PersonExperience(Profession profession, Experience experience ) {
        return graphComRepository.PersonExperience(profession,experience);
    }

    @Override
    public Collection<String> PersonIncomeRange(Profession profession, IncomeRange incomeRange, float income) {
        return graphComRepository.PersonIncomeRange(profession,incomeRange,income);
    }

    @Override
    public Collection<String> PersonField(Education education, Field field) {
        return graphComRepository.PersonField(education,field);
    }

    @Override
    public Collection<String> PersonInstitution(Education education,Institution institution) {
        return graphComRepository.PersonInstitution(education,institution);
    }


}
