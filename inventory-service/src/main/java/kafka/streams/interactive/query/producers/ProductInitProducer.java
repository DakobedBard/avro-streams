package kafka.streams.interactive.query.producers;

import kafka.streams.interactive.query.bean.ProductDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductInitProducer {
//    public List<ProductDTO> getUsers() {
//        List<ProductDTO> products = getRoles();
//        List<User> users = csvDataLoader.loadObjectList(User.class, SetupData.USERS_FILE);
//        List<String[]> usersRoles = csvDataLoader.
//                loadManyToManyRelationship(SetupData.USERS_ROLES_FILE);
//
//        for (String[] userRole : usersRoles) {
//            User user = findByUserByUsername(users, userRole[0]);
//            Set<Role> roles = user.getRoles();
//            if (roles == null) {
//                roles = new HashSet<Role>();
//            }
//            roles.add(findRoleByName(allRoles, userRole[1]));
//            user.setRoles(roles);
//        }
//        return users;
//    }



    public static void main(String[] args){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("stack/db/products.csv"))) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
