package org.example.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueAvatarNamesChecker {

    public static boolean checkAvatarNamesUnique(List<String> avatarLinks){
        Set<String> uniqueLinks = new HashSet<>();
        for(String avatarLink: avatarLinks){

            if(
                    !uniqueLinks.add(avatarLink.substring(avatarLinks.lastIndexOf("/")+1))
            ){
                return false;
            }
        }
        return true;
    }
}
