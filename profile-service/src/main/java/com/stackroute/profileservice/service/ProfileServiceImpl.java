package com.stackroute.profileservice.service;

import com.stackroute.profileservice.model.*;
import com.stackroute.profileservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;
    private MongoTemplate mongoTemplate;
    private UserIdListRepository userIdListRepository;
    private AgeRepository ageRepository;
    private CityRepository cityRepository;
    private ReligionRepository religionRepository;



    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository,MongoTemplate mongoTemplate,UserIdListRepository userIdlist,AgeRepository age,CityRepository city,ReligionRepository rel) {
        this.profileRepository = profileRepository;
        this.mongoTemplate=mongoTemplate;
        this.userIdListRepository=userIdlist;
        this.ageRepository=age;
        this.cityRepository=city;
        this.religionRepository=rel;

    }
    /**for updating profile details */
    @Override
    public ProfileDetails updateProfileDetails(ProfileDetails profileDetails)
    {
        return profileRepository.save(profileDetails);
    }

    /**for saving profile details */
    @Override
    public ProfileDetails saveProfileDetails(ProfileDetails profileDetails) {
            profileDetails.setRecommended_list(new ArrayList<>());
            profileDetails.setLiked_profiles(new ArrayList<>());
            profileDetails.setAccepted_requests(new ArrayList<>());
            profileDetails.setLiked_by_profiles(new ArrayList<>());
            profileDetails.setReceived_requests(new ArrayList<>());
            profileDetails.setSent_requests(new ArrayList<>());
            profileDetails.setDisliked_profiles(new ArrayList<>());

        ProfileDetails profile = profileRepository.save(profileDetails);

         addtoUserIdList(profile);
         addtoAgeList(profile);
         addtoCityList(profile);
         addtoReligionList(profile);
        return profile;
    }
 static final String F="female";
    static final String R="religion";
    /**for searching by parameter */
    @Override
    public List<ProfileDetails> searchbyParam(String str) {
        int switchVal=-1;
        String[] split = str.split(" ");
        if(split[0].equals("bride")){
            split[0]=F;
        }else{split[0]="male";}

        if(str.contains("age") && !(str.contains("city")) && !(str.contains(R)))
        {
            if(split.length==3){
                switchVal=1;

            }
            else if(split.length==4){
                switchVal=2;
            }
        }

        else if(!(str.contains("age")) && str.contains("city") && !(str.contains(R)))//contains only city
        {
            if(split.length==3){switchVal =3;}
        }
        else if(!(str.contains("age")) && !(str.contains("city")) && (str.contains(R)))//contains only religion
        {
            if(split.length==3){switchVal =6;}
        }



        else if(str.contains("age") && str.contains("city"))
        {
            if(split.length==5)
            {
                switchVal=4;
            }
            else if(split.length==6)
            {
                switchVal=5;
            }
            if(str.contains(R))
            {
              if(split.length==8)
              {
                  switchVal=9;
              }
              else if(split.length==7)
              {
                  switchVal=8;
              }
            }
        }
        else if (str.contains("age") && str.contains(R) &&!(str.contains("city")))
        {
            if(split.length==5)
            {
                switchVal=10;
            }
            else if(switchVal==6)
            {
                switchVal=7;
            }
        }
        else if (str.contains("city") && str.contains(R) && !(str.contains("age")) &&split.length==5)
        {
                switchVal=11;


        }

        List<Long> list=new ArrayList<>();
        List<AgeList> ageList;
        List<Long> ageIdList;
        List<CityList> cityList;
        List<Long> cityIdList;
        List<ReligionList> religionList;
        List<Long> religionIdList;
        List<UserIdList> userIdList=userIdListRepository.updateFunc(1);
        List<Long> groomIdList = userIdList.get(0).getGroomidsList();
        List<Long> brideIdList = userIdList.get(0).getBrideidsList();

        switch (switchVal)
        {
            case 1:
                ageList=ageRepository.getAgeList(split[2]);
                if(!ageList.isEmpty()){
                ageIdList = ageList.get(0).getIdList();
                  if(split[0].equals("male"))
                  {
                      for (Long aLong : ageIdList) {
                          if (groomIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }else if (split[0].equals(F))
                  {
                      for (Long aLong : ageIdList) {
                          if (brideIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }}
                break;

            case 2:
                  int age1 = Integer.parseInt(split[2]);
                  int age2 = Integer.parseInt(split[3]);
                  List<Long> appendageIdList = new ArrayList<>();
                  while(age1<=age2) {
                      ageList = ageRepository.getAgeList(String.valueOf(age1));
                      if(!ageList.isEmpty())
                      {
                      ageIdList = ageList.get(0).getIdList();
                      appendageIdList.addAll(ageIdList);}
                      age1++;
                  }
                  if(!appendageIdList.isEmpty()){
                  if(split[0].equals("male"))
                  {
                      for (Long aLong : appendageIdList) {
                          if (groomIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }else if (split[0].equals(F))
                  {
                      for (Long aLong : appendageIdList) {
                          if (brideIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }}
                break;

            case 3:
                  cityList= cityRepository.getCityList(split[2]);
                  if(!cityList.isEmpty()){
                      cityIdList = cityList.get(0).getIdList();
                  if(split[0].equals("male"))
                  {
                      for (Long aLong : cityIdList) {
                          if (groomIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }else if (split[0].equals(F))
                  {
                      for (Long aLong : cityIdList) {
                          if (brideIdList.contains(aLong)) {
                              list.add(aLong);
                          }
                      }
                  }}
                break;

            case 4:
                  cityList=cityRepository.getCityList(split[4]);
                  ageList=ageRepository.getAgeList(split[2]);
                  if(!cityList.isEmpty() && !ageList.isEmpty())
                  {
                      cityIdList=cityList.get(0).getIdList();
                      ageIdList=ageList.get(0).getIdList();
                      Set<Long> appendSet= new LinkedHashSet<>(ageIdList);
                      appendSet.retainAll(cityIdList);
                      List<Long> appendIdList = new ArrayList<>(appendSet);
                      if(split[0].equals("male"))
                      {
                          for (Long aLong : appendIdList) {
                              if (groomIdList.contains(aLong)) {
                                  list.add(aLong);
                              }
                          }
                      }else if(split[0].equals(F))
                      {
                          for (Long aLong : appendIdList) {
                              if (brideIdList.contains(aLong)) {
                                  list.add(aLong);
                              }
                          }
                      }
                  }
                break;

            case 5:
                cityList=cityRepository.getCityList(split[5]);
                if(!cityList.isEmpty())
                {
                    cityIdList = cityList.get(0).getIdList();
                    int agestart = Integer.parseInt(split[2]);
                    int ageend = Integer.parseInt(split[3]);
                    List<Long> appendageIdList1 = new ArrayList<>();
                    while(agestart<=ageend) {
                        ageList = ageRepository.getAgeList(String.valueOf(agestart));
                        if(!ageList.isEmpty())
                        {
                            ageIdList = ageList.get(0).getIdList();
                            appendageIdList1.addAll(ageIdList);}
                        agestart++;
                    }
                    Set<Long> appendSet = new LinkedHashSet<>(appendageIdList1);
                    appendSet.retainAll(cityIdList);
                    List<Long> appendIdList1 = new ArrayList<>(appendSet);
                    if(split[0].equals("male"))
                    {
                        for (Long aLong : appendIdList1) {
                            if (groomIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }else if(split[0].equals(F))
                    {
                        for (Long aLong : appendIdList1) {
                            if (brideIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }
                }

                break;
            case 6:
                religionList = religionRepository.getReligionList(split[2]);
                if(!religionList.isEmpty()) {
                    religionIdList = religionList.get(0).getIdList();
                    if(split[0].equals("male"))
                    {
                        for (Long aLong : religionIdList) {
                            if (groomIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }else if (split[0].equals(F))
                    {
                        for (Long aLong : religionIdList) {
                            if (brideIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }
                }
                break;
            case 7:
                religionList= religionRepository.getReligionList(split[5]);
                if(!religionList.isEmpty())
                {
                    religionIdList=religionList.get(0).getIdList();
                    int agestart = Integer.parseInt(split[2]);
                    int ageend = Integer.parseInt(split[3]);
                    List<Long> appendageIdList1 = new ArrayList<>();
                    while(agestart<=ageend) {
                        ageList = ageRepository.getAgeList(String.valueOf(agestart));
                        if(!ageList.isEmpty())
                        {
                            ageIdList = ageList.get(0).getIdList();
                            appendageIdList1.addAll(ageIdList);}
                        agestart++;
                    }
                    Set<Long> appendSet = new LinkedHashSet<>(appendageIdList1);
                    appendSet.retainAll(religionIdList);
                    List<Long> appendIdList1 = new ArrayList<>(appendSet);
                    if(split[0].equals("male"))
                    {
                        for (Long aLong : appendIdList1) {
                            if (groomIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }else if(split[0].equals(F))
                    {
                        for (Long aLong : appendIdList1) {
                            if (brideIdList.contains(aLong)) {
                                list.add(aLong);
                            }
                        }
                    }
                }
                break;
            case 8:
                religionList=religionRepository.getReligionList(split[6]);
                if(!religionList.isEmpty())
                {
                    religionIdList=religionList.get(0).getIdList();
                    cityList=cityRepository.getCityList(split[4]);
                    if(!cityList.isEmpty()){
                        cityIdList=cityList.get(0).getIdList();
                        ageList=ageRepository.getAgeList(split[2]);
                        if(!ageList.isEmpty())
                        {
                            ageIdList=ageList.get(0).getIdList();
                            Set<Long> appendIdSet = new LinkedHashSet<>(ageIdList);
                            appendIdSet.retainAll(cityIdList);
                            appendIdSet.retainAll(religionIdList);
                            List<Long> appendIdList1 = new ArrayList<>(appendIdSet);
                            if(split[0].equals("male"))
                            {
                                for (Long aLong : appendIdList1) {
                                    if (groomIdList.contains(aLong)) {
                                        list.add(aLong);
                                    }
                                }
                            }else if(split[0].equals(F))
                            {
                                for (Long aLong : appendIdList1) {
                                    if (brideIdList.contains(aLong)) {
                                        list.add(aLong);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 9:
                religionList = religionRepository.getReligionList(split[7]);
                if(!religionList.isEmpty())
                {
                    religionIdList=religionList.get(0).getIdList();
                    cityList = cityRepository.getCityList(split[5]);
                    if(!cityList.isEmpty())
                    {
                        cityIdList = cityList.get(0).getIdList();
                        int agestart = Integer.parseInt(split[2]);
                        int ageend = Integer.parseInt(split[3]);
                        List<Long> appendageIdList1 = new ArrayList<>();
                        while(agestart<=ageend) {
                            ageList = ageRepository.getAgeList(String.valueOf(agestart));
                            if(!ageList.isEmpty())
                            {
                                ageIdList = ageList.get(0).getIdList();
                                appendageIdList1.addAll(ageIdList);}
                            agestart++;
                        }
                        Set<Long> appendIdSet = new LinkedHashSet<>(appendageIdList1);
                        /////here to intersection
                        appendIdSet.retainAll(cityIdList);
                        appendIdSet.retainAll(religionIdList);
                        List<Long> appendIdList1 = new ArrayList<>(appendIdSet);
                        if(split[0].equals("male"))
                        {
                            for (Long aLong : appendIdList1) {
                                if (groomIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }else if(split[0].equals(F))
                        {
                            for (Long aLong : appendIdList1) {
                                if (brideIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }
                    }
                }
                break;
            case 10:
                religionList = religionRepository.getReligionList(split[4]);
                if(!religionList.isEmpty())
                {
                    religionIdList=religionList.get(0).getIdList();
                    ageList= ageRepository.getAgeList(split[2]);
                    if(!ageList.isEmpty())
                    {
                        ageIdList=ageList.get(0).getIdList();
                        Set<Long> appendSet = new LinkedHashSet<>(ageIdList);
                        appendSet.retainAll(religionIdList);
                        List<Long> appendIdList1 = new ArrayList<>(appendSet);
                        if(split[0].equals("male"))
                        {
                            for (Long aLong : appendIdList1) {
                                if (groomIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }else if(split[0].equals(F))
                        {
                            for (Long aLong : appendIdList1) {
                                if (brideIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }
                    }
                }
                break;
            case 11:
                religionList = religionRepository.getReligionList(split[4]);
                if(!religionList.isEmpty()) {
                    religionIdList = religionList.get(0).getIdList();
                    cityList = cityRepository.getCityList(split[2]);
                    if(!cityList.isEmpty())
                    {
                        cityIdList=cityList.get(0).getIdList();
                        Set<Long> appendSet = new LinkedHashSet<>(religionIdList);
                        appendSet.retainAll(cityIdList);
                        List<Long> appendIdList1 = new ArrayList<>(appendSet);
                        if(split[0].equals("male"))
                        {
                            for (Long aLong : appendIdList1) {
                                if (groomIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }else if(split[0].equals(R))
                        {
                            for (Long aLong : appendIdList1) {
                                if (brideIdList.contains(aLong)) {
                                    list.add(aLong);
                                }
                            }
                        }
                    }
                }
                break;
            default:
                list=null;
                break;
        }
        if(list!=null)
        {
            List<ProfileDetails> finalList = new ArrayList<>();
            for (Long aLong : list) {
                finalList.add(profileRepository.findByIdCustom(aLong));
            }
            return finalList;
        }
       return new ArrayList<>();
    }

    /**for getting one's profile */
    @Override
    public ProfileDetails getMyProfile(String email) {
        try {
            return profileRepository.findByEmail(email).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**for list of profiles */
    @Override
    public List<ProfileDetails> getProfiles(List<String> list) {
        List<ProfileDetails> finalList = new ArrayList<>();
        ProfileDetails profileDetails;
        if(list!=null){
            for (String s : list) {
                profileDetails = getMyProfile(s);
                if (profileDetails != null) {
                    finalList.add(profileDetails);
                }
            }
        return finalList;}
        return new ArrayList<>();
    }
/**for adding to user id list*/
    @Override
    public void addtoUserIdList(ProfileDetails profile)
    {
        if(userIdListRepository.updateFunc(1).isEmpty())
        {
            userIdListRepository.save(new UserIdList());
        }
        if(profile.getBasicPersonalDetails().getGender().equalsIgnoreCase("male"))
        {
            List<UserIdList> groomOne= userIdListRepository.updateFunc(1);
            List<Long> idslist = groomOne.get(0).getGroomidsList();
            if(idslist==null){
                idslist= new ArrayList<>();
            }
            idslist.add(profile.getId());
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(1));
            Update update = new Update();
            update.set("groomidsList",idslist);
            mongoTemplate.updateFirst(query,update, UserIdList.class);
        }
        else if(profile.getBasicPersonalDetails().getGender().equalsIgnoreCase(F))
        {
            List<UserIdList> groomOne= userIdListRepository.updateFunc(1);
            List<Long> idslist = groomOne.get(0).getBrideidsList();
            if(idslist==null){
                idslist= new ArrayList<>();
            }
            idslist.add(profile.getId());
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(1));
            Update update = new Update();
            update.set("brideidsList",idslist);
            mongoTemplate.updateFirst(query,update, UserIdList.class);
        }
    }

    /**for adding or modifying subscription */
    @Override
    public void addModifySubscription(String subscription, String email) {
       ProfileDetails profileDetails= profileRepository.findByEmail(email).get(0);
       profileDetails.setHasSubscribed(true);
       profileDetails.setSubscriptionPlan(subscription);
       profileRepository.save(profileDetails);
    }
/**for deleting one's profile */
    @Override
    public void deleteMyProfile(String email) {
        ProfileDetails profileDetails= profileRepository.findByEmail(email).get(0);
        profileRepository.delete(profileDetails);
    }
    static final String L="idList";
/**for adding to age list */
    @Override
    public void addtoAgeList(ProfileDetails profile)
    {
        List<AgeList> ageList = ageRepository.getAgeList(profile.getBasicPersonalDetails().getAge());
        if(ageList.isEmpty())
        {
            AgeList temp = new AgeList();
            temp.setAge(profile.getBasicPersonalDetails().getAge());
            List<Long> tempList = new ArrayList<>();
            tempList.add(profile.getId());
            temp.setIdList(tempList);
            ageRepository.save(temp);
        }
        else
        {
            AgeList temp = ageList.get(0);
            List<Long> tempLong = temp.getIdList();
            tempLong.add(profile.getId());

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(profile.getBasicPersonalDetails().getAge()));
            Update update = new Update();
            update.set(L,tempLong);
            mongoTemplate.updateFirst(query,update, AgeList.class);
        }
    }

    /**for adding to city list */
    @Override
    public void addtoCityList(ProfileDetails profile)
    {
        List<CityList> cityList = cityRepository.getCityList(profile.getBasicPersonalDetails().getCity());
        if(cityList.isEmpty())
        {
            CityList temp = new CityList();
            temp.setCity(profile.getBasicPersonalDetails().getCity());
            List<Long> tempList = new ArrayList<>();
            tempList.add(profile.getId());
            temp.setIdList(tempList);
            cityRepository.save(temp);
        }
        else
        {
            CityList temp = cityList.get(0);
            List<Long> tempLong = temp.getIdList();
            tempLong.add(profile.getId());

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(profile.getBasicPersonalDetails().getCity()));
            Update update = new Update();
            update.set(L,tempLong);
            mongoTemplate.updateFirst(query,update, CityList.class);
        }

    }

    /**for adding to religion list */
    @Override
    public void addtoReligionList(ProfileDetails profile)
    {
        List<ReligionList> religionList = religionRepository.getReligionList(profile.getBasicPersonalDetails().getReligion());
        if(religionList.isEmpty())
        {
            ReligionList temp = new ReligionList();
            temp.setReligion(profile.getBasicPersonalDetails().getReligion());
            List<Long> templong = new ArrayList<>();
            templong.add(profile.getId());
            temp.setIdList(templong);
            religionRepository.save(temp);
        }
        else
        {
            ReligionList temp = religionList.get(0);
            List<Long> tempLong = temp.getIdList();
            tempLong.add(profile.getId());

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(profile.getBasicPersonalDetails().getReligion()));
            Update update = new Update();
            update.set(L,tempLong);
            mongoTemplate.updateFirst(query,update, ReligionList.class);
        }
    }
}
