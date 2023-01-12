package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    /**
     * save user method
     */
    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("manee@gmail.com");
        user.setPassword("mane");
        user.setFirstName("mane");
        user.setLastName("mane");

        User save = repo.save(user);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isGreaterThan(0);


    }

    /**
     * get all details on console output
     */
    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * user update option
     */
    @Test
    public void testUpdate() {
        Optional<User> optionalUser = repo.findById(1);
        User user = optionalUser.get();
        user.setPassword("sample123");
        repo.save(user);

        User updateUser = repo.findById(user.getId()).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("sample123");
    }

    /**
     * choose user by id
     */
    @Test
    public void testGet() {
        Optional<User> optionalUser = repo.findById(1);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    /**
     * delete by given id
     */
    @Test
    public void testDelete() {
        repo.deleteById(5);
        Optional<User> optionalUser = repo.findById(5);
        Assertions.assertThat(optionalUser).isNotPresent();
    }


}
