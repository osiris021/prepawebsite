package com.prepaWeb.Components.UserComponent;

import com.prepaWeb.Repositories.UserProfileRepository.UserProfile;
import com.prepaWeb.Repositories.UserProfileRepository.UserProfileRepository;
import com.prepaWeb.Repositories.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserComponent {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public void createUser(UserProfile user_profile) {

        UUID uuid = UUID.randomUUID();

        user_profile = checkIfFieldsAreEmpty(user_profile);
        updateUserProfile(uuid.toString(), user_profile);

        userRepository.createUser(
                user_profile.getEmail(),
                user_profile.getPassword()
        );
    }

    private UserProfile checkIfFieldsAreEmpty(UserProfile user_profile) {
        if ("".equals(user_profile.getSchool())){
            user_profile.setSchool("Non Communiquée");
        }
        if ("".equals(user_profile.getActual_job())){
            user_profile.setActual_job("Non Communiqué");
        }
        if ("".equals(user_profile.getCity())){
            user_profile.setCity("Non Communiqué");
        }
        if ("".equals(user_profile.getCountry())){
            user_profile.setCountry("Non Communiqué");
        }
        if ("".equals(user_profile.getCountry())){
            user_profile.setCountry("Non Communiqué");
        }
        if ("".equals(user_profile.getDescription())){
            user_profile.setDescription("Non Communiqué");
        }
        return user_profile;
    }

    public String getUserProfileList() {

        StringBuilder toReturn = new StringBuilder();
        toReturn.append("[");

        List<UserProfile> userProfileList = userProfileRepository.getUserProfileList();
        for(UserProfile up : userProfileList){
            toReturn.append("{");
            toReturn.append("\"student_id\":\"" + up.getStudent_id() + "\",");
            toReturn.append("\"student_name\":\"" + up.getName() + "\",");
            toReturn.append("\"student_firstname\":\"" + up.getFirstname() + "\",");
            toReturn.append("\"student_mail\":\"" + up.getEmail() + "\",");
            toReturn.append("\"student_year\":\"" + up.getYear() + "\",");
            toReturn.append("\"student_actual_job\":\"" + up.getActual_job() + "\",");
            toReturn.append("\"student_school\":\"" + up.getSchool() + "\",");
            toReturn.append("\"student_city\":\"" + up.getCity() + "\",");
            toReturn.append("\"student_country\":\"" + up.getCountry() + "\",");
            toReturn.append("\"student_promotion\":\"" + up.getPromotion() + "\"");
            toReturn.append("},");
        }

        toReturn.setLength(toReturn.length() - 1);
        toReturn.append("]");

        return toReturn.toString();
    }

    public UserProfile getUserDetails(String id) {
        UserProfile userProfile = new UserProfile();
        userProfile = userProfileRepository.getUserProfile(id);
        return userProfile;
    }

    public String getUserProfileId() {

        String toReturn = "";
        String email = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        List<UserProfile> studentList = userProfileRepository.getUserProfileList();
        for (UserProfile up : studentList) {
            if (email.equals(up.getEmail())){
                toReturn = up.getStudent_id();
            }
        }
        return toReturn;
    }

    public void updateUserProfile(String profileId, UserProfile up) {

        if("".equals(up.getPassword())){
            userProfileRepository.updateUserProofileWithoutPassword(
                    profileId,
                    up.getName(),
                    up.getFirstname(),
                    up.getEmail(),
                    up.getSchool(),
                    up.getYear(),
                    up.getActual_job(),
                    up.getCity(),
                    up.getCountry(),
                    up.getPromotion()
            );
        }

        userProfileRepository.createUserProfile(
                profileId,
                up.getName(),
                up.getFirstname(),
                up.getEmail(),
                up.getPassword(),
                up.getSchool(),
                up.getYear(),
                up.getActual_job(),
                up.getCity(),
                up.getCountry(),
                up.getPromotion()
        );
    }
}
