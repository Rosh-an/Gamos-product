package com.stackroute.graphcomservice.controller;


import com.stackroute.graphcomservice.model.*;
import com.stackroute.graphcomservice.service.PersonService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**creates a controller for graph com service */
@RestController
@RequestMapping("/rest/neo4j/person")
public class GraphComController {
    private static final Logger logger = LoggerFactory.getLogger(GraphComController.class);

    private PersonService personService;

    @Autowired
    public GraphComController(PersonService personService) {
        this.personService = personService;
    }
    /**for saving DTO */

    public ResponseEntity<?> saveDTO(DTO dto)
    {
        Person person=new Person();
        Hobbies hobbies=new Hobbies();
        Challenged challenged= new Challenged();
        CurrentCity currentCity= new CurrentCity();
        NativeCity nativeCity=new NativeCity();
        Diet diet=new Diet();
        Drink drink=new Drink();
        Smoke smoke=new Smoke();
        Interests interests=new Interests();
        Language language=new Language();
        Religion religion=new Religion();
        Marital marital=new Marital();
        HeightRange heightRange=new HeightRange();
        AgeRange ageRange=new AgeRange();
        Company company = new Company();
        Experience experience = new Experience();
        IncomeRange incomeRange = new IncomeRange();
        Profession profession=new Profession();
        Education education= new Education();
        Field field= new Field();
        Institution institution= new Institution();
        Manglik manglik = new Manglik();



        /**
         *  PERSONAL
        */

        if(dto.getPayloadDTO().isHasPersonal()) {
            person.setFname(dto.getPayloadDTO().getPersonalDTO().getFirstname());
            person.setLname(dto.getPayloadDTO().getPersonalDTO().getLastname());
            person.setName(person.getFname() + person.getLname());
            person.setNumber(dto.getPayloadDTO().getPersonalDTO().getMobileNumber());
            person.setGender(dto.getPayloadDTO().getPersonalDTO().getGender());
            person.setDOB(dto.getPayloadDTO().getPersonalDTO().getDateOfBirth());
            person.setMotherTongue(dto.getPayloadDTO().getPersonalDTO().getMotherTongue());
            person.setWeight(dto.getPayloadDTO().getPersonalDTO().getWeight());
            person.setFatherName(dto.getPayloadDTO().getPersonalDTO().getFathername());
            person.setFatherOccupation(dto.getPayloadDTO().getPersonalDTO().getFatheroccupation());
            person.setMotherName(dto.getPayloadDTO().getPersonalDTO().getMothername());
            person.setMotherOccupation(dto.getPayloadDTO().getPersonalDTO().getMotheroccupation());
            person.setElderBrother(dto.getPayloadDTO().getPersonalDTO().getElderbrother());
            person.setElderSister(dto.getPayloadDTO().getPersonalDTO().getEldersister());
            person.setYoungerBrother(dto.getPayloadDTO().getPersonalDTO().getYoungbrother());
            person.setYoungerSister(dto.getPayloadDTO().getPersonalDTO().getYoungsister());
            person.setEmail(dto.getEmail());
            person.setMotherTongue(dto.getPayloadDTO().getPersonalDTO().getMotherTongue());


            ageRange.setAgeRange("22-25");

            if(dto.getPayloadDTO().getPersonalDTO().getHeight()>=4 && dto.getPayloadDTO().getPersonalDTO().getHeight()<4.5)
            {
                heightRange.setHeightRange("4-4.5");
            }
            if(dto.getPayloadDTO().getPersonalDTO().getHeight()>=4.5 && dto.getPayloadDTO().getPersonalDTO().getHeight()<5)
            {
                heightRange.setHeightRange("4.5-5");
            }
            if(dto.getPayloadDTO().getPersonalDTO().getHeight()>=5 && dto.getPayloadDTO().getPersonalDTO().getHeight()<5.5)
            {
                heightRange.setHeightRange("5-5.5");
            }
            if(dto.getPayloadDTO().getPersonalDTO().getHeight()>=5.5 && dto.getPayloadDTO().getPersonalDTO().getHeight()<6)
            {
                heightRange.setHeightRange("5.5-6");
            }
            if(dto.getPayloadDTO().getPersonalDTO().getHeight()>=6)
            {
                heightRange.setHeightRange("6+");
            }

            hobbies.setHobbies(dto.getPayloadDTO().getPersonalDTO().getHobbies());
            challenged.setChallenged(dto.getPayloadDTO().getPersonalDTO().getChallenged());
            currentCity.setCurrentCity(dto.getPayloadDTO().getPersonalDTO().getCurrentcity());
            nativeCity.setNativeCity(dto.getPayloadDTO().getPersonalDTO().getNativecity());
            diet.setDiet(dto.getPayloadDTO().getPersonalDTO().getDiet());
            drink.setDrink(dto.getPayloadDTO().getPersonalDTO().getDrink());
            interests.setInterests(dto.getPayloadDTO().getPersonalDTO().getInterests());
            language.setLanguage(dto.getPayloadDTO().getPersonalDTO().getLanguage());
            religion.setReligion(dto.getPayloadDTO().getPersonalDTO().getReligion());
            smoke.setSmoke(dto.getPayloadDTO().getPersonalDTO().getSmoke());
            marital.setMaritalStatus(dto.getPayloadDTO().getPersonalDTO().getMarital());


            person=personService.savePerson(person,person.getGender());
            religion=personService.saveReligion(religion);
            ageRange=personService.saveAgeRange(ageRange);
            currentCity=personService.saveCurrentCity(currentCity);
            challenged=personService.saveChallenged(challenged);
            diet=personService.saveDiet(diet);
            drink=personService.saveDrink(drink);
            heightRange=personService.saveHeightRange(heightRange);
            hobbies=personService.saveHobbies(hobbies);
            interests=personService.saveInterests(interests);
            language=personService.saveLanguage(language);
            marital=personService.saveMarital(marital);
            nativeCity=personService.saveNativeCity(nativeCity);
            smoke=personService.saveSmoke(smoke);


            personService.PersonCCity(person,currentCity);
            personService.PersonAgeRange(person,ageRange);
            personService.PersonChallenged(person,challenged);
            personService.PersonDiet(person,diet);
            personService.PersonDrink(person,drink);
            personService.PersonHeightRange(person,heightRange,dto.getPayloadDTO().getPersonalDTO().getHeight());
            personService.PersonHobbies(person,hobbies);
            personService.PersonInterests(person,interests);
            personService.PersonLanguage(person,language);
            personService.PersonMarital(person,marital);
            personService.PersonnNCity(person,nativeCity);
            personService.PersonReligion(person,religion);
            personService.PersonSmoke(person,smoke);

            if(person.getGender().equalsIgnoreCase("male"))
            {

                personService.setLabel(person,person.getGender());

            }
            else if(person.getGender().equalsIgnoreCase("female"))
            {
                personService.setLabel1(person,person.getGender());
            }

        }




         /**
           *     PROFESSIONAL
        */
        if(dto.getPayloadDTO().isHasProfessional()) {
            company.setCompanyName(dto.getPayloadDTO().getProfessionalDTO().getCurrentcompany());
            experience.setWorkExperience(dto.getPayloadDTO().getProfessionalDTO().getExperience());
            if(dto.getPayloadDTO().getProfessionalDTO().getIncome()<5)
            {
                incomeRange.setIncomeRange("Below 5");
            }
            if(dto.getPayloadDTO().getProfessionalDTO().getIncome()>=5 && dto.getPayloadDTO().getProfessionalDTO().getIncome()<10)
            {
                incomeRange.setIncomeRange("5-10");
            }
            if(dto.getPayloadDTO().getProfessionalDTO().getIncome()>=10 && dto.getPayloadDTO().getProfessionalDTO().getIncome()<15)
            {
                incomeRange.setIncomeRange("10-15");
            }
            if(dto.getPayloadDTO().getProfessionalDTO().getIncome()>=15 && dto.getPayloadDTO().getProfessionalDTO().getIncome()<20)
            {
                incomeRange.setIncomeRange("15-20");
            }
            if(dto.getPayloadDTO().getProfessionalDTO().getIncome()>=20)
            {
                incomeRange.setIncomeRange("Above 20");
            }

            profession.setProfession(dto.getPayloadDTO().getProfessionalDTO().getProfession());
            profession.setLinkedIn(dto.getPayloadDTO().getProfessionalDTO().getLinkedin());

            company=personService.saveCompany(company);
            experience=personService.saveExperience(experience);
            incomeRange=personService.saveIncomeRange(incomeRange);
            profession=personService.saveProfession(profession);

            personService.PersonCompany(profession,company);
            personService.PersonExperience(profession,experience);
            personService.PersonIncomeRange(profession,incomeRange,dto.getPayloadDTO().getProfessionalDTO().getIncome());
            personService.PersonProfession(dto.getEmail(),profession);
        }

          /**
           *     EDUCATION
        */
        if(dto.getPayloadDTO().isHasEducation()){


            education.setHighestEducation(dto.getPayloadDTO().getEducationDTO().getEducation());
            education.setPassingYear(dto.getPayloadDTO().getEducationDTO().getPassingyear());

            field.setCourseName(dto.getPayloadDTO().getEducationDTO().getField());

            institution.setInstitutionName(dto.getPayloadDTO().getEducationDTO().getCollege());

            education=personService.saveEducation(education);
            field=personService.saveField(field);
            institution=personService.saveInstitution(institution);

           logger.info(education.getHighestEducation());
            personService.PersonEducation(dto.getEmail(),education);
            personService.PersonField(education,field);
            personService.PersonInstitution(education,institution);

        }


          /**
            *    HOROSCOPE
        */

        if(dto.getPayloadDTO().isHasHoroscope()) {
            manglik.setManglik(dto.getPayloadDTO().getHoroscopeDTO().getManglik());
            manglik=personService.saveManglik(manglik);

            personService.PersonManglik(dto.getEmail(),manglik);
        }

       logger.info("from post mapping");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
